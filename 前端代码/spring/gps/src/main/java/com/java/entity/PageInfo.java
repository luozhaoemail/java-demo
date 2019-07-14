package com.java.entity;

import java.util.List;

public class PageInfo {
	//每页显示个数
	public int pageSize;
	//当前第几页
	public int pageNumber;
	//总页数
	public long pageTotal;
	//当前页显示的数据
	public List<?> list;
	
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public long getTotal() {
		return pageTotal;
	}
	public void setTotal(long total) {
		this.pageTotal = total;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	
	@Override
	public String toString() {
		return "PageInfo [pageSize=" + pageSize + ", pageNumber=" + pageNumber
				+ ", pageTotal=" + pageTotal + "]";
	}
	
}
