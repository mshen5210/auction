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

import auction.action.base.BaseAction;
import auction.action.base.BaseActionInterface;

import com.opensymphony.xwork2.ActionContext;

/**
 * @author mshen
 *
 */
public class DelKindAction extends BaseAction {
	private int id;
	private List kinds;
	@Override
	public String execute() throws Exception
	{
		if(mgr.getItemsByKind(id).size() != 0)
		{	
			setKinds(mgr.getAllKind());
			addActionMessage("该种类下依然有商品正在拍卖，不能删除！");
			return "failure";
		}else{
			mgr.deleteKind(id);
			setKinds(mgr.getAllKind());
			addActionMessage("删除成功");
			return SUCCESS;
		}		
	}

	public void setId(int id)
	{
		this.id = id;
	}
	public int getId()
	{
		 return this.id;
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
