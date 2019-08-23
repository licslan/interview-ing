package licslan.basic

//case  class  通常用在模式匹配中
object CaseClassApp {

  def main(args: Array[String]): Unit = {
    println(Dog("hello").name)
  }
  //case class  不用new
  case class Dog(name:String)


  //Trait 结合抽象类来理解
}
