<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="/WEB-INF/includes/header.jsp">
	<jsp:param value="Alma de Jazmin" name="title"/>
</jsp:include>

<script type="text/javascript">
$(document).ready(function() {
	var imagenes = [];
	var i = 0;

	var contexto = $('#contextoImagenes').val();

	imagenes[i++] = "/fondoAccesorios.jpg";
	imagenes[i++] = "/fondoAnillos.jpg";
	imagenes[i++] = "/fondoBilleteras.jpg";
	imagenes[i++] = "/fondoCabildo.jpg";
	imagenes[i++] = "/fondoCarteras.jpg";
	imagenes[i++] = "/fondoCollares.jpg";
	imagenes[i++] = "/fondoCombinados.jpg";
	imagenes[i++] = "/fondoContacto.jpg";
	imagenes[i++] = "/fondoLocales.jpg";
	imagenes[i++] = "/fondoNosotros.jpg";
	imagenes[i++] = "/fondoPrensa.jpg";
	imagenes[i++] = "/fondoProductos.jpg";
	imagenes[i++] = "/fondoPulseras.jpg";
	imagenes[i++] = "/fondoRivadavia.jpg";	
	imagenes[i++] = "/fondoRopa.jpg";
	imagenes[i++] = "/fondoSantaFe.jpg";
	imagenes[i++] = "/fondoVarios.jpg";
	imagenes[i++] = "/fotoGaleria.png";
	imagenes[i++] = "/fotoGaleria1.png";
	imagenes[i++] = "/fotoGaleria2.png";

	$.each(imagenes, function(index, value) {
		$.ajax({
			url: contexto + value,
			async : true
		});
	});
	
	
});
</script>

<input type="hidden" name="contextoImagenes" value='<c:url value="/images"/>' id="contextoImagenes" />

<jsp:include page="/WEB-INF/includes/footer.jsp">
	<jsp:param value="fondoHome.jpg" name="backgroundImg"/>
</jsp:include>
