package com.example.jogo;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private String corsPath = "/**";
	private String[] corsAllowedOrigins = { "*" };
	private boolean corsAllowCredentials = true;
	private String[] corsAllowedMethods = { "POST", "PUT", "GET", "OPTIONS", "DELETE" };
	private String[] corsAllowedHeaders = { "x-requested-with", "content-type" };
	private long corsMaxAge = 3600;
	
	@Autowired
	 private WebApplicationContext applicationContext;
	 private MyUserDetailsService userDetailsService;
	 
	@Autowired
	DataSource dataSource;
	
	@PostConstruct
	public void completeSetup(){
		userDetailsService = applicationContext.getBean(MyUserDetailsService.class);
	}
	
	
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(encoder())
			.and()
			.authenticationProvider(authenticationProvider());
			/*
			.jdbcAuthentication()
			.dataSource(dataSource);
			*/
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring()
			.antMatchers("/resources/**");
	}
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().and()
		.logout().logoutUrl("/logout").permitAll().logoutSuccessUrl("/login").and()
		.cors().and()
		.csrf().disable();	
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider(){
		final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList(this.corsAllowedOrigins));
		configuration.setAllowedMethods(Arrays.asList(this.corsAllowedMethods));
		configuration.setAllowedHeaders(Arrays.asList(this.corsAllowedHeaders));
		configuration.setMaxAge(this.corsMaxAge);
		if (this.corsAllowCredentials) {
			configuration.setAllowCredentials(Boolean.TRUE);
		}
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration(this.corsPath, configuration);
		return source;
	}
}


