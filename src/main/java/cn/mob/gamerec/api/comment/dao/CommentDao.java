package cn.mob.gamerec.api.comment.dao;

import cn.mob.gamerec.api.comment.domain.Comment;
import com.lamfire.mongodb.DAOSupport;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/29
 */
public class CommentDao extends DAOSupport<Comment, String> {
    public CommentDao() {
        super("comment", "SHAREREC_COMMENT");
    }
}
