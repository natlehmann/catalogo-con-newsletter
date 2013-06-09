<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="/WEB-INF/includes/header.jsp">
	<jsp:param value="Alma de Jazmin" name="title"/>
</jsp:include>

<jsp:include page="/WEB-INF/includes/contacto_submenu.jsp">
	<jsp:param value="selected" name="consultas"/>
</jsp:include>


	
<c:if test="${successMsg == null}">

	<div id="contenidoFormulario">
	
		Este espacio es para que vos nos cuentes tu experiencia, ideas, reclamos, etc. 
		Mandanos tus comentarios.
		<br />Te responderemos a la brevedad. Muchas gracias!
		
		<form:form method="POST" action="enviarComentarios.html" id="commentsForm"
			modelAttribute="finalCustomer" commandName="finalCustomer" cssClass="contacto">		
			
			<div>
				<label>Nombre y apellido:</label>
				<form:input path="name" maxlength="250" /> 
				<form:errors path="name" cssClass="errors" element="span" />
			</div>
			
			<div>
				<label>Mail:</label>
				<form:input path="email" maxlength="250" /> 
				<form:errors path="email" cssClass="errors" element="span" />
			</div>
			
			<div>
				<label>Tel.</label>
				<form:input path="phoneNumber" maxlength="250" /> 
				<form:errors path="phoneNumber" cssClass="errors" element="span" />
			</div>
			
			<div>
				<form:textarea path="comment" rows="2" /> 
				<form:errors path="comment" cssClass="errors" element="span" />
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
	


<jsp:include page="/WEB-INF/includes/footer.jsp">
	<jsp:param value="fondoContacto.jpg" name="backgroundImg" />
</jsp:include>
