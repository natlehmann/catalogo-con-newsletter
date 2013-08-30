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
				$('.galeriaPrensa a.link01').attr('href', $('span.pdf', newImg).text() );
				$('.galeriaPrensa .img01 a').attr('href', $('span.pdf', newImg).text() );
	
				newImg.attr('showing', 'true');
				img.removeAttr("showing");

				
				$('.galeriaPrensa .img02 a img').attr('src', $('img', secondImg).attr('src') )
					.effect( "slide", {direction: 'right'}, "medium" );	
				$('.galeriaPrensa a.link02').text( $('label', secondImg).text() );
				$('.galeriaPrensa a.link02').attr('href', $('span.pdf', secondImg).text() );
				$('.galeriaPrensa .img02 a').attr('href', $('span.pdf', secondImg).text() );

				$('.galeriaPrensa .img03 a img').attr('src', $('img', thirdImg).attr('src') )
					.effect( "slide", {direction: 'right'}, "medium" );	
				$('.galeriaPrensa a.link03').text( $('label', thirdImg).text() );
				$('.galeriaPrensa a.link03').attr('href', $('span.pdf', thirdImg).text() );
				$('.galeriaPrensa .img03 a').attr('href', $('span.pdf', thirdImg).text() );


		

				var fourthImg = thirdImg.next('div');
		
				if (fourthImg.length) {	
					
					$('.galeriaPrensa .img04 a img').attr('src', $('img', fourthImg).attr('src') )
						.effect( "slide", {direction: 'right'}, "medium" );
					$('.galeriaPrensa a.link04').text( $('label', fourthImg).text() );
					$('.galeriaPrensa a.link04').attr('href', $('span.pdf', fourthImg).text() );
					$('.galeriaPrensa .img04 a').attr('href', $('span.pdf', fourthImg).text() );

					
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
	$('.galeriaPrensa a.link01').attr('href', $('span.pdf', prevImg).text() );
	$('.galeriaPrensa .img01 a').attr('href', $('span.pdf', prevImg).text() );

	$('.galeriaPrensa .img02 a img').attr('src', $('img', secondImg).attr('src') )
		.effect( "slide", {direction: 'left'}, "medium" );
	$('.galeriaPrensa a.link02').text( $('label', secondImg).text() );
	$('.galeriaPrensa a.link02').attr('href', $('span.pdf', secondImg).text() );
	$('.galeriaPrensa .img02 a').attr('href', $('span.pdf', secondImg).text() );

	$('.galeriaPrensa .img03 a img').attr('src', $('img', thirdImg).attr('src') )
		.effect( "slide", {direction: 'left'}, "medium" );
	$('.galeriaPrensa a.link03').text( $('label', thirdImg).text() );
	$('.galeriaPrensa a.link03').attr('href', $('span.pdf', thirdImg).text() );
	$('.galeriaPrensa .img03 a').attr('href', $('span.pdf', thirdImg).text() );

	$('.galeriaPrensa .img04 a img').attr('src', $('img', fourthImg).attr('src') )
		.effect( "slide", {direction: 'left'}, "medium" );
	$('.galeriaPrensa a.link04').text( $('label', fourthImg).text() );
	$('.galeriaPrensa a.link04').attr('href', $('span.pdf', fourthImg).text() );
	$('.galeriaPrensa .img04 a').attr('href', $('span.pdf', fourthImg).text() );


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
			<a href="prensa/prensaAgoParatiDeco.pdf" target="_blank">
				<img src="images/prensaAgoParatiDeco.jpg" ></a>
		</div>
		<a class="link01" href="prensa/prensaAgoParatiDeco.pdf" target="_blank">Revista Parati Deco / Agosto 2013</a>
		
		<div class="img02">
			<a href="prensa/prensaAgoGente.pdf" target="_blank">
				<img src="images/prensaAgoGente.jpg" />
			</a>
		</div>
		<a class="link02" href="prensa/prensaAgoGente.pdf" target="_blank">Revista Gente / Agosto 2013</a>
		
		<div class="img03">
			<a href="prensa/prensaJulEntrecasa.pdf" target="_blank">
				<img src="images/prensaJulEntrecasa.jpg" />
			</a>
		</div>
		<a class="link03" href="prensa/prensaJulEntrecasa.pdf" target="_blank">Revista Entrecasa / Julio 2013</a>
		
		<div class="img04">
			<a href="prensa/prensaJulCaras.pdf" target="_blank">
				<img src="images/prensaJulCaras.jpg" />
			</a>
		</div>
		<a class="link04" href="prensa/prensaJulCaras.pdf" target="_blank">Revista Caras / Julio 2013</a>
	</div>
	
	<div id="allPrensa" style="display:none;">
		<div showing="true"><img src="images/prensaAgoParatiDeco.jpg" >
			<label>Revista Parati Deco / Agosto 2013</label>
			<span class="pdf">prensa/prensaAgoParatiDeco.pdf</span>
		</div>
		<div><img src="images/prensaAgoGente.jpg" >
			<label>Revista Gente / Agosto 2013</label>
			<span class="pdf">prensa/prensaAgoGente.pdf</span>
		</div>
        <div><img src="images/prensaJulEntrecasa.jpg" >
        	<label>Revista Entrecasa / Julio 2013</label>
        	<span class="pdf">prensa/prensaJulEntrecasa.pdf</span>
        </div>
		<div><img src="images/prensaJulCaras.jpg" >
			<label>Revista Caras / Julio 2013</label>
			<span class="pdf">prensa/prensaJulCaras.pdf</span>
		</div>
		<div><img src="images/prensaJulCaras3.jpg" >
			<label>Revista Caras / Julio 2013</label>
			<span class="pdf">prensa/prensaJulCaras3.pdf</span>
		</div>
		<div><img src="images/prensaJulCaras2.jpg" >
			<label>Revista Caras / Julio 2013</label>
			<span class="pdf">prensa/prensaJulCaras2.pdf</span>
		</div>
		<div><img src="images/prensaJulCaras1.jpg" >
			<label>Revista Caras / Julio 2013</label>
			<span class="pdf">prensa/prensaJulCaras1.pdf</span>
		</div>
		<div><img src="images/prensaJulMia.jpg" >
			<label>Revista Mia / Julio 2013</label>
			<span class="pdf">prensa/prensaJulMia.pdf</span>
		</div>
        <div><img src="images/prensaJulParateens.jpg" >
        	<label>Revista Para Teens / Julio 2013</label>
        	<span class="pdf">prensa/prensaJulParateens.pdf</span>
        </div>
		<div><img src="images/prensaJulParati1.jpg" >
			<label>Revista Parati / Julio 2013</label>
			<span class="pdf">prensa/prensaJulParati1.pdf</span>
		</div>
		<div><img src="images/prensaJulParati2.jpg" >
			<label>Revista Parati / Julio 2013</label>
			<span class="pdf">prensa/prensaJulParati2.pdf</span>
		</div>
		<div><img src="images/prensaJulParati3.jpg" >
			<label>Revista Parati / Julio 2013</label>
			<span class="pdf">prensa/prensaJulParati3.pdf</span>
		</div>
        <div><img src="images/prensaJulParati.jpg" >
        	<label>Revista Parati / Julio 2013</label>
        	<span class="pdf">prensa/prensaJulParati.pdf</span>
        </div>
		<div><img src="images/prensaJulViva.jpg" >
			<label>Revista Viva / Julio 2013</label>
			<span class="pdf">prensa/prensaJulViva.pdf</span>
		</div>
		<div><img src="images/prensaJunParaTi.jpg" >
			<label>Revista Parati / Junio 2013</label>
			<span class="pdf">prensa/prensaJunParaTi.pdf</span>
		</div>
		<div><img src="images/prensaMayGente.jpg" >
			<label>Revista Gente / Mayo 2013</label>
			<span class="pdf">prensa/prensaMayGente.pdf</span>
		</div>
		<div><img src="images/prensaMayMia.jpg" >
			<label>Revista Mia / Mayo 2013</label>
			<span class="pdf">prensa/prensaJulMia.pdf</span> <!-- xxxxxxxxxxx ESTE PDF NO ESTA -->
		</div>
		<div><img src="images/prensaMayParaTeens1.jpg" >
			<label>Revista Para Teens / Mayo 2013</label>
			<span class="pdf">prensa/prensaMayParaTeens1.pdf</span>
		</div>
		<div><img src="images/prensaMayParateens.jpg" >
			<label>Revista Para Teens / Mayo 2013</label>
			<span class="pdf">prensa/prensaMayParateens.pdf</span>
		</div>
        <div><img src="images/prensaAbrCaras.jpg" >
        	<label>Revista Caras / Abril 2013</label>
        	<span class="pdf">prensa/prensaAbrCaras.pdf</span>
        </div>
		<div><img src="images/prensaAbrCosmopolitan.jpg" >
			<label>Revista Cosmopolitan / Abril 2013</label>
			<span class="pdf">prensa/prensaAbrCosmopolitan.pdf</span>
		</div>
		<div><img src="images/prensaAbrGente.jpg" >
			<label>Revista Gente / Abril 2013</label>
			<span class="pdf">prensa/prensaAbrGente.pdf</span>
		</div>
		<div><img src="images/prensaAbrParateens.jpg" >
			<label>Revista Para Teens / Abril 2013</label>
			<span class="pdf">prensa/prensaAbrParateens.pdf</span>
		</div>
        <div><img src="images/prensaAbrParati.jpg" >
        	<label>Revista Parati / Abril 2013</label>
        	<span class="pdf">prensa/prensaAbrParati.pdf</span>
        </div>
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
