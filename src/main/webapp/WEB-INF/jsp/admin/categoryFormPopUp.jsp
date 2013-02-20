<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="ar.com.almaDeJazmin.website.domain.Category"%>			
						
<table>
	<tr>
		<td><spring:message code="category.name" /></td>
		<td>
			<input type="text" name="category_name" maxlength="250" id="category_name"
				value='<%= request.getParameter("param_category_name") != null ? request.getParameter("param_category_name") : "" %>' /> 
		</td>
	</tr>
	
	<tr>
		<td colspan="2"><div class="spacer-vert"><br/></div></td>
	</tr>
	

	<tr>
		<td colspan="2">
			<div class="right">
				<div class="left cancel">
					<a href="#" onclick="closeDialog('newCategory')" class="left">
						<spring:message code="cancel" />
					</a>
				</div>
				
				<span class="spacer-horiz left"><br/></span>
				
				<div class="ok left">
					<a href="#" onclick="acceptNewCategory()" >
						<spring:message code="accept" />
					</a>
				</div>
			</div>
		</td>
	</tr>

</table>
