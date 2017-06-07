package com.anipai.admin.interfaces.auction.dto;

import java.util.ArrayList;
import java.util.List;

import com.anipai.auction.domain.Category;
import com.anipai.auction.domain.Goods;

public class GoodsDTOConverter {
	public static GoodsDTO toDTO(Goods goods) {
		Category firstCategory = goods.getFirstCategory();
		Category secondCategory = goods.getSecondCategory();
		Category thirdCategory = goods.getThirdCategory();
		return new GoodsDTO(goods.getGoodsId(), goods.getGoodsName(), firstCategory.getCategoryId(),
				firstCategory.getCategoryName(), secondCategory.getCategoryId(),
				secondCategory.getCategoryName(), thirdCategory.getCategoryId(), thirdCategory.getCategoryName(),
				goods.getAssessPrice(), goods.getExpectPrice(), goods.getUnit(), goods.getQtyTotal(),
				goods.getQtyAuction(), goods.getQtyDeal());
	}
	
	public static List<GoodsDTO> toDTOList(List<Goods> goodsList) {
		List<GoodsDTO> goodsDTOList = new ArrayList<GoodsDTO>();
		for(Goods goods : goodsList){
			goodsDTOList.add(GoodsDTOConverter.toDTO(goods));
		}
		return goodsDTOList;
	}
}
