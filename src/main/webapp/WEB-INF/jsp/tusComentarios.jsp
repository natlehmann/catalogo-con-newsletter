<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="/WEB-INF/includes/header.jsp">
	<jsp:param value="Alma de Jazmin" name="title"/>
</jsp:include>


<div class="main-content">

	<c:if test="${successMsg == null}">
		<form:form method="POST" action="enviarComentarios.html" id="commentsForm"
			modelAttribute="finalCustomer" commandName="finalCustomer">		
			
			Nombre y apellido:
			<form:input path="name" maxlength="250" size="22" /> 
			<div class="required left"><br/></div>
			<form:errors path="name" cssClass="errors left" element="div" />
			
			Email:
			<form:input path="email" maxlength="250" size="22" /> 
			<div class="required left"><br/></div>
			<form:errors path="email" cssClass="errors left" element="div" />
			
			Comentario:
			<form:textarea path="comment" rows="6" /> 
			<div class="required left"><br/></div>
			<form:errors path="comment" cssClass="errors left" element="div" />
	
			<div class="required left"><br/></div>
			<spring:message code="required" />
	
		
			<div class="actions">
				
				<button type="submit"" value="send" >
					<spring:message code="send" />
				</button>
		
			</div>
	
		</form:form>
	</c:if>
	
	<c:if test="${successMsg != null}">
		${successMsg}
	</c:if>
</div>


<jsp:include page="/WEB-INF/includes/footer.jsp" />
