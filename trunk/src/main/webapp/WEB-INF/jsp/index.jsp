<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="/WEB-INF/includes/head.jsp">
	<jsp:param value="Alma de Jazmin" name="title"/>
</jsp:include>


	<div id="logo1">
		<a href='<c:url value="/home.html"/>'>
			<img src='<c:url value="/images/logo.png"/>' alt="logo" width="235" height="79"/>
		</a>
	</div> 


<jsp:include page="/WEB-INF/includes/common_footer.jsp">
	<jsp:param value="fondoPortada.jpg" name="backgroundImg"/>
</jsp:include>
