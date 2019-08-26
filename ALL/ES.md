### ES总结
#### es 写入数据的工作原理是什么啊？es 查询数据的工作原理是什么啊？底层的 lucene 介绍一下呗？倒排索引了解吗？
     
     面试官心理分析
     问这个，其实面试官就是要看看你了解不了解 es 的一些基本原理，因为用 es 无非就是写入数据，搜索数据。你要是不
     明白你发起一个写入和搜索请求的时候，es 在干什么，那你真的是......
     对 es 基本就是个黑盒，你还能干啥？你唯一能干的就是用 es 的 api 读写数据了。要是出点什么问题，你啥都不知道，
     那还能指望你什么呢？
     
     es 写数据过程
     客户端选择一个 node 发送请求过去，这个 node 就是 coordinating node（协调节点）。
     coordinating node 对 document 进行路由，将请求转发给对应的 node（有 primary shard）。
     实际的 node 上的 primary shard 处理请求，然后将数据同步到 replica node。
     coordinating node 如果发现 primary node 和所有 replica node 都搞定之后，就返回响应结果给客户端。
![ES00](https://github.com/licslan/interview-ing/raw/master/ALL-THING/ES/ES00.jpg)<br>     