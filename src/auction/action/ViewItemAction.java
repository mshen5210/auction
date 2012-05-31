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
import auction.model.*;

public class ViewItemAction extends BaseAction
{
	private int kindId;
	private String kind;
	private List items; 

	public String execute()throws Exception
	{
		if (kindId <= 0)
		{
			addActionError("您必须选择有效的种类");
			return ERROR;
		}
		else
		{
			setKind(mgr.getKind(kindId));
			setItems(mgr.getItemsByKind(kindId));
			return SUCCESS;
		}
	}

	public void setKindId(int kindId)
	{
		this.kindId = kindId;
	}
	public int getKindId()
	{
		 return this.kindId;
	}

	public void setKind(String kind)
	{
		this.kind = kind;
	}
	public String getKind()
	{
		 return this.kind;
	}

	public void setItems(List items)
	{
		this.items = items;
	}
	public List getItems()
	{
		 return this.items;
	}
}