package licslan01.algorithm;

import java.util.Random;

/**
 * @author LICSLAN
 * 希尔排序算法
 * 基本思想：先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，待整个序列
 * 中的记录“基本有序”时，再对全体记录进行依次直接插入排序。
 * 1. 操作方法：
 * 选择一个增量序列 t1，t2，…，tk，其中 ti>tj，tk=1；
 * 2. 按增量序列个数 k，对序列进行 k 趟排序；
 * 3. 每趟排序，根据对应的增量 ti，将待排序列分割成若干长度为 m 的子序列，分别对各子表进
 * 行直接插入排序。仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长
 * 度。<?>
 * */
public class ShellSort {
    public static void main(String[] args) {
//那我们随机生成一个数组吧
        int[] ints=new int[100];
        Random rand = new Random();
        for(int i=0;i<ints.length;i++){
            //随机生成0-200的数据100个
            ints[i]=rand.nextInt(200);
            System.out.println(ints[i]);
        }
        System.out.println("排序前数量是： "+ints.length);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        int[] insertSort = shellSort(ints);
        System.out.println("排序后数量是： "+insertSort.length);
        for (int x:insertSort){
            System.out.println(x);
        }
    }

    private static int[] shellSort(int[] a) {
        int dk = a.length/2;
        while( dk >= 1 ){
            shellinsertsort(a, dk);
            dk = dk/2;
        }
        return a;
    }

    private static void shellinsertsort(int[] a, int dk) {
//类似插入排序，只是插入排序增量是 1，这里增量是 dk,把 1 换成 dk 就可以了
        for(int i=dk;i<a.length;i++){
            if(a[i]<a[i-dk]){
                int j;
                int x=a[i];
                //x 为待插入元素
                a[i]=a[i-dk];
                for(j=i-dk; j>=0 && x<a[j];j=j-dk){
//通过循环，逐个后移一位找到要插入的位置。
                    a[j+dk]=a[j];
                }
                a[j+dk]=x;
                //插入
            }
        }
    }
}
