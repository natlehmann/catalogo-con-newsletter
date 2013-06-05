<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="/WEB-INF/includes/header.jsp">
	<jsp:param value="Alma de Jazmin" name="title"/>
</jsp:include>

<jsp:include page="/WEB-INF/includes/locales_submenu.jsp">
	<jsp:param value="selected" name="cabildo"/>
</jsp:include>

<div id="contenido">

	<div class="galeriaLocales">
         <div><img src="images/cabildo01.png"></div>
         <div><img src="images/cabildo02.png"></div>
    </div>
	
</div>


<jsp:include page="/WEB-INF/includes/footer.jsp">
	<jsp:param value="fondoCabildo.jpg" name="backgroundImg" />
</jsp:include>