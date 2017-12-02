package org.graphflow.configs;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.orient.commons.repository.config.EnableOrientRepositories;
import org.springframework.data.orient.object.OrientObjectDatabaseFactory;
import org.springframework.data.orient.object.repository.support.OrientObjectRepositoryFactoryBean;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@AllArgsConstructor
@Configuration
@EnableOrientRepositories(
        basePackages = "org.graphflow",
        repositoryFactoryBeanClass = OrientObjectRepositoryFactoryBean.class)
public class OrientDbConfiguration {

    private final OrientObjectDatabaseFactory factory;

    @PostConstruct
    @Transactional
    public void registerEntities() {
        factory.db().getEntityManager()
                .registerEntityClasses("org.graphflow.models");
    }
}
