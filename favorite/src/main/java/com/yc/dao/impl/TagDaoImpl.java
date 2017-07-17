package com.yc.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.yc.bean.Favorite;
import com.yc.bean.Tag;
import com.yc.dao.MyBatisHelper;
import com.yc.dao.TagDao;

public class TagDaoImpl implements TagDao {

	@Override
	public List<Tag> selectTagAll() {
		SqlSession session=null;
		try {
			session=MyBatisHelper.getSession();
			List<Tag> t=session.selectList(Tag.class.getName()+".selectAll");
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(session!=null){
				session.close();
			}
		}
		return null;
	}

	@Override
	public List<Favorite> selectFavoriteByTname(Tag tag) {
		SqlSession session=null;
		try {
			session=MyBatisHelper.getSession();
			List<Favorite> list=session.selectList(Tag.class.getName()+".selectFavoriteByTname",tag);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(session!=null){
				session.close();
			}
		}
		return null;
	}

	@Override
	public Tag selectTagByName(Tag tag) {
		SqlSession session=null;
		try {
			session=MyBatisHelper.getSession();
			Tag t=session.selectOne(Tag.class.getName()+".selectTagByName",tag);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(session!=null){
				session.close();
			}
		}
		return null;
	}

	@Override
	public void addTag(Tag tag) {
		SqlSession session = null;
		try {
			session = MyBatisHelper.getSession();
			session.insert(Tag.class.getName() + ".addTag",tag);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public void increasement(Tag tag) {
		SqlSession session=null;
		try {
			session=MyBatisHelper.getSession();
			session.update(Tag.class.getName()+".increaseCount",tag);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			if(session!=null){
				session.close();
			}
		}
		
	}

	@Override
	public void addTagFaorite(Map<String, String> map) {
		SqlSession session=null;
		try {
			session=MyBatisHelper.getSession();
			session.update(Tag.class.getName()+".addTagFavorite",map);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			if(session!=null){
				session.close();
			}
		}
		
	}


}
