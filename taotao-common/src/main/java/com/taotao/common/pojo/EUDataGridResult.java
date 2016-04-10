package com.taotao.common.pojo;

import java.util.List;

public class EUDataGridResult {
	private long total; //总记录数
	private List<?> rows;	//页面数据
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
}
