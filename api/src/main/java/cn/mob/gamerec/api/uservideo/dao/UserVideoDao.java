package cn.mob.gamerec.api.uservideo.dao;

import cn.mob.gamerec.R;
import cn.mob.gamerec.api.uservideo.domain.UserVideo;
import com.lamfire.mongodb.DAOSupport;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/29
 */
@Component
public class UserVideoDao extends DAOSupport<UserVideo, String> {
    public UserVideoDao() {
        super(R.USERVIDEO, R.USERVIDEO_DB);
    }

    public UserVideo findByPrimaryKey(String id) {
        return findOne(R.ID, id);
    }

    public List<UserVideo> findByUserid(String userid, int pageindex, int pagesize) {
        return createQuery().field(R.USERID).equal(userid).order(R._CREATEAT).offset(pageindex * pagesize).
                limit(pagesize).asList();
    }

    public List<UserVideo> findByVideoid(String videoid, int pageindex, int pagesize) {
        return createQuery().field(R.VIDEOID).equal(videoid).order(R._CREATEAT).offset(pageindex * pagesize).
                limit(pagesize).asList();
    }

    public long countByUserid(String userid) {
        return createQuery().field(R.USERID).equal(userid).countAll();
    }

    public long countByVideoid(String videoid) {
        return createQuery().field(R.VIDEOID).equal(videoid).countAll();
    }
}
