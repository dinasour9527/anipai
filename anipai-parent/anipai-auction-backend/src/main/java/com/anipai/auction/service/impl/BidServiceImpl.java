package com.anipai.auction.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anipai.auction.domain.Auction;
import com.anipai.auction.domain.Bid;
import com.anipai.auction.mapper.BidMapper;
import com.anipai.auction.service.BidService;
import com.anipai.passport.domain.Customer;
import com.anipai.utils.Pagination;

@Service
@Transactional
public class BidServiceImpl implements BidService {
	
	@Autowired
	private BidMapper bidMapper;

	@Override
	public Long createBid(Long auctionId, BigDecimal bidPrice, Date bidTime, Long customerId) {
		Auction auction = new Auction();
		auction.setAuctionId(auctionId);
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		
		Bid bid = new Bid(auction, bidPrice, bidTime, customer);
		bidMapper.insertBid(bid);
		return bid.getBidId();
	}

	@Override
	public Map<String, Object> getBidListPage(String sort, String order, int offset, int limit) {
		Map<String, Object> bidListPage = new HashMap<String, Object>();
		Pagination pagination = new Pagination(sort, order, offset, limit);
		List<Bid> roleList = bidMapper.findRolePage(pagination);
		//int total = roleMapper.total(agencyId);
		bidListPage.put("list", roleList);
		bidListPage.put("total", 10);
		return bidListPage;
	}

}
