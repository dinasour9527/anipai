package com.anipai.auction.mapper;

import java.util.Map;

import com.anipai.utils.Pagination;

public class GoodsMapperProvider {
	
	@SuppressWarnings("rawtypes")
	public String findGoodsPage(Map map) {
		Long agencyId = (Long)map.get("agencyId");
		Pagination page = (Pagination)map.get("page");
		StringBuilder sb = new StringBuilder();
		sb.append("select goods_id, goods_name, first_category_id, second_category_id, third_category_id, "
				+ "assess_price, expect_price, unit, qty_total, qty_auction, qty_deal from goods ");
		if(agencyId != null) {
			sb.append("where agency_id=#{agencyId} ");
		} else {
			sb.append("where agency_id is null ");
		}
		sb.append("order by " + page.getSort() + " " + page.getOrder() + " ");
		sb.append("limit #{page.offset}, #{page.limit}");
		return sb.toString();
	}
	
	@SuppressWarnings("rawtypes")
	public String total(Map map) {
		Long agencyId = (Long)map.get("agencyId");
		StringBuilder sb = new StringBuilder();
		sb.append("select count(goods_id) from goods ");
		if(agencyId != null) {
			sb.append("where agency_id=#{agencyId}");
		} else {
			sb.append("where agency_id is null");
		}
		return sb.toString();
	}
}
