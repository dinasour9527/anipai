package com.anipai.auction.domain;

import java.math.BigDecimal;

import com.anipai.agency.domain.Agency;

public class Goods {
	private Long goodsId;
	private String goodsName;
	private Category firstCategory;
	private Category secondCategory;
	private Category thirdCategory;
	/*估价*/
	private BigDecimal assessPrice;
	/*期望价格*/
	private BigDecimal expectPrice;
	/*单位*/
	private String unit;
	/*总量*/
	private Long qtyTotal;
	/*上拍数量*/
	private Long qtyAuction;
	/*成交数量*/
	private Long qtyDeal;
	private Agency agency;
	
	public Goods() {}
	public Goods(String goodsName, Category firstCategory, Category secondCategory, Category thirdCategory,
			BigDecimal assessPrice, BigDecimal expectPrice, String unit, Long qtyTotal, Long qtyAuction, Long qtyDeal,
			Agency agency) {
		this.goodsName = goodsName;
		this.firstCategory = firstCategory;
		this.secondCategory = secondCategory;
		this.thirdCategory = thirdCategory;
		this.assessPrice = assessPrice;
		this.expectPrice = expectPrice;
		this.unit = unit;
		this.qtyTotal = qtyTotal;
		this.qtyAuction = qtyAuction;
		this.qtyDeal = qtyDeal;
		this.agency = agency;
	}
	public Goods(Long goodsId, String goodsName, Category firstCategory, Category secondCategory,
			Category thirdCategory, BigDecimal assessPrice, BigDecimal expectPrice, String unit, Long qtyTotal,
			Long qtyAuction, Long qtyDeal, Agency agency) {
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.firstCategory = firstCategory;
		this.secondCategory = secondCategory;
		this.thirdCategory = thirdCategory;
		this.assessPrice = assessPrice;
		this.expectPrice = expectPrice;
		this.unit = unit;
		this.qtyTotal = qtyTotal;
		this.qtyAuction = qtyAuction;
		this.qtyDeal = qtyDeal;
		this.agency = agency;
	}
	public Agency getAgency() {
		return agency;
	}
	public void setAgency(Agency agency) {
		this.agency = agency;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Category getFirstCategory() {
		return firstCategory;
	}
	public void setFirstCategory(Category firstCategory) {
		this.firstCategory = firstCategory;
	}
	public Category getSecondCategory() {
		return secondCategory;
	}
	public void setSecondCategory(Category secondCategory) {
		this.secondCategory = secondCategory;
	}
	public Category getThirdCategory() {
		return thirdCategory;
	}
	public void setThirdCategory(Category thirdCategory) {
		this.thirdCategory = thirdCategory;
	}
	public BigDecimal getAssessPrice() {
		return assessPrice;
	}
	public void setAssessPrice(BigDecimal assessPrice) {
		this.assessPrice = assessPrice;
	}
	public BigDecimal getExpectPrice() {
		return expectPrice;
	}
	public void setExpectPrice(BigDecimal expectPrice) {
		this.expectPrice = expectPrice;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Long getQtyTotal() {
		return qtyTotal;
	}
	public void setQtyTotal(Long qtyTotal) {
		this.qtyTotal = qtyTotal;
	}
	public Long getQtyAuction() {
		return qtyAuction;
	}
	public void setQtyAuction(Long qtyAuction) {
		this.qtyAuction = qtyAuction;
	}
	public Long getQtyDeal() {
		return qtyDeal;
	}
	public void setQtyDeal(Long qtyDeal) {
		this.qtyDeal = qtyDeal;
	}

}
