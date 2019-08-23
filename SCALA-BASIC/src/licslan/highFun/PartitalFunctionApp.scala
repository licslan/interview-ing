package licslan.highFun

import scala.util.Random

/**
  * 偏函数学习 by 黄威林
  * 偏函数：被包在花括号内没有match的一组case语句
  * */
object PartitalFunctionApp extends App{

  //片函数学习

  val names = Array("x","y","z")
  val name = names(Random.nextInt(names.length))
  //模式匹配
  name match {
    case "x"=>println("this is x")
    case "y"=>println("this is y")
    case _=>println("you are nothing")
  }

  //利用片函数进行改造  A  输入参数类型  B  输出参数类型
  //偏函数返回类型要用  PartialFunction   def sayHello:PartialFunction[A,B]
  def sayHello:PartialFunction[String,String]={
    case "x"=>"this is x"
    case "y"=>"this is y"
    case _=>"you are nothing"
  }
  println(sayHello("xxxxxxxxxxxxxx"))
}
