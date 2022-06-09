package com.pletenchaos.pletenchaos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pletenchaos.pletenchaos.model.entity.enums.RoleEnum;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
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
				.antMatchers("/**").authenticated()//
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
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)//
				.passwordEncoder(passwordEncoder);
	}

}
