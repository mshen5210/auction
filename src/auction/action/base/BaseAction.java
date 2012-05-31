/**
 * Description:
 * <br/>Copyright (C), 2001-2012, mshen
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  mshen
 * @version  1.0
 */
package auction.action.base;

import com.opensymphony.xwork2.ActionSupport;
import auction.service.AuctionManager;

/**
 * @author mshen
 *
 */
public class BaseAction extends ActionSupport{
	protected AuctionManager mgr;

	public void setMgr(AuctionManager mgr)
	{
		this.mgr = mgr;
	}
}
