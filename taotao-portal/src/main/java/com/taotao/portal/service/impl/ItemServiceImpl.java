package com.taotao.portal.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Value("${REST_ITEM_INFO}")
	private String REST_ITEM_INFO;
	
	@Value("${REST_ITEM_DESC}")
	private String REST_ITEM_DESC;
	
	@Value("${Rest_ITEM_PARAM}")
	private String Rest_ITEM_PARAM;
	
	
	@Override
	public ItemInfo getItemInfo(Long itemId) {
		
		try {
			String json = HttpClientUtil.doGet(REST_BASE_URL+REST_ITEM_INFO+itemId);
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, ItemInfo.class);
			if(taotaoResult.getStatus() ==200){
				ItemInfo itemInfo= (ItemInfo) taotaoResult.getData();
				return itemInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}


	@Override
	public String getItemDesc(Long itemId) {
		String json = HttpClientUtil.doGet(REST_BASE_URL+REST_ITEM_DESC+itemId);
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemDesc.class);
		if(taotaoResult.getStatus() == 200){
			TbItemDesc desc =  (TbItemDesc) taotaoResult.getData();
			String itemDesc = desc.getItemDesc();
			return itemDesc;
		}
		return "";
	}


	@Override
	public String getItemParamItem(Long itemId) {
		String json = HttpClientUtil.doGet(REST_BASE_URL+Rest_ITEM_PARAM+itemId);
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemParamItem.class);
		if(taotaoResult.getStatus() ==200){
			TbItemParamItem tbItemParamItem = (TbItemParamItem) taotaoResult.getData();
			String paramData = tbItemParamItem.getParamData();
			List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
			StringBuffer sb = new StringBuffer();
			sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\"\n" );
			sb.append("       <tbody>\n" );
			for(Map m1:jsonList){
				sb.append("           <tr>\n" );
				sb.append("               <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n" );
				sb.append("           </tr>\n" );
				sb.append("           <tr>\n" );
				sb.append("           </tr>\n" );
				
				List<Map> jsonList2 = (List<Map>) m1.get("params");
				for (Map m2 :jsonList2){
					sb.append("           <tr>\n" );
					sb.append("               <td class=\"tdTitle\">"+m2.get("k")+"</td>\n" );
					sb.append("               <td>"+m2.get("v")+"</td>\n" );
					sb.append("           </tr>\n" );
				}
			}
			sb.append("       </tbody>\n" );
			sb.append("   </table>");
			return sb.toString();
		}
		return "";
	}
}
