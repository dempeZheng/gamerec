package cn.mob.gamerec.api;

import cn.mob.gamerec.R;
import cn.mob.gamerec.util.JSONResult;
import cn.mob.gamerec.util.Producer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 接受客户端事件数据，将时间添加到队列
 * [弃用传统的activemq]
 * 选择kafka方便时间消息存储，支持多个消费者消费，方便以后推荐系统
 *
 * @author : Dempe
 * @version 1.0 date : 2014/9/30
 */
@Controller
@RequestMapping("/event")
public class EventController {


    @Resource
    private Producer producer;

    @RequestMapping("/add")
    @ResponseBody
    public String view(@RequestParam String message) {
        //push queue
        producer.send(message, R.EVENT);
        return JSONResult.getResult().toString();
    }
}
