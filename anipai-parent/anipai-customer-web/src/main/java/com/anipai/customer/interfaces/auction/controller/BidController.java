package com.anipai.customer.interfaces.auction.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anipai.auction.domain.Bid;
import com.anipai.auction.service.BidService;
import com.anipai.customer.interfaces.auction.dto.BidDTO;
import com.anipai.customer.interfaces.auction.dto.BidDTOConverter;
import com.anipai.passport.security.CurrentCustomer;
import com.anipai.passport.security.CustomerDetails;

@Controller
@RequestMapping("/bid")
public class BidController {
	
	@Autowired
	private BidService bidService;
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public int bid(@CurrentCustomer CustomerDetails currentCustomer, BigDecimal bidPrice, Long auctionId) {
		bidService.createBid(auctionId, bidPrice, new Date(), currentCustomer.getCustomerId());
		return 1;
	}
	
	@RequestMapping(value = "/bidRecord", method = RequestMethod.GET)
	@ResponseBody
	public List<BidDTO> bidRecord(String sort, String order) {
		Map<String, Object> bidListPage = bidService.getBidListPage(sort, order, 0 , 10);
		@SuppressWarnings("unchecked")
		List<Bid> bidList = (List<Bid>) bidListPage.get("list");
		List<BidDTO> bidDTOList = BidDTOConverter.toDTOList(bidList);
		return bidDTOList;
	}
}
