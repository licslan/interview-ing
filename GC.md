### GC可能会问的问题
#### 1.垃圾回收内容总结？
#### LICSLAN回答: 
     GC 垃圾回收
     
     	对象被判定为垃圾的标准
     		没有被其他对象引用
     	判断对象是否为垃圾的算法
     		1.引用计数算法
     		2.可达性分析算法	
     
     		对于1：判断对象的引用数量
     			通过判断对象的引用数量来决定对象是否可以被回收
     			每个对象实例都有一个引用计数器 被引用则+1 完成引用-1
     			任何引用计数为0的对象实例可以被当作垃圾收集
     			优点：执行效率高，程序执行受影响较小
     			缺点：无法检测出循环引用的情况，导致内存泄漏
     			主流垃圾收集采用可达性算法
     		对于2：	
     			通过判断对象的引用链是否可达来决定对象是否可以被回收 离散数据的图论由来
     
     		可以作为GC Root的对象 即可达的对象
     			虚拟机栈中引用的对象（栈帧中本地变量表）
     			方法区中的常量引用的对象
     			方法区中的类静态属性引用的对象
     			本地方法栈中JNI（Native方法）的引用对象
     			活跃线程的引用对象
     
     		谈谈你了解的垃圾回收算法
     			标记-清除算法（Mark and Sweep）=======适合老年代垃圾回收(存活率高的对象垃圾回收)
     				标记：从根集合进行扫描，对存活的对象进行标记
     				清除：对堆内存从头到尾进行线性遍历，回收不可达对象内存	
     					碎片化  大量不连续的空间碎片 可能导致程序后面运行时要分配较大的对象时得不到连续内存空间 不得不触发垃圾回收
     			复制算法  ====  适用于对象存活率低的场景  回收年轻代
     				分为对象面和空闲面
     				对象在对象面上创建	
     				存活的对象被从对象面复制到空闲面	
     				将对象面所有对象内存清除
     					解决碎片化问题
     					顺序分配内存 简单高效
     					适用于对象存活率低的场景
     			标记-整理算法  ====  适合老年代垃圾回收		
     				标记：从根集合进行扫描，对存活的对象进行标记
     				清除：移动所有存活的对象，且按照内存地址次序依次排列，然后将末端内存地址以后的内存全部回收   标记-整理算法在标记-清除算法基础上又进行了对象的移动，因此成本更高，但是却解决内存碎片化的问题 
     				避免内存的不连续行
     				不用设置两块内存互换
     				适用于存活率高的场景
     			分带收集算法 =====比较主流的垃圾回收算法
     				垃圾回收算法的组合拳
     				按照对象生命周期的不同划分区域以采用不同的垃圾回收算法
     				目的：提高JVM 的回收效率
     				jdk6/7
     				Young Generation  Old Generation  Permanent Eeneration
     				jdk8+
     				Young Generation  Old Generation
     			分带收集算法GC的分类：
     				Minor GC  发生在年轻代的垃圾回收  复制算法  Java对象的出生的地方
     					年轻代：尽可能快速地收集掉那些生命周期短的对象
     						Eden区  刚分配的对象如果放不了 则可能放到Survivor区或者老年代
     						2个Survivor区  =============from区 to区 2者相互转换
     						Young---->新生代 8：1：1（1/3）  old-----> 老年代（2/3堆空间） 
     				对象如何晋升到老年代？
     					经历一定的Minor次数依然存活的对象
     					Survivor区中村放不下的对象
     					新生成的大对象（-XX:+PretenuserSizeThreshold）
     					常用的调优参数：
     						-XX：SurvivorRatio:Eden和Survivor的比值。默认8：1
     						-XX：NewRatio:老年代和年轻代内存大小的比例
     						-XX：MaxTenuringThreshold:对象从年轻代晋升到老年代经过GC次数的最大阀值
     
     				Full GC  老年代：存放生命周期较长的对象
     					采用标记-清理算法
     					采用标记-整理算法
     					Full GC  & Major GC
     					Full GC 比 Minor GC慢 一般慢10倍以上 但执行频率低
     					触发Full GC 的条件
     						1.老年代空间不足
     						2.永久代空间不足（jdk6/7） jdk8用元空间替代永久代 为了减低Full GC的频率 减少GC 的负担
     						3.CMS GC 时注意是否出现 promotion failed   （Major GC）， concurrent mode failure  CMS
     						4.Major GC 晋升到老年代的平均大小大于老年代的剩余空间
     						5.调用System.gc()
     						6.使用RMI 来进行RPC或者管理JDK应用，每小时执行一次Full GC
     
     				STOP THE WORLD
     					JVM 由于要执行GC 而停止了应用程序的执行
     					任何一种GC 算法中都会发生
     					多数GC优化通过减少stop the world 发生的时间来提高程序性能
     				Safepoint	
     					分析过程中对象引用关系不会发生变化的点
     					产生Safepoint的地方：方法调用；循环跳转；异常跳转等
     					安全点数据得适中
     
     				年轻代常见的垃圾收集器	
     					JVM 的运行模式
     						Server:启动慢  运行稳定后 server要快  重量级虚拟机
     						Clinet:启动快  运行稳定后 client要慢  轻量级虚拟机
     					垃圾收集器之间的联系
     						不同收集器好坏无关  可以搭配使用		
     							Serial收集器（-XX：+UseSerialGc,复制算法） 年轻代
     								单线程收集，进行垃圾收集时，必须暂停所有的工作线程
     								简单高效 client模式下默认的年轻代收集器
     							ParNew收集器（-XX：+UseParNewGC,复制算法） 年轻代
     								多线程收集，其余的行为，特点和Serial收集器一样
     								单核执行效率不如Serial 在多核下执行才有优势 线程默认和cpu一样
     							Parallel Scaverge收集器（-XX:+UseParallerGC，复制算法） 年轻代	
     							吞吐量=运行用户代码时间/（运行用户代码时间+垃圾收集时间）
     								多线程收集
     								比起关注用户线程停顿时间，更关注系统的吞吐量 高吞吐量高利用CPU
     								在多核下执行才有优势，Server模式下默认的年轻代收集器
     								-XX：+UseAdaptiveSicePolicy
     
     				老年代常见的垃圾收集器
     							Serial Old收集器（-XX：+UseSerialOldGc,标记-整理算法）
     								单线程收集，进行垃圾收集时，必须暂停所有的工作线程
     								简单高效 client模式下默认的老年代收集器	
     							Parallel Old收集器（-XX：+UseParallelOldGC,标记-整理算法）
     								多线程，吞吐量优先				
     							CMS收集器（-XX:+UseConcMarkSweepGc,标记-清除算法）	
     								1.初始化标记：stop the world
     								2.并发标记：并发追溯标记，程序不会停顿
     								3.并发预清理：查找执行并发标记阶段从年轻代晋升到老年代的对象
     								4.重新标记：暂停虚拟机，扫描CMS堆中的剩余对象
     								5.并发清理：清理垃圾对象，程序不会停顿
     								6.并发重置：重置CMD收集器的数据结构
     				既可以用于年轻代也可以用于老年代 Grabage Firest
     					G1收集器（-XX：+UseG1GC,复制+标记-整理算法）
     					特点：	
     						并行和并发
     						分代收集
     						空间整合
     						可预测的停顿	
     					将整个Java堆内存划分为多个大小相等的Region
     					年轻代和老年代不在物理隔离	
     
     
     			GC 相关的面试题
     				1.Object的finalize()方法的作用是否与C++的析构造函数作用相同？	
     					与C++的解析构函数不同，析构函数调用确定，而它是不确定的
     					将未被引用的对象放置于F-Queue队列
     					方法执行随时可能会被终止
     					给予对象最后一次重生的机会	
     				2.Java中强引用，软引用，虚引用，弱引用有什么用？
     					强引用 Strong Reference
     						最普遍的引用 Object obj = new Object()
     						JVM 宁可抛出oom 终止程序也不会回收具有强引用的对象
     						通过将对象设置为null来弱化引用，使其被回收
     					软引用（Soft Reference）
     						对象处在有用但非必须的状态
     						只用当内存空间不足时，GC会回收该引用的对象的内存
     						可以用来实现高速缓存	
     						String str = new String ("abc");//强引用
     						SoftReference<String> softRef = new SoftReference<String>(str);//软引用  可以配合引用队列使用
     					弱引用 	Weak Reference
     						非必须的对象，比较软引用更弱一些
     						GC时会被回收
     						被回收的概率也不大，因为GC线程优先级比较低
     						适用于引用偶尔被使用且不影响垃圾收集的对象
     						String str = new String ("abc");//强引用
     						WeakReference<String> weaktRef = new WeakReference<String>(str);//弱引用  可以配合引用队列使用
     					虚引用 PhantomReference
     						不会决定对象的生命周期	
     						任何时候都可能被垃圾收集器回收
     						跟踪对象被垃圾收集回收的活动，起哨兵作用
     						必须和引用队列ReferenceQueue联合使用
     						String str = new String ("abc");//强引用
     						ReferenceQueue queue= new ReferenceQueue()
     						PhantomReference ref = new PhantomReference(str,queue)
     					java 虚拟机对于引用强弱
     						强引用>软引用>弱引用>虚引用	
     
     					引用队列
     						无实际存储结构，存储逻辑依赖于内部节点之间的关系来表达
     						存储关联的且被GC的软引用，弱引用以及虚引用    
