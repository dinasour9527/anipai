package com.anipai.file.domain;

public class Pic {

	private Long picId;
	private String picName;
	private String path;
	public Pic() {
	}
	public Pic(String picName, String path) {
		this.picName = picName;
		this.path = path;
	}
	public Pic(Long picId, String picName, String path) {
		this.picId = picId;
		this.picName = picName;
		this.path = path;
	}
	public Long getPicId() {
		return picId;
	}
	public void setPicId(Long picId) {
		this.picId = picId;
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
