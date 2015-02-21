package org.talang.bookinventory.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.talang.rest.devtools.config.RestConfiguration;

@Configuration
@Import(RestConfiguration.class)
@ComponentScan(basePackages = {"org.talang.rest.devtools"})
public class RestDevtoolsConfig {

}
