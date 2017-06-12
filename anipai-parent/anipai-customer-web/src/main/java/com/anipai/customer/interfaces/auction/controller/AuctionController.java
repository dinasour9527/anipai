package com.anipai.customer.interfaces.auction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anipai.auction.domain.Auction;
import com.anipai.auction.domain.Goods;
import com.anipai.auction.service.AuctionService;
import com.anipai.auction.service.GoodsService;
import com.anipai.customer.interfaces.auction.dto.AuctionDTO;
import com.anipai.customer.interfaces.auction.dto.AuctionDTOConverter;
import com.anipai.file.domain.Pic;
import com.anipai.file.service.PicService;

@Controller
@RequestMapping("/auction")
public class AuctionController {
	
	@Autowired
	private AuctionService auctionService;
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private PicService picService;
	
	@RequestMapping(value = "/beginAucList/{thirdCategoryId}", method=RequestMethod.GET)
	@ResponseBody
	public List<AuctionDTO> getBeginAucList(@PathVariable Long thirdCategoryId) {
		List<Auction> auctionList = auctionService.getBeginAuction(thirdCategoryId, new Long(1));
		List<AuctionDTO> dtoList = AuctionDTOConverter.toDTOList(auctionList);
		return dtoList;
	}
	
	@RequestMapping(value = "/doAuction/{auctionId}", method=RequestMethod.GET)
	public String doAuction(Model model, @PathVariable("auctionId") Long auctionId){
		
		Auction auction = auctionService.getAuction(auctionId);
		Goods goods = goodsService.getGoods(auction.getGoods().getGoodsId());
		Pic pic = picService.getPicByGoodsId(goods.getGoodsId());
		auction.setPic(pic);
		AuctionDTO auctionDTO = AuctionDTOConverter.toDTOWithGoods(auction, goods);
		model.addAttribute("auction", auctionDTO);
		return "auction/doAuction";
	}
}
