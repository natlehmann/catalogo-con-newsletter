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
function showTestEmailField() {
	$('#testEmailForm').show();
}
</script>


<div class="main-content">

	${newsletter.content}

	
	<form:form method="POST" action="sendNewsletter.html" id="newsletterSendForm"
		modelAttribute="newsletter" commandName="newsletter">
		
		<form:hidden path="id"/>
	
		<div class="actions">
		
			<button type="button" onclick="showTestEmailField()" >
				<spring:message code="send.test.email" />
			</button>
		
			<button type="submit" value="sendFinalCustomers" name="sendFinalCustomers">
				<spring:message code="send.to.final.customers" />
			</button>
			
			<button type="submit" value="sendRetailers" name="sendRetailers">
				<spring:message code="send.to.retailers" />
			</button>
			
			<button type="submit" value="sendCorporateSales" name="sendCorporateSales">
				<spring:message code="send.to.corporate.sales.customers" />
			</button>
			
			<button type="submit" value="back" name="back">
				<spring:message code="back" />
			</button>

		</div>
		
		<div id="testEmailForm" style="display:none">
		
			<input type="text" name="testEmail" />
			
			<button type="submit" value="sendTest" name="sendTest">
				<spring:message code="accept" />
			</button>
		</div>

	</form:form>

	
</div>


<jsp:include page="/WEB-INF/includes/footer.jsp" />