### spark总结学习
#### 1.spark是什么？
#### LICSLAN回答：
     首先让我们来看官网吧 https://spark.apache.org
     Apache Spark™ is a 
     [unified analytics engine]
     for 
     [large-scale data processing]
#### 2.spark 的核心RDD理解？
#### LICSLAN回答：
     首先看下官网怎么描述的吧  重要关键字用[]包起来了
     
     [At a high level], every Spark application consists of a [driver program] that runs the user’s main function 
     and [executes] various parallel operations on a cluster. The main abstraction Spark provides is a resilient 
     distributed dataset (RDD), which is a [collection of elements partitioned] across the nodes of the cluster 
     that can be operated on in parallel. RDDs are [created by] starting with a file in the Hadoop file system 
     (or any other Hadoop-supported file system), or an existing Scala collection in the driver program, and 
     [transforming] it. Users may also ask Spark to [persist an RDD in memory], allowing it to be reused 
     efficiently across parallel operations. Finally, RDDs [automatically recover] from node failures.
     
     [A second abstraction] in Spark is [shared variables] that can be used in parallel operations. By default, when 
     Spark runs a function in parallel as a set of tasks on different nodes, it [ships a copy of each variable] used 
     in the function to [each task]. Sometimes, a variable needs to be shared across tasks, or between tasks and the 
     driver program. Spark supports two types of shared variables: [broadcast variables], which can be used to cache 
     a value in memory on all nodes, [and accumulators], which are variables that are only “added” to, such as 
     counters and sums.
     
     RDD有5个很重要的概念在代码里面有体现。大家可以关注源码RDD.scala
     https://github.com/apache/spark/blob/master/core/src/main/scala/org/apache/spark/rdd/RDD.scala 
     * Internally, each RDD is characterized by five main properties:
      1  - A list of partitions   
      	 一个RDD 由多个分区/分片构成
      	 对应源码：protected def getPartitions: Array[Partition]
     
      2  - A function for computing each split（partitions） 
      	 对一个RDD 执行一个函数就是对一个RDD 里面所有分区执行这个函数（方法）的操作  
      	 对应源码：def compute(split: Partition, context: TaskContext): Iterator[T]
     
      3  - A list of dependencies on other RDDs
      	 RDD1====>RDD2====>RDD3=====>RDD4   RDD4依赖RDD3  RDD3依赖RDD2  RDD2依赖RDD1  这些数据存在依赖关系的
      	 假如RDD_A 有5个partitions  那么Map操作RRD_A形成的RDD_B 也会有5个分区partitions   血缘关系 也是核心所在 
      	 如果RDD_A中某个分区数据丢失  RDD_B会重新计算丢失的分区  而是计算所有的数据  因为有依赖关系血缘关系！！！
     	 对应源码：protected def getDependencies: Seq[Dependency[_]] = deps
     
      4  - Optionally, a Partitioner for key-value RDDs (e.g. to say that the RDD is hash-partitioned)
      	 可选特性  这个是对应key-value格式的RDD使用并非所有RDD都具有  分区的key-value
      	 对应源码：@transient val partitioner: Option[Partitioner] = None
     
      5  - Optionally, a list of preferred locations to compute each split on(e.g. block locations - HDFS file)
      	 数据在哪优先把作业调度到数据所在的节点进行计算：移动数据不如移动计算
      	 所以计算spilt的时候选择最佳位置来计算
      	 为什么会有多个位置呢？ locations  location有s?
      	 源码对应： protected def getPreferredLocations(split: Partition): Seq[String] = Nil
#### 3.spark 算子类型有哪些？
#### LICSLAN回答：
     spark里面主要有2种类型的算子 一种是  transformation 算子(LAZY)  一种是 action 算子
     来看看官网怎么描述的吧  重要关键字用[]包起来了
     RDDs support two types of operations: [transformations], which create a new dataset from an existing one, and 
     [actions], which return a value to the [driver program] after running a computation on the dataset. For example, 
     map is a transformation that passes each dataset element through a function and returns a new RDD representing 
     the results. On the other hand, reduce is an action that aggregates all the elements of the RDD using some 
     function and returns the final result to the driver program (although there is also a parallel reduceByKey that
     returns a distributed dataset).
     
     All transformations in Spark are [lazy], in that they do not compute their results right away. Instead, they just 
     remember the transformations applied to some base dataset (e.g. a file). [The transformations are only computed 
     when an action requires a result to be returned to the driver program]. This design enables Spark to run more 
     efficiently. For example, we can realize that a dataset created through map will be used in a reduce and return 
     only the result of the reduce to the driver, rather than the larger mapped dataset.
     
     By default, each transformed RDD may be recomputed each time you run an action on it. However, you may also 
     [persist] an RDD in memory using the persist (or cache) method, in which case Spark will keep the elements 
     around on the cluster for much faster access the next time you query it. There is also support for persisting
     RDDs on disk, or replicated across multiple nodes.	  
     
#### 3.谈谈spark 核心架构吧？
#### LICSLAN回答： 
![SPARK00](https://github.com/licslan/interview-ing/raw/master/SPARK/SPARK00.jpg)  
     
     SPARK CORE:
        包含Sparkd基本功能，尤其是定义RDD的API，操作.其他的spark的库都是构建在RDD和spark core上
     SPARK SQL:
        提供通过apache hive的SQL变体hive查询语言（HiveQL）与spark 进行交互的API。每个数据库表被当作一个RDD
        spark SQL 查询被转换为spark操作
     SPARK STREAMING
        对实时数据流进行处理和控制，spark streaming 允许程序能够像普通RDD一样处理实时数据
     SPARK MLLIB
        一个常用的机械学习算法库，算法被实现为对RDD的spark操作，这个库包含了可扩展的学习算法，比如分类 回归 随机森林...
     GRAPHX
        控制图，并行图操作和计算的一组算法和工具的集合。GraphX扩展了RDD API 包含控制图,创建子图,访问路径上所有顶点的操作              
#### 4.谈谈spark 核心组件吧？
#### LICSLAN回答：
     参考官网：
     Spark applications run as independent sets of processes on a cluster, coordinated by the SparkContext 
     object in your main program (called the driver program).
     Specifically, to run on a cluster, the SparkContext can connect to several types of cluster managers 
     (either Spark’s own standalone cluster manager, Mesos or YARN), which allocate resources across 
     applications. Once connected, Spark acquires executors on nodes in the cluster, which are processes 
     that run computations and store data for your application. Next, it sends your application code 
     (defined by JAR or Python files passed to SparkContext) to the executors. Finally, SparkContext sends 
     tasks to the executors to run.
![SPARK01](https://github.com/licslan/interview-ing/raw/master/SPARK/SPARK01.jpg)
     
     There are several useful things to note about this architecture:
     A.Each application gets its own executor processes, which stay up for the duration of the whole 
       application and run tasks in multiple threads. This has the benefit of isolating applications 
       from each other, on both the scheduling side (each driver schedules its own tasks) and executor 
       side (tasks from different applications run in different JVMs). However, it also means that data 
       cannot be shared across different Spark applications (instances of SparkContext) without writing 
       it to an external storage system.
     B.Spark is agnostic to the underlying cluster manager. As long as it can acquire executor processes, 
       and these communicate with each other, it is relatively easy to run it even on a cluster manager 
       that also supports other applications (e.g. Mesos/YARN).
     C.The driver program must listen for and accept incoming connections from its executors throughout 
       its lifetime (e.g., see spark.driver.port in the network config section). As such, the driver program 
       must be network addressable from the worker nodes.
     D.Because the driver schedules tasks on the cluster, it should be run close to the worker nodes, 
       preferably on the same local area network. If you’d like to send requests to the cluster remotely, 
       it’s better to open an RPC to the driver and have it submit operations from nearby than to run a 
       driver far away from the worker nodes.
       Cluster-Manager 控制整个集群，监控worker,在Stand alone模式中为Master主节点，控制整个集群，在YARN模式中
       为资源管理器
       worker节点负责计算节点 从节点负责计算节点，启动Executor或者Driver
       Driver:运行Application的main()函数
       Executor:执行器是为某个application运行在worker node上的一个进程
#### 5.谈谈spark 编程模型吧？
#### LICSLAN回答
![SPARK02](https://github.com/licslan/interview-ing/raw/master/SPARK/SPARK02.jpg)<br>
     
     Spark应用程序从编写到提交，执行，输出的整个过程如图所示，描述如下：
![SPARK03](https://github.com/licslan/interview-ing/raw/master/SPARK/SPARK03.jpg)     
#### 6.谈谈spark 计算模型吧？
#### LICSLAN回答
     RDD可以看作是对各种数据计算模型的统一抽象，spark的计算过程主要是RDD的迭代计算过程。RDD的迭代
     计算过程非常类似于管道。分区数量取决于partition数量的设定，每个分区的数量只会在一个Task中计算
     所有分区可以在多个机器节点的Executors上并行执行   
![SPARK04](https://github.com/licslan/interview-ing/raw/master/SPARK/SPARK04.jpg) 
#### 7.谈谈spark 运行流程吧？
#### LICSLAN回答
     A.构建spark application的运行环境 启动sparkcontext
     B.sparkcontext向资源管理器（Standalone,Mesos,Yarn,k8s..）申请运行Executor资源，并启动
       Standalone Executor 后台服务
     C.Executor向SparkContext申请Task
     D.SparkContext将应用程序分发给Executor
     E.SparkContext构建成DAG图，将DAG图分解成stage，将Taskset发送给Task Scheduler,最后由Task
       Scheduler 将Task发送给Executor运行
     F.Task在Executor上运行，运行完释放所有资源   
![SPARK05](https://github.com/licslan/interview-ing/raw/master/SPARK/SPARK05.jpg)      
     
     
     
     
                              
