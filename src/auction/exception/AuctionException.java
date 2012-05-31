/**
 * Description:
 * <br/>Copyright (C), 2001-2012, mshen
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  mshen
 * @version  1.0
 */
package auction.exception;

public class AuctionException extends Exception
{
	//定义一个无参数的构造器
	public AuctionException()
	{
	}
	//定义一个带message参数的构造参数
	public AuctionException(String message)
	{
		super(message);
	}
}
