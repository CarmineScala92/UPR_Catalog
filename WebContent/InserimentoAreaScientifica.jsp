<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inserimento Area Scientifica</title>
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/navbar.css" rel="stylesheet">
<link href="assets/stileFooter.css" rel="stylesheet">
<link href="css/stile.css" rel="stylesheet">
<script src="js/jquery.js"></script> 
<script src="js/bootstrap.js"></script> 
<script src="js/controlliGestioneAteneo.js"></script> 
<script src="js/InserimentoAreaScientifica.js"></script> 
</head>
<body>
<%@ page import="it.unisa.upr.data.gestioneAutenticazione.Amministratore"%>

<%Amministratore amm=(Amministratore)session.getAttribute("Amministratore"); 

if(amm==null){
	String url=request.getContextPath()+"/index.jsp";
	response.sendRedirect(url);
}

%>

<div class="row navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      <img class="navbar-brand" src="Loghi/logo.png" height="50px" width="150px" style="padding: 5px"> 
      </div>
    <div class="navbar-collapse collapse">
      <ul class="nav navbar-nav nav-pills">
        
      <li ><a href="./gestioneUtenti.jsp" ><span class="glyphicon glyphicon-briefcase"></span>Gestione Utenti</a></li>
      <li><a href="./gestioneProdotti.jsp"><span class="glyphicon glyphicon-folder-open"></span>Gestione Prodotti</a></li>
      <li class=active><a href="./gestioneAteneo.jsp"><span class="glyphicon glyphicon-globe"></span>Gestione Ateneo</a></li>
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


<div class="row page-header   col-xs-offset-0 " style="margin-top:51px; text-align:center">
  <h1>Gestione Aree Scientifiche </h1><h2><small>Questa pagina permette di inserire una  nuova area scientifica.</small></h2>
  </div> <!-- div gestione utenti -->

  <form class="form-horizontal row" style="margin-top: 102px" method="post" action="./InserisciAreaScientificaServlet" name="formVisualizza">  
  <div class="form-group" id="Div_Nome">
		<label class="col-xs-offset-2 col-xs-2 control-label">Nome</label>
        <div class="col-xs-4" ><input class="form-control" type="text" id="Nome"  name="Nome" required="required" onkeyup="controllaNome(this.value);"/></div>
		<span class="help-inline"></span>
	</div>
	
	 <div class="form-group" id="divCodice">
		<label class="col-xs-offset-2 col-xs-2 control-label">Codice</label>
        <div class="col-xs-4" ><input class="form-control" type="text" id="Codice" name="Codice" required="required" onkeyup="controllaCodice(this.value)"/></div>
		<span class="help-inline"></span>
	</div>
	
	 <div class="form-group" id="divTelefono">
		<label class="col-xs-offset-2 col-xs-2 control-label">Telefono</label>
        <div class="col-xs-4" ><input class="form-control" type="text" id="Telefono" name="Telefono" required="required" onkeyup="controllaTelefono(this.value)"/></div>
		<span class="help-inline"></span>
	</div>
	
	 <div class="form-group" id=divFax>
		<label class="col-xs-offset-2 col-xs-2 control-label">Fax</label>
        <div class="col-xs-4" ><input class="form-control" type="text" id="Fax" name="Fax" required="required" onkeyup="controllaFax(this.value)"/></div>
		<span class="help-inline"></span>
	</div>
	
	 <div class="form-group" id=divEmail>
		<label class="col-xs-offset-2 col-xs-2 control-label">E-mail</label>
        <div class="col-xs-4" ><input class="form-control" type="text" id="Email" name="Email" onkeyup="controllaEmail(this.value)"/></div>
		<span class="help-inline"></span>
	</div>
	
	 <div class="form-group" id=divSito>
		<label class="col-xs-offset-2 col-xs-2 control-label">Sito Web</label>
        <div class="col-xs-4" ><input class="form-control" type="text" id="Sito" name="Sito"  onkeyup="controllaSito(this.value)"/></div>
		<span class="help-inline"></span>
	</div>
	
	  <input name="ID_Dipartimento" value="<% out.print(request.getParameter("id")); %>" hidden> <!-- ha un campo nascosto in piu -->
	
			<div>
			<button name="bottoneModifica" id="bottoneModifica" type="button" data-toggle="modal" data-target="#myModal" class="btn btn-primary" style="margin-left:290px ;margin-top:50px" onclick="controlloInvioDati()">Conferma Inserimento</button>
			<button  id="bottoneA" type="button" class="btn btn-primary " style="margin-left:570px;margin-top:50px" onclick="tornaAGestioneAteneo()">Annulla</button>
			</div>
	
	



 <div class=" row modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="vertical-align:middle">
  		<div class="modal-dialog" style="padding-top: 15%;">
    	<div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 id="h4" class="modal-title" id="myModalLabel">Inserimento Area Scientifica</h4>
      </div>
      <div class="modal-body">
        <p id="p">Inserimento avvenuto con successo</p>
      </div>
      <div class="modal-footer">
      <div style="display: inline-block;">
        
      <button id="bottoneAnnulla" type="button" class="btn btn-default" data-dismiss="modal">Annulla</button>
      </div>
      <div style="display: inline-block;">
        
        <button id="bottoneOk" type="button" class="btn btn-default" data-dismiss="modal">OK</button>
        </div>
        <div style="display: inline-block;">
        </div>
     	 </div>
    	</div><!-- /.modal-content -->
  	  </div><!-- /.modal-dialog -->
   </div><!-- /.modal -->

</form>

<%@ include file="footer.html" %>

</body>
</html>