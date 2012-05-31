<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>管理拍卖物品</title>
</head>
<body>
<table width="780" align="center" cellspacing="0"
	background="images/bodybg.jpg">
<tr>
<td>
<br />
<h3>更新物品</h3>
<div align="center">
<s:actionerror/>
<s:form action="updateItem">
<s:hidden name="id" value="%{itemBean.id}"/>
<s:textfield name="itemName" label="物品名" value="%{itemBean.name}"/>
<s:textfield name="itemDesc" label="物品描述" value="%{itemBean.desc}"/>
<s:textfield name="itemRemark" label="物品备注" value="%{itemBean.remark}"/>
<s:textfield name="itemInitPrice" label="起拍价格" value="%{itemBean.initPrice}"/>
<s:select name="itemAvail" list="#{'1':'一天','2':'二天','3':'三天','4':'四天',
	'5':'五天','6':'一个星期','7':'一个月','8':'一年'}"
	label="有效时间" value="%{avail}"/>
<s:select list="kinds" label="物品种类"   name="itemKind" listKey="id" listValue="kindName" headerKey="0" headerValue="%{itemBean.kind}" />
<s:textfield name="vercode" label="验证码"/>
<s:submit value="更新"/>
</s:form>
验证码：<img name="d" src="authImg.jpg">
</div>
</td>
</tr>
</table>
</body>
</html>