/**
 * Description:
 * <br/>Copyright (C), 2001-2012, mshen
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  mshen
 * @version  1.0
 */
package auction.dao.impl;

import java.util.*;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.hibernate.Hibernate;

import auction.model.*;
import auction.business.*;
import auction.dao.*;

public class StateDaoHibernate
	extends HibernateDaoSupport implements StateDao  
{
	/**
	 * 根据id查找状态
	 * @param id 需要查找的状态id
	 */
	public State get(Integer id)
	{
	return (State)getHibernateTemplate().get(State.class , id);
	}
	/**
	 * 增加状态
	 * @param state 需要增加的状态
	 */   
	public void save(State state)
	{
		getHibernateTemplate().save(state);
	}

	/**
	 * 修改状态
	 * @param state 需要修改的状态
	 */
	public void update(State state)
	{
		getHibernateTemplate().saveOrUpdate(state);
	}

	/**
	 * 删除状态
	 * @param id 需要删除的状态id
	 */
	public void delete(Integer id)
	{
		getHibernateTemplate().delete(get(id));
	}

	/**
	 * 删除状态
	 * @param state 需要删除的状态
	 */
	public void delete(State state)
	{
		getHibernateTemplate().delete(state);
	}

	/**
	 * 查询全部种类
	 * @return 获得全部种类
	 */
	public List<State> findAll()
	{
		return (List<State>)getHibernateTemplate().find("from State");
	}
}