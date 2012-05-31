/**
 * Description:
 * <br/>Copyright (C), 2001-2012, mshen
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  mshen
 * @version  1.0
 */
package auction.schedule;

import java.util.TimerTask;

import auction.exception.AuctionException;
import auction.service.AuctionManager;

/**
 * @author mshen
 *
 */
public class CheckWiner extends TimerTask
{
	//该任务所依赖业务逻辑组件
	private AuctionManager mgr;
	//依赖注入业务逻辑组件必须的setter方法
	public void setMgr(AuctionManager mgr)
	{
		this.mgr = mgr;
	}
	//该任务的执行体
	public void run()
	{
		try
		{
			mgr.updateWiner();
		}
		catch (AuctionException ae)
		{
			ae.printStackTrace();
		}
	}
}
