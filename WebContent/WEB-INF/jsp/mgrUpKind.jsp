<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>管理拍卖种类</title>
</head>
<body>
<table width="780" align="center" 
	cellspacing="0" background="images/bodybg.jpg">
<tr>
<td>
<h3>更新种类</h3>
<div align="center">
<s:actionerror cssClass="error"/>
<s:form action="updateKind">
<s:hidden name="id" value="%{kind.id}"/>
<s:textfield name="kindName" label="种类名" value="%{kind.kindName}"/>
<s:textfield name="kindDesc" label="种类描述" value="%{kind.kindDesc}"/>
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