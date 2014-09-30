package cn.mob.gamerec.api.uservideo.controller;

import cn.mob.gamerec.R;
import cn.mob.gamerec.api.uservideo.dao.UserVideoDao;
import cn.mob.gamerec.api.uservideo.domain.UserVideo;
import cn.mob.gamerec.util.JSONResult;
import cn.mob.gamerec.util.MD5;
import com.lamfire.utils.JSON;
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
    private UserVideoDao userVideoDao;

    /**
     * 喜欢视频
     *
     * @param userid
     * @param videoid
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String add(@PathVariable String userid, @PathVariable String videoid) {
        UserVideo userVideo = new UserVideo();
        userVideo.setId(MD5.hash(userid + R.SPLIT + videoid));
        userVideo.setUserid(userid);
        userVideo.setVideoid(videoid);
        userVideoDao.save(userVideo);
        return JSONResult.getResult().toString();
    }

    @RequestMapping("/isLike")
    @ResponseBody
    public String isLike(@PathVariable String userid, @PathVariable String videoid) {
        boolean isLike = userVideoDao.exists(R.ID, MD5.hash(userid + R.SPLIT + videoid));
        JSON result = JSONResult.getResult();
        result.put("isLike", isLike);
        return result.toString();
    }
}
