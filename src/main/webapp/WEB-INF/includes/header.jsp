<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String title = request.getParameter("title") != null ? request.getParameter("title") : "Alma de Jazmin";
	String showAdminMenu = request.getParameter("showAdminMenu");
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
	<link rel="stylesheet" type="text/css" media="all" href='<c:url value="/css/master.css"/>' />
	<link rel="stylesheet" type="text/css" media="all" href='<c:url value="/css/style.css"/>' />
	<link rel="stylesheet" type="text/css" media="all" href='<c:url value="/css/miltonian-bitter.css"/>' />
	<link rel="stylesheet" type="text/css" media="all" href='<c:url value="/css/lightbox.css"/>' />
	<link rel="stylesheet" type="text/css" media="all" href='<c:url value="/css/administrador.css"/>' />
	 
	<script type="text/javascript" src='<c:url value="/js/jquery-1.9.1.min.js"/>'></script>
	<script type="text/javascript" src='<c:url value="/js/jquery-ui-1.10.1.custom.min.js"/>'></script>
	<script type="text/javascript" src='<c:url value="/js/modernizr.custom.21750.js"/>'></script>
	<script type="text/javascript" src='<c:url value="/js/StackBlur.js"/>'></script>
	
	<script type="text/javascript" src='<c:url value="/js/master.js"/>'></script>
	
	<title><%= title %></title>
</head>
<body id="almaJazmin">

	<div class="container" id="<%= pageId %>">    
		<div id="bx-wrapper" class="bx-wrapper">
			<div id="contenedor">
				<div class="twitter">
					<a href="#">
						<img src='<c:url value="/images/twitter.png"/>' width='29' height='28' border='0' />
					</a>
				</div>
				<div class="facebook">
					<a href="#">
						<img src='<c:url value="/images/facebook.png"/>' width='29' height='28' border='0' />
					</a>
				</div>            
				
				<c:if test="<%= showAdminMenu == null %>">
					<div id="menu">
				
						<div><a href="home.html">HOME</a></div>
	                   	<div><a href="nosotros.html">NOSOTROS</a></div>
	                   	<div><a href="productos.html">PRODUCTOS</a> </div>   
	                   	<div><a href="locales.html">LOCALES</a> </div>
	                   	<div><a href="contacto.html">CONTACTO</a></div>
	                   	<div><a href="prensa.html">PRENSA</a></div>   
	                </div>
	            </c:if>
	                
	            <c:if test="<%= showAdminMenu != null %>">
	            	<div id="menuAdmin">
						<div><a href="productList.html">PRODUCTOS</a></div>
						<div><a href="categoryList.html">CATEGORIAS</a></div>
						<div><a href='<c:url value="/index.html"/>'>VER WEBSITE</a></div>
					</div>
				</c:if>
				

	
	
