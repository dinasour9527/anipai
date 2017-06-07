package com.anipai.auction.service;

import java.math.BigDecimal;
import java.util.Map;

import com.anipai.auction.domain.Goods;

public interface GoodsService {

	Map<String, Object> getGoodsListPage(String sort, String order, int offset, int limit, Long agencyId);

	Goods getGoods(Long goodsId);

	Long createGoods(String goodsName, Long firstCategoryId, Long secondCategoryId, Long thirdCategoryId,
			BigDecimal assessPrice, BigDecimal expectPrice, String unit, Long qtyTotal, Long agencyId);

	void editGoods(Long goodsId, String goodsName, Long firstCategoryId, Long secondCategoryId, Long thirdCategoryId,
			BigDecimal assessPrice, BigDecimal expectPrice, String unit, Long qtyTotal);

	void deleteGoods(Long goodsId);

}
