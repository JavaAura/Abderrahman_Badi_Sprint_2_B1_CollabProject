<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%
    request.setAttribute("title", "Members");
    request.setAttribute("contentPage", "../content/members.jsp"); 
%>

<jsp:include page="/views/layout/layout.jsp" />
