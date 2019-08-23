package licslan.hideChange

import ImplicitAspect._  //封装之后 全部引入即可
import java.io.File

/** 隐式转换
  *  */
object ImplicitApp extends App {

  //那么怎么让普通人飞起来呢？
  //定义隐式转换函数即可   参数 普通对象  返回superman  new  一个超级对象调用   普通对象的属性 man.name
  //implicit def man2superman(man:Man):Superman=new Superman(man.name)
  val man = new Man("hwl")
  man.fly()//将superman中的fly拿过来了....



  //为File增加read方法
  //implicit def file2RichFile(file:File):RichFile=new RichFile(file)
  val file = new File("C:\\Users\\licsl\\Desktop\\Scala-hello.txt")
  var x = file.read
  println(x)


  println("xxxxxxxxxxxxxxxxxxxxx--------------->")


  //隐式参数转换
  def testParam(implicit name:String): Unit ={
    println(name+"``````````````")
  }
  testParam("hwl")
  implicit val name = "xxxx"
  testParam
  //上面2者都可以打印出来说明 没有报错


//  implicit val s1 = "s1"
//  implicit val s2 = "s2"
//  testParam  //这个时候编译不知道放哪一个  报错


  //scala 隐式类转换
  //就是对类增加implicit限定的类 其作用主要是对类的加强
}

class Man(val name:String){
  def eat(): Unit ={
    println(s"man[ $name ] eat ....")
  }
}

class Superman(val name:String){
  def fly(): Unit ={
    println(s"superman[ $name ] fly ....")
  }
}


//io读取
class RichFile(val file:File){
  def read:String={
    scala.io.Source.fromFile(file.getPath).mkString
  }
}