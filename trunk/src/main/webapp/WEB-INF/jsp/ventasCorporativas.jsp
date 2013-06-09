<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="/WEB-INF/includes/header.jsp">
	<jsp:param value="Alma de Jazmin" name="title"/>
</jsp:include>

<jsp:include page="/WEB-INF/includes/contacto_submenu.jsp">
	<jsp:param value="selected" name="corporativas"/>
</jsp:include>



<c:if test="${successMsg == null}">
	
	<div id="contenidoFormulario">
		Si estás interesada/o en una franquicia o en comercializar nuestros productos, escribinos. 
		Te responderemos a la brevedad. Muchas gracias!
		
		<form:form method="POST" action="enviarVentasCorporativas.html" id="commentsForm"
			modelAttribute="corporateSalesContact" commandName="corporateSalesContact" cssClass="contacto">
			
			<div>
				<label>Empresa:</label>
				<form:input path="name" maxlength="250" /> 
				<form:errors path="name" cssClass="errors" element="span" />
			</div>
			
			<div>
				<label>Razón Social:</label>
				<form:input path="companyName" maxlength="250" cssStyle="width:120px" /> 
				<form:errors path="companyName" cssClass="errors" element="span" />
			</div>
			
			<div>
				<label>Mail:</label>
				<form:input path="email" maxlength="250" /> 
				<form:errors path="email" cssClass="errors" element="span" />
			</div>
			
			<div>
				<label>Tel.</label>
				<form:input path="phoneNumber" maxlength="30" /> 
				<form:errors path="phoneNumber" cssClass="errors" element="span" />
			</div>
			
			<div>
				<label>Rubros</label>
				<form:input path="productCategories" maxlength="250" /> 
				<form:errors path="productCategories" cssClass="errors" element="span" />
			</div>
			
			<div>
				<label>Cant. de productos</label>
				<form:input path="productAmount" maxlength="10" cssStyle="width:80px" /> 
				<form:errors path="productAmount" cssClass="errors" element="span" />
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
