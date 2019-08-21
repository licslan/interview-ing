### 多线程可能会问的问题
#### 1.多线程内容总结？
#### LICSLAN回答:
     Java线程：高并发就是基础线程知识和实际经验的组合拳  所以学习多线程掌握原理才是基本 万变不离其中
     
     进程： 资源分配的最小单位
     线程： CPU资源分配的最小单位
     
     所有与进程相关的资源 都被记录在PCB 中  进程控制块 PCB   progress contorl block
     进程是抢占处理机的调度单位 线程属于某个进程 共享其资源
     不同进程拥有不同的虚拟的地址空间  同一进程内不同线程共享同一块虚拟内存地址空间 线程与资源分配无关
     线程不能看做独立的应用  而进程可以看作独立应用
     进程有独立的地址空间  相互不影响 线程只是进程的不同执行路径
     线程没有独立的地址空间  多进程的程序比多线程程序健壮
     进程的切换比线程的切换开销大
     
     Java对操作系统提供的功能进行封装 包括进程和线程
     运行一个程序会产生一个进程 进程包含至少一个线程
     每个进程对应一个JVM 实例 多个线程共享JVM里面的堆
     Java采用单线程编程模型 程序会自动创建主线程  没有主动创建的线程的话  但是JVM 里面会有很多线程执行其他的任务
     就会自动创建一个主线程（所以需要将耗时的内容放到创建的子线程执行 以免阻塞主线程执行 影响用户体验）
     主线程可以创建子线程 原则上要后于子线程完成执行
     
     
     
     线程安全问题的主要诱因
     
     	存在共享数据（也称临界资源）
     	存在多条线程共同操作这些共享资源数据
     
     	解决问题的根本方法：
     		同一时间有且只有一个线程在操作共享数据，其他线程必须等到该线程处理完数据后再对共享数据进行操作
     	互斥锁的特性  synchronized
     		互斥性：即在同时间只允许一个线程持有某个对象锁，通过这种特性来实现多线程的协调机制，这样在
     				同一时间只有一个线程对需要同步的代码块（复合操作）进行访问。互斥性也称为操作的原子性。
     		可见性：必须确保在锁被释放之前，对共享变量所做的修改，对于随后获得该锁的另一个线程是可见的
     		      （即在获得锁时应获得最新共享变量的值），否则另一个线程可能是在本地缓存的耨个副本上继续操作，从而引起不一致。
     		synchronized 锁的不是代码 锁的都是对象		恰当合理给对象上锁 
     
     	根据获取的锁的分类 ： 获取对象锁和获取类锁
     		获取对象锁的两种用法：
     			1.同步代码块（synchronized(this),synchronized(类实例对象)）， 锁是小括号（）中的实例对象
     			2.同步非静态方法	（synchronized method ） 锁是当前对象的实例对象
     		获取类锁的两种方法：
     			1.同步代码块（synchronized(类.class)） 锁是小括号（）中的类对象（Class对象）
     			2.同步静态方法（synchronized static method）, 锁是当前对象的类对象（Class对象）。
     		对象锁和类锁的总结
     			1.有线程访问对象的同步代码块时，另外的线程可以访问该对象的非同步代码块
     			2.若锁住的是同一个对象，一个线程在访问对象的同步代码块时，另一个访问对象的同步代码块的线程会被阻塞
     			3.若锁住的是同一个对象，一个线程在访问对象的同步方法时，另一个访问对象的同步方法的线程会被阻塞
     			4.若锁住的是同一个对象，一个线程在访问对象的同步代码块时，另一个访问对象同步方法的线程会被阻塞反之亦然
     			5.同一个类的不同对象的对象锁互不干扰
     			6.类锁由于也是以中特许的对象锁，因此表现和上述的1234一致，而由于一个类只有一把对象锁，所以同一个的不同
     			  对象使用类锁将会是同步的
     			7.类锁和对象锁互不干扰		
     
     
     sychronized 底层实现原理
     			对象在内存中的布局
     				A:对象头
     				B:实例数据
     				C:对齐填充
     
     				A   mark word ：默认存储对象的hashcode 分代年龄 锁类型 锁标志位等信息（轻量级/偏向锁）
     					class metadata address : 类型指针指向对象的类元数据 JVM通过这个指针确定该对象是哪个类的数据
     					Monitor: 每个Java对象天生自带了一把看不见锁
     
     
     			无锁  锁的重入  偏向锁  自旋锁  自适应自旋锁  轻量级锁  重量级锁 
     			
     
     synchronized 和 ReentrantLock 的区别
     ReentrantLock（再入锁）
     位于java.util.concurrent.locks 包
     和CountDownLatch FutureTask Semaphore 一样基于AQS 实现 
     能够实现比synchronized更加细粒度的控制 如控制fairness  公平性
     调用lock（）之后 必须要调用unlock（）释放锁
     性能未必比synchronized高 （后期版本有很多改进） 并且也是可重入的
     公平锁设置：
     ReentrantLock fairLock = new ReentrantLock(true)
     参数为true时  倾向于将锁赋予等待时间最久的线程
     公平锁：获取锁的顺序按先后调用lock方法的顺序 慎用
     非公平锁：抢占的顺序不一定 看运气
     synchronized时非公平锁  永不公平
     
     
     ReentrantLock 使用习惯时 ReentrantLock fairLock = new ReentrantLock(true)  
     try {lock.lock() // do something }catch(){}finally{lock.unlock}  
     相比synchronized可以像普通对象一样使用来提供各种便利的方法 进行精细的同步操作 
     甚至可以实现synchronized难以表达的用例
     比如：判断是否有线程 或者某个特定线程 在排队等待获取锁
     	  带超时的获取锁的尝试
     	  感知有没有成功获取到锁
     	  能否将wait notify notify all 对象化呢？  YES
     	  java.util.concurrent.locks.Condition做到了上述几点  ArrayBlockingQueue
     1.synchronized 是关键字 ReentrantLock 是类
     2.ReentrantLock 可以对获取锁的等待时间进行设置 避免死锁
     3.ReentrantLock 可以获取各种锁的信息
     4.ReentrantLock 可以灵活地实现多路通知
     5.机制：sync操作mark work   lock调用Unsafe类的park（） 方法
     
     AQS:AbstractQueuedSynchronizer  队列同步器  Java并发用来构建锁或其他同步组件的基础框架，
         是JUC包的核心 一般是继承AQS
     
     
     JMM  Java Memory Model 
     	什么是Java内存模型中happens-before？
     	Java内存模型是一种抽象的概念，并不真实存在 它描述的是一组规则或规范 通过这组规范定义了程序中的
     	各个变量（包括实例字段，静态字段和构成数组对象的；元素）的访问方式
     
     	JMM中的主内存 
     		存储Java实例对象
     		包括成员变量 类信息 常量 静态变量等
     		属于数据共享的区域 多线程并发操作时会引发线程安全的问题
     	JMM 工作内存
     		存储当前方法的所有本地变量信息 本地变量对其他线程不可见
     		字节码行号指示器 Native方法信息
     		属于线程私有数据区域 不存在线程安全问题
     			
     	JMM 和 Java内存区域划分是不同的概念层次
     		JMM 描述的是一组规则 围绕原子性 有序性 可见性展开
     		相似点： 存在共享区域  ===主内存 （堆&方法区）和私有区域  ====工作内存（pc&本地方法栈&虚拟机方法栈）
     	主内存和工作内存的数据存储类型以及操作方式归纳
     		byte Boolean long ... 方法里的基本数据类型本地变量将直接存储在工作内存的栈帧结构中
     		引用类型的本地变量：引用存在在工作内存中，对象实例存储在主内存中（堆）
     		成员变量 static变量 类信息均会存储在主内存中
     		主内存共享的方式是线程各拷贝一一份数据到工作内存，操作完成后刷新回主内存
     
     	JMM 如何解决可见性问题
     		指令重排需要满足的条件	
     			在单线程环境先不能改变程序运行的结果
     			存在数据依赖关系不允许重排序
     		即：无法通过happens-before原则推到出来的 才能进行指令的重排序	
     		happens-before：A操作的结果需要对B操作可见 则A与B存在happens-before关系
     		happens-before 8大原则。。。。
     		如果两个操作不满足上述任意一个happens-before规则，那么这两个操作就没有顺序的保障，JVM可以对这两个操作进行重排
     		如果操作A happens-before操作B 那么操作A在内存上所做的操作对操作B都是可见的	
     		volatile :JVM提供的轻量级同步机制
     			保证被volatile修饰的共享变量对所有线程总是可见的
     			禁止指令的重排序优化
     		volatile + synchronized	
     		volatile变量为何立即可见？
     			当写一个volatile变量是，JMM 会把该线程对象的工作内存中的共享变量刷新到主内存中；
     			当读取一个volatile变量时，JMM  会把该线程对应的工作内存置为无效  就只能从主内存读取变量值
     		volatile如何禁止重排优化？
     			内存屏障（memory Barrier）
     			1.保证特定操作的执行顺序
     			2.保证某些变量的内存可见性
     			通过插入内存屏障指令禁止在内存屏障前后的指令执行重排序优化	
     			强制刷出各种CPU的缓存数据 因此任何CPU上的线程都能读取到这些数据的最新版本
     
    CAS Compare and Swap 
    synchronized  ====>是悲观锁 始终假设会发生并发冲突 因此会屏蔽一切可能发生违反数据完整性的操作
    CAS  ====>乐观锁 首先假设不发发生并发冲突 只在提交操作时 检查是否违反数据完整性  如果提交失败 则会重试
 
    一种高效实现线程安全性的方法
        支持原子更新操作 用于计数器 序列发生器等
        属于乐观锁机制 号称 lock-free
        CAS 操作失败时由开发者决定是继续尝试 还是执行别的操作
        CAS思想
            包含三个操作数 内存位置（V）===主内存的值    预期原值（A） 和新值（B）
 
        CAS多数情况下对开发者来说是透明的
            juc的atomic包提供了常用的原子性数据类型以及；引用/数组等相关原子类型和更新操作工具
            是很多线程安全程序的首选
            Unsafe类虽提供CAS服务，但因能够操作任意内存地址读写而有隐患
            Java9以后 可以使用variable handle api 来代替unsafe
 
            缺点：
                若循环时间长 则开销很大
                只能保证一个共享变量的原子操作
                ABA 问题  解决问题：AtomicStampedReference  
                如果出现ABA 问题 改原子同步互斥可能更高效  synchronized
        
        
        线程池
            内部自带了好几种方法 直接使用就好了  
            Fork/Join 框架 
                把大任务分割成若干个小任务并行执行，最终汇总每个小任务结果后得到大任务结果的框架 类似Hadoop map reduce
                运用了工作窃取算法  work-stealing ：某个线程从其他队列里窃取任务来执行	
 
            JUC 的三个Executor接口
                Executor：运行新任务的简单接口	 将任务提交和任务执行细节解耦
                ExecutorService：具备管理执行器和任务生命周期的方法，提交任务机制更完善 可以有返回值
                ScheduleedExecutorService:支持Future和定期执行任务
            ThreadPoolExecutor	
            ThreadPoolExecutor 的构造函数
                corePoolSize：核心线程数量
                maximumPoolSize:线程不够用时能够创建的最大线程数
                workQueue:任务等待队列
                keepAliveTime:抢占的顺序不一定 看运气
                threadFactory:创建新线程，Executors.defaultThreadFactotu()
                handler:线程池的饱和策略
                    1.AbortPolicy:直接抛出异常，这是默认策略
                    2.CallerRunsPolicy:用调用者所在的线程来执行任务
                    3.DiscardOldestPolicy：丢弃队列中靠最前的任务，并执行当前的任务
                    4.DiscardPolicy:直接丢弃任务
                    可以实现RejectedExecutionHandler接口的自定义handler选择不同策略
 
                    如果运行的线程少于corePoolSize，则创建新线程来处理任务，即使线程中的其他线程时空闲的
                    如果线程池中的线程数据大于等于corePoolSize且小于maximumPoolSize，则只有当workQueue
                    满时才创建新的线程去处理任务
                    如果设置的corePoolSize和maximumPoolSize相同，则创建的线程池的大小时固定的这时如果有新任务提交，
                    若workQueue未满，则将请求放入workQueue中，等待有空闲的线程去workQueue中取任务并处理
                    如果运行的线程数量大于等于maximumPoolSize，这时如果workQueue已经满了 则通过handler所指定的策略来处理任务
