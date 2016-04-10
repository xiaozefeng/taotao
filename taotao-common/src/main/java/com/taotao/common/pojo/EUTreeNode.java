package com.taotao.common.pojo;
/**
 * easyui树形控件节点
 * <p>Title: EUTreeNode</p>
 * <p>Description: </p>
 * <p>Company: www.ewaytec.cn</p> 
 * @author	肖泽锋
 * @date	2016年4月4日上午1:18:04
 * @version 1.0
 */
public class EUTreeNode {
	private long id; //当前节点id
	private String text; //节点显示名称
	private String state; //节点的状态，打开(open)或关闭(closed)
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
