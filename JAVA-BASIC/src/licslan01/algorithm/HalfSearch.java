package licslan01.algorithm;

/**
 * @author LICSLAN
 * 二分查找
 * 又叫折半查找，要求待查找的序列有序。每次取中间位置的值与待查关键字比较，如果中间位置
 * 的值比待查关键字大，则在前半部分循环这个查找的过程，如果中间位置的值比待查关键字小，
 * 则在后半部分循环这个查找的过程。直到查找到了为止，否则序列中没有待查的关键字。
 * */
public class HalfSearch {
    public static void main(String[] args) {
        //TODO 打个比方  我们要从 0-500里面找到450这个数字

        //1.生成一个数组从0-500
        //2.找到数组的中间值
        //3.设置查找初始值 和最大值
        int [] inArr = new int[500];
        for(int i=0;i<500;i++){
            inArr[i]=i;
            System.out.println(inArr[i]);
        }

        //测试找一下450
        int result = getResult(inArr, 450);
        if(result>0){
            System.out.println("i got u");
        }else {
            System.out.println("i lost u");
        }
    }

    private static int getResult(int[] ints, int target){
        int start =0;
        int mid ;
        int high =ints.length-1;
        int count=0;
        while (start<=high){
            count += 1;
            //计算找到心动的那个人
            mid=(start+high)/2;
            //正好想到你了
            if(ints[mid]==target){
                System.out.println("i find u at "+ count +" time");
                return mid+1;
            }
            else if(ints[mid]<target){
                //向👉找 start初始值为 mid+1
                start=mid+1;
            }else {
                //向👈找 high最大值为 mid-1
                high=mid-1;
            }
        }
        //没有找到
        return -1;
    }
}
