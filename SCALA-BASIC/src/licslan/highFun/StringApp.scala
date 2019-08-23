package licslan.highFun

object StringApp extends App {

  val x = "hello "
  val y = "world"
  println(x+y)
  println(s"$x$y")
  //多行字符串
  val many =
    """
      |hello
      |world
      |this
      |is your
      |game
    """.stripMargin
  println(many)
}
