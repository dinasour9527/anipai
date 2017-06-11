package com.anipai.admin.interfaces.file.dto;

public class PicDTO {
	private String picName;
	private String path;
	public PicDTO() {
	}
	public PicDTO(String picName, String path) {
		this.picName = picName;
		this.path = path;
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
