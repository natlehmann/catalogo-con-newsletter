<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="/WEB-INF/includes/header.jsp">
	<jsp:param value="Alma de Jazmin" name="title"/>
</jsp:include>

<script type="text/javascript">
function showForm() {
	$('#nosotros-texto').hide();
	$('#nosotros-cv-form').show();
}
</script>

<div class="main-content">
	
	<div id="nosotros-texto">
		BLAH BLAH BLAH
	</div>
	
	<a onclick="showForm()">Envianos tu CV</a>
	
	<div id="nosotros-cv-form">
		Nombre y apellido
		
	</div>
</div>


<jsp:include page="/WEB-INF/includes/footer.jsp" />
