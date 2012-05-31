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
import auction.model.*;

/**
 * @author mshen
 *
 */
public class UpdateKindAction extends BaseAction {
	private int id;
	private String kindName;
	private String kindDesc;
	private String vercode;
	private List kinds;
	private Kind kind;
	
	public String proUpdateKind() throws Exception
	{
		if(mgr.getKind(id) == null)
		{
			setKinds(mgr.getAllKind());
			addActionMessage("该类型不存在，请再次检查！");
			return "failure";
		}else{
			setKind(mgr.getKindBean(id));
			return SUCCESS;
		}
	}
	
	@Override
	public String execute() throws Exception
	{
		Map session = ActionContext.getContext().getSession();
		//判断如果数据库中不存在该类型，则给出提示信息
		if(mgr.getKind(id) == null)
		{	
			setKinds(mgr.getAllKind());
			addActionMessage("该类型不存在，请再次检查！");
			return "failure";
		}else{
			String ver2 = (String)session.get("rand");
			Kind kind = new Kind(id,kindName,kindDesc);
			session.put("rand" , null);
			mgr.updateKind(kind);
			return SUCCESS;
			
//			if (vercode.equals(ver2))
//			{	
//				mgr.updateKind(kind);
//				return SUCCESS;
//			}
//			else
//			{
//				addActionError("验证码不匹配,请重新输入");
//				return INPUT;
//			}
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
	
	public void setKindName(String kindName)
	{
		this.kindName = kindName;
	}
	public String getKindName()
	{
		 return this.kindName;
	}

	public void setKindDesc(String kindDesc)
	{
		this.kindDesc = kindDesc;
	}
	public String getKindDesc()
	{
		 return this.kindDesc;
	}

	public void setVercode(String vercode)
	{
		this.vercode = vercode;
	}
	public String getVercode()
	{
		 return this.vercode;
	}
	
	public void setKind(Kind kind)
	{
		this.kind = kind;
	}
	public Kind getKind()
	{
		 return this.kind;
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
