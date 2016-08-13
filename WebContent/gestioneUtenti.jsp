<%@page import="it.unisa.upr.data.gestioneAutenticazione.Responsabile"%>
<%@page import="it.unisa.upr.data.gestioneAutenticazione.Amministratore"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestione Utenti - UPR-Catalog</title>

<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="assets/stileFooter.css" rel="stylesheet">
<link href="css/stile.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/navbar.css" rel="stylesheet">
 <script src="js/jquery.js"></script> 
<script src="js/bootstrap.js"></script> 
<script src="js/gestioneUtenti.js"></script> 



<style type="text/css">
tr{text-align: center;}
th{text-align: center;}

</style>

</head>
<body>

<%@ page import="it.unisa.upr.data.gestioneAutenticazione.Amministratore"%>
<%@ page import="it.unisa.upr.data.gestioneAutenticazione.Responsabile"%>

<%Amministratore amm=(Amministratore) session.getAttribute("Amministratore");

	if(amm==null){
		String url=request.getContextPath()+"/index.jsp";
		response.sendRedirect(url);
	}

%>

<div class=" navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      <img class="navbar-brand" src="Loghi/logo.png" height="50px" width="150px" style="padding: 5px"> 
      </div>
    <div class="navbar-collapse collapse">
      <ul class="nav navbar-nav nav-pills">
        
      <li class=active><a href="./gestioneUtenti.jsp" ><span class="glyphicon glyphicon-briefcase"></span>Gestione Utenti</a></li>
      <li><a href="./gestioneProdotti.jsp"><span class="glyphicon glyphicon-folder-open"></span>Gestione Prodotti</a></li>
      <li><a href="./gestioneAteneo.jsp"><span class="glyphicon glyphicon-globe"></span>Gestione Ateneo</a></li>
      <li><a href="./ricerca_prodotti.jsp"><span class="glyphicon glyphicon-search"></span>Ricerca Prodotti</a></li>
      </ul>
      

      <div class="navbar-right row" style="margin-top:10px; margin-right:10px">
        <form class="form-inline" role="form" action="./LogoutServlet" method="post">
        <label style="color: blue;">Benvenuto <% if(amm!=null) out.print(amm.getNome()); %> sei loggato come  <%if(amm!=null) out.print(amm.getTipologia()); %> </label>
          <button type="submit" class="btn btn-primary input-sm"><span class="glyphicon glyphicon-user"></span>Logout</button>
        </form>
      </div>
     
     
    </div>
    <!--/.nav-collapse --> 
  </div>  <!-- Navbar -->
  
  <div class=" row page-header   col-xs-offset-0 " style="margin-top:51px; text-align:center">
  <h1>Gestione Utenti </h1>
  </div> <!-- div gestione utenti -->
  
  <div class="row" style="margin-left: 1030px;">

  </div>
  
<div class="row col-xs-offset-1">
<form class="row form-horizontal" role="form" name=form2 >
  <div class="form-group col-xs-3">
    <label for="Nome" class="col-sm-2 control-label" >Nome</label>
    <div class="col-sm-9">
      <input  id="Nome" type="text" class="form-control"  placeholder="Nome" onkeyup="controlloNome(this.value)">
    </div>
  </div>
  <div class="form-group col-xs-5">
    <label for="Cognome" class="col-sm-2 control-label">Cognome</label>
    <div class="col-sm-6">
      <input id="Cognome" type="text" class="form-control"  placeholder="Cognome">
    </div>
     <button type="button" class="btn btn-primary" onclick="RicercaUtenti()"><span class="glyphicon glyphicon-search"></span>Ricerca</button><button class="btn" type="button" onclick="ListaUtenti()"><span class="glyphicon glyphicon-repeat" ></button>
  </div>
  <div class="col-xs-offset-2 col-xs-2" >
  
  <a class="btn btn-primary" href="./Inserisci_utente.jsp">Inserisci Utente</a>

  </div>
 
  
  </form>  <!-- form ricerca -->
  </div>  
  
  <div class="row">
    <div class="row col-xs-6 col-xs-offset-3 alert alert-success fade in" id="non_visibile" hidden>
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">x</button>
        <div class="row alert-msg"></div>
    </div>
</div>
  
  
  <div class="row col-xs-offset-1 col-xs-10">
  
  <div id=Tabella class="" style="margin-top: 90px ;width:100% ; height:400px;overflow:scroll;" >
   <table id='lista_utenti' class='table table-hover  table-striped '> 
   <thead><tr><th style="padding-left: 20px;">Nome e Cognome</th><th>Ruolo</th><th>Dipartimento</th><th>Area Scientifica</th></tr></thead>	  
   
   
   </table>

   
  </div>
  </div>
  
  <div style="display: inline-block; margin-top: 50px" class="row  col-xs-offset-3">
<!--   <form action="./Inserisci_utente.jsp"> -->
<!--   <button class="btn btn-primary" type="submit" >Inserisci Utente</button> -->
<!--   </form> -->
  </div>
  
  
  
  
 
 <script type="text/javascript">
			  $(document).ready(function () {
               
				  
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
  
  <% if(request.getHeader("User-Agent").indexOf("Firefox")!=-1){ %>
  	<div style="margin-top: 500px">
  	<%@include file="footer.html"%>
  	</div>
  <%} 
  	else {%>
  		<%@include file="footer.html"%>
  		
  <%} %>
  
  
</body>

<script src="js/jquery.js"></script> 
	<script src="js/bootstrap.js"></script> 
	<script type="text/javascript">
			
			
		function lanciaNotifica(strong,frase){
			$(".alert").show();
			$(".alert-msg").html('<strong>'+strong+'</strong>'+frase);
			window.setTimeout(function(){ $(".alert").alert('close'); }, 3000);
		}
				  			  
            </script>

<%if(session.getAttribute("Response")!=null){
	   session.removeAttribute("Response");%>
   		
   		<script type="text/javascript">
   		lanciaNotifica("Ben fatto!","Operazione avvenuta con successo");
   		</script>
	
   <%}%>



</html>