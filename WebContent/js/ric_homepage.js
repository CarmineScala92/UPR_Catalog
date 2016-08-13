
  function PopolaListaProdotti(){
		
	  $.ajax({
			type: "POST",
			url: "./CaricaListaProdottiHomepageServlet",	
			dataType: "xml",
			success: function(msg){							
				// rimuove tutte le righe
				//$(".rigadip").remove();
				
				var row = "";
				$($(msg).find('prodotto')).each(function() {
					var id_prodotto = $(this).find('ID_Prodotto').text();
					var id_proprietario = $(this).find('ID_Proprietario').text();
					var tipologia = $(this).find('Tipologia').text();
                    var titolo = $(this).find('Titolo').text();
					var anno_pubb = $(this).find('Anno_Pubblicazione').text();
					var pubblico = $(this).find('Pubblico').text();
					var stato = $(this).find('Stato').text();
					var note = $(this).find('Note').text();
					
					var ID_Ricercatore=$("#IDRICERCATORE").val();
					
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
					
					
					if(ID_Ricercatore==id_proprietario)
						ruoloProdotto+="Proprietario";
					else
						ruoloProdotto+="Coautore";
						
					var click_v = 'onclick="datiCliente('+id_prodotto+')"';
					if(note!=""){
						if(stato=="draft")
							row += '<tr><td class="tr_mouse" '+click_v+'>'+titolo+'</td><td class="tr_mouse" '+click_v+'>'+ruoloProdotto+'</td><td class="tr_mouse" '+click_v+'>'+tipologia+'</td><td class="tr_mouse" '+click_v+'>'+anno_pubb+'</td><td class="tr_mouse" '+click_v+'><input type="checkbox" '+checkbox+ 'disabled="disabled">'+pubblico_text+'</td><td class="tr_mouse" '+click_v+'><img height="20px" width="20px" src="Loghi/pallino-rifiutato.jpg"> '+stato+'</td><td><button type="button" onmouseover="vediNota('+id_prodotto+');" data-toggle="popover" id="'+id_prodotto+'" data-content="'+note+'">?</button></td></tr>';  	
						else
							row += '<tr><td class="tr_mouse" '+click_v+'>'+titolo+'</td><td class="tr_mouse" '+click_v+'>'+ruoloProdotto+'</td><td class="tr_mouse" '+click_v+'>'+tipologia+'</td><td class="tr_mouse" '+click_v+'>'+anno_pubb+'</td><td class="tr_mouse" '+click_v+'><input type="checkbox" '+checkbox+ 'disabled="disabled">'+pubblico_text+'</td><td class="tr_mouse" '+click_v+'><img height="20px" width="20px" '+pallino+'> '+stato+'</td><td><button type="button" onmouseover="vediNota('+id_prodotto+');" data-toggle="popover" id="'+id_prodotto+'" data-content="'+note+'">?</button></td></tr>';  	
					}
					else{
					 row += '<tr><td class="tr_mouse" '+click_v+'>'+titolo+'</td><td class="tr_mouse" '+click_v+'>'+ruoloProdotto+'</td><td class="tr_mouse" '+click_v+'>'+tipologia+'</td><td class="tr_mouse" '+click_v+'>'+anno_pubb+'</td><td class="tr_mouse" '+click_v+'><input type="checkbox" '+checkbox+' disabled="disabled">'+pubblico_text+'</td><td class="tr_mouse" '+click_v+'><img height="20px" width="20px" '+pallino+'> '+stato+' </td><td></td></tr>';					
					}
				
					
				});
				$(row).appendTo("#lista_products");
				
			},
			async:   false
		});		
	}
  
  
  
  function visualizzaProdotto(id_prodotto)
  {
	  var link="./visualizza_prodotto.jsp?id="+id_prodotto;
	  window.location.href=link;
	  
  }
  
