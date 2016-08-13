
function mostraAvanzate(){ //quando clicco su avanzate compaiono ulteriori campi di ricerca
	if(document.getElementById('divAvanzate').className=="hidden"){
		document.getElementById('divAvanzate').className="visualizza";
		document.form2.ricerca.innerHTML="Chiudi menu avanzate <span  id=\"spanfreccia\" class=\"glyphicon glyphicon-chevron-down\">";
	}else{
		
		document.getElementById('divAvanzate').className="hidden";
		document.form2.ricerca.innerHTML="Apri menu avanzate <span  id=\"spanfreccia\" class=\"glyphicon glyphicon-chevron-right\">";
	}


}

  function controlloTitolo(valore) // controlla il formato del titolo
 {
	 
	 //var pattern=/^\d/;
	 //var pattern1=/^\w*$/;
	 if(valore.length<=100 || valore.length==0)
 	 {
 	       document.form2.Titolo.className="form-control"; 
 	       document.getElementById('divTitolo').className="form-group";    
 	 }
   else
 	  {
 	  document.form2.Titolo.className="form-control has-error";
 	  document.getElementById('divTitolo').className="form-group has-error";
 	  document.getElementById("divAlert").className="alert alert-danger hidden";
 	  document.getElementById("divAlert").innerHTML="";
 	  
 	  }
	}
 
 function controlloAnnoPubblicazione(valore)//gestisce il formato della data 
 {
	
	// var pattern=/\d\d\d\d/;
	 
	 /*(pattern.test(valore) ||pattern1.test(valore))*/

	 if( valore.length==4  || valore.length==0)
 	 {
		   document.form2.Anno_Pubblicazione.className="form-control"; 
	       document.getElementById('divAnnoPubblicazione').className="form-group";     
 	 }
   else
 	  {
	   document.form2.Anno_Pubblicazione.className="form-control has-error"; 
       document.getElementById('divAnnoPubblicazione').className="form-group has-error";
       document.getElementById("divAlert").className="alert alert-danger hidden";
 	   document.getElementById("divAlert").innerHTML="";
 	  }
	}
 
 
 function controlloNome(valore)
 {
	 
	if( valore.length<=50 || valore.length==0)
 	 {
		 document.form2.Nome.className="form-control"; 
	     document.getElementById('divNome').className="form-group";    
 	 }
   else
 	  {
	   document.form2.Nome.className="form-control has-error"; 
       document.getElementById('divNome').className="form-group has-error";
       document.getElementById("divAlert").className="alert alert-danger hidden";
 	   document.getElementById("divAlert").innerHTML="";
 	  
 	  }
	}
 
 
 
 function controlloCognome(valore)
 {
	 
	if( valore.length<=50 || valore.length==0)
 	 {
		 document.form2.Nome.className="form-control"; 
	     document.getElementById('divCognome').className="form-group";    
 	 }
   else
 	  {
	   document.form2.Nome.className="form-control has-error"; 
       document.getElementById('divCognome').className="form-group has-error";
       document.getElementById("divAlert").className="alert alert-danger hidden";
 	   document.getElementById("divAlert").innerHTML="";
 	  
 	  }
	}
 

 function controllaRicercaVuota(){

	 var arr=document.form2;
	 var i=0;
	 var lunghezza=arr.length;
	 var contatore=0;
	var cont=0;
	 
	 for(i=0;i<lunghezza;i++)
	 {
		 if(i==3 || i==8 || i==9)
			 {
			 continue;
			 }
		 else
			{
			 
			 if(arr[i].value!="")
				 {
				 contatore=1;
				 break;
				 }
			}
		 
	 }
	
	 
	 for(i=0;i<lunghezza;i++)
	 {
		 if(i==3 || i==8 || i==9)
			 {
			 continue;
			 }
		 else
			{
			 
			 if(arr[i].className=="form-control has-error")
				 {
				 cont=1;
				 break;
				 }
			}
		 
	 }
	 
	 if(contatore==0)
	 {
		 document.getElementById("notificaRicerca").removeAttribute('hidden');
	     document.getElementById("divAlert1").innerHTML="<strong>Errore!</strong>:compilare almeno un campo per effettuare una ricerca";
	     window.setTimeout("doRedirect()", 3000);
	 }
  else if(cont==1)
	 {
	  	document.getElementById("notificaRicerca").removeAttribute('hidden');
	  	document.getElementById("divAlert1").innerHTML="<strong>Errore!</strong>: compilare correttamente i campi";
	  	window.setTimeout("doRedirect()", 3000);
     }
  else
	 {
	    arr[8].type="submit";
	    arr[8].submit();
	 }
	 
	
	}
 
 
 function doRedirect()
 {
	    document.getElementById("notificaRicerca").setAttribute('hidden');
	 	document.getElementById("divAlert1").innerHTML="";
 }
