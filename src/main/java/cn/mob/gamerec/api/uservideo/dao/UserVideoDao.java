package cn.mob.gamerec.api.uservideo.dao;

import cn.mob.gamerec.R;
import cn.mob.gamerec.api.uservideo.domain.UserVideo;
import com.lamfire.mongodb.DAOSupport;
import org.springframework.stereotype.Component;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/29
 */
@Component
public class UserVideoDao extends DAOSupport<UserVideo, String> {
    public UserVideoDao() {
        super(R.USERVIDEO, R.USERVIDEO_DB);
    }
}
