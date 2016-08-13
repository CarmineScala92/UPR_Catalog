  $(document).ready(function () {
	 ListaUtenti();
	});
  



function RicercaUtenti(){
	
	  
	  
	  document.getElementById('lista_utenti').innerHTML=" <thead><tr><th style=padding-left: 20px;>Nome e Cognome</th><th>Ruolo</th><th>Dipartimento</th><th>Area Scientifica</th></tr></thead>";

	
 $.ajax({
	      
			type:"POST",
			data:"Nome="+$("#Nome").val()+"&Cognome="+$("#Cognome").val(),
            url:"./RicercaAccountServlet",	
			dataType:"xml",
			success: function(msg){							
				row=" ";
				$($(msg).find('Account')).each(function() {
					
					
					var id_account = $(this).find('ID_Account').text();
					
					var Nome = $(this).find('Nome').text();
					var Cognome = $(this).find('Cognome').text();
                    var Dipartimento = $(this).find('Dipartimento').text();
					var AreaScientifica = $(this).find('Area_Scientifica').text();
					var Ruolo = $(this).find('Ruolo').text();
					 
				   row +='<tr class="tr_mouse"><td style="padding-left: 10px;" onclick="visualizzaUtente('+id_account+')">'+ Nome +' '+ Cognome +'</td><td onclick="visualizzaUtente('+id_account+')">'+ Ruolo + '</td><td onclick="visualizzaUtente('+id_account+')">' + Dipartimento + '</td><td onclick="visualizzaUtente('+id_account+')">' +  AreaScientifica+ '</td></tr>';	
				  
				});
			  
				$(row).appendTo("#lista_utenti");
				
			}
		});		
}
 
function ListaUtenti()
{
	
	document.getElementById('lista_utenti').innerHTML=" <thead><tr><th style=padding-left: 20px;>Nome e Cognome</th><th>Ruolo</th><th>Dipartimento</th><th>Area Scientifica</th></tr></thead>";
	  $.ajax({
		      
				type:"POST",
				
	            url:"./RicercaAccountServlet",	
				dataType:"xml",
				success: function(msg){							
					row=" ";
					$($(msg).find('Account')).each(function() {
						
						var id_account = $(this).find('ID_Account').text();
						var Nome = $(this).find('Nome').text();
						var Cognome = $(this).find('Cognome').text();
	                    var Dipartimento = $(this).find('Dipartimento').text();
						var AreaScientifica = $(this).find('Area_Scientifica').text();
						var Ruolo = $(this).find('Ruolo').text();
						 
					   row +='<tr class="tr_mouse"><td onclick="visualizzaUtente('+id_account+')">'+ Nome +' '+ Cognome +'</td><td onclick="visualizzaUtente('+id_account+')">'+ Ruolo + '</td><td onclick="visualizzaUtente('+id_account+')">' + Dipartimento + '</td><td onclick="visualizzaUtente('+id_account+')">' +  AreaScientifica+ '</td></tr>';	
					  
					});
				  
					$(row).appendTo("#lista_utenti");
					
				}
			});		
}

function visualizzaUtente(id_account)
{
	var link="./visualizzaUtente.jsp?id="+id_account;
	window.location.href=link;
}

