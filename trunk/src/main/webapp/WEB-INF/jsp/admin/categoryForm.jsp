<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="content">	
	<form:form method="POST" action="" id="categoryCreateForm"
		modelAttribute="category" commandName="category" cssClass="contacto">
		
		<c:if test="${category.id != null}">
			<form:hidden path="id"/>
		</c:if>
		
		
		<div>
			<label>CATEGORIA</label>
			<form:input path="name" maxlength="250" /> 
		</div>
	
	</form:form>
	
		
	<c:choose>
		<c:when test="${category.id == null}">
		
			<a onclick="sendAction('categoryCreateForm', 'createCategory.html')">
				<img src='<c:url value="/images/adminBotOk.png" />' alt="" width="25" height="21" />
			</a>
			
		</c:when>
		<c:otherwise>
		
			<a onclick="sendAction('categoryCreateForm', 'updateCategory.html')">
				<img src='<c:url value="/images/adminBotOk.png" />' alt="" width="25" height="21" />
			</a>
			
		</c:otherwise>
	</c:choose>
	
	<a onclick="hideLightbox()" >
		<img src='<c:url value="/images/adminBotCancel.png" />' alt="" width="25" height="21" />
	</a>

	
</div>
