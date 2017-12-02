package org.graphflow.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration implements BeanDefinitionRegistryPostProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(SwaggerConfiguration.class);
    private static final Map<String, String> apis = new LinkedHashMap<>();

    static {
        apis.put("GraphFlow",
                "org.graphflow");
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) {
        LOG.info("postProcessBeanDefinitionRegistry: {}", SwaggerConfiguration.class);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        apis.forEach((n, p) -> {
            Docket docket = new Docket(DocumentationType.SWAGGER_2)
                    .groupName(n)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage(p))
                    .paths(PathSelectors.any())
                    .build();
            beanFactory.registerSingleton(n, docket);
        });
    }
}
