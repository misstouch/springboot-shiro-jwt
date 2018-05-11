package com.misstouch.springbootshirojwt.common;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author hechao
 * @date create in 13:34 2018/5/11/011
 */
public class JWTToken implements AuthenticationToken {

    /**
     * 密钥
     */
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
