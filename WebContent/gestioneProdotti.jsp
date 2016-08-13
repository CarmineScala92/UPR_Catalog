<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestione Prodotti - UPR-Catalog</title>
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/navbar.css" rel="stylesheet">
<link href="css/stile.css" rel="stylesheet">
<link href="assets/stileFooter.css" rel="stylesheet">
 <script src="js/jquery.js"></script> 
<script src="js/bootstrap.js"></script> 
<script src="js/index.js"></script> 
<script src="js/gestioneProdotti.js"></script> 
<style>
td{
  text-align:center;
  }
  th{
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
      <li class=active><a href="./gestioneProdotti.jsp"><span class="glyphicon glyphicon-folder-open"></span>Gestione Prodotti</a></li>
      <li ><a href="./gestioneAteneo.jsp"><span class="glyphicon glyphicon-globe"></span>Gestione Ateneo</a></li>
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
  <h1>Gestione Prodotti </h1>
  </div> <!-- div gestione utenti -->
  
  <form class="form-horizontal" role="form" method="post" action="">
  <div class="form-group col-xs-3" style="display:inline-block">
    <label for="inputEmail3" class="col-sm-4 control-label">Titolo</label>
    <div class="col-sm-8">
      <input id="Titolo" type="text" class="form-control" id="inputEmail3" placeholder="Titolo">
    </div>
    
  </div>


  <div class="form-group col-xs-3" style="display:inline-block">
    <label for="inputEmail3" class="col-sm-4 control-label">Tipologia</label>
     <div class="col-sm-8">
     <select id="Tipologia" class="form-control">
       <option ></option>
       <option value="articolo libro">Articolo su libro</option>
       <option value="articolo rivista">Articolo su rivista</option>
       <option value="Brevetto">Brevetto</option>
       <option value="Curatela">Curatela</option>
       <option value="Monografia">Monografia</option>
       <option value="Proceeding">Proceeding</option>
       <option value="Altro">Altro</option>
     </select>

     </div>
    
  </div>

  <div class="form-group col-xs-3" style="display:inline-block">
    <label for="inputEmail3" class="col-sm-4 control-label">Anno</label>
    <div class="col-sm-8">
      <input  id="Anno" type="text" class="form-control" id="inputEmail3" placeholder="Anno Pubblicazione">
      
    </div>
 </div>
 
   <div class="form-group col-xs-3" style="display:inline-block">
    <button type="button" class="btn btn-primary " onclick="RicercaProdotti()"><span class="glyphicon glyphicon-search"></span>Ricerca</button><button class="btn" type="button" onclick="ListaProdotti()"><span class="glyphicon glyphicon-repeat" ></button>
   </div>
 
 
 </form>

<div id='FunzioniAmministratore' class="col-xs-6" style="margin-top: 90px ;width:980px;">
	<div style="margin:auto">
		<button class="btn btn-primary" onclick="location.href='./inserimento_prodotto.jsp'">Inserisci Prodotto</button>
	</div>
</div>

<div id="Tabella" class="col-xs-6" style="width:980px ; height:400px;overflow:scroll;" >
  
  <table id="lista_prodotti" class="table table-hover  table-striped ">
  
  <thead><tr><th>Titolo</th><th>Autori</th><th>Anno</th><th>Tipologia</th></tr></thead>
  
  
  
  </table>
   
  
   
  </div>
  
   <div class="row  col-xs-offset-3">

  </div>

<%@ include file="footer.html" %>
</body>
</html>