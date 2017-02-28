package org.gs.medapp.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private CustomAuthenticationEntryPoint unauthorizedHandler;
	
	@Autowired
	private JwtAuthenticationProvider authenticationProvider;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManager() 
			throws Exception 
	{
		return new ProviderManager(Arrays.asList(authenticationProvider));
	}
	
	@Bean
	public JwtAuthenticationFilter authenticationTokenFilterBean() 
			throws Exception
	{
		JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter("/**");
		jwtAuthenticationFilter.setAuthenticationManager(authenticationManager());
		jwtAuthenticationFilter.setAuthenticationSuccessHandler(new JwtAuthenticationSuccessHandler());
		
		return jwtAuthenticationFilter;
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
                .antMatchers("/users/authenticate");
    }
	
	@Override
	protected void configure( HttpSecurity http ) throws Exception
	{
		http.csrf()
				.disable()
			.authorizeRequests()
				.antMatchers("/users/authenticate").permitAll()
				.anyRequest().authenticated()
				.and()
			.exceptionHandling()
				.authenticationEntryPoint(unauthorizedHandler)
				.and()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
		
		http.headers()
				.cacheControl();
	}
}
