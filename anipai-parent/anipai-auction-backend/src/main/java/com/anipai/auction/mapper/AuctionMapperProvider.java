package com.anipai.auction.mapper;

import java.util.Map;

import com.anipai.utils.Pagination;

public class AuctionMapperProvider {
	
	public String findAuctionPage(Map<String, Object> map) {
		Long agencyId = (Long)map.get("agencyId");
		Pagination page = (Pagination)map.get("page");
		StringBuilder sb = new StringBuilder();
		sb.append("select auction_id, auction_name, intro, begin_time, end_time, goods_id, qty_auction, state "
				+ "from auction ");
		if(agencyId != null) {
			sb.append("where agency_id=#{agencyId} ");
		} else {
			sb.append("where agency_id is null ");
		}
		sb.append("order by " + page.getSort() + " " + page.getOrder() + " ");
		sb.append("limit #{page.offset}, #{page.limit}");
		return sb.toString();
	}
	
	public String total(Map<String, Object> map) {
		Long agencyId = (Long)map.get("agencyId");
		StringBuilder sb = new StringBuilder();
		sb.append("select count(auction_id) from auction ");
		if(agencyId != null) {
			sb.append("where agency_id=#{agencyId}");
		} else {
			sb.append("where agency_id is null");
		}
		return sb.toString();
	}
}
