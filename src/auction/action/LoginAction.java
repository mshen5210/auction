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

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import auction.action.base.BaseAction;

/**
 * @author mshen
 *
 */
public class LoginAction extends BaseAction{
	private String username;
	private String password;
	private String vercode;
	
	public String execute() throws Exception
	{
		Map session = ActionContext.getContext().getSession();
		String ver2 = (String )session.get("rand");
		//清空用户Session的随机验证码字符串。
		session.put("rand" , null);
//		if (vercode.equalsIgnoreCase(ver2))
//		{
			Integer userId = mgr.validLogin(username,password);
			if (userId != null && userId > 0)
			{
				session.put("userId" , userId);
				session.put("userName" , username);
				return SUCCESS;
			}
			else
			{
				addActionError("用户名/密码不匹配");
				return "failure";
			}
//		}
//		else
//		{
//			addActionError("验证码不匹配,请重新输入");
//			return "failure";
//		}
	}
	
	
	
	//username属性的setter和getter方法
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getUsername()
	{
		return this.username;
	}

	//password属性的setter和getter方法
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getPassword()
	{
		return this.password;
	}

	//vercode属性的setter和getter方法
	public void setVercode(String vercode)
	{
		this.vercode = vercode;
	}
	public String getVercode()
	{
		return this.vercode;
	}
}
