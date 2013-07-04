<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="/WEB-INF/includes/header.jsp">
	<jsp:param value="Alma de Jazmin" name="title"/>
	<jsp:param value="true" name="showAdminMenu"/>
</jsp:include>

<script type="text/javascript">
function abrirPopUpCategoria() {
	var url = $('#context').val() + "admin/categoryFormInit.html";
	$.post(url, function(data) {
		$('#over').html(data);
		showLightbox();
	});
}

function editarCategoria(idCategoria) {
	var url = $('#context').val() + "admin/categoryFormInit.html";
	$.post(url, 
		{ id: idCategoria }
	).success(function(data) {
		$('#over').html(data);
		showLightbox();
	});
}

function sendAction(formId, action) {
	$('#' + formId).attr('action', action);
	$('#' + formId).submit();
}
</script>

<div class="main-content">
	
	<div id="submenuAdmin">
    	<div class="botones">
			<a onclick="abrirPopUpCategoria()">CREAR</a>
		</div>
	</div>
	
	<div id="contenidoAdmin">
		<div class="catAdmin">
			
			<h1>CATEGORIAS</h1>
			<ul>

				<c:forEach items="${categories}" var="category">
				
					<li>
						${category.name} 
						
						<c:url value="/admin/deleteCategory.html" var="deleteUrl">
							<c:param name="id" value="${category.id}" />
						</c:url>
						
						<DIV>
							<a onclick="editarCategoria(${category.id})">
								<img src='<c:url value="/images/adminEditar.png" />' alt="" width="12" height="12" />
							</a>
							<a href="${deleteUrl}"
								onclick="return confirm('<spring:message code="are.you.sure.you.want.to.delete.this.category" />')">
								<img src='<c:url value="/images/adminCancelar.png" />' alt="" width="12" height="12" />
							</a>
						</DIV>
					</li>
					
				</c:forEach>
			
			</ul>
		
		</div>
		
	</div>
		
</div>

<input type='hidden' id='context' value='<c:url value="/" />' />


<jsp:include page="/WEB-INF/includes/footer.jsp">
	<jsp:param value="fondoVarios.jpg" name="backgroundImg" />
	<jsp:param value="true" name="includeOverBox"/>
</jsp:include>

