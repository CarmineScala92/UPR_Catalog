$(document).ready(function(){
	optionsDipartimenti();
	optionsAreeScientifiche();
});

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
function optionsAreeScientifiche(){

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
				row +=	'<option value="'+id+'" >'+ nome +'</option>';	
			});
			$(row).appendTo("#area_scientifica1");
		}
	});		
}
