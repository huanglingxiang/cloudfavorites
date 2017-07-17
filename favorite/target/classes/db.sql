create database favorite;
create table tag(
	tid int primary key auto_increment,
	tname varchar(100),
	tcount int
);

create table favorite(
	fid int primary key auto_increment,
	flabel varchar(500),
	furl varchar(500),
	fdesc varchar(500),
	ftags varchar(500)
);

create table tagfavorite(
	tid int,
	fid int,
	primary key (tid,fid)
);

alter table tagfavorite add constraint fk_tagfavorite_tid foreign key(tid) references tag(tid);

alter table tagfavorite add constraint fk_tagfavorite_fid foreign key(fid) references favorite(fid);





----------------------------------------------------------------------------------------------------------
insert into tag(tname,tcount) values('军事',1);
insert into tag(tname,tcount) values('娱乐',1);
insert into tag(tname,tcount) values('生活',1);
insert into tag(tname,tcount) values('科技',1);
insert into tag(tname,tcount) values('故事',1);

insert into tagfavorite(tid,fid) values(1,1);
insert into tagfavorite(tid,fid) values(1,2);
insert into tagfavorite(tid,fid) values(2,1);
insert into tagfavorite(tid,fid) values(2,2);


select tag.tid,tname,tcount,favorite.fid,flabel,furl,flabel,fdesc,ftags
		from favorite
		left join tagfavorite
		on tagfavorite.fid=favorite.fid
		left join tag
		on tagfavorite.tid=tag.tid

select * from tag;
DELETE FROM tag ;
select * from tagfavorite;
DELETE FROM tagfavorite ;
select * from favorite;
DELETE FROM favorite ;

select favorite.fid as fid,flabel,furl,fdesc,ftags from favorite left join tagfavorite on tagfavorite.fid=favorite.fid left join tag on tagfavorite.tid=tag.tid where tag.tname="1231";
