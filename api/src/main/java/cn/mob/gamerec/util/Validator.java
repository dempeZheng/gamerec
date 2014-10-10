package cn.mob.gamerec.util;

import java.util.regex.Pattern;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/10/10
 */
public class Validator {

    private final static Pattern phonePatter = Pattern.compile("^1[3|4|5|6|7|8|9][0-9]{1}[0-9]{8}$");

    public static boolean phoneValidate(String phone) {
        return phonePatter.matcher(phone).matches();
    }
}
