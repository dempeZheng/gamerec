package cn.mob.gamerec.util;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/30
 */

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class Producer {
    private final kafka.javaapi.producer.Producer<Integer, String> producer;
    private final Properties props = new Properties();

    public Producer() {
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("metadata.broker.list", "localhost:9092");
        // Use random partitioner. Don't need the key type. Just set it to Integer.
        // The message is of type String.
        producer = new kafka.javaapi.producer.Producer<Integer, String>(new ProducerConfig(props));
    }

    public void send(String message, String topic) {
        producer.send(new KeyedMessage<Integer, String>(topic, message));
    }


}
