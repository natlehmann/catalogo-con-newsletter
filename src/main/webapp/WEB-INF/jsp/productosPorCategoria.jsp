<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<div class="main-content">
	<c:forEach var="product" items="${products}">
		<div>
			<c:if test="${product.thumbnail != null}">
				<c:url value="imageView.html" var="url">
					<c:param name="id" value="${product.thumbnail.id}" />
				</c:url>
				<img src="${url}" width="100" height="100" />
				
				<c:url value="imageView.html" var="url_big">
					<c:param name="id" value="${product.firstBigImage.id}" />
				</c:url>
				<img src="${url_big}" />
			</c:if>
			
		</div>
	</c:forEach>
</div>
