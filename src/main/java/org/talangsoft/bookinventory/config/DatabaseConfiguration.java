package org.talangsoft.bookinventory.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.talangsoft.rest.devtools.logging.Loggable;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(value = {DatabaseConfiguration.REPOSITORY_LOCATION}, entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager")
public class DatabaseConfiguration implements Loggable{

    public static final String REPOSITORY_LOCATION = "org.talang.bookinventory.repository";

    public static final String CHANGELOG_LOCATION = "classpath:config/liquibase/db-changelog.xml";

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;


    @Bean(name = "liquibase")
    public SpringLiquibase liquibase() {
        logger().debug("Configuring Liquibase for Ref data");
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(CHANGELOG_LOCATION);

        return liquibase;
    }
}

