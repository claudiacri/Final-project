package com.example.cakeme.config;

import com.example.cakeme.security.filters.CustomAuthenticationFilter;
import com.example.cakeme.security.filters.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * This is the main configuration class for security in the application. It enables web security,
 * sets up the password encoder, and sets up the security filter chain.
 */
@Configuration
@EnableWebSecurity // indicates it is a security config class using spring web security
@RequiredArgsConstructor
public class SecurityConfig {

    // UserDetailsService is an interface provided by Spring Security that defines a way to retrieve user information
    private final UserDetailsService userDetailsService;

    // Autowired instance of the AuthenticationManagerBuilder (provided by Spring Security)
    private final AuthenticationManagerBuilder authManagerBuilder;


    /**
     * Bean definition for AuthenticationManager
     *
     * @param authenticationConfiguration the instance of AuthenticationConfiguration
     * @return an instance of the AuthenticationManager
     * @throws Exception if there is an issue getting the instance of the AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Bean definition for SecurityFilterChain
     *
     * @param http the instance of HttpSecurity
     * @return an instance of the SecurityFilterChain
     * @throws Exception if there is an issue building the SecurityFilterChain
     */
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http, AuthenticationConfiguration authenticationConfiguration) throws Exception {
        // Recuperiamo l'AuthenticationManager configurato da Spring
        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();

        // Passiamo l'authenticationManager al tuo filtro personalizzato
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager);
        customAuthenticationFilter.setFilterProcessesUrl("/api/login"); // Ora risponderà a /api/login!

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/login").permitAll()

                        // 🟢 AGGIUNGI QUESTE DUE RIGHE: Sblocca i due endpoint di saluto
                        .requestMatchers(GET, "/api/greet").permitAll() // Libera a tutti
                        .requestMatchers(GET, "/api/greet/personal").hasAnyAuthority("ROLE_USER") // Solo con Token USER
                        .requestMatchers(GET, "/api/cakes/**").permitAll()
                        .requestMatchers(GET, "/api/shops/**").permitAll()
                        .requestMatchers(GET, "/api/ai/**").permitAll()
                        .anyRequest().authenticated());


        http.addFilterAt(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}