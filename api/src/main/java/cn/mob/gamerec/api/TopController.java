package cn.mob.gamerec.api;

import cn.mob.gamerec.util.JSONResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 获取top视频
 *
 * @author : Dempe
 * @version 1.0 date : 2014/9/30
 */
@Controller
@RequestMapping("/top")
public class TopController {

    @RequestMapping("/like")
    @ResponseBody
    public String like(@RequestParam Integer top) {
        return JSONResult.getResult().toString();
    }

    @RequestMapping("/play")
    @ResponseBody
    public String play(@RequestParam Integer top) {
        return JSONResult.getResult().toString();
    }

    @RequestMapping("/comment")
    @ResponseBody
    public String comment(@RequestParam Integer top) {
        return JSONResult.getResult().toString();
    }
}
