package cn.mob.gamerec.api;

import com.alibaba.fastjson.JSONObject;
import com.lamfire.utils.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/10/16
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value ="/hello",method= RequestMethod.POST)
    @ResponseBody
    public String hello(@RequestBody JSONObject json){

        System.out.println(json);

        return "hello";
    }
    //DispatcherServlet
    @RequestMapping(value ="/aaa",method= RequestMethod.POST)
    @ResponseBody
    public String aa(HttpServletRequest request){



        return "hello";
    }
}
