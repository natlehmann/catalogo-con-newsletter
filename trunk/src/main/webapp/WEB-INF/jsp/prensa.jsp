<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="/WEB-INF/includes/header.jsp">
	<jsp:param value="Alma de Jazmin" name="title"/>
</jsp:include>


<div id="contenidoPrensa">
	<div class="galeriaPrensa">
		<div><a href="prensa/prensa.pdf" target="_blank"><img src="images/prensa01.png" ></a><br /><a href="prensa/prensa.pdf" target="_blank">Revista Ohlalá / Noviembre 2012</a></div>
		<div><a href="prensa/prensa.pdf" target="_blank"><img src="images/prensa02.png" /></a><br /><a href="prensa/prensa.pdf" target="_blank">Revista Para Ti / Diciembre 2012</a></div>
		<div><a href="prensa/prensa.pdf" target="_blank"><img src="images/prensa03.png" /></a><br /><a href="prensa/prensa.pdf" target="_blank">Revista Elle / Enero 2013</a></div>
		<div><a href="prensa/prensa.pdf" target="_blank"><img src="images/prensa04.png" /></a><br /><a href="prensa/prensa.pdf" target="_blank">Revista Elle / Enero 2013</a></div>
		<div><a href="prensa/prensa.pdf" target="_blank"><img src="images/prensa01.png" ></a><br /><a href="prensa/prensa.pdf" target="_blank">Revista Ohlalá / Noviembre 2012</a></div>
	</div>
	
	<div class="flechasPrensa">
		<div><a href="#" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('retorceder','','images/retrocesoOn.png',1)"><img src="images/retrocesoOff.png" width="14" height="11" border="0" id="retorceder" /></a></div>
		<div><img src="images/separacionFlechas.png" alt="" width="1" height="11" /></div>
		<div><a href="#" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('avanzar','','images/avanzarOn.png',1)"><img src="images/avanzarOff.png" width="14" height="11" border="0" id="avanzar" /></a></div>
	</div> 
</div>


<jsp:include page="/WEB-INF/includes/footer.jsp">
	<jsp:param value="fondoPrensa.png" name="backgroundImg" />
</jsp:include>
