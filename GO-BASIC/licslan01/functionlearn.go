package main

import "fmt"

/**
go语言的函数定义
*/
func main() {
	fmt.Printf("%d\n %q\n", hello(3, 4, ""), hellomany(5, 6, ""))
	fmt.Println(hellomanyx(3, 5, ""))
	fmt.Println(dive(13, 3))
	i, i2 := add(13, 3)
	fmt.Println(i, i2)
	x, _ := add(13, 3) //第二个值不想用 就可以用 _ 表示不用
	fmt.Println(x)

	//指针
	fmt.Println("--------------------")
	a, b := 3, 4
	fmt.Println(swapx(a, b))

	swapy(&a, &b) //指针
	fmt.Println(a, b)
}

//定义一个函数  参数 ab 类型int  c 类型string  方法返回值int
func hello(a, b int, c string) int {
	return 0
}

//定义一个函数  参数 ab 类型int  c 类型string  方法返回值string
func hellomany(a, b int, c string) string {
	return ""
}

//定义一个函数  参数 ab 类型int  c 类型string  方法返回多个值  int string  这个看起来和其他语言不一样吧
func hellomanyx(a, b int, c string) (int, string) {
	return 0, "hello"
}
func dive(a, b int) (int, int) {
	return a / b, a % b
}

func add(a, b int) (int, int) {
	return a / b, a % b
}

//返回值类型写在最后面
//可以返回多个值
//函数可以作为参数
//没有默认参数  可选参数

//指针
func swapx(a, b int) (int, int) {
	b, a = a, b
	return a, b
}

func swapy(a, b *int) {
	*b, *a = *a, *b
	//return a,b
}
