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
					 id_areaScientifica = $(this).find('ID_Area_Scientifica').text();
					 id_dipartimento = $(this).find('ID_Dipartimento').text();
					
					var Username = $(this).find('Username').text();
					var Password = $(this).find('Password').text();
					var Nome = $(this).find('Nome').text();
					var Cognome = $(this).find('Cognome').text();
					if(Tipologia == "ricercatore" || Tipologia=="Ricercatore" ){
						
						document.getElementById('Visualizza').innerHTML='<div class="form-group">'+'<label class="col-xs-offset-2 col-xs-2 control-label">Nome</label>'+'<div class="col-xs-4" ><input class="form-control" type="text" id="Nome" name="Nome" required="required" disabled /></div>'+
						'<span class="help-inline"></span> </div>' +
					     '<div class="form-group">'+
							'<label class="col-xs-offset-2 col-xs-2 control-label">Cognome</label>'+
					        '<div class="col-xs-4" ><input class="form-control" type="text" id="Cognome" name="Cognome" required="required" disabled /></div>'+
							'<span class="help-inline"></span>'+
						'</div>'+
						
						
						 '<div class="form-group">'+
							'<label class="col-xs-offset-2 col-xs-2 control-label">Username</label>'+
					        '<div class="col-xs-4" ><input class="form-control" type="text" id="Username" name="Username" required="required" disabled /></div>'+
							'<span class="help-inline"></span>'+
					'	</div>'+
						
						' <div class="form-group"> '+
							'<label class="col-xs-offset-2 col-xs-2 control-label">Password</label>' + 
					        '<div class="col-xs-4" ><input class="form-control" type="text" id="Password" name="Password" required="required" disabled /></div>' +
							'<span class="help-inline"></span>' +
						'</div>'+
						
						 '<div class="form-group">'+
							'<label class="col-xs-offset-2 col-xs-2 control-label">E-mail</label>'+
					        '<div class="col-xs-4" ><input class="form-control" type="text" id="Email" name="Email" required="required" disabled /></div>'+
							'<span class="help-inline"></span>'+
						'</div>'+
						
						 '<div class="form-group">'+
							'<label class="col-xs-offset-2 col-xs-2 control-label">Data di Nascita</label>'+
					        '<div class="col-xs-4" ><input class="form-control" type="text" id="DataNascita" name="DataNascita" required="required" disabled /></div>'+
							'<span class="help-inline"></span>'+
						'</div>'+
						
					    '<div class="form-group">'+
						'	<label class="col-xs-offset-2 col-xs-2 control-label">Comune di Nascita</label>'+
					     '   <div class="col-xs-4" ><input class="form-control" type="text" id="CittaNascita" name="CittaNascita" required="required" disabled /></div>'+
							'<span class="help-inline"></span>'+
						'</div>'+
						
						'<div class="form-group">'+
						'	<label class="col-xs-offset-2 col-xs-2 control-label">Provincia</label>'+
					     '   <div class="col-xs-4" ><input class="form-control" type="text" id="ProvinciaNascita" name="ProvinciaNascita" required="required" disabled /></div>'+
							'<span class="help-inline"></span>'+
						'</div>'+
						
						
					    '<div class="form-group">'+
						'	<label class="col-xs-offset-2 col-xs-2 control-label">Sesso</label>'+
					     '   <div class="col-xs-4" ><input class="form-control" type="text" id="Sesso" name="Sesso" required="required" disabled /></div>'+
							'<span class="help-inline"></span>'+
						'</div>'+
						
						'<div class="form-group">'+
						'	<label class="col-xs-offset-2 col-xs-2 control-label">Codice Fiscale</label>'+
					     '   <div class="col-xs-4" ><input class="form-control" type="text" id="CodiceFiscale" name="CodiceFiscale" required="required" disabled /></div>'+
							'<span class="help-inline"></span>'+
						'</div>'+
						
						
						'<div class="form-group">'+
						'	<label class="col-xs-offset-2 col-xs-2 control-label">Matricola</label>'+
					     '   <div class="col-xs-4" ><input class="form-control" type="text" id="Matricola" name="Matricola" required="required" disabled /></div>'+
							'<span class="help-inline"></span>'+
						'</div>'+
						
						'<div class="form-group">'+
						'	<label class="col-xs-offset-2 col-xs-2 control-label">Data inizio servizio</label>'+
					     '   <div class="col-xs-4" ><input class="form-control" type="text" id="DataInizioServizio" name="DataInizioServizio" required="required" disabled /></div>'+
							'<span class="help-inline"></span>'+
						'</div>'+
						
						'<div class="form-group">'+
						'	<label class="col-xs-offset-2 col-xs-2 control-label">Ruolo</label>'+
					     '   <div class="col-xs-4" ><input class="form-control" type="text" id="Ruolo" name="Ruolo" required="required" disabled /></div>'+
							'<span class="help-inline"></span>'+
						'</div>'+
						
						'<div class="form-group">'+
						'	<label class="col-xs-offset-2 col-xs-2 control-label">Tipologia</label>'+
					     '   <div class="col-xs-4" ><input class="form-control"  type="text" id="Tipologia" name="Tipologia" required="required" disabled /></div>'+
							'<span class="help-inline"></span>'+
						'</div>'+
						
						'<div class="form-group">'+
						
						'<label class="col-xs-offset-2 col-xs-2 control-label">Dipartimento</label>'+
					     '   <div class="col-xs-4" ><input class="form-control" type="text" id="NomeDipartimento" name="NomeDipartimento" required="required" disabled /></div>'+
							'<span class="help-inline"></span>'+
						'</div>'+
						
						'<div class="form-group">'+
						'	<label class="col-xs-offset-2 col-xs-2 control-label">Area Scientifica</label>'+
					     '   <div class="col-xs-4" ><input class="form-control" type="text" id="AreaScientifica" name="AreaScientifica" required="required" disabled /></div>'+
							'<span class="help-inline"></span>'+
						'</div>';
								
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
						
						$('#CodiceFiscale').val(CodiceFiscale);
						$('#DataNascita').val(Data_Nascita);
						$('#CittaNascita').val(CittaNascita);
						$('#ProvinciaNascita').val(ProvinciaNascita);
						$('#Matricola').val(Matricola);
						$('#Sesso').val(Sesso);
						$('#Email').val(Email);
						$('#DataInizioServizio').val(DataInizioServizio);
						$('#NomeDipartimento').val(NomeDipartimento);
						$('#Ruolo').val(Ruolo);
						$('#AreaScientifica').val(NomeAreaScientifica);
						$('#Tipologia').val(Tipologia);
					}
					else
				    {
						document.getElementById('Visualizza').innerHTML='<div class="form-group">'+'<label class="col-xs-offset-2 col-xs-2 control-label">Nome</label>'+'<div class="col-xs-4" ><input class="form-control" type="text" id="Nome" name="Nome" required="required" disabled /></div>'+
						'<span class="help-inline"></span> </div>' +
					     '<div class="form-group">'+
							'<label class="col-xs-offset-2 col-xs-2 control-label">Cognome</label>'+
					        '<div class="col-xs-4" ><input class="form-control" type="text" id="Cognome" name="Cognome" required="required" disabled /></div>'+
							'<span class="help-inline"></span>'+
						'</div>'+
						
						
						 '<div class="form-group">'+
							'<label class="col-xs-offset-2 col-xs-2 control-label">Username</label>'+
					        '<div class="col-xs-4" ><input class="form-control" type="text" id="Username" name="Username" required="required" disabled /></div>'+
							'<span class="help-inline"></span>'+
					   '	</div>'+
						
						' <div class="form-group"> '+
							'<label class="col-xs-offset-2 col-xs-2 control-label">Password</label>' + 
					        '<div class="col-xs-4" ><input class="form-control" type="text" id="Password" name="Password" required="required" disabled /></div>' +
							'<span class="help-inline"></span>' +
						'</div>';
						
						
					}
					$('#Nome').val(Nome);
					$('#Cognome').val(Cognome);
					$('#Username').val(Username);
					$('#Password').val(Password);
					
					
					var Stringa = "<span>Utente: "+ Nome+' ' +Cognome+ "</span>";
					document.getElementById("NomeUtente").innerHTML = Stringa;
					
				
				});
			  }
		});		
}

function modifica()
{
	      var link="./modifica_account.jsp?id="+$("#idAccount").val();
	      window.location.href=link;
	      
}

function tornaIndietro2()
{
	var link="./gestioneUtenti.jsp";
    window.location.href=link;


}








