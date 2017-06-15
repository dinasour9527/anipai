package com.anipai.auction.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.anipai.auction.domain.Bid;
import com.anipai.utils.Pagination;

@Mapper
public interface BidMapper {

	@Insert("insert into bid(auction_id, bid_price, bid_time, customer_id) "
			+ "values(#{auction.auctionId}, #{bidPrice}, #{bidTime}, #{customer.customerId})")
	@Options(useGeneratedKeys = true, keyProperty = "bidId", keyColumn = "bid_id")
	void insertBid(Bid bid);

	@Select("select bid_id, auction_id, bid_price, bid_time, customer_id "
			+ "from bid "
			+ "order by ${sort} ${order} "
			+ "limit #{offset}, #{limit}")
	@Results({
		@Result(column = "customer_id", property = "customer.customerId")
	})
	List<Bid> findRolePage(Pagination pagination);

}
