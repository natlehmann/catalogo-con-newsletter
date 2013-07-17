<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="/WEB-INF/includes/header.jsp">
	<jsp:param value="Alma de Jazmin" name="title"/>
</jsp:include>

<script type="text/javascript">
function inicializarZoom() {
	//$('#zoomImg').jqZoomIt();
	//$('#zoomImg').zoomy({border:'6px solid #fff'});
	$("#zoomImg").anythingZoomer({
		overlay : true,
		edit: false,
		// If you need to make the left top corner be at exactly 0,0, adjust the offset values below
		offsetX : 0,
		offsetY : 0
	});
}

function showProducts(categoryId, elem) {
	$.post("showProductsByCategory.html", { 'categoryId': categoryId }, displayProducts, 'html' );
	cleanSelectedCategory();
	$(elem).addClass('selected');	
}

function displayProducts(data) {
	$('#contenido').html(data);
	$('#contenido').show();

	var nombreCategoria = $.trim( $('#submenu a.selected').text() );
	nombreCategoria = nombreCategoria.replace(/ /g, '-');
	nombreCategoria = nombreCategoria.replace(/ñ/g, 'n');
	nombreCategoria = nombreCategoria.replace(',', '');
	nombreCategoria = nombreCategoria.toLowerCase();

	nombreCategoria = 'fondo-' + nombreCategoria;

	if ( $('img#' + nombreCategoria).length ) {
		$('.bx-container img').attr('src', $('img#' + nombreCategoria).attr('src') );
		
	} else {
		$('.bx-container img').attr('src', 'images/fondoProductos.jpg' );
	}

	inicializarZoom();
}

function cleanSelectedCategory() {
	$('#submenu a').removeClass('selected');
}

function avanzarGaleriaImagenes() {
	var img = $('#allImages img[showing="true"]');
	var newImg = img.next('img');
	
	if (newImg.length) {
		$('.galeriaProductos .img01 img').attr('src', newImg.attr('src') )
			.effect( "slide", {direction: 'right'}, "fast" );

		newImg.attr('showing', 'true');
		img.removeAttr("showing");

		var secondImg = newImg.next('img');

		if (secondImg.length) {	
			$('.galeriaProductos .img02 img').attr('src', secondImg.attr('src') )
				.effect( "slide", {direction: 'right'}, "fast" );
		
		} else {
			$('.galeriaProductos .img02 img').attr('src', '');
			
			$('#avanzar').hide();
			$('#avanzar-deshabilitado').show();
		}
	}

	$('#retroceder').show();
	$('#retroceder-deshabilitado').hide();
}

function retrocederGaleriaImagenes() {

	var img = $('#allImages img[showing="true"]');
	var prevImg = img.prev('img');

	$('.galeriaProductos .img01 img').attr('src', prevImg.attr('src') )
		.effect( "slide", {direction: 'left'}, "fast" );
	$('.galeriaProductos .img02 img').attr('src', img.attr('src') )
		.effect( "slide", {direction: 'left'}, "fast" );

	prevImg.attr('showing', 'true');
	img.removeAttr("showing");

	if ( !prevImg.prev('img').length ) {
		$('#retroceder').hide();
		$('#retroceder-deshabilitado').show();
	}
	
	$('#avanzar').show();
	$('#avanzar-deshabilitado').hide();
}
</script>                                     
                                           
                                           
<div id="submenu">
	<div class="botones-submenu">
		<c:forEach var="category" items="${categories}" varStatus="status">
			<div class="${status.count eq categories.size() ? 'botonesultimo' : 'botones' }">
				<a href="#" onclick="showProducts(${category.id}, this)">
					${category.name}
				</a>
			</div>
		</c:forEach>
	</div>
</div>

<div id="contenido" style="display:none;"></div>

<div id="imagenes-fondo" style="display:none;">
	<img src="images/fondoAccesorios.jpg" id="fondo-accesorios-pelo" />
	<img src="images/fondoAnillos.jpg" id="fondo-anillos-y-aros" />
	<img src="images/fondoBilleteras.jpg" id="fondo-billeteras-y-monederos" />
	<img src="images/fondoCarteras.jpg" id="fondo-carteras-y-bolsos" />
	<img src="images/fondoCollares.jpg" id="fondo-collares" />
	<img src="images/fondoCombinados.jpg" id="fondo-combinados" />
	<img src="images/fondoHome1.jpg" id="fondo-home" />	
	<img src="images/fondoPanuelos.jpg" id="fondo-panuelos-bufandas-y-gorros" />	
	<img src="images/fondoRopa.jpg" id="fondo-prendas-de-vestir" />	
	<img src="images/fondoPulseras.jpg" id="fondo-pulseras" />	
	<img src="images/fondoVarios.jpg" id="fondo-varios" />	
</div>


<jsp:include page="/WEB-INF/includes/footer.jsp">
	<jsp:param value="fondoProductos.jpg" name="backgroundImg" />
</jsp:include>
