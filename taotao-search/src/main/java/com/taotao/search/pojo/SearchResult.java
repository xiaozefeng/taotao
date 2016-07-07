package com.taotao.search.pojo;

import java.io.Serializable;
import java.util.List;

/***
 * 搜索结果封装类
 * @author xiaozefeng
 *
 */
public class SearchResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4918549537071130003L;
	/**商品数据*/
	private List<Item> itemList;
	/**记录总数*/
	private long recordCount;
	/**每页显示数据条数*/
	private long pageCount;
	/**当前页*/
	private long curPage;
	
	public List<Item> getItemList() {
		return itemList;
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	public long getPageCount() {
		return pageCount;
	}
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}
	public long getCurPage() {
		return curPage;
	}
	public void setCurPage(long curPage) {
		this.curPage = curPage;
	}
	
}
