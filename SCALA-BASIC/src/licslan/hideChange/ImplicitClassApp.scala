package licslan.hideChange

//scala 隐式类转换
//就是对类增加implicit限定的类 其作用主要是对类的加强
object ImplicitClassApp extends App {

  implicit class Calculator(x:Int){
    def add(a:Int): Int =a+x
  }

  println(1.add(3))
}

