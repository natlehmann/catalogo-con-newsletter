<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="/WEB-INF/includes/header.jsp">
	<jsp:param value="Alma de Jazmin" name="title"/>
</jsp:include>

<script type="text/javascript">
function showProducts(categoryId, elem) {
	$.post("showProductsByCategory.html", { 'categoryId': categoryId }, displayProducts, 'html' );
	cleanSelectedCategory();
	$(elem).addClass('selected');	
}

function displayProducts(data) {
	$('#contenido').html(data);
	$('#contenido').show();
}

function cleanSelectedCategory() {
	$('#submenu a').removeClass('selected');
}
</script>                                     
                                           
                                           
<div id="submenu">
	<c:forEach var="category" items="${categories}" varStatus="status">
		<div class="${status.count eq categories.size() ? 'botonesultimo' : 'botones' }">
			<a href="#" onclick="showProducts(${category.id}, this)">
				${category.name}
			</a>
		</div>
	</c:forEach>
</div>

<div id="contenido" style="display:none;"></div>


<jsp:include page="/WEB-INF/includes/footer.jsp">
	<jsp:param value="fondoProductos.jpg" name="backgroundImg" />
</jsp:include>
