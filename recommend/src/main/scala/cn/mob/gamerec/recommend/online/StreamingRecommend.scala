package cn.mob.gamerec.recommend.online

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.mllib.recommendation.{ALS, Rating}
import org.apache.spark.streaming.kafka.KafkaUtils

/**
 *
 * 基于spark streaming & MLlib CF 实时推荐
 * 1.批量从kafka读入某一时间段（10s）原始事件数据(play,like,download,share)
 * 2.将批量的事件数据转成Array(item,user,score)dataModel
 * 3.基于Content Base Collaborative filtering实时推荐
 * @version 1.0 date : 2014/10/11
 * @author : Dempe 
 */
object StreamingRecommend extends App {

  val sparkConf = new SparkConf()
  sparkConf.setAppName("streaming recommend")
  sparkConf.setMaster("local[2]")
  sparkConf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
  sparkConf.set("spark.executor.memory", "2g")

  val Array(zkQuorum, group, topics, numThreads) = args
  val ssc = new StreamingContext(sparkConf, Seconds(10))
  ssc.checkpoint("checkpoint")

  val topicMap = topics.split(",").map((_, numThreads.toInt)).toMap
  val source = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap).map(_._2)
  //val data = ssc.socketTextStream("localhost", 9999)
  //TODO 处理data 将数据模型转变成Array(user, item, rate)
  val data = source.map(v => {
    v
  })

  val ratings = data.map(_.split(',') match {
    case Array(user, item, rate) =>
      Rating(user.toInt, item.toInt, rate.toDouble)
  })

  // Build the recommendation model using ALS
  val rank = 10
  val numIterations = 20
  val model = ALS.train(ratings, rank, numIterations, 0.01)

  // Evaluate the model on rating data
  val usersProducts = ratings.map {
    case Rating(user, product, rate) =>
      (user, product)
  }
  val predictions =
    model.predict(usersProducts).map {
      case Rating(user, product, rate) =>
        ((user, product), rate)
    }
  val ratesAndPreds = ratings.map {
    case Rating(user, product, rate) =>
      ((user, product), rate)
  }.join(predictions)
  val MSE = ratesAndPreds.map {
    case ((user, product), (r1, r2)) =>
      val err = (r1 - r2)
      err * err
  }.mean()
  println("Mean Squared Error = " + MSE)


}
