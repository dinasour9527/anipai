package com.anipai.customer.interfaces.auction.dto;

import java.util.ArrayList;
import java.util.List;

import com.anipai.auction.domain.Bid;

public class BidDTOConverter {
	public static BidDTO toDTO(Bid bid) {
		return new BidDTO(bid.getCustomer().getCustomerId(), bid.getBidPrice(), bid.getBidTime());
	}
	public static List<BidDTO> toDTOList(List<Bid> bidList) {
		List<BidDTO> bidDTOList = new ArrayList<BidDTO>();
		for(Bid bid : bidList){
			bidDTOList.add(BidDTOConverter.toDTO(bid));
		}
		return bidDTOList;
	}
}
