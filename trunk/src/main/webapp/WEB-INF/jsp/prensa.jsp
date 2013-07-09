<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="/WEB-INF/includes/header.jsp">
	<jsp:param value="Alma de Jazmin" name="title"/>
</jsp:include>

<script type="text/javascript">
function avanzarGaleriaPrensa() {
	var img = $('#allPrensa div[showing="true"]');
	var newImg = img.next('div');
	
	if (newImg.length) {

		var secondImg = newImg.next('div');

		if (secondImg.length) {

			var thirdImg = secondImg.next('div');

			if (thirdImg.length) {

		
				$('.galeriaPrensa .img01 a img').attr('src', $('img', newImg).attr('src') )
					.effect( "slide", {direction: 'right'}, "medium" );
		
				$('.galeriaPrensa a.link01').text( $('label', newImg).text() );
		
				newImg.attr('showing', 'true');
				img.removeAttr("showing");

				
				$('.galeriaPrensa .img02 a img').attr('src', $('img', secondImg).attr('src') )
					.effect( "slide", {direction: 'right'}, "medium" );	
				$('.galeriaPrensa a.link02').text( $('label', secondImg).text() );

				$('.galeriaPrensa .img03 a img').attr('src', $('img', thirdImg).attr('src') )
					.effect( "slide", {direction: 'right'}, "medium" );	
				$('.galeriaPrensa a.link03').text( $('label', thirdImg).text() );


		

				var fourthImg = thirdImg.next('div');
		
				if (fourthImg.length) {	
					
					$('.galeriaPrensa .img04 a img').attr('src', $('img', fourthImg).attr('src') )
						.effect( "slide", {direction: 'right'}, "medium" );
					$('.galeriaPrensa a.link04').text( $('label', fourthImg).text() );

					
					var fifthImg = fourthImg.next('div');

					if (!fifthImg.length) {
						$('#avanzar').hide();
						$('#avanzar-deshabilitado').show();
					}
				
				} 
					
			}
		}
	}

	$('#retroceder').show();
	$('#retroceder-deshabilitado').hide();
}

function retrocederGaleriaPrensa() {

	var img = $('#allPrensa div[showing="true"]');
	var prevImg = img.prev('div');
	var secondImg = prevImg.next('div');
	var thirdImg = secondImg.next('div');
	var fourthImg = thirdImg.next('div');

	$('.galeriaPrensa .img01 a img').attr('src', $('img', prevImg).attr('src') )
		.effect( "slide", {direction: 'left'}, "medium" );
	$('.galeriaPrensa a.link01').text( $('label', prevImg).text() );

	$('.galeriaPrensa .img02 a img').attr('src', $('img', secondImg).attr('src') )
		.effect( "slide", {direction: 'left'}, "medium" );
	$('.galeriaPrensa a.link02').text( $('label', secondImg).text() );

	$('.galeriaPrensa .img03 a img').attr('src', $('img', thirdImg).attr('src') )
		.effect( "slide", {direction: 'left'}, "medium" );
	$('.galeriaPrensa a.link03').text( $('label', thirdImg).text() );

	$('.galeriaPrensa .img04 a img').attr('src', $('img', fourthImg).attr('src') )
		.effect( "slide", {direction: 'left'}, "medium" );
	$('.galeriaPrensa a.link04').text( $('label', fourthImg).text() );


	prevImg.attr('showing', 'true');
	img.removeAttr("showing");

	if ( !prevImg.prev('div').length ) {
		$('#retroceder').hide();
		$('#retroceder-deshabilitado').show();
	}
	
	$('#avanzar').show();
	$('#avanzar-deshabilitado').hide();
}
</script>  


<div id="contenidoPrensa">

	<div class="galeriaPrensa">
		<div class="img01">
			<a href="prensa/prensa.pdf" target="_blank">
				<img src="images/prensa01.jpg" ></a>
		</div>
		<a class="link01" href="prensa/prensa.pdf" target="_blank">Revista Ohlalá / Noviembre 2012</a>
		
		<div class="img02">
			<a href="prensa/prensa.pdf" target="_blank">
				<img src="images/prensa02.jpg" />
			</a>
		</div>
		<a class="link02" href="prensa/prensa.pdf" target="_blank">Revista Para Ti / Diciembre 2012</a>
		
		<div class="img03">
			<a href="prensa/prensa.pdf" target="_blank">
				<img src="images/prensa03.jpg" />
			</a>
		</div>
		<a class="link03" href="prensa/prensa.pdf" target="_blank">Revista Elle / Enero 2013</a>
		
		<div class="img04">
			<a href="prensa/prensa.pdf" target="_blank">
				<img src="images/prensa04.jpg" />
			</a>
		</div>
		<a class="link04" href="prensa/prensa.pdf" target="_blank">Revista Elle / Enero 2013</a>
	</div>
	
	<div id="allPrensa" style="display:none;">
		<div showing="true"><img src="images/prensa01.jpg" ><label>Revista Ohlalá / Noviembre 2012</label></div>
		<div><img src="images/prensa02.jpg" ><label>Revista Para Ti / Diciembre 2012</label></div>
		<div><img src="images/prensa03.jpg" ><label>Revista Elle / Enero 2013</label></div>
		<div><img src="images/prensa04.jpg" ><label>Revista Elle / Enero 2013</label></div>
		<div><img src="images/prensa01.jpg" ><label>Revista Ohlalá / Noviembre 2012</label></div>
		<div><img src="images/prensa02.jpg" ><label>Revista Para Ti / Diciembre 2012</label></div>
		<div><img src="images/prensa03.jpg" ><label>Revista Elle / Enero 2013</label></div>
	</div>
	
	<div class="flechas">
		<div style="width:22px;height: 15px;">
			<a	onmouseout="MM_swapImgRestore()" 
				onmouseover="MM_swapImage('retroceder','','images/retrocesoOn.png',1)"
				onclick="retrocederGaleriaPrensa()">
				<img src="images/retrocesoOff.png" width="14" height="11" border="0" id="retroceder" 
					style="display:none;"/>
			</a>
			<img src="images/deshabilitadoRetrocederOn.png" width="14" height="11" border="0" 
				id="retroceder-deshabilitado"/>
		</div>
		
	    <div>
	    	<img src="images/separacionFlechas.png" alt="" width="1" height="11" id="separacionFlechas"/>
	    </div>
	    
	    <div>
	    	<a 	onmouseout="MM_swapImgRestore()" 
		    	onmouseover="MM_swapImage('avanzar','','images/avanzarOn.png',1)"
		    	onclick="avanzarGaleriaPrensa()">
		    	<img src="images/avanzarOff.png" width="14" height="11" border="0" id="avanzar" />
		    </a>
		    <img src="images/deshabilitadoAvanzarOn.png" width="14" height="11" border="0" 
				id="avanzar-deshabilitado" style="display: none;"/>
		</div>
	</div>
</div>


<jsp:include page="/WEB-INF/includes/footer.jsp">
	<jsp:param value="fondoPrensa.jpg" name="backgroundImg" />
</jsp:include>
