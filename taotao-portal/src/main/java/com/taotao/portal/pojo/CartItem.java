package com.taotao.portal.pojo;
/**
 * 商品信息 
 * 目的：由于cookie存储的数据有限制，所以只存储我们需要的信息
 * @author xiaozefeng
 *
 */

public class CartItem {
	/**商品id*/
	private long id;
	/**商品标题*/
	private String title;
	/**商品数量*/
	private Integer num;
	/**商品价格*/
	private long price;
	/**商品图片，只取一张*/
	private String image;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
