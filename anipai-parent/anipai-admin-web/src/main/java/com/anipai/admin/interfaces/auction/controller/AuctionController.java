package com.anipai.admin.interfaces.auction.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anipai.admin.interfaces.auction.dto.AuctionDTO;
import com.anipai.admin.interfaces.auction.dto.AuctionDTOConverter;
import com.anipai.admin.interfaces.auction.dto.GoodsDTO;
import com.anipai.admin.interfaces.auction.dto.GoodsDTOConverter;
import com.anipai.admin.utils.PageModel;
import com.anipai.auction.domain.Auction;
import com.anipai.auction.domain.Goods;
import com.anipai.auction.service.AuctionService;
import com.anipai.auction.service.GoodsService;
import com.anipai.system.security.CurrentUser;
import com.anipai.system.security.SysUserDetails;

@Controller
@RequestMapping("/auction")
public class AuctionController {
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private AuctionService auctionService;
	
	@RequestMapping(value = "/auctionManage", method = RequestMethod.GET)
	public String auctionManage() {
		return "auction/auctionManage";
	}
	
	@RequestMapping(value = "/auctionManage/{goodsId}", method = RequestMethod.GET)
	public String auctionManageUpAuction(Model model, @PathVariable("goodsId") Long goodsId) {
		Goods goods = goodsService.getGoods(goodsId);
		GoodsDTO goodsDTO = GoodsDTOConverter.toDTO(goods);
		model.addAttribute("goods", goodsDTO);
		model.addAttribute("createFlag", 1);
		return "auction/auctionManage";
	}
	
	@RequestMapping(value = "/auctionTable", method = RequestMethod.GET)
	@ResponseBody
	public PageModel<AuctionDTO> auctionTable(@CurrentUser SysUserDetails currentUser,
			String sort, String order, int offset, int limit) {
		PageModel<AuctionDTO> pageModel = new PageModel<AuctionDTO>();
		Map<String, Object> auctionListPage = auctionService.getAuctionListPage(sort, order, offset, limit, currentUser.getAgencyId());
		@SuppressWarnings("unchecked")
		List<Auction> auctionList = (List<Auction>) auctionListPage.get("list");
		List<AuctionDTO> auctionDTOList = AuctionDTOConverter.toDTOList(auctionList);
		pageModel.setRows(auctionDTOList).setTotal((int) auctionListPage.get("total"));
		return pageModel;
	}
	
	@RequestMapping(value = "/auctionCreate", method = RequestMethod.POST)
	@ResponseBody
	public int auctionCreate(@CurrentUser SysUserDetails currentUser, Long goodsId, String auctionName,
			Date beginTime, Date endTime, Long qtyAuction, String intro) {
		auctionService.createAuction(goodsId, auctionName, beginTime, endTime, qtyAuction, intro, currentUser.getAgencyId());
		return 1;
	}
	
	@RequestMapping(value = "/auctionEdit", method = RequestMethod.POST)
	@ResponseBody
	public int auctionEdit(Long auctionId, Long goodsId, String auctionName, Date beginTime, Date endTime,
			Long qtyAuction, String intro) {
		auctionService.editAuction(auctionId, goodsId, auctionName, beginTime, endTime, qtyAuction, intro);
		return 1;
	}
	
	@RequestMapping(value = "/auctionEditForm/{auctionId}", method = RequestMethod.GET)
	@ResponseBody
	public AuctionDTO auctionEditForm(@PathVariable("auctionId") Long auctionId) {
		Auction auction = auctionService.getAuction(auctionId);
		Goods goods = auction.getGoods();
		AuctionDTO auctionDTO = AuctionDTOConverter.toDTOWithQtyGoodsResidue(auction, goods.getQtyTotal()-goods.getQtyAuction());
		return auctionDTO;
	}
	
	@RequestMapping(value = "/auctionDelete", method = RequestMethod.POST)
	@ResponseBody
	public int auctionDelete(Long auctionId) {
		auctionService.deleteAuction(auctionId);
		return 1;
	}
	
	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
