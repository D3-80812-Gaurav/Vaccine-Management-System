package com.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity // to enable spring sec frmwork support
@Configuration // to tell SC , this is config class containing @Bean methods
@EnableGlobalMethodSecurity(prePostEnabled = true) // To enable method level authorization support : pre n post
													// authorization
public class SecurityConfig {
	// dep : pwd encoder
	@Autowired
	private PasswordEncoder enc;

	// dep : custom jwt auth filter
	@Autowired
	private JwtAuthenticationFilter jwtFilter;

	@Bean
	public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception {
		System.out.println("Inside security filter");

		http.
		// disable CSRF token generation n verification
				csrf().disable().cors().and().authorizeRequests()
				.antMatchers("/passenger/register", "/passenger/login", "/driver/register", "/driver/login").permitAll()
				.antMatchers("/passenger").hasRole("P").antMatchers("/driver").hasRole("D").anyRequest().authenticated()
				.and().httpBasic().and()

				// to tell spring sec : not to use HttpSession to store user's auth details
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				// inserting jwt filter before sec filter
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();

	}

	// configure AuthMgr as a spring bean
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
