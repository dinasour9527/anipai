package com.anipai.auction.service;

import java.util.Date;
import java.util.Map;

import com.anipai.auction.domain.Auction;

public interface AuctionService {

	Long createAuction(Long goodsId, String auctionName, Date beginTime, Date endTime, Long qtyAuction, String intro, Long agencyId);

	Map<String, Object> getAuctionListPage(String sort, String order, int offset, int limit, Long agencyId);

	Auction getAuction(Long auctionId);

	void editAuction(Long auctionId, Long goodsId, String auctionName, Date beginTime, Date endTime, Long qtyAuction,
			String intro);

	void deleteAuction(Long auctionId);

}
