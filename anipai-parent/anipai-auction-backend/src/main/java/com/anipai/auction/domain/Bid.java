package com.anipai.auction.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.anipai.passport.domain.Customer;

public class Bid {
	private Long bidId;
	private Auction auction;
	private BigDecimal bidPrice;
	private Date bidTime;
	private Customer customer;
	public Bid() {}
	public Bid(Auction auction, BigDecimal bidPrice, Date bidTime, Customer customer) {
		this.auction = auction;
		this.bidPrice = bidPrice;
		this.bidTime = bidTime;
		this.customer = customer;
	}
	public Bid(Long bidId, Auction auction, BigDecimal bidPrice, Date bidTime, Customer customer) {
		this.bidId = bidId;
		this.auction = auction;
		this.bidPrice = bidPrice;
		this.bidTime = bidTime;
		this.customer = customer;
	}
	public Long getBidId() {
		return bidId;
	}
	public void setBidId(Long bidId) {
		this.bidId = bidId;
	}
	public Auction getAuction() {
		return auction;
	}
	public void setAuction(Auction auction) {
		this.auction = auction;
	}
	public BigDecimal getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(BigDecimal bidPrice) {
		this.bidPrice = bidPrice;
	}
	public Date getBidTime() {
		return bidTime;
	}
	public void setBidTime(Date bidTime) {
		this.bidTime = bidTime;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
