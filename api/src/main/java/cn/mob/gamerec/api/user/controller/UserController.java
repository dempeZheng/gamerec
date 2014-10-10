package cn.mob.gamerec.api.user.controller;

import cn.mob.gamerec.R;
import cn.mob.gamerec.api.user.dao.UserDao;
import cn.mob.gamerec.api.user.domain.User;
import cn.mob.gamerec.util.JSONResult;
import cn.mob.gamerec.util.MD5;
import com.lamfire.utils.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/28
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserDao userDao;

    @RequestMapping("/getUsersByAppkey")
    @ResponseBody
    public String getUsersByAppkey(@RequestParam String appkey, @RequestParam int pageindex, @RequestParam int pagesize) {

        List<User> users = userDao.findByAppkey(appkey, pageindex, pagesize);
        long total = userDao.countAllByAppkey(appkey);
        return JSONResult.getResult().putResult(JSON.parseFromObject(users)).putTotal(total).toString();
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestParam String nickname, @RequestParam String password) {
        User user = userDao.findByNicknameAndPassword(nickname, MD5.hash(password));
        return JSONResult.getResult().putResult(JSON.parseFromObject(user)).toString();
    }

    @RequestMapping("/view")
    @ResponseBody
    public String view(@RequestParam String id) {
        User user = userDao.findByPrimaryKey(id);
        return JSONResult.getResult().putResult(JSON.parseFromObject(user)).toString();
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public String updateStatus(@RequestParam String id, String status) {
        userDao.update(id, R.STATUS, status);
        return JSONResult.getResult().toString();
    }

    @RequestMapping("/bindPhone")
    @ResponseBody
    public String bindPhone(@RequestParam String id, @RequestParam String phone) {
        JSONResult result = JSONResult.getResult();
        if (userDao.exists(R.PHONE, phone)) {
            result.putErrorStatus(422);
        } else {
            userDao.update(id, R.PHONE, phone);
        }
        return result.toString();
    }

    @RequestMapping("/updateAvatar")
    @ResponseBody
    public String updateAvatar(@RequestParam String id, @RequestParam String avatar) {
        userDao.update(id, R.AVATAR, avatar);
        return JSONResult.getResult().toString();
    }

    @RequestMapping("/updatePassword")
    @ResponseBody
    public String updatePassword(@RequestParam String id, @RequestParam String password) {
        userDao.update(id, R.PASSWORD, password);
        return JSONResult.getResult().toString();
    }


}
