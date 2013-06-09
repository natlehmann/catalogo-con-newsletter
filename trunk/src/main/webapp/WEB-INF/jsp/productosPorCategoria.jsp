<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="galeriaPrensa">
	<c:forEach var="product" items="${products}" varStatus="status">
		<div class="${status.count eq 1 ? 'img01' : (status.count eq 2 ? 'img02' : '') }">
			<c:url value="imageView.html" var="url_big">
				<c:param name="id" value="${product.thumbnail.id}" />
			</c:url>
			<img src="${url_big}" />
		</div>
	</c:forEach>
</div>

<div class="flechas">
	<div>
		<a href="#" onmouseout="MM_swapImgRestore()" 
			onmouseover="MM_swapImage('retorceder','','images/retrocesoOn.png',1)">
			<img src="images/retrocesoOff.png" width="14" height="11" border="0" id="retorceder" />
		</a>
	</div>
    <div>
    	<img src="images/separacionFlechas.png" alt="" width="1" height="11" />
    </div>
    <div>
    	<a href="#" onmouseout="MM_swapImgRestore()" 
	    	onmouseover="MM_swapImage('avanzar','','images/avanzarOn.png',1)">
	    	<img src="images/avanzarOff.png" width="14" height="11" border="0" id="avanzar" />
	    </a>
	</div>
</div>

