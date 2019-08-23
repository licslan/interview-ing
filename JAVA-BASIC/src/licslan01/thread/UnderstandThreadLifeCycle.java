package licslan01.thread;
/**
 * @author LICSLAN 理解Java线程的生命周期
 * 
 * 线程生命周期(状态)
 * 当线程被创建并启动以后，它既不是一启动就进入了执行状态，也不是一直处于执行状态。
 * 在线程的生命周期中，它要经过新建(New)、就绪（Runnable）、运行（Running）、阻塞
 * (Blocked)和死亡(Dead)5 种状态。尤其是当线程启动以后，它不可能一直"霸占"着 CPU 独自
 * 运行，所以 CPU 需要在多条线程之间切换，于是线程状态也会多次在运行、阻塞之间切换
 * 1. 新建状态（NEW）
 * 当程序使用 new 关键字创建了一个线程之后，该线程就处于新建状态，此时仅由 JVM 为其分配
 * 内存，并初始化其成员变量的值
 * 2. 就绪状态（RUNNABLE）：
 * 当线程对象调用了 start()方法之后，该线程处于就绪状态。Java 虚拟机会为其创建方法调用栈和
 * 程序计数器，等待调度运行。
 * 3. 运行状态（RUNNING）：
 * 如果处于就绪状态的线程获得了 CPU，开始执行 run()方法的线程执行体，则该线程处于运行状
 * 态。
 * 4. 阻塞状态（BLOCKED）：
 * 阻塞状态是指线程因为某种原因放弃了 cpu 使用权，也即让出了 cpu timeslice，暂时停止运行。
 * 直到线程进入可运行(runnable)状态，才有机会再次获得 cpu timeslice 转到运行(running)状
 * 态。阻塞的情况分三种：
 * 等待阻塞（o.wait->等待对列）：
 * 运行(running)的线程执行 o.wait()方法，JVM 会把该线程放入等待队列(waitting queue)
 * 中。
 * 同步阻塞(lock->锁池)
 * 运行(running)的线程在获取对象的同步锁时，若该同步锁被别的线程占用，则 JVM 会把该线
 * 程放入锁池(lock pool)中。
 * 其他阻塞(sleep/join)
 * 运行(running)的线程执行 Thread.sleep(long ms)或 t.join()方法，或者发出了 I/O 请求时，
 * JVM 会把该线程置为阻塞状态。当 sleep()状态超时、join()等待线程终止或者超时、或者 I/O
 * 处理完毕时，线程重新转入可运行(runnable)状态。
 * 5. 线程死亡（DEAD）
 *
 *
 * 线程会以下面三种方式结束，结束后就是死亡状态。
 * 正常结束
 * 1. run()或 call()方法执行完成，线程正常结束。
 * 异常结束
 * 2. 线程抛出一个未捕获的 Exception 或 Error。
 * 调用 stop
 * 3. 直接调用该线程的 stop()方法来结束该线程—该方法通常容易导致死锁，不推荐使用
 * */
public class UnderstandThreadLifeCycle  extends Thread {

    private void stopCurrentThread(){
        this.interrupt();
        //调用系统中断，发起异常
    }
    @Override
    public void run() {
        //非阻塞过程中通过判断中断标志来退出
        while (!isInterrupted()){
            try{
                //阻塞过程捕获中断异常来退出
                Thread.sleep(1000);
                System.out.println("我现在正常运行中~~~~~~~~");
            }catch(InterruptedException e){
                e.printStackTrace();
                //捕获到异常之后，执行 break 跳出循环
                break;
            }
        }
        System.out.println("isInterrupted is "+isInterrupted()+"所有我要停止运行了~~~~~~~");
    }

    public static void main(String[] args) {
        //示例1
        UnderstandThreadLifeCycle understandThreadLifeCycle = new UnderstandThreadLifeCycle();
        understandThreadLifeCycle.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        understandThreadLifeCycle.stopCurrentThread();

        //示例2
//        UnderstandThreadLifeCycle.FlagThread u = new UnderstandThreadLifeCycle().new FlagThread();
//        u.start();
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        //中断
//        u.stopCurrentThread();
    }



    /**
     * 定义了一个退出标志 exit，当 exit 为 true 时，while 循环退出，exit 的默认值为 false.在定义 exit
     * 时，使用了一个 Java 关键字 volatile，这个关键字的目的是使 exit 同步，也就是说在同一时刻只
     * 能由一个线程来修改 exit 的值。
     * */
    class ThreadSafe extends Thread {
        volatile boolean exit = false;
        @Override
        public void run() {
            while (!exit){
                //do something
            }
        }
    }



    public class FlagThread extends Thread{
        //设置中断标志
        private boolean isInterrupted = false;

        //中断线程方法

        private void stopCurrentThread(){
            this.isInterrupted = true;
            //中断标志
            super.interrupt();
            //调用系统中断，发起异常
        }

        @Override
        public void run(){
            while(!isInterrupted){
                try{
                    for(int i = 0; i < 100; i++){
                        System.out.println(this.getName()+":"+i);
                        Thread.sleep(1000);
                    }
                }catch(InterruptedException e){
                    //中断抛出异常，通过标记方式结束线程
                }
            }
            System.out.println("异常结束！");
        }
    }



















    /**
     * 终止线程 4 种方式
     * 1. 正常运行结束
     *  程序运行结束，线程自动结束。
     * 2. 使用退出标志退出线程
     * 一般 run()方法执行完，线程就会正常结束，然而，常常有些线程是伺服线程。它们需要长时间的
     * 运行，只有在外部某些条件满足的情况下，才能关闭这些线程。使用一个变量来控制循环，例如：
     * 最直接的方法就是设一个 boolean 类型的标志，并通过设置这个标志为 true 或 false 来控制 while
     * 循环是否退出，代码示例：
     * public class ThreadSafe extends Thread {
     *  public volatile boolean exit = false; 
     *  public void run() {
     *  while (!exit){
     *  //do something
     *  }
     *  }
     * }
     * 定义了一个退出标志 exit，当 exit 为 true 时，while 循环退出，exit 的默认值为 false.在定义 exit
     * 时，使用了一个 Java 关键字 volatile，这个关键字的目的是使 exit 同步，也就是说在同一时刻只
     * 能由一个线程来修改 exit 的值。
     * 3. Interrupt 方法结束线程
     * 使用 interrupt()方法来中断线程有两种情况：
     * 1. 线程处于阻塞状态：如使用了 sleep,同步锁的 wait,socket 中的 receiver,accept 等方法时，
     * 会使线程处于阻塞状态。当调用线程的 interrupt()方法时，会抛出 InterruptException 异常。
     * 阻塞中的那个方法抛出这个异常，通过代码捕获该异常，然后 break 跳出循环状态，从而让
     * 我们有机会结束这个线程的执行。通常很多人认为只要调用 interrupt 方法线程就会结束，实
     * 际上是错的， 一定要先捕获 InterruptedException 异常之后通过 break 来跳出循环，才能正
     * 常结束 run 方法。
     * 2. 线程未处于阻塞状态：使用 isInterrupted()判断线程的中断标志来退出循环。当使用
     * interrupt()方法时，中断标志就会置 true，和使用自定义的标志来控制循环是一样的道理。
     *  public class ThreadSafe extends Thread {
     *  public void run() {
     *  while (!isInterrupted()){ //非阻塞过程中通过判断中断标志来退出
     *  try{
     *  Thread.sleep(5*1000);//阻塞过程捕获中断异常来退出
     *  }catch(InterruptedException e){
     *  e.printStackTrace();
     *  break;//捕获到异常之后，执行 break 跳出循环
     *  }
     *  }
     *  }
     * }
     * 4. stop 方法终止线程（线程不安全）
     * 程序中可以直接使用 thread.stop()来强行终止线程，但是 stop 方法是很危险的，就象突然关
     * 闭计算机电源，而不是按正常程序关机一样，可能会产生不可预料的结果，不安全主要是：
     * thread.stop()调用之后，创建子线程的线程就会抛出 ThreadDeatherror 的错误，并且会释放子
     * 线程所持有的所有锁。一般任何进行加锁的代码块，都是为了保护数据的一致性，如果在调用
     * thread.stop()后导致了该线程所持有的所有锁的突然释放(不可控制)，那么被保护数据就有可能呈
     * 现不一致性，其他线程在使用这些被破坏的数据时，有可能导致一些很奇怪的应用程序错误。因
     * 此，并不推荐使用 stop 方法来终止线程。
     *
     *
     * sleep 与 wait 区别
     * 1. 对于 sleep()方法，我们首先要知道该方法是属于 Thread 类中的。而 wait()方法，则是属于
     * Object 类中的。
     * 2. sleep()方法导致了程序暂停执行指定的时间，让出 cpu 该其他线程，但是他的监控状态依然
     * 保持者，当指定的时间到了又会自动恢复运行状态。
     * 3. 在调用 sleep()方法的过程中，线程不会释放对象锁。
     * 4. 而当调用 wait()方法的时候，线程会放弃对象锁，进入等待此对象的等待锁定池，只有针对此
     * 对象调用 notify()方法后本线程才进入对象锁定池准备获取对象锁进入运行状态。
     *
     *
     * start 与 run 区别
     * 1. start（）方法来启动线程，真正实现了多线程运行。这时无需等待 run 方法体代码执行完毕，
     * 可以直接继续执行下面的代码。
     * 2. 通过调用 Thread 类的 start()方法来启动一个线程， 这时此线程是处于就绪状态， 并没有运
     * 行。
     * 3. 方法 run()称为线程体，它包含了要执行的这个线程的内容，线程就进入了运行状态，开始运
     * 行 run 函数当中的代码。 Run 方法运行结束， 此线程终止。然后 CPU 再调度其它线程。
     *
     *
     * JAVA 后台线程
     * 1. 定义：守护线程--也称“服务线程”，他是后台线程，它有一个特性，即为用户线程 提供 公
     * 共服务，在没有用户线程可服务时会自动离开。
     * 2. 优先级：守护线程的优先级比较低，用于为系统中的其它对象和线程提供服务。
     * 3. 设置：通过 setDaemon(true)来设置线程为“守护线程”；将一个用户线程设置为守护线程
     * 的方式是在 线程对象创建 之前 用线程对象的 setDaemon 方法。
     * 4. 在 Daemon 线程中产生的新线程也是 Daemon 的。
     * 5. 线程则是 JVM 级别的，以 Tomcat 为例，如果你在 Web 应用中启动一个线程，这个线程的
     * 生命周期并不会和 Web 应用程序保持同步。也就是说，即使你停止了 Web 应用，这个线程
     * 依旧是活跃的。
     * 6. example: 垃圾回收线程就是一个经典的守护线程，当我们的程序中不再有任何运行的Thread,
     * 程序就不会再产生垃圾，垃圾回收器也就无事可做，所以当垃圾回收线程是 JVM 上仅剩的线
     * 程时，垃圾回收线程会自动离开。它始终在低级别的状态中运行，用于实时监控和管理系统
     * 中的可回收资源。
     * 7. 生命周期：守护进程（Daemon）是运行在后台的一种特殊进程。它独立于控制终端并且周
     * 期性地执行某种任务或等待处理某些发生的事件。也就是说守护线程不依赖于终端，但是依
     * 赖于系统，与系统“同生共死”。当 JVM 中所有的线程都是守护线程的时候，JVM 就可以退
     * 出了；如果还有一个或以上的非守护线程则 JVM 不会退出。
     * */

}
