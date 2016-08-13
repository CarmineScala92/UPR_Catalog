function controlloTitolo(valore) 
 {

	 if( valore.length<=100 || valore.length==0)
 	 {
		   document.form2.Titolo.className="form-control"; 
 	       document.getElementById('divTitolo').className="form-group";    
 	  }
   else
 	  {
 	  document.form2.Titolo.className="form-control has-error";
 	  document.getElementById('divTitolo').className="form-group has-error";
 	  document.getElementById("divAlert").setAttribute('hidden');
	  document.getElementById("divAlert").innerHTML="";
 	  
 	  }
	}
 
 function controlloAnnoPubblicazione(valore)//gestisce il formato della data 
 {
	
	 //var pattern=/\d\d\d\d/;
	 
	 /*(pattern.test(valore) ||pattern1.test(valore))*/
 
	 if(  valore.length == 4   || valore.length==0)
 	 {
		   document.form2.Anno_Pubblicazione.className="form-control"; 
	       document.getElementById('divAnnoPubblicazione').className="form-group";     
 	 }
   else
 	  {
	   document.form2.Anno_Pubblicazione.className="form-control has-error"; 
       document.getElementById('divAnnoPubblicazione').className="form-group has-error";
       document.getElementById("divAlert").setAttribute('hidden');
 	  document.getElementById("divAlert").innerHTML="";
 	  
 	  }
	}
 
 
var notifica=0;
 function controllaRicercaVuota(){

	 var arr=document.form2;
	 var i=0;
	 var lunghezza=arr.length;
	 var contatore=0;
	 var cont=0;
	 
	 for(i=0;i<lunghezza;i++)
	 {
		 if(i==3 || i==4)
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
		 if(i==3 || i==4)
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
		     if(notifica==0){
		      document.getElementById("notificaRicerca").removeAttribute('hidden');
		      document.getElementById("divAlert1").innerHTML="<strong>Errore!</strong>: per effettuare la ricerca compilare almeno un campo.";
		      window.setTimeout("doRedirect()", 3000);
		      notifica=1;
		     }
		 }
	 
	 else if(cont==1)
		 {
		 if(notifica==0){
			 	document.getElementById("notificaRicerca").removeAttribute('hidden');
			 	document.getElementById("divAlert1").innerHTML="<strong>Errore!</strong>: I Campi non sono compialti correttamente";
			 	window.setTimeout("doRedirect()", 3000);
			 	notifica=1;
		    }
	     }
	 else
		 {
	
		  arr[3].type="submit";
		  arr[3].submit();
		 }
	  }
 
 //chiamato dall' html con jsp per fare uscire la notifica in caso di errore
 function lanciaNotifica(){
		//$("#notificaLogin").show();
        document.getElementById("notificaLogin").removeAttribute('hidden');
		$(".alert-msg").html('<strong>Errore!</strong> Controlla le credenziali di accesso.');
        window.setTimeout("doRedirect1()", 3000);
        
	//	window.setTimeout(function(){ $(".alert").alert('close'); }, 3000);
	}
 
 function doRedirect1(){
	 $("notificaLogin").setAttribute('hidden');
   
	}
 
 
 
 function doRedirect(){
	 document.getElementById("notificaRicerca").setAttribute('hidden');
     document.getElementById("divAlert1").innerHTML="";
     notifica=0;
	}


 
