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
     [transforming] it. Users may also ask Spark to [persist an RDD in memory], allowing it to be reused efficiently 
     across parallel operations. Finally, RDDs [automatically recover] from node failures.
     
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
     
      5  - Optionally, a list of preferred locations to compute each split on (e.g. block locations for an HDFS file)
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
     map is a transformation that passes each dataset element through a function and returns a new RDD representing the 
     results. On the other hand, reduce is an action that aggregates all the elements of the RDD using some function 
     and returns the final result to the driver program (although there is also a parallel reduceByKey that returns a 
     distributed dataset).
     
     All transformations in Spark are [lazy], in that they do not compute their results right away. Instead, they just 
     remember the transformations applied to some base dataset (e.g. a file). [The transformations are only computed 
     when an action requires a result to be returned to the driver program]. This design enables Spark to run more 
     efficiently. For example, we can realize that a dataset created through map will be used in a reduce and return 
     only the result of the reduce to the driver, rather than the larger mapped dataset.
     
     By default, each transformed RDD may be recomputed each time you run an action on it. However, you may also 
     [persist] an RDD in memory using the persist (or cache) method, in which case Spark will keep the elements around 
     on the cluster for much faster access the next time you query it. There is also support for persisting RDDs on 
     disk, or replicated across multiple nodes.	  
 
                              