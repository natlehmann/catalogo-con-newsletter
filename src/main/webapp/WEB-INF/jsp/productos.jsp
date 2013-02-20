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
	$(elem).addClass('sectorLink-selected');	
}

function displayProducts(data) {
	$('#linea').html(data);
	$('#linea').show();
}

function cleanSelectedCategory() {
	$('a').removeClass('sectorLink-selected');
}
</script>


<div class="main-content">
	<c:forEach var="category" items="${categories}">
		<div>
			<a href="#" onclick="showProducts(${category.id}, this)">
				${category.name}
			</a>
		</div>
	</c:forEach>
	
	<div id="linea" style="display:none;"></div>
</div>


<jsp:include page="/WEB-INF/includes/footer.jsp" />
