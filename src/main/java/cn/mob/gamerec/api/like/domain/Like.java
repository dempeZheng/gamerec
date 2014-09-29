package cn.mob.gamerec.api.like.domain;

import com.lamfire.mongodb.morphia.annotations.Entity;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/29
 */
@Entity
public class Like {

    private String id;

    private String userid;

    private String videoid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getVideoid() {
        return videoid;
    }

    public void setVideoid(String videoid) {
        this.videoid = videoid;
    }
}
