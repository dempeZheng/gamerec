package cn.mob.gamerec.recommend.online

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @version 1.0 date : 2014/10/11
 * @author : Dempe 
 */
object StreamingRecommend extends App {

  val sparkConf = new SparkConf()
  sparkConf.setAppName("streaming recommend")
  sparkConf.setMaster("local[2]")
  sparkConf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
  sparkConf.set("spark.executor.memory", "2g")

  val ssc = new StreamingContext(sparkConf, Seconds(5))
  ssc.checkpoint("/app/data/spark/checkpoint")


}
