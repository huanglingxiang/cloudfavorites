package com.yc.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	private String tid;
	private String tname;
	private Integer tcount;
	//一个tag下有多连接  即属于tname为军事的连接有多个  -》mapper  -> collection 
	private List<Favorite> favorites=new ArrayList<Favorite>();

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public Integer getTcount() {
		return tcount;
	}

	public void setTcount(Integer tcount) {
		this.tcount = tcount;
	}

	public List<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}

	@Override
	public String toString() {
		return "Tag [tid=" + tid + ", tname=" + tname + ", tcount=" + tcount + ", favorites=" + favorites + "]";
	}

	
	
	
	
}
