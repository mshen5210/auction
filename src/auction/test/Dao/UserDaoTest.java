/**
 * Description:
 * <br/>Copyright (C), 2001-2012, mshen
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  mshen
 * @version  1.0
 */
package auction.test.Dao;

import auction.dao.KindDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import auction.model.Kind;
import auction.test.BaseTest;

/**
 * @author mshen
 *
 */

public class UserDaoTest extends BaseTest{
	private KindDao kindDao;
	
	@Before
    protected void setUp() throws Exception {
		//从配置文件中获取kindDao实例
		super.setUp();
		kindDao = (KindDao) ctx.getBean("kindDao");	
    }
	/**
	 * @Desc 测试kindDao中的get(Integer id)方法
	 *
	 */
	@Test
	public void testGet()
	{
		//创建期望的kind
		Kind kindExpect = new Kind(1,"房产","这里是比较好的房子，欢迎大家来竞拍");
		//初始化要查询的kind id 
		int kindid = 1;		
		kindDao = (KindDao) ctx.getBean("kindDao");
		//比较结果
		assertEquals(kindDao.get(kindid).getId(),kindExpect.getId());
		assertEquals(kindDao.get(kindid).getKindName(),kindExpect.getKindName());
		assertEquals(kindDao.get(kindid).getKindDesc(),kindExpect.getKindDesc());
	}
	
	/**
	 * @Desc 测试kindDao中的save(Kind kind)方法
	 *
	 */
	@Test
	public void testSave()
	{
		//创建期望的kind
		Kind kindSave = new Kind();
		kindSave.setKindName("测试看看");
		kindSave.setKindDesc("测试看看的描述");
		kindDao.save(kindSave);
		assertEquals(kindDao.get(4).getKindName(),"测试看看");
	}	
	
	@After
	public void tearDown() throws Exception {
		kindDao = null;
        super.tearDown();
	}
}
