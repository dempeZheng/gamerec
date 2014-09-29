package cn.mob.gamerec.api.video.controller;

import cn.mob.gamerec.R;
import cn.mob.gamerec.api.user.dao.UserDao;
import cn.mob.gamerec.api.user.domain.User;
import cn.mob.gamerec.api.video.dao.VideoDao;
import cn.mob.gamerec.api.video.domain.Video;
import cn.mob.gamerec.util.JSONResult;
import com.lamfire.utils.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/28
 */
@Controller
@RequestMapping("/video")
public class VideoController {

    @Resource
    private VideoDao videoDao;
    @Resource
    private UserDao userDao;

    /**
     * 获取当前用户下所有视频
     *
     * @param userid
     * @param pageindex
     * @param pagesize
     * @return
     */

    @RequestMapping("/getVideosByUserId")
    @ResponseBody
    public String getVideosByUserId(@PathVariable String userid, @PathVariable int pageindex, @PathVariable int pagesize) {
        List<Video> videos = videoDao.findByUserId(userid, pageindex, pagesize);
        long total = videoDao.countByUserId(userid);
        JSON result = JSONResult.getResult();
        result.put("result", JSON.toJsonString(videos));
        result.put("total", total);
        return result.toString();
    }

    /**
     * 获取当前appkey视频
     *
     * @param appkey
     * @param pageindex
     * @param pagesize
     * @return
     */
    @RequestMapping("/getVideosByAppKey")
    @ResponseBody
    public String getVideosByAppKey(@PathVariable String appkey, @PathVariable int pageindex, @PathVariable int pagesize) {
        List<Video> videos = videoDao.findByAppKey(appkey, pageindex, pagesize);
        long total = videoDao.countByAppKey(appkey);
        JSON result = JSONResult.getResult();
        result.put("result", JSON.toJsonString(videos));
        result.put("total", total);
        return result.toString();
    }

    /**
     * 获取当前appkey所有status为1的视频，按时间排序
     *
     * @param appkey
     * @param pageindex
     * @param pagesize
     * @return
     */
    @RequestMapping("/getPublicVideosByAppKey")
    @ResponseBody
    public String getPublicVideosByAppKey(@PathVariable String appkey, @PathVariable int pageindex, @PathVariable int pagesize) {
        List<Video> videos = videoDao.findByAppKey(appkey, 1, pageindex, pagesize);
        long total = videoDao.countByAppKey(appkey, 1);
        JSON result = JSONResult.getResult();
        result.put("result", JSON.toJsonString(videos));
        result.put("total", total);
        return result.toString();
    }

    /**
     * 获取所有status为1的视频，按时间排序
     *
     * @param pageindex
     * @param pagesize
     * @return
     */
    @RequestMapping("/getPublicVideosByAppKey")
    @ResponseBody
    public String getPublicVideos(@PathVariable int pageindex, @PathVariable int pagesize) {
        List<Video> videos = videoDao.findByStatus(1, pageindex, pagesize);
        long total = videoDao.countByStatus(1);
        JSON result = JSONResult.getResult();
        result.put("result", JSON.toJsonString(videos));
        result.put("total", total);
        return result.toString();
    }

    /**
     * 通过视频id获取视频详情
     *
     * @param id
     * @return
     */
    public String view(@PathVariable String id) {
        Video video = videoDao.findByPrimaryKey(id);
        JSONResult result = JSONResult.getResult();
        JSON json = JSON.parseFromObject(video);
        String userid = video.getUserid();
        if (userid != null) {
            User user = userDao.findByPrimaryKey(userid);
            json.put(R.NICKNAME, user == null ? "" : user.getNickname());
            json.put(R.AVATAR, user == null ? "" : user.getAvatar());
            json.put(R.SHAREURL, "");
        }
        result.put("result", json);
        return result.toString();
    }


}
