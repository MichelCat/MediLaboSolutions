package com.medilabo.gatewayserver.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build();
        UserDetails adminUser = User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();
        return new MapReactiveUserDetailsService(user, adminUser);
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.formLogin()
                .authenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler("/patient/list"))
                .and()
                .authorizeExchange()
                .anyExchange().authenticated()
                .and()
                .logout()
                .and()
                .csrf().disable()
                .httpBasic(withDefaults());
//        http.csrf(csrfSpec -> csrfSpec.disable())
//                .authorizeExchange(exchanges -> exchanges
//                        .anyExchange().authenticated()
//                )
//                .httpBasic(withDefaults())
//                .formLogin(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
