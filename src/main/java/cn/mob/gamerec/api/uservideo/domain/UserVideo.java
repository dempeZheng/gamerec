package cn.mob.gamerec.api.uservideo.domain;

import com.lamfire.mongodb.morphia.annotations.Entity;
import com.lamfire.mongodb.morphia.annotations.Id;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/29
 */
@Entity
public class UserVideo {

    @Id
    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
