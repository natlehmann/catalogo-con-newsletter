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

function showSpinner() {
	$('#spinner').show();
}
</script>

<c:set var="showSuccessMsg" value="${success}"/>
<c:set var="openForm" value="${openForm}"/>

<div class="main-content">

<c:choose>
	<c:when test="${showSuccessMsg}">
		<div id="contenidoFormularioFeedback">¡MUCHAS GRACIAS!</div>
	</c:when>
	
	<c:otherwise>
		
		<div id="contenidoFormulario">
		
			<div id="nosotros-texto" style='${openForm ? "display:none" : ""}'>
				<span class="textoResaltado">
				Alma de Jazmín</span> es una nueva propuesta. Creada para mujeres que buscan accesorios, 
				indumentaria, aromas y objetos deco con estilo propio.<br />
				Es un espacio femenino, romántico y fresco.<br />
				Inspirado en el ALMA de las mujeres modernas, alegres y sin edad.<br />
				El espíritu de Alma de Jazmín se basa en las experiencias de viajes y momentos 
				que impulsaron la creación de la marca. A través de texturas, colores y esencias, 
				logramos dejar una huella en cada mujer que trasciende la moda y llega al alma.<br /><br />

				<a onclick="showForm()">Si querés ser parte de nuestro equipo, envianos tu curriculum.</a>
			</div>

                                           
			<div id="nosotros-cv-form" style='${openForm ? "" : "display:none"}'>
			
				Si querés ser parte de Alma de Jazmín, dejanos tus datos y envianos tu currículum. 
				Te responderemos a la brevedad. Muchas gracias!
				
				<form:form method="POST" action="receiveJobCandidate.html" id="receiveJobCandidateForm"
				enctype="multipart/form-data" onsubmit="showSpinner()" cssClass="contacto">
			
				
					<div>
						<label>
							<spring:message code="name.and.lastname" />
						</label>
						
						<form:input path="name" maxlength="255" /> 
						<form:errors path="name" cssClass="errors" element="span" />
					</div>
					
					<div>
						<label>
							Raz&oacute;n Social
						</label>
						<form:input path="companyName" maxlength="255" /> 
					</div>
					
					<div>
						<label>
							<spring:message code="email" />
						</label>
						
						<form:input path="email" maxlength="100" /> 
						<form:errors path="email" cssClass="errors" element="span" />
					</div>
					
					<div>
						<label>
							<spring:message code="phone.number.short" />
						</label>
						
						<form:input path="phoneNumber" maxlength="20" /> 
						<form:errors path="phoneNumber" cssClass="errors" element="span" />
					</div>
					
					<div>
						<label>
							<spring:message code="cv" />
						</label>
						<input type="file" name="cvFile" size="6" />
						<form:errors path="cv" cssClass="errors" element="div" />
					</div>
		
					<br/>
						
					<div id="boton">
						<button type="submit" name="actionBt" value="send" class="create-action">
							<spring:message code="send" />
						</button>
					</div>
					
					<span id="spinner" style="display:none">
						Enviando el mail. Espere un momento por favor ...
					</span>
		
			</form:form>
			</div>
		
			<c:if test="${emailError != null}">
				<div class="error">
					<spring:message code="${emailError}" />
				</div>
			</c:if>
			
		</div>
		
	</c:otherwise>
</c:choose>
	
</div>


<jsp:include page="/WEB-INF/includes/footer.jsp">
	<jsp:param value="fondoNosotros.png" name="backgroundImg"/>
</jsp:include>
