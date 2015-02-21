package org.talang.bookinventory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.talang.bookinventory.config.RestDevtoolsConfig;
import org.talang.rest.devtools.logging.Loggable;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;

@ComponentScan
@EnableAutoConfiguration
@Import(RestDevtoolsConfig.class)
@EnableJpaRepositories
@EnableAspectJAutoProxy
public class Application implements Loggable {
    @Resource
    private Environment env;

    private RelaxedPropertyResolver dataSourcePropertyResolver;

    @PostConstruct
    public void initApplication() throws IOException {
        if (env.getActiveProfiles().length == 0) {
            logger().warn("No Spring profile configured, running with default configuration");
        } else {
            logger().info("Running with Spring profile(s) : {}", env.getActiveProfiles());
            this.dataSourcePropertyResolver = new RelaxedPropertyResolver(env, "jmx.");
        }
    }



    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setShowBanner(false);
        app.run(args);
    }

}
