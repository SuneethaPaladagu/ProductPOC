package com.company.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  /**
   * This section defines the user accounts which can be used for
   * authentication as well as the roles each user has.
   */
	
	@Autowired
	DailyExpenseAccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	private CustomAuthenticationSuccessHandler authenticationSuccessHandler;
	
  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.inMemoryAuthentication()
      .withUser("sunitha").password("sunitha").roles("USER").and()
      .withUser("nisum").password("nisum").roles("USER", "ADMIN");
  }

  /**
   * This section defines the security policy for the app.
   * - BASIC authentication is supported (enough for this REST-based demo)
   * - /employees is secured using URL security shown below
   * - CSRF headers are disabled since we are only testing the REST interface,
   *   not a web one.
   *
   * NOTE: GET is not shown which defaults to permitted.
   */
  
  //spring security old
/* @Override
  protected void configure(HttpSecurity http) throws Exception {

    http
      .httpBasic().and()
      .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/addCar").hasRole("ADMIN")
        .antMatchers(HttpMethod.PUT, "/addCar").hasRole("ADMIN")
        .antMatchers(HttpMethod.PATCH, "/addCar").hasRole("ADMIN").and()
      .csrf().disable();
  }*/
  @Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/images/**", "/js/**");
	}
	
 @Override
 protected void configure(HttpSecurity http) throws Exception {
	 http.csrf().disable();
     http
         .authorizeRequests()
             .anyRequest().authenticated()
             .and()
         .formLogin()
             .loginPage("/login")
             .permitAll().successForwardUrl("/main");
 }

}
