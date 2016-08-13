$(document).ready(function () {
	  tooltip();
	 
});
function controllaNome(valore)
{
	
var pattern=/^[a-zA-Z,טיעאלש'\- ]*$/;

	if((pattern.test(valore) && valore.length>=4 &&  valore.length<=100) || valore.length==0)
		{
		
	   	 document.formVisualizza.Nome.className="form-control"; 
	     document.getElementById('Div_Nome').className="form-group";  
		
		}
	else
		{
		
		 document.formVisualizza.Nome.className="form-control has-error"; 
	     document.getElementById('Div_Nome').className="form-group has-error";
		
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



function controllaEmail(valore)
{  
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


function tooltip(){
	
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

function tornaAGestioneAteneo()
{
	
	var link="./gestioneAteneo.jsp";
    window.location.href=link;

}
function controllaCodice(valore)
{
	
	 var pattern=/[0-9][0-9]/;
  
  
    if((pattern.test(valore) && valore.length==2) || valore.length==0)
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
