### MySQL可能会问的问题
#### 1.如何优化慢查询？
#### LICSLAN回答: 
     A.定位慢查询日志,在建库的时候开启慢查询时间规定，在配置文件中开启时间限制，日志文件存储设置，统计慢查询条数设置。
     B.explian select xxxx  语句  可以看到该语句内容  type是什么 当type为index or all 时 extar 字段为 file-sort useing templerly  就注意是否要优化SQL判断是否是慢查询了
     C.如果时慢查询，那么就要根据业务场景来修改SQL了或者给相应的字段加索引了
#### 2.最左匹配原则是怎么样的？
#### LICSLAN回答:
     当建立了联合索引时 比如顺序时（abcd）时
     如果是 select  * from tablename where a ='' and b='' and ='' and =''  会走索引 
     如果是 select * from tablename where c ='' and b='' and d='' and a='' 也会走索引 
     如果是 select  * from tablename where b='' and c='' and d='' 那么就不会走索引了 注意喔
     如果是 select  * from tablename where ... 当后面的where语句遇到 in < > bettewn 等字段也不会走索引      
#### 3.密集索引和稀疏索引的区别？
#### LICSLAN回答:
     密集索引：密集索引文件中的每个搜索码值都对应一个索引值
     稀疏索引：稀疏索引文件只为索引码的某些值建立索引项，不是每个都建立
#### 4.为什么要使用索引？
#### LICSLAN回答:
     使用索引后就不会走全表查询了，提高了查询效率
#### 5.什么样的信息能成为索引？
#### LICSLAN回答:
     主键 就是能明显区别信息的字段  当然字段内容也不宜过大的信息     
#### 6.索引的数据结构？
#### LICSLAN回答:
     B+tree     
     
     
