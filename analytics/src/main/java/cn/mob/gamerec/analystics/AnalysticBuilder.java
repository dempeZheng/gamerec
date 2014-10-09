package cn.mob.gamerec.analystics;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/29
 */
public class AnalysticBuilder {

    private String metadataBrokerList = "localhost:9092";
    private String serializerClass ="kafka.serializer.StringEncoder";
    private String topic = "event";
    private int syncDuration = 5;
    private String dbServers = "127.0.0.1:27001";
    private String dbName = "SHAREREC_VIDEO";
    private String zookeeperConnect = "";
    private String groupId ="";
    private int zookeeperSessionTimeoutMs = 400;
    private int zookeeperSyncTimeMs =200;

    public String getMetadataBrokerList() {
        return metadataBrokerList;
    }

    public AnalysticBuilder setMetadataBrokerList(String metadataBrokerList) {
        this.metadataBrokerList = metadataBrokerList;
        return this;
    }

    public String getSerializerClass() {
        return serializerClass;
    }

    public AnalysticBuilder setSerializerClass(String serializerClass) {
        this.serializerClass = serializerClass;
        return this;
    }

    public String getTopic() {
        return topic;
    }

    public AnalysticBuilder setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public int getSyncDuration() {
        return syncDuration;
    }

    public AnalysticBuilder setSyncDuration(int syncDuration) {
        this.syncDuration = syncDuration;
        return this;
    }

    public String getDbServers() {
        return dbServers;
    }

    public AnalysticBuilder setDbServers(String dbServers) {
        this.dbServers = dbServers;
        return this;
    }

    public String getDbName() {
        return dbName;
    }

    public AnalysticBuilder setDbName(String dbName) {
        this.dbName = dbName;
        return this;
    }
}
