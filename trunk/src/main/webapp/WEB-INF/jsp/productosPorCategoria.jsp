<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<div>
	<c:forEach var="product" items="${products}">
		<div>
			${product.name}
			<c:if test="${product.smallImage != null}">
				<c:url value="imageView.html" var="url">
					<c:param name="id" value="${product.smallImage.id}" />
				</c:url>
				<img src="${url}" width="200" height="200" />
			</c:if>
			
		</div>
	</c:forEach>
</div>

