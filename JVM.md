### JVM可能会问的问题
#### 01.你怎么看待Java这门语言呢？
#### LICSLAN回答: 
     A.平台无关性  Linux  window mac ...
     B.一次编译到处运行 Compile once Run Anywhere
     C.面向对象  封装 继承 多态
     D.类库，异常处理 垃圾回收
#### 02.一次编译到处运行怎么实现呢？
#### LICSLAN回答: 
     一次编译到处运行看下图 
     1.我们在IDEA / eclipse中编写好代码 
     2.我们手动 javac xx.java --->  xx.class
     3.java xx.class ---> 得到运行结果 编译报错或者执行成功
     4.javap xx.class 我们反编译 得到一些字节码指令可以看 ....
     [其实在第3步时候 我们是JVM 将我们编译好的class文件加载到内存 并转化成本操作系统能识别的机器码并执行得到结果
     当然你也可以将编译好的class文件（字节码文件考到任何一台主机上面执行 java  xx.class 当然要主要包路径喔！！！） 
     so that`s the complie once run anywhere u got it?]     
![JVM00](https://github.com/licslan/interview-ing/raw/master/JVM-GC/JVM00.jpg)
#### 03.为什么JVM不直接将源码解析成机器码去执行呢？
#### LICSLAN回答: 
     如果直接解析为机器码的话那么每次执行就会：
     A.准备工作每次都要做：每次都要检查语法，句法语义的，这些检查都不会被保存下来
     B.被编译成中间字节码后就不需要以后每次做重复的工作从而提高效率
     C.兼容性 也可以将别的语言解析成字节码 如Grovey scala等 这符合软件设计的中庸之道
#### 04.JVM如何加载.class文件？
#### LICSLAN回答: 
     JVM(JAVA VIRTAL MACHINE)是仿真模拟了一套计算机系统功能，如处理器/堆栈/寄存器等。还有相应的指令系统
     JVM 屏蔽了与具体操作系统相关的信息使得Java程序只需生成在Java虚拟机上运行的目标代码字节码就可以在多种平台不加修改的运行
     一般情况我们不需要知道jvm运行原理只需要专注写Java代码即可 这也是虚拟机存在的原因就是屏蔽底层操作系统平台的不同并且减少
     基于原生语言开发的复杂度只需要虚拟机厂商在特定的操作系统上实现虚拟机定义了如何将字节码解析成操作系统可执行的二进制码即可
     JVM 内存模型 和 gc回收机制需要重点学习  知道程序如何调优 JVM 是一个内存中的虚拟机 也就意味其存储就是内存！！！
     我们在代码中所写的类 常量 变量 方法都在内存中 其决定着我们程序运行是否健壮是否高效
     [classLoader]  [Runtime Data Area]  [Execution Engine]  [Native Interface]
     class Loader:依据特定格式 加载class文件到内存
     Execution Engine:对命令进行解析
     Native Interface:融合不同开发语言的原生库为Java使用  Java执行性能并没有c c++高
     在一些执行性能较高的操作需要调用其他语言的原生库 比如c++ 就要使用Native Interface来调用 而不重复造轮子
     在Native Method Stack中登记Native方法 在Execution Engine
     执行时加载Native库如Class.forName(),forName0()是Native方法
     Runtime Data Area:JVM内存空间结构模型 我们所写的程序最后都会被加载到这里之后才开始运行
![JVM01](https://github.com/licslan/interview-ing/raw/master/JVM-GC/JVM01.jpg)     
#### 05.谈谈Java反射机制？
#### LICSLAN回答: 
     JAVA反射机制是在运行状态中，对任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的
     任意方法和属性；这种动态获取信息以及动态调用对象方法的功能称为Java语言的反射机制
#### 06.类从编译到执行的过程？
#### LICSLAN回答: 
     1.编译器将licslan.java源文件编译为licslan.class字节码文件  byte形式传进来
     2.ClassLoader将字节码装换为JVM中的Class<licslan>对象
     3.JVM利用Class<licslan>对象实例化为licslan对象
#### 07.谈谈什么是classloader？
#### LICSLAN回答: 
     [ClassLoader在Java中有着非常重要的作用 它主要工作在Class装载过程的加载阶段 其主要作用是
     从外部系统获取Class二进制数据流 它是Java的核心组件 所有的Class都由ClassLoader进行加载的
     ClassLoader负责将Class文件里面的二进制数据流装载到系统，然后交给Java虚拟机链接，初始化]
#### 08.谈谈classloader种类？
#### LICSLAN回答: 
     [classloader中有个重要方法 loadeClass（）  进去发现里面有个prent !!!]
     1.BootstrtpClassLoader: c++编写，加载核心库java.*  如java.lang包下面的 用户不可见
        navtive修饰的方法如果要看的话 可以去openjdk代码去查看  有的是c/c++实现
     2.ExtClassLoader: Java编写 加载扩展库javax.* 用户可以自定义jar放到Ext目录下自定义加载
     3.AppClassLoader: Java编写 加载程序所在目录 用户可见 加载classpath下面的内容 类路径
     4.自定义ClassLoader：Java编写 定制化加载 可能不在classpath下面 或者不是class/jar文件
       自定义classLoader实现需要覆盖2个函数
       findClass(String name)  寻找class文件 怎么去读二进制流
       defineClass(byte[],b,off,len,null)  定义这么一个类 返回class
       自定义classLoader应用有很多 比如对敏感class加密  字节码增强技术  AOP实现也能借鉴该方式
#### 09.谈谈类加载器的双亲委派机制吧？
#### LICSLAN回答: 
     [
     不同classloader相互共存各司其职加载各自管理的区域  检查.class字节码并加载字节码
     1.从低向上检查类是否已经加载过
       08.4-->08.3-->08.2-->08.1 到各自检查该类是否有被加载过 没有就委派上面的面的人去检查
     2.从顶向下尝试加载类   
       08.1-->08.2-->08.3-->08.4 到各自管理的目录区域去找是否有该类并加载没有就委派下面的人去加载
     ]
#### 10.谈谈类加载器为什么要使用双亲委派机制呢？
#### LICSLAN回答: 
     1.避免多份同样字节码的加载 内存很宝贵 
       没有必要保存相同的类对象 class对象 byte字节码 并非new 的实例对象            
#### 11.谈谈Java类加载方式？
#### LICSLAN回答: 
     A.隐式加载：new  构造函数初始化
     B.显式加载：loadClass forName 等    
#### 12.谈谈Java类加载loadClass forName区别？
#### LICSLAN回答: 
     A.二者都满足反射机制 可以获取对任意一个类/对象的方法和属性
     B.类的装载过程
        1）加载：通过ClassLoader加载class字节码文件，生成class对象
        2）链接
            （1）校验：检查加载的class的正确性和安全性
            （2）准备：为类变量(static)分配存储空间并设置类变量初始值，类变量存在方法区中生命周期长，注意内存泄漏
            （3）解析：JVM将常量池内的符号引用转换为直接引用       
        3）初始化：执行类变量赋值和静态代码块   
     C.点到代码里面去看  Class.forName("全路径类名") & 类名.class.getClassLoader()
       Class.forName得到的class是已经初始化完成的  finshed B.2).(3)
       Classloader.loadClass得到的class是还没有链接的  finshed B.1) 
       这个时候就知道2者的区别了吧  ops~~~   
#### 13.既然知道了Java类加载loadClass forName区别，那么这样做有什么作用吗，举个例子？
#### LICSLAN回答:   
     A.比如数据库链接驱动Class.forName("com.mysql.jdbc.driver")其实driver里面有静态代码段可以生成对象使用
     B.spring IOC资源加载器获取独立的资源的时候读取bean配置的时候，之所以这样是和ioc lazy加载有关 可以
       加快初始化速度 spring IOC 就大量使用了延时加载技术 就是用了classLoader  不需要初始化 把类的加载工作留到
       实际使用到这个类的时候再去加载
     C.所以看来2者在使用上都很有意义  
#### 14.谈谈Java内存模型吧？
#### LICSLAN回答:  
     首先看看下面计算机的内存描述吧  其实我们对于计算机的理解基本都依照计算机冯诺依曼体系结构
     计算机所以程序都是在内存中运行的 内存可能包括虚拟内存和硬盘这样的外存支持 在程序运行过程中需要不断将
     内存的逻辑地址和物理地址映射，再将执行相关指令  Java内存受限制与操作系统的可寻址空间 如32 64位
     32位处理器：2^32的可寻址范围  4GB   用户进程<=3G内核代码可以访问所有物理内存
     64位处理器：2^64的可寻址范围  远大于1T...  用户进程<=512G内核代码可以访问所有物理内存
     地址空间可分为：内核空间和用户空间
     内核空间：是主要操作系统程序和c运行空间 链接计算机硬件 调度程序 联网 虚拟内存等服务 基于c的进程
     用户空间：Java程序实际运行空间
     Java程序运行在虚拟机之上运行时需要内存空间，虚拟机执行Java程序过程中会将其管理的内存划分为不同的数据区
     线程私有：程序计数器 虚拟机栈 本地方法栈
     线程共享：MetaSpace 带有字符串常量池的Java堆
     程序计数器：
            当前线程所执行的字节码行号指示器 
            改变计数器的值选取下一条要执行的字节码指令（javap -verbose x.class）
            对Java方法计数若果时native方法则计数器值为Undefind
            不会发生内存泄漏问题
     Java虚拟机栈：
            Java方法执行的内存模型 
            包含多个栈帧（局部变量表 操作数栈 动态链接 返回地址...）
     本地方法栈：
            本地方法栈与Java虚拟机栈非常类似主要作用与标注了native方法 如forName0()  
     MetaSpace:
            元空间在jdk8之后开始把类的元数据放在本地堆内存中这一块区域叫做MetaSpace该区域在jdk7及以前时属于
            永久代的。元空间和永久代都是存储class相关信息的包括class对象的method filed  实际上元空间和永久
            代都是方法区的实现 只是实现有所不同 所以说方法区是JVM的一种规范 在jdk7原先位于方法区的常量池已被
            移动到Java堆中并且在jdk8后使用元空间替代了永久代 该替代并非名字上面替代 2者最大的区别是元空间使用
            的是本地内存 永久代使用的是jvm内存            
     Java堆:
            所有线程共享的堆对象实例分配的区域内存分配中最大的一块
            GC管理的主要区域 一般使用分代回收算法
                           
![JVM02](https://github.com/licslan/interview-ing/raw/master/JVM-GC/JVM02.jpg)<br>     
![JVM03](https://github.com/licslan/interview-ing/raw/master/JVM-GC/JVM03.jpg)<br>
![JVM04](https://github.com/licslan/interview-ing/raw/master/JVM-GC/JVM04.jpg)<br>
![JVM07](https://github.com/licslan/interview-ing/raw/master/JVM-GC/JVM07.jpg)<br>
![JVM08](https://github.com/licslan/interview-ing/raw/master/JVM-GC/JVM08.jpg)<br>
#### 15.谈谈Java内存模型局部变量表和操作数栈？
#### LICSLAN回答:   
     局部变量表：包含方法执行过程中的所有变量 8大基本数据类型 对象引用 returnaddress
     操作数栈：入栈 出栈 复制 交换 产生消费变量  有点类似原生CPU寄存器 javap x.class 里面iload..         
#### 16.递归为什么会引发java.lang.StackOverflowError？
#### LICSLAN回答:   
     递归过深栈帧超出虚拟机深度 虚拟机深度时固定的 解决该问题的话 限制递归次数 或使用循环代替递归
     虚拟机栈过多也会引发OOM异常  当虚拟机动态扩展时如果无法生气足够多的内存 就会OOM
     栈类似集合有固定容量里面包含多个栈帧，在编写代码时每调用一个方法，Java虚拟机在内存中就会自动分配
     一块对应的空间（栈帧）当方法执行完成后对应的栈帧会自动释放掉 所有栈不需要GC 
     JSTCK分析线程卡顿运行情况
![JVM05](https://github.com/licslan/interview-ing/raw/master/JVM-GC/JVM05.jpg)<br>
![JVM06](https://github.com/licslan/interview-ing/raw/master/JVM-GC/JVM06.jpg)<br> 
 
#### 17.MetaSpace元空间和永久代的区别？
#### LICSLAN回答: 
      元空间在jdk8之后开始把类的元数据放在本地堆内存中这一块区域叫做MetaSpace该区域在jdk7及以前时属于
      永久代的。元空间和永久代都是存储class相关信息的包括class对象的method filed  实际上元空间和永久
      代都是方法区的实现 只是实现有所不同 所以说方法区是JVM的一种规范 在jdk7原先位于方法区的常量池已被
      移动到Java堆中并且在jdk8后使用元空间替代了永久代 该替代并非名字上面替代 2者最大的区别是元空间使用
      的是本地内存 永久代使用的是jvm内存 那么使用本地内存有什么好处呢？
      java.lang.OutOfMemoryError:PermGen Space 不会出现了 因为默认类的元数据分配只受本地内存大小限制
      理论上本地内存剩余多少meta space就能有多大
      MetaSpace 相比 PermGend 优势
      字符串常量池存在永久代中 容易出现性能问题和内存溢出
      类的方法信息大小难以确定 给永久代的大小指定带来困难
      永久代会给GC带来不必要的复杂度 且回收效率偏低
      方便HotSpot与其他JVM 如Jrockit 的集成  
#### 18.JVM三大性能调优参数-Xms -Xmx -Xss含义是？
#### LICSLAN回答:    
     比如 java -Xms 128m -Xmx 128m -Xss 256k -jar licslan.jar
     -Xss：规定了每个线程虚拟机栈的大小 此配置将影响此进程中的并发线程数的大小
     -Xms：堆的初始值 该进程刚创建时 专属Java堆大小 一旦超过初始值 Java堆将会自动扩容    
     -Xmx：堆能达到的最大值
     通常情况下 将-Xms和-Xmx设置为一样大 当k不够用扩容会内存抖动影响程序稳定
#### 19.Java内存模型中堆和栈的区别  内存分配策略？
#### LICSLAN回答:    
     程序运行时 有3中内存分配策略 静态 栈式 堆式 
     静态存储：编译时确定每个数据目标在运行时的存储空间需求 不允许有嵌套递归的结构存在 
     栈式存储：数据区需求在编译时未知 运行时模块入口前确定
     堆时存储：编译时或运行时模块入口都都无法确定 动态分配 可变长度串 可变实例
     联系：引用对象，数组时 栈里面定义变量保存堆中目标的首地址
     创建好的对象实例和数组都会保存在堆中想要引用堆中的某个对象或者数组可以在栈中定义一个
     特殊的变量，将栈中的变量的取值等于数组或者对象在堆内存中的首地址，栈中的这个变量就成了
     数组或对象的引用变量，以后就可以使用在栈中的引用变量来访问堆中的数组或者对象了 引用变量就
     像是为数组或者对象的起的一个名称 引用变量是普通的变量定义时在栈中分配引用变量在程序运行到其
     作用域之外后就会被释放掉了而数组和对象本身在堆中分配即使程序运行到使用new 产生数组或者对象
     的语句所在的代码块之外，数组和对象本身占据的内存不会被释放他们在没有引用变量指向的时候才会变
     为垃圾 需要等待后面不确定时间被垃圾回收器GC释放掉
     所以区别主要在以下几个方面：
     管理方式：栈自动释放内存 堆需要GC回收释放
     空间大小：栈比堆小
     碎片想关：栈产生的碎片远远小于堆 垃圾回收堆不是实时所以容易不连续 产生碎片较多较大
     分配方式：栈支持静态和动态分配 而仅仅支持动态分配 
     效率：栈的效率比堆高 栈相对来说灵活度不够 就是入栈出栈 堆双向链表 动态分配复杂灵活效率低
     
![JVM09](https://github.com/licslan/interview-ing/raw/master/JVM-GC/JVM09.jpg)<br> 
#### 20.简单看一段代码 （下面的第一幅图）说说元空间，堆，线程独占部分是一些什么？
#### LICSLAN回答: 
     产生的结果看下面的第二幅图
     元空间：一些class基本信息 如方法 成员变量
     堆内存：当HelloWorld被创建时 就会存在2个对象实例 Object：HelloWorld & Object：String("test")
     线程独占：当程序执行时 main 线程会分配对于的虚拟机栈 本地栈 程序计数器 栈里面会存有
              Parameter reference :"test" to String Object
              Variable reference: "hw" to HelloWorld Object
              Local Variables: a with 1,lineNo 
![JVM10](https://github.com/licslan/interview-ing/raw/master/JVM-GC/JVM10.jpg)<br>
![JVM11](https://github.com/licslan/interview-ing/raw/master/JVM-GC/JVM11.jpg)<br>
#### 21.不同JDK版本之间的intern()方法的区别JDK6vsJDK6+？
#### LICSLAN回答:    
     他们作用上的区别仅在JDK6中通过intern()方法仅仅会在字符串常量池里添加字符串对象而不像6+以后
     版本那样非但能往池中添加字符串对象还能添加字符串在堆中的引用
     7+之后方法区常量池被移动到Java堆中主要原因是先前字符串常量池存在永久代中，永久代内存有限
     如果频繁调用intern()在池里创建字符串对象会使得字符串常量池被挤爆从而引发OOM
![JVM12](https://github.com/licslan/interview-ing/raw/master/JVM-GC/JVM12.jpg)<br>
![JVM15](https://github.com/licslan/interview-ing/raw/master/JVM-GC/JVM15.jpg)<br>
![JVM16](https://github.com/licslan/interview-ing/raw/master/JVM-GC/JVM16.jpg)<br>
#### 22.不同JDK版本之间常量池变化？
#### LICSLAN回答:
     在近三个JDK版本（1.6、1.7、1.8）中， 运行时常量池（Runtime Constant Pool）的所处区域一直在
     不断的变化，在JDK1.6时它是方法区的一部分；1.7又把他放到了堆内存中；1.8之后出现了元空间，它又回
     到了方法区。其实，这也说明了官方对“永久代”的优化从1.7就已经开始了。可以参考下图
![JVM13](https://github.com/licslan/interview-ing/raw/master/JVM-GC/JVM12.jpg)<br>
![JVM14](https://github.com/licslan/interview-ing/raw/master/JVM-GC/JVM12.jpg)<br>      