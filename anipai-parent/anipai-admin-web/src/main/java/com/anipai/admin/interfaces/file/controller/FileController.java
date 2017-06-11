package com.anipai.admin.interfaces.file.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.anipai.admin.interfaces.file.dto.PicDTO;
import com.anipai.admin.interfaces.file.dto.PicDTOConverter;
import com.anipai.file.domain.Pic;
import com.anipai.file.service.PicService;

@Controller
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	private PicService picService;
	
	@RequestMapping(value = "/goodsPicUpload", method = RequestMethod.POST)
	@ResponseBody
	public int goodsPicUpload(@RequestParam("pic") MultipartFile pic, Long goodsId) {
		InputStream inputStream = null;
		try {
			inputStream = pic.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(inputStream != null){
			picService.createPic(inputStream, goodsId);
		}
		return 1;
	}
	
	@RequestMapping(value = "/getPic/{goodsId}", method = RequestMethod.GET)
	@ResponseBody
	public PicDTO getPic(@PathVariable("goodsId") Long goodsId) {
		Pic pic = picService.getPicByGoodsId(goodsId);
		if(pic == null) {
			return null;
		} else {
			PicDTO dto = PicDTOConverter.toDTO(pic);
			return dto;
		}
	}
	
	@RequestMapping(value = "/deletePic/{goodsId}", method = RequestMethod.POST)
	@ResponseBody
	public int deletePic(@PathVariable("goodsId") Long goodsId) {
		picService.deletePicByGoodsId(goodsId);
		return 1;
	}
}
