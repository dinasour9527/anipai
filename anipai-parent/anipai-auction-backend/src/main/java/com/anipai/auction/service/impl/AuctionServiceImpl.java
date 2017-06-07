package com.anipai.auction.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anipai.agency.domain.Agency;
import com.anipai.auction.domain.Auction;
import com.anipai.auction.domain.Goods;
import com.anipai.auction.mapper.AuctionMapper;
import com.anipai.auction.mapper.GoodsMapper;
import com.anipai.auction.service.AuctionService;
import com.anipai.utils.Pagination;

@Service
@Transactional
public class AuctionServiceImpl implements AuctionService {
	
	@Autowired
	private AuctionMapper auctionMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;

	@Override
	public Map<String, Object> getAuctionListPage(String sort, String order, int offset, int limit, Long agencyId) {
		Map<String, Object> auctionListPage = new HashMap<String, Object>();
		Pagination pagination = new Pagination(sort, order, offset, limit);
		List<Auction> auctionList = auctionMapper.findAuctionPage(pagination, agencyId);
		int total = auctionMapper.total(agencyId);
		auctionListPage.put("list", auctionList);
		auctionListPage.put("total", total);
		return auctionListPage;
	}

	@Override
	public Long createAuction(Long goodsId, String auctionName, Date beginTime, Date endTime, Long qtyAuction,
			String intro, Long agencyId) {
		Goods goods = goodsMapper.findGoods(goodsId);
		Agency agency = new Agency(agencyId, null);
		Auction auction = new Auction(auctionName, intro, beginTime, endTime, goods, qtyAuction, 0, agency);
		auctionMapper.addAuction(auction);
		goodsMapper.updateQty(goods.getQtyAuction() + qtyAuction, goods.getQtyDeal(), goodsId);
		return auction.getAuctionId();
	}

	@Override
	public Auction getAuction(Long auctionId) {
		Auction auction = auctionMapper.findAuction(auctionId);
		return auction;
	}

	@Override
	public void editAuction(Long auctionId, Long goodsId, String auctionName, Date beginTime, Date endTime,
			Long qtyAuction, String intro) {
		Goods goods = goodsMapper.findGoods(goodsId);
		Auction oldAuction = auctionMapper.findAuction(auctionId);
		Long oldQtyAuction = oldAuction.getQtyAuction();
		Long newQtyAuction = goods.getQtyAuction() - oldQtyAuction + qtyAuction;
		goodsMapper.updateQty( newQtyAuction, new Long(0), goodsId);
		
		Auction auction = new Auction(auctionId, auctionName, intro, beginTime, endTime, null, qtyAuction, null, null);
		auctionMapper.updateAuction(auction);	
	}

	@Override
	public void deleteAuction(Long auctionId) {
		Auction auction = auctionMapper.findAuction(auctionId);
		Goods goods = goodsMapper.findGoods(auction.getGoods().getGoodsId());
		Long newQtyAuction = goods.getQtyAuction() - auction.getQtyAuction();
		goodsMapper.updateQty(newQtyAuction, new Long(0), goods.getGoodsId());
		auctionMapper.deleteAuction(auctionId);
	}

}
