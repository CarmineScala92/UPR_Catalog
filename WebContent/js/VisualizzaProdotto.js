$(document).ready(function() {
	caricaRicercatori();
	createFields();
	disabilitaCampi();
	compilaForm();
});
function showElimina(){
	$('#ModalElimina').modal('show');
}
var id_ricercatori = new Array();
function compilaForm() {
	//alert("Avvio la compilazione del form\n"+$("#ID_Prodotto").val());
	$.ajax({
		type: "POST",
		url: "./VisualizzaProdottoServlet",
		data: "Id="+$("#ID_Prodotto").val(),
		dataType: "xml",
		success: function(xml) {
			var isAmministratore=$("#amministratore").val();
			var idProprietario, isPubblico, statoProdotto, urlfile;
			
			idProprietario=$(xml).find('ID_Proprietario').text();
			$("#ID_Proprietario").val(idProprietario);
			isPubblico=$(xml).find('Pubblico').text();
			statoProdotto=$(xml).find('Stato').text();
			urlfile=$(xml).find('URL').text();
			
			var titolo, autori, anno, t_abstract, tipologia;
			titolo=$(xml).find('Titolo').text();
			autori=$(xml).find('Autori').text();
			anno=$(xml).find('Anno_Pubblicazione').text();
			t_abstract=$(xml).find('Abstract').text();
			tipologia=$(xml).find('Tipologia').text();
			var autoriRicercatori=$(xml).find('Autori_Ricercatori').text();
			
			$(xml).find("Id_Ricercatori").find("Id").each(function(){
				var l_index=id_ricercatori.length;
				id_ricercatori[l_index]=parseInt($(this).text());
				if(id_ricercatori[l_index]==parseInt(idProprietario)){
					$(elenco_ricercatori_completo).find("option[value='"+id_ricercatori[i]+"']").remove();
					return;
				}
			});
			
			if(idProprietario==$("#ID_CurrentUser").val() || $("#amministratore").val()=='1'){
				if(statoProdotto=='draft' || statoProdotto=='completo'){
					$("<button id='btnModProd' type='button' class='btn btn-primary' onclick='abilitaCampi()'>Modifica prodotto</button>").appendTo("#bottoni-azione");
				}
				else if($("#amministratore").val()=='1'){
					$("<button id='btnModProd' type='button' class='btn btn-primary' onclick='abilitaCampi()'>Modifica prodotto</button>").appendTo("#bottoni-azione");
					$("#formInserimentoProdotto").prop('action', './ModificaProdottoServlet');
				}
				
				if(statoProdotto=='draft')
					$("<button id='btnDelProd' type='button' class='btn btn-danger col-xs-offset-2' onclick='showElimina()'>Elimina prodotto</button>").appendTo("#bottoni-azione");
				if(statoProdotto=='completo' && isAmministratore=='1'){
					$("<button id='btnDelProd' type='button' class='btn btn-danger col-xs-offset-2' onclick='showElimina()'>Elimina prodotto</button>").appendTo("#bottoni-azione");
				}
			}
			
			if(statoProdotto!='draft' && statoProdotto!='completo') { //PRODOTTO VALIDATO
				if(isPubblico=='0') {
					if($("#ID_CurrentUser").val()=='0') { //Se il prodotto non è pubblico, gli utenti esterni non possono visualizzarlo
						showModal("",'Devi essere loggato per visualizzare questo prodotto');
						return;
					}
					else { //Gli utenti loggati possono visualizzare il prodotto
						$("<button id='btnPrintProd' type='button' class='btn btn-warning col-xs-offset-2' onclick='location.href=\"./UploadDownloadFileServlet?fileName="+urlfile+"\"'>Stampa prodotto</button>").appendTo("#bottoni-azione");
						$("#tipologia option[value='"+tipologia+"']").prop('selected', 'selected');
						$("#Titolo").val(titolo);
						$(document).attr('title', titolo);
						$("#autori").val(autori);
						$("#Anno_Pubblicazione").val(anno);
						$("#Abstract").val(t_abstract);
						if(isPubblico=='1')
							$("#Pubblico1").prop("checked", "checked");
						else
							$("#Pubblico2").prop("checked", "checked");
						$("#AutoriRicercatori").val(autoriRicercatori);
						
						createFields();
						disabilitaCampi();				
						popolaCampi(tipologia, xml);
					}
				}
				else { //PRODOTTO PUBBLICO VALIDATO
					if(parseInt($("#ID_CurrentUser").val())>0)
						$("<button id='btnPrintProd' type='button' class='btn btn-warning col-xs-offset-2' onclick='location.href=\"./UploadDownloadFileServlet?fileName="+urlfile+"\"'>Stampa prodotto</button>").appendTo("#bottoni-azione");
					$("#tipologia option[value='"+tipologia+"']").prop('selected', 'selected');
					$("#Titolo").val(titolo);
					$(document).attr('title', titolo);
					$("#autori").val(autori);
					$("#Anno_Pubblicazione").val(anno);
					$("#Abstract").val(t_abstract);
					if(isPubblico=='1')
						$("#Pubblico1").prop("checked", "checked");
					else
						$("#Pubblico2").prop("checked", "checked");
					$("#AutoriRicercatori").val(autoriRicercatori);
					
					createFields();
					disabilitaCampi();				
					popolaCampi(tipologia, xml);
				}
			}
			else { //PRODOTTO NON VALIDATO
				if(idProprietario==$("#ID_CurrentUser").val() || $("#amministratore").val()=='1' || ($("#direttore").val()=='1' && statoProdotto=='completo')){ //Solo il proprietario del prodotto può visualizzare il prodotto non validato
					$("<button id='btnPrintProd' type='button' class='btn btn-warning col-xs-offset-2' onclick='location.href=\"./UploadDownloadFileServlet?fileName="+urlfile+"\"'>Stampa prodotto</button>").appendTo("#bottoni-azione");
					$("#tipologia option[value='"+tipologia+"']").prop('selected', 'selected');
					$("#Titolo").val(titolo);
					$(document).attr('title', titolo);
					$("#autori").val(autori);
					$("#Anno_Pubblicazione").val(anno);
					$("#Abstract").val(t_abstract);
					if(isPubblico=='1')
						$("#Pubblico1").prop("checked", "checked");
					else
						$("#Pubblico2").prop("checked", "checked");
					$("#AutoriRicercatori").val(autoriRicercatori);
					
					createFields();
					disabilitaCampi();				
					popolaCampi(tipologia, xml);
				}
				else {
					//gli autori citati possono visualizzare il prodotto e stamparlo
					var utenteRic=false;
					for(var i=0;i<id_ricercatori.length;i++){
						if(id_ricercatori[i]==parseInt($("#ID_CurrentUser").val())){
							utenteRic=true;
							break;
						}
					}
					if(utenteRic){
						$("<button id='btnPrintProd' type='button' class='btn btn-warning col-xs-offset-2' onclick='location.href=\"./UploadDownloadFileServlet?fileName="+urlfile+"\"'>Stampa prodotto</button>").appendTo("#bottoni-azione");
						$("#tipologia option[value='"+tipologia+"']").prop('selected', 'selected');
						$("#Titolo").val(titolo);
						$(document).attr('title', titolo);
						$("#autori").val(autori);
						$("#Anno_Pubblicazione").val(anno);
						$("#Abstract").val(t_abstract);
						if(isPubblico=='1')
							$("#Pubblico1").prop("checked", "checked");
						else
							$("#Pubblico2").prop("checked", "checked");
						$("#AutoriRicercatori").val(autoriRicercatori);
						
						createFields();
						disabilitaCampi();				
						popolaCampi(tipologia, xml);
					}
					else {
						showModal("","Il prodotto non è stato ancora validato. Solo il responsabile del file può visualizzare il prodotto.");
						return;
					}
				}
			}
		}
	});
}
function popolaCampi(tipologia, xml){
	switch(tipologia){
		case 'articolo libro':
			$("#Nome_Volume").val($(xml).find('Nome_Volume').text());
			$("#Autori_Volume").val($(xml).find('Autori_Volume').text());
			$("#Editore").val($(xml).find('Editore').text());
			$("#Citta_Editore").val($(xml).find('Citta_Editore').text());
			$("#Paese_Editore").val($(xml).find('Paese_Editore').text());
			$("#Pagine_Riferimento").val($(xml).find('Pagine_Riferimento').text());
			$("#ISBN").val($(xml).find('ISBN').text());
			$("#DOI").val($(xml).find('DOI').text());
			$("#Keywords").val($(xml).find('Keywords').text());
			break;
		//Nome_Volume
		//Autori_Volume
		//Editore
		//Citta_Editore
		//Paese_Editore
		//Pagine_Riferimento
		//ISBN
		//DOI
		//Keywords
		case 'articolo rivista':
			$("#Nome_Rivista").val($(xml).find('Nome_Rivista').text());
			$("#Numero_Volume").val($(xml).find('Numero_Volume').text());
			$("#Pagine_Riferimento").val($(xml).find('Pagine_Riferimento').text());
			$("#DOI").val($(xml).find('DOI').text());
			$("#Keywords").val($(xml).find('Keywords').text());
		//Nome_Rivista
		//Numero_Volume
		//Pagine_Riferimento
		//DOI
		//Keywords		
		case 'brevetto':
			$("#Proprieta").val($(xml).find('Proprieta').text());
			$("#Numero_Brevetto").val($(xml).find('Numero_Brevetto').text());
			$("#Tipo option[value='"+$(xml).find('Tipo').text()+"']").prop("selected", "selected");
			$("#DOI").val($(xml).find('DOI').text());
			$("#Keywords").val($(xml).find('Keywords').text());
			break;
		//Proprieta
		//Numero_Brevetto
		//Tipo
		//DOI
		//Keywords
		case 'curatela':
			$("#Nome_Rivista").val($(xml).find('Nome_Rivista').text());
			$("#Numero_Volume").val($(xml).find('Numero_Volume').text());
			$("#Autori_Volume").val($(xml).find('Autori_Volume').text());
			$("#Editore").val($(xml).find('Editore').text());
			$("#Citta_Editore").val($(xml).find('Citta_Editore').text());
			$("#Pagine_Riferimento").val($(xml).find('Pagine_Riferimento').text());
			$("#ISBN").val($(xml).find('ISBN').text());
			$("#DOI").val($(xml).find('DOI').text());
			$("#Keywords").val($(xml).find('Keywords').text());
			break;
		//Nome_Rivista
		//Numero_Volume
		//Autori_Volume
		//Editore
		//Citta_Editore
		//Pagine_Riferimento
		//ISBN
		//DOI
		//Keywords
				
		case 'monografia':
			$("#Editore").val($(xml).find('Editore').text());
			$("#Citta_Editore").val($(xml).find('Citta_Editore').text());
			$("#Paese_Editore").val($(xml).find('Paese_Editore').text());
			$("#Pagine_Riferimento").val($(xml).find('Pagine_Riferimento').text());
			$("#ISBN").val($(xml).find('ISBN').text());
			$("#DOI").val($(xml).find('DOI').text());
			$("#Keywords").val($(xml).find('Keywords').text());
			break;
		//Editore
		//Citta_Editore
		//Paese_Editore
		//Pagine_Riferimento
		//ISBN
		//DOI
		//Keywords
		case 'proceeding':
			$("#Relazione option[value='"+$(xml).find('Relazione').text()+"']").prop("selected", "selected");
			$("#Nome_Volume").val($(xml).find('Nome_Volume').text());
			$("#Autori_Volume").val($(xml).find('Autori_Volume').text());
			$("#Editore").val($(xml).find('Editore').text());
			$("#Citta_Editore").val($(xml).find('Citta_Editore').text());
			$("#Paese_Editore").val($(xml).find('Paese_Editore').text());
			$("#Pagine_Riferimento").val($(xml).find('Pagine_Riferimento').text());
			$("#ISBN").val($(xml).find('ISBN').text());
			$("#DOI").val($(xml).find('DOI').text());
			$("#Nome_Congresso").val($(xml).find('Nome_Congresso').text());
			var data=$(xml).find('Data_Congresso').text();
			if(data=='1970-01-01')
				data='';
			else if(data.length>0){
				var ar_data=data.split("-");
				data=ar_data[2]+"/"+ar_data[1]+"/"+ar_data[0];
			}
			$("#Data_Congresso").val(data);
			//$("#Data_Congresso").val($(xml).find('Data_Congresso').text());
			$("#Luogo_Congresso").val($(xml).find('Luogo_Congresso').text());
			$("#Rilevanza option[value='"+$(xml).find('Rilevanza').text()+"']").prop("selected", "selected");
			$("#Keywords").val($(xml).find('Keywords').text());
			break;
		//Relazione
		//Nome_Volume
		//Autori_Volume
		//Editore
		//Citta_Editore
		//Paese_Editore
		//Pagine_Riferimento
		//ISBN
		//DOI
		//Nome_Congresso
		//Data_Congresso
		//Luogo_Congresso
		//Rilevanza
		//Keywords
		case 'altro':
			$("#Nome_Volume").val($(xml).find('Nome_Volume').text());
			$("#Numero_Volume").val($(xml).find('Numero_Volume').text());
			$("#Pagine_Riferimento").val($(xml).find('Pagine_Riferimento').text());
			$("#ISBN").val($(xml).find('ISBN').text());
			$("#Keywords").val($(xml).find('Keywords').text());
			break;
		}
}
function downloadFile() {
	window.location.href="./UploadDownloadFileServlet?fileName="+($("#URLFile").val());
}
var elenco_ricercatori_completo="";
function caricaRicercatori() {
	$.ajax({
		type: "POST",
		url: "./CaricaListaRicercatoriDipartimentiServlet",
		data: "",
		dataType: "xml",
		success: function(msg) {
			$("#authors_select").empty();
			elenco_ricercatori_completo="";
			$("<option></option>").appendTo("#authors_select");
			elenco_ricercatori_completo+="<option></option>";
			$($(msg).find('Dipartimento')).each(function(){
				var nomeDipartimento=$(this).find("Nome_Dipartimento").text().replace("'", "\'");
				$('<optgroup label=\"'+nomeDipartimento+'\">').appendTo("#authors_select");
				elenco_ricercatori_completo+='<optgroup label=\"'+nomeDipartimento+'\">';
				($(this).find("Ricercatore")).each(function(){
					var nome=$(this).find("Nome_Ricercatore").text().replace("'", "\'");
					var cognome=$(this).find("Cognome_Ricercatore").text().replace("'", "\'");
					var id=$(this).find("ID_Ricercatore").text().replace("'", "\'");
					if(id==$("#ID_Proprietario").val())
						return;
					$("<option value='"+id+"'>"+cognome+" "+nome+"</option>").appendTo("#authors_select");
					elenco_ricercatori_completo+="<option value='"+id+"'>"+cognome+" "+nome+"</option>";
				});
				
				$("</optgroup><option></option>").appendTo("#authors_select");
				elenco_ricercatori_completo+="</optgroup><option></option>";
			});
		}
	});
}
function creaData(){
	var tipologia=$("#tipologia").val();
	var data="Tipologia="+$("#tipologia").val()+"&ID_Prodotto="+$("#ID_Prodotto").val()+"&Abstract="+$("#Abstract").val()+"&Pubblico="+$(":input[name='Pubblico']:checked").val()+"&Autori="+$("#autori").val();
	$("#ID_Ricercatore option").each(function(){
		data+="&ID_Ricercatore="+$(this).val();
	});
	switch(tipologia){
		case 'articolo libro':
			data=data+"&Keywords="+$("#Keywords").val()+"&Nome_Volume="+$("#Nome_Volume").val()+"&Autori_Volume="+$("#Autori_Volume").val()+"&Editore="+$("#Editore").val()+"&Citta_Editore="+$("#Citta_Editore").val()+"&Paese_Editore="+$("#Paese_Editore").val()+"&Pagine_Riferimento="+$("#Pagine_Riferimento").val()+"&ISBN="+$("#ISBN").val()	+"&DOI="+$("#DOI").val();
			break;
		case 'articolo rivista':
			data=data+"&Nome_Rivista="+$("#Nome_Rivista").val()+"&Numero_Volume="+$("#Numero_Volume").val()+"&Pagine_Riferimento="+$("#Pagine_Riferimento").val()+"&DOI="+$("#DOI").val()+"&Keywords="+$("#Keywords").val();
			break;
		case 'brevetto':
			data=data+"&Proprieta="+$("#Proprieta").val()+"&Numero_Brevetto="+$("#Numero_Brevetto").val()+"&Tipo="+$("#Tipo").val()+"&DOI="+$("#DOI").val()+"&Keywords="+$("#Keywords").val();
			break;
		case 'curatela':
			data=data+"&Nome_Rivista="+$("#Nome_Rivista").val()
			+"&Numero_Volume="+$("#Numero_Volume").val()
			+"&Autori_Volume="+$("#Autori_Volume").val()
			+"&Editore="+$("#Editore").val()
			+"&Citta_Editore="+$("#Citta_Editore").val()
			+"&Pagine_Riferimento="+$("#Pagine_Riferimento").val()
			+"&ISBN="+$("#ISBN").val()
			+"&DOI="+$("#DOI").val()
			+"&Keywords="+$("#Keywords").val();
			break;
		case 'monografia':
			data=data+"&Editore="+$("#Editore").val()
			+"&Citta_Editore="+$("#Citta_Editore").val()
			+"&Paese_Editore="+$("#Paese_Editore").val()
			+"&Pagine_Riferimento="+$("#Pagine_Riferimento").val()
			+"&ISBN="+$("#ISBN").val()
			+"&DOI="+$("#DOI").val()
			+"&Keywords="+$("#Keywords").val();
			break;
		case 'proceeding':
			data=data+"&Nome_Volume="+$("#Nome_Volume").val()
			+"&Autori_Volume="+$("#Autori_Volume").val()
			+"&Editore="+$("#Editore").val()
			+"&Citta_Editore="+$("#Citta_Editore").val()
			+"&Paese_Editore="+$("#Paese_Editore").val()
			+"&Pagine_Riferimento="+$("#Pagine_Riferimento").val()
			+"&ISBN="+$("#ISBN").val()
			+"&DOI="+$("#DOI").val()
			+"&Nome_Congresso="+$("#Nome_Congresso").val()
			+"&Data_Congresso="+$("#Data_Congresso").val()
			+"&Luogo_Congresso="+$("#Luogo_Congresso").val()
			+"&Rilevanza="+$("#Rilevanza").val()
			+"&Relazione="+$("#Relazione").val()
			+"&Keywords="+$("#Keywords").val();
			break;
		case 'altro':
			data=data+"&Nome_Volume="+$("#Nome_Volume").val()
			+"&Numero_Volume="+$("#Numero_Volume").val()
			+"&Pagine_Riferimento="+$("#Pagine_Riferimento").val()
			+"&ISBN="+$("#ISBN").val()
			+"&Keywords="+$("#Keywords").val();
			break;
	}
	return data;
}
function showModificaModalC(){
	$('#statoModifica').val('completo');
	$('#ModalModifica').modal('show');
}
function showModificaModalD(){
	$('#statoModifica').val('draft');
	$('#ModalModifica').modal('show');
}
function modifica(){
	$("#btnSalvaProdCompl").prop('disabled','true');
	$("#btnSalvaProdBozza").prop('disabled','true');
	
	var stato=$("#statoModifica").val();
	if(!verificaCampi(stato)){
		showModal("Errore", "I dati inseriti non soddisfano i requisiti");
		$("#btnSalvaProdCompl").prop('disabled','');
		$("#btnSalvaProdBozza").prop('disabled','');
		return;
	}
	
	var dataParameter=creaData();
	$.ajax({
		type: "POST",
		url: "./ModificaProdottoServlet",
		data: dataParameter+"&Stato="+stato,
		dataType: "text",
		success: function(msg){
			if(msg=='OK') {
				showModal("Modifica prodotto", "Prodotto modificato con successo");
				setTimeout(function(){location.reload(true);}, 2000);
			}
			else
				showModal("Modifica prodotto", "Errore durante la modifica");
			$("#btnSalvaProdCompl").prop('disabled','');
			$("#btnSalvaProdBozza").prop('disabled','');
		}
	});
}
function abilitaCampi() {
	//$("#formInserimentoProdotto").prop('action', './ModificaProdottoServlet');
	$("#titoloPagina").text("Modifica Prodotto");
	$("#authors_select").prop('disabled', '');
	
	var autori_select="<label class='col-xs-offset-2 col-xs-2 control-label'>Autori disponibili</label>"
		+"<div class='col-xs-4' >"
		+"<select class='form-control' id='authors_select' contenteditable='false' >"
		+"</select>"
		+"</div>"
		+"<span class='help-inline'></span>"
        +"<button type='button' class='btn' onclick='aggiungiAutore()' id='btnAggiungi'>Aggiungi</button>"
		+"<span class='help-inline'></span>";
	
	$(autori_select).appendTo("#divAutoriDisponibili");
	
	var id_ricercatore="<label class='col-xs-offset-2 col-xs-2 control-label'>Autori selezionati</label>"
        +"<div class='col-xs-4'>"
		+"<select class='form-control' id='ID_Ricercatore' name='ID_Ricercatore' multiple='multiple' disabled></select></div>"
		+"<span class='help-inline'></span>"
		+"<div id='divBtnRimuovi'>"
		+"<button type='button' class='btn' onclick='rimuoviAutore()' id='btnRimuovi'>Rimuovi</button>"
		+"</div>";
	
	$(id_ricercatore).appendTo("#divID_Ricercatore");
	
	for(var i=0;i<id_ricercatori.length;i++){
		var ricercatore=$(elenco_ricercatori_completo).find("option[value='"+id_ricercatori[i]+"']");
		$(ricercatore).appendTo("#ID_Ricercatore");
	}
	
	
	$("#ID_Ricercatore").prop('disabled', '');
	$("#autori").prop('disabled', '');
	$("#Anno_Pubblicazione").prop('disabled', '');
	$("#Abstract").prop('disabled', '');
	$("#Pubblico1").prop('disabled', '');
	$("#Pubblico2").prop('disabled', '');
	
	$("#Keywords").prop('disabled', '');
	$("#Nome_Rivista").prop('disabled', '');
	$("#Numero_Volume").prop('disabled', '');
	$("#Nome_Volume").prop('disabled', '');
	$("#Relazione").prop('disabled', '');
	$("#Nome_Congresso").prop('disabled', '');
	$("#Data_Congresso").prop('disabled', '');
	$("#Luogo_Congresso").prop('disabled', '');
	$("#Rilevanza").prop('disabled', '');
	$("#Proprieta").prop('disabled', '');
	$("#Numero_Brevetto").prop('disabled', '');
	$("#Tipo").prop('disabled', '');
	$("#Nome_Articolo").prop('disabled', '');
	$("#Autori_Volume").prop('disabled', '');
	$("#Editore").prop('disabled', '');
	$("#Citta_Editore").prop('disabled', '');
	$("#Paese_Editore").prop('disabled', '');
	$("#ISBN").prop('disabled', '');
	$("#DOI").prop('disabled', '');
	$("#Pagine_Riferimento").prop('disabled', '');
	
	$("#historyBack").remove();
	$("<button class='btn btn-warning' onclick='location.reload(true)' id='historyBack'>Indietro</button>").appendTo("#divBack");
	$("#btnModProd").remove();
	$("#btnDelProd").remove();
	$("#btnPrintProd").remove();
	$("#divAutoriRicercatori").remove();
	
	var btnCompleto="<button id='btnSalvaProdCompl' type='button' class='btn btn-warning' onclick='showModificaModalC()'>Salva 'Completo'</button>";
	var btnBozza="<button id='btnSalvaProdBozza' type='button' class='btn btn-warning col-xs-offset-2' onclick='showModificaModalD()'>Salva 'Bozza'</button>";
	var btnStampa="<button id='btnPrintProd' value='' type='button' class='btn btn-primary col-xs-offset-2' onclick='downloadFile()'>Stampa prodotto</button>";
	
	$(btnCompleto).appendTo("#bottoni-azione");
	$(btnBozza).appendTo("#bottoni-azione");
	$(btnStampa).appendTo("#bottoni-azione");

	refreshAutori();
	createTooltips();
}
function disabilitaCampi() {
	$("#Titolo").prop('disabled', 'true');
	$("#authors_select").prop('disabled', 'true');
	$("#ID_Ricercatore").prop('disabled', 'true');
	$("#autori").prop('disabled', 'true');
	$("#Anno_Pubblicazione").prop('disabled', 'true');
	$("#Abstract").prop('disabled', 'true');
	$("#tipologia").prop('disabled', 'true');
	$("#Pubblico1").prop('disabled', 'true');
	$("#Pubblico2").prop('disabled', 'true');
	$("#file").prop('disabled', 'true');
	
	$("#Keywords").prop('disabled', 'true');
	$("#Nome_Rivista").prop('disabled', 'true');
	$("#Numero_Volume").prop('disabled', 'true');
	$("#Nome_Volume").prop('disabled', 'true');
	$("#Relazione").prop('disabled', 'true');
	$("#Nome_Congresso").prop('disabled', 'true');
	$("#Data_Congresso").prop('disabled', 'true');
	$("#Luogo_Congresso").prop('disabled', 'true');
	$("#Rilevanza").prop('disabled', 'true');
	$("#Proprieta").prop('disabled', 'true');
	$("#Numero_Brevetto").prop('disabled', 'true');
	$("#Tipo").prop('disabled', 'true');
	$("#Nome_Articolo").prop('disabled', 'true');
	$("#Autori_Volume").prop('disabled', 'true');
	$("#Editore").prop('disabled', 'true');
	$("#Citta_Editore").prop('disabled', 'true');
	$("#Paese_Editore").prop('disabled', 'true');
	$("#ISBN").prop('disabled', 'true');
	$("#DOI").prop('disabled', 'true');
	$("#Pagine_Riferimento").prop('disabled', 'true');
	//$("#").prop('disabled', '');
}
function createTooltips() {
	$("#Titolo").tooltip({		              
        'selector': '',
		'title': 'Titolo della ricerca. Un testo di lunghezza compresa tra 2 e 100 caratteri e non deve iniziare con un carattere speciale',
		'trigger': 'focus',
        'placement': 'top'
    });
	$("#authors_select").tooltip({		              
        'selector': '',
		'title': 'Lista di ricercatori dell\'Ateneo',
		'trigger': 'hover',
        'placement': 'top'
    });
	$("#btnAggiungi").tooltip({		              
        'selector': '',
		'title': 'Cliccare per aggiungere un ricercatore agli autori.',
		'trigger': 'hover',
        'placement': 'right'
    });
	$("#ID_Ricercatore").tooltip({		              
        'selector': '',
		'title': 'Ricercatori selezionati come autori.',
		'trigger': 'hover',
        'placement': 'top'
    });
	$("#btnRimuovi").tooltip({		              
        'selector': '',
		'title': 'Cliccare per rimuovedere dei ricercatori dalla lista degli autori.',
		'trigger': 'hover',
        'placement': 'right'
    });
	$("#autori").tooltip({		              
        'selector': '',
		'title': 'Altri autori non facenti parte dell\'Ateneo. Una stringa di lunghezza massima di 300 caratteri. Più autori vanno separati da virgola',
		'trigger': 'focus',
        'placement': 'top'
    });
	$("#Anno_Pubblicazione").tooltip({		              
        'selector': '',
		'title': 'Anno della pubblicazione compreso tra 2013 e 2014. Stringa numerica di 4 caratteri',
		'trigger': 'focus',
        'placement': 'top'
    });
	$("#Abstract").tooltip({		              
        'selector': '',
		'title': 'Un testo di lunghezza massima di 1000 caratteri',
		'trigger': 'focus',
        'placement': 'top'
    });
	$("#tipologia").tooltip({		              
        'selector': '',
		'title': 'Tipo di ricerca. Selezionare un tipo dall\'elenco',
		'trigger': 'hover',
        'placement': 'top'
    });
	$("#Pubblico").tooltip({		              
        'selector': '',
		'title': 'Visibilità  del prodotto',
		'trigger': 'hover',
        'placement': 'top'
    });
	/*
	$("#file").tooltip({		              
        'selector': '',
		'title': 'Il nome del file deve avere una lunghezza tra 15 e 100 caratteri.',
		'trigger': 'hover',
        'placement': 'top'
    });
    */
	$("#Keywords").tooltip({		              
        'selector': '',
		'title': 'Lunghezza massima di 300 caratteri. Ogni parola deve essere separata da virgola.',
		'trigger': 'focus',
        'placement': 'top'
    });
	$("#Nome_Rivista").tooltip({		              
        'selector': '',
		'title': 'Testo di lunghezza massima di 100 caratteri',
		'trigger': 'focus',
        'placement': 'top'
    });
	$("#Numero_Volume").tooltip({		              
        'selector': '',
		'title': 'Stringa numerica di lunghezza massima di 50 caratteri',
		'trigger': 'focus',
        'placement': 'top'
    });
	$("#Nome_Volume").tooltip({		              
        'selector': '',
		'title': 'Testo di lunghezza compresa tra 10 e 100 caratteri',
		'trigger': 'focus',
        'placement': 'top'
    });
	$("#Relazione").tooltip({		              
        'selector': '',
		'title': 'Selezionare un tipo di relazione dalla lista',
		'trigger': 'hover',
        'placement': 'top'
    });
	$("#Nome_Congresso").tooltip({		              
        'selector': '',
		'title': 'Testo di lunghezza compresa tra 2 e 100 caratteri',
		'trigger': 'focus',
        'placement': 'top'
    });
	$("#Data_Congresso").tooltip({		              
        'selector': '',
		'title': 'Data del congresso. Formato: GG/MM/AAAA',
		'trigger': 'focus',
        'placement': 'top'
    });
	$("#Luogo_Congresso").tooltip({		              
        'selector': '',
		'title': 'Testo di lunghezza compresa tra 2 e 100 caratteri',
		'trigger': 'focus',
        'placement': 'top'
    });
	$("#Rilevanza").tooltip({		              
        'selector': '',
		'title': 'Selezionare un\'opzione dalla lista',
		'trigger': 'hover',
        'placement': 'top'
    });
	$("#Proprieta").tooltip({		              
        'selector': '',
		'title': 'Un testo di lunghezza compresa tra 2 e 300 caratteri',
		'trigger': 'focus',
        'placement': 'top'
    });
	$("#Numero_Brevetto").tooltip({		              
        'selector': '',
		'title': 'Stringa numerica di lunghezza compresa tra 1 e 20 caratteri',
		'trigger': 'focus',
        'placement': 'top'
    });
	$("#Tipo").tooltip({		              
        'selector': '',
		'title': 'Selezionare un\'opzione dalla lista',
		'trigger': 'hover',
        'placement': 'top'
    });
	$("#Nome_Articolo").tooltip({		              
        'selector': '',
		'title': 'Un testo di lunghezza compresa tra 2 e 100 caratteri',
		'trigger': 'focus',
        'placement': 'top'
    });
	$("#Autori_Volume").tooltip({		              
        'selector': '',
		'title': 'Un testo di lunghezza compresa tra 2 e 100 caratteri. Autori diversi devono essere separati da virgola.',
		'trigger': 'focus',
        'placement': 'top'
    });
	$("#Editore").tooltip({		              
        'selector': '',
		'title': 'Un testo di lunghezza compresa tra 2 e 100 caratteri',
		'trigger': 'focus',
        'placement': 'top'
    });
	$("#Citta_Editore").tooltip({		              
        'selector': '',
		'title': 'Un testo di lunghezza compresa tra 2 e 100 caratteri',
		'trigger': 'focus',
        'placement': 'top'
    });
	$("#Paese_Editore").tooltip({		              
        'selector': '',
		'title': 'Un testo di lunghezza compresa tra 2 e 100 caratteri',
		'trigger': 'focus',
        'placement': 'top'
    });
	$("#ISBN").tooltip({		              
        'selector': '',
		'title': 'Stringa numerica di 13 caratteri',
		'trigger': 'focus',
        'placement': 'top'
    });
	$("#DOI").tooltip({		              
        'selector': '',
		'title': 'Stringa di lunghezza compresa tra 15 e 20 caratteri',
		'trigger': 'focus',
        'placement': 'top'
    });
	$("#Pagine_Riferimento").tooltip({		              
        'selector': '',
		'title': 'Stringa numeriche di lunghezza massima di 50 caratteri. Ogni numero deve essere separato dall\'altro da un trattino.',
		'trigger': 'focus',
        'placement': 'top'
    });
	/*
	$("#").tooltip({		              
        'selector': '',
		'title': 'Un testo di lunghezza compresa tra 2 e 100 caratteri',
		'trigger': 'focus',
        'placement': 'right'
    });
	*/
}
function refreshAutori() {
	$("#authors_select").empty();
	$(elenco_ricercatori_completo).appendTo("#authors_select");
	$("#ID_Ricercatore option").each(function(){
		$("#authors_select option[value='"+$(this).val()+"']").remove();
	});
}
function aggiungiAutore() {
	var selectedAutore=$("#authors_select option:selected");
	if($(selectedAutore).val()==''){
		return;
	}
	var select=$("#ID_Ricercatore");
	$("#authors_select option:selected").remove();
	$(selectedAutore).appendTo(select);
}
function rimuoviAutore() {
	var selectedAuthors=$("#ID_Ricercatore option:selected");
	//var selection=$("#authors_select");
	$(selectedAuthors).remove();
	refreshAutori();
	//$(selectedAuthors).appendTo(selection);
}
/*
function inserisciProdotto(){
	var form=$("#formInserimentoProdotto");
	if(confirm("Vuoi salvare il prodotto in stato \"Completo\"?")){
		if(verificaCampi('completo')){
			var stato="<input class='form-control' type='hidden' value='Completo' name='Stato'/>";
			$(stato).appendTo(form);
			form.submit();
		}
		else {
			showModal("Errore durante l'inserimento", "Il prodotto non ha superato le verifiche per l'inserimento in stato completo.");
		}
	}
}
*/
/*
function salvaProdotto() {

	$("#ID_Ricercatore option").prop("selected", "selected");
	
	var form=$("form[name='formInserimentoProdotto']");
	if(confirm("Vuoi salvare il prodotto in stato Bozza?")){
		if(verificaCampi('bozza')){
			var stato="<input class='form-control' type='hidden' value='Draft' name='Stato'/>";
			$(stato).appendTo(form);
			//$("form[name='formInserimentoProdotto']").submit();
			modifica();
		}
		else {
			showModal("Errore durante l'inserimento", "Il prodotto non ha superato le verifiche per l'inserimento in stato bozza.");
		}
	}
}
*/
function isDate(input) {
	if(input == 'undefined' || input == null) {
		return false;
	}
	else {
		var data=input.split("\/");
		if(data.length == 3) {
			var giorno=data[0];
			var mese=data[1];
			var anno=data[2];
			
			if(anno.length != 4)
				return false;
			
			if($.isNumeric(anno)) {
				anno=parseInt(anno);
				if($.isNumeric(mese)){
					mese=parseInt(mese);
					if(mese<=0 || mese >12)
						return false;
						
					if($.isNumeric(giorno)){
						giorno=parseInt(giorno);
						if(giorno>0 && giorno<=31){
							switch(mese) {
								case 2:
									if(anno%4 == 0) {
										if(giorno<=29)
											return true;
										else
											return false;
									}
									else {
										if(giorno <=28)
											return true;
										else
											return false;
									}
									break;
								case 4:
								case 6:
								case 9:
								case 11:
									if(giorno<=30)
										return true;
									else
										return false;
								default:
									return true;
							}
						}
					}
				}
			}
		}
		else {
			return false;
		}
	}
	return false;
}
function verificaCampi(verifica) {
	if(!verificaCampo($("#Titolo"), verifica) 
		//|| !verificaCampo($("#ID_Ricercatore"), verifica)
		|| !verificaCampo($("#autori"), verifica) 
		|| !verificaCampo($("#Anno_Pubblicazione"), verifica) 
		|| !verificaCampo($("#Abstract"), verifica) 
		|| !verificaCampo($("#tipologia"), verifica)
		|| !verificaCampo($(":input[name='Pubblico']:checked"), verifica)
	)
	{	
		return false;
	}
	
	if($("#autore_proprietario").length>0){
		if(!verificaCampo($("#autore_proprietario")))
			return false;
	}
	
	var s_tipo=$("#tipologia").val();
	if(s_tipo=='articolo libro'){
		return (verificaCampo($("#Nome_Volume"), verifica) 
				&& verificaCampo($("#Autori_Volume"), verifica) 
				&& verificaCampo($("#Editore"), verifica) 
				&& verificaCampo($("#Citta_Editore"), verifica) 
				&& verificaCampo($("#Paese_Editore"), verifica) 
				&& verificaCampo($("#Pagine_Riferimento"), verifica) 
				&& verificaCampo($("#ISBN"), verifica) 
				&& verificaCampo($("#DOI"), verifica) 
				&& verificaCampo($("#Keywords"), verifica));
		//Nome_Volume
		//Autori_Volume
		//Editore
		//Citta_Editore
		//Paese_Editore
		//Pagine_Riferimento
		//ISBN
		//DOI
		//Keywords
	}
	else if (s_tipo=='articolo rivista'){
		return verificaCampo($("#Nome_Rivista"), verifica)
			&& verificaCampo($("#Numero_Volume"), verifica)
			&& verificaCampo($("#Pagine_Riferimento"), verifica)
			&& verificaCampo($("#DOI"), verifica)
			&& verificaCampo($("#Keywords"), verifica);
		//Nome_Rivista
		//Numero_Volume
		//Pagine_Riferimento
		//DOI
		//Keywords
	}
	else if (s_tipo=='brevetto'){
		return verificaCampo($("#Proprieta"), verifica)
			&& verificaCampo($("#Numero_Brevetto"), verifica)
			&& verificaCampo($("#Tipo"), verifica)
			&& verificaCampo($("#DOI"), verifica)
			&& verificaCampo($("#Keywords"), verifica);
		//Proprieta
		//Numero_Brevetto
		//Tipo
		//DOI
		//Keywords
	}
	else if (s_tipo=='curatela'){
		return verificaCampo($("#Nome_Rivista"), verifica)
			&& verificaCampo($("#Numero_Volume"), verifica)
			&& verificaCampo($("#Autori_Volume"), verifica)
			&& verificaCampo($("#Editore"), verifica)
			&& verificaCampo($("#Citta_Editore"), verifica)
			&& verificaCampo($("#Pagine_Riferimento"), verifica)
			&& verificaCampo($("#ISBN"), verifica)
			&& verificaCampo($("#DOI"), verifica)
			&& verificaCampo($("#Keywords"), verifica);
		//Nome_Rivista
		//Numero_Volume
		//Autori_Volume
		//Editore
		//Citta_Editore
		//Pagine_Riferimento
		//ISBN
		//DOI
		//Keywords
	}
	else if (s_tipo=='monografia'){
		return verificaCampo($("#Editore"), verifica)
			&& verificaCampo($("#Citta_Editore"), verifica)
			&& verificaCampo($("#Paese_Editore"), verifica)
			&& verificaCampo($("#Pagine_Riferimento"), verifica)
			&& verificaCampo($("#ISBN"), verifica)
			&& verificaCampo($("#DOI"), verifica)
			&& verificaCampo($("#Keywords"), verifica);
		//Editore
		//Citta_Editore
		//Paese_Editore
		//Pagine_Riferimento
		//ISBN
		//DOI
		//Keywords
	}
	else if (s_tipo=='proceeding'){
		return verificaCampo($("#Relazione"), verifica)
			&& verificaCampo($("#Nome_Volume"), verifica)
			&& verificaCampo($("#Autori_Volume"), verifica)
			&& verificaCampo($("#Editore"), verifica)
			&& verificaCampo($("#Citta_Editore"), verifica)
			&& verificaCampo($("#Paese_Editore"), verifica)
			&& verificaCampo($("#Pagine_Riferimento"), verifica)
			&& verificaCampo($("#ISBN"), verifica)
			&& verificaCampo($("#DOI"), verifica)
			&& verificaCampo($("#Nome_Congresso"), verifica)
			&& verificaCampo($("#Data_Congresso"), verifica)
			&& verificaCampo($("#Luogo_Congresso"), verifica)
			&& verificaCampo($("#Rilevanza"), verifica)
			&& verificaCampo($("#Keywords"), verifica);
		//Relazione
		//Nome_Volume
		//Autori_Volume
		//Editore
		//Citta_Editore
		//Paese_Editore
		//Pagine_Riferimento
		//ISBN
		//DOI
		//Nome_Congresso
		//Data_Congresso
		//Luogo_Congresso
		//Rilevanza
		//Keywords
	}
	else if (s_tipo=='altro'){
		return verificaCampo($("#Nome_Articolo"), verifica)
			&& verificaCampo($("#Numero_Volume"), verifica)
			&& verificaCampo($("#Pagine_Riferimento"), verifica)
			&& verificaCampo($("#ISBN"), verifica)
			&& verificaCampo($("#Keywords"), verifica);
		//Nome_Articolo
		//Numero_Volume
		//Pagine_Riferimento
		//ISBN
		//Keywords
	}
	else {
		//VUOTO
		return false;
	}
}
function verificaCampo(campo, verifica){
	var nomeCampo=$(campo).attr('name');
	if(verifica==null || verifica=='undefined')
		verifica='completo';
	
	switch(nomeCampo){
		case 'Abstract':
			if(verifica=='completo'){
				if($(campo).val().length==0 || $(campo).val().length>1000){
					$("#Abstract").addClass("has-error");
					$("#divAbstract").addClass("has-error");
					return false;
				}
			}
			else {
				if($(campo).val().length>0)
					return verificaCampo(campo, 'completo');
			}
			$("#Abstract").removeClass("has-error");
			$("#divAbstract").removeClass("has-error");
			break;
		case 'Anno_Pubblicazione':
			if($(campo).val().length!=4 || !$.isNumeric($(campo).val())){
				$("#Anno_Pubblicazione").addClass("has-error");
				$("#divAnnoPubblicazione").addClass("has-error");
				return false;
			}
			else {
				if($(campo).val()=='2013' || $(campo).val()=='2014'){}
				else {
					$("#Anno_Pubblicazione").addClass("has-error");
					$("#divAnnoPubblicazione").addClass("has-error");
					return false;
				}
			}
			$("#Anno_Pubblicazione").removeClass("has-error");
			$("#divAnnoPubblicazione").removeClass("has-error");
			break;
		case "Autore_Proprietario":
			if($(campo).val().length==0){
				$("#autore_proprietario").addClass("has-error");
				$("#divProprietario").addClass("has-error");
				return false;
			}
			$("#autore_proprietario").removeClass("has-error");
			$("#divProprietario").removeClass("has-error");
			break;
		case 'Autori':
			//var pattern =/^[A-Za-z]*[[^\d\W0-9]A-Za-z'àèéìòù]{2,50} [[^\d\W]A-Za-z'àèéìòù]{2,50}/;
			var pattern=/[A-Za-z']{2,50} [A-Za-z']{2,50}/;
			if(verifica=='completo'){
				if($(campo).val().length==1 || $(campo).val().length>300){
					$("#autori").addClass("has-error");
					$("#divAltriAutori").addClass("has-error");
					return false;
				}
				else if($(campo).val().length==0){
					$("#autori").removeClass("has-error");
					$("#divAltriAutori").removeClass("has-error");
					return true;
				}
				else {
					if($(campo).val().indexOf(",")>0){
						var nomi=$(campo).val().split(",");
						var pattern_special=/\W/;
						for(var i=0;i<nomi.length;i++){
							if(!pattern.test(nomi[i].trim())){
								$("#autori").addClass("has-error");
								$("#divAltriAutori").addClass("has-error");
								return false;
							}
							else {
								var nomi_split=nomi[i].split(" ");
								for(var j=0;j<nomi_split.length;j++){
									if(pattern_special.test(nomi_split[j])){
										$("#autori").addClass("has-error");
										$("#divAltriAutori").addClass("has-error");
										return false;
									}
								}
							}
						}
					}
					else {
						if($(campo).val().indexOf(" ")>0){
							var nomi=$(campo).val().split(" ");
							var pattern_special=/\W/;
							for(var i=0;i<nomi.length;i++){
								if(pattern_special.test(nomi[i])){
									$("#autori").addClass("has-error");
									$("#divAltriAutori").addClass("has-error");
									return false;
								}
							}
						}
						else{
							$("#autori").addClass("has-error");
							$("#divAltriAutori").addClass("has-error");
							return false;
						}	
					}
				}
			}
			else {
				if($(campo).val().length>0){
					return verificaCampo(campo,"completo");
				}
			}
			$("#autori").removeClass("has-error");
			$("#divAltriAutori").removeClass("has-error");
			break;
		case 'Autori_Volume':
			var pattern=/[A-Za-z']{2,50} [A-Za-z']{2,50}/;
			if(verifica=='completo'){
				if($(campo).val().length==1 || $(campo).val().length>300){
					$("#Autori_Volume").addClass("has-error");
					$("#divAutoriVolume").addClass("has-error");
					return false;
				}
				else if($(campo).val().length==0){
					$("#Autori_Volume").removeClass("has-error");
					$("#divAutoriVolume").removeClass("has-error");
					return true;
				}
				else {
					if($(campo).val().indexOf(",")>0){
						var nomi=$(campo).val().split(",");
						var pattern_special=/\W/;
						for(var i=0;i<nomi.length;i++){
							if(!pattern.test(nomi[i].trim())){
								$("#Autori_Volume").addClass("has-error");
								$("#divAutoriVolume").addClass("has-error");
								return false;
							}
							else {
								var nomi_split=nomi[i].split(" ");
								for(var j=0;j<nomi_split.length;j++){
									if(pattern_special.test(nomi_split[j])){
										$("#Autori_Volume").addClass("has-error");
										$("#divAutoriVolume").addClass("has-error");
										return false;
									}
								}
							}
						}
					}
					else {
						if($(campo).val().indexOf(" ")>0){
							var nomi=$(campo).val().split(" ");
							var pattern_special=/\W/;
							for(var i=0;i<nomi.length;i++){
								if(pattern_special.test(nomi[i])){
									$("#Autori_Volume").addClass("has-error");
									$("#divAutoriVolume").addClass("has-error");
									return false;
								}
							}
						}
						else{
							$("#Autori_Volume").addClass("has-error");
							$("#divAutoriVolume").addClass("has-error");
							return false;
						}	
					}
				}
			}
			else {
				if($(campo).val().length>0)
					return verificaCampo(campo,"completo");
			}
			$("#Autori_Volume").removeClass("has-error");
			$("#divAutoriVolume").removeClass("has-error");
			break;
		case 'Citta_Editore':
			var pattern=/^[A-Za-z][A-Za-z ]{0,100}[a-zA-Z]$/;
			if(($(campo).val().length>0 && $(campo).val().length<2) || $(campo).val().length>100){
				$("#divCittaEditore").addClass('has-error');
				$("#Citta_Editore").addClass('has-error');
				return false;
			} 
			if($(campo).val().length>0){
				if(!pattern.test($(campo).val())){
					$("#divCittaEditore").addClass('has-error');
					$("#Citta_Editore").addClass('has-error');
					return false;
				}
			}
			$("#divCittaEditore").removeClass('has-error');
			$("#Citta_Editore").removeClass('has-error');
			break;
		case 'Data_Congresso':
			if(verifica=='completo'){
				if(!isDate($(campo).val())){
					$("#Data_Congresso").addClass("has-error");
					$("#divDataCongresso").addClass("has-error");
					return false;
				}
			}
			else {
				if($(campo).val().length>0)
					return verificaCampo(campo, 'completo');
			}
			$("#Data_Congresso").removeClass("has-error");
			$("#divDataCongresso").removeClass("has-error");
			break;
		case 'DOI':
			var pattern=/10.[.0-9]{11,16}[0-9]$/;
			if(verifica=='completo'){
				if(!pattern.test($(campo).val())){
					$("#DOI").addClass("has-error");
				    $("#divDOI").addClass("has-error");
					 return false;
				}
			}
			else {
				if($(campo).val().length>0)
					return verificaCampo(campo, 'completo');
			}
			$("#DOI").removeClass("has-error");
		    $("#divDOI").removeClass("has-error");
			break;
		case 'Editore':
			var pattern=/[\w]{2,100}/;
			if(verifica=='completo'){
				if($(campo).val().length<2 || $(campo).val().length>100){
					$("#Editore").addClass("has-error");
				    $("#divEditore").addClass("has-error");	
					return false;
				}
				if(!pattern.test($(campo).val())){
					$("#Editore").addClass("has-error");
				    $("#divEditore").addClass("has-error");	
					return false;
				}
			}
			else {
				if($(campo).val().length>0)
					return verificaCampo(campo, 'completo');
			}
			$("#Editore").removeClass("has-error");
			$("#divEditore").removeClass("has-error");
			break;
		case "fileName":
			if($("#file").val().length==0){
				$("#file").addClass("has-error");
				$("#divFile").addClass("has-error");
				return false;
			}
			$("#file").removeClass("has-error");
			$("#divFile").removeClass("has-error");
			break;
		case 'ISBN':
			var isbn=$(campo).val();
			if(verifica=='completo'){
				if(isbn.length==13){
					if(!$.isNumeric(isbn)){
						$("#ISBN").addClass("has-error");
						$("#divISBN").addClass("has-error");
						return false;
					}
				}
				else {
					$("#ISBN").addClass("has-error");
					$("#divISBN").addClass("has-error");
					return false;
				}
			}
			else {
				if(isbn.length>0)
					return verificaCampo(campo, 'completo');
			}
			
			$("#ISBN").removeClass("has-error");
			$("#divISBN").removeClass("has-error");
			break;
		case 'Keywords':
			if(verifica=='completo'){
				var pattern=/[A-Za-z]/;
				if($(campo).val().length<2 || $(campo).val().length>300){
					$("#Keywords").addClass("has-error");
					$("#divKeywords").addClass("has-error");
					return false;
				}
				if($(campo).val().indexOf(" ")>0){ //il testo contiene spazi
					$("#Keywords").addClass("has-error");
					$("#divKeywords").addClass("has-error");
					return false;
				}
				if(!pattern.test($(campo).val().slice(-1))){ //l'ultimo carattere non è un carattere
					$("#Keywords").addClass("has-error");
					$("#divKeywords").addClass("has-error");
					return false;
				}
				if($(campo).val().indexOf(",")>0){
					var special_chars=/\W/;
					var nomi=$(campo).val().split(",");
					for(var i=0;i<nomi.length;i++){
						if(special_chars.test(nomi[i])){
							$("#Keywords").addClass("has-error");
							$("#divKeywords").addClass("has-error");
							return false;
						}
					}
				}
				else {
					if(/\W/.test($(campo).val())){
						$("#Keywords").addClass("has-error");
						$("#divKeywords").addClass("has-error");
						return false;
					}
				}
			}
			$("#Keywords").removeClass("has-error");
			$("#divKeywords").removeClass("has-error");
			break;
		case 'Luogo_Congresso':
			var pattern=/[a-zA-Z ]{2,100}/;
			if(verifica=='completo'){
				if(!pattern.test($(campo).val())){
					$("#Luogo_Congresso").addClass("has-error");
					$("#divLuogoCongresso").addClass("has-error");
					return false;
				}
			}
			else {
				if($(campo).val().length>0)
					return verificaCampo(campo, 'completo');
			}
			$("#Luogo_Congresso").removeClass("has-error");
			$("#divLuogoCongresso").removeClass("has-error");
			break;
		case 'Nome_Congresso':
			var pattern=/[a-zA-Z ]{2,100}/;
			if(verifica=='completo'){
				if(!pattern.test($(campo).val())){
					$("#Nome_Congresso").addClass("has-error");
					$("#divNomeCongresso").addClass("has-error");
					return false;
				}
			}
			else {
				if($(campo).val().length>0)
					return verificaCampo(campo, 'completo');
			}
			$("#Nome_Congresso").removeClass("has-error");
			$("#divNomeCongresso").removeClass("has-error");
			break;
		case 'Nome_Rivista':
			var pattern=/^[a-zA-Z0-9][\W\w]{1,100}/;
			if(verifica=='completo'){
				if($(campo).val()<2 ||$(campo).val().length>100){
					$("#Nome_Rivista").addClass("has-error");
					$("#divNomeRivista").addClass("has-error");
					return false;
				}
				if(!pattern.test($(campo).val())){
					$("#Nome_Rivista").addClass("has-error");
					$("#divNomeRivista").addClass("has-error");
					return false;
				}
			}
			else {
				if($(campo).val().length>0){
					return verificaCampo(campo, 'completo');
				}
			}
			$("#Nome_Rivista").removeClass("has-error");
			$("#divNomeRivista").removeClass("has-error");
			break;
		case 'Nome_Volume':
			var pattern=/[\w\W]{10,100}/;
			if(verifica=='completo'){
				if($(campo).val().length<10 || $(campo).val().length>100){
					$("#Nome_Volume").addClass("has-error");
				    $("#divNomeVolume").addClass("has-error");
				    return false;
				}
				
				var pattern_special=/[\W_]/;
				if(pattern_special.test($(campo).val()[0])){
					$("#Nome_Volume").addClass("has-error");
				    $("#divNomeVolume").addClass("has-error");
				    return false;
				}
				if(!pattern.test($(campo).val())){
					$("#Nome_Volume").addClass("has-error");
				    $("#divNomeVolume").addClass("has-error");
				    return false;
				}
			}
			else {
				if($(campo).val().length>0){
					return verificaCampo(campo, 'completo');
				}
			}
			$("#Nome_Volume").removeClass("has-error");
		    $("#divNomeVolume").removeClass("has-error");
			break;
		case 'Numero_Brevetto':
			if(verifica=='completo'){
				if($(campo).val().length>20){
					$("#Numero_Brevetto").addClass("has-error");
					$("#divNumeroBrevetto").addClass("has-error");
					return false;
				} 
				if($(campo).val().length==0 || !$.isNumeric($(campo).val())){
					$("#Numero_Brevetto").addClass("has-error");
					$("#divNumeroBrevetto").addClass("has-error");
					return false;
				}
			}
			$("#Numero_Brevetto").removeClass("has-error");
			$("#divNumeroBrevetto").removeClass("has-error");
			break;
		case 'Numero_Volume':
			if(verifica=='completo'){
				if($(campo).val().length==0 || $(campo).val().length>50 || !$.isNumeric($(campo).val())){
					$("#Numero_Volume").addClass("has-error");
					$("#divNumeroVolume").addClass("has-error");
					return false;
				}
			}
			$("#Numero_Volume").removeClass("has-error");
			$("#divNumeroVolume").removeClass("has-error");
			break;
		case 'Paese_Editore':
			var pattern=/[a-zA-Z ]{2,100}/;
			if(verifica=='completo'){
				if($(campo).val()<2 || $(campo).val().length>100){
					$("#Paese_Editore").addClass("has-error");
					$("#divPaeseEditore").addClass("has-error");
					return false;
				}
				if(!pattern.test($(campo).val())){
					$("#Paese_Editore").addClass("has-error");
					$("#divPaeseEditore").addClass("has-error");
					return false;
				}
			}
			else {
				if($(campo).val().length>0)
					return verificaCampo(campo, 'completo');
			}
			$("#Paese_Editore").removeClass("has-error");
			$("#divPaeseEditore").removeClass("has-error");
			break;
		case 'Pagine_Riferimento':
			var pattern=/[0-9\/-]{1,50}/;
			if(verifica=='completo'){
				if($(campo).val().length>50){
					$("#Pagine_Riferimento").addClass("has-error");
				    $("#divPagineRiferimento").addClass("has-error");
					return false;
				}
				if(/[a-zA-Z\s]/.test($(campo).val())){
					$("#Pagine_Riferimento").addClass("has-error");
				    $("#divPagineRiferimento").addClass("has-error");
					return false;
				}
				if(!pattern.test($(campo).val())){
					$("#Pagine_Riferimento").addClass("has-error");
				    $("#divPagineRiferimento").addClass("has-error");
					return false;
				}
				else {
					var pattern_last=/[0-9]/;
					var lastChar=$(campo).val().slice(-1);
					if(!pattern_last.test(lastChar)){
						$("#Pagine_Riferimento").addClass("has-error");
					    $("#divPagineRiferimento").addClass("has-error");
						return false;
					}
				}
			}
			else {
				if($(campo).val().length>0)
					return verificaCampo(campo, 'completo');
			}
			$("#Pagine_Riferimento").removeClass("has-error");
		    $("#divPagineRiferimento").removeClass("has-error");
			break;
		case 'Proprieta':
			var pattern=/^[\w][\w\W]{1,300}/;
			if(verifica=='completo'){
				if($(campo).val().length<2 || $(campo).val().length>300){
					$("#Proprieta").addClass("has-error");
				    $("#divProprieta").addClass("has-error");
					return false;
				}
				if(!pattern.test($(campo).val())){
					$("#Proprieta").addClass("has-error");
				    $("#divProprieta").addClass("has-error");
					return false;
				}
			}
			else {
				if($(campo).val().length>0){
					return validaCampo(campo, 'completo');
				}
			}
			$("#Proprieta").removeClass("has-error");
		    $("#divProprieta").removeClass("has-error");
			break;
		case 'Relazione':
			if(verifica=='completo'){
				switch($(campo).val()){
				case 'invito':
				case 'contributo':
					break;
				default:
					$("#Relazione").addClass("has-error");
					$("#divRelazione").addClass("has-error");
					return false;
				}
			}
			else {
				if($(campo).val().length>0)
					return verificaCampo(campo, 'completo');
			}	
			$("#Relazione").removeClass("has-error");
			$("#divRelazione").removeClass("has-error");
			break;
		case 'Rilevanza':
			if(verifica=='completo'){
				switch($(campo).val()){
					case 'nazionale':
					case 'internazionale':
						break;
					default:
						$("#Rilevanza").addClass("has-error");
						$("#divRilevanza").addClass("has-error");
						return false;
				}
			}
			else {
				if($(campo).val().length>0)
					return verificaCampo(campo,'completo');
			}
			$("#Rilevanza").removeClass("has-error");
			$("#divRilevanza").removeClass("has-error");
			break;
		case 'Tipo':
			if(verifica=='completo'){
				switch($(campo).val()){
				case 'nazionale':
				case 'internazionale':
				case 'europeo':
				case 'cooperativo':
					break;
				default:
					$("#Tipo").addClass("has-error");
					$("#divTipo").addClass("has-error");
					return false;
				}
			}
			$("#Tipo").removeClass("has-error");
			$("#divTipo").removeClass("has-error");
			break;
		case 'Tipologia':
			switch($(campo).val()){
			case 'articolo libro':
			case 'articolo rivista':
			case 'brevetto':
			case 'curatela':
			case 'monografia':
			case 'proceeding':
			case 'altro':
				$("#tipologia").removeClass("has-error");
				$("#divTipologia").removeClass("has-error");
				break;
			default:
				$("#tipologia").addClass("has-error");
				$("#divTipologia").addClass("has-error");
				return false;
			}
		case 'Titolo':
			var pattern=/^[a-zA-Z0-9][\w\W]{1,100}/;
			if($(campo).val().length<2 || $(campo).val().length>100){
				$("#Titolo").addClass("has-error");
				$("#divTitolo").addClass("has-error");
				return false;
			}
				
			if(!pattern.test($(campo).val())){
				$("#Titolo").addClass("has-error");
				$("#divTitolo").addClass("has-error");
				return false;
			}
			$("#Titolo").removeClass("has-error");
			$("#divTitolo").removeClass("has-error");
			break;
	}
	return true;
}
function createFields() {  //aggiunge i campi del prodotto in base alla tipologia
	
	var form=$("form[name='formInserimentoProdotto']");
	if(form == null ) {
		//alert("Form non trovato");
		return;
	}
	else {
		svuotaDiv();	
	}
	
	var s_tipo=$("#tipologia").val();
	
	if(s_tipo=='articolo libro'){
		$(getField('Nome_Volume')).appendTo("#otherFields");
		$(getField('Autori_Volume')).appendTo("#otherFields");
		$(getField('Editore')).appendTo("#otherFields");
		$(getField('Citta_Editore')).appendTo("#otherFields");
		$(getField('Paese_Editore')).appendTo("#otherFields");
		$(getField('Pagine_Riferimento')).appendTo("#otherFields");
		$(getField('ISBN')).appendTo("#otherFields");
		$(getField('DOI')).appendTo("#otherFields");
		$(getField('Keywords')).appendTo("#otherFields");
		//Nome_Volume
		//Autori_Volume
		//Editore
		//Citta_Editore
		//Paese_Editore
		//Pagine_Riferimento
		//ISBN
		//DOI
		//Keywords
	}
	else if (s_tipo=='articolo rivista'){
		$(getField('Nome_Rivista')).appendTo("#otherFields");
		$(getField('Numero_Volume')).appendTo("#otherFields");
		$(getField('Pagine_Riferimento')).appendTo("#otherFields");
		$(getField('DOI')).appendTo("#otherFields");
		$(getField('Keywords')).appendTo("#otherFields");
		//Nome_Rivista
		//Numero_Volume
		//Pagine_Riferimento
		//DOI
		//Keywords
	}
	else if (s_tipo=='brevetto'){
		$(getField('Proprieta')).appendTo("#otherFields");
		$(getField('Numero_Brevetto')).appendTo("#otherFields");
		$(getField('Tipo')).appendTo("#otherFields");
		$(getField('DOI')).appendTo("#otherFields");
		$(getField('Keywords')).appendTo("#otherFields");
		//Proprieta
		//Numero_Brevetto
		//Tipo
		//DOI
		//Keywords
	}
	else if (s_tipo=='curatela'){
		$(getField('Nome_Rivista')).appendTo("#otherFields");
		$(getField('Numero_Volume')).appendTo("#otherFields");
		$(getField('Autori_Volume')).appendTo("#otherFields");
		$(getField('Editore')).appendTo("#otherFields");
		$(getField('Citta_Editore')).appendTo("#otherFields");
		$(getField('Pagine_Riferimento')).appendTo("#otherFields");
		$(getField('ISBN')).appendTo("#otherFields");
		$(getField('DOI')).appendTo("#otherFields");
		$(getField('Keywords')).appendTo("#otherFields");
		//Nome_Rivista
		//Numero_Volume
		//Autori_Volume
		//Editore
		//Citta_Editore
		//Pagine_Riferimento
		//ISBN
		//DOI
		//Keywords
	}
	else if (s_tipo=='monografia'){
		$(getField('Editore')).appendTo("#otherFields");
		$(getField('Citta_Editore')).appendTo("#otherFields");
		$(getField('Paese_Editore')).appendTo("#otherFields");
		$(getField('Pagine_Riferimento')).appendTo("#otherFields");
		$(getField('ISBN')).appendTo("#otherFields");
		$(getField('DOI')).appendTo("#otherFields");
		$(getField('Keywords')).appendTo("#otherFields");
		//Editore
		//Citta_Editore
		//Paese_Editore
		//Pagine_Riferimento
		//ISBN
		//DOI
		//Keywords
	}
	else if (s_tipo=='proceeding'){
		$(getField('Nome_Volume')).appendTo("#otherFields");
		$(getField('Autori_Volume')).appendTo("#otherFields");
		$(getField('Editore')).appendTo("#otherFields");
		$(getField('Citta_Editore')).appendTo("#otherFields");
		$(getField('Paese_Editore')).appendTo("#otherFields");
		$(getField('Pagine_Riferimento')).appendTo("#otherFields");
		$(getField('ISBN')).appendTo("#otherFields");
		$(getField('DOI')).appendTo("#otherFields");
		$(getField('Nome_Congresso')).appendTo("#otherFields");
		$(getField('Data_Congresso')).appendTo("#otherFields");
		$(getField('Luogo_Congresso')).appendTo("#otherFields");
		$(getField('Rilevanza')).appendTo("#otherFields");
		$(getField('Relazione')).appendTo("#otherFields");
		$(getField('Keywords')).appendTo("#otherFields");
		//Nome_Volume
		//Autori_Volume
		//Editore
		//Citta_Editore
		//Paese_Editore
		//Pagine_Riferimento
		//ISBN
		//DOI
		//Nome_Congresso
		//Data_Congresso
		//Luogo_Congresso
		//Rilevanza
		//Relazione
		//Keywords
	}
	else if (s_tipo=='altro'){
		$(getField('Nome_Volume')).appendTo("#otherFields");
		$(getField('Numero_Volume')).appendTo("#otherFields");
		$(getField('Pagine_Riferimento')).appendTo("#otherFields");
		$(getField('ISBN')).appendTo("#otherFields");
		$(getField('Keywords')).appendTo("#otherFields");
		//Nome_Volume
		//Numero_Volume
		//Pagine_Riferimento
		//ISBN
		//Keywords
	}
	else {
		//VUOTO
	}
	createTooltips();
}
function getField(nomeField){
	switch(nomeField){
		case 'Autori_Volume':
			return "<div class='form-group' id='divAutoriVolume'>"
			+"<label class='col-xs-offset-2 col-xs-2 control-label'>Autori Volume *</label>"
			+"<div class='col-xs-4' ><input class='form-control' type='text' id='Autori_Volume' name='Autori_Volume' onkeyup='verificaCampo(this)' /></div>"
			+"<span class='help-inline'></span>"
			+"</div>";
		case 'Citta_Editore':
			return "<div class='form-group' id='divCittaEditore'>"
			+"<label class='col-xs-offset-2 col-xs-2 control-label'>Citt&aacute; Editore</label>"
			+"<div class='col-xs-4' ><input class='form-control' type='text' id='Citta_Editore' name='Citta_Editore' onkeyup='verificaCampo(this)'/></div>"
			+"<span class='help-inline'></span>"
			+"</div>";
		case 'Data_Congresso':
			return "<div class='form-group' id='divDataCongresso'>"
			+"<label class='col-xs-offset-2 col-xs-2 control-label'>Data Congresso *</label>"
			+"<div class='col-xs-4' ><input class='form-control' type='text' id='Data_Congresso' name='Data_Congresso' placeholder='GG/MM/AAAA' /></div>"
			+"<span class='help-inline'></span>"
			+"</div>";
		case 'DOI':
			return "<div class='form-group' id='divDOI'>"
			+"<label class='col-xs-offset-2 col-xs-2 control-label'>DOI *</label>"
			+"<div class='col-xs-4' ><input class='form-control' type='text' id='DOI' name='DOI'  onkeyup='verificaCampo(this)' /></div>"
			+"<span class='help-inline'></span>"
			+"</div>";
		case 'Editore':
			return "<div class='form-group' id='divEditore'>"
			+"<label class='col-xs-offset-2 col-xs-2 control-label'>Editore *</label>"
			+"<div class='col-xs-4' ><input class='form-control' type='text' id='Editore' name='Editore'  onkeyup='verificaCampo(this)' /></div>"
			+"<span class='help-inline'></span>"
			+"</div>";
		case 'ISBN':
			return "<div class='form-group' id=divISBN>"
			+"<label class='col-xs-offset-2 col-xs-2 control-label'>ISBN *</label>"
			+"<div class='col-xs-4' ><input class='form-control' type='text' id='ISBN' name='ISBN' onkeyup='verificaCampo(this)'  /></div>"
			+"<span class='help-inline'></span>"
			+"</div>";
		case 'Keywords':
			return "<div class='form-group' id='divKeywords'>"
			+"<label class='col-xs-offset-2 col-xs-2 control-label'>Keywords *</label>"
			+"<div class='col-xs-4' ><input class='form-control' type='text' id='Keywords' name='Keywords' onkeyup='verificaCampo(this)' /></div>"
			+"<span class='help-inline'></span>"
			+"</div>";
		case 'Luogo_Congresso':
			return "<div class='form-group' id='divLuogoCongresso'>"
			+"<label class='col-xs-offset-2 col-xs-2 control-label'>Luogo Congresso *</label>"
			+"<div class='col-xs-4' ><input class='form-control' type='text' id='Luogo_Congresso' name='Luogo_Congresso' onkeyup='verificaCampo(this)' /></div>"
			+"<span class='help-inline'></span>"
			+"</div>";
		case 'Nome_Congresso':
			return "<div class='form-group' id='divNomeCongresso'>"
			+"<label class='col-xs-offset-2 col-xs-2 control-label'>Nome Congresso *</label>"
			+"<div class='col-xs-4' ><input class='form-control' type='text' id='Nome_Congresso' name='Nome_Congresso' onkeyup='verificaCampo(this)' /></div>"
			+"<span class='help-inline'></span>"
			+"</div>";
		case 'Nome_Rivista':
			return "<div class='form-group' id='divNomeRivista'>"
			+"<label class='col-xs-offset-2 col-xs-2 control-label'>Nome Rivista *</label>"
			+"<div class='col-xs-4' ><input class='form-control' type='text' id='Nome_Rivista' name='Nome_Rivista'  onkeyup='verificaCampo(this)'/></div>"
			+"<span class='help-inline'></span>"
			+"</div>";
		case 'Nome_Volume':
			return "<div class='form-group' id='divNomeVolume'>"
			+"<label class='col-xs-offset-2 col-xs-2 control-label'>Nome Volume *</label>"
			+"<div class='col-xs-4' ><input class='form-control' type='text' id='Nome_Volume' name='Nome_Volume' onkeyup='verificaCampo(this)' /></div>"
			+"<span class='help-inline'></span>"
			+"</div>";
		case 'Numero_Brevetto':
			return "<div class='form-group' id='divNumeroBrevetto'>"
			+"<label class='col-xs-offset-2 col-xs-2 control-label'>Numero Brevetto *</label>"
			+"<div class='col-xs-4' ><input class='form-control' type='text' id='Numero_Brevetto' name='Numero_Brevetto' onkeyup='verificaCampo(this)' /></div>"
			+"<span class='help-inline'></span>"
			+"</div>";
		case 'Numero_Volume':
			return "<div class='form-group' id='divNumeroVolume'>"
			+"<label class='col-xs-offset-2 col-xs-2 control-label'>Numero Volume *</label>"
			+"<div class='col-xs-4' ><input class='form-control' type='text' id='Numero_Volume' name='Numero_Volume' onkeyup='verificaCampo(this)' /></div>"
			+"<span class='help-inline'></span>"
			+"</div>";
		case 'Paese_Editore':
			return "<div class='form-group' id='divPaeseEditore'>"
			+"<label class='col-xs-offset-2 col-xs-2 control-label'>Paese Editore</label>"
			+"<div class='col-xs-4' ><input class='form-control' type='text' id='Paese_Editore' name='Paese_Editore' onkeyup='verificaCampo(this)'/></div>"
			+"<span class='help-inline'></span>"
			+"</div>";
		case 'Pagine_Riferimento':
			return "<div class='form-group' id='divPagineRiferimento'>"
			+"<label class='col-xs-offset-2 col-xs-2 control-label'>Pagine di Riferimento *</label>"
			+"<div class='col-xs-4' ><input class='form-control' type='text' id='Pagine_Riferimento' name='Pagine_Riferimento' onkeyup='verificaCampo(this)' /></div>"
			+"<span class='help-inline'></span>"
			+"</div>";
		case 'Proprieta':
			return "<div class='form-group' id='divProprieta'>"
			+"<label class='col-xs-offset-2 col-xs-2 control-label'>Propriet&aacute; *</label>"
			+"<div class='col-xs-4' ><input class='form-control' type='text' id='Proprieta' name='Proprieta' onkeyup='verificaCampo(this)' /></div>"
			+"<span class='help-inline'></span>"
			+"</div>";
		case 'Relazione':
			return "<div class='form-group' id='divRelazione'>"
			+"<label class='col-xs-offset-2 col-xs-2 control-label'>Relazione *</label>"
			+"<div class='col-xs-4' >"
			+"<select class='form-control' id='Relazione' name='Relazione' >"
			+"<option></option>"
			+"<option value='invito'>Su invito</option>"
			+"<option value='contributo'>Contributo</option>"
			+"</select></div>"
			+"<span class='help-inline'></span>"
			+"</div>";
		case 'Rilevanza':
			return "<div class='form-group' id='divRilevanza'>"
			+"<label class='col-xs-offset-2 col-xs-2 control-label'>Rilevanza *</label>"
			+"<div class='col-xs-4' >"
			+"<select class='form-control' id='Rilevanza' name='Rilevanza' >"
			+"<option value=''></option>"
			+"<option value='nazionale'>Nazionale</option>"
			+"<option value='internazionale'>Internazionale</option>"
			+"</select>"
			+"</div>"
			+"<span class='help-inline'></span>"
			+"</div>";
		case 'Tipo':
			return "<div class='form-group' id='divTipo'>"
			+"<label class='col-xs-offset-2 col-xs-2 control-label'>Tipo *</label>"
			+"<div class='col-xs-4' ><select class='form-control' id='Tipo' name='Tipo' >"
			+"<option value=''></option>"
			+"<option value='nazionale'>Nazionale</option>"
			+"<option value='internazionale'>Internazionale</option>"
			+"<option value='europeo'>Europeo</option>"
			+"<option value='cooperativo'>Cooperativo</option>"
			+"</select></div>"
			+"<span class='help-inline'></span>"
			+"</div>";
	}
}
function svuotaDiv(){  //rimuove i campi delle specializzazioni dei prodotti
	$("#otherFields").empty();
}
function showModal(titolo, messaggio) {
	$("#ModalMessaggioLabel").text(titolo);
	$("#TestoModal").text(messaggio);
	$('#ModalMessaggio').modal('show');
}
function eliminaFile() {
	$.ajax({
		type: "POST",
		url: "./EliminaProdottoServlet",
		data: "ID_Prodotto="+$("#ID_Prodotto").val()+"&Tipologia="+$("#tipologia option:selected").val(),
		dataType: "text",
		success: function(msg) {
			window.location.href="./index.jsp";
		}
	});
}