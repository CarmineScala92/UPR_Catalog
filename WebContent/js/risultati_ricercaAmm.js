 $(document).ready(function () {
	 PopolaProdottiRicercati('.$_SESSION["Ricercatore"]');
});
 
 
 function PopolaProdottiRicercati(ricercatore){
	
if($("#nascosto").val()==1){
	
 $.ajax({
			type:"GET",
			async: false,
			data:"Titolo="+$("#Titolo").val()+"&Tipologia="+$("#Tipologia").val()+"&Anno_Pubblicazione="+$("#Anno_Pubblicazione").val(),
//			data:"Nome="+$("#Nome").val()+"&Cognome="+$("#Cognome").val()+"&Titolo="+$("#Titolo").val()+"&Tipologia="+$("#Tipologia").val()+"&Anno_Pubblicazione="+$("#Anno_Pubblicazione").val()+"&Nome_Dipartimento="+$("#Nome_Dipartimento").val()+"&Nome_Area_Scientifica="+$("#Nome_Area_Scientifica").val(),
			url:"./RicercaProdottiServlet",	
			dataType:"xml",
			success: function(msg){							
				// rimuove tutte le righe
				//$(".rigadip").remove();
				 			
				var row = "";
				$($(msg).find('Prodotto')).each(function() {
					
					
					var id_prodotto = $(this).find('ID_Prodotto').text();
					var id_proprietario = $(this).find('ID_Proprietario').text();
					var tipologia = $(this).find('Tipologia').text();
                    var titolo = $(this).find('Titolo').text();
					var anno_pubb = $(this).find('Anno_Pubblicazione').text();
					var pubblico = $(this).find('Pubblico').text();
					var stato = $(this).find('Stato').text();
					var note = $(this).find('Note').text();
					var autori= $(this).find('Autori').text();
				
						
						
						var checkbox="";
						var pubblico_text="";

						
						if(pubblico==1){
							checkbox+='checked="checked"';
							pubblico_text="Pubblico";
						}
						else 
							pubblico_text="Privato";
						
								
						var pallino='src="Loghi/pallino-'+stato+'.jpg"';
						
						var ruoloProdotto="";
						
						if(ricercatore.getID_Dipartimento==id_prodotto)
							ruoloProdotto+="Proprietario";
						else
							ruoloProdotto+="Coautore";
							
						
						var click_v = 'onclick="visualizzaProdotto('+id_prodotto+')"';						
						row += '<tr><td class="tr_mouse" '+click_v+'>'+titolo+'</td><td class="tr_mouse" '+click_v+'>'+tipologia+'</td><td class="tr_mouse" '+click_v+'>'+autori+'</td><td class="tr_mouse" '+click_v+'>'+anno_pubb+'</td><td class="tr_mouse" '+click_v+'><input type="checkbox" '+checkbox+' disabled="disabled">'+pubblico_text+'</td><td class="tr_mouse" '+click_v+'><img height="20px" width="20px" '+pallino+'> '+stato+' </td></tr>';					

						
					
					
					
				});
				$(row).appendTo("#lista_products");
			}
		});		
	 
	
	
	
	
}
else
{
	//risultati_ricerca(ricercatore);
}	 
	 
	}
 
 
 
 
 function risultati_ricerca(ricercatore) {
	 
		//document.write($("#Nome").val());
	   $.ajax({
				type:"GET",
				async: false,
				data:"Nome="+$("#Nome").val()+"&Cognome="+$("#Cognome").val()+"&Titolo="+$("#Titolo").val()+"&Tipologia="+$("#Tipologia").val()+"&Anno_Pubblicazione="+$("#Anno_Pubblicazione").val()+"&Nome_Dipartimento="+$("#Nome_Dipartimento").val()+"&Nome_Area_Scientifica="+$("#Nome_Area_Scientifica").val(),
				url:"./RicercaProdottiServlet",	
				dataType:"xml",
				success: function(msg){							
					// rimuove tutte le righe
					//$(".rigadip").remove();
					 			
					var row = "";
					$($(msg).find('Prodotto')).each(function() {
						
						
						var id_prodotto = $(this).find('ID_Prodotto').text();
						var id_proprietario = $(this).find('ID_Proprietario').text();
						var tipologia = $(this).find('Tipologia').text();
	                    var titolo = $(this).find('Titolo').text();
						var anno_pubb = $(this).find('Anno_Pubblicazione').text();
						var pubblico = $(this).find('Pubblico').text();
						var stato = $(this).find('Stato').text();
						var note = $(this).find('Note').text();
						var autori= $(this).find('Autori').text();
					
							
							
							var checkbox="";
							var pubblico_text="";

							
							if(pubblico==1){
								checkbox+='checked="checked"';
								pubblico_text="Pubblico";
							}
							else 
								pubblico_text="Privato";
							
									
							var pallino='src="Loghi/pallino-'+stato+'.jpg"';
							
							var ruoloProdotto="";
							
							if(ricercatore.getID_Dipartimento==id_prodotto)
								ruoloProdotto+="Proprietario";
							else
								ruoloProdotto+="Coautore";
								
							
							var click_v = 'onclick="visualizzaProdotto('+id_prodotto+')"';						
							row += '<tr><td class="tr_mouse" '+click_v+'>'+titolo+'</td><td class="tr_mouse" '+click_v+'>'+tipologia+'</td><td class="tr_mouse" '+click_v+'>'+autori+'</td><td class="tr_mouse" '+click_v+'>'+anno_pubb+'</td><td class="tr_mouse" '+click_v+'><input type="checkbox" '+checkbox+' disabled="disabled">'+pubblico_text+'</td><td class="tr_mouse" '+click_v+'><img height="20px" width="20px" '+pallino+'> '+stato+' </td></tr>';					
							
							
						
						
						
					});
					$(row).appendTo("#lista_products");
				}
			});		
		 
	 
	 
 }
 
 
 function visualizzaProdotto(id_prodotto)
 {
	 var link="./visualizza_prodottoDir.jsp?id="+id_prodotto;
	 window.location.href=link;  
 }