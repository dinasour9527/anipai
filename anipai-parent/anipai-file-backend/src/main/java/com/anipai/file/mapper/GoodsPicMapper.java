package com.anipai.file.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.anipai.file.domain.GoodsPic;

@Mapper
public interface GoodsPicMapper {

	@Insert("insert into goods_pic(goods_id, pic_id) values(#{goodsId}, #{picId})")
	void insertGoodsPic(GoodsPic goodsPic);

	@Delete("delete from goods_pic where goods_id=#{goodsId}")
	void deleteGoodsPicByGoodsId(Long goodsId);

	@Select("select goods_id, pic_id from goods_pic where goods_id=#{goodsId}")
	GoodsPic findGoodsPicByGoodsId(Long goodsId);
	
}
