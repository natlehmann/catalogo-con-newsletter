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
		El email se ha enviado con exito.
	</c:when>
	
	<c:otherwise>
		<div id="nosotros-texto">
			BLAH BLAH BLAH
		</div>
		
		<a onclick="showForm()">Envianos tu CV</a>
		
		<div id="nosotros-cv-form" style='${openForm ? "" : "display:none"}'>
			
			<form:form method="POST" action="receiveJobCandidate.html" id="receiveJobCandidateForm"
			enctype="multipart/form-data" onsubmit="showSpinner()">
		
			
				<div>
					<spring:message code="name.and.lastname" />
					
					<form:input path="name" maxlength="255" size="22" cssClass="left" /> 
					<div class="required left"><br/></div>
					<form:errors path="name" cssClass="errors left" element="div" />
				</div>
				
				<div>
					<spring:message code="email" />
					
					<form:input path="email" maxlength="100" size="22" cssClass="left" /> 
					<div class="required left"><br/></div>
					<form:errors path="email" cssClass="errors left" element="div" />
				</div>
				
				<div>
					<spring:message code="cv" />
					<input type="file" name="cvFile" class="left" size="30"/>
					<form:errors path="cv" cssClass="errors left" element="div" />
				</div>
	
				<br/>
				
				<div>
					<div class="required left"><br/></div>
					<spring:message code="required" />
				</div>
					
	
				<button type="submit" name="actionBt" value="send" class="create-action">
					<spring:message code="send" />
				</button>
				
				<div id="spinner" style="display:none">
					Enviando el mail. Espere un momento por favor ...
				</div>
	
		</form:form>
		</div>
		
		<c:if test="${emailError != null}">
			<div class="error">
				<spring:message code="${emailError}" />
			</div>
		</c:if>
		
	</c:otherwise>
</c:choose>
	
</div>


<jsp:include page="/WEB-INF/includes/footer.jsp" />
