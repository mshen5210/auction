/**
 * Description:
 * <br/>Copyright (C), 2001-2012, mshen
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  mshen
 * @version  1.0
 */



import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner; 

import auction.dao.AuctionUserDao;
import auction.dao.impl.AuctionUserDaoHibernate;
import auction.exception.AuctionException;
import auction.model.AuctionUser;
import auction.service.AuctionManager;

/**
 * @author mshen
 *
 */

public class AuctionUserDaoTest extends TestCase{

	private static Log log = LogFactory.getLog(AuctionManager.class);
	private ApplicationContext ctx;
	private AuctionUser user;
	private AuctionManager mgr;
	
	protected void setUp() throws Exception {
		 
        String paths[] = { "applicationContext.xml","daoContext.xml" };
        ctx = new ClassPathXmlApplicationContext(paths);
        mgr = (AuctionManager) ctx.getBean("mgr");
        super.setUp();
    }
	@Test
	public void testGet() throws AuctionException
	{
		
		System.out.println(mgr.getAllKind());
	}
	

}
