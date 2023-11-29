package com.dawii.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.dawii.jwt.JwtEntryPoint;
import com.dawii.jwt.JwtTokenFilter;
import com.dawii.service.UserDetailsImp;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MainSecurity {

	@Autowired
	private UserDetailsImp userDetailsImp;
	
	@Autowired
	private JwtEntryPoint jwtEntryPoint;
	
	@Bean
	public JwtTokenFilter jwtTokenFilter() {
		return new JwtTokenFilter();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		builder.userDetailsService(userDetailsImp).passwordEncoder(passwordEncoder());
		AuthenticationManager authenticationManager = builder.build();
		
		/*Configuracion CORS*/
		CorsConfiguration config = new CorsConfiguration();
	    config.addAllowedOrigin("http://localhost:4200");
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("*");
	    
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
		
		http.authenticationManager(authenticationManager);
		http.csrf(csrf -> csrf.disable());
		http.cors(cors -> cors.configurationSource(source));
		http.authorizeHttpRequests(auth -> auth.
				requestMatchers("/api/usuario/**").permitAll().
				requestMatchers("/api/ubigeo/**").permitAll().
				requestMatchers("/api/habitacion/**").permitAll().
				requestMatchers("/api/reservacion/**").permitAll().
				requestMatchers("/api/cliente/**").permitAll().
				anyRequest().authenticated());
		http.exceptionHandling(eh -> eh.authenticationEntryPoint(jwtEntryPoint));
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(jwtTokenFilter(),UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
}
