package licslan01.datastructuresalgorithms.array;
/**
 * @author LICSLAN 学习封装数组 by play with datastructures from liuyubo
 * */
public class Array {

    /**数组大小*/
    private int size;
    /**申明一个数组*/
    private int[] data;

    /**数组构造方法*/
    private Array(int capacity){
        data=new int[capacity];
        size=0;
    }
    /**无参构造函数 默认数组容量capacity10*/
    public Array(){
        this(10);
    }
    /**获取数组size大小*/
    public int getSize(){
        return size;
    }
    /**获取数组的容量*/
    public int getCapacity(){
        return data.length;
    }
    /**判断数组是否为空*/
    public boolean isEmpty(){
        return size==0;
    }
    /**向数组最后一个位置添加新的元素*/
    public void addLast(int e){
        /*if(size==data.length){
            throw new IllegalArgumentException("Add last Filed array is full");
        }
        data[size]=e;
        size++;*/
        //可以改造如下
        add(size,e);
    }
    /**那么在数组第一个位置添加也知道怎么做了*/
    public void addFirst(int e){
        add(0,e);
    }
    /**在数组index位置插入新元素e*/
    public void add(int index,int e){
        //插入之前判断是否有空间可以插入新元素
        if(size==data.length){
            throw new IllegalArgumentException("Add Filed array is full");
        }
        //检查index是否合法
        if(index<0||index>size){
            throw new IllegalArgumentException("Add Filed the index should be index>=0 && index<=size");
        }
        //从后往前遍历数组 知道index时  所有元素都往后挪动
        for(int i=size-1;i>=index;i--){
            //每循环一次 当前下表比index小或者相等 都需要往后挪一位
            //刚到挪到index=i时 往后挪一位之后 新地方data[index]就空出来了
            data[i+1]=data[i];
        }
        //刚到挪到index=i时 往后挪一位之后 那么就把新值e赋值给data[index]
        data[index]=e;
        //size也要加一位
        size++;
    }


}
