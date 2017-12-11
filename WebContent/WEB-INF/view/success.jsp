<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

恭喜，操作成功！！

<c:if test="${requestScope.parent==true }">
<a href="#" onclick="parent.window.location.href='<%=request.getContextPath()%>/${requestScope.targetUrl}'">点击此处，立即跳转</a>
</c:if>

<c:if test="${requestScope.parent==false }">
<a href="#" onclick="window.location.href='<%=request.getContextPath()%>/${requestScope.targetUrl}'">点击此处，立即跳转</a>
</c:if>