package com.yc.biz;

import java.util.List;

import com.yc.bean.Favorite;
import com.yc.bean.Tag;

public interface FavoriteBiz {
	/**
	 *	-》TODO:所有的操作要在一个事物中（采用spring）
	 * 从fav中取出ftags（门户，军事），根据，分割，取出每个标签 【“门户”，“军事”】
	 *	循环查是否有门户，是否有军事
	 *		存在则更新这个标签数量  -》 TagMapper中increaseCount方法
	 *		不存在，则要想Tag中加入一条数据  -》TagMapper中的addTag  ->还要在中间表中加入关系的数据   (TagMapper 中的addTagFavorite)
	 *	在想favorite添加一条数据  favoriteMapper的addFav
	 * @param fav
	 */
	public void addFavorite(Favorite fav);
	
	/**
	 * 查所有的Tag
	 * 用于云图， 首页显示所有的Tag
	 */
	public List<Tag> findAllTag();
	
	/**
	 * 
	 * @param condition:值有全部 未分类  名字
	 * @return
	 */
	public List<Favorite> findFavorite(String condition);
	
	
	
	
}
