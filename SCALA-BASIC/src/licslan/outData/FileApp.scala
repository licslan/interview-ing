package licslan.outData

import scala.io.{Codec, Source}

object FileApp {

  def main(args: Array[String]): Unit = {
    val file = Source.
      fromFile("C:\\Users\\licsl\\Desktop\\Scala-hello.txt")(Codec.UTF8)
    //一行一行读
    def readLine(): Unit ={
      for(line<- file.getLines()){
        println(line)
      }
    }
    readLine()

    //一个字符一个字符读
    def readChar(): Unit ={
      for(els<-file){
        println(els)
      }
    }
    readChar()


    //读取网络数据
    def readNetwork(): Unit ={
     val file = Source.fromURL("http://www.baidu.com")
      for (els<-file.getLines()){
        println(els)
      }
    }
    readNetwork()

  }
}
