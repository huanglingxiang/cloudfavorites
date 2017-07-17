package com.yc.dao.test;

import java.util.List;

import com.yc.bean.Favorite;
import com.yc.bean.Tag;
import com.yc.dao.TagDao;
import com.yc.dao.impl.TagDaoImpl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TagTest extends TestCase {
	public static Test suite(){
		return new TestSuite(FavoriteTest.class);
	}
	
	public TagTest(String name) {
		super(name);
	}
	
	public void selectAllTest() {
		TagDao td=new TagDaoImpl();
		List<Tag> list=td.selectTagAll();
		System.out.println(list);
	}
	
	public void selectFavoriteByTnameTest() {
		TagDao td=new TagDaoImpl();
		Tag tag=new Tag();
		tag.setTname("军事");
		List<Favorite> list=td.selectFavoriteByTname(tag);
		System.out.println(list);
	}
	
	public void selectTagByName() {
		TagDao td=new TagDaoImpl();
		Tag tag=new Tag();
		tag.setTname("军事");
		Tag tag2=td.selectTagByName(tag);
		System.out.println(tag2);
	}
	
	public void addTagTest() {
		TagDao td=new TagDaoImpl();
		Tag tag=new Tag();
		tag.setTname("人文");
		td.addTag(tag);
	}
	
	public void increaseCountTest() {
		TagDao td=new TagDaoImpl();
		Tag tag=new Tag();
		tag.setTname("军事");
		td.increasement(tag);
	}
}
