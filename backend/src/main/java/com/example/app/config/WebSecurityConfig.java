package com.example.app.config;

import com.example.app.config.jwt.JwtAuthenticationEntryPoint;
import com.example.app.config.jwt.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtRequestFilter jwtRequestFilter;
	private final UserDetailsService jwtUserDetailsService;
	private final AppConfig appConfig;

	public WebSecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtRequestFilter jwtRequestFilter,
			UserDetailsService jwtUserDetailsService, AppConfig appConfig) {
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
		this.jwtRequestFilter = jwtRequestFilter;
		this.jwtUserDetailsService = jwtUserDetailsService;
		this.appConfig = appConfig;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeHttpRequests(auth -> auth
				.requestMatchers(HttpMethod.GET, "/api/products/**").permitAll()
				.requestMatchers("/authenticate", "/register", "/swagger-ui/**", "/v3/**", "/swagger-resources/**", "/",
						"/js/**", "/css/**", "/img/**", "/demo/**", "/api/auth/**")
				.permitAll().anyRequest().authenticated())
				.exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
}