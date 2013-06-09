<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String selected = request.getParameter("selected") != null ? request.getParameter("selected") : "";
%>

<div id="submenu">
	<div class="botones">
		
		<c:choose>
			<c:when test="<%= selected.equals(\"consultas\") %>">
				<div class="submenuSelect">Consultas</div> 
			</c:when>
			<c:otherwise>
				<a href="tusComentarios.html">Consultas</a> 
			</c:otherwise>
		</c:choose>
	</div>
	
	<div class="botones">
		<c:choose>
			<c:when test="<%= selected.equals(\"franquicias\") %>">
				<div class="submenuSelect">Franquicias y Venta x mayor</div> 
			</c:when>
			<c:otherwise>
				<a href="franquiciasYmayoristas.html">Franquicias y Venta x mayor</a>
			</c:otherwise>
		</c:choose>
	</div>
	
	<div class="botonesultimo">
		<c:choose>
			<c:when test="<%= selected.equals(\"corporativas\") %>">
				<div class="submenuSelectUltimo">Ventas Corporativas</div> 
			</c:when>
			<c:otherwise>
				<a href="ventasCorporativas.html">Ventas Corporativas</a>
			</c:otherwise>
		</c:choose>
	</div>

</div>