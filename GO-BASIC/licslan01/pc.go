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

/**
go简单原生爬虫学习
*/

//定义一个全局变量
var (
	urldirx     = "E:/PCLX"
	urldirFilex = "hwl.txt"
	// regHref       = `((ht|f)tps?)://[w]{0,3}.baidu.com/link\?[a-zA-z=0-9-\s]*`
	regTitle      = `<title[\sa-zA-z="-]*>([^x00-xff]|[\sa-zA-Z=-：|，？"])*</title>`
	regCheckTitle = `(为什么|怎么)*.*([G|g][O|o][L|l][A|a][N|n][G|g]).*(怎么|实现|如何|为什么).*`
)

func checkFilex(dir string, file string) os.FileInfo {
	list, _ := ioutil.ReadDir(dir)
	for _, info := range list {
		if info.Name() == file {
			return info
		}
	}
	return list[0]
}

//一个爬虫如果不限制分秒爬抓次数，那你的网络肯定会受不了，
// 如果电脑配置不行的话，电脑也会挂掉，所以我们需要写一个计时器，
// golang已经提供了计时器的包 => time
func Timer() {
	t := time.NewTimer(time.Second * 1)
	<-t.C
	fmt.Print("\n\n\n执行爬抓\n\n")
	//添加新的内容

	/**
	regTitle是为了在代码中匹配真标题，因为有些网站为了防止爬虫，做了一些假标题以混淆视听，
	但是这些小伎俩还是很容易解决的，这个regTitle足以屏蔽掉70%的假标题。
	反正爬虫就是要和各大网站斗智斗勇 /手动滑稽
	regCheckTitle是为了过滤出这个网址是不是我想要的内容，所以我简单的写了一串正则。
	这串正则的意思主要是标题要带有为什么、怎么等关键词，然后标题必须有golang或者go的存在，
	这样的内容基本上是我想要的了
	*/
	f, _ := os.OpenFile(urldirx+"/"+urldirFilex, os.O_CREATE|os.O_APPEND|os.O_RDWR, 0666)
	file, _ := ioutil.ReadAll(f)
	pageCont, _ := pageVisit(strings.Split(string(file), "\n")[0])
	if checkRegexp(checkRegexp(pageCont, regTitle, 0).(string), regCheckTitle, 0).(string) != "" {
		fmt.Print(checkRegexp(checkRegexp(pageCont, regTitle, 0).(string), regCheckTitle, 0).(string))
		fmt.Print("\n有效内容 => " + checkRegexp(pageCont, regTitle, 0).(string))
	}
	fmt.Print("\n\n待爬抓网址共" +
		strconv.Itoa(len(strings.Split(string(file), "\n"))-1) + "个 => " +
		strings.Split(string(file), "\n")[0] + "\n")
	DelFirstText(urldirx + "/" + urldirFilex)
	Timer()
}

//去重  检查相同的链接
func checkRegexpx(cont string, reg string, style int) (result interface{}) {
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

//第一次爬取  文件内容为空时
func fistStart() {
	var num int
	//搜索golang 实现  在百度
	url := "http://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=39042058_20_oem_dg&wd=golang%E5%AE%9E%E7%8E%B0&oq=golang%2520%25E5%2588%25A0%25E9%2599%25A4%25E6%2595%25B0%25E7%25BB%2584&rsv_pq=d9be28ec0002df1b&rsv_t=8017GWpSLPhDmKilZQ1StC04EVpUAeLEP90NIm%2Bk5pRh5R9o57NHMO8Gaxm1TtSOo%2FvtJj%2B98%2Fsc&rqlang=cn&rsv_enter=1&inputT=3474&rsv_sug3=16&rsv_sug1=11&rsv_sug7=100&rsv_sug2=0&rsv_sug4=4230"
	//url :="http://www.zhenai.com/zhenghun"
	resp, _ := http.Get(url)
	defer resp.Body.Close()
	//将返回结果转为byte[]
	body, _ := ioutil.ReadAll(resp.Body)
	//正则表达式
	reg := regexp.MustCompile(`((ht|f)tps?)://[w]{0,3}.baidu.com/link\?[a-zA-z=0-9-\s]*`)
	f, _ := os.OpenFile(urldirx+"/"+urldirFilex, os.O_CREATE|os.O_APPEND|os.O_RDWR, 0666)
	defer f.Close()
	//循环匹配 查看的结果
	for _, d := range reg.FindAllString(string(body), -1) {
		//打开本地的文件 获得一个*File   ff
		ff, _ := os.OpenFile(urldirx+"/"+urldirFilex, os.O_RDWR, 0666)
		//获得[]byte
		file, _ := ioutil.ReadAll(ff)
		//切割为数组string
		dd := strings.Split(d, "")
		dddd := ""
		//遍历数组
		for _, ddd := range dd {
			if ddd == "?" {
				ddd = `\?`
			}
			dddd += ddd
		}
		//正则匹配
		if checkRegexpx(string(file), dddd, 0).(string) == "" {
			n, _ := io.WriteString(f, d+"\n")
			fmt.Println(n)
			fmt.Print("\n收集地址：" + d + "\n")
			num++
		}
		//fmt.Print(string(file))
		ff.Close()
	}
	fmt.Print("\n首次收集网络地址：" + strconv.Itoa(len(reg.FindAllString(string(body), -1))) + "\n")
	fmt.Print("\n去重后网络地址数：" + strconv.Itoa(num))
	fmt.Print("\n\n首次储存成功！\n")
}

func pageVisit(url string) (page string, body []byte) {
	resp, _ := http.Get(url)
	defer resp.Body.Close()
	body, _ = ioutil.ReadAll(resp.Body)
	page = string(body)
	return
}

func saveFile(file string, cont string) {
	f, _ := os.OpenFile(file, os.O_RDWR|os.O_APPEND|os.O_CREATE, 0666)
	defer f.Close()
	io.WriteString(f, cont)
}

/**
然后这一段就是说删掉这一条链接地址，如果没有有一段，你的爬虫将不厌其烦的去爬抓第一条链接地址，
能一直爬到你的ip被服务器安全程序处理掉
应该有人发现，然后呢？怎么没有把东西入库，怎么没有抓取新的链接。
这些内容已经把爬虫的基本原理都给讲掉了，其实很简单对不对，就是发起http请求，
然后通过正则匹配出自己想要的内容，再做后续的入库或者注入新鲜链接地址，让程序一直运行下去就好了。
*/
func DelFirstText(file string) {
	var text = ""
	f, _ := os.OpenFile(file, os.O_RDWR|os.O_CREATE, 0666)
	files, _ := ioutil.ReadAll(f)
	var ss = strings.Split(string(files), "\n")
	for i := 1; i < len(ss)-1; i++ {
		text += ss[i] + "\n"
	}
	defer f.Close()
	ioutil.WriteFile(file, []byte(text), 0666)
	fmt.Print("\n\n删除该地址 => " + ss[0])
}

/**
上面的方法已经可以将第一级的链接存在 E:/PCLX/hwl.txt 里面并且已经去重了
下一步就是要通过地址列表里的地址逐一爬抓，
去掉已经爬抓过的链接，并记录新的有效链接到地址列表里。
------------------------------------------------------------------------------
上面的firstStart函数（首次执行爬抓）已经执行过了，那就会重新调用main函数，
也就是在执行一次判断，但是因为我们的hwl.txt里已经有12条Url地址，所以这次会执行Timer函数。
Timer函数里我们写了一个计时器，防止程序崩溃或者网络崩溃，所以我这里设置了1秒执行一次，
其实没有必要这样，一秒钟执行3-8次也是没什么大问题的（本地情况下），如果放在服务器上，
那你得看一下自己的服务器配置和带宽配置酌情考虑了
*/
func main() {
	if checkFilex(urldirx, urldirFilex).Size() == 0 {
		//文件为空
		fistStart()
		//自己调用自己
		main()
	} else {
		//文件不为空了
		Timer()
	}
}

/**
	大量的数据散落在互联网中，要分析互联网上的数据，需要先把数据从网络中获取下业，这就需要网络爬虫技术。
　　网络爬虫是搜索引擎抓取系统的重要组成部分，爬虫的主要目的是将互联网上网页下载到本地，形成一个或联网内容的镜像备份。
　　网络爬虫的基本工作流程如下：
　　1.首先选取一部分种子URL
　　2.将这些URL放入待抓取URL队列
　　3.从待抓取URL队列中取出待抓取的URL,解析DNS，得到主机的IP，并将URL对应的网页下载下来，
	 存储到已下载网页库中，此外，将这些URL放入已抓取URL队列。
　　4.分析已抓取到的网页内容中的其他URL,并将URL放入待抓取URL队列，从而进入下一个循环。
　　 人工智能、大数据、云计算和物联网的未来发展值得重视，均为前沿产业，
	多智时代专注于人工智能和大数据的入门和科谱，在此为你推荐几篇优质好文：
	网络爬虫是什么，我们为什么要学习网络爬虫
	http://www.duozhishidai.com/article-14888-1.html
	Python和R语言对比，数据分析与挖掘该选哪一个?
	http://www.duozhishidai.com/article-21757-1.html
	Python工程师与人工智能工程师之间，最根本的区别是什么?
	http://www.duozhishidai.com/article-14635-1.html
	为什么要学习Python，有哪些优缺点，应该如何上手？
	http://www.duozhishidai.com/article-1784-1.html
*/
