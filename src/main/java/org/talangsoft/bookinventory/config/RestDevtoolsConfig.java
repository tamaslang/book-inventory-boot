package org.talangsoft.bookinventory.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.talangsoft.rest.devtools.config.RestConfiguration;

@Configuration
@Import(RestConfiguration.class)
@ComponentScan(basePackages = {"org.talangsoft.rest.devtools"})
public class RestDevtoolsConfig {

}
