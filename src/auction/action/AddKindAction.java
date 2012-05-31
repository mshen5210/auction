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

public class AddKindAction extends BaseAction
{
	private String name;
	private String desc;
	private String vercode;

	public String execute()throws Exception
	{
		Map session = ActionContext.getContext().getSession();
		
		String ver2 = (String)session.get("rand");
		session.put("rand" , null);
//		if (vercode.equalsIgnoreCase(ver2))
//		{	
			mgr.addKind(name , desc);
			return SUCCESS;
//		}
//		else
//		{
//			addActionError("验证码不匹配,请重新输入");
//			return INPUT;
//		}
	}


	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		 return this.name;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}
	public String getDesc()
	{
		 return this.desc;
	}

	public void setVercode(String vercode)
	{
		this.vercode = vercode;
	}
	public String getVercode()
	{
		 return this.vercode;
	}
}