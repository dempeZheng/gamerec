package cn.mob.gamerec.util;

import com.lamfire.code.AES;

/**
 * 加密解密工具类
 *
 * @author : Dempe
 * @version 1.0 date : 2014/10/15
 */
public class CrypUtil {
    private static byte[] key = null;
    private static AES aes = null;

    static {
        key = MD5.digest("".getBytes());
        aes = new AES(key);
    }
}
