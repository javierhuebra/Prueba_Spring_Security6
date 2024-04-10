package com.javier.app_security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.List;

@Configuration
//@EnableMethodSecurity
public class SecurityConfing {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        var requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        http.authorizeHttpRequests(auth ->
               // auth.requestMatchers("/loans","/balance", "accounts", "/cards")
                        auth
                                .requestMatchers("/loans", "/balance").hasRole("USER")
                                .requestMatchers("/accounts", "/cards").hasRole("ADMIN")
                                .anyRequest().permitAll()
                )
                .formLogin(Customizer.withDefaults()) // Este método configura un formulario de inicio de sesión básico.
                .httpBasic(Customizer.withDefaults()); // Esto permite la autenticación mediante el envío de credenciales en el encabezado Authorization de la solicitud HTTP.
        http.cors(cors -> corsConfigurationSource());
        http.csrf(csrf -> csrf
                .csrfTokenRequestHandler(requestHandler)
                .ignoringRequestMatchers("/welcome","/about_us","/authenticate")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class);

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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
        //corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        corsConfiguration.setAllowedOrigins(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
