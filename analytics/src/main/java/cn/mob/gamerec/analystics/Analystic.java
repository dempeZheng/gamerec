package cn.mob.gamerec.analystics;

import cn.mob.gamerec.analystics.analysis.Analysis;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import java.util.UUID;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/29
 */
public class Analystic {

    public static final Logger LOGGER = Logger.getLogger(Analystic.class);

    private String metadataBrokerList;
    private String topic;
    private int syncDuration;


    public Analystic(AnalysticBuilder builder) {
        this.metadataBrokerList = builder.getMetadataBrokerList();
        this.topic = builder.getTopic();
        this.syncDuration = builder.getSyncDuration();

    }

    // private static KafkaStream stream = new KafkaStreamUtil(R.EVENT_TOPIC).getStream();


    public static void main(String args[]) {
        LOGGER.info("START ANALYSIS NOW ");
       /* ConsumerIterator<byte[], byte[]> it = stream.iterator();
        while (it.hasNext()) {
            String message = new String(it.next().message());
            Analysis.streaming(message);
        }*/

        while (true) {
            Analysis.streaming(getTestMessage());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static String getTestMessage() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(R.EVENT_TYPE, R.LIKE_TYPE_CODE);
        jsonObject.put(R.UID, UUID.randomUUID());
        return jsonObject.toString();
    }


}
