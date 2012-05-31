/**
 * Description:
 * <br/>Copyright (C), 2001-2012, mshen
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  mshen
 * @version  1.0
 */
package auction.model;

import java.util.Date;

/**
 * @author mshen
 *
 */
public class Bid
{
	//标识属性
	private Integer id;
	//竞价的价格
	private double bidPrice;
	//竞价的日期
	private Date bidDate;
	//本次竞价所竞拍的物品
	private Item bidItem;
	//参与竞价的用户
	private AuctionUser bidUser;


	//无参数的构造器
	public Bid()
	{
	}
	//初始化全部基本属性的构造器
	public Bid(Integer id , double bidPrice , Date bidDate)
	{
		this.id = id;
		this.bidPrice = bidPrice;
		this.bidDate = bidDate;
	}

	//id属性的setter和getter方法
	public void setId(Integer id)
	{
		this.id = id;
	}
	public Integer getId()
	{
		return this.id;
	}

	//bidPrice属性的setter和getter方法
	public void setBidPrice(double bidPrice)
	{
		this.bidPrice = bidPrice;
	}
	public double getBidPrice()
	{
		return this.bidPrice;
	}

	//bidDate属性的setter和getter方法
	public void setBidDate(Date bidDate)
	{
		this.bidDate = bidDate;
	}
	public Date getBidDate()
	{
		return this.bidDate;
	}

	//bidItem属性的setter和getter方法
	public void setBidItem(Item bidItem)
	{
		this.bidItem = bidItem;
	}
	public Item getBidItem()
	{
		return this.bidItem;
	}

	//bidUser属性的setter和getter方法
	public void setBidUser(AuctionUser bidUser)
	{
		this.bidUser = bidUser;
	}
	public AuctionUser getBidUser()
	{
		return this.bidUser;
	}

	public int hashCode()
	{
		return bidUser.getUsername().hashCode()
			+ bidItem.hashCode() * 13 + (int)bidPrice * 19;
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj != null && obj.getClass() == Bid.class)
		{
			Bid bid = (Bid)obj;
			if (bid.getBidUser().getUsername().equals(bidUser.getUsername())
				&& bid.getBidItem().equals(this.getBidItem()) 
				&& bid.getBidPrice() == this.getBidPrice())
			{
				return true;
			}
		}
		return false;
	}
}
