<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>



<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="assets/stileFooter.css" rel="stylesheet">
<link href="css/navbar.css" rel="stylesheet">
<link href="css/stile.css" rel="stylesheet">
 <script src="js/jquery.js"></script> 
<script src="js/bootstrap.js"></script> 
<script src="js/Inserimento_utente.js"></script> 
</head>
<body>


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
      </ul>
      

      <div class="navbar-right row" style="margin-top:10px; margin-right:10px">
        <form class="form-inline" role="form" action="./LogoutServlet" method="post">
       <label style="color: blue;">Benvenuto <% //out.print(amm.getNome()) %> sei loggato come  <% //out.print(ric.getRuolo()); %> </label>   
          <button type="submit" class="btn btn-primary input-sm"><span class="glyphicon glyphicon-user"></span>Logout</button>
        </form>
      </div>
     
     
    </div>
    <!--/.nav-collapse --> 
  </div>  <!-- Navbar -->
  
  <div class="row page-header   col-xs-offset-0 " style="margin-top:51px; text-align:center">
 <h1 id="NomeUtente">Inserimento Amministratore/Responsabile</h1><small id="small">Questa pagina permette l'inserimento di un Amministratore/Responsabile</small>
 </div> <!-- div gestione utenti -->


<form class="form-horizontal row" style="margin-left:50px" method="post" action="Inserisci_utente.jsp">
<input class="btn btn-primary" type="button"  value="Inserisci responsabile/amministratore"name=inserisci></br> 
<input  class="btn btn-primary" type="submit" style="margin-left: 900px;margin-top: -37px"  name=inserisci  value="Inserisci Utente">
</form>
  

               <form  name="formVisualizza" class="form-horizontal row" method="post" action="./InserisciAccountServlet">
               <div class="form-group" id="divNome">
               <label class="col-xs-offset-2 col-xs-2 control-label">Nome</label>
               <div class="col-xs-4" >
               <input class="form-control" type="text" id="Nome" name="Nome" required="required" onkeyup="controllaNome(this.value)"  />
               </div>
						<span class="help-inline"></span> </div> 
					     <div class="form-group" id="divCognome">
							<label class="col-xs-offset-2 col-xs-2 control-label">Cognome</label>
					       <div class="col-xs-4" ><input class="form-control" type="text" id="Cognome" name="Cognome" required="required"  onkeyup="controllaCognome(this.value)"/>
					       </div>
							<span class="help-inline"></span>
						</div>
						
						
						<div class="form-group" id="divUsername">
							<label class="col-xs-offset-2 col-xs-2 control-label">Username</label>
					        <div class="col-xs-4" ><input class="form-control" type="text" id="Username" name="Username" required="required"  onkeyup="controllaUsername(this.value);controlloUsername2(this.value);" />
					        </div>
							<span class="help-inline"></span>
						</div>
						
						 <div class="form-group" id="divPassword"> 
							<label class="col-xs-offset-2 col-xs-2 control-label">Password</label>
					        <div class="col-xs-4" >
					        <input class="form-control" type="text" id="Password" name="Password" required="required"  onkeyup="controllaPassword(this.value)" />
					        </div>
							<span class="help-inline"></span>
						</div>
						
							<div class="form-group" id="divTipologia" >
							<label class="col-xs-offset-2 col-xs-2 control-label">Tipologia</label>
					        <div class="col-xs-4" id="divSelect"><select name="Tipologia" id="select" class="form-control"><option value="Amministratore">Amministratore</option><option value="Responsabile">Responsabile</option></select></div>
							<span class="help-inline"></span>
						    </div>
						
						    <div><button name="bottoneModifica" id="bottoneModifica" type="button" onclick="controlloInvioDati();" data-toggle="modal" data-target="#myModal" class="btn btn-success" style="margin-left:290px ;margin-top:50px" >Conferma Inserimento</button>
					        <button  id="bottoneElimina" type="button" class="btn btn-danger " style="margin-left:370px;margin-top:50px" onclick="tornaIndietro()" >Annulla</button></div>;
						
					</form>	
  
<div class="row modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="vertical-align:middle">
  		<div class="modal-dialog" style="padding-top: 15%;">
    	<div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="h4">Elimina Dipartimento</h4>
      </div>
      <div class="modal-body">
        <p id="p">Sei sicuro di voler eliminare il dipartimento?</p>
      </div>
      <div class="modal-footer">
        <div style="display: inline-block;">
        
        <button type="button" id="bottone_conferma" class="btn btn-danger"  >Conferma eliminazione</button>
     	
     	 </div>
     	 </div>
    	</div><!-- /.modal-content -->
  	  </div><!-- /.modal-dialog -->
   </div><!-- /.modal -->


</body>
</html>