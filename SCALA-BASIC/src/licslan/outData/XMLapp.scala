package licslan.outData

import java.io.{FileInputStream, InputStream, InputStreamReader}

import scala.xml.XML

object XMLapp {

  def main(args: Array[String]): Unit = {

    loadXML()
  }
  def loadXML(): Unit ={
    //1.val xml =XML.load(this.getClass.getClassLoader.getResource("test.xml"))
    //2.val xml = XML.load(new FileInputStream("C:\\Users\\licsl\\Desktop\\Scala-learning\\scala-learn\\src\\main\\resource\\test.xml"))
    //3.val xml = XML.load(new InputStreamReader(new FileInputStream("C:\\Users\\licsl\\Desktop\\Scala-learning\\scala-learn\\src\\main\\resource\\test.xml")))
    //println(xml)
    //接下来就是读取XML 中节点元素  可以查资料解决
  }
}
