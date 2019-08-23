package licslan.basic

/**
  * 伴生类  &&  伴生对象
  * */
object ApplyApp {

  def main(args: Array[String]): Unit = {
    for(i<- 1 to 10){
      println(ApplyTest.incr)
    }
    println(ApplyTest.count)//10   单例

    var b = ApplyTest()// ========>调用了Object.apply ApplyTest中的apply方法
    var c = new ApplyTest()
    println(c)
    c()  // ========>调用了ApplyTet中的apply ApplyTest class中的apply方法


    //面试中经常考到
    //类名（）===>Object.apply   ApplyTest()
    //对象名（）===》class.apply   new ApplyTest()
  }
}

// class ApplyTest  是 object ApplyTest  的伴生类  同名  ApplyTest
// object ApplyTest  是 class ApplyTest 伴生对象   同名  ApplyTest
class ApplyTest{

  def apply()={
    println("hello world class....")
  }
}
//伴生对象
object ApplyTest{
  var count = 0
  def incr:Int = {
    count = count+1
    count
  }

  //最佳实践：在object的apply方法中去new Class
  def apply()={
    println("hello world object....")

    new ApplyTest
  }
}