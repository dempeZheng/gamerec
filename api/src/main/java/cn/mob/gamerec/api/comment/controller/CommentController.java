package cn.mob.gamerec.api.comment.controller;

import cn.mob.gamerec.R;
import cn.mob.gamerec.api.comment.dao.CommentDao;
import cn.mob.gamerec.api.comment.domain.Comment;
import cn.mob.gamerec.util.JSONResult;
import cn.mob.gamerec.util.MD5;
import cn.mob.gamerec.util.RedisPool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.ShardedJedis;

import javax.annotation.Resource;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/29
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentDao commentDao;

    private ShardedJedis jedis = RedisPool.getJedis();

    @RequestMapping("/add")
    @ResponseBody
    public String add(@PathVariable String userid, @PathVariable String videoid, @PathVariable String content,
                      @PathVariable String plat) {
        Comment comment = new Comment();
        comment.setUserid(userid);
        comment.setVideoid(videoid);
        comment.setContent(content);
        comment.setPlat(plat);
        commentDao.save(comment);
        return JSONResult.getResult().toString();
    }

    /**
     * 赞评论
     * [redis:(userid:commentid=commentid)]
     *
     * @param userid
     * @param commentid
     */
    @RequestMapping("/like")
    @ResponseBody
    public String like(@PathVariable String userid, @PathVariable String commentid) {
        //redis
        JSONResult result = JSONResult.getResult();
        String key = MD5.hash(userid + R.SPLIT + commentid);
        if (jedis.exists(key)) {
            result.putErrorStatus(423);
        } else {
            jedis.set(key, "1");
        }
        return result.toString();
    }

    @RequestMapping("/isLike")
    @ResponseBody
    public String isLike(@PathVariable String userid, @PathVariable String commentid) {
        //redis
        JSONResult result = JSONResult.getResult();
        String key = MD5.hash(userid + R.SPLIT + commentid);
        result.put("isLike", jedis.exists(key));
        return result.toString();
    }
}
