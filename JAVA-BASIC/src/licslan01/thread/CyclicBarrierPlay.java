package licslan01.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author LICSLAN  CyclicBarrier使用
 * */
public class CyclicBarrierPlay {

    /*
     * CyclicBarrier（回环栅栏-等待至 barrier 状态再全部同时执行）
     * 字面意思回环栅栏，通过它可以实现让一组线程等待至某个状态之后再全部同时执行。叫做回环
     * 是因为当所有等待线程都被释放以后，CyclicBarrier 可以被重用。我们暂且把这个状态就叫做
     * barrier，当调用 await()方法之后，线程就处于 barrier 了。
     * CyclicBarrier 中最重要的方法就是 await 方法，它有 2 个重载版本：
     * 1. public int await()：用来挂起当前线程，直至所有线程都到达 barrier 状态再同时执行后续任
     * 务；
     * 2. public int await(long timeout, TimeUnit unit)：让这些线程等待至一定的时间，如果还有
     * 线程没有到达 barrier 状态就直接让到达 barrier 的线程执行后续任务。
     * */

    public static void main(String[] args) {
        int n =5;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(n);
        for(int i=0;i<n;i++){
            //内部类实例化
            new CyclicBarrierPlay().new Lick(cyclicBarrier,i).start();
        }
    }

    class Lick extends Thread{
        private CyclicBarrier cyclicBarrier;
        private int i;
        Lick(CyclicBarrier cyclicBarrier,int i) {
            this.cyclicBarrier = cyclicBarrier;
            this.i = i;
        }
        @Override
        public void run() {
            try {


                //[A包裹的代码块业务逻辑
                System.out.println("这个时候i的值是： "+i+" 才去执行该线程的。线程名字是 "+Thread.currentThread().getName());
                //以睡眠来模拟线程需要预定写入数据操作
                Thread.sleep(5000);
                System.out.println("线程"+Thread.currentThread().getName()
                        +"写入数据完毕，等待其他线程写入完毕");
                        cyclicBarrier.await();    //A包裹的代码块业务逻辑]
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println("~~~~~~~~~~~~~~~~分割线~~~~~~~~~~~~~~~~");
            //[B包裹的代码块业务逻辑
            try {
                Thread.sleep(5000);
                System.out.println("所有线程写入完毕，继续处理其他任务，比如数据操作");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //B包裹的代码块业务逻辑]
        }
    }

    //通过实验 发现  循环5次到了 run()中使用了public int await()：用来挂起当前线程，
    //直至所有线程都到达 barrier 状态再同时执行后续任
    //[A包裹的代码块业务逻辑]会一起执行 等其执行完了再去执行[B包裹的代码块业务逻辑]

}
