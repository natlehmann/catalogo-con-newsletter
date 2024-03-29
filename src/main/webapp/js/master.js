$(function() {

	var BlurBGImage = (function() {

		var $bxWrapper = $('#bx-wrapper'),
		// loading status to show while preloading images
		$bxLoading = $bxWrapper.find('div.bx-loading'),
		// container for the bg images and respective canvas
		$bxContainer = $bxWrapper.find('div.bx-container'),
		// the bg images we are gonna use
		$bxImgs = $bxContainer.children('img'),
		// total number of bg images
		bxImgsCount = $bxImgs.length,
		// the thumb elements
		$thumbs = $bxWrapper.find('div.bx-thumbs > a').hide(),
		// the title for the current image
		$title = $bxWrapper.find('h2:first'),
		// current image's index
		current = 0,
		// variation to show the image:
		// (1) - blurs the current one, fades out and shows the next image
		// (2) - blurs the current one, fades out, shows the next one (but initially blurred)
		// speed is the speed of the animation
		// blur Factor is the factor used in the StackBlur script
		animOptions = {
			speed : 700,
			variation : 1,
			blurFactor : 10
		},
		// control if currently animating
		isAnim = false,
		// check if canvas is supported
		supportCanvas = Modernizr.canvas,

		// init function
		init = function() {

			// preload all images and respective canvas
			var loaded = 0;

			$bxImgs.each(function(i) {

				var $bximg = $(this);

				// save the position of the image in data-pos
				$('<img data-pos="' + $bximg.index() + '"/>').load(
						function() {

							var $img = $(this),
							// size of image to be fullscreen and centered
							dim = getImageDim($img.attr('src')), pos = $img
									.data('pos');

							// add the canvas to the DOM
							$.when(createCanvas(pos, dim)).done(function() {

								++loaded;

								// all images and canvas loaded
								if (loaded === bxImgsCount) {

									// show thumbs
									$thumbs.fadeIn();

									// apply style for bg image and canvas
									centerImageCanvas();

									// hide loading status
									$bxLoading.hide();

									// initialize events
									initEvents();

								}

							});

						}).attr('src', $bximg.attr('src'));

			});

		},
		// creates the blurred canvas image
		createCanvas = function(pos, dim) {

			return $
					.Deferred(
							function(dfd) {

								// if canvas not supported return
								if (!supportCanvas) {
									dfd.resolve();
									return false;
								}

								// create the canvas element:
								// size and position will be the same like the fullscreen image
								var $img = $bxImgs.eq(pos), imgW = dim.width, imgH = dim.height, imgL = dim.left, imgT = dim.top,

								canvas = document.createElement('canvas');

								canvas.className = 'bx-canvas';
								canvas.width = imgW;
								canvas.height = imgH;
								canvas.style.width = imgW + 'px';
								canvas.style.height = imgH + 'px';
								canvas.style.left = imgL + 'px';
								canvas.style.top = imgT + 'px';
								canvas.style.visibility = 'hidden';
								// save position of canvas to know which image this is linked to
								canvas.setAttribute('data-pos', pos);
								// append the canvas to the same container where the images are
								$bxContainer.append(canvas);
								// blur it using the StackBlur script
								stackBlurImage($img.get(0), dim, canvas,
										animOptions.blurFactor, false,
										dfd.resolve);

							}).promise();

		},
		// gets the image size and position in order to make it fullscreen and centered.
		getImageDim = function(img) {

			var $img = new Image();

			$img.src = img;

			var $win = $(window), w_w = $win.width(), w_h = $win.height(), r_w = w_h
					/ w_w, i_w = $img.width, i_h = $img.height, r_i = i_h / i_w, new_w, new_h, new_left, new_top;

			if (r_w > r_i) {

				new_h = w_h;
				new_w = w_h / r_i;

			} else {

				new_h = w_w * r_i;
				new_w = w_w;

			}

			return {
				width : new_w,
				height : new_h,
				left : (w_w - new_w) / 2,
				top : (w_h - new_h) / 2
			};

		},
		// initialize the events
		initEvents = function() {

			$(window).on('resize.BlurBGImage', function(event) {

				// apply style for bg image and canvas
				centerImageCanvas();
				return false;

			});

			// clicking on a thumb shows the respective bg image
			$thumbs.on('click.BlurBGImage', function(event) {

				var $thumb = $(this), pos = $thumb.index();

				if (!isAnim && pos !== current) {

					$thumbs.removeClass('bx-thumbs-current');
					$thumb.addClass('bx-thumbs-current');
					isAnim = true;
					// show the bg image
					showImage(pos);

				}

				return false;

			});

		},
		// apply style for bg image and canvas
		centerImageCanvas = function() {

			$bxImgs
					.each(function(i) {

						var $bximg = $(this), dim = getImageDim($bximg
								.attr('src')), $currCanvas = $bxContainer
								.children('canvas[data-pos=' + $bximg.index()
										+ ']'), styleCSS = {
							width : dim.width,
							height : dim.height,
							left : dim.left,
							top : dim.top
						};

						$bximg.css(styleCSS);

						if (supportCanvas)
							$currCanvas.css(styleCSS);

						if (i === current)
							$bximg.show();

					});

		},
		// shows the image at position "pos"
		showImage = function(pos) {

			// current image 
			var $bxImage = $bxImgs.eq(current);
			// current canvas
			$bxCanvas = $bxContainer.children('canvas[data-pos='
					+ $bxImage.index() + ']'),
			// next image to show
			$bxNextImage = $bxImgs.eq(pos),
			// next canvas to show
			$bxNextCanvas = $bxContainer.children('canvas[data-pos='
					+ $bxNextImage.index() + ']');

			// if canvas is supported
			if (supportCanvas) {

				$.when($title.fadeOut()).done(function() {

					$title.text($bxNextImage.attr('title'));

				});

				$bxCanvas.css('z-index', 100).css('visibility', 'visible');

				$
						.when($bxImage.fadeOut(animOptions.speed))
						.done(
								function() {

									switch (animOptions.variation) {

									case 1:
										$title.fadeIn(animOptions.speed);
										$
												.when(
														$bxNextImage
																.fadeIn(animOptions.speed))
												.done(
														function() {

															$bxCanvas
																	.css(
																			'z-index',
																			1)
																	.css(
																			'visibility',
																			'hidden');
															current = pos;
															$bxNextCanvas
																	.css(
																			'visibility',
																			'hidden');
															isAnim = false;

														});
										break;
									case 2:
										$bxNextCanvas.css('visibility',
												'visible');

										$
												.when(
														$bxCanvas
																.fadeOut(animOptions.speed * 1.5))
												.done(
														function() {

															$(this)
																	.css(
																			{
																				'z-index' : 1,
																				'visibility' : 'hidden'
																			})
																	.show();

															$title
																	.fadeIn(animOptions.speed);

															$
																	.when(
																			$bxNextImage
																					.fadeIn(animOptions.speed))
																	.done(
																			function() {

																				current = pos;
																				$bxNextCanvas
																						.css(
																								'visibility',
																								'hidden');
																				isAnim = false;

																			});

														});
										break;

									}
									;

								});

			}
			// if canvas is not shown just work with the bg images
			else {

				$title.hide().text($bxNextImage.attr('title')).fadeIn(
						animOptions.speed);
				$.when(
						$bxNextImage.css('z-index', 102).fadeIn(
								animOptions.speed)).done(function() {

					current = pos;
					$bxImage.hide();
					$(this).css('z-index', 101);
					isAnim = false;

				});

			}

		};

		return {
			init : init
		};

	})();

	// call the init function
	BlurBGImage.init();

});

function MM_swapImgRestore() { // v3.0
	var i, x, a = document.MM_sr;
	for (i = 0; a && i < a.length && (x = a[i]) && x.oSrc; i++) {
		x.src = x.oSrc;
	}
}
function MM_preloadImages() { // v3.0
	var d = document;
	if (d.images) {
		if (!d.MM_p) {
			d.MM_p = new Array();
		}
		var i, j = d.MM_p.length, a = MM_preloadImages.arguments;
		for (i = 0; i < a.length; i++) {
			if (a[i].indexOf("#") != 0) {
				d.MM_p[j] = new Image;
				d.MM_p[j++].src = a[i];
			}
		}
	}
}

function MM_findObj(n, d) { // v4.01
	var p, i, x;
	if (!d) {
		d = document;
	}
	if ((p = n.indexOf("?")) > 0 && parent.frames.length) {
		d = parent.frames[n.substring(p + 1)].document;
		n = n.substring(0, p);
	}
	if (!(x = d[n]) && d.all) {
		x = d.all[n];
	}
	for (i = 0; !x && i < d.forms.length; i++) {
		x = d.forms[i][n];
	}
	for (i = 0; !x && d.layers && i < d.layers.length; i++) {
		x = MM_findObj(n, d.layers[i].document);
	}
	if (!x && d.getElementById) {
		x = d.getElementById(n);
	}
	return x;
}

function MM_swapImage() { // v3.0
	var i, j = 0, x, a = MM_swapImage.arguments;
	document.MM_sr = new Array;
	for (i = 0; i < (a.length - 2); i += 3) {
		if ((x = MM_findObj(a[i])) != null) {
			document.MM_sr[j++] = x;
			if (!x.oSrc) {
				x.oSrc = x.src;
			}
			x.src = a[i + 2];
		}
	}
}

function showLightbox() {
    $('#over').show();
    $('#fade').show();
}
function hideLightbox() {
	$('#over').hide();
    $('#fade').hide();
}
