<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="ar.com.almaDeJazmin.website.domain.Product"%>
<%@page import="java.util.List"%>
<%@page import="ar.com.almaDeJazmin.website.domain.Category"%>
<%@page import="java.util.Map"%>




<c:if test="${warning != null}">
	<script type="text/javascript">
	
	$(function() {
		$('#warning').dialog({ 
			title: '<spring:message code="warning" />',
			resizable: false,
			width: 300,
			dialogClass: 'error-msg'
		 });
	});
	</script>	
</c:if>



	<c:if test="${warning != null}">
		<div class="warning" id="warning" style="display:none;">
			<div class="right link-container">
				<a href="#" onclick="closeDialog('warning')">x</a>
			</div>
			<div class="msg">
				<div><spring:message code="warning" /></div>
				<spring:message code="${warning}" />
			</div>
		</div>
	</c:if>


<c:if test="${product != null }">
<div class="content">
	<form:form method="POST" action="productCreate.html" id="productCreateForm"
		enctype="multipart/form-data" cssClass="contacto">
		
		<c:if test="${product.id != null}">
			<form:hidden path="id"/>
		</c:if>
	
		<input type="hidden" name="action" value="" id="actionParam" />
		<input type="hidden" name="param_category_name" value="" id="param_category_name" />
		<input type="hidden" name="param_categories" value="" id="param_categories" />
		
		<form:errors path="thumbnail" cssClass="errors" element="span" />
		
		<div id="selectCategory">
			<label>CATEGORIA</label>
			<form:checkboxes items="${categories}" path="categoryNames" cssClass="categories"
						delimiter="<br/>"  itemValue="id" itemLabel="name"/>
		</div>

		<div>
			<label>FOTO</label>
			<c:choose>
				<c:when test="${product.thumbnail == null}">
					<input type="file" name="smallImageFile" size="30"/>
				</c:when>
				<c:otherwise>
					<input type="file" name="smallImageFile" class="left" size="30" 
						id="smallImageFile_input" style="display: none;" />
						
					<div id="smallImageFile_div" class="disabled-input">
						${product.thumbnail.fileName}
					</div>
					
					<a href="#" onclick="toggleInput('smallImageFile')" class="agregarLink edit"
						id="smallImageFile_link" title='<spring:message code="change" />'>
					</a>
					
					<a href='#' onclick="sendImageAction('productCreateForm', 'deleteSmallImage')" 
						class="agregarLink delete" id="smallImageFile_delete_link"
						title='<spring:message code="delete" />'>
					</a>
						
				</c:otherwise>
			</c:choose>
		</div> 	
		
	</form:form>
		
		
	<c:choose>
		<c:when test="${product.id == null}">
		
			<a onclick="sendImageAction('productCreateForm', 'create')" >
				<img src='<c:url value="/images/adminBotOk.png" />' alt="" width="25" height="21" />
			</a>
			
		</c:when>
		<c:otherwise>
		
			<a onclick="sendImageAction('productCreateForm', 'update')">
				<img src='<c:url value="/images/adminBotOk.png" />' alt="" width="25" height="21" />
			</a>
			
		</c:otherwise>
	</c:choose>
	
	<a onclick="hideLightbox()" >
		<img src='<c:url value="/images/adminBotCancel.png" />' alt="" width="25" height="21" />
	</a>	
		

</div>
</c:if>

