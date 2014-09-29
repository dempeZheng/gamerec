package cn.mob.gamerec.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/28
 */
public class ErrorMsg {


    public static Map<Integer, String> errorMsgMap = new ConcurrentHashMap<Integer, String>();

    public static String get(int status) {
        return errorMsgMap.get(status);
    }
}
