package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.constant.RedisConstant;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Override
	public CatResult getItemCatList() {
		CatResult result = new CatResult();
		String string = jedisClient.hget(RedisConstant.INDEX_CATEGORY_REDIS_KEY, "0");
		if(StringUtils.isNotBlank(string)){
			List<Object> list = JsonUtils.jsonToList(string, Object.class);
			result.setData(list);
			return result;
		}
		
		List<?> catList = getCatList(0);
		result.setData(catList);
		
		try {
			String json = JsonUtils.objectToJson(catList);
			jedisClient.hset(RedisConstant.INDEX_CATEGORY_REDIS_KEY, "0", json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	/**
	 * 获取商品分类列表
	 * @param parentId
	 * @return
	 */
	private List<?> getCatList(long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//查询商品分类
		List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
		//返回值list
		List resultList = new ArrayList<>();
		int count =0 ;
		//向List中添加节点信息
		for (TbItemCat itemCat : list) {
			//判断是否为父节点
			if(itemCat.getIsParent()){
				CatNode node = new CatNode();
				//当前层级在第一层时
				if(parentId == 0){
					node.setName("<a href='/products/"+itemCat.getId()+".html'>"+itemCat.getName()+"</a>");
				}else{
					node.setName(itemCat.getName());
				}
				node.setUrl("/products/"+itemCat.getId()+".html");
				node.setItem(getCatList(itemCat.getId()));
				resultList.add(node);
				count ++;
				if(parentId == 0 && count>=14){
					break;
				}
			}else{
				//叶子节点
				resultList.add("/products/"+itemCat.getId()+".html|" + itemCat.getName());
			}
		}
		
		
		return resultList;
	}

}
