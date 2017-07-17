package com.yc.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.yc.bean.Favorite;
import com.yc.bean.Tag;
import com.yc.biz.FavoriteBiz;
import com.yc.dao.FavoriteDao;
import com.yc.dao.TagDao;
import com.yc.dao.impl.FavoriteDaoImpl;
import com.yc.dao.impl.TagDaoImpl;

/**
 * addFavorite思路 1.先插入连接查询favorite，生成fid -> 一条sql 2.要根据“,”分隔，在循环每个 tname
 * 3.查tname是否已经存在 ->n条 ->将 application中的list传过来，是否有数据 4.改成循环list, ->
 * 判断这个tname是否已经在list中存在 如果存在，则update数量 ->改成记录要修改的id存到一个集中，【3,4,5】 ->update tag
 * set count=count+1 where tid in(3,4,5) 如果不存在，则insert ->改成记录要添加的tname存到一个集合中
 * 【搞笑，美女，房产】 ->insert into tag values(tname,count) values(搞笑，1),(xxx,2),(xxx,3)
 * 5.插入中间表。 insert into tagfavorite(tid,fid) values(0
 * 
 * @author ada
 *
 * 
 *
 *
 */
public class FavoriteBizImpl implements FavoriteBiz {
	private TagDao td = new TagDaoImpl();
	private FavoriteDao fd = new FavoriteDaoImpl();

	@Override
	public void addFavorite(Favorite fav) {
		fd.addFavorite(fav); // 1.得到fid
		
		String ftags = fav.getFtags();
		if (ftags != null && !ftags.equals("")) {
			// 1.分隔
			String[] tags = ftags.split(",");
			for (String t : tags) { 
				// 查询是否有这个tag
				Tag tag = new Tag();
				tag.setTname(t);
				tag = td.selectTagByName(tag);// 查是否已经存在这个tag
				if (tag == null) { 
					// 没有这个标签，则添加一个到tag表，并添加一个中间表
					tag = new Tag();
					tag.setTname(t);
					td.addTag(tag);
					tag = td.selectTagByName(tag);
				} else { // 原来有这个标签，则加数量即可
					td.increasement(tag);
				} // 添加中间表
				Map<String, String> map = new HashMap<String, String>();
				map.put("tid", tag.getTid() + "");  //tid无法获取，因为tid也是自动主键
				map.put("fid", fav.getFid() + "");
				td.addTagFaorite(map);
			}
		}

	}

	@Override
	public List<Tag> findAllTag() {
		return td.selectTagAll();
	}

	@Override
	public List<Favorite> findFavorite(String condition) {
		Favorite f = new Favorite();
		if (condition.equals("全部")) {
			f.setFtags(null);
			return fd.selectFavorite(f);
		} else if (condition.equals("未分类")) {
			f.setFtags("");
			return fd.selectFavorite(f);
		} else {
			f.setFtags(condition);
			Tag tag = new Tag();
			tag.setTname(condition);
			TagDao td = new TagDaoImpl();
			return td.selectFavoriteByTname(tag);
		}

	}

}
