package org.jesuitasrioja.proyectoFinalEval.configurations.security;

import org.jesuitasrioja.proyectoFinalEval.configurations.security.jwt.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	private final AuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	private final JwtAuthorizationFilter jwtAuthorizationFilter;

	@Bean
	@Override
	// Este metodo expone el mecanismo de autenticacion para poder utilizarlo en un
	// filtro. Por eso lleva la anotacion de Bean.
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().authorizeRequests()
				.antMatchers(HttpMethod.GET, "/alumnos/**").permitAll() // ver todos los alumnos
				.antMatchers(HttpMethod.GET, "/alumno/**").permitAll() // ver un determinado alumno
				.antMatchers(HttpMethod.GET, "/usuarios/**").permitAll() // ver todos los usuarios
				.antMatchers(HttpMethod.POST, "/alumno/**").hasRole("ADMIN") // aniadir un alumno
				.antMatchers(HttpMethod.PUT, "/alumno/**").hasRole("ADMIN") // modificar un alumno
				.antMatchers(HttpMethod.DELETE, "/alumno/**").hasRole("ADMIN") // borrar un alumno
				.antMatchers(HttpMethod.POST, "/usuario/**").hasRole("ADMIN") // aniadir un usuario
				.antMatchers(HttpMethod.PUT, "/usuario/**").hasRole("ADMIN") // modificar un alumno
				.antMatchers(HttpMethod.DELETE, "/usuario/**").hasRole("ADMIN") // borrar un alumno
				.anyRequest().permitAll().and().csrf().disable();

		// Aniadimos el filtro (lo hacemos mas adelante). Justo antes de
		// UsernamePasswordAuthenticationFilter.class.
		http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
