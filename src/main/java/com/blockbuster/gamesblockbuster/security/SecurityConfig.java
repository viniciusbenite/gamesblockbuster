//package com.blockbuster.gamesblockbuster.security;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
//import org.springframework.security.oauth2.core.OAuth2TokenValidator;
//import org.springframework.security.oauth2.jwt.*;
//import org.springframework.security.web.authentication.logout.LogoutHandler;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@EnableWebSecurity
//@Order(SecurityProperties.BASIC_AUTH_ORDER)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final LogoutHandler logoutHandler;
//
//    public SecurityConfig(LogoutHandler logoutHandler) {
//        this.logoutHandler = logoutHandler;
//    }
//
//    @Value("${spring.security.oauth2.client.provider.auth0.audience}")
//    private String audience;
//
//    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
//    private String issuer;
//
//    @Bean
//    JwtDecoder jwtDecoder() {
//        NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder)
//                JwtDecoders.fromOidcIssuerLocation(issuer);
//
//        OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);
//        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
//        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);
//
//        jwtDecoder.setJwtValidator(withAudience);
//
//        return jwtDecoder;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.oauth2Login()
//                .and().logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .addLogoutHandler(logoutHandler);
//        http.authorizeRequests()
//                .mvcMatchers("/").permitAll()
//                .mvcMatchers("/api/platform").permitAll()
//                .mvcMatchers("/api/game").permitAll()
//                .mvcMatchers("/api/private-scoped").hasAuthority("SCOPE_read:messages")
//                .and()
//                .oauth2ResourceServer().jwt();
//    }
//}
