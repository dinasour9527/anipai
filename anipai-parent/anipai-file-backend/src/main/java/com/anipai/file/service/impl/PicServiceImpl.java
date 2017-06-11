package com.anipai.file.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import com.anipai.file.domain.GoodsPic;
import com.anipai.file.domain.Pic;
import com.anipai.file.mapper.GoodsPicMapper;
import com.anipai.file.mapper.PicMapper;
import com.anipai.file.service.PicService;

@Service
@Transactional
public class PicServiceImpl implements PicService {
	
	@Autowired
	private PicMapper picMapper;
	
	@Autowired
	private GoodsPicMapper goodsPicMapper;

	@Override
	public Long createPic(InputStream inputStream, Long goodsId) {

		try {
			FileCopyUtils.copy(inputStream,
				new FileOutputStream(new File("F:/anipai-file/goodsimg/goodsimg"+goodsId+".jpg")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String picName = "goodsimg"+goodsId+".jpg";
		String path = "/goodsimg/" + picName;
		Pic pic = new Pic(picName, path);
		picMapper.insertPic(pic);
		Long picId = pic.getPicId();
		GoodsPic goodsPic = new GoodsPic(goodsId, picId);
		goodsPicMapper.insertGoodsPic(goodsPic);
		
		return picId;
	}

	@Override
	public Pic getPicByGoodsId(Long goodsId) {
		Pic pic = picMapper.getPicByGoodsId(goodsId);
		return pic;
	}

	@Override
	public void deletePicByGoodsId(Long goodsId) {
		GoodsPic goodsPic = goodsPicMapper.findGoodsPicByGoodsId(goodsId);
		goodsPicMapper.deleteGoodsPicByGoodsId(goodsId);
		//Pic pic = picMapper.findPic(goodsPic.getPicId());
		picMapper.deletePic(goodsPic.getPicId());
		
	}

}
