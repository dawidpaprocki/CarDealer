package com.example.car_dealer;

import com.example.car_dealer.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(appUserDetailsService);
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/cars/").hasRole("TRADER")
                .antMatchers("/buyCar/").hasRole("TRADER")
                .antMatchers("/buyCar/").hasRole("MANAGER")
                .antMatchers("/cars/waitingCarsSell/").hasRole("MANAGER")
                .antMatchers("/cars/").hasRole("MANAGER")
                .antMatchers("/cars/waitingCarsBuy/").hasRole("MANAGER")
                .antMatchers("/addWorker/").hasRole("MANAGER")
                .anyRequest().permitAll()
                .and()
                .formLogin().defaultSuccessUrl("/")
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .httpBasic();
    }
}
