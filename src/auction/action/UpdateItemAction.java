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
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import auction.action.base.BaseAction;
import auction.business.*;

/**
 * @author mshen
 *
 */
public class UpdateItemAction extends BaseAction{
	private int id;
	private String itemName;
	private String itemDesc;
	private String itemRemark;
	private double itemInitPrice;
	private int avail;
	private int itemKind;
	private String vercode;
	private List kinds;
	private ItemBean itemBean;
	
	public String proUpdateItem() throws Exception
	{
		if(mgr.getItem(id)== null)
		{
			addActionMessage("该物品不存在，请再次检查！");
			return "failure";
		}else{
			Map session = ActionContext.getContext().getSession();
			Integer userId = (Integer)session.get("userId");
			setKinds(mgr.getAllKind());
			setItemBean(mgr.getItem(id));
			return SUCCESS;
		}
	}
	
	
	public String execute() throws Exception
	{
		if(mgr.getItem(id)== null)
		{
			addActionMessage("该物品不存在，请再次检查！");
			return "failure";
		}else{
			Map session = ActionContext.getContext().getSession();
			Integer userId = (Integer)session.get("userId");
			//根据用户选择有效时间选项，指定实际的有效时间
			switch(avail)
			{
				case 6 :
					avail = 7;
					break;
				case 7 :
					avail = 30;
					break;
				case 8 :
					avail = 365;
					break;
			}
			
			//添加物品
			mgr.updateItem(id,itemName , itemDesc , itemRemark , itemInitPrice ,avail , itemKind, userId);
			//将收集用户输入信息的表单域清空
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
	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}
	public String getItemName()
	{
		 return this.itemName;
	}

	public void setItemDesc(String itemDesc)
	{
		this.itemDesc = itemDesc;
	}
	public String getItemDesc()
	{
		 return this.itemDesc;
	}

	public void setItemRemark(String itemRemark)
	{
		this.itemRemark = itemRemark;
	}
	public String getItemRemark()
	{
		 return this.itemRemark;
	}

	public void setItemInitPrice(double itemInitPrice)
	{
		this.itemInitPrice = itemInitPrice;
	}
	public double getItemInitPrice()
	{
		 return this.itemInitPrice;
	}

	public void setAvail(int avail)
	{
		this.avail = avail;
	}
	public int getAvail()
	{
		 return this.avail;
	}

	public void setItemKind(int itemKind)
	{
		this.itemKind = itemKind;
	}
	public int getItemKind()
	{
		 return this.itemKind;
	}

	public void setVercode(String vercode)
	{
		this.vercode = vercode;
	}
	public String getVercode()
	{
		 return this.vercode;
	}
	public void setKinds(List kinds)
	{
		this.kinds = kinds;
	}
	public List getKinds()
	{
		 return this.kinds;
	}
	public void setItemBean(ItemBean itemBean)
	{
		this.itemBean = itemBean;
	}
	public ItemBean getItemBean()
	{
		 return this.itemBean;
	}

}
