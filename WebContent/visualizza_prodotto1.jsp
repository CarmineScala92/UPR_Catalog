<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Inserimento Prodotto - Ricercatore</title>

<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="css/navbar.css" />
<link rel="stylesheet" type="text/css" href="css/stile.css" />


<style>
.container {
	width:100%;
}
.header {
	text-align:center;	
}
.center {
	padding-left: 40%;
}
body {
	padding-top: 70px;	
	margin-bottom: 70px;
}
.user-logout {
	float: right;
	padding-right: 5%;
}
.border-active {
	border:dashed;
	border-width:1px;
	margin:2px 2px 2px 2px;
}
.buttons-save-div {
	padding-top: 20px;
}

</style>
<script src="js/jquery-1.10.2.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/VisualizzaProdotto.js"></script>
</head>
<body>
	<%@ page import="it.unisa.upr.data.gestioneAutenticazione.Account" %>
	<%@ page import="it.unisa.upr.data.gestioneAutenticazione.Ricercatore" %>
	<%@ page import="it.unisa.upr.data.gestioneProdotti.Prodotto" %>
	<% 
		int idProdotto;
		Ricercatore ric=(Ricercatore)session.getAttribute("Ricercatore");
		int idRic;
		if(ric == null){
			idRic=0;
		}
		else 
			idRic=ric.getID_Account();
		if(request.getParameter("id")==null){ %>
			<script>
				alert("Il prodotto cercato non esiste");
				window.history.back();
			</script>
	<%	}
		else {
			idProdotto=Integer.parseInt(request.getParameter("id"));
	%>
	<script>
	</script>
	<div class="container">
    
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
				<li><a href="ricerca_prodotti.jsp"><span class="glyphicon glyphicon-search"></span>Ricerca</a></li>
				<li><a href="inserimento_prodotto.jsp"><span class="glyphicon glyphicon-plus"></span>Inserimento</a></li>
				<%if(ric.getRuolo().equalsIgnoreCase("Direttore") || ric.getRuolo().equalsIgnoreCase("Membro")){ %>
      				<li><a href="validazione_homepage.jsp"><span class="glyphicon glyphicon-ok"></span>Validazione</a></li>
      			<%}%>
		    </ul>
		 
		<div class="navbar-right" style="margin-top: 10px"> 
		<%
			if(ric==null){
		%>
			<form action="./LoginServlet" method="post">
	 		<ul class="nav navbar-nav navbar-right">
	 		<li><input  name="Username" type="text" class="form-control" id="Username" placeholder="Username" style="margin-top: 10px" required="required"/></li>
	 		<li><input  name="Password" type="password" class="form-control" id="Password" placeholder="Password" style="margin-top: 10px" required/></li>
	 		<li><button type="submit" class="btn btn-primary" style="margin-right: 20px;margin-top:10px"><span class="glyphicon glyphicon-user"></span>Login</button></li>
	 		</ul>
	 		</form>
		<%
			}
			else {
		%>
		
			<form class="form-inline" role="form" action="./LogoutServlet" method="post">
				<label style="color: blue;">Benvenuto <% out.print(ric.getNome()); %> sei loggato come  <% out.print(ric.getRuolo()); %> </label>
			    <button type="submit" class="btn btn-primary input-sm"><span class="glyphicon glyphicon-user"></span>Logout</button>
			</form>
		<%
			}
		%>
		</div>
		 
		 
		</div><!-- /.navbar-collapse -->
	</nav>	
  
	<div class="col-xs-6 col-xs-offset-3 page-header" style="margin-top: 51px" ><h2 style="text-align: center" id="titoloPagina">Visualizza Prodotto</h2></div>

	<div class="alert alert-danger" id="divAlert" hidden ></div>  
  
  
	<form class="form-horizontal row" style="margin-top: 102px" method="post" name="formInserimentoProdotto">    
	<input type="hidden" id="ID_CurrentUser" value=<% out.print(idRic);  %> ></input>
	<input type="hidden" id="ID_Prodotto" value="<% out.print(idProdotto);  %>" ></input>
	
	<input class="form-control" type='hidden' id="ID_Proprietario" value="<%  %>" name="ID_Proprietario" />
	
	<div class="form-group">
		<label class="col-xs-offset-2 col-xs-2 control-label" for="titolo">Titolo</label>
       	<div class="col-xs-4" >
		<input class="form-control" type="text" placeholder="Titolo" id="Titolo" name="Titolo" required="required" disabled value="" />
		<span class="help-inline"></span>
		</div>
    </div>
            
			
    <div class="form-group" id="divAutoriDisponibili">
      	
    </div>
                
    <div class="form-group">
        <label class="col-xs-offset-2 col-xs-2 control-label">Autori selezionati</label>
        <div class="col-xs-4">
		<select class="form-control" id="ID_Ricercatore" name="ID_Ricercatore" multiple="multiple" disabled></select></div>
		<span class="help-inline"></span>
		<div id="divBtnRimuovi">
		
		</div>
    </div>
				
	<div class="form-group">
        <label class="col-xs-offset-2 col-xs-2 control-label">Altri Autori</label>
        <div class="col-xs-4" >
		<input class="form-control" type="text" id="autori" name="Autori" placeholder="Altri autori" disabled /></div>
		<span class="help-inline"></span>
    </div>
            
    <div class="form-group">
		<label class="col-xs-offset-2 col-xs-2 control-label">Anno pubblicazione</label>
        <div class="col-xs-4" ><input class="form-control" type="number" id="Anno_Pubblicazione" name="Anno_Pubblicazione" required="required" disabled /></div>
		<span class="help-inline"></span>
	</div>
		  
	<div class="form-group">
        <label class="col-xs-offset-2 col-xs-2 control-label">Abstract</label>
		<div class="col-xs-4" ><textarea class="form-control" rows="6" cols="20" id="Abstract" name="Abstract" disabled ></textarea></div>
		<span class="help-inline"></span>
    </div>
            
    <div class="form-group">
		<label class="col-xs-offset-2 col-xs-2 control-label">Tipologia</label>
        <div class="col-xs-4" >
		<select class="form-control" id="tipologia" name="Tipologia" onchange="createFields()" required="required" disabled>
            <option></option>
            <option value="Articolo su libro">Articolo su libro</option>
            <option value="Articolo su rivista" selected>Articolo su rivista</option>
            <option value="Brevetto">Brevetto</option>
            <option value="Curatela">Curatela</option>
            <option value="Monografia">Monografia</option>
            <option value="Proceeding">Proceeding</option>
            <option value="Altro">Altro</option>
        </select>
        </div>
		<span class="help-inline"></span>
	</div>
	
                
    <div id="otherFields" class="form-group">
		
	</div>
			
	<div id="otherFields2">
		<div class="form-group">
			<label class="col-xs-offset-2 col-xs-2 control-label">Stato prodotto</label>
			<div class="col-xs-4">
			<input type="radio" id="Pubblico1" name="Pubblico" value="1" checked disabled><label class="control-label">Pubblico</label></input>
			<input type="radio" id="Pubblico2" name="Pubblico" value="0" disabled><label class="control-label">Privato</label></input>
			</div>
			<span class="help-inline"></span>
		</div>
				
		<div class="form-group" id="divFile">	
			<div class="col-xs-4" id="divButtonDownload">
				<input type='hidden' id="file"/>
			</div>
			<span class="help-inline"></span>
		</div>
	</div>
			
	<div class="form-group buttons-save-div">
        <div class="col-xs-offset-3 col-xs-7" id="bottoni-azione">
		<% 	%>
			<button id="btnModProd" type="button"  class="btn btn-warning" onclick="abilitaCampi()">Modifica prodotto</button>
			<button id="btnDelProd" type="button" data-toggle="modal" data-target="#ModalElimina" class="btn btn-danger col-xs-offset-2" >Elimina prodotto</button>
			<button id="btnPrintProd" type="button" onclick="window.location.href='UploadDownloadFileServlet?fileName=README.txt'" class="btn btn-primary col-xs-offset-2" >Stampa prodotto</button>
		<% %>
		</div>
    </div>
	
	</form>
   
    <div class="footer">
		
	</div>
	
	</div>
	<% } 
	%>
	
	
	
<!-- Modal -->
<div class="modal fade danger" id="ModalElimina" tabindex="-1" role="dialog" aria-labelledby="ModalEliminaLabel" aria-hidden="true" style="vertical-align:middle">
  <div class="modal-dialog" style="padding-top: 5%;">
    <div class="modal-content panel-danger">
      <div class="modal-header panel-heading">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="ModalEliminaLabel">Elimina prodotto</h4>
      </div>
      <div class="modal-body">
        <p>Sei sicuro di voler eliminare il prodotto "Software & Tecnology" ?</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Annulla</button>
        <button type="button" class="btn btn-danger">Conferma eliminazione</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
	
	
	
</body>
</html>
