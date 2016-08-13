$(document).ready(function () {
	  VisualizzaDipartimento();
	 
});

function VisualizzaDipartimento(){

	// document.getElementById('Tabella1').innerHTML="<table id='lista_areeScientifiche' class='table table-hover  table-striped'><thead><tr><th>Codice</th><th>Nome</th><th>Telefono</th></tr></thead></table>";
	
$.ajax({
	      
			type:"POST",
			data: "Id="+$("#idDipartimento").val(),
			url:"./VisualizzaDipartimentoServlet",
			dataType:"xml",
			success: function(msg){							
				row=" ";
				$($(msg).find('Dipartimento')).each(function() {
					//var id_dipartimento = $(this).find('ID_Dipartimento').text();
					
					var Nome = $(this).find('Nome').text();
					var Telefono = $(this).find('Telefono').text();
					var Fax = $(this).find('Fax').text();
					var Sito = $(this).find('Sito').text();
					var Email = $(this).find('Email').text();
					
					var Stringa = "<span>Dipartimento: "+ Nome+"</span>";
					document.getElementById("nomeDip").innerHTML = Stringa;
					$('#Nome').val(Nome);
					$('#Telefono').val(Telefono);
					$('#Fax').val(Fax);
					$('#Sito').val(Sito);
					$('#Email').val(Email);
					 
				});
			  
			
				
			}
		});		
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



function modificaDipartimento()
{
	
	 var link="./Modifica_Dipartimento.jsp?id="+$("#idDipartimento").val();
	  window.location.href=link;

}


function tornaIndietro()
{
	var link="./visualizza_dipartimento.jsp?id="+$("#idDipartimento").val();
    window.location.href=link;


}
function tornaIndietro2()
{
	var link="./gestioneAteneo.jsp";
    window.location.href=link;


}

