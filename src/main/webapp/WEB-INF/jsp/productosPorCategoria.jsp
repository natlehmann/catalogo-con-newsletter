<%@page import="ar.com.almaDeJazmin.website.domain.ConfigConstants"%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="galeriaProductos">
	<c:forEach var="product" items="${products}" varStatus="status">
	
		<c:if test="${status.count <= 2}">
		
			<div class="${status.count eq 1 ? 'img01' : (status.count eq 2 ? 'img02' : '') }">
				
				<c:url value="imageView.html" var="url_big">
					<c:param name="id" value="${product.firstBigImage.id}" />
				</c:url>
				
				<c:if test="${status.count eq 1}">
					<div class="zoom" id="zoomImg">
						<div class="small">
				</c:if>
				
				<img src="${url_big}" style="width:<%=ConfigConstants.PRODUCT_GALLERY_FULL_SIZE_IMG_WIDTH + 10 %>px;height: <%=ConfigConstants.PRODUCT_GALLERY_FULL_SIZE_IMG_HEIGHT %>px;" />
				
				<c:if test="${status.count eq 1}">
						</div>
						<div class="large">
							<img alt="" src="${url_big}" style="width:<%=ConfigConstants.PRODUCT_GALLERY_ZOOM_SIZE_IMG_WIDTH %>px; height:<%=ConfigConstants.PRODUCT_GALLERY_ZOOM_SIZE_IMG_HEIGHT %>px;" >
						</div>
					</div>
				</c:if>
				
			</div>
		
		</c:if>
		
	</c:forEach>
</div>

<div id="allImages" style="display:none;">
	<c:forEach var="product" items="${products}" varStatus="status">
	
		<c:url value="imageView.html" var="url_big">
			<c:param name="id" value="${product.firstBigImage.id}" />
		</c:url>
		<img src="${url_big}" ${status.count eq 1 ? "showing='true'" : "" } />
		
	</c:forEach>
</div>

<div class="flechas">
	<div style="width:22px;height: 15px;">
		<a	onmouseout="MM_swapImgRestore()" 
			onmouseover="MM_swapImage('retroceder','','images/retrocesoOn.png',1)"
			onclick="retrocederGaleriaImagenes()">
			<img src="images/retrocesoOff.png" width="14" height="11" border="0" id="retroceder" 
				style="display:none;"/>
		</a>
	</div>
	
	<c:if test="${products.size() > 1 }">
	    <div>
	    	<img src="images/separacionFlechas.png" alt="" width="1" height="11" id="separacionFlechas"/>
	    </div>
	    <div>
	    	<a 	onmouseout="MM_swapImgRestore()" 
		    	onmouseover="MM_swapImage('avanzar','','images/avanzarOn.png',1)"
		    	onclick="avanzarGaleriaImagenes()">
		    	<img src="images/avanzarOff.png" width="14" height="11" border="0" id="avanzar" />
		    </a>
		</div>
	</c:if>
</div>

