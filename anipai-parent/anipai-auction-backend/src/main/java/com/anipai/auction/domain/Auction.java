package com.anipai.auction.domain;

import java.util.Date;

import com.anipai.agency.domain.Agency;
import com.anipai.file.domain.Pic;

public class Auction {
	private Long auctionId;
	private String auctionName;
	private String intro;
	private Date beginTime;
	private Date endTime;
	private Goods goods;
	private Long qtyAuction;
	private Integer state;
	private Agency agency;
	private Pic pic;
	
	public Auction() {}
	public Auction(String auctionName, String intro, Date beginTime, Date endTime, Goods goods, Long qtyAuction,
			Integer state, Agency agency) {
		this.auctionName = auctionName;
		this.intro = intro;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.goods = goods;
		this.qtyAuction = qtyAuction;
		this.state = state;
		this.agency = agency;
	}
	public Auction(Long auctionId, String auctionName, String intro, Date beginTime, Date endTime, Goods goods,
			Long qtyAuction, Integer state, Agency agency) {
		this.auctionId = auctionId;
		this.auctionName = auctionName;
		this.intro = intro;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.goods = goods;
		this.qtyAuction = qtyAuction;
		this.state = state;
		this.agency = agency;
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
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Long getQtyAuction() {
		return qtyAuction;
	}
	public void setQtyAuction(Long qtyAuction) {
		this.qtyAuction = qtyAuction;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Agency getAgency() {
		return agency;
	}
	public void setAgency(Agency agency) {
		this.agency = agency;
	}
	public Pic getPic() {
		return pic;
	}
	public void setPic(Pic pic) {
		this.pic = pic;
	}
}
