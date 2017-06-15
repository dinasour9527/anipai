package com.anipai.customer.interfaces.auction.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AuctionDTO {
	private Long auctionId;
	private String auctionName;
	private String intro;
	private String path;
	/*以下单个拍品展示需要的信息*/
	private BigDecimal assessPrice;
	private BigDecimal expectPrice;
	private String unit;
	private Long qtyAuction;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Long remainTime;
	
	public AuctionDTO(Long auctionId, String auctionName, String intro, String path) {
		this.auctionId = auctionId;
		this.auctionName = auctionName;
		this.intro = intro;
		this.path = path;
	}
	public AuctionDTO(Long auctionId, String auctionName, String intro, String path, BigDecimal assessPrice,
			BigDecimal expectPrice, String unit, Long qtyAuction, Date endTime) {
		this.auctionId = auctionId;
		this.auctionName = auctionName;
		this.intro = intro;
		this.path = path;
		this.assessPrice = assessPrice;
		this.expectPrice = expectPrice;
		this.unit = unit;
		this.qtyAuction = qtyAuction;
		this.remainTime = (endTime.getTime()-new Date().getTime())/1000;
	}
	public Long getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(Long auctionId) {
		this.auctionId = auctionId;
	}
	public String getAuctionName() {
		return auctionName;
	}
	public void setAuctionName(String auctionName) {
		this.auctionName = auctionName;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
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
	public Long getQtyAuction() {
		return qtyAuction;
	}
	public void setQtyAuction(Long qtyAuction) {
		this.qtyAuction = qtyAuction;
	}
	public Long getRemainTime() {
		return remainTime;
	}
	public void setRemainTime(Long remainTime) {
		this.remainTime = remainTime;
	}
}
