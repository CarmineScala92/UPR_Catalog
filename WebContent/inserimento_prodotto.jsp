<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Inserimento Prodotto - UPR-Catalog</title>
<link href="css/stile.css" rel="stylesheet" />
<link href="assets/stileFooter.css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/navbar.css" />

	<script src="js/jquery.js"></script> 
	<script src="js/bootstrap.min.js"></script> 
	<script src="js/controlloRicercaProdotto.js"></script>
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
	padding-top: 50px;
	
}
.error-custom {
	color: red;
}

</style>
<script src="js/jquery-1.10.2.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/InserimentoProdotto.js"></script>
</head>
<body>
	<%@ page import="it.unisa.upr.data.gestioneAutenticazione.Account" %>
	<%@ page import="it.unisa.upr.data.gestioneAutenticazione.Ricercatore"%>
	<%@ page import="it.unisa.upr.data.gestioneAutenticazione.Amministratore"%>
	<% 
		Ricercatore ric = (Ricercatore) session.getAttribute("Ricercatore");
		Amministratore amm=(Amministratore) session.getAttribute("Amministratore");
		if (ric == null && amm==null) {
			response.sendRedirect("index.jsp");
		}
		else {
			String nome;
			String cognome;
			String ruolo;
			nome=ric==null?amm.getNome():ric.getNome();
			cognome=ric==null?amm.getCognome():ric.getCognome();
			ruolo=ric==null?"Amministratore":ric.getRuolo();
	%>
	<div class="container">
    <%
    	String risposta=request.getParameter("response");
    	int r=Integer.parseInt(risposta==null?"0":risposta);
	   	switch(r){
	   	case 1:
	   		%>
	   			<script>
	   				$(document).ready(function(){
	   					showModal("Prodotto esistente", "Il prodotto è già presente nel database.");	
	   				});
	   				
	   			</script>
	   		<%
	   		break;
	   	case 2:
	   		%>
		   		<script>
		   				$(document).ready(function(){
		   					showModal("Errore durante l'inserimento", "Non è stato possibile inserire il prodotto. Riprovare più tardi.");	
		   				});
		   				
		   		</script>
	   		<%
	   		break;
	   	}
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
				<%if(ric!=null){ %>
				<li><a href="./ric_homepage.jsp"><span class="glyphicon glyphicon-home"></span>Home</a></li>
				<li><a href="./ricerca_prodotti.jsp"><span class="glyphicon glyphicon-search"></span>Ricerca</a></li>
				<li class="active" ><a href="./inserimento_prodotto.jsp"><span class="glyphicon glyphicon-plus"></span>Inserimento</a></li>
			   		<%if(ric!=null){
			   			if(ric.getRuolo().equalsIgnoreCase("Direttore") || ric.getRuolo().equalsIgnoreCase("Membro")){ %>
	      				<li><a href="validazione_homepage.jsp"><span class="glyphicon glyphicon-ok"></span>Validazione</a></li>
	      				<%}%>
	      			<%} %>
      			<%}
				 else if(amm!=null){ %>
      			 <li><a href="./gestioneUtenti.jsp" ><span class="glyphicon glyphicon-briefcase"></span>Gestione Utenti</a></li>
     			 <li class=active><a href="./gestioneProdotti.jsp"><span class="glyphicon glyphicon-folder-open"></span>Gestione Prodotti</a></li>
      			 <li><a href="./gestioneAteneo.jsp"><span class="glyphicon glyphicon-globe"></span>Gestione Ateneo</a></li>
       			 <li><a href="./ricerca_prodotti.jsp"><span class="glyphicon glyphicon-search"></span>Ricerca Prodotti</a></li>
      			<%} %>
		    </ul>
		 
		 <div class="navbar-right" style="margin-top: 10px">
		<form class="form-inline" role="form" action="./LogoutServlet" method="post">
			<label style="color: blue;">Benvenuto <% out.print(nome); %> sei loggato come  <%out.print(ruolo); %> </label>
		    <button type="submit" class="btn btn-primary input-sm"><span class="glyphicon glyphicon-user"></span>Logout</button>
		</form>
		 </div>
		 
		</div><!-- /.navbar-collapse -->
	</nav>	
   <!-- Main component for a primary marketing message or call to action -->
<div class="col-xs-6 col-xs-offset-3 page-header" style="margin-top: 31px" ><h2 style="text-align: center">Inserimento Prodotto<br><small>La pagina permette di inserire un prodotto nel catalogo.</small></h2></div>

  
  
	<div class="alert alert-danger" id="divAlert" hidden ></div>  
	<input type='hidden' id="ProprietarioNome" value='<% out.print(nome +" "+ cognome); %>>'  ></input>
	<form  class="form-horizontal row" style="margin-top: 102px" method="post" action="./InserisciProdottoServlet" name="formInserimentoProdotto" enctype="multipart/form-data">    
	
	<input class="form-control" type='hidden' id="ID_Proprietario" value="<% out.print(ric==null?amm.getID_Account():ric.getID_Account()); %>" name="ID_Proprietario" />
	
	<div id="divTitolo" class="form-group">
		<label class="col-xs-offset-2 col-xs-2 control-label" for="titolo">Titolo *</label>
       	<div class="col-xs-4" >
		<input class="form-control" type="text" placeholder="Titolo" id="Titolo" name="Titolo" required="required" onkeyup="verificaCampo(this)"/>
		<!-- <span class="help-inline"></span> -->
		</div>
    </div>
            
    <%if(amm!=null){ %>
	<div id="divProprietario" class="form-group">
		<label class="col-xs-offset-2 col-xs-2 control-label">Proprietario</label>
		<div class="col-xs-4" >
		<select class="form-control" id="autore_proprietario" name="Autore_Proprietario" onchange="onChangeProprietario();verificaCampo(this);" contenteditable="false" required>
		
		</select>
		</div>
	</div>
	<%} %>
    <div class="form-group">
      	<label class="col-xs-offset-2 col-xs-2 control-label">Autori disponibili</label>
	    <div class="col-xs-4" >
		<select class="form-control" id="authors_select" contenteditable="false">
		<!-- caricamento lista autori -->
        </select>
		</div>
		<span class="help-inline"></span>
        <button type="button" class="btn" onclick="aggiungiAutore()" id="btnAggiungi">Aggiungi</button>
		<span class="help-inline"></span>
    </div>
                
    <div class="form-group">
        <label class="col-xs-offset-2 col-xs-2 control-label">Autori selezionati</label>
        <div class="col-xs-4">
		<select class="form-control" id="ID_Ricercatore" name="ID_Ricercatore" multiple="multiple"></select></div>
		<span class="help-inline"></span>
		<button type="button" class="btn" onclick="rimuoviAutore()" id="btnRimuovi">Rimuovi</button>
    </div>
				
	<div class="form-group" id="divAltriAutori">
        <label class="col-xs-offset-2 col-xs-2 control-label">Altri Autori</label>
        <div class="col-xs-4" >
		<input class="form-control" type="text" id="autori" name="Autori" placeholder="Altri autori" onkeyup="verificaCampo(this)"/></div>
		<span class="help-inline"></span>
    </div>
            
    <div class="form-group" id="divAnnoPubblicazione">
		<label class="col-xs-offset-2 col-xs-2 control-label">Anno pubblicazione *</label>
        <div class="col-xs-4" ><input class="form-control" type="number" id="Anno_Pubblicazione" name="Anno_Pubblicazione" onkeyup="verificaCampo(this)" required/></div>
		<span class="help-inline"></span>
	</div>
		  
	<div class="form-group" id="divAbstract">
        <label class="col-xs-offset-2 col-xs-2 control-label">Abstract *</label>
		<div class="col-xs-4" ><textarea class="form-control" rows="6" cols="20" id="Abstract" name="Abstract" onkeyup="verificaCampo(this)"></textarea></div>
		<span class="help-inline"></span>
    </div>
            
    <div class="form-group" id="divTipologia">
		<label class="col-xs-offset-2 col-xs-2 control-label">Tipologia *</label>
        <div class="col-xs-4" >
		<select class="form-control" id="tipologia" name="Tipologia" onchange="createFields(); verificaCampo(this)" required="required">
            <option selected></option>
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
			<input type="radio" id="Pubblico" name="Pubblico" value="1" checked><label   class="control-label">Pubblico</label></input>
			<input type="radio" id="Pubblico" name="Pubblico" value="0"><label class="control-label">Privato</label></input>
			</div>
			<span class="help-inline"></span>
		</div>
				
		<div class="form-group" id="divFile">
			<label class="col-xs-offset-2 col-xs-2 control-label">File *</label>
			<div class="col-xs-4">
				<input class="form-control" type="file" id="file" name="fileName" onchange="verificaCampo(this)" required/></div>
			<span class="help-inline"></span>
		</div>
		
	</div>
	
	<div class="col-xs-offset-3">
		<span>I campi contrassegnati da * sono obbligatori per l'inserimento Completo.<br/>(Per inserire un prodotto in bozza sono necessari i campi Titolo, Tipologia, Anno Pubblicazione e File).</span>
	</div>
	</form>
	<!-- 
	<div class="form-group">
		<iframe src="Upload.jsp" id="uploadFrame" class=" col-xs-offset-3 col-xs-5"></iframe>
	</div>
	 -->
	<div id="buttons" class="form-group buttons-save-div">
        <div class="col-xs-offset-3 col-xs-7 " >
        	<button type="button" class="btn btn-primary col-xs-offset-1" data-toggle="modal" data-target="#ModalCompleto" >Inserisci Prodotto 'Completo'</button>
  			<button type="button" class="btn btn-warning col-xs-offset-2" data-toggle="modal" data-target="#ModalBozza">Inserisci Prodotto 'Bozza'</button>      
        </div>
    </div>
	
	
	</div>
	<%@ include file="footer.html" %>
	<% } %>
	
	<div class="modal fade" id="ModalCompleto" tabindex="-1" role="dialog" aria-labelledby="ModalCompletoLabel" aria-hidden="true" style="vertical-align:middle">
	  <div class="modal-dialog" style="padding-top: 5%;">
	    <div class="modal-content panel">
	      <div class="modal-header panel-heading">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="ModalCompletoLabel">Inserimento prodotto</h4>
	      </div>
	      <div class="modal-body">
	        <p>Vuoi inserire il prodotto <script>$("#Titolo").val();</script> in stato Completo ?</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Annulla</button>
	        <button type="button" class="btn btn-danger" onclick="verificaEsistenzaCompleto()" data-dismiss="modal">Conferma</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<div class="modal fade" id="ModalBozza" tabindex="-1" role="dialog" aria-labelledby="ModalBozzaLabel" aria-hidden="true" style="vertical-align:middle">
	  <div class="modal-dialog" style="padding-top: 5%;">
	    <div class="modal-content panel">
	      <div class="modal-header panel-heading">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="ModalBozzaLabel">Inserimento prodotto</h4>
	      </div>
	      <div class="modal-body">
	        <p>Vuoi inserire il prodotto <script>$("#Titolo").val();</script> in stato Bozza ?</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Annulla</button>
	        <button type="button" class="btn btn-success" onclick="verificaEsistenzaBozza()" data-dismiss="modal">Conferma</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
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
	
	<div class="modal fade" id="ModalInsCompleto" tabindex="-1" role="dialog" aria-labelledby="ModalInsCompletoLabel" aria-hidden="true" style="vertical-align:middle">
	  <div class="modal-dialog" style="padding-top: 5%;">
	    <div class="modal-content panel">
	      <div class="modal-header panel-heading">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="ModalInsCompletoLabel">Inserimento prodotto</h4>
	      </div>
	      <div class="modal-body">
	        <p>Il prodotto è già presente. Vuoi inserirlo lo stesso?</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Annulla</button>
	        <button type="button" class="btn btn-danger" onclick="inserisciProdottoCompleto()" data-dismiss="modal">Conferma</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<div class="modal fade" id="ModalInsBozza" tabindex="-1" role="dialog" aria-labelledby="ModalInsBozzaLabel" aria-hidden="true" style="vertical-align:middle">
	  <div class="modal-dialog" style="padding-top: 5%;">
	    <div class="modal-content panel">
	      <div class="modal-header panel-heading">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="ModalInsBozzaLabel">Inserimento prodotto</h4>
	      </div>
	      <div class="modal-body">
	        <p>Il prodotto è già presente. Vuoi inserirlo lo stesso?</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Annulla</button>
	        <button type="button" class="btn btn-danger" onclick="inserisciProdottoBozza()" data-dismiss="modal">Conferma</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
</body>
</html>
