package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.taotao.common.pojo.EUTreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.service.ItemCatService;
@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	
	@Override
	public List<EUTreeNode> getItemCatList(long parentId) {
		List<TbItemCat> itemCats = tbItemCatMapper.getItemByParentId(parentId);
		if(itemCats != null && itemCats.size()>0){
			List<EUTreeNode> lists =new ArrayList<>();
			for (TbItemCat tbItemCat : itemCats) {
				EUTreeNode treeNode = new EUTreeNode();
				treeNode.setId(tbItemCat.getId());
				treeNode.setText(tbItemCat.getName());
				treeNode.setState(tbItemCat.getIsParent()?"closed":"open");
				lists.add(treeNode);
			}
			return lists;
		}
		return null;
	}

}
