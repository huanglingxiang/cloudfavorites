package com.yc.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tag implements Serializable {

	private static final long serialVersionUID = 2776264839186532004L;
	private String tid;
	private String tname;
	private Integer tcount;
	
	

	public Tag(String tid) {
		super();
		this.tid = tid;
	}

	public Tag(String tid, String tname) {
		super();
		this.tid = tid;
		this.tname = tname;
	}

	public Tag() {
		super();
	}

	//一个Tag下有多个联接，  即属于 tname为军事的联接有多个.   ->   mapper  ->    collection
	private List<Favorite> favorites = new ArrayList<Favorite>();

	public List<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}

	@Override
	public String toString() {
		return "Tag [tid=" + tid + ", tname=" + tname + ", tcount=" + tcount + "]";
	}

	

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

}
