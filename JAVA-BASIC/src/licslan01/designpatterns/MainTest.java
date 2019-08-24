package licslan01.designpatterns;
/**
 * @author LICSLAN
 * 各种测试
 * */
public class MainTest {
    public static void main(String[] args) {
        //SingletonMode singletonMode = new SingletonMo()  private 不可实例化
        //只有通过SingletonMode获取
        SingletonMode instance = SingletonMode.getInstance();
        instance.message();
    }
}
