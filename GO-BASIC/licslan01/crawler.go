package main

import (
	"fmt"
	"io"
	"io/ioutil"
	"net/http"
	"os"
	"regexp"
	"strconv"
	"strings"
	"time"
)

/**原生爬虫练习  */

//定义一个全局变量
var (
	regexHerf  = `((ht|f)tps?)://[w]{0,3}.baidu.com/link\?[a-zA-z=0-9-\s]*`
	urldir     = "E:/PCLX"
	urldirFile = "hwl.txt"
)

//定义个计时器的方法   限制爬取次数和时间
func timex() {
	timer := time.NewTimer(time.Second * 1)
	<-timer.C
	fmt.Printf("\n\n\n执行爬取\n\n")
	timex()
}

//校验文件
func checkFile(dir string, file string) os.FileInfo {
	list, _ := ioutil.ReadDir(dir)
	for _, info := range list {
		if info.Name() == file {
			return info
		}
	}
	return list[0]
}

//第一次抓取数据
func fistGrab() {
	var num int
	url := "http://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=39042058_20_oem_dg&wd=golang%E5%AE%9E%E7%8E%B0&oq=golang%2520%25E5%2588%25A0%25E9%2599%25A4%25E6%2595%25B0%25E7%25BB%2584&rsv_pq=d9be28ec0002df1b&rsv_t=8017GWpSLPhDmKilZQ1StC04EVpUAeLEP90NIm%2Bk5pRh5R9o57NHMO8Gaxm1TtSOo%2FvtJj%2B98%2Fsc&rqlang=cn&rsv_enter=1&inputT=3474&rsv_sug3=16&rsv_sug1=11&rsv_sug7=100&rsv_sug2=0&rsv_sug4=4230"
	//url :="https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=golang%E5%AE%9E%E7%8E%B0&rsv_pq=d8026186010cef2b&rsv_t=40c4JU30NreIJRMPIgyqlXUZFBVKyEUmkBnRd7iF%2BdoQ1GGX31nDDHkncXM&rqlang=cn&rsv_enter=1&rsv_dl=ib&rsv_sug3=5&rsv_sug1=4&rsv_sug7=101"
	//http get 请求获取地址页面信息
	resp, err := http.Get(url)
	if err != nil {
		panic(err)
	}
	defer resp.Body.Close()
	bytes, _ := ioutil.ReadAll(resp.Body)
	reg := regexp.MustCompile(`((ht|f)tps?)://[w]{0,3}.baidu.com/link\?[a-zA-z=0-9-\s]*`)
	file, _ := os.OpenFile(urldir+"/"+urldirFile, os.O_CREATE|os.O_APPEND|os.O_RDWR, 0666)
	defer file.Close()
	//遍历
	for _, d := range reg.FindAllString(string(bytes), -1) {
		ff, _ := os.OpenFile(urldir+"/"+urldirFile, os.O_RDWR, 0666)
		files, _ := ioutil.ReadAll(ff)
		dd := strings.Split(d, "")
		ddd := ""
		for _, ddd := range dd {
			if ddd == "?" {
				ddd = `\?`
			}
			ddd += ddd
		}

		/**
		通过循环数组，首先对链接里的特殊符号做特出处理，然后通过checkRegexp函数做查重，
		就是防xx止有多个重复链接记录导致浪费资源，最后存入txt文件。
		*/
		if checkRegexp(string(files), ddd, 0).(string) == "" {
			io.WriteString(file, d+"\n")
			fmt.Println("\n收集的地址： " + d + "\n")
			num++
		}
		fmt.Print(string(files))
		ff.Close() //关闭文件流
	}
	fmt.Print("\n首次收集网络地址：" + strconv.Itoa(len(reg.FindAllString(string(bytes), -1))) + "\n")
	fmt.Print("\n去重后网络地址数：" + strconv.Itoa(num))
	fmt.Print("\n\n首次储存成功！\n")
}

func checkRegexp(cont string, reg string, style int) (result interface{}) {
	check := regexp.MustCompile(reg)
	switch style {
	case 0:
		result = check.FindString(cont)
	case 1:
		result = check.FindAllString(cont, -1)
	default:
		result = check.FindAll([]byte(cont), -1)
	}
	return
}

func main() {
	/**
	因为我们存在两种情况，第一次爬取或不是第一次爬取的情况是做不同操作的。
	那要怎么判断呢？因为我们的链接是储存在txt文件里的，所以我们只需要去
	查txt文件是不是为空，如果为空就认为他是第一次执行程序，先访问源网址，
	否则就按照文件里的链接依次访问。
	*/

	if checkFile(urldir, urldirFile).Size() == 0 {
		//文件夹是空的
		fistGrab()
		//main()
	} else {
		//不是第一次执行
		timex()
	}

}
