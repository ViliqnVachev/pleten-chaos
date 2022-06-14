package com.pletenchaos.pletenchaos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pletenchaos.pletenchaos.model.entity.enums.RoleEnum;
import com.pletenchaos.pletenchaos.repository.UserRepository;
import com.pletenchaos.pletenchaos.service.impl.UserDetailsImpl;

@Configuration
public class SecurityConfig {

	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http//
				.authorizeRequests()
				.antMatchers("/resources/**", "/static/**", "/css/**", "/fonts/**", "/images/**", "/js/**",
						"/vendor/**")
				.permitAll() // allow
																														// access
																														// to
																														// all
																														// static
																														// resources
				.antMatchers("/login", "/register")
				.anonymous() // allows access to the login page and
																			// registration for everyone
				.antMatchers("/admin/**").hasRole(RoleEnum.ADMIN.name())//
				.anyRequest().authenticated()//
				.and()//
				.formLogin()//
				.loginPage("/login")//
				.usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)//
				.passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)//
				.defaultSuccessUrl("/home")//
				.failureForwardUrl("/login-error")//
				.and()//
				.logout()//
				.logoutUrl("/logout")//
				.logoutSuccessUrl("/login")//
				.invalidateHttpSession(true).deleteCookies("JSESSIONID");
		return http.build();
	}

	@Bean
	public UserDetailsService userService(UserRepository repo) {
		return new UserDetailsImpl(repo);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new Pbkdf2PasswordEncoder();
	}

}
