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
import auction.action.base.BaseActionInterface;

public class MgrKindAction extends BaseActionInterface
{
	private List kinds;
	private String errMsg;

	public String execute() throws Exception
	{
		Map session = ActionContext.getContext().getSession();
		setKinds(mgr.getAllKind());
		setErrMsg(errMsg);
		return SUCCESS;
	}

	public void setKinds(List kinds)
	{
		this.kinds = kinds;
	}
	public List getKinds()
	{
		 return this.kinds;
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