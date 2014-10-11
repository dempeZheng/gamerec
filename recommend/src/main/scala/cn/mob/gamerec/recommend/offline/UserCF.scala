package cn.mob.gamerec.recommend.offline

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.mllib.recommendation.{ALS, Rating}
import org.apache.spark.SparkContext._

/**
 * 基于用户协同过滤
 * @version 1.0 date : 2014/10/11
 * @author : Dempe 
 */
object UserCF {

  def main(args: Array[String]) {
    val conf = new SparkConf()
    conf.setMaster("local[2]")
    conf.setSparkHome(System.getenv("SPARK_HOME"))
    conf.setAppName("gameRec recommend")
    val sc = new SparkContext(conf)
    val data = sc.textFile("data/mllib/als/test.data")
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


}
