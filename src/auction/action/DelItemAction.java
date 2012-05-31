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

import java.util.List;

import auction.action.base.BaseAction;

/**
 * @author mshen
 *
 */
public class DelItemAction extends BaseAction{
	private int id;
	private List Items;
	@Override
	public String execute() throws Exception
	{
		if(mgr.getItem(id) == null)
		{	
			addActionMessage("该商品不存在，请查看！");
			return "failure";
		}
		else
		{
			mgr.deleteItem(id);
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

	public void setItems(List items)
	{
		this.Items = items;
	}
	public List getItems()
	{
		 return this.Items;
	}
}
