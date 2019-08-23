package main

import (
	"fmt"
	"io/ioutil"
)

/**
条件语句学习
*/
func main() {

	const filename = "helloworld/abc.txt"
	/*bytes, e := ioutil.ReadFile(filename)
	if e!=nil{
		panic(e)
		fmt.Println(e)
	}else {
		fmt.Printf("%s\n",bytes)
	}*/

	//上面的写法可以改写为
	if bytes, e := ioutil.ReadFile(filename); e != nil {
		fmt.Println(e)
	} else {
		fmt.Printf("%s\n", bytes)
	}

	//if的条件里可以赋值
	//if的条件里赋值的变量作用域就在这个if语句里

	fmt.Println(sw(3, 4, "+"))
	fmt.Println(grade(0))
}

//switch 学习   switch 会自动break 除非使用fallthrough
func sw(a, b int, op string) int {
	var result int
	switch op {
	case "+":
		result = a + b
	case "-":
		result = a - b
	case "*":
		result = a * b
	case "/":
		result = a / b
	default:
		panic("unsupported operator:" + op)
	}
	return result
}

func grade(score int) string {
	g := ""
	switch {
	case score < 0 || score > 100:
		panic(fmt.Sprintf(
			"Wrong score: %d", score))
	case score < 60:
		g = "F"
	case score < 80:
		g = "C"
	case score < 90:
		g = "B"
	case score <= 100:
		g = "A"
	}
	return g
}
