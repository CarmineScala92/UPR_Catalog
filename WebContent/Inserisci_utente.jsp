<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="assets/stileFooter.css" rel="stylesheet">
<link href="css/navbar.css" rel="stylesheet">
<link href="css/stile.css" rel="stylesheet">
 <script src="js/jquery.js"></script> 
<script src="js/bootstrap.js"></script> 
<script src="js/Inserimento_utente.js"></script> 

<title>Inserisci Account</title>
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
        
      <li class="active"><a href="./gestioneUtenti.jsp" ><span class="glyphicon glyphicon-briefcase"></span>Gestione Utenti</a></li>
      <li><a href="./gestioneProdotti.jsp"><span class="glyphicon glyphicon-folder-open"></span>Gestione Prodotti</a></li>
      <li><a href="./gestioneAteneo.jsp"><span class="glyphicon glyphicon-globe"></span>Gestione Ateneo</a></li>
      </ul>
      

      <div class="navbar-right row" style="margin-top:10px; margin-right:10px">
        <form class="form-inline" role="form" action="./LogoutServlet" method="post">
       <label style="color: blue;">Benvenuto <%if(amm!=null) out.print(amm.getNome());%> sei loggato come  <%if(amm!=null) out.print(amm.getTipologia()); %> </label>   
          <button type="submit" class="btn btn-primary input-sm"><span class="glyphicon glyphicon-user"></span>Logout</button>
        </form>
      </div>
     
     
    </div>
    <!--/.nav-collapse --> 
  </div>  <!-- Navbar -->
  
  <div class="row page-header   col-xs-offset-0 " style="margin-top:51px; text-align:center">
 <h1 id="NomeUtente">Inserimento Utente</h1><h2><small id="small">Questa pagina permette l'inserimento di un Utente</small></h2>
 </div> <!-- div gestione utenti -->
  
  <form class="form-horizontal row" style="margin-left:50px" method="post" action="InserimentoResp_Amm.jsp" >
  <input class="btn btn-primary"  value="Inserisci Amministratore/Responsabile" type="submit" name=inserisci></br>
  <input  value="Inserisci Utente" class="btn btn-primary" style="margin-left: 900px;margin-top: -37px" type="button"  name=inserisci  checked>
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
						
						<div class="form-group" id="divEmail">
							<label class="col-xs-offset-2 col-xs-2 control-label">E-mail</label>
					        <div class="col-xs-4" >
					        <input class="form-control" type="text" id="Email" name="Email" required="required" onkeyup="controllaEmail(this.value)" />
					        </div>
							<span class="help-inline"></span>
						</div>
						
						 <div class="form-group" id="divDataNascita">
							<label class="col-xs-offset-2 col-xs-2 control-label">Data di Nascita</label>
					        <div class="col-xs-4" ><input class="form-control" type="text" id="DataNascita" name="Data_Nascita" required="required"   onkeyup="controllaDataNascita(this.value)"/></div>
							<span class="help-inline"></span>
						</div>
						
					    <div class="form-group" id="divCittaNascita">
							<label class="col-xs-offset-2 col-xs-2 control-label">Comune di Nascita</label>
					       <div class="col-xs-4" ><input class="form-control" type="text" id="CittaNascita" name="Citta_Nascita" required="required"  onkeyup="controllaCittaNascita(this.value)" /></div>
							<span class="help-inline"></span>
					 	</div>
						
						<div class="form-group" id="divProvincia">
						<label class="col-xs-offset-2 col-xs-2 control-label">Provincia</label>
					       <div class="col-xs-4" ><input class="form-control" type="text" id="ProvinciaNascita" name="Provincia_Nascita" required="required"  onkeyup="controllaProvincia(this.value)" />
					       </div>
							<span class="help-inline"></span>
						</div>
						
						
					    <div class="form-group" id="divSesso">
							<label class="col-xs-offset-2 col-xs-2 control-label">Sesso</label>
					       <div class="col-xs-4" ><input class="form-control" type="text" id="Sesso" name="Sesso" required="required"  onkeyup="controllaSesso(this.value)"/></div>
							<span class="help-inline"></span>
						</div>
						
						<div class="form-group" id="divCodiceFiscale">
							<label class="col-xs-offset-2 col-xs-2 control-label">Codice Fiscale</label>
					        <div class="col-xs-4" ><input class="form-control" type="text" id="CodiceFiscale" name="Codice_Fiscale" required="required"  onkeyup="controllaCodiceFiscale(this.value)"/></div>
							<span class="help-inline"></span>
						</div>
						
					
						<div class="form-group" id="divMatricola">
							<label class="col-xs-offset-2 col-xs-2 control-label">Matricola</label>
					        <div class="col-xs-4" ><input class="form-control" type="text" id="Matricola" name="Matricola" required="required"  onkeyup="controllaMatricola(this.value)"/></div>
							<span class="help-inline"></span>
					</div>
						
						<div class="form-group" id="divDataInizio">
							<label class="col-xs-offset-2 col-xs-2 control-label">Data inizio servizio</label>
					       <div class="col-xs-4" ><input class="form-control" type="text" id="DataInizioServizio" name="Data_Inizio_Servizio" required="required"  onkeyup="controllaInizio(this.value)"/></div>
							<span class="help-inline"></span>
						</div>
						
						<div class="form-group" id="divRuolo">
							<label class="col-xs-offset-2 col-xs-2 control-label">Ruolo</label>
					        <div class="col-xs-4" id="ruoloInsert"><select name="Ruolo" class="form-control"><option value="ricercatore"></option><option  value="Direttore">Direttore dipartimento</option><option  value="Membro">Membro area scientifica</option></select></div>
							<span class="help-inline"></span>
						</div>
						
						<div class="form-group" id="divTipologia" hidden>
							<label class="col-xs-offset-2 col-xs-2 control-label">Tipologia</label>
					        <div class="col-xs-4" id="divSelect"><select name="Tipologia" id="select" class="form-control"><option value="Ricercatore">Ricercatore</option></select></div>
							<span class="help-inline"></span>
						</div>
						
						
						<div class="form-group" id="divDipartimento">
						
						<label class="col-xs-offset-2 col-xs-2 control-label">Dipartimento</label>
					        <div class="col-xs-4"><select name="ID_Dipartimento" class="form-control" id="Dipartimento"><option></option></select></div>
							
						</div>
						
					<div class="form-group" id="divAreaScientifica">
							<label class="col-xs-offset-2 col-xs-2 control-label">Area Scientifica</label>
					       <div class="col-xs-4" ><select name="ID_Area_Scientifica" class="form-control" id="Area_Scientifica"></select></div>
							<span class="help-inline"></span>
						</div>
						
<!-- 						 <input  name="ID_Account" value="'+$("#idAccount").val()+'" hidden> -->
							
						
								<div><button name="bottoneModifica" id="bottoneModifica" type="button" data-toggle="modal" data-target="#myModal" class="btn btn-success" style="margin-left:290px ;margin-top:50px" onclick="controlloInvioDati();" >Conferma Inserimento</button>
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