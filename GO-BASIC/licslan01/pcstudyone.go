package GO_BASIC

import (
	"fmt"
	"io/ioutil"
	"net/http"
)

//单机版爬虫学习

/**
@author LICSLAN
单机单任务版    单机并发版    分布式版
*/
func main() {

	resp, err := http.Get("http://www.zhenai.com/zhenghun")
	if err != nil {
		panic(err)
	}
	defer resp.Body.Close()
	//获取页面的内容
	if resp.StatusCode == http.StatusOK {
		//获取body  不要heard
		all, e := ioutil.ReadAll(resp.Body)
		if e != nil {
			panic(e)
		}
		fmt.Printf("%s\n", all)
	}

}
