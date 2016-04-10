package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.TbItem;

public interface TbItemMapper {
	/**
	 * 分页查询
	 * <p>Title: getItemForPage</p>
	 * <p>Description: </p>
	 * @return
	 */
	public List<TbItem> getItemForPage();
	
	TbItem selectByPrimaryKey(Long id);
	
	/**
	 * 保存商品信息
	 * <p>Title: insertItem</p>
	 * <p>Description: </p>
	 * @return
	 */
	int insertItem(TbItem item);
   /* int countByExample(TbItemExample example);

    int deleteByExample(TbItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbItem record);

    int insertSelective(TbItem record);

    List<TbItem> selectByExample(TbItemExample example);

    TbItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbItem record, @Param("example") TbItemExample example);

    int updateByExample(@Param("record") TbItem record, @Param("example") TbItemExample example);

    int updateByPrimaryKeySelective(TbItem record);

    int updateByPrimaryKey(TbItem record);*/
}