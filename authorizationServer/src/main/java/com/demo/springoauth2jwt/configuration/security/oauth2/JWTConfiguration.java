package com.demo.springoauth2jwt.configuration.security.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.demo.springoauth2jwt.configuration.security.userdetails.CustomUserDetailsService;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class JWTConfiguration {
    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }

    @Bean
    public TokenEnhancerChain tokenEnhancerChain() {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        List tokenEnhancerList = new ArrayList();
        tokenEnhancerList.add(new JWTTokenEnhancer());
        tokenEnhancerList.add(tokenEnhancer());

        tokenEnhancerChain.setTokenEnhancers(tokenEnhancerList);
        return tokenEnhancerChain;
    }

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setAccessTokenConverter(defaultAccessTokenConverter());

        jwtAccessTokenConverter.setSigningKey("123");

        return jwtAccessTokenConverter;
    }

    @Bean
    public DefaultAccessTokenConverter defaultAccessTokenConverter() {
        DefaultAccessTokenConverter converter = new DefaultAccessTokenConverter();

        DefaultUserAuthenticationConverter userConverter = new DefaultUserAuthenticationConverter();
        userConverter.setUserDetailsService(userDetailsService);

        converter.setUserTokenConverter(userConverter);

        return converter;
    }

    @Bean
    public AuthorizationServerTokenServices defaultTokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setClientDetailsService(clientDetailsService);
        defaultTokenServices.setTokenEnhancer(tokenEnhancerChain());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }
}

