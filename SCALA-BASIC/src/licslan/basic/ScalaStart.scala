package licslan.basic

/**
  * Scala 学习 by 黄威林
  * Scala基本语法学习
  * */

object ScalaStart {

  //val(不可变)  vs  var（可变）

  //定义一个主函数入口
  def main(args: Array[String]): Unit = {
    println(licslan(5,6))
    println(add())
    println(add("你好！"))
    println(add)
    println(numberall(1,2,3,4,5))
    println(1 to 5)  // Range(1, 2, 3, 4, 5)  包括 5
    println(Range(1,5)) // Range(1, 2, 3, 4)  不包括5
    println(Range(1,10,2)) // Range(1, 3, 5, 7, 9)
    println(Range(11,1,-3))//步长 3  从 10 开始   Range(11, 8, 5, 2)
    println(1.to(10)) //Range(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(1.until(10)) // Range(1, 2, 3, 4, 5, 6, 7, 8, 9)
    for(i<- 1 to 10){
      println(i)
    }
    println("。。。。。。。。。完美分割线。。。。。。。")
    for (i<- 1 to 10 if i % 2==0 && i<8){println(i)}

    println("。。。。。。。。。完美分割线。。。。。。。")

    fortest
    hwl

    var(num,sum) = (100,0)
    while (num>0){
      sum = sum+num
      num = num -1 //步长
    }
    println(sum)










  }


  //scala定义个一个函数  licslan  ---函数名称
  // x y  参数名称  Int 参数的类型   Int =  ---表示返回的数据类型
  def licslan(x:Int,y:Int):Int={
    x*y-x-y  //注意这里不需要  return  不像Java 那样
  }


  //scala  默认是参数的使用  默认使用 hello world
  def add (f:String ="hello world"):String={
    "this is a test for deafult parmas..."+f
  }

  def add: String ={
    "this is a test for deafult parmas"
  }

  //可变参数  JDK5+就提供了 可变参数
  def numbera (a:Int,b:Int): Unit ={
    a+b
  }
  def numberb (a:Int,b:Int,c:Int): Unit ={
    a+b+c
  }

  //可变参数 计算求和  使用for循环  numbers:Int*
  def numberall(numbers:Int*): Int ={
    var a = 0
    for (number<- numbers){
      a += number
    }
    a
  }

  //Scala循环表达式
  // to  Range  Until


  val names = Array("licslan","licslin","hwl","hellohwl","lin")
  def fortest=names.foreach(name=>println(name))
  def hwl = for(name<-names){println(name)}
  //names.foreach(name=>println(name))

}
