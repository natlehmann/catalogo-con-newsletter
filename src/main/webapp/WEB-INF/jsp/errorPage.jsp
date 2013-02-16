<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<jsp:include page="/WEB-INF/includes/header.jsp">
	<jsp:param value="Alma de Jazmin" name="title"/>
</jsp:include>

<%
	String errorMsgCode = request.getAttribute("error-msg-code") != null ? (String)request.getAttribute("error-msg-code") : null;
%>



<c:choose>
	<c:when test="<%= errorMsgCode != null %>">
		<spring:message code="<%= errorMsgCode %>" />
	</c:when>
	<c:otherwise>
		<spring:message code="this.site.is.temporarily.unavailable"/>
	</c:otherwise>
</c:choose>


<jsp:include page="/WEB-INF/includes/footer.jsp" />
