<!DOCTYPE html>

<!--[if IE 7]> <html lang="en" class="ie7"> <![endif]-->  
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->  
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->  
<!--[if !IE]><!--> <!--<![endif]-->  
<html lang="it">
<head>

	<title>UPR-Catalog :: University Products Research Catalogue</title>
    <!-- Meta -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="content-type" content="text/html" charset="utf-8" />
    <link rel="shortcut icon" href="favicon.ico" />      
    
		<link href="css/bootstrap.min.css" rel="stylesheet">
      
    <!-- CSS Slider Plugin -->    
    <link rel="stylesheet" href="assets/plugins/flexslider/flexslider.css" type="text/css" media="screen" />  
    <link rel="stylesheet" href="assets/plugins/revolution_slider/css/rs-style.css" media="screen" />
    <link rel="stylesheet" href="assets/plugins/revolution_slider/rs-plugin/css/settings.css" media="screen" />    
    <!----------------------->    
    
    <!-- Custom styles for this template -->
   	<link href="css/Unify.css" rel="stylesheet">
		<link href="css/stile.css" rel="stylesheet">
		
		<script src="js/jquery.js"></script> 
<script src="js/bootstrap.js"></script> 
<script src="js/index.js"></script> 
</head> 

<body>

<%@ page import="it.unisa.upr.data.gestioneAutenticazione.Ricercatore"%>
<%@ page import="it.unisa.upr.data.gestioneAutenticazione.Amministratore"%>
<%@ page import="it.unisa.upr.data.gestioneAutenticazione.Responsabile"%>
<%@ page import="it.unisa.upr.data.gestioneAutenticazione.Account"%>
	
	
	<%
		Ricercatore ric = (Ricercatore) session.getAttribute("Ricercatore");
		if (ric != null) {
	        	
			response.sendRedirect("ric_homepage.jsp");
		}
		Amministratore am = (Amministratore) session.getAttribute("Amministratore");
		if (am != null) {
			response.sendRedirect("gestioneUtenti.jsp");
		}
		Responsabile res = (Responsabile) session.getAttribute("Responsabile");
		if (res != null) {
			response.sendRedirect("home_ricercatore.jsp");
		}
		
	%>


<!--=== Header ===-->
<div class="">               
    <div class="container"> 
 


  <!-- Static navbar -->
  <div class="navbar navbar-default navbar-fixed-top" role="navigation" style="z-index: 1000">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      <img class="navbar-brand" src="Loghi/logo.png" height="50px" width="150px" style="padding: 5px"> 
      </div>
    <div class="navbar-collapse collapse">
      <ul class="nav navbar-nav nav-pills">
          <li class="active"><a href="./index.jsp" ><span class=" glyphicon glyphicon-home"></span>Home</a></li>
        <li><a href="./ricerca_prodotti.jsp" ><span class="glyphicon glyphicon-search"></span>Ricerca</a></li>
       <!--   <li><a href="#"><span class="glyphicon glyphicon-file"></span>Inserimento Prodotto</a></li>-->
       <!-- <li><a href="#"><span class="glyphicon glyphicon-ok"></span>Validazione</a></li>-->
      <!--  <li><a href="#"><span class="glyphicon glyphicon-file"></span>Eventi</a></li>-->
      </ul>
      
     

      
     
     
     <div class="navbar-right row" style="margin-top:10px; margin-right:10px">
        <form class="form-inline" role="form" action="./LoginServlet" method="post">
          <div class="form-group">
            <label class="sr-only" for="account_username">Username</label>
            <input type="text" name="Username" class="form-control input-sm" id="account_username" placeholder="Username" required>
          </div>
          <div class="form-group">
            <label class="sr-only"  for="account_password">Password</label>
            <input type="password" name="Password" class="form-control input-sm" id="account_password" placeholder="Password" required>
          </div>
  		  <div class="form-group">
  		  	<label class="checkbox-inline">
  				<input type="checkbox" id="inlineCheckbox3" value="yes" name="Ricordami">Ricordami
			</label>
  		  </div>
          
          <button type="submit" class="btn btn-primary input-sm"><span class="glyphicon glyphicon-user"></span>Login</button>
        </form>
      </div>

     

   </div>
    <!--/.nav-collapse --> 
  </div>


  
  
  
  <div style="z-index: 999; margin-top: 80px; position: absolute; float: left" id="notificaLogin" class="col-xs-6 col-xs-offset-3 alert alert-danger fade in" hidden>

      <button type="button" class="close" data-dismiss="alert" aria-hidden="true">x</button>

      <div class="alert-msg"></div>

  </div>

     <%
		 if(session.getAttribute("error") != null) {
			 session.removeAttribute("error");
	 	 %>
			<script type="text/javascript">
			lanciaNotifica()
			</script>
    <%
		}
	  %>
     


    </div><!-- /container -->               
</div><!--/header -->      
<!--=== End Header ===-->

<!--=== Slider ===-->
<div style="margin-top: 50px" class="fullwidthbanner-container">
    <div class="fullwidthabnner">
        <ul>
            <!-- THE FIRST SLIDE -->
            <li data-transition="3dcurtain-vertical" data-slotamount="10" data-masterspeed="300" data-thumb="assets/img/sliders/revolution/thumbs/thumb1.jpg">

                <!-- THE MAIN IMAGE IN THE FIRST SLIDE -->
                <img src="assets/img/sliders/revolution/bg1.jpg" />

                <div class="caption large_text sfb bg-black-opacity" data-x="176" data-y="12" data-speed="300" data-start="800" data-easing="easeOutExpo">
                     OLTRE 
                     <span style="color: #ffcc00;">100</span> 
                     RICERCATORI IN UPR-Catalog
                </div>

                <div class="caption randomrotate" data-x="189" data-y="76" data-speed="600" data-start="1100" data-easing="easeOutExpo">
                     <img class="img-border" src="assets/img/sliders/revolution/p1.jpg" alt="Image 2" />
                </div>

                <div class="caption randomrotate" data-x="0" data-y="92" data-speed="600" data-start="1200" data-easing="easeOutExpo">
                     <img class="img-border" src="assets/img/sliders/revolution/p2.jpg" alt="Image 3" />
                </div>

                <div class="caption randomrotate" data-x="200" data-y="209" data-speed="600" data-start="1300" data-easing="easeOutExpo">
                     <img class="img-border" src="assets/img/sliders/revolution/p6.jpg" alt="Image 4" />
                </div>

                <div class="caption randomrotate" data-x="97" data-y="117" data-speed="300" data-start="1400" data-easing="easeOutExpo">
                     <img class="img-border" src="assets/img/sliders/revolution/p4.jpg" alt="Image 5" />
                </div>

                <div class="caption randomrotate" data-x="14" data-y="222" data-speed="600" data-start="1500" data-easing="easeOutExpo">
                     <img class="img-border" src="assets/img/sliders/revolution/p5.jpg" alt="Image 6" />
                </div>

                <div class="caption randomrotate" data-x="638" data-y="201" data-speed="300" data-start="1600" data-easing="easeOutExpo">
                     <img class="img-border" src="assets/img/sliders/revolution/p3.jpg" alt="Image 7" />
                </div>

                <div class="caption randomrotate" data-x="717" data-y="294" data-speed="300" data-start="1700" data-easing="easeOutExpo">
                     <img class="img-border" src="assets/img/sliders/revolution/p4.jpg" alt="Image 8" />
                </div>

                <div class="caption randomrotate" data-x="682" data-y="79" data-speed="300" data-start="1800" data-easing="easeOutExpo">
                     <img class="img-border" src="assets/img/sliders/revolution/p8.jpg" alt="Image 9" />
                </div>

                <div class="caption randomrotate" data-x="807" data-y="71" data-speed="300" data-start="1900" data-easing="easeOutExpo">
                     <img class="img-border" src="assets/img/sliders/revolution/p10.jpg" alt="Image 10" />
                </div>

                <div class="caption randomrotate" data-x="818" data-y="271" data-speed="300" data-start="2000" data-easing="easeOutExpo">
                     <img class="img-border" src="assets/img/sliders/revolution/p5.jpg" alt="Image 11" />
                </div>

                <div class="caption randomrotate" data-x="95" data-y="248" data-speed="300" data-start="2100" data-easing="easeOutExpo">
                     <img class="img-border" src="assets/img/sliders/revolution/p9.jpg" alt="Image 12" />
                </div>

                <div class="caption randomrotate" data-x="762" data-y="165" data-speed="300" data-start="2200" data-easing="easeOutExpo">
                     <img class="img-border" src="assets/img/sliders/revolution/p7.jpg" alt="Image 13" />
                </div>
            </li>

            <!-- THE SECOND SLIDE -->
            <li data-transition="papercut" data-slotamount="15" data-masterspeed="300" data-delay="11900" data-thumb="assets/img/sliders/revolution/thumbs/thumb2.jpg">

                <!-- THE MAIN IMAGE IN THE SECOND SLIDE -->                                               
                <img src="assets/img/sliders/revolution/bg3.jpg" />

                <div class="caption very_big_white lfl stl" data-x="-30" data-y="343" data-speed="300" data-start="500" data-easing="easeOutExpo" data-end="8800" data-endspeed="300" data-endeasing="easeInSine">
                     CATALOGO DELLA RICERCA
                </div>

                <div class="caption big_white lfl stl" data-x="-30" data-y="390" data-speed="300" data-start="800" data-easing="easeOutExpo" data-end="9100" data-endspeed="300" data-endeasing="easeInSine">
                     TUTTI I PRODOTTI IN UN UNICO CATALOGO DELL'ATENEO
                </div>

                <div class="caption lft ltb" data-x="600" data-y="0" data-speed="600" data-start="1100" data-easing="easeOutExpo" data-end="3100" data-endspeed="600" data-endeasing="easeInSine">
                     <img src="assets/img/sliders/revolution/pubblicazioni.jpg" alt="Image 3" />
                </div>

                <div class="caption bold_red_text sft stb" data-x="760" data-y="290" data-speed="300" data-start="1400" data-easing="easeOutExpo" data-end="3300" data-endspeed="300" data-endeasing="easeInSine">
                     ARTICOLI SU LIBRO E RIVISTA
                </div>

                <div class="caption big_black sfb stb" data-x="780" data-y="320" data-speed="300" data-start="1700" data-easing="easeOutExpo" data-end="3200" data-endspeed="300" data-endeasing="easeInSine">
                     Pubblicazioni
                </div>

                <div class="caption lft ltb" data-x="600" data-y="0" data-speed="600" data-start="3600" data-easing="easeOutExpo" data-end="5600" data-endspeed="600" data-endeasing="easeInSine">
                     <img src="assets/img/sliders/revolution/monografie.jpg" alt="Image 6" />
                </div>

                <div class="caption bold_brown_text sft stb" data-x="760" data-y="290" data-speed="300" data-start="3900" data-easing="easeOutExpo" data-end="5800" data-endspeed="300" data-endeasing="easeInSine">
                     MONOGRAFIE E CURATELE
                </div>

                <div class="caption big_black sfb stb" data-x="780" data-y="320" data-speed="300" data-start="4200" data-easing="easeOutExpo" data-end="5700" data-endspeed="300" data-endeasing="easeInSine">
                     Studio e ricerca
                </div>

                <div class="caption lft ltb" data-x="600" data-y="0" data-speed="600" data-start="6100" data-easing="easeOutExpo" data-end="8100" data-endspeed="300" data-endeasing="easeInSine">
                     <img src="assets/img/sliders/revolution/proceedings.jpg" alt="Image 9" />
                </div>

                <div class="caption bold_red_text sft stb" data-x="760" data-y="290" data-speed="300" data-start="6400" data-easing="easeOutExpo" data-end="8300" data-endspeed="300" data-endeasing="easeInSine">
                     PROCEEDINGS
                </div>

                <div class="caption big_black sfb stb" data-x="780" data-y="320" data-speed="300" data-start="6700" data-easing="easeOutExpo" data-end="8200" data-endspeed="300" data-endeasing="easeInSine">
                     Atti di congresso
                </div>
                
                
                <div class="caption lft ltb" data-x="600" data-y="0" data-speed="600" data-start="8600" data-easing="easeOutExpo" data-end="10600" data-endspeed="300" data-endeasing="easeInSine">
                     <img src="assets/img/sliders/revolution/brevetti.jpg" alt="Image 9" />
                </div>
                
                
                 <div class="caption bold_red_text sft stb" data-x="760" data-y="290" data-speed="300" data-start="8900" data-easing="easeOutExpo" data-end="10800" data-endspeed="300" data-endeasing="easeInSine">
                     BREVETTI
                </div>

                <div class="caption big_black sfb stb" data-x="780" data-y="320" data-speed="300" data-start="9200" data-easing="easeOutExpo" data-end="10700" data-endspeed="300" data-endeasing="easeInSine">
                     Invenzioni
                </div>
            </li>

            <!-- THE THIRD SLIDE -->
            <li data-transition="slideleft" data-slotamount="1" data-masterspeed="300" data-thumb="assets/img/sliders/revolution/thumbs/thumb3.jpg">

                <!-- THE MAIN IMAGE IN THE THIRD SLIDE -->                                               
                <img src="assets/img/sliders/revolution/bg5.jpg" />

                <div class="caption large_text sft" data-x="10" data-y="44" data-speed="300" data-start="800" data-easing="easeOutExpo">
                     Interfaccia adattiva
                </div>

                <div class="caption medium_text sfl" data-x="39" data-y="82" data-speed="300" data-start="1100" data-easing="easeOutExpo">
                     
                </div>

                <div class="caption large_text sfr" data-x="88" data-y="78" data-speed="300" data-start="1100" data-easing="easeOutExpo">
                     <span style="color: #72c02c;">compatibile con tutti i dispositivi</span>
                </div>

                <div class="caption lfl" data-x="10" data-y="150" data-speed="800" data-start="900" data-easing="easeOutExpo">
                     <img src="assets/img/sliders/revolution/imac.png" alt="Image 4" />
                </div>

                <div class="caption lfl" data-x="210" data-y="245" data-speed="600" data-start="1000" data-easing="easeOutExpo">
                     <img src="assets/img/sliders/revolution/ipad.png" alt="Image 5" />
                </div>

                <div class="caption lfl" data-x="322" data-y="313" data-speed="400" data-start="1100" data-easing="easeOutExpo">
                     <img src="assets/img/sliders/revolution/iphone.png" alt="Image 6" />
                </div>

                <div class="caption lft" data-x="500" data-y="130" data-speed="300" data-start="500" data-easing="easeOutExpo">
                     <img src="assets/img/sliders/revolution/html5andcss3.png" alt="Image 6" />
                </div>
            </li>

            <!-- THE FOURTH SLIDE -->
            <li data-transition="flyin" data-slotamount="1" data-masterspeed="300" data-thumb="assets/img/sliders/revolution/thumbs/thumb4.jpg">
                
                <!-- THE MAIN IMAGE IN THE FOURTH SLIDE -->                                
                <img src="assets/img/sliders/revolution/bg4.jpg" />

                <div class="caption lfb boxshadow" data-x="0" data-y="120" data-speed="900" data-start="500" data-easing="easeOutBack">
                     <iframe src="http://player.vimeo.com/video/84614161?title=0&amp;byline=0&amp;portrait=0" width="500" height="281" frameborder="0" webkitallowfullscreen="" mozallowfullscreen="" allowfullscreen=""></iframe> 
                </div>

                <div class="caption sft big_black" data-x="550" data-y="120" data-speed="300" data-start="1200" data-easing="easeOutExpo">
                     Le funzionalit&aacute;
                </div>
                
                <div class="caption sft big_white" data-x="550" data-y="162" data-speed="300" data-start="1300" data-easing="easeOutExpo">
                     Video preview
                </div>
                
                <div class="caption lfb medium_grey" data-x="550" data-y="215" data-speed="300" data-start="1400" data-easing="easeOutExpo">
                     Scopri tutte 
                </div>
                
                <div class="caption lfb medium_grey" data-x="550" data-y="240" data-speed="300" data-start="1500" data-easing="easeOutExpo">   
             		 le funzionalit&aacute;
                </div>
                
                <div class="caption lfb medium_grey" data-x="550" data-y="265" data-speed="300" data-start="1600" data-easing="easeOutExpo">
                     presenti in UPR-Catalog
                </div>
            </li>
        </ul>

        <div class="tp-bannertimer tp-bottom"></div>
    </div>
</div>
<!--=== End Slider ===-->

<div class="row margin-bottom-50" style="margin-top:30px;">
 <h3 class="col-xs-offset-2 col-xs-8 text-center success"> Benvenuti in <em>UPR-Catalog</em> il catalogo dei prodotti della ricerca del tuo Ateneo.<br>
 	<small> Ricerca subito tutti i prodotti presenti in catalogo</small></h3>
</div>
<!--  Form che invia i dati alla pagina risultati ricerca prodotti(dove poi viene chiamata la servlet)-->
 

 <form class="form-horizontal row"  action="./risultati_ricerca.jsp" method="post" name="form2">

 
  <div class="form-group"  id="divTitolo">
    <label for="prodotto_titolo" class="col-xs-offset-2 col-xs-2 control-label" >Titolo</label>
    <div class="col-xs-4">
      <input type="text" class="form-control" name="Titolo" id="prodotto_titolo" onkeyup="controlloTitolo(this.value)" placeholder="Inserire il titolo...">
    </div>
  </div>
  
  
  <div class="form-group">
    <label for="Tipologia" class="col-xs-offset-2 col-xs-2 control-label">Tipologia</label>
    <div class="col-xs-4">
      <select class="form-control" name="Tipologia" id="Tipologia">
       <option ></option>
       <option value="articolo libro">Articolo su libro</option>
       <option value="articolo rivista">Articolo su rivista</option>
       <option value="Brevetto">Brevetto</option>
       <option value="Curatela">Curatela</option>
       <option value="Monografia">Monografia</option>
       <option value="Proceeding">Proceeding</option>
       <option value="Altro">Altro</option>
      </select>
    </div>
  </div>

  
  <div class="form-group" id="divAnnoPubblicazione">
    <label for="Anno_Pubblicazione" class="col-xs-offset-2 col-xs-2 control-label">Anno Pubblicazione</label>
    <div class="col-xs-4">
      <input type="text" class="form-control" name="Anno_Pubblicazione" id="Anno_Pubblicazione" placeholder="Inserire il titolo..." onkeyup="controlloAnnoPubblicazione(this.value)">
    </div>
  </div>
  <div class="row">
  <div class="col-xs-offset-4 col-xs-4">
  <button type="button" class="btn btn-primary btn-block" onclick="controllaRicercaVuota()">Ricerca</button>
  <input value="1" type="hidden" name="nascosto" id="nascosto">
  </div>
  </div>
</form>  
  


</div>

  
 <div id="notificaRicerca" class="col-xs-6 col-xs-offset-3 alert alert-danger fade in"  hidden style="margin-top:50px">

      

      <div id="divAlert1"class="alert-msg"></div>

  </div>

<!-- /container --> 


<!-- Placed at the end of the document so the pages load faster --> 



<!-- JS Slider Plugins -->           
<script type="text/javascript" src="assets/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="assets/js/modernizr.custom.js"></script>		
<script type="text/javascript" src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>	
<script type="text/javascript" src="assets/plugins/flexslider/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="assets/plugins/revolution_slider/rs-plugin/js/jquery.themepunch.plugins.min.js"></script>
<script type="text/javascript" src="assets/plugins/revolution_slider/rs-plugin/js/jquery.themepunch.revolution.min.js"></script> 
<script type="text/javascript" src="assets/js/app.js"></script>
<script type="text/javascript" src="assets/js/pages/index.js"></script>
<!----------------------->           



<script type="text/javascript">
		$(document).ready(function () {
      	App.init();
        App.initSliders();
        Index.initRevolutionSlider(); 
		
		 $("#account_username").tooltip({		              
                  'selector': '',
				  'title': 'Inserire la username per accedere.',
				  'trigger': 'focus',
                  'placement': 'bottom'
                });
				
				$("#account_password").tooltip({		              
                  'selector': '',
				  'title': 'Inserire la password per accedere.',
				  'trigger': 'focus',
                  'placement': 'bottom'
                });     
				
				$("#prodotto_titolo").tooltip({		              
	                  'selector': '',
					  'title': 'Un testo di lunghezza minore  di 100 caratteri.',
					  'trigger': 'focus',
	                  'placement': 'bottom'
	                }); 
				
			
				$("#Anno_Pubblicazione").tooltip({		              
	                  'selector': '',
					  'title': 'Inserisci anno pubblicazione. ',
					  'trigger': 'focus',
	                  'placement': 'bottom'
	                });
			       
    });
</script>




<!--=== Footer ===-->
<%@ include file="footer.html" %>
<!--=== End Footer ===-->


</body>
</html>	