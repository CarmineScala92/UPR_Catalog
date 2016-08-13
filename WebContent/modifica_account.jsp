<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="assets/stileFooter.css" rel="stylesheet">
<link href="css/navbar.css" rel="stylesheet">
<link href="css/stile.css" rel="stylesheet">
 <script src="js/jquery.js"></script> 
<script src="js/bootstrap.js"></script> 
<script src="js/visualizzaAreaScientifica.js"></script> 

<script src="js/modificaAccount.js"></script>
<title>Modifica Account</title>
</head>
<body>
<%@ page import="it.unisa.upr.data.gestioneAutenticazione.Amministratore"%>

<%Amministratore amm=(Amministratore)session.getAttribute("Amministratore"); 

if(amm==null){
	String url=request.getContextPath()+"/index.jsp";
	response.sendRedirect(url);
}

%>


<input id="idAccount"  value=<% out.print(request.getParameter("id")); %> hidden>

<div class="row navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      <img class="navbar-brand" src="Loghi/logo.png" height="50px" width="150px" style="padding: 5px"> 
      </div>
    <div class="navbar-collapse collapse">
      <ul class="nav navbar-nav nav-pills">
        
      <li class="active"><a href="./gestioneUtenti.jsp" ><span class="glyphicon glyphicon-briefcase"></span>Gestione Utenti</a></li>
      <li><a href="./gestioneProdotti.jsp"><span class="glyphicon glyphicon-folder-open"></span>Gestione Prodotti</a></li>
      <li><a href="./gestioneAteneo.jsp"><span class="glyphicon glyphicon-globe"></span>Gestione Ateneo</a></li>
      <li><a href="./ricerca_prodotti.jsp"><span class="glyphicon glyphicon-search"></span>Ricerca Prodotti</a></li>
     
      </ul>
      

      <div class="navbar-right row" style="margin-top:10px; margin-right:10px">
        <form class="form-inline" role="form" action="./LogoutServlet" method="post">
       <label style="color: blue;">Benvenuto <%if(amm!=null) out.print(amm.getNome()); %> sei loggato come  <% if(amm!=null) out.print(amm.getTipologia()); %> </label>   
          <button type="submit" class="btn btn-primary input-sm"><span class="glyphicon glyphicon-user"></span>Logout</button>
        </form>
      </div>
     
     
    </div>
    <!--/.nav-collapse --> 
  </div>  <!-- Navbar -->
  
  <div class="row page-header   col-xs-offset-0 " style="margin-top:51px; text-align:center">
 <h1 id="NomeUtente"></h1><h2><small>In questa pagina è possibile modificare i dettagli relativi ad un utente<small></h2>
 </div> <!-- div gestione utenti -->
  
 
 
  
  
  <form id="formVisualizza" class="form-horizontal row" style="margin-top: 102px" method="post" action="./ModificaAccountServlet" name="formVisualizza">   
 
 
  
 	
 
 
  <div class="row" id="Div_Visualizza">
  
  
  
  
  
  </div>



 

<div class="row modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="vertical-align:middle">
  		<div class="modal-dialog" style="padding-top: 15%;">
    	<div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 id="h4" class="modal-title" id="myModalLabel">Modifica account</h4>
      </div>
      <div class="modal-body">
        <p id="p">Sei sicuro di voler modificare l'account?</p>
      </div>
      <div class="modal-footer">
      <div id="div_annulla" style="display: inline-block;">
        <button id="bottone_annulla" type="button" class="btn btn-default" data-dismiss="modal">Annulla</button>
        </div>
        <div style="display: inline-block;">
			
			
			<button id="bottone_conferma" type="submit" class="btn btn-success">Conferma modifica</button>
			
     	 </div>
     	 </div>
    	</div><!-- /.modal-content -->
  	  </div><!-- /.modal-dialog -->
   </div><!-- /.modal -->
 
 </form>



  <%@ include file="footer.html" %>
  
</body>

</html>
