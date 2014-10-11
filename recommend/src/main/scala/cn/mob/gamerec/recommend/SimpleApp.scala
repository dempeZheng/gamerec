package cn.mob.gamerec.recommend

/**
 * @version 1.0 date : 2014/10/9
 * @author : Dempe 
 */

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.mllib.recommendation.ALS
import org.apache.spark.mllib.recommendation.Rating

object SimpleApp {
  def main(args: Array[String]) {
    val sc = new SparkContext("spark://192.168.159.129:7077", "Collaborative Filtering", "/root/spark-0.9",
      List("target/scala-2.10/scala_2.10-0.1-SNAPSHOT.jar"))
    val data = sc.textFile("hdfs://master:9000/mllib/test.data")
    val ratings = data.map(_.split(',') match {
      case Array(user, item, rate) => Rating(user.toInt, item.toInt, rate.toDouble)
    })

    // Build the recommendation model using ALS
    val numIterations = 20
    val model = ALS.train(ratings, 1, 20, 0.01)

    // Evaluate the model on rating data
    val usersProducts = ratings.map {
      case Rating(user, product, rate) => (user, product)
    }
    val predictions = model.predict(usersProducts).map {
      case Rating(user, product, rate) => ((user, product), rate)
    }
    val ratesAndPreds = ratings.map {
      case Rating(user, product, rate) => ((user, product), rate)
    }.join(predictions)

    val MSE = ratesAndPreds.map {
      case ((user, product), (r1, r2)) => math.pow((r1 - r2), 2)
    }.reduce(_ + _) / ratesAndPreds.count
    println("Mean Squared Error = " + MSE)
  }
}
