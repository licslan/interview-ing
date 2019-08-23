package licslan.hideChange

import java.io.File

/**
  * 将隐式转换的方法封装一下
  * */
object ImplicitAspect extends App {

  implicit def man2superman(man:Man):Superman=new Superman(man.name)
  val man = new Man("hwl")
  man.fly()//将superman中的fly拿过来了....



  //为File增加read方法
  implicit def file2RichFile(file:File):RichFile=new RichFile(file)
  val file = new File("C:\\Users\\licsl\\Desktop\\Scala-hello.txt")
  var x = file.read
  println(x)
}
