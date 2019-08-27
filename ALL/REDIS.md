### Redis可能会问的问题
#### 1.Redis内容总结？
#### LICSLAN回答: 
     Redis
     
     Oracle MySQL  数据存在磁盘中
     缓存数据库
     缓存中间件
     memcache redis区别
     Memache:代码层次类锁Hash
     支持简单数据类型
     不支持数据持久化存储
     不支持主从
     不支持分片
     Redis 有持久化需求
     数据类型丰富  5中数据类型
     支持数据磁盘持久化存储
     支持主从
     支持分片（3.0+）
     
     为什么redis能这么快
     10万+	QPS（query per second 每秒查询次数）
     	1.完全基于内存，绝大部分请求是纯粹的内存操作，执行效率高 单进程单线程key-value缓存数据库 用c语言编写
     	2.数据库结构简单，对数据操作也简单
     	3.采用单线程，单线程也能处理高并发，想多核也可以启动多实例
     	（一般在面对多并发的时候想到是用多个线程处理，将IO线程和业务线程分开，业务线程使用线程池来避免频繁创建线程和销毁线程，即便是一次请求，阻塞了也不会影响到其他线程。为什么redis会选择反其道而选之？）
     	redis 单线程是指主线程是单线程的，主线程包括IO事件的处理以及IO 对应的相关请求的业务处理，此外主线程还负责过期键的处理，赋值协调，集群协调等等，这些除了IO 事情的处理逻辑会被封装成周期性的任务由主线程周期性的处理，正因为是单线程，对于所有客户端的请求读写请求都会串行化的执行，因此多个客户端对同一个key的操作不会产生并发，就会不会频繁的上下文线程切换和锁竞争
     	4.使用多路I复用模型，非阻塞IO
     
     
     	多路io复用模型
     	FD:file Descriptor 文件描述符
     		一个打开的文件通过唯一 的描述符进行运用，该描述符是打开文件的元数据到文件本身的映射
     
     		传统的阻塞IO模型
     						   		applicaion                    kernel
     						 -------  read -----system call------- >no data ready 		
     																	|		wait for data
     																	|
     		process block in                                       data ready
     		 a call to ready                                       copy data
     		 															|      copy data from kernel to user
     		 															|	
     		 				process data<------return ok-----------copy complete 
     
     
     		select 系统调用
     		Redis 采用的IO多路复用函数：epoll/kqueue/evport/select?
     		因地制宜
     		优先选用时间复杂度为O(1)的IO 多路复用函数作为底层实现
     		以时间复杂度为O（n）的select作为保底
     		基于react设计模式监听IO 事件
     
     		说说用过的Redis的数据类型
     		1.String 最基本 的数据类型 二进制安全 最大512兆   sdshdr 字符串 图片 数字
     		2.Hash String 元素组成的字典 适用于存储对象
     		3，List 列表 按照String元素插入顺序排序  栈功能 先进后出
     		Set String 元素组成的无序集合 通过哈希表实现  不允许重复  删除添加查找 O(1)
     		Sorted Set: String元素组成集合 通过哈希表实现  不允许重复 通过分数来为集合中的成员进行从小到大的排序
     		用于计数的HyperLogLog 用于支持存储地理位置信息的Geo
     
     		底层数据类型基础
     			1.简单动态字符串
     			2.链表
     			3.字典
     			4.跳跃表
     			5.整数集合
     			6.压缩列表
     			7.对象
     			
     			
#### 关于redis常见面试题总结
![redis常见面试题总结](https://www.cnblogs.com/jasontec/p/9699242.html)     			