package com.misstouch.springbootshirojwt.security;

import com.misstouch.springbootshirojwt.common.ReslutBean;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hechao
 * @date create in 17:13 2018/5/11/011
 */
@ControllerAdvice
public class AuthorizationExceptionAdvice {

    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public ReslutBean handleAuthorizationException(AuthorizationException e){
        return new ReslutBean(200,"error","no have Authorization");
    }

}
