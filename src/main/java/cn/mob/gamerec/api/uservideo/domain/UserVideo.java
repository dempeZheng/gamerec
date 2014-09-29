package cn.mob.gamerec.api.uservideo.domain;

import com.lamfire.mongodb.morphia.annotations.Entity;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/29
 */
@Entity
public class UserVideo {


    private String userid;

    private String videoid;


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
