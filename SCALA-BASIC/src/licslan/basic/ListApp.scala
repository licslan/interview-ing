package licslan.basic

/**
  * Scala List学习 by 黄威林
  * */
object ListApp extends App {

  //extends App 和
  // def main(args: Array[String]): Unit = {
  //    println(Dog("hello").name)
  //  }效果一样
  //Nil  就是一个空的集合  有序集合  可以重复的集合
  val list_hwl = Nil//空集合   List()
  println(list_hwl)
  val l = List(1,2,3,4,5)
  val li = List(5,5,4,3,2)
  for(i<-l.indices){println(l(i))}
  //拼接集合
  val l2 = 1::Nil//1 作为头 NIL 作为尾
  val l3 = 2::l2 //2作为头 l2 作为尾
  val l4 = 1::2::3::4::Nil
  val l5 = scala.collection.mutable.ListBuffer[Int]()
  l5+=(1,2)
  l5+=(3,4,5)
  l5++=List(6,7,8,9)
  //减去元素
  l5-=2
  l5--=List(3,4,5)
  l5.toArray
  l5.toList
  l5.tail.head

  def sum(nums:Int*): Int ={
    if(nums.isEmpty){
      0
    }else{
      nums.head+sum(nums.tail:_*)//nums.tail:_*  这里的_*表示
      // 在Java中可以直接将数组传给printArgs方法，但是在Scala中，
      // 你必须要明确的告诉编译器，你是想将集合作为一个独立的参数传进去，
      // 还是想将集合的元素传进去。如果是后者则要借助下划线：
      //printArgs(List("a", "b"): _*)
    }
  }



  val l6 = l5::l4

  println(l4)
  println(l3)
  println("-----------")
  println(l.head)//头
  println(l.tail)//尾
  println(l5)
  println(l6)
  println(sum(1,2,3))
  println(List(3,4,5).tail)
  println(li)
}
