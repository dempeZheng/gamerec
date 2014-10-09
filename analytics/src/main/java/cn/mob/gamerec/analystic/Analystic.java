package cn.mob.gamerec.analystic;

import cn.mob.gamerec.R;
import cn.mob.gamerec.analystic.analysis.Analysis;
import cn.mob.gamerec.analystic.analysis.KafkaStreamUtil;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import org.apache.log4j.Logger;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/29
 */
public class Analystic  {

    public static final Logger LOGGER = Logger.getLogger(Analystic.class);

    private String metadataBrokerList ;
    private String serializerClass ;
    private String topic;
    private int syncDuration;
    private String dbServers ;
    private String dbName ;

    public Analystic(AnalysticBuilder builder){
        this.metadataBrokerList = builder.getMetadataBrokerList();
        this.serializerClass = builder.getSerializerClass();
        this.topic = builder.getTopic();
        this.syncDuration = builder.getSyncDuration();
        this.dbServers = builder.getDbServers();
        this.dbName = builder.getDbName();
    }

    private static KafkaStream stream =new KafkaStreamUtil(R.EVENT).getStream();


    public static void main(String args[]) {
        LOGGER.info("START ANALYSIS NOW ");
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        while (it.hasNext()){
            String message = new String(it.next().message());
            Analysis.streaming(message);
        }

    }

}
