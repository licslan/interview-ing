package licslan.highFun

/**
  * Scala 函数学习 by 黄威林
  * */
object FunctionApp extends App{

  //Scala 匿名函数学习 by 黄威林
  //（参数名：参数类型...）=>函数体
  //有函数名称 syaSth
  def saySth(name:String): Unit ={
    println("you are talking about sth "+name)
  }
  saySth("hwl")

  val saySthxxx = (name:String)=>{println("you are talking about sth "+name)}
  println(saySthxxx("testing"))
  //匿名就是没有名字的函数
  val m = (x:Int,y:Int)=>{x+y}//将一个没有函数名称表达式赋值给一个变量
  println(m(2,3))
  //curry  函数
  def sum(a:Int,b:Int)=a+b
  println(sum(2,5))
  //curry 改造  将原来接收2个参数的函数转换成2个
  def sum2(a:Int)(b:Int)=a+b
  println(sum2(2)(7))
  println("```````````高阶函数学习`````````````")
  //高阶函数！！！
  val ll = List(1,2,3,4,5,6,7,8,9)
  println("```````````高阶函数Map学习`````````````")
  //map  逐个去操作集合中的每个元素
  println(ll.map((x:Int)=>x+1))//全称写法
  println(ll.map((x)=>x+1))//简写
  println(ll.map(x=>x+1))//一个元素括号可以省略
  println(ll.map(_+1))//继续简写  就是每一个占位符（任意一个元素）+1  看到这个就不感觉spark的蒙蔽了  之前学习spark flink  多少有点懵逼。。。。
  ll.map(_*2).foreach(println)//打印每个操作后的元素
  println("```````````高阶函数filter学习`````````````")
  //filter  过滤的使用  比如过滤大于8 的元素
  ll.map(_*2).filter(_>8).foreach(println)
  //take
  ll.take(2)//取集合的前2个元素
  ll.take(4)//取集合的前4个元素
  println("```````````高阶函数reduce学习`````````````")
  ll.reduce(_+_)//表示集合两两相邻的元素加起来操作  求和   ==== ll.sum
  ll.reduceLeft(_-_)
  ll.reduceRight(_-_)
  ll.fold(0)(_-_)
  ll.foldLeft(0)(_-_)
  ll.foldRight(0)(_-_)
  ll.max
  ll.min
  //flatten
  val f = List(List(1,2),List(3,4),List(5,6))  //List[List(Int)]
  println(f.flatten)  //List(Int)   将f里面的元素压扁放在一起了

  f.map(_.map(_*2))//List[List(Int)]
  f.flatMap(_.map(_*2))//List(Int)

  //flatMap  ======>  flatten + map  flatten  将原属压扁放在一起  再加上map 对每一个压扁的进行操作  map操作

  //C:\Users\licsl\Desktop\Scala-hello.txt
  val txt = scala.io.Source.fromFile("C:\\Users\\licsl\\Desktop\\Scala-hello.txt").mkString
  println(txt)
  val listTxt = List(txt)
  println("```````````````````````````-----------------")
  listTxt.foreach(println)
  println("--------------------------``````````````````")
  listTxt.flatMap(_.split(",")).foreach(println)
  listTxt.flatMap(_.split(",")).map(x=>(x,1)).foreach(println)
  listTxt.flatMap(_.split(",")).map((_,1)).foreach(println)

  println("如何使用Scala完成world count 统计")
  //如何使用Scala完成world count 统计    链式编程
  // 参考  https://blog.csdn.net/qq_31780525/article/details/79036728
  val gg = listTxt.
    flatMap(_.split(",")).//压扁
    map((_,1)).//元祖
    groupBy(_._1).//分组
    map(t=>(t._1,t._2.size)).
    toList.
    sortBy(_._2).
    reverse

  gg.foreach(println)


  //片函数
}
