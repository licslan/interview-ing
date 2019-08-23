package licslan.basic

/**
  * Scala数组学习 by 黄威林
  * */
object ArrayApp extends App {

//  def main(args: Array[String]): Unit = {
//    print("test")
//  }
  println("test")

  //初始化一个数组
  val a = new Array[String](5)
  a(1)="licslan"
  println(a(1))
  val b = Array("licslan","x","y")//底层使用apply方法
  println(b(0))
  println(a.length)
  val c = Array(1,2,3,4,5,6,7,8,9)
  println(c.sum)
  println(c.max)
  println(c.min)
  println(c.mkString("."))

  println("-------------分割线---------------")
  //可变数组
  val d = scala.collection.mutable.ArrayBuffer[Int]()
  d +=1
  d +=2
  d +=(3,4,5)
  d ++=Array(6,7,8)
  d.insert(0,0)
  d.remove(1)
  d.remove(0,3)
  d.trimEnd(2)
  println(d)
  //for遍历数组
  for(i<- 0 until d.length) println(d(i))
  println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
  for(x<- d) println(x)
  println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
  for(i<- 0 until(d.length)) println(d(i))
  println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
  for(i<- d.indices)println(d(i))
  //反序输出
  println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
  for(i<-d.indices.reverse)println(d(i))
  //可变装换成不可变
  println(d.toArray.mkString("_"))
}
