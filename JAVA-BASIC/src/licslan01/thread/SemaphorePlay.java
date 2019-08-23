package licslan01.thread;

import java.util.concurrent.Semaphore;
/**
 * @author LICSLAN  Semaphore使用
 * Semaphore（信号量-控制同时访问的线程个数）
 * Semaphore 翻译成字面意思为 信号量，Semaphore 可以控制同时访问的线程个数，通过
 * acquire() 获取一个许可，如果没有就等待，而 release() 释放一个许可。
 * Semaphore 类中比较重要的几个方法：
 * 1. public void acquire(): 用来获取一个许可，若无许可能够获得，则会一直等待，直到获得许
 * 可。
 * 2. public void acquire(int permits):获取 permits 个许可
 * 3. public void release() { } :释放许可。注意，在释放许可之前，必须先获获得许可。
 * 4. public void release(int permits) { }:释放 permits 个许可
 * 上面 4 个方法都会被阻塞，如果想立即得到执行结果，可以使用下面几个方法
 * 1. public boolean tryAcquire():尝试获取一个许可，若获取成功，则立即返回 true，若获取失
 * 败，则立即返回 false
 * 2. public boolean tryAcquire(long timeout, TimeUnit unit):尝试获取一个许可，若在指定的
 * 时间内获取成功，则立即返回 true，否则则立即返回 false
 * 3. public boolean tryAcquire(int permits):尝试获取 permits 个许可，若获取成功，则立即返
 * 回 true，若获取失败，则立即返回 false
 * 4. public boolean tryAcquire(int permits, long timeout, TimeUnit unit): 尝试获取 permits
 * 个许可，若在指定的时间内获取成功，则立即返回 true，否则则立即返回 false
 * 5. 还可以通过 availablePermits()方法得到可用的许可数目。
 * 例子：若一个工厂有 5 台机器，但是有 8 个工人，一台机器同时只能被一个工人使用，只有使用完
 * 了，其他工人才能继续使用。那么我们就可以通过 Semaphore 来实现：
 * */
public class SemaphorePlay {


    public static void main(String[] args) {
        //工人数量
        int n = 8;
        //机器数量
        int num =5;
        //也就是会有8个人去使用5台机器
        //一台机器同时只能被一个工人使用，只有使用完了，其他工人才能继续使用
        //资源一共就允许使用5个  资源有释放其他线程才可以使用
        Semaphore semaphore = new Semaphore(num);
        for(int i=0;i<n;i++){
            new SemaphorePlay().new Lise(semaphore,i).start();
        }
    }
    class Lise extends Thread{
        private Semaphore semaphore;
        private int num;
        private Lise(Semaphore semaphore,int num){
            this.semaphore=semaphore;
            this.num=num;
        }
        @Override
        public void run(){
            try {
                //[A代码块逻辑
                semaphore.acquire();
                System.out.println("工人"+this.num+"占用一个机器在生产...");
                Thread.sleep(2000);
                System.out.println("工人"+this.num+"释放出机器");
                semaphore.release();
                //A代码块逻辑]
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
