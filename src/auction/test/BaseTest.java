/**
 * Description:
 * <br/>Copyright (C), 2001-2012, mshen
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  mshen
 * @version  1.0
 */
package auction.test;


import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import auction.model.AuctionUser;
import auction.service.AuctionManager;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author mshen
 *
 */
public class BaseTest extends TestCase{
	/**
	 * @throws java.lang.Exception
	 */
	private static Log log = LogFactory.getLog(AuctionManager.class);
	private SessionFactory sessionFactory;  
	private Session session;  
	protected ApplicationContext ctx;
	private AuctionUser user;
	protected AuctionManager mgrTest;
	
	@Before
	protected void setUp() throws Exception {
        String paths[] = { "applicationContext.xml","daoContext.xml" };
        ctx = new ClassPathXmlApplicationContext(paths);
        sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");  
		session = SessionFactoryUtils.getSession(sessionFactory, true);  
        session.setFlushMode(FlushMode.AUTO);  
        TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session)); 
        mgrTest = (AuctionManager) ctx.getBean("mgr");
        super.setUp();
    }
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		user = null;
		mgrTest = null;
		TransactionSynchronizationManager.unbindResource(sessionFactory);  
        SessionFactoryUtils.releaseSession(session, sessionFactory);  
        super.tearDown();
	}

}
