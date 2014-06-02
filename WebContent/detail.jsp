<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style.css" rel="stylesheet" type="text/css" />
<title>招聘信息</title>
</head>
<body>
<a href="index.jsp">返回首页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:history.go(-1);">返回上一页</a>
<div class="hi_title"><span>${hi.title}</span></div>
<div class="hi_content">
${hi.content}
</div>
<br/>
源地址：<a target="_blank" href="${hi.content_url}">${hi.content_url}</a><br/>
<a href="index.jsp">返回首页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:history.go(-1);">返回上一页</a>
<s:include value="footer.jsp"></s:include>
</body>
</html>