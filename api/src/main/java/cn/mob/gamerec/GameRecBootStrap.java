package cn.mob.gamerec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/28
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class GameRecBootStrap {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(GameRecBootStrap.class, args);
    }
}
