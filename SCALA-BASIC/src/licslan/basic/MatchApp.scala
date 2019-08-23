package licslan.basic

import scala.util.Random

/**
  * scala 模式匹配学习 by 黄威林
  * */
object MatchApp {

  //模式匹配
  //Java 对一个值进行条件判断 返回针对不同的条件进行不通处理  switch case
  //Scala 中的模式匹配功能要比Java中强大很多很多
  //scala 语法
  // 变量 match{
  // case value1 => 代码1处理
  // case value2 => 代码2处理
  // case value3 => 代码3处理
  // case value4 => 代码4处理
  //  .......
  // case _ => 代码N处理
  // }
  def main(args: Array[String]): Unit = {
    val names = Array("x","y","z")
    val name = names(Random.nextInt(names.length))
    //模式匹配
    name match {
      case "x"=>println("this is x")
      case "y"=>println("this is y")
      case _=>println("you are nothing")
    }
  testScore("50")
    //加条件判断
  testCondition("hwl","5")
    //array test
    testArray(Array("zs"))
    testArray(Array("zs","ff"))
    testArray(Array("zs","ff","hh"))
    testArray(Array("ss"))
    //list  test
    testList(List("zs"))
    testList(List("zs","ff"))
    testList(List("zs","ff","hh"))
    testList(List("ss"))
    //类型匹配  test
    testTypeMath("i")
    testTypeMath(1)
    testTypeMath(Map("x"->1))//scala  map("k"->1)
  }

  def testScore(grade:String): Unit ={
    grade match {
      case "100"=> println("you are so great...")
      case "50"=>println("ooo you are fucked...")
    }
  }

  //加条件匹配  模式匹配
  def testCondition(name:String,grade:String): Unit ={
    grade match {
      case "100"=> println("you are so great...")
      case "50"=>println("ooo you are fucked...")
      //加条件判断
      case _ if name.equalsIgnoreCase("hwl") =>println("you are "+name)
    }
  }

  //数组匹配  array
  def testArray(array: Array[String]): Unit ={
    array match {
      case Array("zs")=>println("hi zs")
      case Array(x,y)=>println("x: "+x +" y:"+y)//任意的2个人
      case Array("zs",_*)=>println("hi hi")//第一个是sz 后面随便什么 多个元素匹配
      case _ => println("hi Everybody")//没有匹配到
    }
  }

  //list 匹配
  def testList(list:List[String]): Unit ={
    list match {
//      case List("zs")=>println("hi zs")
//      case List(x,y)=>println("x: "+x +" y:"+y)
//      case List("zs",_*)=>println("hi hi")
//      case _=>println("hi xx")
      case "zs"::Nil=>println("hi zs")
      case x::y::Nil=>println("x: "+x +" y:"+y)
      case "zs"::tail =>println("hi hi -----------")
      case _=>println("hi xx")
    }
  }

  //类型匹配
  def testTypeMath(obj:Any): Unit ={
    obj match {
      case _:Int=>println("you are Int type...")
      case _:Double=>println("you are Double type...")
      case m:Map[_,_]=>m.foreach(println)
      case _=>println("you are nothing...")
    }
  }

  //case class 模式匹配
  class Person
  case  class CFO(name:String,floor:String)extends Person
  case  class Employee(name:String,floor:String)extends Person
  case  class Other(name: String)extends Person

  def caseClassMatch(person: Person){
    person match {
      case CFO(name,floor)=>println("this is cfo name is  " +name+"floor  "+floor)
      case Employee(name,floor)=>println("this is employee")
      case _=>println("this is nothing...")
    }
  }


}
