package main

import (
	"errors"
	"fmt"
	"strconv"
	"time"
)

//|------------------------------------------------------------------------------//
//|
//|
//|
//|              go  语言  学习  start  .....................  WeiLin Huang
//|
//|
//|
//|
//|------------------------------------------------------------------------------//
var g = 20 //全局变量
func main() {

	//定义变量  变量名在前  类型在后
	//print("hello go")
	fmt.Println("hello world!")
	var i string = "10"
	fmt.Println(i)

	//变量申明
	// 声明一个变量并初始化
	var a string = "RUNOOB"
	a4 := "x"
	fmt.Println(a, a4)

	// 没有初始化就为零值
	var b int
	fmt.Println(b)

	// bool 零值为 false
	var c bool
	fmt.Println(c)

	var x *int
	fmt.Println(x)

	var I []int
	var J map[string]int
	var K chan int
	var H func(string) int
	var G error // error 是接口
	fmt.Println(I)
	fmt.Println(J)
	fmt.Println(K)
	fmt.Println(H)
	fmt.Println(G)

	//go 常量
	const LENGTH string = "100"
	fmt.Println(LENGTH)

	//go 运算符
	var ii int = 12
	var jj int = 13
	fmt.Println(ii + jj) //+
	fmt.Println(ii - jj) //-
	fmt.Println(ii * jj) //*
	fmt.Println(ii / jj) ///

	//关系运算符

	/*运算符	描述	实例
	==	检查两个值是否相等，如果相等返回 True 否则返回 False。	(A == B) 为 False
	!=	检查两个值是否不相等，如果不相等返回 True 否则返回 False。	(A != B) 为 True
	>	检查左边值是否大于右边值，如果是返回 True 否则返回 False。	(A > B) 为 False
	<	检查左边值是否小于右边值，如果是返回 True 否则返回 False。	(A < B) 为 True
	>=	检查左边值是否大于等于右边值，如果是返回 True 否则返回 False。	(A >= B) 为 False
	<=	检查左边值是否小于等于右边值，如果是返回 True 否则返回 False。	(A <= B) 为 True*/

	//条件语句
	var name int = 100
	if name > 200 {
		fmt.Println("you over 200")
	} else {
		fmt.Println("you less 200")
	}

	// for 循环练习
	//for true {
	// go	fmt.Println("this is a dead loop...")
	//}

	var bb int = 15
	var aa int

	numbers := [6]int{1, 2, 3, 5}

	for aa := 0; aa < 10; aa++ {
		fmt.Printf("aa : %d\n", aa)
	}

	for aa < bb {
		aa++
		fmt.Printf("aa 的值为: %d\n", aa)
	}

	for i, x := range numbers {
		fmt.Printf("第 %d 位 x 的值 = %d\n", i, x)
	}

	//函数定义
	/**
	你可以通过函数来划分不同功能，逻辑上每个函数执行的是指定的任务。
	函数声明告诉了编译器函数的名称，返回类型，和参数。
	Go 语言标准库提供了多种可动用的内置的函数。例如，len() 函数可以接受
	不同类型参数并返回该类型的长度。如果我们传入的是字符串则返回字符串的长度，
	如果传入的是数组，则返回数组中包含的元素个数。
	*/
	fmt.Println(max(100, 200))

	fmt.Println(swap(10, 20))

	//Go 语言程序中全局变量与局部变量名称可以相同，但是函数内的局部变量会被优先考虑。实例如下：

	/* 声明局部变量 */
	var g = 10 //局部变量   全局变量与局部变量相同

	fmt.Printf("结果： g = %d\n", g)

	//初始化数组
	var blanca = [3]float32{20.01, 29.00, 23.11}
	var ix int
	for ix = 0; ix < 3; ix++ {
		//fmt.Println(blanca[ix])
		fmt.Printf("Element[%d] = %d\n", ix, blanca[ix])
	}

	/**
	Go 语言指针
	Go 语言中指针是很容易学习的，Go 语言中使用指针可以更简单的执行一些任务。
	接下来让我们来一步步学习 Go 语言指针。
	我们都知道，变量是一种使用方便的占位符，用于引用计算机内存地址。
	Go 语言的取地址符是 &，放到一个变量前使用就会返回相应变量的内存地址。
	以下实例演示了变量在内存中地址：
	*/
	var licslan = 200
	fmt.Println("变量地址: \n", &licslan)

	var aaa = 20 /* 声明实际变量 */
	var ip *int  /* 声明指针变量 */

	ip = &aaa /* 指针变量的存储地址 */

	fmt.Printf("aaa 变量的地址是: %x\n", &aaa)

	/* 指针变量的存储地址 */
	fmt.Printf("ip 变量储存的指针地址: %x\n", ip)

	/* 使用指针访问值 */
	fmt.Printf("*ip 变量的值: %d\n", *ip)

	//go语言结构体
	// 创建一个新的结构体
	fmt.Println(Books{"Go 语言", "www.runoob.com", "Go 语言教程", 6495407})

	// 也可以使用 key => value 格式
	fmt.Println(Books{title: "Go 语言", author: "www.runoob.com", subject: "Go 语言教程", id: 6495407})

	// 忽略的字段为 0 或 空
	fmt.Println(Books{title: "Go 语言", author: "www.runoob.com"})

	//访问结构体成员
	//如果要访问结构体成员，需要使用点号 . 操作符，格式为：
	//结构体.成员名"

	var Book1 Books /* 声明 Book1 为 Books 类型 */
	var Book2 Books /* 声明 Book2 为 Books 类型 */

	/* book 1 描述 */
	Book1.title = "Go 语言"
	Book1.author = "www.runoob.com"
	Book1.subject = "Go 语言教程"
	Book1.id = 6495407

	/* book 2 描述 */
	Book2.title = "Python 教程"
	Book2.author = "www.runoob.com"
	Book2.subject = "Python 语言教程"
	Book2.id = 6495700

	/* 打印 Book1 信息 */
	fmt.Printf("Book 1 title : %s\n", Book1.title)
	fmt.Printf("Book 1 author : %s\n", Book1.author)
	fmt.Printf("Book 1 subject : %s\n", Book1.subject)
	fmt.Printf("Book 1 id : %d\n", Book1.id)

	/* 打印 Book2 信息 */
	fmt.Printf("Book 2 title : %s\n", Book2.title)
	fmt.Printf("Book 2 author : %s\n", Book2.author)
	fmt.Printf("Book 2 subject : %s\n", Book2.subject)
	fmt.Printf("Book 2 id : %d\n", Book2.id)

	/**
	Go 语言切片(Slice)
	Go 语言切片是对数组的抽象。
	Go 数组的长度不可改变，在特定场景中这样的集合就不太适用，
	Go中提供了一种灵活，功能强悍的内置类型切片("动态数组"),
	与数组相比切片的长度是不固定的，可以追加元素，在追加时可能使切片的容量增大。
	*/
	s := make([]int, 3, 5)
	fmt.Printf("slice=%v\n", s)
	printSlice(s)

	/* 创建切片 */
	numbersss := []int{0, 1, 2, 3, 4, 5, 6, 7, 8}
	printSlice(numbersss)

	/* 打印原始切片 */
	fmt.Println("numbersss ==", numbersss)

	/* 打印子切片从索引1(包含) 到索引4(不包含)*/
	fmt.Println("numbersss[1:4] ==", numbersss[1:4])

	/* 默认下限为 0*/
	fmt.Println("numbers[:3] ==", numbers[:3])

	/* 默认上限为 len(s)*/
	fmt.Println("numbers[4:] ==", numbers[4:])

	numbers1 := make([]int, 0, 5)
	printSlice(numbers1)

	/* 打印子切片从索引  0(包含) 到索引 2(不包含) */
	number2 := numbers[:2]
	printSlice(number2)

	/* 打印子切片从索引 2(包含) 到索引 5(不包含) */
	number3 := numbers[2:5]
	printSlice(number3)

	/**
	append() 和 copy() 函数
	如果想增加切片的容量，我们必须创建一个新的更大的切片并把原分片的内容都拷贝过来。
	下面的代码描述了从拷贝切片的 copy 方法和向切片追加新元素的 append 方法。
	*/
	var numberss []int
	printSlice(numberss)

	/* 允许追加空切片 */
	numberss = append(numberss, 0)
	printSlice(numberss)

	/* 向切片添加一个元素 */
	numberss = append(numberss, 1)
	printSlice(numberss)

	/* 同时添加多个元素 */
	numberss = append(numberss, 2, 3, 4)
	printSlice(numberss)

	/* 创建切片 numbers1 是之前切片的两倍容量*/
	numbers11 := make([]int, len(numbers), (cap(numbers))*2)

	/* 拷贝 numbers 的内容到 numbers1 */
	copy(numbers11, numbers1)
	printSlice(numbers11)

	/**
	Go 语言中 range 关键字用于 for 循环中迭代数组(array)、切片(slice)、
	通道(channel)或集合(map)的元素。在数组和切片中它返回元素的索引和索引对应的值，
	在集合中返回 key-value 对的 key 值。
	*/

	//这是我们使用range去求一个slice的和。使用数组跟这个很类似
	nums := []int{21, 3, 5}
	sum := 0
	for _, num := range nums {
		sum += num
	}
	fmt.Println("sum:", sum)

	//在数组上使用range将传入index和值两个变量。上面那个例子我们不需
	// 要使用该元素的序号，所以我们使用空白符"_"省略了。有时侯我们确实需要知道它的索引。

	for i, num := range nums {
		if num > 0 {
			fmt.Println("The index is ", i)
		}
	}

	//range也可以用在map的键值对上。
	kvs := map[string]string{"a": "apple", "b": "banana"}
	for k, v := range kvs {
		fmt.Printf("%s -> %s\n", k, v)
	}
	//range也可以用来枚举Unicode字符串。第一个参数是字符的索引，第二个是字符（Unicode的值）本身。
	for i, c := range "go" {
		fmt.Println(i, c)
	}

	/**
	Map 是一种无序的键值对的集合。Map 最重要的一点是通过 key 来快速检索数据，key 类似于索引，指向数据的值。
	Map 是一种集合，所以我们可以像迭代数组和切片那样迭代它。不过，Map 是无序的，我们无法决定它的返回顺序，
	这是因为 Map 是使用 hash 表来实现的。
	定义 Map
	可以使用内建函数 make 也可以使用 map 关键字来定义 Map:
	*/
	/* 声明变量，默认 map 是 nil */
	//var map_variable map[key_data_type]value_data_type

	/* 使用 make 函数 */
	//map_variable := make(map[key_data_type]value_data_type)

	var countryCapitalMap = make(map[string]string)
	countryCapitalMap = make(map[string]string)

	/* map插入key - value对,各个国家对应的首都 */
	countryCapitalMap["France"] = "巴黎"
	countryCapitalMap["Italy"] = "罗马"
	countryCapitalMap["Japan"] = "东京"
	countryCapitalMap["India "] = "新德里"

	/*使用键输出地图值 */
	for country := range countryCapitalMap {
		fmt.Println(country, "首都是", countryCapitalMap[country])
	}

	/*查看元素在集合中是否存在 */
	capital, ok := countryCapitalMap["American"] /*如果确定是真实的,则存在,否则不存在 */
	/*fmt.Println(capital) */
	/*fmt.Println(ok) */
	if ok {
		fmt.Println("American 的首都是", capital)
	} else {
		fmt.Println("American 的首都不存在")
	}

	//delete() 函数用于删除集合的元素, 参数为 map 和其对应的 key。实例如下：

	fmt.Println("原始地图")

	/* 打印地图 */
	for country := range countryCapitalMap {
		fmt.Println(country, "首都是", countryCapitalMap[country])
	}

	/*删除元素*/
	delete(countryCapitalMap, "France")
	fmt.Println("法国条目被删除")

	fmt.Println("删除元素后地图")

	/*打印地图*/
	for country := range countryCapitalMap {
		fmt.Println(country, "首都是", countryCapitalMap[country])
	}

	//递归函数
	var in int
	for in = 0; in < 10; in++ {
		fmt.Printf("%d\t", fibonacci(in))
	}

	//Go 语言类型转换
	//类型转换用于将一种数据类型的变量转换为另外一种类型的变量。Go 语言类型转换基本格式如下：
	var suming int = 17
	var count int = 5
	var mean float32

	mean = float32(suming) / float32(count)
	fmt.Printf("mean 的值为: %f\n", mean)

	//Go 语言接口
	//Go 语言提供了另外一种数据类型即接口，它把所有的具有共性的方法定义在一起，任何其他类型只要实现了这些方法就是实现了这个接口。
	var phone Phone

	phone = new(NokiaPhone)
	phone.call()

	phone = new(IPhone)
	phone.call()

	//Go 错误处理
	//Go 语言通过内置的错误接口提供了非常简单的错误处理机制。
	//error类型是一个接口类型，这是它的定义：
	//我们可以在编码中通过实现 error 接口类型来生成错误信息。
	//函数通常在最后的返回值中返回错误信息。使用errors.New 可返回一个错误信息：
	_, err := Sqrt(-1)

	if err != nil {
		fmt.Println(err)
	}

	// 正常情况
	if result, errorMsg := Divide(100, 10); errorMsg == "" {
		fmt.Println("100/10 = ", result)
	}
	// 当被除数为零的时候会返回错误信息
	if _, errorMsg := Divide(100, 0); errorMsg != "" {
		fmt.Println("errorMsg is: ", errorMsg)
	}

	/**
	Go 并发
	Go 语言支持并发，我们只需要通过 go 关键字来开启 goroutine 即可。
	goroutine 是轻量级线程，goroutine 的调度是由 Golang 运行时进行管理的。
	goroutine 语法格式：
	go 函数名( 参数列表 )
	例如：
	go f(x, y, z)
	开启一个新的 goroutine:
	f(x, y, z)
	*/

	go say("world")
	say("hello")

	/**
	通道（channel）
	通道（channel）是用来传递数据的一个数据结构。
	通道可用于两个 goroutine 之间通过传递一个指定类型的值来同步运行和通讯。操作符 <-
	用于指定通道的方向，发送或接收。如果未指定方向，则为双向通道。
	ch <- v    // 把 v 发送到通道 ch
	v := <-ch  // 从 ch 接收数据
			   // 并把值赋给 v
	声明一个通道很简单，我们使用chan关键字即可，通道在使用前必须先创建：
	ch := make(chan int)
	注意：默认情况下，通道是不带缓冲区的。发送端发送数据，同时必须又接收端相应的接收数据。
	以下实例通过两个 goroutine 来计算数字之和，在 goroutine 完成计算后，它会计算两个结果的和：
	*/

	ss := []int{7, 2, 8, -9, 4, 0}

	cx := make(chan int)
	go sumx(ss[:len(ss)/2], cx)
	go sumx(ss[len(ss)/2:], cx)
	xc, yc := <-cx, <-cx // 从通道 cx 中接收

	fmt.Println(xc, yc, xc+yc)

	/**
	通道缓冲区
	通道可以设置缓冲区，通过 make 的第二个参数指定缓冲区大小：
	ch := make(chan int, 100)
	带缓冲区的通道允许发送端的数据发送和接收端的数据获取处于异步状态，就是说发送端发送的数据可以放在缓冲区里面，
	可以等待接收端去获取数据，而不是立刻需要接收端去获取数据。
	不过由于缓冲区的大小是有限的，所以还是必须有接收端来接收数据的，否则缓冲区一满，数据发送端就无法再发送数据了。
	注意：如果通道不带缓冲，发送方会阻塞直到接收方从通道中接收了值。如果通道带缓冲，发送方则会阻塞直到发送的值被
	拷贝到缓冲区内；如果缓冲区已满，则意味着需要等待直到某个接收方获取到一个值。接收方在有值可以接收之前会一直阻塞。
	*/
	// 这里我们定义了一个可以存储整数类型的带缓冲通道
	// 缓冲区大小为2
	ch := make(chan int, 2)

	// 因为 ch 是带缓冲的通道，我们可以同时发送两个数据
	// 而不用立刻需要去同步读取数据
	ch <- 1
	ch <- 2

	// 获取这两个数据
	fmt.Println(<-ch)
	fmt.Println(<-ch)

	/**
	Go 遍历通道与关闭通道
	Go 通过 range 关键字来实现遍历读取到的数据，类似于与数组或切片。格式如下：
	v, ok := <-ch
	如果通道接收不到数据后 ok 就为 false，这时通道就可以使用 close() 函数来关闭。
	*/
	m := make(chan int, 10)
	go fibonaccix(cap(m), m)
	// range 函数遍历每个从通道接收到的数据，因为 c 在发送完 10 个
	// 数据之后就关闭了通道，所以这里我们 range 函数在接收到 10 个数据
	// 之后就结束了。如果上面的 c 通道不关闭，那么 range 函数就不
	// 会结束，从而在接收第 11 个数据的时候就阻塞了。
	for i := range m {
		fmt.Println(i)
	}

}

func fibonaccix(n int, c chan int) {
	x, y := 0, 1
	for i := 0; i < n; i++ {
		c <- x
		x, y = y, x+y
	}
	close(c)
}

func sumx(s []int, c chan int) {
	sum := 0
	for _, v := range s {
		sum += v
	}
	c <- sum // 把 sum 发送到通道 c
}

func say(s string) {
	for i := 0; i < 5; i++ {
		time.Sleep(100 * time.Millisecond)
		fmt.Println(s)
	}
}

// 定义一个 DivideError 结构
type DivideError struct {
	dividee int
	divider int
}

// 实现 `error` 接口
func (de *DivideError) Error() string {
	strFormat := `
    Cannot proceed, the divider is zero.
    dividee: %d
    divider: 0
`
	return fmt.Sprintf(strFormat, de.dividee)
}

// 定义 `int` 类型除法运算的函数
func Divide(varDividee int, varDivider int) (result int, errorMsg string) {
	if varDivider == 0 {
		dData := DivideError{
			dividee: varDividee,
			divider: varDivider,
		}
		errorMsg = dData.Error()
		return
	} else {
		return varDividee / varDivider, ""
	}

}

func Sqrt(f float64) (float64, error) {
	if f < 0 {
		return 0, errors.New("math: square root of negative number")
	}
	// 实现
	return 0, nil
}

type error interface {
	Error() string
}

type Phone interface {
	call()
}

type NokiaPhone struct {
}

func (nokiaPhone NokiaPhone) call() {
	fmt.Println("I am Nokia, I can call you!")
}

type IPhone struct {
}

func (iPhone IPhone) call() {
	fmt.Println("I am iPhone, I can call you!")
}

func printSlice(x []int) {
	fmt.Printf("len=%d cap=%d slice=%v\n", len(x), cap(x), x)
}

//斐波那契数列
func fibonacci(n int) int {
	if n < 2 {
		return n
	}
	return fibonacci(n-2) + fibonacci(n-1)
}

//函数
func max(a, b int) int {
	var result = 0
	if a > b {
		result = a
	} else {
		result = b
	}

	return result
}

//返回多个值
func swap(x int, y int) (int, string) {
	fmt.Println(x)
	fmt.Println(y)
	// int --->  string
	return x, strconv.Itoa(y)
}

/**
Go 语言中数组可以存储同一类型的数据，但在结构体中
我们可以为不同项定义不同的数据类型。
结构体是由一系列具有相同类型或不同类型的数据构成的数据集合。
结构体表示一项记录，比如保存图书馆的书籍记录，每本书有以下属性：
Title ：标题
Author ： 作者
Subject：学科
ID：书籍ID
*/

type Books struct {
	title   string
	author  string
	subject string
	id      int
}
