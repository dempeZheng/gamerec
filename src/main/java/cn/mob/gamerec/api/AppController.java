package cn.mob.gamerec.api;

import cn.mob.gamerec.util.ThirdParty;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/28
 */
//@Controller("/app")
public class AppController {


    //@Cacheable(value="appCache",key="'app:view' + #appkey")
    @RequestMapping("/view")
    @ResponseBody
    public String view(@PathVariable String appkey) {

        return ThirdParty.getAppInfo();
    }
}
