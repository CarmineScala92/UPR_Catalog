$(document).ready(function () {
	 ListaProdotti();
});



function RicercaProdotti(){
	
	 document.getElementById('Tabella').innerHTML="<table id='lista_prodotti' class='table table-hover  table-striped '> <thead><tr><th>Titolo</th><th>Autori</th><th>Anno</th><th>Tipologia</th></tr></thead>	  </table>";
	  
$.ajax({
	      
			type:"POST",
			data:"Titolo="+$("#Titolo").val()+"&Tipologia="+$("#Tipologia").val()+"&Anno_Pubblicazione="+$("#Anno").val(),
            url:"./RicercaProdottiServlet",	
			dataType:"xml",
			success: function(msg){							
				row=" ";
				$($(msg).find('Prodotto')).each(function() {
					
					
					var id = $(this).find('ID_Prodotto').text();
					
					var Titolo = $(this).find('Titolo').text();
					var Autori = $(this).find('Autori').text();
                    var Anno = $(this).find('Anno_Pubblicazione').text();
					var Tipologia = $(this).find('Tipologia').text();
					
					 
				   row +=	'<tr><td  onclick="visualizzaProdotto('+id+')">'+ Titolo+ '</td><td  onclick="visualizzaProdotto('+id+')">'+ Autori + '</td><td  onclick="visualizzaProdotto('+id+')">' + Anno + '</td><td  onclick="visualizzaProdotto('+id+')">' + Tipologia + '</td></tr>';	
				  
				});
			  
				$(row).appendTo("#lista_prodotti");
				
			}
		});		
}
 
function ListaProdotti()
{
	document.getElementById('Tabella').innerHTML="<table id='lista_prodotti' class='table table-hover  table-striped '> <thead><tr><th>Titolo</th><th>Autori</th><th>Anno</th><th>Tipologia</th></tr></thead>	  </table>";
	  
	$.ajax({
		      
				type:"POST",
				url:"./VisualizzaProdottiAmministratoreServlet",	
				dataType:"xml",
				success: function(msg){							
					row=" ";
					$($(msg).find('prodotto')).each(function() {
						
						
						var id = $(this).find('ID_Prodotto').text();
						
						var Titolo = $(this).find('Titolo').text();
						var Autori = $(this).find('Autori').text();
	                    var Anno = $(this).find('Anno_Pubblicazione').text();
						var Tipologia = $(this).find('Tipologia').text();
						
						 
						row +=	'<tr class="tr_mouse"><td  onclick="visualizzaProdotto('+id+')">'+ Titolo+ '</td><td  onclick="visualizzaProdotto('+id+')" >'+ Autori + '</td><td  onclick="visualizzaProdotto('+id+')">' + Anno + '</td><td  onclick="visualizzaProdotto('+id+')">' + Tipologia + '</td></tr>';	
					  
					});
				  
					$(row).appendTo("#lista_prodotti");
					
				}
			});		
}
function visualizzaProdotto(id_prodotto)
{
	  var link="./visualizza_prodottoDir.jsp?id="+id_prodotto;
	  window.location.href=link;
	  
}
