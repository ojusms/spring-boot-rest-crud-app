package com.SpringBoot.RestCrudApp.Security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


// Spring Security Configuration class with users having roles which overrides application.properties configs

@Configuration
public class SecurityConfig {

	// add support for JDBC. No need for hard coded user values, it reads from the DB
	
	@Bean
	public UserDetailsManager userDetailsManager(DataSource datasource) {
		
		// below code for when using custom tables/schema for users and authorities  in the database	
		
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(datasource);
		
		// define query to retrieve user details. Here ? is the value provided in the username filed by REST client
		jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id, pw, active from members where user_id=?");;
		
		// define query to retrieve role details
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");
		
		return jdbcUserDetailsManager;
		
		/*
		 *  this line is enough in this method if using the Spring Security default table schema of 'users' 
		 *  and 'authorities' tables in the databsae
		 *  
		 *  return new JdbcUserDetailsManager(datasource);
		 */
	}
	
	// add security filter to implement Role Based Access Control for API end points
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(configurer ->
		configurer
		.requestMatchers(HttpMethod.GET,"/api/employees").hasRole("EMPLOYEE")
		.requestMatchers(HttpMethod.GET,"/api/employees/**").hasRole("EMPLOYEE")
		.requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
		.requestMatchers(HttpMethod.PUT,"/api/employees").hasRole("MANAGER")
		.requestMatchers(HttpMethod.PATCH,"/api/employees/**").hasRole("MANAGER")
		.requestMatchers(HttpMethod.DELETE,"/api/employees/**").hasRole("ADMIN")
		);
		
		// use HTTP Basic authentication
		http.httpBasic(Customizer.withDefaults());
		
		// disable Cross Site Request Forgery (CSRF)
		// generally not needed for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
		http.csrf(csrf -> csrf.disable());
		
		return http.build();
	}

	/* // previous version of creating hard coded user roles 
	@Bean
	public InMemoryUserDetailsManager userDetailsManager( ) {
		
		UserDetails john = User.builder()
				.username("john")
				.password("{noop}test123") // noop - no operation, stores as plain text as opposed to encrypted
				.roles("EMPLOYEE")
				.build();
		
		UserDetails mary = User.builder()
				.username("mary")
				.password("{noop}test123")
				.roles("EMPLOYEE","MANAGER")
				.build();
		
		UserDetails susan = User.builder()
				.username("susan")
				.password("{noop}test123")
				.roles("EMPLOYEE","MANAGER","ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(john, mary, susan);
	}
	*/
	
}
