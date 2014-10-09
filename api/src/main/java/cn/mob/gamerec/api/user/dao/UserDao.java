package cn.mob.gamerec.api.user.dao;

import cn.mob.gamerec.R;
import cn.mob.gamerec.api.user.domain.User;
import cn.mob.gamerec.util.EhCache;
import cn.mob.gamerec.util.EhCacheFactory;
import com.lamfire.mongodb.DAOSupport;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * TODO 缓存暂时不考虑user对象更新问题，以后会使用优雅是方式替代
 *
 * @author : Dempe
 * @version 1.0 date : 2014/9/28
 */
@Component
public class UserDao extends DAOSupport<User, String> {

    private final static EhCache userCache = EhCacheFactory.newCache("userCache", 1000, 2 * 60, 2 * 60);

    public UserDao() {
        super(R.USER, R.USER_DB);
    }

    public User findByPrimaryKey(String id) {
        User user = (User) userCache.get(id);
        if (user == null) {
            user = findOne(R.ID, id);
            userCache.put(id, user);
        }
        return user;
    }

    public List<User> findByAppkey(String appkey, int pageindex, int pagesize) {
        return createQuery().field(R.APPKEY).equal(appkey).offset(pageindex * pagesize).limit(pagesize).asList();
    }

    public long countAllByAppkey(String appkey) {
        return createQuery().field(R.APPKEY).equal(appkey).countAll();
    }

    public User findByNicknameAndPassword(String nickname, String password) {
        return findOne(createQuery().field(R.NICKNAME).equal(nickname).field(R.PASSWORD).equal(password));
    }

    public User findByPhoneAndPassword(String phone, String password) {
        return findOne(createQuery().field(R.PHONE).equal(phone).field(R.PASSWORD).equal(password));
    }


}
