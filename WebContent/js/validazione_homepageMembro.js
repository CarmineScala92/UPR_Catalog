 $(document).ready(function () { 
	 PopolaListaProdottiCompleti();
	  
	  
  });
 function PopolaListaProdottiCompleti(){
	 
		$.ajax({
			type: "POST",
			url: "./CaricaListaProdottiAccettatiServlet",	
			dataType: "xml",
			success: function(msg){							
				// rimuove tutte le righe
				//$(".rigadip").remove();
				
				var row = "";
				$($(msg).find('prodotto')).each(function() {
					var id_prodotto = $(this).find('ID_Prodotto').text();
					
					var tipologia = $(this).find('Tipologia').text();
                    var titolo = $(this).find('Titolo').text();
					var autori= $(this).find('Autori').text();
					
					
				
						
					
					row +=	'<tr class="tr_mouse"><td><input type=checkbox value='+ id_prodotto +' onclick="controlloBottone();" name="ID_Prodotto"></td><td onclick="visualizzaProdotto('+id_prodotto+')">'+ titolo +'</td><td onclick="visualizzaProdotto('+id_prodotto+')">' + tipologia + '</td><td onclick="visualizzaProdotto('+id_prodotto+')">' + autori + '</td></tr>';										
					
				});
				$(row).appendTo("#tabella_prodotti");
			}
		});		
	}
 
 function visualizzaProdotto(id_prodotto)
 {
	  var link="./visualizza_prodottoDir.jsp?id="+id_prodotto;
	  window.location.href=link;
	  
 }
 

function controlloBottone(){
	 
	 numero_righe=document.getElementsByTagName("tr").length-1;
	 
	 var cont1=0;
	 for(var i=0;i<numero_righe*4;i++){
		 	 
		 
		 if(i%4==0){
		 if(document.getElementsByTagName("td").item(i).firstChild.checked){
			cont1++;
		 }
		}
		
	 }
	 
	 if(cont1>=1){
		 $("#primo_bottone").prop('disabled','');
		 //document.getElementById("primo_bottone").removeAttribute("disabled");
	 }else{
		 //document.getElementById("primo_bottone").setAttribute("disabled");
		 $("#primo_bottone").prop('disabled','true');
	 }
	 if(cont1==1){
		 $("#rifiuta_bottone").prop('disabled', '');
		 $("#text_area").prop('disabled', '');
		 //document.getElementById("rifiuta_bottone").removeAttribute("disabled");
		 //document.getElementById("text_area").removeAttribute("disabled");
	 }
	 if(cont1>=2 || cont1==0){
		 //document.getElementById("rifiuta_bottone").setAttribute("disabled");
		 //document.getElementById("text_area").setAttribute("disabled");
		 $("#rifiuta_bottone").prop('disabled', 'true');
		 $("#text_area").prop('disabled', 'true');
		 document.getElementById("text_area").value=" ";
	 } 
	}
 
   function cambiaRifiuta(){
	
	   if(document.getElementById("text_area").value=="" && !document.getElementById("text_area").hasAttribute("disabled")){
			document.getElementById("h4").innerHTML="Errore";
	 		document.getElementById("p").innerHTML="Non è stato compilato il campo note con la motivazione del rifiuto";
	 		document.getElementById("bottone_conferma").innerHTML="OK";
	 		document.getElementById("bottone_conferma").className="btn btn-danger";
	 		document.getElementById("bottone_conferma").setAttribute("type", "button");
	 		document.getElementById("bottone_conferma").setAttribute("data-dismiss", "modal");
	 		
		}else{
	   
	   
	   document.getElementById("h4").innerHTML="Rifiuta";
		document.getElementById("p").innerHTML="Sei sicuro di voler rifiutare il prodotto?";
		document.getElementById("bottone_conferma").innerHTML="Rifiuta";
		document.getElementById("bottone_conferma").className="btn btn-danger";
		document.getElementById("bottone_conferma").removeAttribute("data-dismiss", "modal");
		document.getElementById("bottone_conferma").setAttribute("type", "submit");
		document.getElementById("form").setAttribute("action", "./RifiutaProdottiServlet?Stato=rifiutato");
		document.getElementById("text_area").setAttribute("name", "Note");
		//document.getElementById("form").submit();		
		}
	}
 
   
   function cambiaValida(){
		
	   if(document.getElementById("text_area").value=="" && !document.getElementById("text_area").hasAttribute("disabled")){
			document.getElementById("h4").innerHTML="Errore";
	 		document.getElementById("p").innerHTML="Non è stato compilato il campo note con la motivazione della validazione";
	 		document.getElementById("bottone_conferma").innerHTML="OK";
	 		document.getElementById("bottone_conferma").className="btn btn-danger";
	 		document.getElementById("bottone_conferma").setAttribute("type", "button");
	 		document.getElementById("bottone_conferma").setAttribute("data-dismiss", "modal");
	 		
		}else{
	   
	   
	   document.getElementById("h4").innerHTML="Validazione";
		document.getElementById("p").innerHTML="Sei sicuro di validare il/i prodotti?";
		document.getElementById("bottone_conferma").innerHTML="Valida";
		document.getElementById("bottone_conferma").className="btn btn-success";
		document.getElementById("bottone_conferma").removeAttribute("data-dismiss", "modal");
		document.getElementById("bottone_conferma").setAttribute("type", "submit");
		document.getElementById("form").setAttribute("action", "./ValidaProdottiServlet");
		document.getElementById("text_area").setAttribute("name", "Note");
		//document.getElementById("form").submit();
		}
	}

 
 
 