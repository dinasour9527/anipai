package com.anipai.file.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.anipai.file.domain.Pic;

@Mapper
public interface PicMapper {

	@Insert("insert into pic(pic_name, path) values (#{picName}, #{path})")
	@Options(useGeneratedKeys = true, keyProperty = "picId", keyColumn = "pic_id")
	void insertPic(Pic pic);

	@Select("select p.pic_id, p.pic_name, p.path from pic p, goods_pic gp "
			+ "where p.pic_id=gp.pic_id and gp.goods_id=#{goodsId}")
	Pic getPicByGoodsId(Long goodsId);

	@Select("select pic_id, pic_name, path from pic where pic_id=#{picId}")
	Pic findPic(Long picId);

	@Delete("delete from pic where pic_id=#{picId}")
	void deletePic(Long picId);

}
