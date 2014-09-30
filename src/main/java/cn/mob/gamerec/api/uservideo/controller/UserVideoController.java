package cn.mob.gamerec.api.uservideo.controller;

import cn.mob.gamerec.R;
import cn.mob.gamerec.api.user.dao.UserDao;
import cn.mob.gamerec.api.user.domain.User;
import cn.mob.gamerec.api.uservideo.dao.UserVideoDao;
import cn.mob.gamerec.api.uservideo.domain.UserVideo;
import cn.mob.gamerec.api.video.dao.VideoDao;
import cn.mob.gamerec.api.video.domain.Video;
import cn.mob.gamerec.util.JSONResult;
import cn.mob.gamerec.util.MD5;
import com.lamfire.utils.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/29
 */
@Controller
@RequestMapping("/uservideo")
public class UserVideoController {

    @Resource
    private UserVideoDao userVideoDao;
    @Resource
    private VideoDao videoDao;
    @Resource
    private UserDao userDao;

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

    @RequestMapping("/getVideosByUserid")
    @ResponseBody
    public String getVideosByUserid(@PathVariable String userid, @PathVariable int pageindex, @PathVariable int pagesize) {
        List<UserVideo> userVideoList = userVideoDao.findByUserid(userid, pageindex, pagesize);
        long total = userVideoDao.countByUserid(userid);
        List<String> videoIds = new ArrayList<String>();
        for (UserVideo userVideo : userVideoList) {
            videoIds.add(userVideo.getVideoid());
        }
        List<Video> videoList = videoDao.gets(videoIds);
        return JSONResult.getResult().putResult(JSON.parseFromObject(videoList)).putTotal(total).toString();

    }

    @RequestMapping("/getUsersByVideoid")
    @ResponseBody
    public String getUsersByVideoid(@PathVariable String videoid, @PathVariable int pageindex, @PathVariable int pagesize) {
        List<UserVideo> userVideoList = userVideoDao.findByVideoid(videoid, pageindex, pagesize);
        long total = userVideoDao.countByVideoid(videoid);
        List<String> userIds = new ArrayList<String>();
        for (UserVideo userVideo : userVideoList) {
            userIds.add(userVideo.getUserid());
        }
        List<User> userList = userDao.gets(userIds);
        return JSONResult.getResult().putResult(JSON.parseFromObject(userList)).putTotal(total).toString();
    }


}
