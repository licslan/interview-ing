package licslan.basic

/**
  * scala 异常处理学习 by 黄威林
  * */
object ExceptionApp extends App {

  val file = "/xx/text.txt"
  val  i =10/2
  println(i)
  //val j = 10/0   //Exception in thread "main" java.lang.ArithmeticException: / by zero
  //println(j)
  //改造上述代码
  try{
    //IO流处理
    //1.open file
    //2.use file
    val j = 10/0
    println(j)
  }catch {
    case _:java.lang.ArithmeticException =>println("除数不能为0....")
    case y:Exception =>println(y.getMessage)
  }finally {
    //一般是释放资源 一定可以执行
    //3.close file  释放资源
  }
}
