$(document).ready(function () {
	  VisualizzaUtente();
	 
	  
});

function VisualizzaUtente(){

	// document.getElementById('Tabella1').innerHTML="<table id='lista_areeScientifiche' class='table table-hover  table-striped'><thead><tr><th>Codice</th><th>Nome</th><th>Telefono</th></tr></thead></table>";
	
$.ajax({
	      
			type:"POST",
			data: "Id="+$("#idAccount").val(),
			url:"./VisualizzaAccountServlet",
			dataType:"xml",
			success: function(msg){							
				row=" ";
				$($(msg).find('Account')).each(function() {
					
					var Tipologia = $(this).find('Tipologia').text();
					var ID_Account = $(this).find('ID_Account').text();
					//var id_dipartimento = $(this).find('ID_Area_Scientifica').text();
					
					 var id_areaScientifica = $(this).find('ID_Area_Scientifica').text();
					 var id_dipartimento = $(this).find('ID_Dipartimento').text();
					 
					var Username = $(this).find('Username').text();
					var Password = $(this).find('Password').text();
					var Nome = $(this).find('Nome').text();
					var Cognome = $(this).find('Cognome').text();
					if(Tipologia == "ricercatore" || Tipologia=="Ricercatore" ){
						
						document.getElementById("Div_Visualizza").innerHTML='<div class="form-group" id="divNome">'+'<label class="col-xs-offset-2 col-xs-2 control-label">Nome</label>'+'<div class="col-xs-4" ><input class="form-control" type="text" id="Nome" name="Nome" required="required" onkeyup="controllaNome(this.value)"  /></div>'+
						'<span class="help-inline"></span> </div>' +
					     '<div class="form-group" id="divCognome">'+
							'<label class="col-xs-offset-2 col-xs-2 control-label">Cognome</label>'+
					        '<div class="col-xs-4" ><input class="form-control" type="text" id="Cognome" name="Cognome" required="required"  onkeyup="controllaCognome(this.value)"/></div>'+
							'<span class="help-inline"></span>'+
						'</div>'+
						
						
						 '<div class="form-group" id="divUsername">'+
							'<label class="col-xs-offset-2 col-xs-2 control-label">Username</label>'+
					        '<div class="col-xs-4" ><input class="form-control" type="text" id="Username" name="Username" required="required"  onkeyup="controllaUsername(this.value);controlloUsername2(this.value)" /></div>'+
							'<span class="help-inline"></span>'+
					'	</div>'+
						
						' <div class="form-group" id="divPassword"> '+
							'<label class="col-xs-offset-2 col-xs-2 control-label">Password</label>' + 
					        '<div class="col-xs-4" ><input class="form-control" type="text" id="Password" name="Password" required="required"  onkeyup="controllaPassword(this.value)" /></div>' +
							'<span class="help-inline"></span>' +
						'</div>'+
						
						 '<div class="form-group" id="divEmail">'+
							'<label class="col-xs-offset-2 col-xs-2 control-label">E-mail</label>'+
					        '<div class="col-xs-4" ><input class="form-control" type="text" id="Email" name="Email" required="required" onkeyup="controllaEmail(this.value)" /></div>'+
							'<span class="help-inline"></span>'+
						'</div>'+
						
						 '<div class="form-group" id="divDataNascita">'+
							'<label class="col-xs-offset-2 col-xs-2 control-label">Data di Nascita</label>'+
					        '<div class="col-xs-4" ><input class="form-control" type="text" id="DataNascita" name="Data_Nascita" required="required"   onkeyup="controllaDataNascita(this.value)"/></div>'+
							'<span class="help-inline"></span>'+
						'</div>'+
						
					    '<div class="form-group" id="divCittaNascita">'+
						'	<label class="col-xs-offset-2 col-xs-2 control-label">Comune di Nascita</label>'+
					     '   <div class="col-xs-4" ><input class="form-control" type="text" id="CittaNascita" name="Citta_Nascita" required="required"  onkeyup="controllaCittaNascita(this.value)" /></div>'+
							'<span class="help-inline"></span>'+
						'</div>'+
						
						'<div class="form-group" id="divProvincia">'+
						'	<label class="col-xs-offset-2 col-xs-2 control-label">Provincia</label>'+
					     '   <div class="col-xs-4" ><input class="form-control" type="text" id="ProvinciaNascita" name="Provincia_Nascita" required="required"  onkeyup="controllaProvincia(this.value)" /></div>'+
							'<span class="help-inline"></span>'+
						'</div>'+
						
						
					    '<div class="form-group" id="divSesso">'+
						'	<label class="col-xs-offset-2 col-xs-2 control-label">Sesso</label>'+
					     '   <div class="col-xs-4" ><input class="form-control" type="text" id="Sesso" name="Sesso" required="required"  onkeyup="controllaSesso(this.value)"/></div>'+
							'<span class="help-inline"></span>'+
						'</div>'+
						
						'<div class="form-group" id="divCodiceFiscale">'+
						'	<label class="col-xs-offset-2 col-xs-2 control-label">Codice Fiscale</label>'+
					     '   <div class="col-xs-4" ><input class="form-control" type="text" id="CodiceFiscale" name="Codice_Fiscale" required="required"  onkeyup="controllaCodiceFiscale(this.value)"/></div>'+
							'<span class="help-inline"></span>'+
						'</div>'+
						
						
						'<div class="form-group" id="divMatricola">'+
						'	<label class="col-xs-offset-2 col-xs-2 control-label">Matricola</label>'+
					     '   <div class="col-xs-4" ><input class="form-control" type="text" id="Matricola" name="Matricola" required="required"  onkeyup="controllaMatricola(this.value)"/></div>'+
							'<span class="help-inline"></span>'+
						'</div>'+
						
						'<div class="form-group" id="divDataInizio">'+
						'	<label class="col-xs-offset-2 col-xs-2 control-label">Data inizio servizio</label>'+
					     '   <div class="col-xs-4" ><input class="form-control" type="text" id="DataInizioServizio" name="Data_Inizio_Servizio" required="required"  onkeyup="controllaInizio(this.value)"/></div>'+
							'<span class="help-inline"></span>'+
						'</div>'+
						
						'<div class="form-group" id="divRuolo">'+
						'	<label class="col-xs-offset-2 col-xs-2 control-label">Ruolo</label>'+
					     '   <div class="col-xs-4" ><select class="form-control" name="Ruolo" id="Ruolo"><option value="ricercatore">Ricercatore</option><option id="Direttore" value="Direttore">Direttore dipartimento</option><option id="Membro" value="Membro">Membro area scientifica</option></select></div>'+
							'<span class="help-inline"></span>'+
						'</div>'+
						
						'<div class="form-group">'+
						'	<label class="col-xs-offset-2 col-xs-2 control-label">Tipologia</label>'+
					     '   <div class="col-xs-4" ><input class="form-control"  type="text" id="Tipologia" name="Tipologia" required="required" disabled /></div>'+
							'<span class="help-inline"></span>'+
						'</div>'+
						
						
						'<div class="form-group" id="divDipartimento">'+
						
						'<label class="col-xs-offset-2 col-xs-2 control-label">Dipartimento</label>'+
					     '   <div class="col-xs-4"><select name="ID_Dipartimento" class="form-control" id="DipartimentoSelect"></select></div>'+
							
						'</div>'+
						
						'<div class="form-group" id="divAreaScientifica">'+
						'	<label class="col-xs-offset-2 col-xs-2 control-label">Area Scientifica</label>'+
					     '   <div class="col-xs-4" ><select  name="ID_Area_Scientifica" class="form-control" id="Area_ScientificaSelect"></select></div>'+
							'<span class="help-inline"></span>'+
						'</div>'+
						
						 '<input  name="ID_Account" value="'+ID_Account+'" hidden>'+
							
						
						'		<div><button name="bottoneModifica" id="bottoneModifica" type="button" data-toggle="modal" data-target="#myModal" class="btn btn-success" style="margin-left:290px ;margin-top:50px" onclick="liberaTipologia();controlloInvioDati();">Conferma</button>'+
						'		<button  id="bottoneElimina" type="button" class="btn btn-danger " style="margin-left:370px;margin-top:50px" onclick="tornaIndietro()" >Annulla</button></div>';  
								
						var CodiceFiscale = $(this).find('Codice_Fiscale').text();
						var Data_Nascita = $(this).find('Data_Nascita').text();
						var CittaNascita = $(this).find('Citta_Nascita').text();
						var ProvinciaNascita = $(this).find('Provincia_Nascita').text();
						var Matricola = $(this).find('Matricola').text();
						var Sesso = $(this).find('Sesso').text();
						var Email = $(this).find('Email').text();
						var DataInizioServizio = $(this).find('Data_Inizio_Servizio').text();
						var NomeDipartimento = $(this).find('Nome_Dipartimento').text();
						var NomeAreaScientifica = $(this).find('Nome_Area_Scientifica').text();
						var Ruolo = $(this).find('Ruolo').text();	
						
						$('#Nome').val(Nome);
						$('#Cognome').val(Cognome);
						$('#Username').val(Username);
						$('#Password').val(Password);
						$('#CodiceFiscale').val(CodiceFiscale);
						$('#DataNascita').val(Data_Nascita);
						$('#CittaNascita').val(CittaNascita);
						$('#ProvinciaNascita').val(ProvinciaNascita);
						$('#Matricola').val(Matricola);
						$('#Sesso').val(Sesso);
						$('#Email').val(Email);
						$('#DataInizioServizio').val(DataInizioServizio);
						$('#NomeDipartimento').val(NomeDipartimento);
						$("#Ruolo option[value='"+Ruolo+"']").prop('selected','selected');
						$('#AreaScientifica').val(NomeAreaScientifica);
						$('#Tipologia').val(Tipologia);
					
						 optionsDipartimenti(id_dipartimento);
						 optionsAreeScientifiche(id_areaScientifica);
						 optionRuolo(Ruolo);
					}
					else
				    {
						document.getElementById("Div_Visualizza").innerHTML='<div class="form-group" id="divNome">'+'<label class="col-xs-offset-2 col-xs-2 control-label">Nome</label>'+'<div class="col-xs-4" ><input class="form-control" type="text" id="Nome" name="Nome" required="required" onkeyup="controllaNome(this.value)" /></div>'+
						'<span class="help-inline"></span> </div>' +
					     '<div class="form-group" id="divCognome">'+
							'<label class="col-xs-offset-2 col-xs-2 control-label">Cognome</label>'+
					        '<div class="col-xs-4" ><input class="form-control" type="text" id="Cognome" name="Cognome" required="required"   onkeyup="controllaCognome(this.value)"/></div>'+
							'<span class="help-inline"></span>'+
						'</div>'+
						
						
						 '<div class="form-group" id="divUsername">'+
							'<label class="col-xs-offset-2 col-xs-2 control-label">Username</label>'+
					        '<div class="col-xs-4" ><input class="form-control" type="text" id="Username" name="Username" required="required" onkeyup="controllaUsername(this.value);controlloUsername2(this.value)" /></div>'+
							'<span class="help-inline"></span>'+
					'	</div>'+
						
						' <div class="form-group" id="divPassword"> '+
							'<label class="col-xs-offset-2 col-xs-2 control-label">Password</label>' + 
					        '<div class="col-xs-4" ><input class="form-control" type="text" id="Password" name="Password" required="required"  onkeyup="controllaPassword(this.value)"/></div>' +
							'<span class="help-inline"></span>' +
						'</div>'+
						'<input  name="ID_Account" value="'+ID_Account+'" hidden>'+
						'<input  name="Tipologia" value="'+Tipologia+'" hidden>'+		
						'		<div><button name="bottoneModifica" id="bottoneModifica" type="button" data-toggle="modal" data-target="#myModal" class="btn btn-success" style="margin-left:290px ;margin-top:50px" onclick="controlloInvioDati()">Conferma</button>'+
						'		<button  id="bottoneElimina" type="button" class="btn btn-danger " data-toggle="modal" data-target="#myModal" style="margin-left:370px;margin-top:50px" onclick="tornaIndietro()">Annulla</button></div>';  
						
					}
					$('#Nome').val(Nome);
					$('#Cognome').val(Cognome);
					$('#Username').val(Username);
					$('#Password').val(Password);
					$('#Tipologia').val(Tipologia);

					
					
					var Stringa = "<span>Modifica Utente: "+ Nome+' ' +Cognome+ "</span>";
					document.getElementById("NomeUtente").innerHTML = Stringa;
					
					
					 tooltip();
					 
				});
			  }
		});		
}



/*function eliminaDisabled(){
	
	var array=document.formVisualizza;
	var lunghezza= array.length;
	var i=0;
	
	for(i=0;i<lunghezza;i++)
		{
		 
		  array[i].removeAttribute('disabled');
		
		}
}*/

function tornaIndietro()
{
	var link="./visualizzaUtente.jsp?id="+$("#idAccount").val();
    window.location.href=link;

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
			  'title': 'Stringa qualsiasi non contenente  caratteri  letterali con il formato“gg-mm-aaaa”.',
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
			  'title': 'Stringa qualsiasi non contenente  caratteri  letterali con il formato“gg-mm-aaaa”. ',
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
				row +=	'<option value="'+id+'" >'+ nome +'</option>';	
			});
			$(row).appendTo("#Dipartimento");
		}
	});		
}


function controllaNome(valore){
	
	var pattern=/^[a-zA-Z'èòùàì ]*$/;
	
	
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
	
	var pattern=/^[a-zA-Z'èòùàì ]*$/;
	
	
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
	

	
	if((valore.length>=6 &&  valore.length<=20) || valore.length==0)
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


function controllaCittaNascita(valore){
	
	var pattern=/^[a-zA-Z ]*$/;
	
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




function controllaInizio(valore){
	
	var pattern=/^(19|20)\d\d[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])/;

	 
	
	if((pattern.test(valore)) && valore.length==10  && valore.slice(0,4)>=1950 && valore.slice(0,4)<=2014 || valore.length==0)
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
	          
	      document.getElementById("h4").innerHTML="Attenzione!";
	      document.getElementById("p").innerHTML="Non sono stati compilati i campi correttamente.";
	      //document.getElementById("div_annulla").setAttribute("hidden","hidden");
	      document.getElementById("bottone_conferma").className="btn btn-danger";
	      document.getElementById("bottone_conferma").innerHTML="OK";
	      document.getElementById("bottone_conferma").setAttribute("type", "button");
	      document.getElementById("bottone_conferma").setAttribute("data-dismiss", "modal");
	  }
  else
	  {
	 
	  
	  
	  document.getElementById("h4").innerHTML="Modifica account";
      document.getElementById("p").innerHTML="Sei sicuro di voler modificare l'account?";
      //document.getElementById("div_annulla").removeAttribute("hidden");
      document.getElementById("bottone_conferma").className="btn btn-success";
      document.getElementById("bottone_conferma").innerHTML="Conferma modifica";
      document.getElementById("bottone_conferma").removeAttribute("data-dismiss", "modal");
      document.getElementById("bottone_conferma").setAttribute("type", "submit");
      
  
	  
//	     document.formVisualizza.bottoneModifica.type="submit";
//	     document.formVisualizza.bottoneModifica.submit();
	  }
}

function liberaTipologia()
{
	
	 document.formVisualizza.Tipologia.removeAttribute('disabled');
}




function optionsDipartimenti(idDip){
	
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
				if(	idDip==id)
				row +=	'<option value="'+id+'" selected>'+ nome +'</option>';
				else
				row +=	'<option value="'+id+'" >'+ nome +'</option>';	
			});
			$(row).appendTo("#DipartimentoSelect");
		}
	});		
}
function optionsAreeScientifiche(idArea){

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
				if(	idArea==id)
				row +=	'<option value="'+id+'" selected>'+ nome +'</option>';
				else
			    row +=	'<option value="'+id+'" >'+ nome +'</option>';
			});
			$(row).appendTo("#Area_ScientificaSelect");
		}
	});		
}


function optionRuolo(Ruolo)
{
     if(document.getElementById('Direttore').value==Ruolo)
    	 {
    	 document.getElementById('Direttore').setAttribute('selected');
    	  
    	 
    	 }
     else
    	 {
    	 
    	 document.getElementById('Membro').setAttribute('selected');
    	 
    	 }



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