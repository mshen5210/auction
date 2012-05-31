/**
 * Description:
 * <br/>Copyright (C), 2001-2012, mshen
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  mshen
 * @version  1.0
 */
package auction.model;
import java.util.*;

/**
 * @author mshen
 *
 */
public class State{
	//标识属性
	private Integer id;
	//物品的状态名
	private String stateName;
	//该状态下的所有物品
	private Set<Item> items = new HashSet<Item>();
	//无参数的构造器
	public State()
	{
	}
	//初始化全部基本属性的构造器
	public State(Integer id , String stateName)
	{
		this.id = id;
		this.stateName = stateName;
	}
	
	//id属性的setter和getter方法
	public void setId(Integer id)
	{
		this.id = id;
	}
	public Integer getId()
	{
		return this.id;
	}

	//stateName属性的setter和getter方法
	public void setStateName(String stateName)
	{
		this.stateName = stateName;
	}
	public String getStateName()
	{
		return this.stateName;
	}

	//items属性的setter和getter方法
	public void setItems(Set<Item> items)
	{
		this.items = items;
	}
	public Set<Item> getItems()
	{
		return this.items;
	}
	
	
}
