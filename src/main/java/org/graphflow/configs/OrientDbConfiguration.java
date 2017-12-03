package org.graphflow.configs;

import com.orientechnologies.orient.client.remote.OServerAdmin;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tinkerpop.gremlin.orientdb.OrientGraphFactory;
import org.graphflow.yaml.OrientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
@Configuration
public class OrientDbConfiguration {

    private final OrientConfig orientConfig;

    @Bean
    public OrientGraphFactory orientGraphFactory(OServerAdmin serverAdmin) {
        OrientGraphFactory factory = new OrientGraphFactory(
                orientConfig.getDb(),
                orientConfig.getUsername(),
                orientConfig.getPassword()
        );
        factory.setupPool(5);
        return factory;
    }

    @Bean
    public OServerAdmin orientDB() throws IOException {
        OServerAdmin server = new OServerAdmin(orientConfig.getDb())
                .connect(orientConfig.getUsername(), orientConfig.getPassword());
        if (!server.existsDatabase()) {
            server.createDatabase("graph", "local");
        }
        return server;
    }

}
