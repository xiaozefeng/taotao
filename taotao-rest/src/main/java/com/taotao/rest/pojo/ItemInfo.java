package com.taotao.rest.pojo;

import com.taotao.pojo.TbItem;
/**
 * 商品信息
 * @author xiaozefeng
 *
 */
public class ItemInfo extends TbItem {
	/**解决多张图片取值问题*/
	public String[] getImages() {
		if(getImage() != null){
			String[] split = getImage().split(",");
			return split;
		}
		return null;
	}
}
