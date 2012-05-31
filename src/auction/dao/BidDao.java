/**
 * Description:
 * <br/>Copyright (C), 2001-2012, mshen
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  mshen
 * @version  1.0
 */
package auction.dao;

import java.util.List;

import auction.model.*;
import auction.business.*;

public interface BidDao  
{
	/**
	 * 根据主键查找竞价记录
	 * @param bidId 竞价id;
	 * @return id对应的竞价记录
	 */
	Bid get(Integer bidId);

	/**
	 * 保存竞价记录
	 * @param bid 需要保存的竞价记录
	 */     
	void save(Bid bid);

	/**
	 * 修改竞价记录
	 * @param bid 需要修改的竞价记录
	 */
	void update(Bid bid);

	/**
	 * 删除竞价记录
	 * @param id 需要删除的竞价id
	 */
	void delete(Integer id);

	/**
	 * 删除竞价
	 * @param bid 需要删除的竞价
	 */
	void delete(Bid bid);

	/**
	 * 根据用户查找竞价
	 * @param id 用户id
	 * @return 用户对应的全部
	 * @return 用户对应的全部竞价
	 */
	List<Bid> findByUser(Integer userId);

	/**
	 * 根据物品id，以及出价查询用户
	 * @param itemId 物品id;
	 * @param price 竞价的价格
	 * @return 对指定物品、指定竞价对应的用户
	 */
	AuctionUser findUserByItemAndPrice(Integer itemId , Double price);
}
