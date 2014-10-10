package cn.mob.gamerec.token;

import cn.mob.gamerec.util.EhCache;
import cn.mob.gamerec.util.EhCacheFactory;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/10/10
 */
public class TokenInterceptor implements HandlerInterceptor {

    public static final Logger LOGGER = Logger.getLogger(TokenInterceptor.class);

    private final static EhCache tokenCache = EhCacheFactory.newCache("tokenCache", 1000, 2 * 60, 2 * 60);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        LOGGER.info("preHandle interceptor");
        request.getParameterNames();
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            LOGGER.info("token is null");
            return false;
        }
        if(tokenCache.get(token)==null){
            LOGGER.info("token is not exists");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
