<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="/WEB-INF/includes/header.jsp">
	<jsp:param value="Alma de Jazmin" name="title"/>
	<jsp:param value="true" name="showAdminMenu"/>
</jsp:include>


<div>


<script type="text/javascript">
function sendAction(formId, action) {
	$('#' + formId).attr('action', action);
	$('#' + formId).submit();
}
</script>

	
	<form:form method="POST" action="" id="categoryCreateForm"
		modelAttribute="category" commandName="category">
		
		<c:if test="${category.id != null}">
			<form:hidden path="id"/>
		</c:if>
		
		
		<table border="0" cellpadding="0" cellspacing="0">
		
		<tr>
			<td colspan="2">
				<spring:message code="category" />
			</td>
		</tr>
		
		<tr class="spacer">
			<td colspan="2"><br/></td>
		</tr>
	
		<tr>
			<td><spring:message code="category.name" /></td>
			<td>
				<form:input path="name" maxlength="250" size="22" cssClass="left" /> 
				<div class="required left"><br/></div>
				<form:errors path="name" cssClass="errors left" element="div" />
			</td>
		</tr>
		
		<tr>
			<td colspan="2">
				<div class="required left"><br/></div>
				<spring:message code="required" />
			</td>
		</tr>
		
	</table>
	
	<div class="actions">
		
		<c:choose>
			<c:when test="${category.id == null}">
				<button type="button"" value="create" 
					onclick="sendAction('categoryCreateForm', 'createCategory.html')">
					<spring:message code="create" />
				</button>
			</c:when>
			<c:otherwise>
				<button type="button" value="delete"
					onclick="if (confirm('<spring:message code="are.you.sure.you.want.to.delete.this.category" />')) sendAction('categoryCreateForm', 'deleteCategory.html')">
					<spring:message code="delete" />
				</button>
				<button type="button" value="update"
					onclick="sendAction('categoryCreateForm', 'updateCategory.html')">
					<spring:message code="update" />
				</button>
			</c:otherwise>
		</c:choose>
		
		<button type="submit" value="back"
			onclick="sendAction('categoryCreateForm', 'categoryList.html')">
			<spring:message code="cancel" />
		</button>
	</div>

	</form:form>

	
</div>


<jsp:include page="/WEB-INF/includes/footer.jsp" />