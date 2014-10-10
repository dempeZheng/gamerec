package cn.mob.gamerec.token;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/10/10
 */
@Component
public class TokenValidator extends WebMvcConfigurerAdapter {
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/**");

    }
}
