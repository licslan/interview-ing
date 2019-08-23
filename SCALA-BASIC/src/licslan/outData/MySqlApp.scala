package licslan.outData

import java.sql.{Connection, DriverManager, Statement}

object MySqlApp {

  def main(args: Array[String]): Unit = {

    //和Java差不多方式
    //val driver = "com.mysql.jdbc.Driver"
//    val url ="jdbc:mysql://localhost:3306/mysql"
//    val username="root"
//    val password="root"
//    var connection:Connection=null
//    var statement:Statement=null
//  try{
//     classOf[com.mysql.jdbc.Driver]
//    connection =  DriverManager.getConnection(url,username,password)
//    statement = connection.createStatement()
//    val resultSet = statement.executeQuery("select host,user from user")
//    while (resultSet.next()){
//      val host = resultSet.getString("host")
//      val user = resultSet.getString("user")
//      println(s"$host,$user")
//    }
//  }catch {
//
//    case e:Exception=>println(e.printStackTrace())
//  }finally {
//
//    if(connection!=null){
//      connection.close()
//    }
//  }


    //上面的写法性能不太好 可以改造成数据库连接池来操作链接

  }


}
