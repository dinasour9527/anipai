package com.anipai.customer.interfaces.auction.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BidDTO {
	private Long customerId;
	private BigDecimal bidPrice;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date bidTime;
	public BidDTO(Long customerId, BigDecimal bidPrice, Date bidTime) {
		this.customerId = customerId;
		this.bidPrice = bidPrice;
		this.bidTime = bidTime;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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
}
