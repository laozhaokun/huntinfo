<%@ page language="java" contentType="text/html; charList=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charList=UTF-8">
<link href="style.css" rel="stylesheet" type="text/css" />
<title>最新招聘信息列表</title>
</head>
<body>
	<div class="index_body">
		<h2>
			<a href="index.jsp">最新の招聘信息</a>
		</h2>
		<br />
		<h3>
			<dd>
				<a href="getHuntInfoListAction?source=shuimu_social">水木_社会招聘</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="getHuntInfoListAction?source=shuimu_campus">水木_校园招聘</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="getHuntInfoListAction?source=shuimu_hunting">水木_猎头招聘</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="getHuntInfoListAction?source=byr">北邮人_招聘</a>
			</dd>
		</h3>
		<br /> <br />
		<c:forEach var="item" items="${shi}">
			<ul>
				<li class="title_href">
				<a href="getHuntContentAction?id=${item.id}">${item.title}</a>
				</li>
			</ul>
		</c:forEach>
		<div class="pageTag">
			<s:if test="source == 'shuimu_social'">
				<s:url id="url_pre"
					value="getHuntInfoListAction?source=shuimu_social">
					<s:param name="pageNow" value="pageNow-1"></s:param>
				</s:url>
			</s:if>
			<s:elseif test="source == 'shuimu_campus'">
				<s:url id="url_pre"
					value="getHuntInfoListAction?source=shuimu_campus">
					<s:param name="pageNow" value="pageNow-1"></s:param>
				</s:url>
			</s:elseif>
			<s:elseif test="source == 'shuimu_hunting'">
				<s:url id="url_pre"
					value="getHuntInfoListAction?source=shuimu_hunting">
					<s:param name="pageNow" value="pageNow-1"></s:param>
				</s:url>
			</s:elseif>
			<s:elseif test="source == 'byr'">
				<s:url id="url_pre"
					value="getHuntInfoListAction?source=byr">
					<s:param name="pageNow" value="pageNow-1"></s:param>
				</s:url>
			</s:elseif>

			<s:if test="source == 'shuimu_social'">
				<s:url id="url_next"
					value="getHuntInfoListAction?source=shuimu_social">
					<s:param name="pageNow" value="pageNow+1"></s:param>
				</s:url>
			</s:if>
			<s:elseif test="source == 'shuimu_campus'">
				<s:url id="url_next"
					value="getHuntInfoListAction?source=shuimu_campus">
					<s:param name="pageNow" value="pageNow+1"></s:param>
				</s:url>
			</s:elseif>
			<s:elseif test="source == 'shuimu_hunting'">
				<s:url id="url_next"
					value="getHuntInfoListAction?source=shuimu_hunting">
					<s:param name="pageNow" value="pageNow+1"></s:param>
				</s:url>
			</s:elseif>
			<s:elseif test="source == 'byr'">
				<s:url id="url_next"
					value="getHuntInfoListAction?source=byr">
					<s:param name="pageNow" value="pageNow+1"></s:param>
				</s:url>
			</s:elseif>

			<s:if test="pageNow > 1">
				<s:a href="%{url_pre}">上一页</s:a>
			</s:if>
			<s:else>
				<span style="display: none">上一页</span>
			</s:else>
			<s:if test="%{totalPage > pageNow}">
				<s:a href="%{url_next}">下一页</s:a>
			</s:if>
			<s:else>
				<span style="display: none">下一页</span>
			</s:else>
		</div>
	</div>
	<s:include value="footer.jsp"></s:include>
</body>
</html>