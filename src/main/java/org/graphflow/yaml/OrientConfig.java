package org.graphflow.yaml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties("orient")
@Configuration
public class OrientConfig {

    private String url;
    private String username;
    private String password;
    private String db;
}
