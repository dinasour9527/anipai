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

import com.anipai.auction.domain.Auction;
import com.anipai.utils.Pagination;

@Mapper
public interface AuctionMapper {

	@Insert("insert into auction(auction_name, intro, begin_time, end_time, goods_id, qty_auction, state, agency_id) "
			+ "values (#{auctionName}, #{intro}, #{beginTime}, #{endTime}, #{goods.goodsId}, #{qtyAuction}, "
			+ "#{state}, #{agency.agencyId})")
	@Options(useGeneratedKeys = true, keyProperty = "auctionId", keyColumn = "auction_id")
	void addAuction(Auction auction);

	@SelectProvider(type = AuctionMapperProvider.class, method = "findAuctionPage")
	@Results({
		@Result(column = "goods_id", property = "goods",
			one = @One(select = "com.anipai.auction.mapper.GoodsMapper.findGoods"))
	})
	List<Auction> findAuctionPage(@Param("page") Pagination pagination, @Param("agencyId") Long agencyId);

	@SelectProvider(type = AuctionMapperProvider.class, method = "total")
	int total(@Param("agencyId") Long agencyId);

	@Select("select auction_id, auction_name, intro, begin_time, end_time, goods_id, qty_auction, state "
			+ "from auction where auction_id=#{auctionId}")
	@Results({
		@Result(column = "goods_id", property = "goods",
			one = @One(select = "com.anipai.auction.mapper.GoodsMapper.findGoods"))
	})
	Auction findAuction(Long auctionId);

	@Update("update auction set auction_name=#{auctionName}, intro=#{intro}, begin_time=#{beginTime}, "
			+ "end_time=#{endTime}, qty_auction=#{qtyAuction} where auction_id=#{auctionId}")
	void updateAuction(Auction auction);

	@Delete("delete from auction where auction_id=#{auctionId}")
	void deleteAuction(Long auctionId);

}
