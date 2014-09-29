package cn.mob.gamerec.api.like.dao;

import cn.mob.gamerec.api.like.domain.Like;
import com.lamfire.mongodb.DAOSupport;
import org.springframework.stereotype.Component;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/29
 */
@Component
public class LikeDao extends DAOSupport<Like, String> {
    public LikeDao() {
        super("like", "SHAREREC_LIKE");
    }
}
