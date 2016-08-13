
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
	 
	 var pattern=/^\d/;
	 var pattern1=/^\w*$/;
	 
	
	 if(((pattern.test(valore) ||pattern1.test(valore)) && valore.length>10 && valore.length<100) || valore.length==0)
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
	
	 var pattern=/\d\d\d\d/;
	 
	 /*(pattern.test(valore) ||pattern1.test(valore))*/
	 if((pattern.test(valore) && valore.length==4 && valore>=2013 && valore<=2014 ) || valore.length==0)
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
	 
	 var pattern=/^\D*$/;
	 var pattern1=/^\w*$/;
	 var pattern2=/^[^_]*$/;
	 
	if((((pattern.test(valore) && pattern1.test(valore) && pattern2.test(valore))&&( valore.length>=2 && valore.length<=100 ))) || valore.length==0)
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
	
	 var pattern=/^\D*$/;
	 var pattern1=/^\w*$/;
	 var pattern2=/^[^_]*$/;
	 
	if((((pattern.test(valore) && pattern1.test(valore) && pattern2.test(valore))&&( valore.length>=2 && valore.length<=100 ))) || valore.length==0)
 	 {
		 document.form2.Cognome.className="form-control"; 
	       document.getElementById('divCognome').className="form-group";      
 	 }
   else
 	  {
	   document.form2.Cognome.className="form-control has-error"; 
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
			    if(arr[i].value!="" && arr[i].className!="form-control has-error")
			    {
				  contatore=1;
			    }
			    
			    if(arr[i].className=="form-control has-error")
			    {
			      cont=2;	
			      contatore--;
			    }
			 
	        } 
		 
	  }
	 
	 if(contatore==0)
		 {
		 
		     document.getElementById("divAlert").className="alert alert-danger visualizza";
		     document.getElementById("divAlert").innerHTML="<strong>Errore!</strong>:compilare almeno un campo per effettuare una ricerca";
		 }
	  else if(cont==2)
		 {
		  document.getElementById("divAlert").className="alert alert-danger visualizza";
		  document.getElementById("divAlert").innerHTML="<strong>Errore!</strong>: compilare correttamente i campi";
		 } 
	  else
		{
		 arr[8].type="submit";
		  arr[8].submit();
		  
		}
	 }
 
 
/*function controllaTutti(){
	 var i=0;
	 var arr=document.form3;
	 var n=arr.length;
	 var cont=0;
	 for (i = 0; i < n; i++) {
		 if(i==1){
			 if(arr[1].value!=""){
				 cont++;
			 }
		 }
		 if(i==5){
			 if(arr[5].value!=""){
				 cont++;
			 }
			 
		 }
		 if(i==6){
			 if(arr[6].value!=""){
				 cont++;
			 }
			 
		 }
		if(i==3||i==4){
			continue;
		}
		if(i!=1&&i!=5&&i!=6&&i!=3&&i!=4&&arr[i].value!=""){
			cont++;
		}
	}
	 if(cont==0){
		 arr[4].type="button";
		 document.getElementById("divAlertRicerca").className="errore visualizza row alert alert-danger";
		 document.getElementById("divAlertRicerca").innerHTML="<strong>Errore!</strong>: compilare almeno un campo per effettuare una ricerca";
		 
	 }

 }*/
 
 
 
 
