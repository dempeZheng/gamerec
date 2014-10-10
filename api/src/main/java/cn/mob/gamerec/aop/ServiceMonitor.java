package cn.mob.gamerec.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/10/10
 */
@Aspect
@Component
public class ServiceMonitor {

    public static final Logger LOGGER = Logger.getLogger(ServiceMonitor.class);

    @Before("execution(* cn.mob.gamerec.api..*Controller.*(..))")
    public void monitor(JoinPoint joinPoint) {
        LOGGER.info("Completed: " + joinPoint);
    }

}