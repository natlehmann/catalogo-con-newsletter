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


<div class="main-content">


<script type="text/javascript">

	$(function() {
		setCategoryValues();
	});

	function show(elemId) {
		$('#' + elemId).show();
	}

	function sendImageAction(formId, action, orderNumber) {
		$('#actionParam').val(action);
		$('#' + formId).submit();
	}

	function openNewCategory() {
		$('#newCategory').dialog({ 
			title: '<spring:message code="enter.new.category" />',
			resizable: false,
			width: 320
		 });
	}

	function openSelectCategory() {
		$('#selectCategory').dialog({ 
			title: '<spring:message code="category.selection" />',
			resizable: false,
			width: 320
		 });
	}

	function closeDialog(dialogId) {
		$('#' + dialogId).dialog("close");
	}

	function acceptNewCategory() {

		closeDialog('newCategory');
		setCategoryValues();
	}

	function setCategoryValues() {

		// take dafault
		var defaultValue = $('#category_name').val();

		if (defaultValue != '') {
			$('#new_category_holder').html(defaultValue);
		}
		
	}

	function copyParameters() {
		$('#param_category_name').val( $('#category_name').val() );

		var selectedCategories = '';
		$('input:checked').each(function(index) {
			if (index > 0) {
				selectedCategories = selectedCategories + ',';
			}
			selectedCategories = selectedCategories + $(this).val();
		  });

		$('#param_categories').val(selectedCategories);

		$('#selectCategory').html('');
	}

	function toggleInput(elemId) {
		$('#' + elemId + '_input').show();
		$('#' + elemId + '_div').hide();
		$('#' + elemId + '_link').hide();
		$('#' + elemId + '_delete_link').hide();
	}

	function closeDialog(dialogName) {
		$('#' + dialogName).dialog('close');
	}
		
</script>


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

	<form:form method="POST" action="productCreate.html" id="productCreateForm"
		enctype="multipart/form-data" onsubmit="copyParameters()">
		
		<c:if test="${product.id != null}">
			<form:hidden path="id"/>
		</c:if>
	
		<input type="hidden" name="action" value="" id="actionParam" />
		<input type="hidden" name="param_category_name" value="" id="param_category_name" />
		<input type="hidden" name="param_categories" value="" id="param_categories" />
		
		
		<div id="selectCategory" style="display: none;">
			<div class="right link-container">
				<a href="#" onclick="closeDialog('selectCategory')">x</a>
			</div>
			<div class="msg">
				<div class="inner-title">
					<spring:message code="please.select.the.product.categories" />
				</div>
				<form:checkboxes items="${categories}" path="categories" cssClass="categories"
						delimiter="<br/>"  itemValue="id" itemLabel="name"/>
			</div>
				
		</div>
		
		
		<div id="newCategory" style="display: none;">
			<div class="right link-container">
				<a href="#" class="Sector2Link" onclick="closeDialog('newCategory')">x</a>
			</div>
			<div class="msg">
				<div class="inner-title">
					<spring:message code="please.enter.a.new.category" />
				</div>
				<jsp:include page="/WEB-INF/jsp/admin/categoryFormPopUp.jsp"></jsp:include>
			</div>
		</div>
		
		
		
		<table border="0" cellpadding="0" cellspacing="0">
		
			<tr class="titleRow">
				<td colspan="4">
					<spring:message code="category" />
				</td>
			</tr>
			
			<tr class="spacer">
				<td colspan="4"><br/></td>
			</tr>
			
			<tr>
				<td>
					<spring:message code="new.category" />
				</td>
				<td>
					<div class="input-link" id="new_category_holder" onclick="openNewCategory()">
						<spring:message code="insert" />
					</div>
				</td>
				
				<td>
					<div class="right pad-right">
						<spring:message code="existent.category" />
					</div>
				</td>
				<td>
					<div class="input-link" id="existent_category_holder" onclick="openSelectCategory()">
						<spring:message code="select" />
					</div>
				</td>
			</tr>
			
			
			<tr class="divider">
				<td colspan="4"><br/></td>
			</tr>
			
			<tr class="spacer">
				<td colspan="4"><br/></td>
			</tr>
			
			<tr class="titleRow">
				<td colspan="4">
					<spring:message code="product" />
				</td>
			</tr>
			
			<tr class="spacer">
				<td colspan="4"><br/></td>
			</tr>
			
			<tr>
				<td>
					<spring:message code="product.name" />
				</td>
				<td>
					<form:input path="name" maxlength="250" size="22" cssClass="left" /> 
					<div class="required left"><br/></div>
					<form:errors path="name" cssClass="errors left" element="div" />
				</td>
				
				<td>
					<div class="right pad-right">
						<spring:message code="product.code" />
					</div>
				</td>
				<td>
					<form:input path="code" maxlength="20" size="10" cssClass="left" /> 
					<div class="required left"><br/></div>
					<form:errors path="code" cssClass="errors left" element="div"  />
				</td>
			</tr>
			
			
			<tr class="divider">
				<td colspan="4"><br/></td>
			</tr>
			
			<tr class="spacer">
				<td colspan="4"><br/></td>
			</tr>
			
			<tr class="titleRow">
				<td colspan="4">
					<spring:message code="picture" />
				</td>
			</tr>
			
			<tr class="spacer">
				<td colspan="4"><br/></td>
			</tr>
			
			<tr>
				<td>
					<spring:message code="product.smallImage" />
				</td>
				<td colspan="3">
					<c:choose>
						<c:when test="${product.smallImage == null}">
							<input type="file" name="smallImageFile" class="left" size="30"/>
							<form:errors path="smallImage" cssClass="errors left" element="div" />
						</c:when>
						<c:otherwise>
							<input type="file" name="smallImageFile" class="left" size="30" 
								id="smallImageFile_input" style="display: none;" />
								
							<div id="smallImageFile_div" class="disabled-input">
								${product.smallImage.fileName}
							</div>
							<form:errors path="smallImage" cssClass="errors left" element="div" />
							
							<a href="#" onclick="toggleInput('smallImageFile')" class="agregarLink edit"
								id="smallImageFile_link" title='<spring:message code="change" />'>
							</a>
							
							<a href='#' onclick="sendImageAction('productCreateForm', 'deleteSmallImage', '')" 
								class="agregarLink delete" id="smallImageFile_delete_link"
								title='<spring:message code="delete" />'>
							</a>
								
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			
			
			<tr>
				<td colspan="4">
					<div class="required left"><br/></div>
					<spring:message code="required" />
				</td>
			</tr>
	
		</table>
				
		<div class="actions">
			<c:choose>
				<c:when test="${product.id == null}">
					<button type="submit" name="actionBt" value="create" class="create-action">
						<spring:message code="create" />
					</button>
				</c:when>
				<c:otherwise>
					<button type="submit" name="actionBt" value="delete" class="delete-action"
						onclick="return confirm('<spring:message code="are.you.sure.you.want.to.delete.this.product" />')">
						<spring:message code="delete" />
					</button>
					<button type="submit" name="actionBt" value="update" class="update-action">
						<spring:message code="update" />
					</button>
				</c:otherwise>
			</c:choose>
			<button type="submit" name="actionBt" value="back">
				<spring:message code="cancel" />
			</button>
		</div>

	</form:form>


</div>


<jsp:include page="/WEB-INF/includes/footer.jsp" />