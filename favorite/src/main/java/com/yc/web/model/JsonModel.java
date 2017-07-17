package com.yc.web.model;

import java.io.Serializable;
import java.util.List;

public class JsonModel<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer code;
	private String msg;
	private Object obj;
	
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getErrorMsg() {
		return msg;
	}
	public void setErrorMsg(String errorMsg) {
		this.msg = errorMsg;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	private Integer total; //* 总记录数
	private Integer pages; //* 当前页
	private Integer pagesize; //* 每页页数
	private List<T> rows;		//记录
	
	private Integer prepage; //* 上一页
	private Integer nextpage; //* 下一页
	private Integer totalpages; //* 总页数
	
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
		this.getTotalpages();
		this.getPrepage();
		this.getNextpage();
	}
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public Integer getPrepage() {
		if(this.pages<=1){
			this.prepage=this.pages;
		}else{
			this.prepage=this.pages-1;
		}
		return prepage;
	}
	public void setPrepage(Integer prepage) {
		this.prepage = prepage;
	}
	public Integer getNextpage() {
		if(this.pages>=this.totalpages){
			this.nextpage=this.pages;
		}else{
			this.nextpage=this.pages+1;
		}
		return nextpage;
	}
	public void setNextpage(Integer nextpage) {
		this.nextpage = nextpage;
	}
	public Integer getTotalpages() {
		this.totalpages=this.total%this.pagesize==0? this.total/this.pagesize:this.total/this.pagesize+1;
		return this.totalpages;
	}
	public void setTotalpages(Integer totalpages) {
		this.totalpages = totalpages;
	}

}
