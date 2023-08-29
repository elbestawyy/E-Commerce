package com.ecommerce.lavana.Config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

@Configuration
public class SecurityConfiguration {
    @Autowired
    private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // Disable Cross Site Request Forgery
        http.csrf().disable();

        // protect endpoint /api/type/secure
        http.authorizeRequests(configurer ->
                configurer.
                        antMatchers("/api/brand/secure/**",
                                "/api/category/secure/**",
                                "/api/product/secure/**",
                                "/api/subcategory/secure/**",
                                "/api/wishlist/secure/**",
                                "/api/review/secure/**",
                                "/api/message/secure/**",
                                "/api/cart/secure/**",
                                "/api/coupon/secure/**",
                                "/api/checkout/secure/**")
                        .authenticated())
                .oauth2ResourceServer()
                .jwt();

        // add CORS filters
        http.cors();

        // add content negotiation strategy
        http.setSharedObject(ContentNegotiationStrategy.class,
                new HeaderContentNegotiationStrategy());

        // Force a non-Empty response body for 401's to make the response finally
        Okta.configureResourceServer401ResponseBody(http);

        return http.build();
    }
}
