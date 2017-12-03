package org.graphflow.services;

import org.apache.commons.collections4.CollectionUtils;
import org.graphflow.commons.Constants;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@ApplicationScope
public class AnnotationLoaderService {

    private List<ClassLoader> classLoadersList;
    private Reflections typeReflections;

    private Map<Class<? extends Annotation>, Set<Class<?>>> annotatedClasses = new LinkedHashMap<>();

    @PostConstruct
    public void init() {
        initClassloaderList();
        initTypeReflections();
    }

    private void initClassloaderList() {
        classLoadersList = new LinkedList<>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());
    }

    private void initTypeReflections() {
        typeReflections = new Reflections(Constants.BASE_PKG);
    }

    public Set<Class<?>> loadClasses(Class<? extends Annotation> annotation) {
        annotatedClasses.put(annotation, new LinkedHashSet<>());

        Set<Class<?>> classes = typeReflections.getTypesAnnotatedWith(annotation);
        if (!CollectionUtils.isEmpty(classes)) {
            annotatedClasses.put(annotation, classes);
        }
        return annotatedClasses.get(annotation);
    }

}
