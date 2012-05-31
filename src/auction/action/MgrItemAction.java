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

public class MgrItemAction extends BaseActionInterface
{
	private List items;
	private List kinds;

	public String execute() throws Exception
	{
		Map session = ActionContext.getContext().getSession();
		Integer userId = (Integer)session.get("userId");
		setItems(mgr.getItemsByOwner(userId));
		setKinds(mgr.getAllKind());
		return SUCCESS;
	}

	public void setItems(List items)
	{
		this.items = items;
	}
	public List getItems()
	{
		 return this.items;
	}

	public void setKinds(List kinds)
	{
		this.kinds = kinds;
	}
	public List getKinds()
	{
		 return this.kinds;
	}
}