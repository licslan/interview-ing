package licslan.basic

//抽象类
object AbstractApp {

  def main(args: Array[String]): Unit = {

    val  xx = new licslan()
    println(xx.age+"..."+xx.speakEnglish+"..."+xx.name)
  }

  /**
    * 抽象类：类的一个或者多个方法没有完整的实现  只有定义 没有实现
    * */
  abstract class hwl(){
    def speakEnglish()  //定义名称 没有实现
    val name:String
    val age:Int
  }
//1
  //abstract class licslan extends hwl
//2
  class licslan extends hwl{
  override val age: Int = 28
  override val name: String = "LICSLAN"
  override def speakEnglish(): Unit = {
    println("Weiling huang speaks english....")
  }

}


}
