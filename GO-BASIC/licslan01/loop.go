package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

/**
循环学习
*/
func main() {

	sum := 0
	for i := 1; i < 100; i++ {
		sum += i
	}
	fmt.Println(sum)
	//for循环里面不需要括号
	//for的条件里可以省略初始条件，结束条件，递增表达式

	fmt.Print(converToBin(5)) //101
	opfile("helloworld/abc.txt")
	//死循环  处理很有特别  gorountie很多是死循环
	foreverloop()
}

func foreverloop() {
	for {
		fmt.Println("hello world")
	}
}

//将整数转成2进制
func converToBin(n int) string {
	result := ""
	//n/ =2  递增是2
	for ; n > 0; n /= 2 {
		lsb := n % 2
		result = strconv.Itoa(lsb) + result
	}
	return result
}

func opfile(filename string) {
	file, e := os.Open(filename)
	if e != nil {
		panic(e)
	}
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		fmt.Println(scanner.Text())
	}

}
