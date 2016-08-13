

function controlloInvioDati()
{
	
	

	controllaNome(document.formVisualizza.Nome.value);
	controllaTelefono(document.formVisualizza.Telefono.value);
	controllaFax(document.formVisualizza.Fax.value);
	controllaEmail(document.formVisualizza.Email.value);
	controllaSito(document.formVisualizza.Sito.value);
	
  var array=document.formVisualizza;
  var lunghezza=array.length;
  var i=0;
  var contatore=0;
  var cont=0;

  for(i=0;i<lunghezza;i++)
  {
 
      if(array[i].className=="form-control has-error")
    	  { 
    	    contatore=1;
    	    break;
    	  }
     
  
  }
  
  
  for(i=0;i<3;i++)
	  {
	 
	     
	      if(array[i].value.length==""){
	    	  cont++;
	      }
	  
	  }
 

  if(contatore==1)
	  {
	          
	      document.getElementById("h4").innerHTML="Attenzione!";
	      document.getElementById("p").innerHTML="Non sono stati compilati i campi correttamente.";
	      document.getElementById("bottoneOk").className="btn btn-danger";
	      document.getElementById("bottoneAnnull").className=" btn btn-default hidden";
	      document.getElementById("bottoneOk").innerHTML="OK";
	      document.getElementById("bottoneOk").setAttribute("type", "button");
	      document.getElementById("bottoneOk").setAttribute("data-dismiss", "modal");
	      //document.getElementById("bottoneModifica").setAttribute("data-target", "#myModal");
	  }
  else
	  {
	 
	  	if(cont>0){
	  		document.getElementById("h4").innerHTML="Attenzione!";
		      document.getElementById("p").innerHTML="Non sono stati compilati tutti i campi .";
		      document.getElementById("bottoneOk").className="btn btn-danger";
		      document.getElementById("bottoneOk").innerHTML="OK";
		      document.getElementById("bottoneAnnull").className=" btn btn-default hidden";
		      document.getElementById("bottoneOk").setAttribute("type", "button");
		      document.getElementById("bottoneOk").setAttribute("data-dismiss", "modal");
		      //document.getElementById("bottoneModifica").setAttribute("data-target", "#myModal");
		  
	  		
	  	}else{

	     
	  		  document.getElementById("h4").innerHTML="Conferma!";
		      document.getElementById("p").innerHTML="Sei sicuro di voler confermare l'inserimento?";
		      document.getElementById("bottoneOk").className="btn btn-success";
		      document.getElementById("bottoneOk").innerHTML="Conferma";
		      document.getElementById("bottoneAnnull").className=" btn btn-default visualizza";
		      document.getElementById("bottoneOk").removeAttribute("data-dismiss", "modal");
		      document.getElementById("bottoneOk").setAttribute("type", "submit");
	  	// document.getElementById("bottoneConferma").removeAttribute("data-toggle");
	     //document.getElementById("bottoneConferma").removeAttribute("data-target");
	     
	     //document.getElementById("bottoneConferma").type="submit";
	     //document.getElementById("bottoneConferma").submit();
	  		}
	  	}
}




