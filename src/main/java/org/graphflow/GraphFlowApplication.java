package org.graphflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
        GraphFlowApplicationConfig.class
})
public class GraphFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphFlowApplication.class, args);
    }
}
