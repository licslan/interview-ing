package licslan01.algorithm;

import java.util.Random;

/**
 * @author LICSLAN
 * 冒泡排序算法
 * （1）比较前后相邻的二个数据，如果前面数据大于后面的数据，就将这二个数据交换。
 * （2）这样对数组的第 0 个数据到 N-1 个数据进行一次遍历后，最大的一个数据就“沉”到数组第N-1 个位置。
 * （3）N=N-1，如果 N 不为 0 就重复前面二步，否则排序完成。
 */
public class BubbleSort {
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
        //冒泡排序来来排个序
        int[] intResult = bubbleSort(ints);
        /*for(元素类型 元素变量x：遍历对象(数组或集合)){
            引用元素变量x的语句；
        }*/
        System.out.println("排序后数量是： "+intResult.length);
        for (int x:intResult){
            System.out.println(x);
        }
    }
     private static int[] bubbleSort(int[] ints){
         //逻辑
        int [] x;
        //第一层循环  循环遍历每个数据
        for(int i=0;i<ints.length;i++){
            //第二层循环遍历每个数据这里ints.length-i因为排序一次就往👉前进一次
            //也就是前面排了就不管了就往后排后面排序就少一次第一层控制了已经排了几次
            //那么剩下还要排序的次数就是ints.length-i  u got it?
            for(int j=1;j<ints.length-i;j++){
                //比较相邻的数据的大小 并交换位置 前面的数和后面的数比较
                if(ints[j-1]>ints[j]){
                    int temp;
                    //我们是从小到大排序的
                    //1.将ints[j-1]赋值给临时变量位置上   ints[j-1]位置现在空了
                    temp=ints[j-1];
                    //2.将ints[i]赋值给ints[j-1]   ints[j]位置现在空了
                    ints[j-1]=ints[j];
                    //3.临时变量的值赋值给ints[j]
                    ints[j]=temp;
                }
            }
        }
        //排完序后
         x=ints;
        return x;
    }
}
