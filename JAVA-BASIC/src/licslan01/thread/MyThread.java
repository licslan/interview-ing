package licslan01.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author LICSLAN 创建线程三种方式
 * */
public class MyThread {

    /** first one  继承Thread类*/
    class ThreadTest0 extends Thread{
        @Override
        public void run() {
            System.out.println("hello extends Thread");
        }
    }

    /** first two  实现Runnable接口*/
    class ThreadTest1 implements Runnable{
        @Override
        public void run() {
            System.out.println("hello implements Runnable");
        }
    }

    /** first three  实现Callable接口 带有返回值*/
    class ThreadTest2 implements Callable<Integer>{
        @Override
        public Integer call() {
                int count =100;
                int i = 0;
                for(;i<count;i++)
                {
                    System.out.println(Thread.currentThread().getName()+" "+i);
                }
                return i;
        }
    }

    /** 多线程线程池玩法*/
    class MultiCallable implements Callable<String>{
        private int index;
        MultiCallable (int index){
            this.index=index;
        }
        @Override
        public String call() {
            return "hello "+index;
        }
    }






















    public static void main(String[] args) {
        // 第1种
        MyThread.ThreadTest0 threadTest = new MyThread().new ThreadTest0();
        threadTest.start();
        // 第2种
        MyThread.ThreadTest1 threadTest1 = new MyThread().new ThreadTest1();
        threadTest1.run();
        //or下面的方式  Runnable 放到 Thread构造方法里面
        Thread thread = new Thread(threadTest1);
        thread.start();


        System.out.println("~~~~~~~~~~~~~~~~~~~~分割线~~~~~~~~~~~~~~~~~~~~~~~~~~");


        //执行过程发现线程是无序的
        //第3种
        MyThread.ThreadTest2 threadTest2 = new MyThread().new ThreadTest2();
        FutureTask<Integer> ft = new FutureTask<>(threadTest2);
        int size = 100;
        for(int i = 0;i < size;i++)
        {
            System.out.println(Thread.currentThread().getName()+" 的循环变量i的值"+i);
            if(i==20)
            {
                new Thread(ft,"有返回值的线程").start();
            }
        }
        try
        {
            System.out.println("子线程的返回值："+ft.get());
            System.out.println("~~~~~~~~~~implements Callable<Integer> everything is done~~~~~~");
        } catch (InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }


        //多线程玩法  更详细请到package licslan01.threadpool
        MyThread.MultiCallable multiCallable;
        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<String>> futureList = new ArrayList<>();
        for(int i=0;i<10;i++){
            multiCallable = new MyThread().new MultiCallable(i);
            Future<String> submit = pool.submit(multiCallable);
            //获取Future对象并放入集合中
            futureList.add(submit);
        }
        //关闭线程池
        pool.shutdown();
        for (Future<String> aFutureList : futureList) {
            try {
                System.out.println("the result is " + aFutureList.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        }
    }




}