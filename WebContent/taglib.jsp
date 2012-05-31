<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
	<body>
		<table border="1" width="100">
			<s:iterator value="{'Spring2.0宝典','轻量级J2EE企业应用实战','基于J2EE的Ajax宝典'}" id="name" status="st">
				<tr <s:if test="#st.odd">style="background-color:#bbbbbb"</s:if>>
					<td>
						<s:property value="st.count"/>
						<s:property value="name"/>
					</td>
				</tr>
			</s:iterator>
			<tr>
				<s:form>
					<s:checkboxlist name = "a" label="请选择您喜欢的图书" labelposition="top" list="{'Spring2.0宝典','轻量级J2EE企业应用实战','基于J2EE的Ajax宝典'}"/>
					<s:checkboxlist name = "b" label="请选择您想要的出版日期" labelposition="top" list="#{'Spring2.0宝典':'2006年10月','轻量级J2EE企业应用实战':'2007年1月','基于J2EE的Ajax宝典':'2008年07月'}" listKey="key" listValue="value"/>
					<s:bean name="lee.BookService" id="bs"></s:bean>
					<s:checkboxlist name = "bs" label="请选择您喜欢的图书" labelposition="top" list="#bs.books" listKey="name" listValue="author"/>
				</s:form>
			</tr>
			
			<tr>
				<s:form>
					<s:select name = "sa" label="请选择您喜欢的图书" labelposition="top" list="{'Spring2.0宝典','基于J2EE的Ajax宝典'}" headerKey="00" headerValue="轻量级J2EE企业应用实战" />
					<s:select name = "sb" label="请选择您想要的出版日期" labelposition="top" list="#{'Spring2.0宝典':'2006年10月','轻量级J2EE企业应用实战':'2007年1月','基于J2EE的Ajax宝典':'2008年07月'}" listKey="key" listValue="value"/>
					<s:bean name="lee.BookService" id="bs"></s:bean>
					<s:select name = "sbs" label="请选择您喜欢的图书" labelposition="top" list="#bs.books" listKey="name" listValue="author" multiple="true"/>
				</s:form>
			</tr>
		</table>
	</body>
</html>