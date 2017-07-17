package com.yc.dao.test;

import java.util.List;

import com.yc.bean.Favorite;
import com.yc.bean.Tag;
import com.yc.biz.FavoriteBiz;
import com.yc.biz.impl.FavoriteBizImpl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class FavoriteBizTest extends TestCase {

	public FavoriteBizTest(String name) {
		super(name);
	}
	
	public static Test suite(){
		return new TestSuite(FavoriteTest.class);
	}
	
	public void testAddFavorite(){
		Favorite f=new Favorite();
		f.setFdesc("good");
		f.setFlabel("google");
		f.setFtags("门户,军事,娱乐,搜索");
		f.setFurl("http://www.google.com");
		FavoriteBiz fd=new FavoriteBizImpl();
		fd.addFavorite(f);
	}
	
	public void testFindAllTag(){
		FavoriteBiz fd=new FavoriteBizImpl();
		List<Tag> list=fd.findAllTag();
		for(Tag t:list){
			System.out.println(t);
		}
	}
	
	
}
