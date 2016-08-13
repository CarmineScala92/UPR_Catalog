<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualizza Utente</title>

<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/stile.css" rel="stylesheet">
<link href="assets/stileFooter.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/navbar.css" rel="stylesheet">
<link href="css/stile.css" rel="stylesheet">
 <script src="js/jquery.js"></script> 
<script src="js/bootstrap.js"></script> 
<script src="js/visualizzaUtente.js"></script> 

</head>
<body>
<%@ page import="it.unisa.upr.data.gestioneAutenticazione.Amministratore"%>
<%@ page import="it.unisa.upr.data.gestioneAutenticazione.Responsabile"%>

<%Amministratore amm=(Amministratore) session.getAttribute("Amministratore");

	if(amm==null){
		response.sendRedirect("index.jsp");
	}

%>
<div class="row navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      <img class="navbar-brand" src="Loghi/logo.png" height="50px" width="150px" style="padding: 5px"> 
      </div>
      <div class="navbar-collapse collapse">
      <ul class="nav navbar-nav nav-pills">
        
      <li class=active><a href="./gestioneUtenti.jsp" ><span class="glyphicon glyphicon-briefcase"></span>Gestione Utenti</a></li>
      <li><a href="./gestioneProdotti.jsp"><span class="glyphicon glyphicon-folder-open"></span>Gestione Prodotti</a></li>
      <li ><a href="./gestioneAteneo.jsp"><span class="glyphicon glyphicon-globe"></span>Gestione Ateneo</a></li>
       <li><a href="./ricerca_prodotti.jsp"><span class="glyphicon glyphicon-search"></span>Ricerca Prodotti</a></li>
      
      </ul>
      

      <div class="navbar-right row" style="margin-top:10px; margin-right:10px">
        <form class="form-inline" role="form" action="./LogoutServlet" method="post">
       <label style="color: blue;">Benvenuto <% out.print(amm.getNome()); %> sei loggato come  <% out.print(amm.getTipologia()); %> </label>   
          <button type="submit" class="btn btn-primary input-sm"><span class="glyphicon glyphicon-user"></span>Logout</button>
        </form>
      </div>
     
     
    </div>
    <!--/.nav-collapse --> 
  </div>  <!-- Navbar -->





 <div class="row page-header   col-xs-offset-0 " style="margin-top:51px; text-align:center">
 <h1 id="NomeUtente"> </h1><h2><small>In questa pagina è possibile visualizzare i dettagli di un utente</small></h2>
 </div> <!-- div gestione utenti -->


 <div class="row">
  <button type="button" onclick="tornaIndietro2();" class="btn btn-primary" style="margin-left: 20px; margin-top: -90px"><span class="glyphicon glyphicon-arrow-left"></span>Indietro</button>
  </div>
  
  
   <div class="row col-xs-6 col-xs-offset-3 alert alert-danger fade in" hidden id="non_visibile">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">x</button>
        <div id="parole" class="alert-msg"></div>
    </div>

<input id="idAccount" value="<% out.print(request.getParameter("id")); %>" hidden>

<div class="form-horizontal row" id="Visualizza">

</div>

<button id="bottoneModifica" type="button" class="btn btn-success" style="margin-left:290px ;margin-top:50px" onclick="modifica()">Modifica utente</button>

<form method="post" action="./EliminaAccountServlet">
<%-- <input name="ID_Account" value="<% out.print(request.getParameter("id")); %>" hidden>  --%>
<button  id="bottoneElimina" type="button" class="btn btn-danger " data-toggle="modal" data-target="#myModal2" style="margin-left:770px;margin-top:-32px">Elimina utente</button> 
 </form> 
 <!--<form id="formVisualizza" class="form-horizontal row" style="margin-top: 102px" method="post" name="formVisualizza"> --> 
 
 
  <div class="row modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="vertical-align:middle">
  		<div class="modal-dialog" style="padding-top: 15%;">
    	<div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">Elimina Utente</h4>
      </div>
      <div class="modal-body">
        <p>Sei sicuro di voler eliminare l'utente?</p>
      </div>
      <div class="modal-footer">
      <div style="display: inline-block;">
        <button type="button" class="btn btn-default" data-dismiss="modal">Annulla</button>
        </div>
        <div style="display: inline-block;">
        <form method="post" action="./EliminaAccountServlet">
        <input name="ID_Account" value="<% out.print(request.getParameter("id")); %>" hidden>
        <button type="submit" class="btn btn-danger"  >Conferma eliminazione</button>
     	 </form>
     	 </div>
     	 </div>
    	</div><!-- /.modal-content -->
  	  </div><!-- /.modal-dialog -->
   </div><!-- /.modal -->
 
 
 
 
	
<script src="js/jquery.js"></script> 
	<script src="js/bootstrap.js"></script> 
	<script type="text/javascript">
			
			
		function lanciaNotifica(strong, frase){
			$(".alert").show();
			$(".alert-msg").html('<strong>'+strong+'</strong>'+frase);
			window.setTimeout(function(){ $(".alert").alert('close'); }, 3000);
		}
				  			  
            </script>
  
   
   <%if(session.getAttribute("Error")!=null){ 
	   session.removeAttribute("Error");
   %>
   
   		<script type="text/javascript">
   		lanciaNotifica("Errore","L\'operazione è fallita!");
   		
   		</script>
   		
 
   <%} %>
   
   
   <%if(session.getAttribute("Response")!=null){
	   session.removeAttribute("Response");%>
   		
   		<script type="text/javascript">
   		document.getElementById("non_visibile").className="col-xs-6 col-xs-offset-3 alert alert-success fade in";
   		lanciaNotifica("Ben fatto!","L\'operazione è stata eseguita con successo");
   		</script>
	
   <%}%>	


 
<%@ include file="footer.html" %>

</body>
</html>