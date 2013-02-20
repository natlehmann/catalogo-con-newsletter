<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String title = request.getParameter("title") != null ? request.getParameter("title") : "Alma de Jazmin";
	String showAdminMenu = request.getParameter("showAdminMenu");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<meta name="KEYWORDS" content='<spring:message code="meta.keywords"/>' />
	
	<link rel="shortcut icon" href='<c:url value="/favicon.ico"/>' />
	<link rel="stylesheet" type="text/css" media="all" href='<c:url value="/css/jquery-ui-1.10.1.custom.min.css"/>' /> 
	<link rel="stylesheet" type="text/css" media="all" href='<c:url value="/css/master.css"/>' /> 
	<script type="text/javascript" src='<c:url value="/js/jquery-1.9.1.min.js"/>'></script>
	<script type="text/javascript" src='<c:url value="/js/jquery-ui-1.10.1.custom.min.js"/>'></script>
	<title><%= title %></title>
</head>
<body id="almaJazmin" style="position: relative;">

	<c:if test="<%= showAdminMenu == null %>">
		<div id="menu" style="width:50px; position: absolute; top: 300px; left: 0px; height:100px; font-size:11px;">
			<a href="home.html">Home</a>
			<a href="nosotros.html">Nosotros</a>
			<a href="productos.html">Productos</a>
			<a href="locales.html">Locales</a>
			<a href="contacto.html">Contacto</a>
			<a href="prensa.html">Prensa</a>
		</div>
	</c:if>
	
	<c:if test="<%= showAdminMenu != null %>">
		<div id="menu" style="width:100px; position: absolute; top: 300px; left: 0px; height:100px; font-size:11px;">
			<a href="productList.html">Productos</a>
			<a href="categoryList.html">Categorias</a>
			<br/>
			<a href='<c:url value="/index.html"/>'>Ir al sitio de Alma de Jazmin</a>
		</div>
	</c:if>
