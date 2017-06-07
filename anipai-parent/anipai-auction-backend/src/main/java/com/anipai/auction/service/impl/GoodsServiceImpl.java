package com.anipai.auction.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anipai.agency.domain.Agency;
import com.anipai.auction.domain.Category;
import com.anipai.auction.domain.Goods;
import com.anipai.auction.mapper.GoodsMapper;
import com.anipai.auction.service.GoodsService;
import com.anipai.utils.Pagination;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {
	
	@Autowired
	private GoodsMapper goodsMapper;

	@Override
	public Map<String, Object> getGoodsListPage(String sort, String order, int offset, int limit, Long agencyId) {
		Map<String, Object> goodsListPage = new HashMap<String, Object>();
		Pagination pagination = new Pagination(sort, order, offset, limit);
		List<Goods> goodsList = goodsMapper.findGoodsPage(pagination, agencyId);
		int total = goodsMapper.total(agencyId);
		goodsListPage.put("list", goodsList);
		goodsListPage.put("total", total);
		return goodsListPage;
	}

	@Override
	public Goods getGoods(Long goodsId) {
		Goods goods = goodsMapper.findGoods(goodsId);
		return goods;
	}

	@Override
	public Long createGoods(String goodsName, Long firstCategoryId, Long secondCategoryId, Long thirdCategoryId,
			BigDecimal assessPrice, BigDecimal expectPrice, String unit, Long qtyTotal, Long agencyId) {
		Category firstCategory = new Category(firstCategoryId, null, null, null, null);
		Category secondCategory = new Category(secondCategoryId, null, null, null, null);
		Category thirdCategory = new Category(thirdCategoryId, null, null, null, null);
		Agency agency = new Agency(agencyId, null);
		Goods goods = new Goods(goodsName, firstCategory, secondCategory, thirdCategory,
				assessPrice, expectPrice, unit, qtyTotal, null, null, agency);
		goodsMapper.addGoods(goods);
		return goods.getGoodsId();
	}

	@Override
	public void editGoods(Long goodsId, String goodsName, Long firstCategoryId, Long secondCategoryId,
			Long thirdCategoryId, BigDecimal assessPrice, BigDecimal expectPrice, String unit, Long qtyTotal) {
		Category firstCategory = new Category(firstCategoryId, null, null, null, null);
		Category secondCategory = new Category(secondCategoryId, null, null, null, null);
		Category thirdCategory = new Category(thirdCategoryId, null, null, null, null);
		Goods goods = new Goods(goodsId, goodsName, firstCategory, secondCategory, thirdCategory,
				assessPrice, expectPrice, unit, qtyTotal, null, null, null);
		goodsMapper.updateGoods(goods);
	}

	@Override
	public void deleteGoods(Long goodsId) {
		goodsMapper.deleteGoods(goodsId);
	}

}
