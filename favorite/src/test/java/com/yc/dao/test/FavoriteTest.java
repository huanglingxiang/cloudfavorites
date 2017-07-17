package com.yc.dao.test;

import java.util.List;

import com.yc.bean.Favorite;
import com.yc.dao.FavoriteDao;
import com.yc.dao.impl.FavoriteDaoImpl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class FavoriteTest extends TestCase {

	
	public FavoriteTest(String name) {
		super(name);
	}
	
	public static Test suite(){
		return new TestSuite(FavoriteTest.class);
	}
	
	
	public void testAddFavorite() {
		Favorite f=new Favorite();
		f.setFdesc("");
		f.setFlabel("baidu");
		f.setFtags("门户");
		f.setFurl("http://www.baidu.com");
		FavoriteDao fd=new FavoriteDaoImpl();
		fd.addFavorite(f);
	}
	
	public void testSelectFavoriteAll() {
		Favorite f=new Favorite();
		f.setFtags(null);
		FavoriteDao fd=new FavoriteDaoImpl();
		List<Favorite> list=fd.selectFavorite(f);
		System.out.println(list);
	}
	
}
