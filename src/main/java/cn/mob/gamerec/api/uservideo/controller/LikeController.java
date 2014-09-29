package cn.mob.gamerec.api.uservideo.controller;

import cn.mob.gamerec.api.uservideo.dao.UserVideoDao;
import cn.mob.gamerec.api.uservideo.domain.UserVideo;
import cn.mob.gamerec.util.JSONResult;
import cn.mob.gamerec.util.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/29
 */
@Controller
@RequestMapping("/like")
public class LikeController {

    @Resource
    private UserVideoDao likeDao;

    @RequestMapping("/add")
    @ResponseBody
    public String add(@PathVariable String userid, @PathVariable String videoid) {
        UserVideo like = new UserVideo();
        like.setUserid(userid);
        like.setVideoid(videoid);
        likeDao.save(like);
        return JSONResult.getResult().toString();

    }
}
