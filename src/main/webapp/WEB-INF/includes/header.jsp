<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String title = request.getParameter("title") != null ? request.getParameter("title") : "Alma de Jazmin";
	String showAdminMenu = request.getParameter("showAdminMenu");
	String pageId = request.getParameter("pageId") != null ? request.getParameter("pageId") : "page_id";
%>

<jsp:include page="/WEB-INF/includes/head.jsp">
	<jsp:param value="<%= title %>" name="title"/>
	<jsp:param value="<%= showAdminMenu %>" name="showAdminMenu"/>
	<jsp:param value="<%= pageId %>" name="pageId"/>
</jsp:include>
			
				<c:if test="<%= showAdminMenu == null %>">
				
					<div class="twitter">
						<a href="#">
							<img src='<c:url value="/images/twitter.png"/>' width='29' height='28' border='0' />
						</a>
					</div>
					<div class="facebook">
						<a href="#">
							<img src='<c:url value="/images/facebook.png"/>' width='29' height='28' border='0' />
						</a>
					</div>            
				
					<div id="menu">
				
						<div><a href="home.html">HOME</a></div>
	                   	<div><a href="nosotros.html">NOSOTROS</a></div>
	                   	<div><a href="productos.html">PRODUCTOS</a> </div>   
	                   	<div><a href="locales.html">LOCALES</a> </div>
	                   	<div><a href="contacto.html">CONTACTO</a></div>
	                   	<div><a href="prensa.html">PRENSA</a></div>   
	                </div>
	            </c:if>
	                
	            <c:if test="<%= showAdminMenu != null %>">
	            	<div id="menuAdmin">
						<div><a href="productList.html">PRODUCTOS</a></div>
						<div><a href="categoryList.html">CATEGORIAS</a></div>
						<div><a href='<c:url value="/index.html"/>'>VER WEBSITE</a></div>
					</div>
				</c:if>
				

	
	
