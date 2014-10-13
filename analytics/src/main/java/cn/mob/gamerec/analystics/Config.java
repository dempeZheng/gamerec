package cn.mob.gamerec.analystics;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * 配置analystics启动参数
 * 读取配置顺序依次为：启动参数，配置文件参数，环境变量参数
 *
 * @author : Dempe
 * @version 1.0 date : 2014/10/13
 */
public class Config {

    private static final Logger LOGGER = Logger.getLogger(Config.class);

    private static PropertiesConfiguration conf;

    private static String propPath = null;


    static {
        initPropPath();
        initProp();
    }

    public static void initProp() {
        try {
            LOGGER.info("use " + propPath);
            conf = new PropertiesConfiguration(propPath);
        } catch (ConfigurationException e) {
            LOGGER.error(e);
        }
    }

    public static void initPropPath() {
        try {
            propPath = new File("").getCanonicalPath() + "/conf/" + R.PROPS_NAME;
            File file = new File(propPath);
            if (!file.exists()) {
                propPath = R.PROPS_NAME;
            }
        } catch (IOException e) {
            LOGGER.error("conf/" + R.PROPS_NAME + " not found error", e);
        }
    }

    public static String getString(String key) {
        String str = conf.getString(key);
        if (str == null) {
            str = System.getenv(key);
        }
        return str;
    }

    public static String getString(String key, String defaultValue) {
        return conf.getString(key, defaultValue);
    }


}
