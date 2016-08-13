<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
	
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css"> 
	<link rel="stylesheet" type="text/css" href="css/navbar.css">
	<link href="css/Unify.css" rel="stylesheet">
	<link href="css/stile.css" rel="stylesheet">
	
	<script src="js/jquery.js"></script> 
  <script src="js/bootstrap.js"></script> 
	<script type="text/javascript" src="js/controlloRicercaProdotto.js"></script>
	<script type="text/javascript" src="js/ricerca_prodotti.js"></script>  
</head>




<body>
  <%@ page import="it.unisa.upr.data.gestioneAutenticazione.Ricercatore"%>
  <%@ page import="it.unisa.upr.data.gestioneAutenticazione.Amministratore"%> 
   <% 
      Ricercatore ric = (Ricercatore) session.getAttribute("Ricercatore");
      Amministratore amm = (Amministratore) session.getAttribute("Amministratore");
	  if (ric != null) {
   %>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
  <!-- Brand and toggle get grouped for better mobile display -->
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <img class="navbar-brand" src="Loghi/logo.png" height="50px" width="150px" style="padding: 5px">
  </div>


  <!-- Collect the nav links, forms, and other content for toggling -->
  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <ul class="nav navbar-nav">
      <li> </li>
      <li><a href="./ric_homepage.jsp"><span class="glyphicon glyphicon-home"></span>Home</a></li>
      <li class="active"><a href="./ricerca_prodotti.jsp"><span class="glyphicon glyphicon-search"></span>Ricerca</a></li>
      <li><a href="./inserimento_prodotto.jsp"><span class="glyphicon glyphicon-plus"></span>Inserimento</a></li>
      <%if(ric.getRuolo().equalsIgnoreCase("Direttore") || ric.getRuolo().equalsIgnoreCase("Membro")){ %>
      <li><a href="validazione_homepage.jsp"><span class="glyphicon glyphicon-ok"></span>Validazione</a></li>
      <%}%>
    </ul>
 
 <div class="navbar-right" style="margin-top:10px; margin-right:10px">
 <form class="form-inline" role="form" action="./LogoutServlet" method="post">
    <label style="color: blue;">Benvenuto <% out.print(ric.getNome()); %> sei loggato come  <%out.print(ric.getRuolo()); %> </label>
    <button type="submit" class="btn btn-primary input-sm  control-label"><span class="glyphicon glyphicon-user"></span>Logout</button>
 </form>
 </div>
 
 </div><!-- /.navbar-collapse -->
 </nav>
<% }else if(amm!=null){%>

<div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      <img class="navbar-brand" src="Loghi/logo.png" height="50px" width="150px" style="padding: 5px"> 
      </div>
    <div class="navbar-collapse collapse">
      <ul class="nav navbar-nav nav-pills">
        
      <li ><a href="./gestioneUtenti.jsp" ><span class="glyphicon glyphicon-briefcase"></span>Gestione Utenti</a></li>
      <li><a href="./gestioneProdotti.jsp"><span class="glyphicon glyphicon-folder-open"></span>Gestione Prodotti</a></li>
      <li ><a href="./gestioneAteneo.jsp"><span class="glyphicon glyphicon-globe"></span>Gestione Ateneo</a></li>
       <li class=active><a href="./ricerca_prodotti.jsp"><span class="glyphicon glyphicon-search"></span>Ricerca Prodotti</a></li>
      </ul>
      

      <div class="navbar-right row" style="margin-top:10px; margin-right:10px">
        <form class="form-inline" role="form" action="./LogoutServlet" method="post">
       <label style="color: blue;">Benvenuto <%if(amm!=null) out.print(amm.getNome()); %> sei loggato come  <%if(amm!=null) out.print(amm.getTipologia()); %> </label>   
          <button type="submit" class="btn btn-primary input-sm"><span class="glyphicon glyphicon-user"></span>Logout</button>
        </form>
      </div>
    </div>
    <!--/.nav-collapse --> 
  </div>  <!-- Navbar -->

<% }else{ %>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
  <!-- Brand and toggle get grouped for better mobile display -->
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <img class="navbar-brand" src="Loghi/logo.png" height="50px" width="150px" style="padding: 5px">
  </div>

  <!-- Collect the nav links, forms, and other content for toggling -->
  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <ul class="nav navbar-nav">
      <li> </li>
      <li><a href="./index.jsp"><span class="glyphicon glyphicon-home"></span>Home</a></li>
      <li class="active"><a href="./ricerca_prodotti.jsp"><span class="glyphicon glyphicon-search"></span>Ricerca</a></li>
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
   
 
         
  </div><!-- /.navbar-collapse -->
 
</nav>




<% } %>


	<div class="row" style="margin-top: 80px;">
		<div class="col-xs-offset-2 col-xs-8">
			<h3 class="text-center success">
				Ricerca prodotti<br> <small> Nella pagina sono elencati
					tutti i criteri di ricerca disponibili.</small>
			</h3>
		</div>
	</div>





	<div id="notificaRicerca" class="col-xs-6 col-xs-offset-3 alert alert-danger fade in"  hidden style="margin-top:50px">

      <button type="button" class="close" data-dismiss="alert" aria-hidden="true">x</button>

      <div id="divAlert1"class="alert-msg"></div>

  </div>

<form class="form-horizontal row" style="margin-top: 102px" role="form" name="form2" method="post" action="./risultati_ricerca.jsp">


  <div class="form-group" id="divTitolo">
    <label for="Titolo" class="col-xs-offset-2 col-xs-2 control-label">Titolo</label>
    <div class="col-xs-4" >
      <input  name="Titolo" type="text" class="form-control" id="Titolo" placeholder="Inserire il titolo..." onkeyup="controlloTitolo(this.value)">
    </div>
  </div>
  
  <div class="form-group">
    <label for="Tipologia" class="col-xs-offset-2 col-xs-2 control-label">Tipologia</label>
    <div class="col-xs-4">
      <select class="form-control" onclick="controlloGenerale()" name="Tipologia" id="Tipologia">
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
    <label for="Anno_Pubblicazione" class="col-xs-offset-2 col-xs-2 control-label" >Anno di pubblicazione</label>
    <div class="col-xs-4">
      <input name="Anno_Pubblicazione" type="text" class="form-control" id="Anno_Pubblicazione" placeholder="Inserire l'anno..." onkeyup="controlloAnnoPubblicazione(this.value)">
    </div>
  </div>
  
  <div class="form-group">
    <div class="col-xs-1 col-md-offset-3">
      <button name="ricerca" type="button" class="btn btn-primary" onclick="mostraAvanzate()">Mostra menu avanzate<span id="spanfreccia"class="glyphicon glyphicon-chevron-right"></span></button>
    </div>
  </div>
  
  <div class="hidden" id="divAvanzate">
  <div class="form-group">
    <label for="dipartimento1" class="col-xs-offset-2 col-xs-2 control-label">Dipartimento</label>
    <div class="col-xs-4">
      <select class="form-control" onclick="controlloGenerale()" name="Nome_Dipartimento" id="Dipartimento">
     <option></option>
     
     
      </select>
    </div>
  </div>
  
  <div class="form-group">
    <label for="area_scientifica" class="col-xs-offset-2 col-xs-2 control-label">Area Scientifica</label>
    <div class="col-xs-4">
      <select class="form-control" onclick="controlloGenerale()" name="Nome_Area_Scientifica" id="area_scientifica1">
       <option></option>
       
       
      </select>
    </div>
  </div>
  
  <div class="form-group" id="divNome">
    <label for="Nome" class="col-xs-offset-2 col-xs-2 control-label">Autore Nome</label>
    <div class="col-xs-4">
      <input name="Nome" type="text" class="form-control" id="Nome" placeholder="Inserire il nome dell'autore..." onkeyup="controlloNome(this.value);" onblur="prendiValore(this.value);">
    </div>
  </div>
  
  <div class="form-group" id="divCognome">
    <label for="Cognome" class="col-xs-offset-2 col-xs-2 control-label">Autore Cognome</label>
    <div class="col-xs-4">
      <input name="Cognome" type="text" class="form-control" id="Cognome" placeholder="Inserire il cognome dell'autore..." onkeyup="controlloCognome(this.value)">
    </div>
  </div>
  </div>
   <div class="col-xs-6 col-xs-offset-3" style="height: 20px"></div>
  
  
  
  <div class="form-group">
    <div class="col-xs-3 col-xs-offset-4">
      <button type="button" class="btn btn-success"  onclick="controllaRicercaVuota()">Ricerca!</button>
  	  <button type="reset" class="btn btn-danger">Annulla</button>
    </div>
  </div>
  
  
</form>



 <script type="text/javascript">
			  $(document).ready(function () {
               
				  
				  $("#Username").tooltip({		              
	                  'selector': '',
					  'title': 'Inserire la username per accedere.',
					  'trigger': 'focus',
	                  'placement': 'bottom'
	                });
					
					$("#Password").tooltip({		              
	                  'selector': '',
					  'title': 'Inserire la password per accedere.',
					  'trigger': 'focus',
	                  'placement': 'bottom'
	                });    
			  
			  
             $("#Titolo").tooltip({		              
                  'selector': '',
				  'title': 'Un testo di lunghezza minore  di 100 caratteri.',
				  'trigger': 'focus',
                  'placement': 'bottom'
                });
			$("#Anno_Pubblicazione").tooltip({		              
                  'selector': '',
				  'title': 'Inserisci anno pubblicazione.',
				  'trigger': 'focus',
                  'placement': 'bottom'
                });
			$("#Nome").tooltip({		              
                  'selector': '',
				  'title': 'Inserire testo di lunghezza minore di 50.',
				  'trigger': 'focus',
                  'placement': 'bottom'
                });
			
			$("#Cognome").tooltip({		              
                  'selector': '',
				  'title': 'Inserire testo di lunghezza minore di 50.',
				  'trigger': 'focus',
                  'placement': 'bottom'
                });
	 
			
			  
			  });
			  
            </script>



<script type="text/javascript">

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

</script>


<!--=== Footer ===-->
<%@ include file="footer.html" %>
<!--=== End Footer ===-->


</body>





</html>