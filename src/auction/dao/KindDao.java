/**
 * Description:
 * <br/>Copyright (C), 2001-2012, mshen
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  mshen
 * @version  1.0
 */
package auction.dao;

import java.util.List;

import auction.model.*;
import auction.business.*;

public interface KindDao  
{
	/**
	 * 根据id查找种类
	 * @param id 需要查找的种类的id
	 */
	Kind get(Integer id);

	/**
	 * 增加种类
	 * @param kind 需要增加的种类
	 */
	void save(Kind kind);

	/**
	 * 修改种类
	 * @param kind 需要修改的种类
	 */
	void update(Kind kind);

	/**
	 * 删除种类
	 * @param id 需要删除的种类id
	 */
	void delete(Integer id);

	/**
	 * 删除种类
	 * @param kind 需要删除的种类
	 */
	void delete(Kind kind);

	/**
	 * 查询全部种类
	 * @return 获得全部种类
	 */
	List<Kind> findAll();
}
