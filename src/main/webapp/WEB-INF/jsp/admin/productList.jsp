<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="ar.com.almaDeJazmin.website.domain.Product"%>
<%@page import="java.util.List"%>
<%@page import="ar.com.almaDeJazmin.website.domain.Category"%>
<%@page import="java.util.Map"%>

<jsp:include page="/WEB-INF/includes/header.jsp">
	<jsp:param value="Alma de Jazmin" name="title"/>
	<jsp:param value="true" name="showAdminMenu"/>
</jsp:include>


<div style="margin-left:120px;">


<%
	Map<Category, List<Product>> allProductsByCategory = (Map<Category, List<Product>>)request.getAttribute("allProductsByCategory");	
	List<Product> unassigned = (List<Product>)request.getAttribute("unassigned");
%>

				
	<div class="actions">
		<button onclick="window.location='productFormInit.html'">
			<spring:message code="create.new.product" />
		</button>
	</div>
					
	<%
		for (Category category : allProductsByCategory.keySet()) {
	%>
	
		<div class="category-title">
			<spring:message code="category" /> <%= category.getName() %>
		</div>
	
		<table cellpadding="0" cellspacing="0" border="0">
		<%
			for (Product product : allProductsByCategory.get(category)) {
		%>

				
			<tr class="table-cell">
				<td class="code">
					<%= product.getCode() %>
				</td>
				<td class="name">
					<%= product.getName() %>
				</td>
				<td class="image">
					<c:if test="<%= product.getSmallImage() != null %>">
						<c:url value="/imageView.html" var="url">
							<c:param name="id" value="<%= String.valueOf(product.getSmallImage().getId()) %>" />
						</c:url>
						<img src="${url}" width="80" height="80" />
					</c:if>
				</td>
						
				<td class="actions">
					<c:url value="/admin/productFormInit.html" var="editUrl">
						<c:param name="id" value="<%= String.valueOf(product.getId()) %>" />
					</c:url>
					<div class="left edit">
						<a href="${editUrl}">
							<spring:message code="edit" />
						</a>
					</div>
					
					<c:url value="/admin/deleteProduct.html" var="deleteUrl">
						<c:param name="id" value="<%= String.valueOf(product.getId()) %>" />
					</c:url>
					<div class="delete left">
						<a href="${deleteUrl}" 
							onclick="return confirm('<spring:message code="are.you.sure.you.want.to.delete.this.product" />')">
							<spring:message code="delete" />
						</a>
					</div>
				</td>
			</tr>
		<%
			}
		%>
		</table>
		
		<br/>
		
	<%
		}
	%>
	
	
	<c:if test="${unassigned != null and not empty unassigned}">
		<div class="category-title">
			<spring:message code="unassigned.products" /> 
		</div>
	</c:if>

	<table cellpadding="0" cellspacing="0" border="0" class="contenidoTexto">
	<%
		for (Product product : unassigned) {
	%>
			
		<tr class="table-cell">
			<td class="code">
				<%= product.getCode() %>
			</td>
			<td class="name">
				<%= product.getName() %>
			</td>
			<td class="image">
				<c:if test="<%= product.getSmallImage() != null %>">
					<c:url value="/imageView.html" var="url">
						<c:param name="id" value="<%= String.valueOf(product.getSmallImage().getId()) %>" />
					</c:url>
					<img src="${url}" width="80" height="80" />
				</c:if>
			</td>
					
			<td class="actions">
				<c:url value="/admin/productFormInit.html" var="editUrl">
					<c:param name="id" value="<%= String.valueOf(product.getId()) %>" />
				</c:url>
				<a href="${editUrl}">
					<spring:message code="edit" />
				</a>
				
				<c:url value="/admin/deleteProduct.html" var="deleteUrl">
					<c:param name="id" value="<%= String.valueOf(product.getId()) %>" />
				</c:url>
				<a href="${deleteUrl}" 
					onclick="return confirm('<spring:message code="are.you.sure.you.want.to.delete.this.product" />')">
					<spring:message code="delete" />
				</a>
			</td>
		</tr>
	<%
		}
	%>
	</table>


</div>


<jsp:include page="/WEB-INF/includes/footer.jsp" />



