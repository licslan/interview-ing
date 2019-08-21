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
            TODO    
     MetaSpace:
            TODO             
     Java堆:
            TODO               
![JVM02](https://github.com/licslan/interview-ing/raw/master/JVM-GC/JVM02.jpg)     
![JVM03](https://github.com/licslan/interview-ing/raw/master/JVM-GC/JVM03.jpg)
![JVM04](https://github.com/licslan/interview-ing/raw/master/JVM-GC/JVM04.jpg)  
#### 15.谈谈Java内存模型局部变量表和操作数栈？
#### LICSLAN回答:   
     局部变量表：包含方法执行过程中的所有变量
     操作数栈：入栈 出栈 复制 交换 产生消费变量  有点类似原生CPU寄存器 javap x.class 里面iload..         
 #### 16.递归为什么会引发java.lang.StackOverflowError？
 #### LICSLAN回答:   
      递归过深栈帧超出虚拟机深度 虚拟机深度时固定的 解决该问题的话 限制递归次数 或使用循环代替递归
      虚拟机栈过多也会引发OOM异常  当虚拟机动态扩展时如果无法生气足够多的内存 就会OOM
      栈类似集合有固定容量里面包含多个栈帧，在编写代码时每调用一个方法，Java虚拟机在内存中就会自动分配
      一块对应的空间（栈帧）当方法执行完成后对应的栈帧会自动释放掉 所有栈不需要GC 
      JSTCK分析线程卡顿运行情况