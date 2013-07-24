<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="/WEB-INF/includes/header.jsp">
	<jsp:param value="Alma de Jazmin" name="title"/>
    <jsp:param value="formularioFranquicias" name="pageId"/>
</jsp:include>

<jsp:include page="/WEB-INF/includes/contacto_submenu.jsp">
	<jsp:param value="selected" name="franquicias"/>
</jsp:include>

<script type="text/javascript">
$(function() {
	if ($("#over").length) {
		showLightbox();
	}
});
</script>


<c:if test="${successMsg == null}">

	<div id="contenidoFormularioContacto">
		Si estás interesada/o en una franquicia o en comercializar nuestros productos, escribinos. 
		Te responderemos a la brevedad. Muchas gracias!
		
		<form:form method="POST" action="enviarFranquiciasYMayoristas.html" id="commentsForm"
			modelAttribute="retailer" commandName="retailer" cssClass="contacto">		
			
			<div>
				<label>Empresa</label>
				<form:input path="name" maxlength="250" cssStyle="width:60%;" /> 
			</div>
			
			<div>
				<label>Razón Social</label>
				<form:input path="companyName" maxlength="250" cssStyle="width:120px" /> 
			</div>
			
			<div>
				<label>Mail</label>
				<form:input path="email" maxlength="100" cssStyle="width:70%;" /> 
			</div>
			
			<div>
				<label>Tel.</label>
				<form:input path="phoneNumber" maxlength="20" cssStyle="width:70%;" /> 
			</div>
			
			<div>
				<label>Rubros</label>
				<form:input path="productCategories" maxlength="250" cssStyle="width:64%;" /> 
			</div>
			
			<div>
				<label>Cant. de productos</label>
				<form:input path="productAmount" maxlength="10" cssStyle="width:80px" /> 
			</div>
			
			<div>
				<form:textarea path="comment" rows="2" /> 
			</div>
	
		
			<div id="boton">
				
				<button type="submit"" value="send" >
					<spring:message code="send" />
				</button>
		
			</div>
	
		</form:form>
		
	</div>
		
</c:if>
	
<c:if test="${successMsg != null}">
	<div id="contenidoFormularioFeedback">
    	¡MUCHAS GRACIAS! ENVIAREMOS UNA RESPUESTA A LA BREVEDAD.
    </div>
</c:if>

<c:if test="${errorMsg != null}">
	<div id="over" class="overbox">
		<div class="content">
			${errorMsg}<br /><br /><br /><br />
			<a href="javascript:hideLightbox();" >cerrar</a>
		</div>
	</div>
	<div id="fade" class="fadebox"></div>
</c:if>


<jsp:include page="/WEB-INF/includes/footer.jsp">
	<jsp:param value="fondoContacto.jpg" name="backgroundImg" />
</jsp:include>
