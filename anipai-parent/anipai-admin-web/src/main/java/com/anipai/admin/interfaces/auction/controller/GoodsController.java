package com.anipai.admin.interfaces.auction.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anipai.admin.interfaces.auction.dto.GoodsDTO;
import com.anipai.admin.interfaces.auction.dto.GoodsDTOConverter;
import com.anipai.admin.utils.PageModel;
import com.anipai.auction.domain.Goods;
import com.anipai.auction.service.GoodsService;
import com.anipai.system.security.CurrentUser;
import com.anipai.system.security.SysUserDetails;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	
	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping(value = "/goodsManage", method = RequestMethod.GET)
	public String goodsManage() {
		return "auction/goodsManage";
	}
	
	@RequestMapping(value = "/goodsTable", method = RequestMethod.GET)
	@ResponseBody
	public PageModel<GoodsDTO> goodsTable(@CurrentUser SysUserDetails currentUser,
			String sort, String order, int offset, int limit) {
		PageModel<GoodsDTO> pageModel = new PageModel<GoodsDTO>();
		Map<String, Object> goodsListPage = goodsService.getGoodsListPage(sort, order, offset, limit, currentUser.getAgencyId());
		@SuppressWarnings("unchecked")
		List<Goods> goodsList = (List<Goods>) goodsListPage.get("list");
		List<GoodsDTO> goodsDTOList = GoodsDTOConverter.toDTOList(goodsList);
		pageModel.setTotal((int) goodsListPage.get("total"))
			.setRows(goodsDTOList);
		return pageModel;
	}
	
	@RequestMapping(value = "/goodsEditForm/{goodsId}", method = RequestMethod.GET)
	@ResponseBody
	public GoodsDTO goodsEditForm(@PathVariable("goodsId") Long goodsId){
		Goods goods = goodsService.getGoods(goodsId);
		GoodsDTO goodsDTO = GoodsDTOConverter.toDTO(goods);
		return goodsDTO;
	}
	
	@RequestMapping(value = "/goodsCreate", method = RequestMethod.POST)
	@ResponseBody
	public int goodsCreate(@CurrentUser SysUserDetails currentUser, String goodsName, Long firstCategoryId, Long secondCategoryId,
			Long thirdCategoryId, BigDecimal assessPrice, BigDecimal expectPrice, String unit, Long qtyTotal) {
		goodsService.createGoods(goodsName, firstCategoryId, secondCategoryId, thirdCategoryId, assessPrice, expectPrice,
				unit, qtyTotal, currentUser.getAgencyId());
		return 1;
	}
	
	@RequestMapping(value = "/goodsEdit", method = RequestMethod.POST)
	@ResponseBody
	public int goodsEdit(Long goodsId, String goodsName, Long firstCategoryId, Long secondCategoryId,
			Long thirdCategoryId, BigDecimal assessPrice, BigDecimal expectPrice, String unit, Long qtyTotal) {
		goodsService.editGoods(goodsId, goodsName, firstCategoryId, secondCategoryId, thirdCategoryId,
				assessPrice, expectPrice, unit, qtyTotal);
		return 1;
	}
	
	@RequestMapping(value = "/goodsDelete", method = RequestMethod.POST)
	@ResponseBody
	public int goodsDelete(Long goodsId) {
		goodsService.deleteGoods(goodsId);
		return 1;
	}
}
