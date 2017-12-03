package org.graphflow.startup;

import org.apache.commons.collections4.CollectionUtils;
import org.graphflow.commons.Constants;
import org.reflections.Reflections;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Stateful
 */
@Service
@ApplicationScope
public class AnnotationLoaderService {

    private Reflections typeReflections;

    private Map<Class<? extends Annotation>, Map<String, Class<?>>> annotatedClasses = new LinkedHashMap<>();

    @PostConstruct
    public void init() {
        initTypeReflections();
    }

    private void initTypeReflections() {
        typeReflections = new Reflections(Constants.BASE_PKG);
    }

    public synchronized Map<String, Class<?>> loadClasses(Class<? extends Annotation> annotation) {
        Map<String, Class<?>> annotated = initOrGet(annotation);
        if (CollectionUtils.isNotEmpty(annotated.entrySet())) {
            return annotated;
        }
        Set<Class<?>> classes = typeReflections.getTypesAnnotatedWith(annotation);
        if (CollectionUtils.isNotEmpty(classes)) {
            classes.forEach(c -> annotated.put(c.getSimpleName(), c));
        }
        return annotated;
    }

    private Map<String, Class<?>> initOrGet(Class<? extends Annotation> annotation) {
        Map<String, Class<?>> previousLoaded = annotatedClasses.get(annotation);
        if (previousLoaded != null && CollectionUtils.isNotEmpty(previousLoaded.entrySet())) {
            return previousLoaded;
        }
        annotatedClasses.put(annotation, new HashMap<>());
        return annotatedClasses.get(annotation);
    }

}
