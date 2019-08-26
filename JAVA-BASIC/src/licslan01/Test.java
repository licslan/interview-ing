package licslan01;
/**
 * @author LICSLAN 说一下构造函数 静态代码块 构造代码块执行顺序
 * */
public class Test {
    //静态代码块
    static {System.out.println("i am static code block");}
    /* 构造代码块*/
    {System.out.println("i am Construct code block");}
    /** 构造函数*/
    public Test(){System.out.println("我是无参构造函数");}
    /** 有参构造函数*/
    public Test(int i){System.out.println("我是无参构造函数 "+i);}
    //静态代码块
    static {System.out.println("i am static code block again");}
    //构造代码块2
    {System.out.println("i am Construct code block again");}
    public static void main(String[] args) {
            Integer i1 = 100;
            Integer i2 = 100;
            Integer i3 = 128;
            Integer i4 = 128;
            System.out.println(i1 == i2);
            System.out.println(i3 == i4);
//        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~创建一个对象");
//        Test test1 = new Test();
//
//        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~创建第二对象");
//        Test test2 = new Test();
//
//        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~创建第三对象");
//        Test test3 = new Test(1);

        /*
         * i am static code block
         * i am static code block again
         * ~~~~~~~~~~~~~~~~~~~~~~~~~~创建一个对象
         * i am Construct code block
         * i am Construct code block again
         * 我是无参构造函数
         * ~~~~~~~~~~~~~~~~~~~~~~~~~~创建第二对象
         * i am Construct code block
         * i am Construct code block again
         * 我是无参构造函数
         * ~~~~~~~~~~~~~~~~~~~~~~~~~~创建第三对象
         * i am Construct code block
         * i am Construct code block again
         * 我是无参构造函数 1
         * */

        /*
         * 编译后javac -encoding UTF-8 Test.java   java licslan01.Test 执行字节码  JVM 会将Test.class  byte字节load到JVM中解析解释执行为改平台能识别的机器码
         * C:\Users\licsl\Desktop\JVM-GC\interview-ing\JAVA-BASIC\src>java licslan01.Test
         * i am static code block
         * i am static code block again
         * ~~~~~~~~~~~~~~~~~~~~~~~~~~创建一个对象
         * i am Construct code block
         * i am Construct code block again
         * 我是无参构造函数
         * ~~~~~~~~~~~~~~~~~~~~~~~~~~创建第二对象
         * i am Construct code block
         * i am Construct code block again
         * 我是无参构造函数
         * ~~~~~~~~~~~~~~~~~~~~~~~~~~创建第三对象
         * i am Construct code block
         * i am Construct code block again
         * 我是无参构造函数 1
         * */

        //执行顺序没有调用无参构造函数：静态代码块>构造代码块>构造函数（无参默认可以不用显示写出来）
        //执行顺序如果调用有参构造函数：静态代码块>构造代码块>构造函数（有参当调用有参无参不执行了）

        //下面看一下线程执行时  JDK8 JVM run data area 发生一些什么操作吧...

        /*
         * javap -verbose Test.class
         * Classfile /C:/Users/licsl/Desktop/JVM-GC/interview-ing/JAVA-BASIC/src/licslan01/Test.class
         *   Last modified 2019-8-22; size 1133 bytes
         *   MD5 checksum ce482ab94045e2cb7b75be18d40de206
         *   Compiled from "Test.java"
         * public class licslan01.Test
         *   minor version: 0
         *   major version: 52
         *   flags: ACC_PUBLIC, ACC_SUPER
         * Constant pool:
         *    #1 = Methodref          #19.#30        // java/lang/Object."<init>":()V
         *    #2 = Fieldref           #31.#32        // java/lang/System.out:Ljava/io/PrintStream;
         *    #3 = String             #33            // i am Construct code block
         *    #4 = Methodref          #34.#35        // java/io/PrintStream.println:(Ljava/lang/String;)V
         *    #5 = String             #36            // i am Construct code block again
         *    #6 = String             #37            // 我是无参构造函数
         *    #7 = Class              #38            // java/lang/StringBuilder
         *    #8 = Methodref          #7.#30         // java/lang/StringBuilder."<init>":()V
         *    #9 = String             #39            // 我是无参构造函数
         *   #10 = Methodref          #7.#40         // java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
         *   #11 = Methodref          #7.#41         // java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
         *   #12 = Methodref          #7.#42         // java/lang/StringBuilder.toString:()Ljava/lang/String;
         *   #13 = String             #43            // ~~~~~~~~~~~~~~~~~~~~~~~~~~创建一个对象
         *   #14 = Class              #44            // licslan01/Test
         *   #15 = Methodref          #14.#30        // licslan01/Test."<init>":()V
         *   #16 = String             #45            // ~~~~~~~~~~~~~~~~~~~~~~~~~~创建第二对象
         *   #17 = String             #46            // i am static code block
         *   #18 = String             #47            // i am static code block again
         *   #19 = Class              #48            // java/lang/Object
         *   #20 = Utf8               <init>
         *   #21 = Utf8               ()V
         *   #22 = Utf8               Code
         *   #23 = Utf8               LineNumberTable
         *   #24 = Utf8               (I)V
         *   #25 = Utf8               main
         *   #26 = Utf8               ([Ljava/lang/String;)V
         *   #27 = Utf8               <clinit>
         *   #28 = Utf8               SourceFile
         *   #29 = Utf8               Test.java
         *   #30 = NameAndType        #20:#21        // "<init>":()V
         *   #31 = Class              #49            // java/lang/System
         *   #32 = NameAndType        #50:#51        // out:Ljava/io/PrintStream;
         *   #33 = Utf8               i am Construct code block
         *   #34 = Class              #52            // java/io/PrintStream
         *   #35 = NameAndType        #53:#54        // println:(Ljava/lang/String;)V
         *   #36 = Utf8               i am Construct code block again
         *   #37 = Utf8               我是无参构造函数
         *   #38 = Utf8               java/lang/StringBuilder
         *   #39 = Utf8               我是无参构造函数
         *   #40 = NameAndType        #55:#56        // append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
         *   #41 = NameAndType        #55:#57        // append:(I)Ljava/lang/StringBuilder;
         *   #42 = NameAndType        #58:#59        // toString:()Ljava/lang/String;
         *   #43 = Utf8               ~~~~~~~~~~~~~~~~~~~~~~~~~~创建一个对象
         *   #44 = Utf8               licslan01/Test
         *   #45 = Utf8               ~~~~~~~~~~~~~~~~~~~~~~~~~~创建第二对象
         *   #46 = Utf8               i am static code block
         *   #47 = Utf8               i am static code block again
         *   #48 = Utf8               java/lang/Object
         *   #49 = Utf8               java/lang/System
         *   #50 = Utf8               out
         *   #51 = Utf8               Ljava/io/PrintStream;
         *   #52 = Utf8               java/io/PrintStream
         *   #53 = Utf8               println
         *   #54 = Utf8               (Ljava/lang/String;)V
         *   #55 = Utf8               append
         *   #56 = Utf8               (Ljava/lang/String;)Ljava/lang/StringBuilder;
         *   #57 = Utf8               (I)Ljava/lang/StringBuilder;
         *   #58 = Utf8               toString
         *   #59 = Utf8               ()Ljava/lang/String;
         * {
         *   public licslan01.Test();
         *     descriptor: ()V
         *     flags: ACC_PUBLIC
         *     Code:
         *       stack=2, locals=1, args_size=1
         *          0: aload_0
         *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         *          4: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         *          7: ldc           #3                  // String i am Construct code block
         *          9: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         *         12: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         *         15: ldc           #5                  // String i am Construct code block again
         *         17: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         *         20: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         *         23: ldc           #6                  // String 我是无参构造函数
         *         25: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         *         28: return
         *       LineNumberTable:
         *         line 11: 0
         *         line 9: 4
         *         line 17: 12
         *         line 11: 20
         *
         *   public licslan01.Test(int);
         *     descriptor: (I)V
         *     flags: ACC_PUBLIC
         *     Code:
         *       stack=3, locals=2, args_size=2
         *          0: aload_0
         *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         *          4: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         *          7: ldc           #3                  // String i am Construct code block
         *          9: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         *         12: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         *         15: ldc           #5                  // String i am Construct code block again
         *         17: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         *         20: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         *         23: new           #7                  // class java/lang/StringBuilder
         *         26: dup
         *         27: invokespecial #8                  // Method java/lang/StringBuilder."<init>":()V
         *         30: ldc           #9                  // String 我是无参构造函数
         *         32: invokevirtual #10                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
         *         35: iload_1
         *         36: invokevirtual #11                 // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
         *         39: invokevirtual #12                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
         *         42: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         *         45: return
         *       LineNumberTable:
         *         line 13: 0
         *         line 9: 4
         *         line 17: 12
         *         line 13: 20
         *
         *   public static void main(java.lang.String[]);
         *     descriptor: ([Ljava/lang/String;)V
         *     flags: ACC_PUBLIC, ACC_STATIC
         *     Code:
         *       stack=2, locals=3, args_size=1
         *          0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         *          3: ldc           #13                 // String ~~~~~~~~~~~~~~~~~~~~~~~~~~创建一个对象
         *          5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         *          8: new           #14                 // class licslan01/Test
         *         11: dup
         *         12: invokespecial #15                 // Method "<init>":()V
         *         15: astore_1
         *         16: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         *         19: ldc           #16                 // String ~~~~~~~~~~~~~~~~~~~~~~~~~~创建第二对象
         *         21: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         *         24: new           #14                 // class licslan01/Test
         *         27: dup
         *         28: invokespecial #15                 // Method "<init>":()V
         *         31: astore_2
         *         32: return
         *       LineNumberTable:
         *         line 19: 0
         *         line 20: 8
         *         line 21: 16
         *         line 22: 24
         *         line 36: 32
         *
         *   static {};
         *     descriptor: ()V
         *     flags: ACC_STATIC
         *     Code:
         *       stack=2, locals=0, args_size=0
         *          0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         *          3: ldc           #17                 // String i am static code block
         *          5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         *          8: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         *         11: ldc           #18                 // String i am static code block again
         *         13: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         *         16: return
         *       LineNumberTable:
         *         line 7: 0
         *         line 15: 8
         * }
         * SourceFile: "Test.java"
         * */
    }



}
