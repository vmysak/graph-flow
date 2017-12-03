package org.graphflow.configs;

import com.orientechnologies.orient.client.remote.OServerAdmin;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tinkerpop.gremlin.orientdb.OrientGraphFactory;
import org.graphflow.yaml.OrientYamlConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
@Configuration
public class OrientDbConfiguration {

    private final OrientYamlConfig orientYamlConfig;

    @Bean
    public OrientGraphFactory orientGraphFactory(OServerAdmin serverAdmin) {
        OrientGraphFactory factory = new OrientGraphFactory(
                orientYamlConfig.getDb(),
                orientYamlConfig.getUsername(),
                orientYamlConfig.getPassword()
        );
        factory.setupPool(5);
        return factory;
    }

    @Bean
    public OServerAdmin orientDB() throws IOException {
        OServerAdmin server = new OServerAdmin(orientYamlConfig.getDb())
                .connect(orientYamlConfig.getUsername(), orientYamlConfig.getPassword());
        if (!server.existsDatabase()) {
            server.createDatabase("graph", null);
        }
        return server;
    }

}
