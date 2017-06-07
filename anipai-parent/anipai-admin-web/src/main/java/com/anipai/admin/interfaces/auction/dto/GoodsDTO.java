package com.anipai.admin.interfaces.auction.dto;

import java.math.BigDecimal;

public class GoodsDTO {
	private Long goodsId;
	private String goodsName;
	private Long firstCategoryId;
	private String firstCategoryName;
	private Long secondCategoryId;
	private String secondCategoryName;
	private Long thirdCategoryId;
	private String thirdCategoryName;
	private BigDecimal assessPrice;
	private BigDecimal expectPrice;
	private String unit;
	private Long qtyTotal;
	private Long qtyAuction;
	private Long qtyDeal;

	public GoodsDTO(Long goodsId, String goodsName, Long firstCategoryId, String firstCategoryName,
			Long secondCategoryId, String secondCategoryName, Long thirdCategoryId, String thirdCategoryName,
			BigDecimal assessPrice, BigDecimal expectPrice, String unit, Long qtyTotal, Long qtyAuction, Long qtyDeal) {
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.firstCategoryId = firstCategoryId;
		this.firstCategoryName = firstCategoryName;
		this.secondCategoryId = secondCategoryId;
		this.secondCategoryName = secondCategoryName;
		this.thirdCategoryId = thirdCategoryId;
		this.thirdCategoryName = thirdCategoryName;
		this.assessPrice = assessPrice;
		this.expectPrice = expectPrice;
		this.unit = unit;
		this.qtyTotal = qtyTotal;
		this.qtyAuction = qtyAuction;
		this.qtyDeal = qtyDeal;
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

	public Long getFirstCategoryId() {
		return firstCategoryId;
	}

	public void setFirstCategoryId(Long firstCategoryId) {
		this.firstCategoryId = firstCategoryId;
	}

	public String getFirstCategoryName() {
		return firstCategoryName;
	}

	public void setFirstCategoryName(String firstCategoryName) {
		this.firstCategoryName = firstCategoryName;
	}

	public Long getSecondCategoryId() {
		return secondCategoryId;
	}

	public void setSecondCategoryId(Long secondCategoryId) {
		this.secondCategoryId = secondCategoryId;
	}

	public String getSecondCategoryName() {
		return secondCategoryName;
	}

	public void setSecondCategoryName(String secondCategoryName) {
		this.secondCategoryName = secondCategoryName;
	}

	public Long getThirdCategoryId() {
		return thirdCategoryId;
	}

	public void setThirdCategoryId(Long thirdCategoryId) {
		this.thirdCategoryId = thirdCategoryId;
	}

	public String getThirdCategoryName() {
		return thirdCategoryName;
	}

	public void setThirdCategoryName(String thirdCategoryName) {
		this.thirdCategoryName = thirdCategoryName;
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
