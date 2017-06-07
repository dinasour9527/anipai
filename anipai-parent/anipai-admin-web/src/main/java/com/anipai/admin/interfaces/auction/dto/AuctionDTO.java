package com.anipai.admin.interfaces.auction.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AuctionDTO {
	private Long auctionId;
	private Long goodsId;
	private String auctionName;
	private String intro;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date beginTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date endTime;
	private Long qtyAuction;
	private Long qtyGoodsResidue;
	private Integer state;
	
	public AuctionDTO(Long auctionId, Long goodsId, String auctionName, String intro, Date beginTime, Date endTime,
			Long qtyAuction, Long qtyGoodsResidue, Integer state) {
		this.auctionId = auctionId;
		this.goodsId = goodsId;
		this.auctionName = auctionName;
		this.intro = intro;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.qtyAuction = qtyAuction;
		this.qtyGoodsResidue = qtyGoodsResidue;
		this.state = state;
	}
	
	public Long getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(Long auctionId) {
		this.auctionId = auctionId;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
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
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Long getQtyAuction() {
		return qtyAuction;
	}
	public void setQtyAuction(Long qtyAuction) {
		this.qtyAuction = qtyAuction;
	}
	public Long getQtyGoodsResidue() {
		return qtyGoodsResidue;
	}
	public void setQtyGoodsResidue(Long qtyGoodsResidue) {
		this.qtyGoodsResidue = qtyGoodsResidue;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
