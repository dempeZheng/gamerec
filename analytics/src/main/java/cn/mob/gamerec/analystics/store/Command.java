package cn.mob.gamerec.analystics.store;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/30
 */
public interface Command {
    void incr(String key, int step);

    void incr(String key);
}
