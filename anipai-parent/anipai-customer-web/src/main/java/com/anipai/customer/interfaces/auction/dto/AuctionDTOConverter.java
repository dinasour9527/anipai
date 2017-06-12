package com.anipai.customer.interfaces.auction.dto;

import java.util.ArrayList;
import java.util.List;

import com.anipai.auction.domain.Auction;
import com.anipai.auction.domain.Goods;

public class AuctionDTOConverter {
	public static AuctionDTO toDTO(Auction auction) {
		return new AuctionDTO(auction.getAuctionId(), auction.getAuctionName(), auction.getIntro(),
				auction.getPic().getPath());
	}
	
	public static AuctionDTO toDTOWithGoods(Auction auction, Goods goods){
		return new AuctionDTO(auction.getAuctionId(), auction.getAuctionName(), auction.getIntro(),
				auction.getPic().getPath(), goods.getAssessPrice(), goods.getExpectPrice(), goods.getUnit(),
				goods.getQtyAuction());
	}
	
	public static List<AuctionDTO> toDTOList(List<Auction> auctionList) {
		List<AuctionDTO> auctionDTOList = new ArrayList<AuctionDTO>();
		for(Auction auction : auctionList){
			auctionDTOList.add(AuctionDTOConverter.toDTO(auction));
		}
		return auctionDTOList;
	}
}
