<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String selected = request.getParameter("selected") != null ? request.getParameter("selected") : "";
%>

<div id="submenu">
<div class="botones-submenu">

	<div class="botones">
		
		<c:choose>
			<c:when test="<%= selected.equals(\"cabildo\") %>">
				<div class="submenuSelect">Cabildo 1668, CABA.</div> 
			</c:when>
			<c:otherwise>
				<a href="locales_cabildo.html">Cabildo 1668, CABA.</a> 
			</c:otherwise>
		</c:choose>
		<a href="https://maps.google.com.ar/maps?q=Avenida+Cabildo+1668,+CABA&amp;hl=es&amp;ll=-34.565807,-58.452888&amp;spn=0.007139,0.013937&amp;sll=-34.565728,-58.444369&amp;sspn=0.007139,0.013937&amp;oq=Cabildo+1668,+CABA.&amp;hnear=Av+Cabildo+1668,+Belgrano,+Buenos+Aires&amp;t=m&amp;z=17"
			target="_blank">
			<img src="images/googlemaps.png" alt="" width="12" height="18" border="0" />
		</a>
	</div>
	
	<div class="botones">
		<c:choose>
			<c:when test="<%= selected.equals(\"santaFe\") %>">
				<div class="submenuSelect">Av. Santa Fe 1960, CABA.</div> 
			</c:when>
			<c:otherwise>
				<a href="locales_santaFe.html">Av. Santa Fe 1960, CABA.</a>
			</c:otherwise>
		</c:choose>
		<a href="https://maps.google.com.ar/maps?q=Av.+Santa+Fe+1960,+CABA&amp;hl=es&amp;sll=-34.565807,-58.452888&amp;sspn=0.007139,0.013937&amp;hnear=Av+Santa+Fe+1960,+Recoleta,+Buenos+Aires&amp;t=m&amp;z=17"
		target="_blank">
			<img src="images/googlemaps.png" alt="" width="12" height="18" border="0" />
		</a>
	</div>
	
	<div class="botonesultimo">
		<c:choose>
			<c:when test="<%= selected.equals(\"rivadavia\") %>">
				<div class="submenuSelectUltimo">Av. Rivadavia 6837, CABA.</div> 
			</c:when>
			<c:otherwise>
				<a href="locales_rivadavia.html">Av. Rivadavia 6837, CABA.</a>
			</c:otherwise>
		</c:choose>
		<a href="https://maps.google.com.ar/maps?q=Av.+Rivadavia+6837,+CABA&amp;hl=es&amp;sll=-34.595787,-58.396604&amp;sspn=0.007136,0.011748&amp;hnear=Av+Rivadavia+6837,+Flores,+Buenos+Aires&amp;t=m&amp;z=17"
		target="_blank">
			<img src="images/googlemaps.png" alt="" width="12" height="18" border="0" />
		</a>
	</div>

</div>
</div>