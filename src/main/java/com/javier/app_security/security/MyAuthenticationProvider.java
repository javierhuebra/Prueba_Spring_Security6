package com.javier.app_security.security;

import com.javier.app_security.entities.CustomerEntity;
import com.javier.app_security.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component //Si no se pone component no se carga al contenedor de spring
@AllArgsConstructor
public class MyAuthenticationProvider implements AuthenticationProvider {

    private CustomerRepository customerRepository; //No necesito el constructor por el allargsconstructor
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();

        final var customerFromDb = this.customerRepository.findByEmail(username); //El var se usa a partir de java 10, aca lo use para usar el optional de otra manera a la que venia usandolo siempre
        final CustomerEntity customer = customerFromDb.orElseThrow(() -> new BadCredentialsException("Invalid credentials"));
        final var customerPwd= customer.getPassword();

        if (passwordEncoder.matches(password, customerPwd)) {
            final var authorities = List.of(new SimpleGrantedAuthority(customer.getRole()));
            return new UsernamePasswordAuthenticationToken(username, password, authorities);
        } else {
            throw new BadCredentialsException("Invalid credentials");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
