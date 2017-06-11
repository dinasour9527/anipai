package com.anipai.file.service;

import java.io.InputStream;

import com.anipai.file.domain.Pic;

public interface PicService {

	Long createPic(InputStream inputStream, Long goodsId);

	Pic getPicByGoodsId(Long goodsId);

	void deletePicByGoodsId(Long goodsId);

}
