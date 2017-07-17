package com.yc.dao;

import java.util.List;

import com.yc.bean.Favorite;

public interface FavoriteDao {
	
	public List<Favorite> selectFavorite(Favorite favorite);
	
	/**
	 * 添加链接
	 * @param favorite
	 */
	public void addFavorite(Favorite favorite);
}
