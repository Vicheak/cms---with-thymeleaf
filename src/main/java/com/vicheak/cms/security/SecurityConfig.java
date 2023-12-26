package com.vicheak.cms.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    /*@Bean
    public InMemoryUserDetailsManager configureInMemoryUserDetailsManager() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        //create in-memory users
        //use {noop} prefix for password encoder
        UserDetails adminUser = User.withUsername("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .build();

        UserDetails editorUser = User.withUsername("editor")
                .password("{noop}editor")
                .roles("EDITOR")
                .build();

        UserDetails subscriberUser = User.withUsername("subscriber")
                .password("{noop}subscriber")
                .roles("SUBSCRIBER")
                .build();

        manager.createUser(adminUser);
        manager.createUser(editorUser);
        manager.createUser(subscriberUser);

        return manager;
    }*/

    @Bean
    public DaoAuthenticationProvider configureAuthProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public SecurityFilterChain configureSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //customize your security logic
        httpSecurity.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/auth/**", "/resources/**").permitAll();

            //configure category security
            auth.requestMatchers(
                    "/category/form",
                    "/category/{id}/delete",
                    "/category/{id}/edit").hasRole("ADMIN");
            auth.requestMatchers(HttpMethod.GET,
                    "/category/**").hasAnyRole("ADMIN", "EDITOR");
            auth.requestMatchers(HttpMethod.POST,
                    "/category/**").hasRole("ADMIN");

            //configure content security
            auth.requestMatchers(
                    "/content/form",
                    "/content/{id}/delete",
                    "/content/{id}/edit").hasAnyRole("ADMIN", "EDITOR");
            auth.requestMatchers(HttpMethod.POST,
                    "/content/**").hasAnyRole("ADMIN", "EDITOR");

            //configure user security
            auth.requestMatchers("/user/**").hasRole("ADMIN");

            auth.anyRequest().authenticated();
        });

        //configure default form login
        //httpSecurity.formLogin(Customizer.withDefaults());

        //configure form login with customization
        //default parameter : username and password
        httpSecurity.formLogin(formLogin ->
                formLogin.usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/content", true)
                        .loginPage("/auth/login") //redirect to this route when need to authenticate
                        .loginProcessingUrl("/auth/login") //method must be post for security
        );

        //must handle configuration of form logout
        httpSecurity.logout(logout ->
                logout.logoutUrl("/auth/logout") //method must be post for security
                        .logoutSuccessUrl("/auth/login"));

        //for session management, Spring will handle for us

        return httpSecurity.build();
    }

}
