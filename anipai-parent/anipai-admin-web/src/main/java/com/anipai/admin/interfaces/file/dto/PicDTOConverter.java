package com.anipai.admin.interfaces.file.dto;

import java.util.ArrayList;
import java.util.List;

import com.anipai.file.domain.Pic;

public class PicDTOConverter {
	public static PicDTO toDTO(Pic pic) {
		return new PicDTO(pic.getPicName(), pic.getPath());
	}
	
	public static List<PicDTO> toDTOList(List<Pic> picList) {
		List<PicDTO> picDTOList = new ArrayList<PicDTO>();
		for(Pic pic : picList){
			picDTOList.add(PicDTOConverter.toDTO(pic));
		}
		return picDTOList;
	}
}
