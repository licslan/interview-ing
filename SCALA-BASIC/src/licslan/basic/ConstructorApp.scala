package licslan.basic

object ConstructorApp {

  def main(args: Array[String]): Unit = {

    val person = new person("sz",30)
    println(person.age+"..."+person.name+"..."+person.school)
    val person2 = new person("sz",30,"F")
    println(person2.age+"..."+person2.name+"..."+person2.school+"..."+person2.gender)

    println("----------------------------------------------")

    val student = new student("hwl",28,"english")
    println(student.gender+"..."+student.school+"..."+student.name+"..."+student.age+"..."+student.major)

    println(student.toString())
    //you are enter.....
    //you are leaving...
    //the student is coming
    //the student is leaving

    //上述结果表明  会先执行父类的方法
  }

  //定义构造器   主构造器
  class person(val name:String,val age:Int){
    println("you are enter.....")

    val school = "MIT"
    var gender :String = _  //"hello" //占位符 null
    //定义附属构造器
    def this(name: String,age:Int,gender:String){
      this(name,age) //附属构造器的第一行代码必须调用主构造器或者其他的附属构造器
      this.gender = gender
    }

    println("you are leaving...")
  }


  //继承&重写
  class student(name:String,age:Int,var major:String) extends person(name:String,age:Int){
    println("the student is coming")

    override def toString(): String ={
      "use deafult method toStirng"
    }

    override val school: String = "peking"


    println("the student is leaving")
  }
}
