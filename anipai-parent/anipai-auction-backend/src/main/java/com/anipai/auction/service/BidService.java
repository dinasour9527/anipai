package com.anipai.auction.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public interface BidService {

	Long createBid(Long auctionId, BigDecimal bidPrice, Date bidTime, Long customerId);

	Map<String, Object> getBidListPage(String sort, String order, int offset, int limit);

}
