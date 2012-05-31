/**
 * Description:
 * <br/>Copyright (C), 2001-2012, mshen
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  mshen
 * @version  1.0
 */
package auction.service.impl;

import java.util.*;

import org.apache.log4j.Logger;

import auction.business.*;
import auction.dao.*;
import auction.model.*;
import auction.exception.AuctionException;
import auction.model.*;
import auction.service.AuctionManager;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * @author mshen
 *
 */
public class AuctionManagerImpl implements AuctionManager{
	static Logger log = Logger.getLogger(
			AuctionManagerImpl.class.getName());
	//以下是该业务逻辑组件所依赖的DAO组件
	private AuctionUserDao userDao;
	private BidDao bidDao;
	private ItemDao itemDao;
	private KindDao kindDao;
	private StateDao stateDao;
	//业务逻辑组件发送邮件所依赖的两个Bean
	private MailSender mailSender;
	private SimpleMailMessage message;
	
	//为业务逻辑组件依赖注入DAO组件所需的setter方法
	public void setUserDao(AuctionUserDao userDao) 
	{
		this.userDao = userDao; 
	}
	public void setBidDao(BidDao bidDao) 
	{
		this.bidDao = bidDao; 
	}
	public void setItemDao(ItemDao itemDao) 
	{
		this.itemDao = itemDao; 
	}
	public void setKindDao(KindDao kindDao) 
	{
		this.kindDao = kindDao; 
	}
	public void setStateDao(StateDao stateDao) 
	{
		this.stateDao = stateDao; 
	}
	
	//为业务逻辑组件注入两个邮件发送Bean的setter方法
	public void setMailSender(MailSender mailSender)
	{
		this.mailSender = mailSender;
	}
	public void setMessage(SimpleMailMessage message)
	{
		this.message = message;
	}
	
	/**
	 * 根据赢取者查询物品
	 * @param winerId 赢取者的ID
	 * @return 赢取者获得的全部物品
	 */
	public List getItemByWiner(int winerId) throws AuctionException
	{
		try
		{
			List items = itemDao.findItemByWiner(winerId);
			List result = new ArrayList();
			for (Iterator it = items.iterator(); it.hasNext();)
			{
				Item item = (Item)it.next();
				ItemBean ib = new ItemBean();
				initItem(ib,item);
				result.add(ib);
			}
			return result;
		}catch (Exception e)
		{
			log.debug(e.getMessage());
			throw new AuctionException("查询用户所赢取的物品出现异常,请重试");
		}
	}
	
	/**
	 * 查询流拍的全部物品
	 * @return 全部流拍物品
	 */
	public List getFailItems() throws AuctionException
	{
		try{
			List items = itemDao.findItemByState(3);
			List result = new ArrayList();
			for (Iterator it = items.iterator() ; it.hasNext() ; )
			{
				Item item = (Item)it.next();
				ItemBean ib = new ItemBean();
				initItem(ib,item);
				result.add(ib);
			}
			return result;
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw new AuctionException("查询流拍物品出现异常,请重试");
		}
	}
	
	/**
	 * 根据用户名，密码验证登录是否成功
	 * @param username 登录的用户名
 	 * @param pass 登录的密码
	 * @return 登录成功返回用户ID，否则返回-1
	 */
	
	public int validLogin(String username , String pass) throws AuctionException
	{
		try
		{
			AuctionUser u = userDao.findUserByNameAndPass(username , pass);
			if (u != null)
			{
				return u.getId();
			}
			return -1;
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw new AuctionException("处理用户登录出现异常,请重试");
		}
	}
	
	/**
	 * 查询用户的全部出价
	 * @param userId 竞价用户的ID
	 * @return 用户的全部出价
	 */
	public List getBidByUser(Integer userId) throws AuctionException
	{
		try
		{
			List l = bidDao.findByUser(userId);
			List result = new ArrayList();
			for ( int i = 0 ; i < l.size() ; i++ )
			{
				Bid bid = (Bid)l.get(i);
				BidBean bb = new BidBean();
				initBid(bb, bid);
				result.add(bb);
			}
			return result;
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw new AuctionException("浏览用户的全部竞价出现异常,请重试");
		}
	}
	/**
	 * 根据用户查找目前仍在拍卖中的全部物品
	 * @param userId 所属者的ID
	 * @return 属于当前用户的、处于拍卖中的全部物品。
	 */
	public List getItemsByOwner(Integer userId) throws AuctionException
	{
		try
		{
			List result = new ArrayList();
			List items = itemDao.findItemByOwner(userId);
			for (Iterator it = items.iterator() ; it.hasNext() ; )
			{
				Item item = (Item)it.next();
				ItemBean ib = new ItemBean();
				initItem(ib,item);
				result.add(ib);
			}
			return result;
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw new AuctionException("查询用户所有的物品出现异常,请重新");
		}
	}
	/**
	 * 查询全部种类
	 * @return 系统中全部全部种类
	 */   
	public List getAllKind() throws AuctionException
	{
		List result = new ArrayList();
		try
		{
			List kl = kindDao.findAll();
			for (Object o : kl )
			{
				Kind k = (Kind)o;
				result.add(new KindBean(k.getId(),
					k.getKindName(), k.getKindDesc()));
			}
			return result;
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw new AuctionException("查询全部种类出现异常,请重试");
		}
	}
	/**
	* 添加物品
	* @param name 物品名称
	* @param desc 物品描述
	* @param remark 物品备注
	* @param avail 有效天数
	* @param kind 物品种类
	* @param userId 添加者的ID
	* @return 新增物品的主键
	*/
	public int addItem(String name , String desc , String remark ,
		double initPrice , int avail , int kind , Integer userId)
		throws AuctionException
	{
		try
		{
			Kind k = kindDao.get(kind);
			AuctionUser owner = userDao.get(userId);
			//创建Item对象
			Item item = new Item();
			item.setItemName(name);
			item.setItemDesc(desc);
			item.setItemRemark(remark);
			item.setAddtime(new Date());
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE , avail);
			item.setEndtime(c.getTime());
			item.setInitPrice(new Double(initPrice));
			item.setMaxPrice(new Double(initPrice));
			item.setItemState(stateDao.get(1));
			item.setKind(k);
			item.setOwner(owner);
			//持久化Item对象
			itemDao.save(item);
			return item.getId();
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw new AuctionException("添加物品出现异常,请重试");
		}
	}
	
	
	/**
	 * 根据参数修改物品
	 */
	public void updateItem(int id,String name , String desc , String remark ,
			double initPrice , int avail , int kind , Integer userId)
			throws AuctionException
		{
			try
			{
				Kind k = kindDao.get(kind);
				AuctionUser owner = userDao.get(userId);
				//创建Item对象
				Item item = new Item();
				item.setId(id);
				item.setItemName(name);
				item.setItemDesc(desc);
				item.setItemRemark(remark);
				item.setAddtime(new Date());
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DATE , avail);
				item.setEndtime(c.getTime());
				item.setInitPrice(new Double(initPrice));
				item.setMaxPrice(new Double(initPrice));
				item.setItemState(stateDao.get(1));
				item.setKind(k);
				item.setOwner(owner);
				//持久化Item对象
				itemDao.update(item);
			}
			catch (Exception e)
			{
				log.debug(e.getMessage());
				throw new AuctionException("更新物品出现异常,请重试");
			}
		}
	
	/**
	 * 添加种类
	 * @param name 种类名称
	 * @param desc 种类描述
	 * @return 新增种类的主键
	 */ 
	public int addKind(String name , String desc)
		throws AuctionException
	{
		try
		{
			Kind k = new Kind();
			k.setKindName(name);
			k.setKindDesc(desc);
			kindDao.save(k);
			return k.getId();
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw new AuctionException("添加种类出现异常,请重试");
		}
	}
	
	/**
	 * 根据产品分类，获取处于拍卖中的全部物品
	 * @param kindId 种类id;
	 * @return 该类的全部产品
	 */
	public List getItemsByKind(int kindId) throws AuctionException
	{
		List result = new ArrayList();
		try
		{
			List items = itemDao.findItemByKind(kindId);
			for (Iterator it = items.iterator() ; it.hasNext() ; )
			{
				Item item = (Item)it.next();
				ItemBean ib = new ItemBean();
				initItem(ib , item);
				result.add(ib);
			}
			return result;
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw new AuctionException("根据种类获取物品出现异常,请重试");
		}
	}
	/**
	 * 根据种类id获取种类名
	 * @param kindId 种类id;
	 * @return 该种类的名称
	 */
	public String getKind(int kindId) throws AuctionException
	{
		try
		{
			Kind  k = kindDao.get(kindId);
			if (k != null)
			{
				return k.getKindName();
			}
			return null;
		}
		catch (Exception ex)
		{
			log.debug(ex.getMessage());
			throw new AuctionException("根据种类id获取种类名称出现异常,请重试");
		}
	}
	
	/**
	 * 根据种类id获取种类名
	 * @param kindId 种类id;
	 * @return 种类
	 */
	public Kind getKindBean(int kindId) throws AuctionException
	{
		try
		{
			Kind  kind = kindDao.get(kindId);
			
			if (kind != null)
			{
				Kind kind_return = new Kind();
				kind_return.setId(kind.getId());
				kind_return.setKindName(kind.getKindName());
				kind_return.setKindDesc(kind.getKindDesc());
				return kind_return;
			}
			return null;
		}
		catch (Exception ex)
		{
			log.debug(ex.getMessage());
			throw new AuctionException("根据种类id获取种类出现异常,请重试");
		}
	}
	
	/**
	 * 根据参数修改种类
	 */
	public void updateKind(Kind kind) 
		throws AuctionException
	{
		try
		{
			kindDao.update(kind);
		}
		catch (Exception ex)
		{
			log.debug(ex.getMessage());
			throw new AuctionException("更新种类信息时出现异常,请重试");
		}
	}
	
	/**
	 * 根据物品id，获取物品
	 * @param itemId 物品id;
	 * @return 指定id对应的物品
	 */
	public ItemBean getItem(int itemId)
		throws AuctionException
	{
		try
		{
			Item item = itemDao.get(itemId);
			ItemBean ib = new ItemBean();
			initItem(ib, item);
			return ib;
		}
		catch (Exception ex)
		{
			log.debug(ex.getMessage());
			throw new AuctionException("根据物品id获取物品详细信息出现异常,请重试");
		}
	}

	/**
	 * 增加新的竞价，并对竞价用户发邮件通知
	 * @param itemId 物品id;
	 * @param bidPrice 竞价价格
	 * @param userId 竞价用户的ID
	 * @return 返回新增竞价记录的ID
	 */
	public int addBid(int itemId , double bidPrice , Integer userId)
		throws AuctionException
	{
		try
		{
			AuctionUser au = userDao.get(userId);
			Item item = itemDao.get(itemId);
			if (bidPrice > item.getMaxPrice())
			{
				item.setMaxPrice(new Double(bidPrice));
				itemDao.save(item);
			}
			//初始化Bid对象
			Bid bid = new Bid();
			bid.setBidItem(item);
			bid.setBidUser(au);
			bid.setBidDate(new Date());
			bid.setBidPrice(bidPrice);
			//持久化Bid对象
			bidDao.save(bid);
			//准备发送邮件
			SimpleMailMessage msg = new SimpleMailMessage(this.message);
			msg.setTo(au.getEmail());
			msg.setText("Dear "
				+ au.getUsername()
				+ ", 谢谢你参与竞价，你的竞价的物品的是: "
				+ item.getItemName());
			mailSender.send(msg);
			return bid.getId();
		}
		catch(Exception ex)
		{
			log.debug(ex.getMessage());
			throw new AuctionException("处理用户竞价出现异常,请重试");
		}
	}
	
	
	/**
	 * 根据时间来修改物品的状态、赢取者
	 */
	public void updateWiner()throws AuctionException
	{
		try
		{
			List itemList = itemDao.findItemByState(1);
			for (int i = 0 ; i < itemList.size() ; i++ )
			{
				Item item = (Item)itemList.get(i);
				if (!item.getEndtime().after(new Date()))
				{
					//根据指定物品和最高竞价来查询用户
					AuctionUser au = bidDao.findUserByItemAndPrice(
						item.getId() , item.getMaxPrice());
					//如果该物品的最高竞价者不为null
					if (au != null)
					{
						//将该竞价者设为赢取者
						item.setWiner(au);
						//修改物品的状态成为“被赢取”
						item.setItemState(stateDao.get(2));
						itemDao.save(item);
					}
					else
					{
						//设置该物品的状态为“流拍”
						item.setItemState(stateDao.get(3));
						itemDao.save(item);
					}
				}
			}
		}
		catch (Exception ex)
		{
			log.debug(ex.getMessage());
			throw new AuctionException("根据时间来修改物品的状态、赢取者出现异常,请重试");
		}
	}
	
	/**
	 * 根据kind id删除类型
	 */
	public void deleteKind(int kindid) throws AuctionException
	{
		try
		{
			kindDao.delete(kindid);
		}catch (Exception ex){
			log.debug(ex.getMessage());
			throw new AuctionException("删除类型出现异常，请重试");
		}
	}
	
	/**
	 * 根据item id删除物品
	 */
	public void deleteItem(int itemId) throws AuctionException
	{
		try
		{
			itemDao.delete(itemId);
		}catch (Exception ex){
			log.debug(ex.getMessage());
			throw new AuctionException("删除物品出现异常，请重试");
		}
	}
	
	/**
	 * 将一个Bid对象转换成BidBean对象
	 * @param bb BidBean对象
	 * @param bid Bid对象
	 */
	private void initBid(BidBean bb , Bid bid)
	{
		bb.setId(bid.getId().intValue());
		if (bid.getBidUser() != null )
			bb.setUser(bid.getBidUser().getUsername());
		if (bid.getBidItem() != null )
			bb.setItem(bid.getBidItem().getItemName());
		bb.setPrice(bid.getBidPrice());
		bb.setBidDate(bid.getBidDate());
	}
	
	/**
	 * 将一个Item PO转换成ItemBean的VO
	 * @param ib ItemBean的VO
	 * @param item Item的PO
	 */
	
	private void initItem(ItemBean ib , Item item)
	{
		ib.setId(item.getId());
		ib.setName(item.getItemName());
		ib.setDesc(item.getItemDesc());
		ib.setRemark(item.getItemRemark());
		if (item.getKind() != null)
			ib.setKind(item.getKind().getKindName());
		if (item.getOwner() != null)
			ib.setOwner(item.getOwner().getUsername());
		if (item.getWiner() != null)
			ib.setWiner(item.getWiner().getUsername());
		ib.setAddTime(item.getAddtime());
		ib.setEndTime(item.getEndtime());
		if (item.getItemState() != null)
			ib.setState(item.getItemState().getStateName());
		ib.setInitPrice(item.getInitPrice());
		ib.setMaxPrice(item.getMaxPrice());
	}
	
	
}
