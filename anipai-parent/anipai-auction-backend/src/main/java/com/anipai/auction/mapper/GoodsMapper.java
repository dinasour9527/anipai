package com.anipai.auction.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.anipai.auction.domain.Goods;
import com.anipai.utils.Pagination;

@Mapper
public interface GoodsMapper {

	@SelectProvider(type = GoodsMapperProvider.class, method = "total")
	int total(@Param("agencyId") Long agencyId);

	@SelectProvider(type = GoodsMapperProvider.class, method = "findGoodsPage")
	@Results({
		@Result(column = "first_category_id", property = "firstCategory",
			one = @One(select = "com.anipai.auction.mapper.CategoryMapper.findCategory")),
		@Result(column = "second_category_id", property = "secondCategory",
			one = @One(select = "com.anipai.auction.mapper.CategoryMapper.findCategory")),
		@Result(column = "third_category_id", property = "thirdCategory",
			one = @One(select = "com.anipai.auction.mapper.CategoryMapper.findCategory"))
	})
	List<Goods> findGoodsPage(@Param("page") Pagination pagination, @Param("agencyId") Long agencyId);

	@Select("select goods_id, goods_name, first_category_id, second_category_id, third_category_id, "
			+ "assess_price, expect_price, unit, qty_total, qty_auction, qty_deal from goods "
			+ "where goods_id=#{goodsId} ")
	@Results({
		@Result(column = "first_category_id", property = "firstCategory",
			one = @One(select = "com.anipai.auction.mapper.CategoryMapper.findCategory")),
		@Result(column = "second_category_id", property = "secondCategory",
			one = @One(select = "com.anipai.auction.mapper.CategoryMapper.findCategory")),
		@Result(column = "third_category_id", property = "thirdCategory",
			one = @One(select = "com.anipai.auction.mapper.CategoryMapper.findCategory"))
	})
	Goods findGoods(Long goodsId);

	@Insert("insert into goods(goods_name, first_category_id, second_category_id, third_category_id, assess_price, "
			+ "expect_price, unit, qty_total, agency_id) "
			+ "values (#{goodsName}, #{firstCategory.categoryId}, #{secondCategory.categoryId}, #{thirdCategory.categoryId}, "
			+ "#{assessPrice}, #{expectPrice}, #{unit}, #{qtyTotal}, #{agency.agencyId})")
	@Options(useGeneratedKeys = true, keyProperty = "goodsId", keyColumn = "goods_id")
	void addGoods(Goods goods);

	@Update("update goods set goods_name=#{goodsName}, first_category_id=#{firstCategory.categoryId}, second_category_id=#{secondCategory.categoryId}, "
			+ "third_category_id=#{thirdCategory.categoryId}, assess_price=#{assessPrice}, expect_price=#{expectPrice}, unit=#{unit}, "
			+ "qty_total=#{qtyTotal} "
			+ "where goods_id=#{goodsId}")
	void updateGoods(Goods goods);

	@Update("update goods set qty_auction=#{qtyAuction}, qty_deal=#{qtyDeal} where goods_id=#{goodsId}")
	void updateQty(@Param("qtyAuction") Long qtyAuction, @Param("qtyDeal") Long qtyDeal, @Param("goodsId") Long goodsId);

	@Delete("delete from goods where goods_id=#{goodsId}")
	void deleteGoods(Long goodsId);

}
