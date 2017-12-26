package com.banking.young.config;

import com.banking.young.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private CustomUserService userDetailsService;

    protected void configure(HttpSecurity http) throws Exception{
//        http.authorizeRequests().antMatchers("/**")
//                .hasAnyAuthority("ROLE_CUSTOMER", "ROLE_ADMIN").and()
//                .formLogin().and().logout().and().rememberMe().and().headers()
//                .cacheControl().and().xssProtection();

//        http.formLogin().loginPage("/index").loginProcessingUrl("/login").successForwardUrl("/index");


        http.authorizeRequests()
                    .antMatchers("/accounts/**", "/register", "/registerBanker", "/resources/**", "/actuator", "/autoconfig").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/loginPage")
                    .loginProcessingUrl("/perform_login")
                    .permitAll()
                    .and()
                .logout().permitAll()
                .and()
                .csrf().disable();

    }

//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);

//        auth.inMemoryAuthentication().withUser("admin").password("admin")
//                .authorities("ROLE_ADMIN").and().withUser("cust1")
//                .password("cust1").authorities("ROLE_CUSTOMER").and()
//                .withUser("cust2").password("cust2")
//                .authorities("ROLE_CUSTOMER");
//    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }

}
