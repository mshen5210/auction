package auction.test.Service;
/**
 * Description:
 * <br/>Copyright (C), 2001-2012, mshen
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  mshen
 * @version  1.0
 */

import org.junit.Test;

import auction.exception.AuctionException;
import auction.test.BaseTest;


/**
 * @author mshen
 *
 */

public class AuctionUserDaoTest extends BaseTest{

	@Test
	public void testGet() throws AuctionException
	{
		String username = "tomcat";
		String pass = "tomcat";
		assertEquals(mgrTest.validLogin(username, pass),1);
	}
	

}
