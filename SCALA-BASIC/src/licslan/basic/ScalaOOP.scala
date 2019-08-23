package licslan.basic

/**
  * scala面向对象的学习 by 黄威林
  * Scala是集面向对象和函数式编程于一体的语言  ********  非常重要！！！
  * 封装  private  getter setter
  * 继承  父类和子类的关系  extends
  * 多态  父类引用指向子类对象
  * */
object ScalaOOP {

  def main(args: Array[String]): Unit = {
    val user = new people()
    println(user.age)
    println(user.add())
    println(user.printInfo())
    //println(user.gender)
  }

  //定义类
  class people{
    //定义属性
    var name:String = ""
    //var name:String = _   占位符
    val age:Int =10

    // private 修饰的属性仅在class里面可以使用
    private [this] val gender = "male"

    def printInfo(): Unit ={
      println("gender "+gender)
    }

    //定义方法
    def eat(): String = {
        "hello world"
    }
    //定义方法
    def add(name :String="xx"):String ={
      name + "   hello world"
    }
  }

  //主构造器


  //附属构造器


}
