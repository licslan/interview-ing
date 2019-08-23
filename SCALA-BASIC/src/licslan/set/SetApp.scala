package licslan.set

/**
  * Scala set学习 by 黄威林
  * */
object SetApp extends App {

  //set 与list 用法类似  需要注意的是 set 无序不可重复
  val a = Set(1,23.4,6)
  val b = Set(1,1,3,4,4,6,6,7)
  val c = scala.collection.mutable.Set[Int]()
  c+=1
  c+=(3,4,6)
  c++=Set(3,5,6)
  println(a.head)
  println(b)
  println(c)

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
}
