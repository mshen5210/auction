/**
 * Description:
 * <br/>Copyright (C), 2001-2012, mshen
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  mshen
 * @version  1.0
 */
package auction.action;

import java.util.*;
import com.opensymphony.xwork2.*;

import auction.service.AuctionManager;
import auction.exception.AuctionException;
import auction.action.base.BaseActionInterface;
import auction.model.*;
import auction.business.*;

public class ViewDetailAction extends BaseActionInterface
{
	//封装用户请求参数的属性
	private int itemId;
	private ItemBean item;
	//封装系统错误提示的属性
	private String errMsg;
	//处理用户请求
	public String execute()throws Exception
	{
		if (itemId <= 0)
		{
			setErrMsg("您选择物品种类不是一个有效的物品种类！");
			return ERROR;
		}
		else
		{
			setItem(mgr.getItem(itemId));
			return SUCCESS;
		}
	}

	public void setItemId(int itemId)
	{
		this.itemId = itemId;
	}
	public int getItemId()
	{
		 return this.itemId;
	}

	public void setItem(ItemBean item)
	{
		this.item = item;
	}
	public ItemBean getItem()
	{
		 return this.item;
	}

	public void setErrMsg(String errMsg)
	{
		this.errMsg = errMsg;
	}
	public String getErrMsg()
	{
		 return this.errMsg;
	}

}