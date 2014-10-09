package cn.mob.gamerec.analystic.analysis;

import cn.mob.gamerec.util.KafkaProperties;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/30
 */
public class KafkaStreamUtil {
    private final ConsumerConnector consumer;
    private final String topic;
    ConsumerIterator<byte[], byte[]> it ;
    public KafkaStreamUtil(String topic)
    {
        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(createConsumerConfig());
        this.topic = topic;
    }

    private static ConsumerConfig createConsumerConfig()
    {
        Properties props = new Properties();
        props.put("zookeeper.connect", KafkaProperties.zkConnect);
        props.put("group.id", KafkaProperties.groupId);
        props.put("zookeeper.session.timeout.ms", "400");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");

        return new ConsumerConfig(props);

    }



    public kafka.consumer.KafkaStream<byte[], byte[]> getStream() {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(1));
        Map<String, List<kafka.consumer.KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        kafka.consumer.KafkaStream<byte[], byte[]> stream =  consumerMap.get(topic).get(0);
        return stream;
    }


}
