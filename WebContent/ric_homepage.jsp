<!DOCTYPE html>
<%@page import="com.sun.java.swing.plaf.windows.resources.windows"%>
<!--[if IE 7]> <html lang="en" class="ie7"> <![endif]-->  
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->  
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->  
<!--[if !IE]><!--> <!--<![endif]-->  
<html lang="it">
<head>

	<title>UPR-Catalog :: University Products Research Catalogue</title>
    <!-- Meta -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta http-equiv="content-type" content="text/html" charset="utf-8" />
    <link rel="shortcut icon" href="favicon.ico" />      
    
		<link href="css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Custom styles for this template -->
   	<link href="css/Unify.css" rel="stylesheet">
		<link href="css/stile.css" rel="stylesheet">

		<script src="js/jquery.js"></script> 
		<script src="js/bootstrap.js"></script> 
		<script src="js/index.js"></script> 
		<script type="text/javascript" src="js/ric_homepage.js"></script> 


	<!-- Demo stuff -->
	<link href="table/prettify.css" rel="stylesheet">
	<script src="table/prettify.js"></script>
	<script src="table/docs.js"></script>

	<!-- Tablesorter: required for bootstrap -->
	<link rel="stylesheet" href="table/css/theme.bootstrap.css">
	<script src="table/js/jquery.tablesorter.js"></script>
	<script src="table/js/jquery.tablesorter.widgets.js"></script>

	<!-- Tablesorter: optional -->
	<link rel="stylesheet" href="table/addons/pager/jquery.tablesorter.pager.css">
	<script src="table/addons/pager/jquery.tablesorter.pager.js"></script>
		
		
	<script id="js">$(function() {

	$.extend($.tablesorter.themes.bootstrap, {
		// these classes are added to the table. To see other table classes available,
		// look here: http://twitter.github.com/bootstrap/base-css.html#tables
		table      : 'table table-bordered',
		caption    : 'caption',
		header     : 'bootstrap-header', // give the header a gradient background
		footerRow  : '',
		footerCells: '',
		icons      : '', // add "icon-white" to make them white; this icon class is added to the <i> in the header
		sortNone   : 'bootstrap-icon-unsorted',
		sortAsc    : 'icon-chevron-up glyphicon glyphicon-chevron-up',     // includes classes for Bootstrap v2 & v3
		sortDesc   : 'icon-chevron-down glyphicon glyphicon-chevron-down', // includes classes for Bootstrap v2 & v3
		active     : '', // applied when column is sorted
		hover      : '', // use custom css here - bootstrap class may not override it
		filterRow  : '', // filter row class
		even       : '', // odd row zebra striping
		odd        : ''  // even row zebra striping
	});

	// call the tablesorter plugin and apply the uitheme widget
	$("table").tablesorter({
		// this will apply the bootstrap theme if "uitheme" widget is included
		// the widgetOptions.uitheme is no longer required to be set
		theme : "bootstrap",

		widthFixed: true,

		headerTemplate : '{content} {icon}', // new in v2.7. Needed to add the bootstrap icon!

		// widget code contained in the jquery.tablesorter.widgets.js file
		// use the zebra stripe widget if you plan on hiding any rows (filter widget)
		widgets : [ "uitheme", "filter", "zebra" ],

		widgetOptions : {
			// using the default zebra striping class name, so it actually isn't included in the theme variable above
			// this is ONLY needed for bootstrap theming if you are using the filter widget, because rows are hidden
			zebra : ["even", "odd"],

			// reset filters button
			filter_reset : ".reset"

			// set the uitheme widget to use the bootstrap theme class names
			// this is no longer required, if theme is set
			// ,uitheme : "bootstrap"

		}
	})
	.tablesorterPager({

		// target the pager markup - see the HTML block below
		container: $(".ts-pager"),

		// target the pager page select dropdown - choose a page
		cssGoto  : ".pagenum",

		// remove rows from the table to speed up the sort of large tables.
		// setting this to false, only hides the non-visible rows; needed if you plan to add/remove rows with the pager enabled.
		removeRows: false,

		// output string - default is '{page}/{totalPages}';
		// possible variables: {page}, {totalPages}, {filteredPages}, {startRow}, {endRow}, {filteredRows} and {totalRows}
		output: '{startRow} - {endRow} / {filteredRows} ({totalRows})'

	});

});</script>

	<script>
	$(function(){

		// filter button demo code
		$('button.filter').click(function(){
			var col = $(this).data('column'),
				txt = $(this).data('filter');
			$('table').find('.tablesorter-filter').val('').eq(col).val(txt);
			$('table').trigger('search', false);
			return false;
		});

		// toggle zebra widget
		$('button.zebra').click(function(){
			var t = $(this).hasClass('btn-success');
//			if (t) {
				// removing classes applied by the zebra widget
				// you shouldn't ever need to use this code, it is only for this demo
//				$('table').find('tr').removeClass('odd even');
//			}
			$('table')
				.toggleClass('table-striped')[0]
				.config.widgets = (t) ? ["uitheme", "filter"] : ["uitheme", "filter", "zebra"];
			$(this)
				.toggleClass('btn-danger btn-success')
				.find('i')
				.toggleClass('icon-ok icon-remove glyphicon-ok glyphicon-remove').end()
				.find('span')
				.text(t ? 'disabled' : 'enabled');
			$('table').trigger('refreshWidgets', [false]);
			return false;
		});
	});
	</script>
	
	
	
	
	
	
	
	
	
		
		
		
		
		
		
 
<!-- Just for debugging purposes. Don't actually copy this line! -->
<!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  
    <meta charset="UTF-8">
    <style type="text/css">
    th{text-align: center;}
    td{text-align: center;}
    
    </style>
    
</head>

<body>

	<%@ page import="it.unisa.upr.data.gestioneAutenticazione.Ricercatore"%>
  
	<%
	Ricercatore ric = (Ricercatore) session.getAttribute("Ricercatore");
	int id=0;
	if (ric == null) {
		response.sendRedirect("index.jsp");
	}
	else{
		id=ric.getID_Account();
		if(session.getAttribute("success")!=null)
		{
			session.removeAttribute("success");
			
		%>
		
	     <script  type="text/javascript">

	         $(document).ready(function () {

	        	 $("#notificaLogin").show();

	     		$(".alert-msg").html('<strong>Accesso effettuato con successo</strong>.');

	     		window.setTimeout(function(){ $(".alert").alert('close'); }, 5000);
	         });
	        </script>
		<%
		}
		
		%>
	


  
  <!-- Static navbar -->
  <div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      <img class="navbar-brand" src="Loghi/logo.png" height="50px" width="150px" style="padding: 5px"> 
      </div>
    <div class="navbar-collapse collapse">
      <ul class="nav navbar-nav nav-pills">
          <li class="active"><a href="./ric_homepage.jsp" ><span class=" glyphicon glyphicon-home"></span>Home</a></li>
        <li><a href="./ricerca_prodotti.jsp" ><span class="glyphicon glyphicon-search"></span>Ricerca</a></li>
      <li><a href="./inserimento_prodotto.jsp"><span class="glyphicon glyphicon-plus"></span>Inserimento</a></li>
      <%if(ric.getRuolo().equalsIgnoreCase("Direttore") ||ric.getRuolo().equalsIgnoreCase("Membro")){ %>
       <li><a href="./validazione_homepage.jsp"><span class="glyphicon glyphicon-ok"></span>Validazione</a></li>
      <%}%>
      </ul>
      

      <div class="navbar-right row" style="margin-top:10px; margin-right:10px">
        <form class="form-inline" role="form" action="./LogoutServlet" method="post">
       <label style="color: blue;">Benvenuto <% out.print(ric.getNome()); %> sei loggato come  <% out.print(ric.getRuolo()); %> </label>   
          <button type="submit" class="btn btn-primary input-sm"><span class="glyphicon glyphicon-user"></span>Logout</button>
        </form>
      </div>
     
     
    </div>
    <!--/.nav-collapse --> 
  </div>
  
  
  <div id="notificaLogin" class="col-xs-6 col-xs-offset-3 alert alert-success fade in" hidden>

      <button type="button" class="close" data-dismiss="alert" aria-hidden="true">x</button>

      <div class="alert-msg"></div>

       </div>
  
<input id="IDRICERCATORE" value="<%out.print(id); %>" type="hidden">
  
<div class="row" style="margin-top:80px;">
<div class="col-xs-offset-1 col-xs-8">
 <h3 class="text-center success"> Lista prodotti<br>
 	<small> Nella tabella sottostante sono elencati tutti i prodotti da te inseriti all'interno catalogo. Inoltre sono mostrati anche i prodotti sui quali sei stato riconosciuto come coautore.</small></h3>
</div>
  <div class="col-xs-2">
	<p style="font-size: 9px"><img height="10px" width="10px" src="Loghi/pallino-draft.jpg">PRODOTTO IN STATO DRAFT</p> 
	<p style="font-size: 9px"><img height="10px" width="10px" src="Loghi/pallino-rifiutato.jpg">PRODOTTO IN STATO RIFIUTATO</p>
 <p style="font-size: 9px"><img height="10px" width="10px" src="Loghi/pallino-completo.jpg">PRODOTTO IN STATO COMPLETO</p> 
 <p style="font-size: 9px"><img height="10px" width="10px" src="Loghi/pallino-accettato.jpg">PRODOTTO IN STATO ACCETTATO</p>
 <p style="font-size: 9px"><img height="10px" width="10px" src="Loghi/pallino-validato.jpg">PRODOTTO IN STATO VALIDATO</p>
 <p style="font-size: 9px"><img height="10px" width="10px" src="Loghi/pallino-inviato.jpg">PRODOTTO IN STATO INVIATO</p></div>
</div>








<div class="row col-xs-offset-1 col-xs-10 ">
	<div class="bootstrap_buttons">
		<button type="button" class="reset btn btn-primary" data-column="0" data-filter=""><i class="icon-white icon-refresh glyphicon glyphicon-refresh"></i> Reset filters</button>
		<br><br>
		<button type="button" class="filter btn btn-small btn-default" data-column="3" data-filter=">2010"><i class="icon-white icon-filter glyphicon glyphicon-filter"></i> Anno pubblicazione: ">2010"</button> 
		<button type="button" class="filter btn btn-small btn-default" data-column="1" data-filter="Proprietario"><i class="icon-white icon-filter glyphicon glyphicon-filter"></i> Proprietà: "Proprietario"</button>	
		<button type="button" class="filter btn btn-small btn-default" data-column="4" data-filter="Pubblico"><i class="icon-white icon-filter glyphicon glyphicon-filter"></i> Visibilità: "Pubblico"</button>	
	
		</div>
	<br>

	<div id="demo"><table class="tablesorter">

	<thead>
		<tr>
			<th>Titolo</th>
			<th class="filter-select filter-exact" data-placeholder="Seleziona la proprietà">Proprietà</th>
			<th class="filter-select filter-exact" data-placeholder="Seleziona una tipologia">Tipologia</th>
			<th>Anno pubblicazione</th>
			<th class="filter-select filter-exact" data-placeholder="Seleziona visibilità">Visibilità</th>
			<th class="filter-select filter-exact" data-placeholder="Seleziona uno stato">Stato</th>
			<th>Note</th></tr>
	</thead>
	<tfoot>
		<tr>
			<th>Titolo</th>
			<th>Proprietà</th>
			<th>Tipologia</th>
			<th>Anno pubblicazione</th>
			<th>Visibilità</th>
			<th>Stato</th>
			<th>Note</th></tr>
		</tr>
		<tr>
			<th colspan="7" class="ts-pager form-horizontal">
				<button type="button" class="btn first"><i class="icon-step-backward glyphicon glyphicon-step-backward"></i></button>
				<button type="button" class="btn prev"><i class="icon-arrow-left glyphicon glyphicon-backward"></i></button>
				<span class="pagedisplay"></span> <!-- this can be any element, including an input -->
				<button type="button" class="btn next"><i class="icon-arrow-right glyphicon glyphicon-forward"></i></button>
				<button type="button" class="btn last"><i class="icon-step-forward glyphicon glyphicon-step-forward"></i></button>
				<select class="pagesize input-mini" title="Select page size">
					<option selected="selected" value="10">10</option>
					<option value="20">20</option>
					<option value="30">30</option>
					<option value="40">40</option>
				</select>
				<select class="pagenum input-mini" title="Select page number"></select>
			</th>
		</tr>
	</tfoot>
	<tbody id="lista_products">
		<script>PopolaListaProdotti()</script>
	</tbody>
</table></div>


</div>



















 <!-- serve per non fare sovrapporre il footer al contenuto superiore -->
 <div class="row"> </div>
  

 <script>

 function vediNota(id_prodotto){
	 
	     $('#'+id_prodotto).popover({		              
	       'selector': '',
			'trigger': 'click',
	         'placement': 'bottom',
	         'container':"body"
	     
			

			
	   });
 }
 	  
		</script>	  
			  
              
  

<% } %>

<!--=== Footer ===-->
<%@ include file="footer.html" %>
<!--=== End Footer ===-->

</body>
</html>
