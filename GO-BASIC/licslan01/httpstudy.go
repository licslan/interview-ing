package main

import (
	"fmt"
	"net/http"
	"net/http/httputil"
)

func main() {
	resp, err := http.Get("https://www.baidu.com")
	//如果有错误的话 就去处理
	if err != nil {
		panic(err)
	}
	//关闭http请求
	defer resp.Body.Close()

	//获取http请求内容
	bytes, e := httputil.DumpResponse(resp, true)
	if err != nil {
		panic(e)
	}
	fmt.Printf("%s\n", bytes)

}
