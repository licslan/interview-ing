package main

import (
	"fmt"
	"math"
)

/**
go 语言不断学习中
*/

/**go并发再学习 */
func hellox(som string) {
	for i := 1; i < 10; i++ {
		//需要加上这个时间  否则  go hellox 没机会执行了
		//Sleep(100 * Millisecond)
		fmt.Println("hello " + som)
	}
}

/**
通道（channel）
通道（channel）是用来传递数据的一个数据结构。
通道可用于两个 goroutine 之间通过传递一个指定类型的值来同步运行和通讯。
操作符 <- 用于指定通道的方向，发送或接收。如果未指定方向，则为双向通道。
*/
func vv(c int, d chan int) {
	fmt.Println(c)
	d <- c + 1 //将c+1写入 通道d
}

func main() {
	//go hellox("hwl")
	//hellox("x")
	//startvar()
	//申明一个channel
	s := make(chan int)
	//v:=10
	//ints<-v   //将10发送到通道ints
	//hwl:=<-ints //将通道的值10赋值给hwl
	go vv(10, s)
	go vv(5, s)
	x, y := <-s, <-s
	fmt.Println(x, y, x+y)

}

/**定义方法 初始化变量 */
func startvar() {
	var age int
	var name string
	//"%q"   空字符 “”
	fmt.Printf("%d %q\n", age, name)

	var i = 3
	var j = "hello"
	fmt.Println(i)
	fmt.Println(j)

	//var 定义变量可以在外面里面都行 ：=只能在函数里面
	a, b, f := 1, "s", true
	fmt.Printf("%d\n %s\n %d\n", a, b, f)

	//常量
	consts()
	//枚举
	enums()
}

func v() {
	//"内建变量使用"
	//bool
	//string
	//(u)int (u)int8  (u)int16 (u)int32 (u)int64   uintptr -->指针
	//byte rune   rune 就是go语言的char类型
	//float32 float64                 complex64 complex128 复数  实部 虚部
}

//go语言的常量定义
func consts() {
	//const hwl  ="licslan"
	//const a,b=3,4
	//上面也可以改为下面的方式  不要大写  大写有特殊含义注意
	const (
		hwl  = "licslan"
		a, b = 3, 4
	)
	var c int
	c = int(math.Sqrt(a*a + b*b))
	fmt.Println(hwl, c)
}

//go语言的枚举类型写法
func enums() {
	//const(
	//	cpp=1
	//	java=2
	//	c=3
	//	golang=5
	//)
	//上面的写法改为iota自增值
	const (
		cpp = iota
		_   //跳过不写
		java
		c
		golang
	)
	//定义 1024 自增  b kb mb gb tb pb
	const (
		b = 1 << (10 * iota)
		kb
		mb
		gb
		tb
		pb
	)
	fmt.Println(b, kb, mb, gb, tb, pb)
	fmt.Println(cpp, java, c, golang)
}
