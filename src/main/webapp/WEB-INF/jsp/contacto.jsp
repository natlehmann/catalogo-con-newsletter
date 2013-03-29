<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="/WEB-INF/includes/header.jsp">
	<jsp:param value="Alma de Jazmin" name="title"/>
</jsp:include>


<div class="main-content">
	<a href="tusComentarios.html">Tus Comentarios</a>
	<br/>
	<a href="franquiciasYmayoristas.html">Franquicias y mayoristas</a>
	<br/>
	<a href="ventasCorporativas.html">Ventas Corporativas</a>
</div>


<jsp:include page="/WEB-INF/includes/footer.jsp" />