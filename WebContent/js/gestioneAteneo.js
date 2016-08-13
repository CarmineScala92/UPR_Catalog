  $(document).ready(function () {
	 ListaAree();
	 ListaDipartimenti();
});
 
  function ListaAree()
  {
	  
	  document.getElementById('Tabella1').innerHTML="<table id='lista_areeScientifiche' class='table table-hover  table-striped'><thead><tr><th>Codice</th><th>Nome</th><th>Telefono</th></tr></thead></table>";
		
	  $.ajax({
	  	      
	  			type:"POST",
	  			
	              url:"./RicercaAreaScientificaServlet",	
	  			dataType:"xml",
	  			success: function(msg){							
	  				row=" ";
	  				$($(msg).find('Area_Scientifica')).each(function() {
	  					
	  					
	  					var id_area = $(this).find('ID_Area_Scientifica').text();
	  					var codice=$(this).find('Codice').text();
	  					var Nome = $(this).find('Nome').text();
	  					var Telefono = $(this).find('Telefono').text();
	                     
	  					
	  					 
	  				   row +='<tr class="tr_mouse"><td onclick="visualizzaAree('+id_area+')">'+ codice +'</td><td onclick="visualizzaAree('+id_area+')">'+ Nome + '</td><td onclick="visualizzaAree('+id_area+')">' + Telefono + '</td></tr>';	
	  				  
	  				});
	  			  
	  				$(row).appendTo("#lista_areeScientifiche");
	  				
	  			}
	  		});		
	  
	  
  }
  
  function ListaDipartimenti()
  {
	  
	  document.getElementById('Tabella').innerHTML="<table id='lista_dipartimenti' class='table table-hover  table-striped'><thead><tr><th>Nome</th><th>Direttore</th><th>Telefono</th></tr></thead></table>";
		
	  $.ajax({
	  	      
	  			type:"POST",
	              url:"./RicercaDipartimentoServlet",	
	  			dataType:"xml",
	  			success: function(msg){							
	  				row=" ";
	  				$($(msg).find('Dipartimento')).each(function() {
	  					
	  					
	  					var id_dipartimento = $(this).find('ID_Dipartimento').text();
	  					
	  					var Nome = $(this).find('Nome').text();
	  					var Telefono = $(this).find('Telefono').text();
	                     var NomeD = $(this).find('Nome_Direttore').text();
	                    var CognomeD= $(this).find('Cognome_Direttore').text();
	  					 
	                      row +='<tr class="tr_mouse"><td onclick="visualizzaDipartimento('+id_dipartimento+')">'+ Nome +'</td><td onclick="visualizzaDipartimento('+id_dipartimento+')">'+ NomeD + ' '+ CognomeD + '</td><td onclick="visualizzaDipartimento('+id_dipartimento+')">' + Telefono + '</td></tr>';
	  				  
	  				});
	  			  
	  				$(row).appendTo("#lista_dipartimenti");
	  				
	  			}
	  		});	
	  
	  
	  
  }
  
  
  
 function RicercaAreaScientifica(){
	
	 document.getElementById('Tabella1').innerHTML="<table id='lista_areeScientifiche' class='table table-hover  table-striped'><thead><tr><th>Codice</th><th>Nome</th><th>Telefono</th></tr></thead></table>";
	
$.ajax({
	      
			type:"POST",
			data:"Nome="+$("#AreaScientifica").val(),
            url:"./RicercaAreaScientificaServlet",	
			dataType:"xml",
			success: function(msg){							
				row=" ";
				$($(msg).find('Area_Scientifica')).each(function() {
					
					
					var id_area = $(this).find('ID_Area_Scientifica').text();
					var codice=$(this).find('Codice').text();
					var Nome = $(this).find('Nome').text();
					var Telefono = $(this).find('Telefono').text();
                   
					
					 
				   row +='<tr><td onclick="visualizzaAree('+id_area+')">'+ codice +'</td><td onclick="visualizzaAree('+id_area+')">'+ Nome + '</td><td onclick="visualizzaAree('+id_area+')">' + Telefono + '</td></tr>';	
				  
				});
			  
				$(row).appendTo("#lista_areeScientifiche");
				
			}
		});		
}




function RicercaDipartimento(){
	 
	 document.getElementById('Tabella').innerHTML="<table id='lista_dipartimenti' class='table table-hover  table-striped'><thead><tr><th>Nome</th><th>Direttore</th><th>Telefono</th></tr></thead></table>";
	
$.ajax({
	      
			type:"POST",
			data:"Nome="+$("#Dipartimento").val(),
            url:"./RicercaDipartimentoServlet",	
			dataType:"xml",
			success: function(msg){							
				row=" ";
				$($(msg).find('Dipartimento')).each(function() {
					
					
					var id_dipartimento = $(this).find('ID_Dipartimento').text();
					
					var Nome = $(this).find('Nome').text();
					var Telefono = $(this).find('Telefono').text();
                    var Sito= $(this).find('Sito').text();
                    var NomeD = $(this).find('Nome_Direttore').text();
                    var CognomeD= $(this).find('Cognome_Direttore').text();
					 
				   row +='<tr><td onclick="visualizzaDipartimento('+id_dipartimento+')">'+ Nome+'</td><td onclick="visualizzaDipartimento('+id_dipartimento+')">'+ NomeD+' '+CognomeD + '</td><td onclick="visualizzaDipartimento('+id_dipartimento+')">' + Telefono + '</td></tr>';	
				  
				});
			  
				$(row).appendTo("#lista_dipartimenti");
				
			}
		});		
}
 
 
 function visualizzaDipartimento(id_dipartimento)
 {
	  var link="./visualizza_dipartimento.jsp?id="+id_dipartimento;
	  window.location.href=link;
 }
 
 function visualizzaAree(id_Area)
 {
	 var link="./visualizzaAreaScientifica.jsp?id="+id_Area;
	  window.location.href=link;
	 
 }
