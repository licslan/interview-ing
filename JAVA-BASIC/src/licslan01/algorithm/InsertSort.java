package licslan01.algorithm;

import java.util.Random;

/**
 * @author LICSLAN
 * 插入排序算法
 * 通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应的位置并插入。
 * 插入排序非常类似于整扑克牌。在开始摸牌时，左手是空的，牌面朝下放在桌上。接着，一次从
 * 桌上摸起一张牌，并将它插入到左手一把牌中的正确位置上。为了找到这张牌的正确位置，要将
 * 它与手中已有的牌从右到左地进行比较。无论什么时候，左手中的牌都是排好序的。
 * 如果输入数组已经是排好序的话，插入排序出现最佳情况，其运行时间是输入规模的一个线性函
 * 数。如果输入数组是逆序排列的，将出现最坏情况。平均情况与最坏情况一样，其时间代价是(n2)。
 * <?>
 * */
public class InsertSort {

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
        int[] insertSort = getInsertSort(ints);
        System.out.println("排序后数量是： "+insertSort.length);
        for (int x:insertSort){
            System.out.println(x);
        }
    }
    private static int[] getInsertSort(int[] ints){
        //实现逻辑
        //1.循环遍历数组
        //2.首先从这个数组挑一个数字 作为起始数  作为插入左手的一张牌  X
        //3.后面拿原数组里面数和左手上的那几张好牌比较
        //4.如果X们 比in 好 那么就是 in X们 多张牌排序 （我们从小到大排）
        //5.理清逻辑我们开始编码吧
//        for(int i=0;i<ints.length;i++){
//            //下面拿一张牌准备插入
//            int goodOne = ints[i];
//            //ok 被插入的位置(准备和前一个数比较)
//            int insertAfter = i-1;
//            //第二次遍历  比较后面插入的牌和手上当前牌的数据比较  从👉往👈比较 ！！！ 记住左手的牌是最好的最小的
//            while (insertAfter>0&&goodOne<ints[insertAfter]){
//                // 就把大的数据往👉移动  从👈往👉 从小到大排序的
//                ints[insertAfter+1]=ints[insertAfter];
//                //让insertAfter向前移动  减小  👈手上的牌比较过一次就减少一次比较
//                insertAfter--;
//            }
//            //插入的牌和👈手上的牌都比较万了 就知道要插入到什么地方了 把插入的数放入合适的位置
//            ints[insertAfter+1]=goodOne;
//        }
        for(int i =1; i<ints.length;i++)
        {
            //插入的数
            int insertVal = ints[i];
            //被插入的位置(准备和前一个数比较)
            int index = i-1;
            //如果插入的数比被插入的数小
            while(index>=0&&insertVal<ints[index])
            {
                //将把 arr[index] 向后移动
                ints[index+1]=ints[index];
                //让 index 向前移动
                index--;
            }
            //把插入的数放入合适位置
            ints[index+1]=insertVal;
        }
        return ints;
    }
}
