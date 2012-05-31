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
import com.opensymphony.xwork2.ActionContext;

import auction.service.AuctionManager;
import auction.exception.AuctionException;
import auction.action.base.BaseAction;

public class ViewBidAction extends BaseAction
{
	private List bids;
	
	public String execute()throws Exception
	{
		Map session = ActionContext.getContext().getSession();
		Integer userId = (Integer)session.get("userId");
		setBids(mgr.getBidByUser(userId));
		return SUCCESS;
	}

	public void setBids(List bids)
	{
		this.bids = bids;
	}

	public List getBids()
	{
		 return this.bids;
	}

}