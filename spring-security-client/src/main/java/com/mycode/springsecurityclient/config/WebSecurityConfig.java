package com.mycode.springsecurityclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    private static final String[] WHITE_LIST_URLS = {
            "/hello",
            "/register",
            "/verifyRegistration*",
            "/resendVerifyToken*",
            "/resetPassword*",
            "/savePassword*"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }


//    Starting from Spring Security 4.x, the CSRF protection is enabled by default.
//    This default configuration adds the CSRF token to the HttpServletRequest attribute named _csrf.

//    If we need to, we can disable this configuration:
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        // for all the urls mentioned in white_list there is no need of authentication and also we can make request to urls since csrf is disabled
        http
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(WHITE_LIST_URLS).permitAll().requestMatchers("/api/**").authenticated())
                .oauth2Login(oauth2login -> oauth2login.loginPage("/oauth2/authorization/api-client-oidc"))
                .oauth2Client(Customizer.withDefaults());

        return http.build();
    }
}
