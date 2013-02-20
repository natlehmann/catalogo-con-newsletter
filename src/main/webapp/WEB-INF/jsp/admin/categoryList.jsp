<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="/WEB-INF/includes/header.jsp">
	<jsp:param value="Alma de Jazmin" name="title"/>
	<jsp:param value="true" name="showAdminMenu"/>
</jsp:include>


<div style="margin-left:120px;">

	<div class="actions">
		<button onclick="window.location='categoryFormInit.html'">
			<spring:message code="create.new.category" />
		</button>
	</div>
	
	<table cellpadding="0" cellspacing="0" border="0">
					
		<tr>
			<th>Nombre</th>
			<th><br/></th>
		</tr>
	

		<c:forEach items="${categories}" var="category">
			<tr>
				<td>
					${category.name} 
				</td>
				<td class="actions">
					<c:url value="/admin/categoryFormInit.html" var="editUrl">
						<c:param name="id" value="${category.id}" />
					</c:url>
					<div class="left edit">
						<a href="${editUrl}">
							<spring:message code="edit" />
						</a>
					</div>
					
					<c:url value="/admin/deleteCategory.html" var="deleteUrl">
						<c:param name="id" value="${category.id}" />
					</c:url>
					<div class="delete left">
						<a href="${deleteUrl}" 
							onclick="return confirm('<spring:message code="are.you.sure.you.want.to.delete.this.category" />')">
							<spring:message code="delete" />
						</a>
					</div>
				</td>
			</tr>
		</c:forEach>
		
		</table>
</div>


<jsp:include page="/WEB-INF/includes/footer.jsp" />

