package org.talangsoft.bookinventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.talangsoft.bookinventory.config.RestDevtoolsConfig;
import org.talangsoft.rest.devtools.logging.Loggable;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;

@ComponentScan
@EnableAutoConfiguration
@Import(RestDevtoolsConfig.class)
@EnableJpaRepositories
public class Application implements Loggable {
    @Resource
    private Environment env;

    @PostConstruct
    public void initApplication() throws IOException {
        if (env.getActiveProfiles().length == 0) {
            logger().warn("No Spring profile configured, running with default configuration");
        } else {
            logger().info("Running with Spring profile(s) : {}", env.getActiveProfiles());
        }
    }



    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setShowBanner(false);
        app.run(args);
    }

}
