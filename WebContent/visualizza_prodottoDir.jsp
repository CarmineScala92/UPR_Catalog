<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Inserimento Prodotto - Ricercatore</title>

<link href="assets/stileFooter.css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="css/navbar.css" />

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
<script src="js/VisualizzaProdottoDir.js"></script>
</head>
<body>
	<%@ page import="it.unisa.upr.data.gestioneAutenticazione.Account" %>
	<%@ page import="it.unisa.upr.data.gestioneAutenticazione.Ricercatore" %>
	<%@ page import="it.unisa.upr.data.gestioneAutenticazione.Amministratore" %>
	<%@ page import="it.unisa.upr.data.gestioneProdotti.Prodotto" %>
	<% 
		int idProdotto;
		Ricercatore ric=(Ricercatore)session.getAttribute("Ricercatore");
		Amministratore amm=(Amministratore)session.getAttribute("Amministratore");
		int idRic;
		if(ric == null && amm==null){
			idRic=0;
		}
		else 
			idRic=ric!=null?ric.getID_Account():amm.getID_Account();
		if(amm!=null){%>
				<input type='hidden' name='amministratore' id='amministratore' value='1'/>				
		<% }
		if(ric!=null && ric.getRuolo().equalsIgnoreCase("Direttore")){ %>
			<input type='hidden' name='direttore' id='direttore' value='1'/>
		<%}
		if(request.getParameter("id")==null){ %>
			<script>
				showModal("","Il prodotto cercato non esiste");
			</script>
	<%	}
		else {
			idProdotto=Integer.parseInt(request.getParameter("id"));
	%>
	<script>
	</script>
	
	<div class="container">
	
	 
	
    <% if(amm!=null){%>
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
			 <li ><a href="./gestioneUtenti.jsp" ><span class="glyphicon glyphicon-briefcase"></span>Gestione Utenti</a></li>
      <li><a href="./gestioneProdotti.jsp"><span class="glyphicon glyphicon-folder-open"></span>Gestione Prodotti</a></li>
      <li ><a href="./gestioneAteneo.jsp"><span class="glyphicon glyphicon-globe"></span>Gestione Ateneo</a></li>
       <li class=active><a href="./ricerca_prodotti.jsp"><span class="glyphicon glyphicon-search"></span>Ricerca Prodotti</a></li>
      </ul>
		 
		<div class="navbar-right" style="margin-top: 10px"> 
		
			<form class="form-inline" role="form" action="./LogoutServlet" method="post">
			
				<label style="color: blue;">Benvenuto <% out.print(amm.getNome()); %> sei loggato come  <% out.print(amm.getTipologia()); %> </label>
				<button type="submit" class="btn btn-primary input-sm"><span class="glyphicon glyphicon-user"></span>Logout</button>
			</form>
		
		</div>
		 
		 
		</div><!-- /.navbar-collapse -->
	</nav>
    
    <% }else if(ric!=null){%>
    
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
				<%
				if(ric!=null)
					if(ric.getRuolo().equalsIgnoreCase("Direttore") || ric.getRuolo().equalsIgnoreCase("Membro")){ %>
	      				<li><a href="validazione_homepage.jsp"><span class="glyphicon glyphicon-ok"></span>Validazione</a></li>
	      			<%}%>
		    </ul>
		 
		<div class="navbar-right" style="margin-top: 10px"> 
		

		
			<form class="form-inline" role="form" action="./LogoutServlet" method="post">
			   
				<label style="color: blue;">Benvenuto <% out.print(ric.getNome()); %> sei loggato come  <% out.print(ric.getRuolo()); %> </label>
				<button type="submit" class="btn btn-primary input-sm"><span class="glyphicon glyphicon-user"></span>Logout</button>
			</form>
		
		</div>
		 
		 
		</div><!-- /.navbar-collapse -->
	</nav>
    
    
    <%}else{%>
   	
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
   	
   	 
  <%} %>
	
	

  
  
	<div class="col-xs-6 col-xs-offset-3 page-header" style="margin-top: 51px" ><h2 style="text-align: center" id="titoloPagina">Visualizza Prodotto<br/><small>La seguente pagina mostra i dettagli relativi al prodotto</small></h2></div>
  
	<div class="alert alert-danger" id="divAlert" hidden ></div>  
  	
  
	<form class="form-horizontal row" style="margin-top: 102px" method="post" name="formInserimentoProdotto" accept-charset="utf-8"><!-- action="./ModificaProdottoServlet"> -->  
	<input type="hidden" id="ID_CurrentUser" value="<% out.print(idRic);  %>" ></input>
	<input type="hidden" id="ID_Prodotto" value="<% out.print(idProdotto);  %>" ></input>
	<input type='hidden' id="ID_Proprietario" value="<% out.print(idRic); %>" name="ID_Proprietario" />
	
	
	<div class="form-group">
		<label class="col-xs-offset-2 col-xs-2 control-label" for="titolo">Titolo *</label>
       	<div class="col-xs-4" >
		<input class="form-control" type="text" placeholder="Titolo" id="Titolo" name="Titolo" required="required" disabled value="" />
		<span class="help-inline"></span>
		</div>
    </div>
    
    <%if(amm!=null){ %>
    	<div id="divProprietario" class="form-group">
		<label class="col-xs-offset-2 col-xs-2 control-label">Proprietario</label>
		<div class="col-xs-4" >
		<select class="form-control" id="autore_proprietario" name="Autore_Proprietario" onchange="verificaCampo(this);" contenteditable="false" required>
		
		</select>
		</div>
	</div>
    <%} %>        
			
    <div class="form-group" id="divAutoriDisponibili">
      	
    </div>
    
    <div class="form-group" id="divAutoriRicercatori">
      	<label class="col-xs-offset-2 col-xs-2 control-label">Autori ricercatori</label>
      	<div class="col-xs-4" >
      	<textarea class="form-control" rows="3" cols="20" id="AutoriRicercatori" name="AutoriRicercatori" disabled ></textarea>
      	</div>
    </div>
                
    <div class="form-group" id='divID_Ricercatore'>
        
    </div>
				
	<div class="form-group">
        <label class="col-xs-offset-2 col-xs-2 control-label">Altri Autori</label>
        <div class="col-xs-4" >
		<input class="form-control" type="text" id="autori" name="Autori" placeholder="Altri autori" disabled /></div>
		<span class="help-inline"></span>
    </div>
            
    <div class="form-group">
		<label class="col-xs-offset-2 col-xs-2 control-label">Anno pubblicazione *</label>
        <div class="col-xs-4" ><input class="form-control" type="number" id="Anno_Pubblicazione" name="Anno_Pubblicazione" required="required" disabled /></div>
		<span class="help-inline"></span>
	</div>
		  
	<div class="form-group">
        <label class="col-xs-offset-2 col-xs-2 control-label">Abstract *</label>
		<div class="col-xs-4" ><textarea class="form-control" rows="6" cols="20" id="Abstract" name="Abstract" disabled ></textarea></div>
		<span class="help-inline"></span>
    </div>
            
    <div class="form-group">
		<label class="col-xs-offset-2 col-xs-2 control-label">Tipologia *</label>
        <div class="col-xs-4" >
		<select class="form-control" id="tipologia" name="Tipologia" onchange="createFields()" required="required" disabled>
            <option></option>
            <option value="articolo libro">Articolo su libro</option>
            <option value="articolo rivista">Articolo su rivista</option>
            <option value="brevetto">Brevetto</option>
            <option value="curatela">Curatela</option>
            <option value="monografia">Monografia</option>
            <option value="proceeding">Proceeding</option>
            <option value="altro">Altro</option>
        </select>
        </div>
		<span class="help-inline"></span>
	</div>
	
                
    <div id="otherFields" class="form-group">
		
	</div>
			
	<div id="otherFields2">
		<div class="form-group">
			<label class="col-xs-offset-2 col-xs-2 control-label">Stato prodotto *</label>
			<div class="col-xs-4">
			<input type="radio" id="Pubblico1" name="Pubblico" value="1" disabled><label class="control-label">Pubblico</label></input>
			<input type="radio" id="Pubblico2" name="Pubblico" value="0" disabled><label class="control-label">Privato</label></input>
			</div>
			<span class="help-inline"></span>
		</div>
				
		<div class="form-group" id="divFile">	
			<div class="col-xs-4" id="divButtonDownload">

			</div>
			<span class="help-inline"></span>
		</div>
	</div>
			
	<div class="form-group buttons-save-div">
        <div class="col-xs-offset-3 col-xs-7" id="bottoni-azione">
		</div>
    </div>
  
	</form>
	
	<div class="col-xs-offset-3 col-xs-7" style="padding-top:20px;padding-bottom:20px;" id="divBack">
		<button class="btn btn-warning" onclick="history.back()" id="historyBack">Indietro</button>
	</div>
   
    <div class="footer">
		<%@ include file="footer.html" %>
	</div>
	
	</div>
	
	
	
	<% } 
	%>
	
	
	<div class="modal fade" id="ModalMessaggio" tabindex="-1" role="dialog" aria-labelledby="ModalMessaggioLabel" aria-hidden="true" style="vertical-align:middle">
	  <div class="modal-dialog" style="padding-top: 5%;">
	    <div class="modal-content panel">
	      <div class="modal-header panel-heading">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="ModalMessaggioLabel"></h4>
	      </div>
	      <div class="modal-body">
	        <p id="TestoModal"></p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">OK</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<div class="modal fade" id="ModalElimina" tabindex="-1" role="dialog" aria-labelledby="ModalEliminaLabel" aria-hidden="true" style="vertical-align:middle">
	  <div class="modal-dialog" style="padding-top: 5%;">
	    <div class="modal-content panel">
	      <div class="modal-header panel-heading">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="ModalEliminaLabel">Elimina prodotto</h4>
	      </div>
	      <div class="modal-body">
	        <p>Sei sicuro di voler eliminare il prodotto ?</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Annulla</button>
	        <button type="button" class="btn btn-danger" onclick="eliminaFile()" data-dismiss="modal">Conferma</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<div class="modal fade" id="ModalModifica" tabindex="-1" role="dialog" aria-labelledby="ModalModificaLabel" aria-hidden="true" style="vertical-align:middle">
	  <div class="modal-dialog" style="padding-top: 5%;">
	    <div class="modal-content panel">
	      <div class="modal-header panel-heading">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="ModalModificaLabel">Modifica prodotto</h4>
	      </div>
	      <div class="modal-body">
	        <p>Sei sicuro di voler modificare il prodotto ?</p>
	      </div>
	      <div class="modal-footer">
	      	<input type="hidden" id="statoModifica" value=''></input>
	        <button type="button" class="btn btn-default" data-dismiss="modal">Annulla</button>
	        <button type="button" class="btn btn-success" onclick="modifica()" data-dismiss="modal">Conferma</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
</body>
</html>
