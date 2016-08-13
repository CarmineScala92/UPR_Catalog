$(document).ready(function () {
	  VisualizzaArea();
});

function VisualizzaArea(){

	// document.getElementById('Tabella1').innerHTML="<table id='lista_areeScientifiche' class='table table-hover  table-striped'><thead><tr><th>Codice</th><th>Nome</th><th>Telefono</th></tr></thead></table>";
	
$.ajax({
	      
			type:"POST",
			data: "Id="+$("#idArea").val(),
			url:"./VisualizzaAreaScientificaServlet",
			dataType:"xml",
			success: function(msg){							
				row=" ";
				$($(msg).find('Area_Scientifica')).each(function() {
					//var id_dipartimento = $(this).find('ID_Area_Scientifica').text();
					
					var Codice = $(this).find('Codice').text();
					var Nome = $(this).find('Nome').text();
					var Telefono = $(this).find('Telefono').text();
					var Fax = $(this).find('Fax').text();
					var Sito = $(this).find('Sito').text();
					var Email = $(this).find('Email').text();
					
					var Stringa = "<span>Area scientifica: "+ Nome+"</span>";
					document.getElementById("NomeArea").innerHTML = Stringa;
					$('#Nome').val(Nome);
					$('#Codice').val(Codice);
					$('#Telefono').val(Telefono);
					$('#Fax').val(Fax);
					$('#Sito').val(Sito);
					$('#Email').val(Email);
					tooltip();
				});
			  
			
				
			}
		});		
}

function modificaAreaScientifica()
{
	
	 var link="./modifica_Area_Scientifica.jsp?id="+$("#idArea").val();
	  window.location.href=link;

}

/*function tooltip(){
	
	$("#Nome").tooltip({		              
      'selector': '',
		  'title': 'Stringa letterale compresa tra 4 e 50.',
		  'trigger': 'focus',
      'placement': 'bottom'
    });
		
		$("#Telefono").tooltip({		              
      'selector': '',
		  'title': 'Stringa numerica con lunghezza massima di 50 caratteri.',
		  'trigger': 'focus',
      'placement': 'bottom'
    });     
		
		$("#Fax").tooltip({		              
          'selector': '',
			  'title': 'Stringa numerica con lunghezza massima di 50 caratteri.',
			  'trigger': 'focus',
          'placement': 'bottom'
        }); 
		
	
		$("#Email").tooltip({		              
          'selector': '',
			  'title': 'Stringa alfanumerica con lunghezza compresa tra 6 e 50 caratteri e ben formata (contenente la “@” e il “.”). ',
			  'trigger': 'focus',
          'placement': 'bottom'
        });
		

		$("#Sito").tooltip({		              
          'selector': '',
			  'title': 'Stringa qualsiasi compresa tra 6 e 50 caratteri ben formata (che inizia per ” www.” e che non presenta senza spazi).',
			  'trigger': 'focus',
          'placement': 'bottom'
        });
		
		$("#Codice").tooltip({		              
	          'selector': '',
				  'title': 'Inserisci stringa numerica che rappresenta il codice dell\'area scientifica',
				  'trigger': 'focus',
	          'placement': 'bottom'
	        });
}



function controllaNome(valore)
{
	
var pattern=/^[a-zA-Z' ]*$/;
	
	
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

function controllaTelefono(valore)
{
	 var pattern=/^[0-9]*$/;
	 
		if(pattern.test(valore) && valore.length>=8 && valore.length<=50  || valore.length==0)
			{
			
		   	 document.formVisualizza.Telefono.className="form-control"; 
		     document.getElementById('divTelefono').className="form-group";  
			
			}
		else
			{
			 document.formVisualizza.Telefono.className="form-control has-error"; 
		     document.getElementById('divTelefono').className="form-group has-error";
			}


}

function controllaFax(valore)
{
	 var pattern=/^[0-9]*$/;
	
		if(pattern.test(valore) && valore.length>=10 && valore.length<=50 || valore.length==0)
			{
			
		   	 document.formVisualizza.Fax.className="form-control"; 
		     document.getElementById('divFax').className="form-group";  
			
			}
		else
			{
		
			 document.formVisualizza.Fax.className="form-control has-error"; 
		     document.getElementById('divFax').className="form-group has-error";
			}


}



function controllaSito(valore)
{
	
	var pattern=/^w{3}/;
    var pattern1=/^[^ ]*$/;
  
    if(pattern.test(valore) && pattern1.test(valore) && valore.length>=6 && valore.length<=50  || valore.length==0)
	{
	
   	 document.formVisualizza.Sito.className="form-control"; 
     document.getElementById('divSito').className="form-group";  
	
	}
else
	{
	 document.formVisualizza.Sito.className="form-control has-error"; 
     document.getElementById('divSito').className="form-group has-error";
	}
}

function controllaCodice(valore)
{
	
	 var pattern=/^[0-9]*$/;
  
  
    if(pattern.test(valore) || valore.length==0)
	{
	
   	 document.formVisualizza.Codice.className="form-control"; 
     document.getElementById('divCodice').className="form-group";  
	
	}
else
	{
	 document.formVisualizza.Codice.className="form-control has-error"; 
     document.getElementById('divCodice').className="form-group has-error";
	}
}

function controllaEmail(valore)
{  
	var pattern=/^[a-zA-Z]{1}[a-zA-Z0-9._]{2,}[@][a-zA-Z]{2,}[.][a-zA-Z]{1,5} *$/;
	 
	if(pattern.test(valore) && valore.length>=6 && valore.length<=50)
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
*/

function controlloInvioDati()
{
	
  var array=document.formVisualizza;
  var lunghezza=array.length;
  var i=0;
  var contatore=0;

  for(i=0;i<lunghezza;i++)
	  {
	 
	      if(array[i].className=="form-control has-error")
	    	  { 
	    	    contatore=1;
	    	    break;
	    	  }
	  
	  }

  if(contatore==1)
	  {
	       document.getElementById('h4').innerHTML="Attenzione!";
	       document.getElementById('p').innerHTML="Campi non compilati correttamente.";
	       document.getElementById('bottoneAnnulla').className="hidden";
	       document.getElementById('bottoneConferma').className="btn btn-danger";
	       document.getElementById('bottoneConferma').innerHTML="OK";
	       document.getElementById('bottoneConferma').type="button";
	       document.getElementById('bottoneConferma').setAttribute("data-dismiss","modal");
	       
	  }
  else
	  {
	 
	  document.getElementById('h4').innerHTML="Modifica Dipartimento";
      document.getElementById('p').innerHTML="Sei sicuro di voler modificare il dipartimento?.";
      document.getElementById('bottoneAnnulla').className="btn btn-default";
      document.getElementById('bottoneConferma').className="btn btn-success";
      document.getElementById('bottoneConferma').innerHTML="Conferma Modifica";
      document.getElementById('bottoneConferma').type="submit";
      document.getElementById('bottoneConferma').removeAttribute("data-dismiss","modal");
	     
	    
//	     document.formVisualizza.bottoneModifica.type="submit";
//	     document.formVisualizza.bottoneModifica.submit();
	  }
}

function tornaIndietro()
{
	var link="./visualizzaAreaScientifica.jsp?id="+$("#idArea").val();
    window.location.href=link;


}
function tornaIndietro2()
{
	var link="./gestioneAteneo.jsp";
    window.location.href=link;


}