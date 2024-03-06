package com.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder enc;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(enc);
	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests().antMatchers("/**").hasRole("CITIZEN").and().formLogin();
//		
//	}
	
	public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception
	{
		http.authorizeRequests()
		.antMatchers("/login").permitAll()
		.antMatchers("/citizen/**").hasRole("CITIZEN")
		.antMatchers("/admin/**").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.httpBasic();
		return http.build();
	}
}
