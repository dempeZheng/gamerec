package cn.mob.gamerec.util;

import java.util.UUID;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/12
 */
public class Utils {

    public static String unzip(String str) {
        return null;
    }

    /**
     * 通过一致hash算法获取key和host映射关系
     *
     * @param key
     * @return
     */
    public static String getHostByKey(String key) {

        return "192.168.1.144:2553";
    }


    public static String getUUid() {
        return UUID.randomUUID().toString();
    }
}

