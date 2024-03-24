package com.javier.app_security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


import javax.sql.DataSource;

@Configuration
public class SecirityConfing {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(auth ->
                auth.requestMatchers("/loans","/balance", "accounts", "/cards").authenticated()
                    .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    /*
    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager(){ // Creo dos usuarios con roles diferentes en la memoria implementacion de userDetailService
        UserDetails admin = User.withUsername("admin")
                .password("to_be_encoded")
                .authorities("ADMIN")
                .build();
        UserDetails user = User.withUsername("user")
                .password("to_be_encoded")
                .authorities("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
    */

//    @Bean
//    UserDetailsService userDetailsService(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }

//    @Bean // Bean se pone para que tome la config
//    PasswordEncoder passwordEncoder(){ // Esto es para desarrollar nuestra seguridad, para que ignore la encriptación de la contraseña que exige Spring Security
//        return NoOpPasswordEncoder.getInstance();
//    }
}
