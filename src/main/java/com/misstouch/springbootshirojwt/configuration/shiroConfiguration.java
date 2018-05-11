package com.misstouch.springbootshirojwt.configuration;

import com.misstouch.springbootshirojwt.security.JWTFilter;
import com.misstouch.springbootshirojwt.security.MyShiroRelam;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro 配置类
 * @author hechao
 * @date create in 13:00 2018/5/11/011
 */
@Configuration
public class shiroConfiguration {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置shiro的securitymanage
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //验证码过滤器
        Map<String,Filter> filterMap = shiroFilterFactoryBean.getFilters();
        filterMap.put("jwt",new JWTFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        //拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/user","jwt");
        filterChainDefinitionMap.put("/401","anon");
        filterChainDefinitionMap.put("/403","anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public MyShiroRelam myShiroRelam(){
        MyShiroRelam relam = new MyShiroRelam();
        return relam;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager df = new DefaultWebSecurityManager();
        df.setRealm(myShiroRelam());
        //关闭shiro自带的session
        DefaultSubjectDAO defaultSubjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator storageEvaluator = new DefaultSessionStorageEvaluator();
        storageEvaluator.setSessionStorageEnabled(false);
        defaultSubjectDAO.setSessionStorageEvaluator(storageEvaluator);
        df.setSubjectDAO(defaultSubjectDAO);
        return df;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
