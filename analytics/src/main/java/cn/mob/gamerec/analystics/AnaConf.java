package cn.mob.gamerec.analystics;

/**
 * 配置analystics启动参数
 * 读取配置顺序依次为：启动参数，配置文件参数，环境变量参数
 *
 * @author : Dempe
 * @version 1.0 date : 2014/10/13
 */
public class AnaConf {

    private String metadataBrokerList = "localhost:9092";
    private String serializerClass = "kafka.serializer.StringEncoder";
    private String topic = "event";
    private int syncDuration = 5;
    private String dbServers = "127.0.0.1:27001";
    private String dbName = "SHAREREC_VIDEO";

    public AnaConf() {

    }


}
