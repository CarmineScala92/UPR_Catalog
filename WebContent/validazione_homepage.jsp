	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.sun.java.swing.plaf.windows.resources.windows"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="assets/stileFooter.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/navbar.css" rel="stylesheet">
<link href="css/stile.css" rel="stylesheet">
 <script src="js/jquery.js"></script> 
<script src="js/bootstrap.js"></script> 
<script src="js/index.js"></script> 

 
<title>Validazione home page</title>
<style type="text/css">
tr{text-align: center;}
th{text-align: center;}

</style>

</head>
<body>

<%@ page import="it.unisa.upr.data.gestioneAutenticazione.Ricercatore"%>
  
	
	<% Ricercatore ric = (Ricercatore) session.getAttribute("Ricercatore");
	int id=0;
	if (ric == null) {
		String url=request.getContextPath()+"/index.jsp";
		response.sendRedirect(url);
	}
		
	%>
 
 <%if(ric!=null && ric.getRuolo().equalsIgnoreCase("Direttore")){ %>
 <script src="js/validazione_homepage.js"></script> 
<%}else if(ric!=null && ric.getRuolo().equalsIgnoreCase("Membro")){ %>
<script src="js/validazione_homepageMembro.js"></script> 
<%}%>


<div class="row navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      <img class="navbar-brand" src="Loghi/logo.png" height="50px" width="150px" style="padding: 5px"> 
      </div>
    <div class="navbar-collapse collapse">
      <ul class="nav navbar-nav nav-pills">
          <li><a href="./ric_homepage.jsp" ><span class=" glyphicon glyphicon-home"></span>Home</a></li>
        <li><a href="./ricerca_prodotti.jsp" ><span class="glyphicon glyphicon-search"></span>Ricerca</a></li>
      <li><a href="./inserimento_prodotto.jsp"><span class="glyphicon glyphicon-plus"></span>Inserimento</a></li>
      <li class="active"><a href="#"><span class="glyphicon glyphicon-ok"></span>Validazione</a></li>
      </ul>
      

      <div class="navbar-right row" style="margin-top:10px; margin-right:20px">
        <form class="form-inline" role="form" action="./LogoutServlet" method="post">
       <label style="color: blue;">Benvenuto <% if(ric!=null) out.print(ric.getNome()); %> sei loggato come  <%if(ric!=null) out.print(ric.getRuolo()); %> </label>
          <button type="submit" class="btn btn-primary input-sm""><span class="glyphicon glyphicon-user"></span>Logout</button>
        </form>
      </div>
     
     
    </div>
    <!--/.nav-collapse --> 
  </div>
  
  <div class="row page-header   col-xs-offset-0 " style="margin-top:35px; text-align:center">
  <%if(ric!=null&&ric.getRuolo().equalsIgnoreCase("Direttore")){ %>
  <h1 id="nomeDip"><span class="glyphicon glyphicon-ok"></span>Accettazione</h1>
  <%}else if(ric!=null&&ric.getRuolo().equalsIgnoreCase("Membro")){ %>
  <h1 id="nomeDip"><span class="glyphicon glyphicon-ok"></span>Validazione</h1>
  <%} %>
  </div> <!-- div gestione utenti -->



 <div class="row col-xs-6 col-xs-offset-3 alert alert-danger fade in" hidden id="non_visibile">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">x</button>
        <div id="parole" class="alert-msg"></div>
    </div>
  
  
  
 <form class="row" action="" id="form">
  <div style="margin-top: 51px" id="tabella">
  <div class="col-xs-6" style="display: inline-block;margin-top: 51px;overflow: scroll;">
  <table class="table table-hover" id="tabella_prodotti">
  <thead><th>Seleziona</th><th>Titolo</th><th>Tipologia</th><th>Autori</th></thead>
  
  
  </table>
  
  </div>
  
  <div class="row col-xs-4 col-xs-offset-2" style="display: inline-block;margin-top: 100px">
  <div class="" style="margin-top: ">
  <%if(ric!=null&&ric.getRuolo().equalsIgnoreCase("Direttore")){ %>
  
  <button id="primo_bottone" class="btn btn-success btn-lg" type="button" data-toggle="modal" data-target="#myModal" onclick="cambiaAccetta();" disabled>Accetta</button><br>
  <input value=accettato name="Stato" hidden>
  
  <%}else if(ric!=null&&ric.getRuolo().equalsIgnoreCase("Membro")){ %>
  
  <button class="btn btn-success btn-lg" id="primo_bottone" type="button" data-toggle="modal" data-target="#myModal" onclick="cambiaValida();" disabled>Valida</button><br>
  <input value=validato name="Stato" hidden>
  <%} %>
  </div>
  <div class="" style="margin-top: 51px">
  <button class="btn btn-danger btn-lg" type="button" onclick="cambiaRifiuta();" id="rifiuta_bottone" data-toggle="modal" data-target="#myModal" disabled>Rifiuta</button><br>
  </div>
  <div class="" style="margin-top: 51px">
  <textarea rows="10" cols="30" id="text_area" disabled></textarea>
  </div>
  </div>
 </div>
 
 
 
 <div class=" row modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="vertical-align:middle">
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
<!--         <form action="./EliminaAreaScientificaServlet" method="post"> -->
<%-- 			<input id="idArea" name="ID_Area_Scientifica" value="<% out.print(request.getParameter("id")); %>" hidden> --%>
			<button id="bottone_conferma" type="submit" class="btn btn-success">Conferma modifica</button>
<!--      	 </form> -->
     	 </div>
     	 </div>
    	</div><!-- /.modal-content -->
  	  </div><!-- /.modal-dialog -->
   </div><!-- /.modal -->
 
 
 </form>
 
 
 <script src="js/jquery.js"></script> 
	<script src="js/bootstrap.js"></script> 
	<script type="text/javascript">
			
			
		function lanciaNotifica(strong, frase){
			$(".alert").show();
			$(".alert-msg").html('<strong>'+strong+'</strong>'+frase);
			window.setTimeout(function(){ $(".alert").alert('close'); }, 3000);
		}
				  			  
            </script>
 
 
 <%@ include file="footer.html" %>
 
<!--  <script type="text/javascript"> -->
//  document.getElementById("non_visibile").setAttribute("hidden");
 
<!--  </script> -->
 
<%
  if(request.getAttribute("response") != null) {	  
	  
 	 request.removeAttribute("response");  
  %>  
 <script type="text/javascript">
 
		document.getElementById("non_visibile").className="col-xs-6 col-xs-offset-3 alert alert-success fade in"; 
 		lanciaNotifica("Ottimo!"," L'operazione è avvenuta con successo"); 
 
  </script> 
 <%}else if(request.getAttribute("err")!=null){ %>
 <script type="text/javascript">
	  document.getElementById("non_visibile").className="col-xs-6 col-xs-offset-3 alert alert-danger fade in"; 
 		lanciaNotifica("Errore!"," L'operazione è fallita");
 		</script>
<%} %>
</body>
</html>