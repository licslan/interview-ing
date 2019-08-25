### Mybatis3+ 学习与总结
#### 以前只知道怎么去使用 其实也不是完全怎么使用了吧 就是编程的时候知道怎么CRUD?
#### LICSLAN回答
     怎么说呢 其实就是一个CRUD工程师 很SB 的吧 但是一直不想就这么平庸 这么平庸下去
     今天就来看看mybatis做了什么吧 我也是硬着头皮去看的 我们一起相互学习 最近在找工作
     也想找到一份满意的工作 既然没有女朋友 那就在其他方面写点什么吧 学习英语 学习代码源码...
     总之就是不让自己闲着吧  哪怕一个人也无所谓  一个人行走的快  利于思考  不容考虑其他人
     等其他人... so 也 喜欢一个人  暂时而已。
     好的 言归正传~~~~ ^_^
     Mybatis是支持定制化SQL、存储过程和高级映射的持久层框架。主要完成两件事：
     {封装JDBC的操作}
     {利用反射(反射可以了解之前写的内容)完成Java类和SQL之间的转换}
     
     
     mybatis的主要目的就是管理执行SQL是参数的输入和输出，编写SQL和结果集的映射是mybatis的主要优点
     mybatis中主要类和接口
     Configuration：将mybatis配置文件中的信息保存到该类中
     SqlSessionFactory：解析Configuration类中的配置信息，获取SqlSession
     SqlSession：负责和数据库交互，完成增删改查
     Executor：mybatis的调度核心，负责SQL的生成
     StatementHandler：封装了JDBC的statement操作
     ParameterHandler：负责完成JavaType到jdbcType的转换
     ResultSetHandler：负责完成结果集到Java Bean的转换
     MappedStatement：代表一个select|update|insert|delete元素
     SqlSource：根据传入的ParamterObject生成SQL
     BoundSql：包含SQL和参数信息
