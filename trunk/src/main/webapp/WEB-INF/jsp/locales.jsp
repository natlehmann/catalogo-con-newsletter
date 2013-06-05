<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="/WEB-INF/includes/header.jsp">
	<jsp:param value="Alma de Jazmin" name="title"/>
</jsp:include>


<jsp:include page="/WEB-INF/includes/locales_submenu.jsp"/>

<div id="contenido">

	<div class="galeria360">
		<div>
              <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="1200" height="405" id="FlashID" title="360">
                <param name="movie" value="images/alma.swf" />
                <param name="quality" value="high" />
                <param name="wmode" value="opaque" />
                <param name="swfversion" value="7.0.70.0" />
                <!-- Esta etiqueta param indica a los usuarios de Flash Player 6.0 r65 o posterior que descarguen la versión más reciente de Flash Player. Elimínela si no desea que los usuarios vean el mensaje. -->
                <param name="expressinstall" value="Scripts/expressInstall.swf" />
                <!-- La siguiente etiqueta object es para navegadores distintos de IE. Ocúltela a IE mediante IECC. -->
                <!--[if !IE]>-->
                <object type="application/x-shockwave-flash" data="images/alma.swf" width="1200" height="405">
                  <!--<![endif]-->
                  <param name="quality" value="high" />
                  <param name="wmode" value="opaque" />
                  <param name="swfversion" value="7.0.70.0" />
                  <param name="expressinstall" value="Scripts/expressInstall.swf" />
                  <!-- El navegador muestra el siguiente contenido alternativo para usuarios con Flash Player 6.0 o versiones anteriores. -->
                  <div>
                    <h4>El contenido de esta p&aacute;gina requiere una versi&oacute;n m&aacute;s reciente de Adobe Flash Player.</h4>
                    <p><a href="http://www.adobe.com/go/getflashplayer"><img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Obtener Adobe Flash Player" width="112" height="33" /></a></p>
                  </div>
                  <!--[if !IE]>-->
                </object>
                <!--<![endif]-->
              </object>
            </div>
	</div>
</div>


<jsp:include page="/WEB-INF/includes/footer.jsp">
	<jsp:param value="fondoLocales.jpg" name="backgroundImg" />
</jsp:include>