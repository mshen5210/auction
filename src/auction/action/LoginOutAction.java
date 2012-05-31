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

import auction.action.base.BaseAction;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

/**
 * @author mshen
 *
 */
public class LoginOutAction extends BaseAction{
	public String execute() throws Exception
	{
		Map session = ActionContext.getContext().getSession();
		session.clear();
		return "success";
	}
}
