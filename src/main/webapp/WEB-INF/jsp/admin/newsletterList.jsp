<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="/WEB-INF/includes/header.jsp">
	<jsp:param value="Alma de Jazmin" name="title"/>
	<jsp:param value="true" name="showAdminMenu"/>
</jsp:include>


<div class="main-content">

	<div class="actions">
		<button onclick="window.location='newsletterFormInit.html'">
			<spring:message code="create.new.newsletter" />
		</button>
	</div>
	
	<table cellpadding="0" cellspacing="0" border="0">
					
		<tr>
			<th>Nombre</th>
			<th>Subject</th>
			<th><br/></th>
		</tr>
	

		<c:forEach items="${newsletters}" var="newsletter">
			<tr>
				<td>
					${newsletter.name} 
				</td>
				<td>
					${newsletter.subject} 
				</td>
				<td class="actions">
					<c:url value="/admin/newsletterPreview.html" var="previewUrl">
						<c:param name="id" value="${newsletter.id}" />
					</c:url>
					<div class="left preview">
						<a href="${previewUrl}">
							<spring:message code="preview.and.send" />
						</a>
					</div>
					
					<c:url value="/admin/newsletterFormInit.html" var="editUrl">
						<c:param name="id" value="${newsletter.id}" />
					</c:url>
					<div class="left edit">
						<a href="${editUrl}">
							<spring:message code="edit" />
						</a>
					</div>
					
					<c:url value="/admin/copyNewsletter.html" var="copyUrl">
						<c:param name="id" value="${newsletter.id}" />
					</c:url>
					<div class="left copy">
						<a href="${copyUrl}">
							<spring:message code="copy" />
						</a>
					</div>
					
					<c:url value="/admin/deleteNewsletter.html" var="deleteUrl">
						<c:param name="id" value="${newsletter.id}" />
					</c:url>
					<div class="delete left">
						<a href="${deleteUrl}" 
							onclick="return confirm('<spring:message code="are.you.sure.you.want.to.delete.this.newsletter" />')">
							<spring:message code="delete" />
						</a>
					</div>
				</td>
			</tr>
		</c:forEach>
		
		</table>
</div>


<jsp:include page="/WEB-INF/includes/footer.jsp" />

