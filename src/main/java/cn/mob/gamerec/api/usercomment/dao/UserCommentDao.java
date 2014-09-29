package cn.mob.gamerec.api.usercomment.dao;

import cn.mob.gamerec.R;
import cn.mob.gamerec.api.uservideo.domain.UserVideo;
import com.lamfire.mongodb.DAOSupport;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/29
 */
public class UserCommentDao  extends DAOSupport<UserVideo, String> {
    public UserCommentDao() {
        super(R.USERCOMMENT, R.USERCOMMENT_DB);
    }
}

