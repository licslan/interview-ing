## MySQL可能会问的问题
#### 1.如何优化慢查询？
#### LICSLAN: 
     A.定位慢查询日志 在建库的时候开启慢查询时间规定，比如说SQL执行超过多久就算慢查询了，在配置文件中开启时间限制，
     日志文件存储设置，统计慢查询条数设置。<br>
     B.explian select xxxx  语句  可以看到该语句内容  type是什么 当type为index or all 时  就注意是否要优化SQL判断是否是慢查询了
     C.如果时慢查询，那么就要根据业务场景来修改SQL了或者给相应的字段加索引了<br>
#### 2.最左匹配原则是怎么样的？
#### LICSLAN:
     当建立了联合索引时 比如顺序时（abcd）时<br>
     如果是 select  * from tablename where a ='' and b='' and ='' and ='' 会走索引 <br>
     如果是 select * from tablename where c ='' and b='' and d='' and a='' 也会走索引  <br>
     如果是 select  * from tablename where b='' and c='' and d=''<br>
     那么就不会走索引了 注意喔 <br>
#### 2.密集索引和稀疏索引的区别
#### LICSLAN:
     密集索引：
     稀疏索引：
     
     
     
