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
	<jsp:param value="adminProductList" name="pageId"/>
</jsp:include>

<script type="text/javascript">
$(function() {
	if ( $('#productCreateForm .errors').length ) {
		showLightbox();
	}
});

function abrirPopUpProductos() {
	var url = $('#context').val() + "admin/productFormInit.html";
	$.post(url, function(data) {
		$('#over').html(data);
		showLightbox();
	});
}

function editarProducto(idProducto) {
	var url = $('#context').val() + "admin/productFormInit.html";
	$.post(url, 
		{ id: idProducto }
	).success(function(data) {
		$('#over').html(data);
		showLightbox();
	});
}

function toggleInput(elemId) {
	$('#' + elemId + '_input').show();
	$('#' + elemId + '_div').hide();
	$('#' + elemId + '_link').hide();
	$('#' + elemId + '_delete_link').hide();
}

function sendImageAction(formId, action) {
	$('#actionParam').val(action);
	$('#' + formId).submit();
}

function closeDialog(dialogId) {
	$('#' + dialogId).dialog("close");
}
</script>



<div class="main-content">


<%
	Map<Category, List<Product>> allProductsByCategory = (Map<Category, List<Product>>)request.getAttribute("allProductsByCategory");	
	List<Product> unassigned = (List<Product>)request.getAttribute("unassigned");
%>

	<div id="submenuAdmin">
    	<div class="botones">
			<a onclick="abrirPopUpProductos()">CREAR</a>
		</div>
	</div>
	
	<div id="contenidoAdmin">
		<div class="catAdmin">
					
	<%
		for (Category category : allProductsByCategory.keySet()) {
	%>
                                    
	
		<h1 style="text-transform: uppercase;">
			<spring:message code="category" /> <%= category.getName() %>
		</h1>
	
		<%
			for (Product product : allProductsByCategory.get(category)) {
		%>

				<div class='thumbnail'>
					<c:if test="<%= product.getThumbnail() != null %>">
						<c:url value="/imageView.html" var="url">
							<c:param name="id" value="<%= String.valueOf(product.getThumbnail().getId()) %>" />
						</c:url>
						<img src="${url}" />
					</c:if>
					
					<div class="actions">
						<c:url value="/admin/deleteProduct.html" var="deleteUrl">
							<c:param name="id" value="<%= String.valueOf(product.getId()) %>" />
						</c:url>
					
						<a onclick="editarProducto(<%= String.valueOf(product.getId()) %>)">
								<img src='<c:url value="/images/adminEditar.png" />' 
								alt="editar" width="12" height="12" title="editar" />
						</a>
						<a href="${deleteUrl}"
							onclick="return confirm('<spring:message code="are.you.sure.you.want.to.delete.this.product" />')">
							<img src='<c:url value="/images/adminCancelar.png" />' 
							alt="eliminar" width="12" height="12" title="eliminar" />
						</a>
					</div>
				</div>

		<%
			}
		
		}
	%>
	
	
	<c:if test="${unassigned != null and not empty unassigned}">
		<h1 style="text-transform: uppercase;">
			<spring:message code="unassigned.products" /> 
		</h1>
	</c:if>

	<%
		for (Product product : unassigned) {
	%>
	
		<div class='thumbnail'>
			<c:if test="<%= product.getThumbnail() != null %>">
				<c:url value="/imageView.html" var="url">
					<c:param name="id" value="<%= String.valueOf(product.getThumbnail().getId()) %>" />
				</c:url>
				<img src="${url}" />
			</c:if>
			
			<div class="actions">
				<c:url value="/admin/deleteProduct.html" var="deleteUrl">
					<c:param name="id" value="<%= String.valueOf(product.getId()) %>" />
				</c:url>
			
				<a onclick="editarProducto(<%= String.valueOf(product.getId()) %>)">
						<img src='<c:url value="/images/adminEditar.png" />' 
						alt="editar" width="12" height="12" title="editar" />
				</a>
				<a href="${deleteUrl}"
					onclick="return confirm('<spring:message code="are.you.sure.you.want.to.delete.this.product" />')">
					<img src='<c:url value="/images/adminCancelar.png" />' 
					alt="eliminar" width="12" height="12" title="eliminar" />
				</a>
			</div>
		</div>
	<%
		}
	%>

		</div>
	</div>
</div>


<input type='hidden' id='context' value='<c:url value="/" />' />

<jsp:include page="/WEB-INF/includes/footer.jsp">
	<jsp:param value="fondoVarios.jpg" name="backgroundImg" />
	<jsp:param value="true" name="includeOverBox"/>
	<jsp:param value="/WEB-INF/jsp/admin/productForm.jsp" name="overBoxContent"/>
</jsp:include>



