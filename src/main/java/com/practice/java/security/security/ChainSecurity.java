package com.practice.java.security.security;

import com.practice.java.security.entities.other.Role;
import com.practice.java.security.security.filter.FilterSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@EnableWebSecurity
//@EnableMethodSecurity(prePostEnabled = true)
@Configuration
public class ChainSecurity {


    @Autowired
    private AuthenticationProvider provider;

    @Autowired
    private FilterSession filterSession;

    @Autowired
    private AuthenticationEntryPoint authEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Bean
    SecurityFilterChain chain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
//                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**","/auth/**"))
                .headers(headers -> headers.frameOptions(e-> e.sameOrigin())
                )
                .sessionManagement(sessMang-> sessMang.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(provider)
                .addFilterBefore(filterSession, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(exchanges -> {
                    exchanges.requestMatchers("/h2-console/**").permitAll();
                    exchanges.requestMatchers("/auth/**").permitAll();

                    exchanges.requestMatchers(HttpMethod.GET,"/product/all","/product/**")
                                    .hasAnyRole(Role.ADMIN.name(),Role.CUSTOMER.name() , Role.USER.name());

                    exchanges.requestMatchers(HttpMethod.POST,"/product/save")
                            .hasAnyRole(Role.ADMIN.name(), Role.CUSTOMER.name());

                    exchanges.requestMatchers(HttpMethod.PUT,"/product/update")
                            .hasAnyRole(Role.ADMIN.name(), Role.CUSTOMER.name());

//                    exchanges.requestMatchers("/product/update","/product/save")
//                            .hasAnyRole(Role.ADMIN.name(), Role.CUSTOMER.name());

                    exchanges.requestMatchers(RegexRequestMatcher.regexMatcher(HttpMethod.DELETE,"/product/[0-9]*/delete"))
                            .hasRole(Role.ADMIN.name());

                    exchanges.anyRequest().authenticated();
                }).exceptionHandling(ex->{
                    ex.authenticationEntryPoint(authEntryPoint);
                    ex.accessDeniedHandler(accessDeniedHandler);
                })
                .build();
    }
}
