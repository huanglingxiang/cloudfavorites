升级增加操作:


1. 先插入联接 favorite, 生成fid  ->  一条sql
2. 要根据,分隔，再循环每个  tname
3. 查tname是否已经存在      ->   n条   ->  将   application中的list传过来,是否有数据
4. 改成循环list, -> 
        判断这个  tname 是否在list中存在.
                   如果存在，则update 数量.      -> 改成记录要修改的id 存到一个集中.   [3,4,5,6]   -> update tag set count=count+1 where tid in(3,4,5,6)
                   如果不存在，则insert          ->  改成记录要添加的tname存到一个集合中  [搞笑,美女，房产]   ->   insert into tag values(tname,count) values (搞笑,1),(xx,1,),(xxx,1),() 


5.  插入中间表. 
    insert into tagfavorite( tid, fid) values()
    
    
====
1.修改数据表中的自增列为非自增.类型为  varchar(50)
2.在程序中采用不  UUID类来生成编号. 
    