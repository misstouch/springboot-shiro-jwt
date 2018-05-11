package com.misstouch.springbootshirojwt.security;

import com.misstouch.springbootshirojwt.common.JWTToken;
import com.misstouch.springbootshirojwt.entity.Role;
import com.misstouch.springbootshirojwt.entity.User;
import com.misstouch.springbootshirojwt.repository.UserRepository;
import com.misstouch.springbootshirojwt.utils.JwtUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author hechao
 * @date create in 13:14 2018/5/11/011
 */
public class MyShiroRelam extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(MyShiroRelam.class);

    @Autowired
    private UserRepository userRepository;

    /**
     * 必须重写此方法，不然Shiro会报错 (因为重写了token)
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     * @param principalCollection 用户信息
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("begin excute doGetAuthorizationInfo");
        String username = JwtUtils.getUsername(principalCollection.toString());
        User user = userRepository.findByUsername(username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(user.getRolesName());
        //用户的角色对应的权限
        List<Role> roles = user.getRoles();
        for (Role role:roles) {
            info.addStringPermissions(role.getPermissionsName());
        }
        log.info("doGetAuthorizationInfo end");
        return info;
    }

    /**
     * 身份认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("begin doGetAuthentication");
        String token = (String) authenticationToken.getCredentials();
        String username = JwtUtils.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("Username is empety");
        }
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new AuthenticationException("user not exist");
        }
        if (!JwtUtils.verify(token,username)) {
            throw new AuthenticationException("username or password is error");
        }
        log.info("doGetAuthentication end");
        return new SimpleAuthenticationInfo(token,token,getName());
    }
}
