package cn.mob.gamerec.recommend.example

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.mllib.linalg.{Vector, Vectors}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint

/**
 * @version 1.0 date : 2014/10/11
 * @author : Dempe 
 */
object SparkTest extends  App{
  val conf = new SparkConf()
  //conf.setSparkHome(System.getenv("SPARK_HOME"))
  conf.setAppName("gameRec recommend")
  conf.setMaster("local[4]")
  conf.setSparkHome("E:\\spark\\spark-1.1.0")
  val sc = new SparkContext(conf)
  val data = sc.textFile("data/mllib/als/test.data")
  println(data)
  data.foreach(println)



}
