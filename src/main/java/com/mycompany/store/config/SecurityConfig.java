package com.mycompany.store.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private UserDetailsService userDetailsService;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void configure(HttpSecurity security) throws Exception
    {
    	security
    	.authorizeRequests()
    		.antMatchers("/accounts-panel/**", "/**", "/registration", "/customer/**", "/webjars/**", "/h2/**", "/css/**", "/js/**").permitAll()
    //		.antMatchers("/accounts-panel/**").hasRole("ADMIN")
    		.anyRequest().authenticated()
        	.and()
        .formLogin()
        	.loginPage("/login").permitAll()
        	.successHandler(new AuthenticationSuccessHandler() {
        	    @Override
        	    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        	            Authentication authentication) throws IOException, ServletException {
        	        redirectStrategy.sendRedirect(request, response, "/login-success");
        	    }
        	})
        	.permitAll() 
        	.and()
        	.logout()
        	.logoutSuccessUrl("/login")
        	.permitAll();

    	security.csrf().disable();
    	security.headers().frameOptions().disable();
    }
    
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    	
    }
   
}
