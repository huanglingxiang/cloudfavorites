package com.yc.biz.test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.yc.bean.Tag;
import com.yc.dao.TagDao;
import com.yc.dao.impl.TagDaoImpl;

import junit.framework.TestCase;

public class TestDao extends TestCase {
	
	public void testAddTag(){
		List<Tag> list=new ArrayList<Tag>();
		for(int i=1;i<=10;i++){
			list.add( new Tag( UUID.randomUUID().toString(), "标签"+i  ));
		}
		TagDao td=new TagDaoImpl();
		td.addTag(   list );
	}
	
	public void testUpdateTag(){
		List<Tag> list=new ArrayList<Tag>();
		
		list.add( new Tag( "5c36e0ed-3117-477f-866b-f0846b856856"));
		list.add( new Tag( "0ed8c3d0-3e20-4fb2-9ac5-6b3d5af8b528"));
		list.add( new Tag( "4c531108-5fd3-4a59-adea-57e67d11ce2f"));
		
		TagDao td=new TagDaoImpl();
		td.increaseCount(list);
	}
}
