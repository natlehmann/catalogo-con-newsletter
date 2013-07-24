<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String title = request.getParameter("title") != null ? request.getParameter("title") : "Alma de Jazmin";
	boolean showAdminMenu = Boolean.parseBoolean(request.getParameter("showAdminMenu"));
	String pageId = request.getParameter("pageId") != null ? request.getParameter("pageId") : "page_id";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es-es">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<meta name="KEYWORDS" content='<spring:message code="meta.keywords"/>' />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    
	<link rel="shortcut icon" href='<c:url value="/favicon.ico"/>' />
	<link rel="stylesheet" type="text/css" media="all" href='<c:url value="/css/jquery-ui-1.10.1.custom.min.css"/>' />
	<link rel="stylesheet" type="text/css" media="all" href='<c:url value="/css/anythingzoomer.css"/>' />
	<link rel="stylesheet" type="text/css" media="all" href='<c:url value="/css/master.css"/>' />
	<link rel="stylesheet" type="text/css" media="all" href='<c:url value="/css/style.css"/>' />
	<link rel="stylesheet" type="text/css" media="all" href='<c:url value="/css/miltonian-bitter.css"/>' />
	<link rel="stylesheet" type="text/css" media="all" href='<c:url value="/css/lightbox.css"/>' />
	<link rel="stylesheet" type="text/css" media="all" href='<c:url value="/css/administrador.css"/>' />
	 
	<script type="text/javascript" src='<c:url value="/js/jquery-1.9.1.min.js"/>'></script>
	<script type="text/javascript" src='<c:url value="/js/jquery-ui-1.10.1.custom.min.js"/>'></script>
	<script type="text/javascript" src='<c:url value="/js/modernizr.custom.21750.js"/>'></script>
	<script type="text/javascript" src='<c:url value="/js/StackBlur.js"/>'></script>
	<script type="text/javascript" src='<c:url value="/js/jquery.anythingzoomer.min.js"/>'></script>
	
	<script type="text/javascript" src='<c:url value="/js/master.js"/>'></script>
	
	<title><%= title %></title>
</head>
<body id="almaJazmin" class='<%= showAdminMenu ? "admin" : "" %>'>

	<div class="container" id="<%= pageId %>">    
		<div id="bx-wrapper" class="bx-wrapper">
			<div id="contenedor">
			
	
	
