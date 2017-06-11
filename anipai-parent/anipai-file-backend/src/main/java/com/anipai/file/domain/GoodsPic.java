package com.anipai.file.domain;

public class GoodsPic {
	
	private Long id;
	private Long goodsId;
	private Long picId;
	public GoodsPic() {
	}
	public GoodsPic(Long goodsId, Long picId) {
		this.goodsId = goodsId;
		this.picId = picId;
	}
	public GoodsPic(Long id, Long goodsId, Long picId) {
		this.id = id;
		this.goodsId = goodsId;
		this.picId = picId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public Long getPicId() {
		return picId;
	}
	public void setPicId(Long picId) {
		this.picId = picId;
	}
}
