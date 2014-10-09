package cn.mob.gamerec.api.video.dao;

import cn.mob.gamerec.R;
import cn.mob.gamerec.api.video.domain.Video;
import com.lamfire.mongodb.DAOSupport;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/28
 */
@Component
public class VideoDao extends DAOSupport<Video, String> {
    public VideoDao() {
        super(R.VIDEO, R.VIDEO_DB);
    }

    public Video findByPrimaryKey(String id) {
        return findOne(R.ID, id);
    }

    public List<Video> findByStatus(int status, int pageindex, int pagesize) {
        return createQuery().field(R.STATUS).equal(status).order(R._CREATEAT).offset(pageindex * pagesize).
                limit(pagesize).asList();
    }

    public List<Video> findByUserId(String userid, int pageindex, int pagesize) {
        return createQuery().field(R.USERID).equal(userid).offset(pageindex * pagesize).limit(pagesize).asList();
    }

    public List<Video> findByAppKey(String appkey, int pageindex, int pagesize) {
        return createQuery().field(R.APPKEY).equal(appkey).order(R._CREATEAT).offset(pageindex * pagesize).
                limit(pagesize).asList();
    }

    public List<Video> findByAppKey(String appkey, int status, int pageindex, int pagesize) {
        return createQuery().field(R.APPKEY).equal(appkey).field(R.STATUS).equal(status).order(R._CREATEAT).
                offset(pageindex * pagesize).limit(pagesize).asList();
    }

    public long countByUserId(String userid) {
        return createQuery().field(R.USERID).equal(userid).countAll();
    }

    public long countByAppKey(String appkey) {
        return createQuery().field(R.APPKEY).equal(appkey).countAll();
    }

    public long countByStatus(int status) {
        return createQuery().field(R.STATUS).equal(status).countAll();
    }

    public long countByAppKey(String appkey, int status) {
        return createQuery().field(R.APPKEY).equal(appkey).field(R.STATUS).equal(status).countAll();
    }
}
