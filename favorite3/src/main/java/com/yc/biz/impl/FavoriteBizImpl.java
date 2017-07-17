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

public class FavoriteBizImpl implements FavoriteBiz {

	private FavoriteDao fd = new FavoriteDaoImpl();
	private TagDao td = new TagDaoImpl();

	private Tag selectTagByName(String tname) {
		Tag t = new Tag();
		t.setTname(tname);
		List<Tag> list = td.selectTag(t);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void addFavorite(Favorite fav, List<Tag> taglist) {
		// 存新的tagname
		List<Tag> newtaglist = new ArrayList<Tag>();
		List<Map<String, String>> newtagfavorite = new ArrayList<Map<String, String>>();
		List<Tag> oldtaglist = new ArrayList<Tag>();
		List<Map<String, String>> oldtagfavorite = new ArrayList<Map<String, String>>();
		fd.addFavorite(fav); // 1. 得到 fid?
		int newfid = fav.getFid();
		String ftags = fav.getFtags();
		if (ftags != null && !ftags.equals("")) {
			// 1. ,分隔
			String[] tags = ftags.split(",");
			for (String tagname : tags) {
				boolean isnewtag = true;
				for (Tag tag : taglist) {
					if (tag.getTname().equals(tagname)) {
						isnewtag = false;
						oldtaglist.add(tag);
						Map<String, String> tagfavorite = new HashMap<String, String>();
						tagfavorite.put("tid", tag.getTid());
						tagfavorite.put("fid", newfid + "");
						oldtagfavorite.add(tagfavorite);
						break;
					}
				}
				if (isnewtag) {
					String tid = UUID.randomUUID().toString();
					newtaglist.add(new Tag(tid, tagname));
					Map<String, String> tagfavorite = new HashMap<String, String>();
					tagfavorite.put("tid", tid);
					tagfavorite.put("fid", newfid + "");
					newtagfavorite.add(tagfavorite);
				}
			}

			// 先插入新的tag,插入中间表
			if (newtaglist != null && newtaglist.size() > 0) {
				td.addTag(newtaglist);
				td.addTagFavorite(newtagfavorite);
			}
			// 再将旧的tag更新，再插入中间表
			if (oldtaglist != null && oldtaglist.size() > 0) {
				td.increaseCount(oldtaglist);
				td.addTagFavorite(oldtagfavorite);
			}
		}

	}

	@Override
	public List<Tag> findAllTag() {
		Tag tag = new Tag();
		return td.selectTag(tag);
	}

	@Override
	public List<Favorite> findFavorite(String condition) {
		Favorite f = new Favorite();
		if (condition.equals("全部")) {
			f.setFtags("");
			return fd.selectFavoriteAll(f);
		} else if (condition.equals("未分类")) {
			f.setFtags(null);
			return fd.selectFavoriteAll(f);
		} else {
			TagDao td = new TagDaoImpl();
			Tag t = new Tag();
			t.setTname(condition);
			return td.selectFavoriteByTname(t);
		}
	}

}
