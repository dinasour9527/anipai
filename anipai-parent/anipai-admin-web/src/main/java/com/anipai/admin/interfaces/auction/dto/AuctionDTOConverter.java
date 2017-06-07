package com.anipai.admin.interfaces.auction.dto;

import java.util.ArrayList;
import java.util.List;

import com.anipai.auction.domain.Auction;

public class AuctionDTOConverter {
	public static AuctionDTO toDTO(Auction auction) {
		return new AuctionDTO(auction.getAuctionId(), auction.getGoods().getGoodsId(), auction.getAuctionName(), auction.getIntro(),
			auction.getBeginTime(), auction.getEndTime(), auction.getQtyAuction(), new Long(0), auction.getState());
	}
	
	public static AuctionDTO toDTOWithQtyGoodsResidue(Auction auction, Long qtyGoodsResidue) {
		return new AuctionDTO(auction.getAuctionId(), auction.getGoods().getGoodsId(), auction.getAuctionName(), auction.getIntro(),
				auction.getBeginTime(), auction.getEndTime(), auction.getQtyAuction(), qtyGoodsResidue, auction.getState());
	}
	
	public static List<AuctionDTO> toDTOList(List<Auction> auctionList) {
		List<AuctionDTO> auctionDTOList = new ArrayList<AuctionDTO>();
		for(Auction auction : auctionList){
			auctionDTOList.add(AuctionDTOConverter.toDTO(auction));
		}
		return auctionDTOList;
	}
}
