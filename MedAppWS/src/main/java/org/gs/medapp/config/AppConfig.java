package org.gs.medapp.config;

import org.gs.medapp.security.SecurityConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.gs.medapp")
@Import({ SecurityConfig.class })
public class AppConfig 
{

}
