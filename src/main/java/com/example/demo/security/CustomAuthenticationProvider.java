package com.example.demo.security;

import com.example.demo.constant.TestDataConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author dustforest
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String token = authentication.getCredentials().toString();
        if (StringUtils.isEmpty(token)) {
            throw new BadCredentialsException("no token");
        }

        String userName = token;
        if (TestDataConstants.USER_NAME.equals(userName)) {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
            return new UsernamePasswordAuthenticationToken(userName, token, userDetails.getAuthorities());
        } else {
            return new UsernamePasswordAuthenticationToken(userName, token);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
