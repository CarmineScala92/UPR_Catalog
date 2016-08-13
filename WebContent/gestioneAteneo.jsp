<%@page import="it.unisa.upr.data.gestioneAutenticazione.Account"%>
<%@page import="it.unisa.upr.data.gestioneAutenticazione.Amministratore"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestione Ateneo - UPR-Catalog</title>
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/navbar.css" rel="stylesheet">
<link href="css/stile.css" rel="stylesheet">
<link href="assets/stileFooter.css" rel="stylesheet">
 <script src="js/jquery.js"></script> 
<script src="js/bootstrap.js"></script> 
<script src="js/index.js"></script> 
<script src="js/gestioneAteneo.js"></script> 
<style>
th{
 text-align:center;
}
td{
 text-align:center;
}
</style>
</head>
<body>


<%@ page import="it.unisa.upr.data.gestioneAutenticazione.Amministratore"%>

<%Amministratore amm=(Amministratore)session.getAttribute("Amministratore"); 

if(amm==null){
	String url=request.getContextPath()+"/index.jsp";
	response.sendRedirect(url);
}

%>




<div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      <img class="navbar-brand" src="Loghi/logo.png" height="50px" width="150px" style="padding: 5px"> 
      </div>
    <div class="navbar-collapse collapse">
      <ul class="nav navbar-nav nav-pills">
        
      <li ><a href="./gestioneUtenti.jsp" ><span class="glyphicon glyphicon-briefcase"></span>Gestione Utenti</a></li>
      <li><a href="./gestioneProdotti.jsp"><span class="glyphicon glyphicon-folder-open"></span>Gestione Prodotti</a></li>
      <li class=active><a href="./gestioneAteneo.jsp"><span class="glyphicon glyphicon-globe"></span>Gestione Ateneo</a></li>
       <li><a href="./ricerca_prodotti.jsp"><span class="glyphicon glyphicon-search"></span>Ricerca Prodotti</a></li>
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

<div class="page-header   col-xs-offset-0 " style="margin-top:51px; text-align:center">
  <h1>Gestione Ateneo </h1>
  </div> <!-- div gestione utenti -->


<div class="col-xs-6 col-xs-offset-3 alert alert-success fade in" id="non_visibile" hidden>
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">x</button>
        <div class="alert-msg"></div>
    </div>



<form class="form-horizontal" role="form" method="post" action="">
  <div class="form-group col-xs-6" style="display:inline-block">
    <label for="inputEmail3" class="col-sm-4 control-label">Nome Dipartimento</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="Dipartimento" placeholder="Nome dipartimento">
    </div>
    <button type="button" class="btn btn-primary " onclick="RicercaDipartimento()"><span class="glyphicon glyphicon-search"></span>Ricerca</button><button class="btn " type="button" onclick="ListaDipartimenti()"><span class="glyphicon glyphicon-repeat" ></button>
  </div>
 </form>

 <form class="form-horizontal" role="form" method="post" action="">
  <div class="form-group col-xs-6" style="display:inline-block">
    <label for="inputEmail3" class="col-sm-5 control-label">Nome Area Scientifica</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="AreaScientifica" placeholder="Nome area scientifica">
    </div>
    <button type="button" class="btn btn-primary " onclick="RicercaAreaScientifica()"><span class="glyphicon glyphicon-search" ></span>Ricerca</button><button class="btn" type="button" onclick="ListaAree()"><span class="glyphicon glyphicon-repeat" ></button>
  </div>
 </form>
     
     
     <div style="display: inline-block;margin-left: 200px;" class="col-xs-3">
     <h4>Lista Dipartimenti</h4>
     </div>
     <div style="display: inline-block;margin-left: 350px" >
     <h4>Lista Aree Scientifiche</h4>
     </div>
 
   <div class="col-xs-12">

   <div  id=Tabella class="col-xs-6" style="margin-top: 50px ;width:560px ; height:400px;overflow:scroll;" >
  
  <table id="lista_dipartimenti" class="table table-hover  table-striped ">
  
  <thead><tr><th>Nome Dipartimento</th><th>Direttore</th><th>Telefono</th></tr></thead>
  
 
  
  </table>
   
  </div>
  
<!-- Fine tabella Dipartimenti --> 


  <div id="Tabella1" class="col-xs-6 col-xs-offset-1" style="margin-top: 50px ;width:560px ; height:400px;overflow:scroll;" >
  
  <table id="lista_areeScientifiche" class="table table-hover  table-striped ">
  
  <thead><tr><th>Codice</th><th>Nome area scientifica</th><th>Membri</th></tr></thead>
  
 
  
  </table>
   
  </div>
</div>

<div style="margin-top:25px; margin-left:125px" class="col-xs-6"><form method="post" action="InserimentoDipartimento.jsp"><button type="submit" class="btn btn-primary">Inserisci Dipartimento</button></form></div>
<div style="margin-top:25px" class="col-xs-2"><form method="post" action="InserimentoAreaScientifica.jsp"><button type="submit" class="btn btn-primary">Inserisci Area Scientifica</button></form></div>


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

   <div class="row  col-xs-offset-3">

  </div>
  
  <%@ include file="footer.html" %>

</body>
</html>