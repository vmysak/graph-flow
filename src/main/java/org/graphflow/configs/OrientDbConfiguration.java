package org.graphflow.configs;

import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.graphflow.yaml.OrientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@AllArgsConstructor
@Configuration
public class OrientDbConfiguration {

    private final OrientConfig orientConfig;

    @Bean
    public OrientGraphFactory orientGraphFactory(OrientDB orientDB) {
        return new OrientGraphFactory(
                orientConfig.getUrl() + "/" + orientConfig.getDb(),
                orientConfig.getUsername(),
                orientConfig.getPassword()
        );
    }

    @Bean
    public OrientDB orientDB() {
        return new OrientDB(
                orientConfig.getUrl(),
                orientConfig.getUsername(),
                orientConfig.getPassword(),
                OrientDBConfig.defaultConfig());
    }

}
