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
![JVM01](https://github.com/licslan/interview-ing/raw/master/JVM-GC/JVM01.jpg)     
