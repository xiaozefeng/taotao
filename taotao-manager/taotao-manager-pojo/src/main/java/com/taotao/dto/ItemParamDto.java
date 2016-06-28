package com.taotao.dto;

import java.io.Serializable;
import java.util.Date;

public class ItemParamDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 91152053020761820L;
	
	private String itemCatName;
	private Long id;
	private String paramData;
	private Date created;
	private Date updated;
	private Long itemCatId;
	
	public String getItemCatName() {
		return itemCatName;
	}
	public void setItemCatName(String itemCatName) {
		this.itemCatName = itemCatName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getParamData() {
		return paramData;
	}
	public void setParamData(String paramData) {
		this.paramData = paramData;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public Long getItemCatId() {
		return itemCatId;
	}
	public void setItemCatId(Long itemCatId) {
		this.itemCatId = itemCatId;
	}
	
	
}
