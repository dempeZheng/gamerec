package cn.mob.gamerec.api.comment.controller;

import cn.mob.gamerec.api.comment.dao.CommentDao;
import cn.mob.gamerec.api.comment.domain.Comment;
import cn.mob.gamerec.util.JSONResult;
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
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentDao commentDao;

    @RequestMapping("/add")
    @ResponseBody
    public String add(@PathVariable String userid,@PathVariable String videoid,@PathVariable String content,
                      @PathVariable String plat ){
        Comment comment = new Comment();
        comment.setUserid(userid);
        comment.setVideoid(videoid);
        comment.setContent(content);
        comment.setPlat(plat);
        commentDao.save(comment);
        return JSONResult.getResult().toString();
    }
}
