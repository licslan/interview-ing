package licslan01.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author LICSLAN
 * CountDownLatch（线程计数器 ）
 * CountDownLatch 类位于 java.util.concurrent 包下，利用它可以实现类似计数器的功能。比如有
 * 一个任务 A，它要等待其他 4 个任务执行完毕之后才能执行，此时就可以利用 CountDownLatch
 * 来实现这种功能了。
 * */
public class CountDownLatchPlay {
    public static void main(String[] args) {
        System.out.println("当前线程名称是 "+Thread.currentThread().getName());


//        final CountDownLatch latch = new CountDownLatch(2);
//        new CountDownLatchPlay().new A(latch).start();
//        new CountDownLatchPlay().new B(latch).start();

        new CountDownLatchPlay().new C().start();
        new CountDownLatchPlay().new D().start();
        System.out.println("等待 2 个子线程执行完毕...");



//        try {
//            latch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        System.out.println("2 个子线程已经执行完毕");
        System.out.println("继续执行主线程");
        System.out.println("当前线程名称是 "+Thread.currentThread().getName());
    }

    class A extends Thread{
        private CountDownLatch latch;
        private A (CountDownLatch latch){
            this.latch=latch;
        }
        @Override
        public void run(){
                System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                latch.countDown();
        }
    }

    class B extends Thread{
        private CountDownLatch latch;
        private B(CountDownLatch latch){
            this.latch=latch;
        }
        @Override
        public void run(){
                System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                latch.countDown();
        }
    }


    /**写2个普通方法*/
    class C extends Thread{
        @Override
        public void run(){
            try {
                Thread.sleep(3000);
                System.out.println("hello C");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    class D extends Thread{
        @Override
        public void run(){
            try {
                Thread.sleep(3000);
                System.out.println("hello D");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }



    /**
     * 分析 但没有使用  CountDownLatch 结果如下
     * 当前线程名称是 main
     * 等待 2 个子线程执行完毕...
     * 2 个子线程已经执行完毕
     * 继续执行主线程
     * 当前线程名称是 main
     * hello D
     * hello C
     *
     *
     * 分析 但如果使用  CountDownLatch 结果如下
     * 当前线程名称是 main
     * 子线程Thread-0正在执行
     * 等待 2 个子线程执行完毕...
     * 子线程Thread-1正在执行
     * 子线程Thread-0执行完毕
     * 子线程Thread-1执行完毕
     * 2 个子线程已经执行完毕
     * 继续执行主线程
     * 当前线程名称是 main
     *
     * 使用的话  主线程必须要等到其他子线程执行完成才可以执行后面的操作
     * 测试放开相应的注释就行
     * */



}
