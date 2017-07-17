package com.yc.dao;

import java.util.List;
import java.util.Map;

import com.yc.bean.Favorite;
import com.yc.bean.Tag;

public interface TagDao {
	
	public List<Tag> selectTagAll();
	
	/**
	 * 根据标签名查这个标签下所有的favorite
	 * @param tag
	 * @return
	 */
	public List<Favorite> selectFavoriteByTname(Tag tag);
	
	public Tag selectTagByName(Tag tag); 
	
	public void addTag(Tag tag);
	
	public void increasement(Tag tag);

	/**
	 * 中间表的操作
	 * @param map
	 */
	public void addTagFaorite(Map<String, String> map);
	
}
