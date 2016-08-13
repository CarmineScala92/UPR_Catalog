$(document).ready(function () {
	  
	optionsAreeScientifiche();
	optionsDipartimenti();
	tooltip();
});

function optionsDipartimenti(){
	
	$.ajax({
		type: "POST",
		url: "./CaricaListaDipartimentiServlet",	
		dataType: "xml",
		success: function(msg){
						
			// rimuove tutte le righe
			//$(".rigadip").remove();
			var row = "";
			$($(msg).find('dipartimento')).each(function() {
				var nome = $(this).find('nome').text();
				var id = $(this).find('id').text();
			
				row +=	'<option  value="'+id+'">'+ nome +'</option>';	
			});
			$(row).appendTo("#Dipartimento");
		}
	});		
}
function optionsAreeScientifiche(){

	//alert("xml test|");
	
	$.ajax({
		type: "POST",
		url: "./CaricaListaAreeScientificheServlet",
		dataType: "xml",
		success: function(msg){
						
			// rimuove tutte le righe
			//$(".rigaarsc").remove();
			var row = "";
			$($(msg).find('areascientifica')).each(function() {
				var nome = $(this).find('nome').text();
				var id = $(this).find('id').text();
				
				
				
			    row +=	'<option value="'+id+'" >'+ nome +'</option>';
			});
			$(row).appendTo("#Area_Scientifica");
		}
	});		
}

function controllaNome(valore){
	
	var pattern=/^[a-zA-Zàèìòùé' ]*$/;
	
	
	if((pattern.test(valore) && valore.length>=2 &&  valore.length<=50) || valore.length==0)
		{
		
	   	document.formVisualizza.Nome.className="form-control"; 
	     document.getElementById('divNome').className="form-group";  
		
		}
	else
		{
		
		 document.formVisualizza.Nome.className="form-control has-error"; 
	     document.getElementById('divNome').className="form-group has-error";
		
		}
}

function controllaCognome(valore){
	
	var pattern=/^[a-zA-Zàèìòùé' ]*$/;
	
	
	if((pattern.test(valore)  && valore.length>=2 &&  valore.length<=50) || valore.length==0)
		{
		
	   	document.formVisualizza.Cognome.className="form-control"; 
	     document.getElementById('divCognome').className="form-group";  
		
		}
	else
		{
		
		 document.formVisualizza.Cognome.className="form-control has-error"; 
	     document.getElementById('divCognome').className="form-group has-error";
		
		}
}

function controllaUsername(valore){
	
	var pattern=/^[a-zA-Z]/;
	
	
	if((pattern.test(valore)  && valore.length>=6 &&  valore.length<=50) || valore.length==0)
		{
		
	   	document.formVisualizza.Username.className="form-control"; 
	     document.getElementById('divUsername').className="form-group";  
		
		}
	else
		{
		
		 document.formVisualizza.Username.className="form-control has-error"; 
	     document.getElementById('divUsername').className="form-group has-error";
		
		}
}


function controllaPassword(valore){
	

	
	if((valore.length>=7 &&  valore.length<=20) || valore.length==0)
		{
		
	   	document.formVisualizza.Password.className="form-control"; 
	     document.getElementById('divPassword').className="form-group";  
		
		}
	else
		{
		
		 document.formVisualizza.Password.className="form-control has-error"; 
	     document.getElementById('divPassword').className="form-group has-error";
		
		}
}

function controllaEmail(valore){
	
	 var pattern=/^[a-zA-Z]{1}[a-zA-Z0-9._]{2,}[@][a-zA-Z]{2,}[.][a-zA-Z]{1,5} *$/;
	  if(pattern.test(valore) && valore.length>=6 && valore.length<=50 || valore.length==0)
   {
   	
  	 document.formVisualizza.Email.className="form-control"; 
    document.getElementById('divEmail').className="form-group";  
	
	}
else
	{
	
	 document.formVisualizza.Email.className="form-control has-error"; 
    document.getElementById('divEmail').className="form-group has-error";
	}
}

function controllaDataNascita(valore){

	
	var pattern=/^(19|20)\d\d[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])/;

	 
	
	if((pattern.test(valore)) && valore.length==10  && valore.slice(0,4)>=1950 && valore.slice(0,4)<=1995 || valore.length==0)
	{
	
   	document.formVisualizza.Data_Nascita.className="form-control"; 
     document.getElementById('divDataNascita').className="form-group";  
	
	}
else
	{
	
	 document.formVisualizza.Data_Nascita.className="form-control has-error"; 
     document.getElementById('divDataNascita').className="form-group has-error";
	
	}
	
	
}


function controllaInizio(valore){

	
	var pattern=/^(19|20)\d\d[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])/;

	 
	
	if((pattern.test(valore)) && valore.length==10 && valore.slice(0,4)>=1950 && valore.slice(0,4)<=2014|| valore.length==0)
	{
	
   	document.formVisualizza.Data_Inizio_Servizio.className="form-control"; 
     document.getElementById('divDataInizio').className="form-group";  
	
	}
 else
	{
	
	 document.formVisualizza.Data_Inizio_Servizio.className="form-control has-error"; 
     document.getElementById('divDataInizio').className="form-group has-error";
	
	}
	
	
}



function controllaCittaNascita(valore){
	
	var pattern=/^[a-zA-Z' ]*$/;
	
	if((pattern.test(valore) && valore.length>=4 &&  valore.length<=50) || valore.length==0)
		{
		
	   	document.formVisualizza.Citta_Nascita.className="form-control"; 
	     document.getElementById('divCittaNascita').className="form-group";  
		
		}
	else
		{
		
		 document.formVisualizza.Citta_Nascita.className="form-control has-error"; 
	     document.getElementById('divCittaNascita').className="form-group has-error";
		
		}
}

function controllaProvincia(valore){
	
	var pattern=/^[a-zA-Z']*$/;
	
	if((pattern.test(valore) && valore.length==2 ) || valore.length==0)
		{
		
	   	document.formVisualizza.Provincia_Nascita.className="form-control"; 
	     document.getElementById('divProvincia').className="form-group";  
		
		}
	else
		{
		
		 document.formVisualizza.Provincia_Nascita.className="form-control has-error"; 
	     document.getElementById('divProvincia').className="form-group has-error";
		}
}

function controllaSesso(valore){
	
	
	
	if((valore=='M' || valore=='F')  || valore.length==0)
		{
		
	   	document.formVisualizza.Sesso.className="form-control"; 
	     document.getElementById('divSesso').className="form-group";  
		
		}
	else
		{
		
		 document.formVisualizza.Sesso.className="form-control has-error"; 
	     document.getElementById('divSesso').className="form-group has-error";
		}
}

function controllaCodiceFiscale(valore){
	 
	 var pattern=/^[A-Z]{6}[0-9]{2}[A-Z]{1}[0-9]{2}[A-Z]{1}[0-9]{3}[A-Z]{1}$/;
	
	if(pattern.test(valore)|| valore.length==0)
		{
		
	   	document.formVisualizza.Codice_Fiscale.className="form-control"; 
	     document.getElementById('divCodiceFiscale').className="form-group";  
		
		}
	else
		{
		 document.formVisualizza.Codice_Fiscale.className="form-control has-error"; 
	     document.getElementById('divCodiceFiscale').className="form-group has-error";
		}
}


function controllaMatricola(valore){
	
	 var pattern=/^[0-9]*$/;
	 
	if(pattern.test(valore) && valore.length==10|| valore.length==0)
		{
		
	   	document.formVisualizza.Matricola.className="form-control"; 
	     document.getElementById('divMatricola').className="form-group";  
		
		}
	else
		{
		 document.formVisualizza.Matricola.className="form-control has-error"; 
	     document.getElementById('divMatricola').className="form-group has-error";
		}
}


function tooltip(){
	
	 
    $("#Nome").tooltip({		              
      'selector': '',
		  'title': 'Stringa letterale compresa tra  2 e 50  caratteri,non deve contenere caratteri speciali.',
		  'trigger': 'focus',
      'placement': 'bottom'
    });
		
		$("#Cognome").tooltip({		              
      'selector': '',
		  'title': 'Stringa letterale compresa tra  2 e 50  caratteri,non deve contenere caratteri speciali.',
		  'trigger': 'focus',
      'placement': 'bottom'
    });     
		
		$("#Username").tooltip({		              
          'selector': '',
			  'title': 'Stringa alfanumerica con lunghezza compresa tra 6 e 20 (che non inizia con un carattere qualsiasi escluso caratteri letterali ) e non presente già all’interno del database.',
			  'trigger': 'focus',
          'placement': 'bottom'
        }); 
		
	
		$("#Password").tooltip({		              
          'selector': '',
			  'title': 'Stringa alfanumerica con lunghezza compresa tra 7 e 20 caratteri. ',
			  'trigger': 'focus',
          'placement': 'bottom'
        });
		

		$("#Email").tooltip({		              
          'selector': '',
			  'title': 'Stringa alfanumerica con lunghezza compresa tra 6 e 50 caratteri e ben formata (contenente la “@” e il “.”) ',
			  'trigger': 'focus',
          'placement': 'bottom'
        });
		

		$("#DataNascita").tooltip({		              
          'selector': '',
			  'title': 'Stringa qualsiasi non contenente  caratteri  letterali con il formato aaaa-mm-gg',
			  'trigger': 'focus',
          'placement': 'bottom'
        });
		

		$("#CittaNascita").tooltip({		              
          'selector': '',
			  'title': 'Stringa letterale  compresa tra 4 e 50 caratteri.  ',
			  'trigger': 'focus',
          'placement': 'bottom'
        });
		

		$("#ProvinciaNascita").tooltip({		              
          'selector': '',
			  'title': 'Stringa letterale con lunghezza di 2 caratteri.  ',
			  'trigger': 'focus',
          'placement': 'bottom'
        });
		

		$("#Sesso").tooltip({		              
          'selector': '',
			  'title': 'Inserisci M oppure F. ',
			  'trigger': 'focus',
          'placement': 'bottom'
        });
		$("#CodiceFiscale").tooltip({		              
          'selector': '',
			  'title': 'Stringa alfanumerica con lunghezza di 16 caratteri. ',
			  'trigger': 'focus',
          'placement': 'bottom'
        });
		$("#Matricola").tooltip({		              
          'selector': '',
			  'title': 'Stringa numerica con lunghezza di 10 caratteri.  ',
			  'trigger': 'focus',
          'placement': 'bottom'
        });
		
		$("#DataInizioServizio").tooltip({		              
          'selector': '',
			  'title': 'Stringa qualsiasi non contenente  caratteri  letterali con il formato "aaaa-mm-gg". ',
			  'trigger': 'focus',
          'placement': 'bottom'
        });
		
		$("#Ruolo").tooltip({		              
          'selector': '',
			  'title': 'Inserisci ruolo. ',
			  'trigger': 'focus',
          'placement': 'bottom'
        });
		
		$("#NomeDipartimento").tooltip({		              
          'selector': '',
			  'title': 'Inserisci nome del dipartimento. ',
			  'trigger': 'focus',
          'placement': 'bottom'
        });
		
		$("#AreaScientifica").tooltip({		              
          'selector': '',
			  'title': 'Inserisci area scientifica ',
			  'trigger': 'focus',
          'placement': 'bottom'
        });

	
	}

function controlloInvioDati()
{
	
  var array=document.formVisualizza;
  var lunghezza=array.length;
  var i=0;
  var contatore=0;
  var cont=0;
  for(i=0;i<lunghezza-2;i++)
	  {
	 
	      if(array[i].className=="form-control has-error")
	    	  { 
	    	    contatore=1;
	    	    break;
	    	  }
	      if(array[i].value.length==""){
	    	  cont++;
	      }
	  
	  }
  

  if(contatore==1)
	  {
	          
	      document.getElementById("h4").innerHTML="Attenzione!";
	      document.getElementById("p").innerHTML="Non sono stati compilati i campi correttamente.";
	      document.getElementById("bottone_conferma").className="btn btn-danger";
	      document.getElementById("bottone_conferma").innerHTML="OK";
	      document.getElementById("bottone_conferma").setAttribute("type", "button");
	      document.getElementById("bottone_conferma").setAttribute("data-dismiss", "modal");
	      document.getElementById("bottoneModifica").setAttribute("data-target", "#myModal");
	  }
  else
	  {
	 
	  	if(cont>0){
	  		document.getElementById("h4").innerHTML="Attenzione!";
		      document.getElementById("p").innerHTML="Non sono stati compilati tutti i campi .";
		      document.getElementById("bottone_conferma").className="btn btn-danger";
		      document.getElementById("bottone_conferma").innerHTML="OK";
		      document.getElementById("bottone_conferma").setAttribute("type", "button");
		      document.getElementById("bottone_conferma").setAttribute("data-dismiss", "modal");
		      document.getElementById("bottoneModifica").setAttribute("data-target", "#myModal");
		  
	  		
	  	}else{
	
	
	  	 document.getElementById("bottoneModifica").removeAttribute("data-toggle");
	     document.getElementById("bottoneModifica").removeAttribute("data-target");
	
	  		
	     document.getElementById("bottoneModifica").type="submit";
//	     document.getElementById("bottoneModifica").submit();
	  		}
	  	}
}



function tornaIndietro()
{
	var link="./gestioneUtenti.jsp";
    window.location.href=link;


}

function controlloUsername2(valore)
 {
	
	flag=0;
	$.ajax({
		type: "POST",
		url: "./ListaAccountServlet",	
		dataType: "xml",
		success: function(msg){
						
			
			$($(msg).find('Account')).each(function() {
				var username = $(this).find('Username').text();
				if(username==valore)
			      {
					flag=1;
			      }
				
			});
			if(flag==1)
			{
				 document.formVisualizza.Username.className="form-control has-error"; 
			     document.getElementById('divUsername').className="form-group has-error";
			}	
		}
	});		
	
	
 }

