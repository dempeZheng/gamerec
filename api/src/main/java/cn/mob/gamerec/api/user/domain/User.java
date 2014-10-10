package cn.mob.gamerec.api.user.domain;

import com.lamfire.mongodb.morphia.annotations.Entity;
import com.lamfire.mongodb.morphia.annotations.Id;

import java.io.Serializable;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/28
 */
@Entity
public class User implements Serializable {

    @Id
    private String id;
    private String duid;
    private String appkey;
    private String nickname;
    private String avatar;
    private Long createat;
    private String password;
    private Integer status;
    private String intro;
    private Integer videos;
    private Integer following;
    private Integer followers;
    //@Pattern(regexp="/^1[3|4|5|6|7|8|9][0-9]{1}[0-9]{8}$/", message="phone number wrong")
    private String phoneNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDuid() {
        return duid;
    }

    public void setDuid(String duid) {
        this.duid = duid;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getCreateat() {
        return createat;
    }

    public void setCreateat(Long createat) {
        this.createat = createat;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getVideos() {
        return videos;
    }

    public void setVideos(Integer videos) {
        this.videos = videos;
    }

    public Integer getFollowing() {
        return following;
    }

    public void setFollowing(Integer following) {
        this.following = following;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
