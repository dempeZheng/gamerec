package cn.mob.gamerec.api.usercomment.domain;

import com.lamfire.mongodb.morphia.annotations.Entity;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/29
 */
@Entity
public class UserComment {
    private String userid;
    private String commentid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCommentid() {
        return commentid;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid;
    }
}
