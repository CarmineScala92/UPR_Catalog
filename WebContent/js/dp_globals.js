function esci(){
	
	$.ajax({
		type: "POST",
		url: "./GestioneLogoutServlet",
		dataType: "text"
		//success: function(xml){
			//document.location.href="index.jsp";
		//}
	});	
}

function goHome(){
	document.location.href="dp_home.jsp";
}

function goHelp(){
	document.location.href="dp_help.jsp";
}


function showWait(message){
	var popup = '	<div id="popup-container">'+
				'		<div id="popup-window">'+
				'			<p class="title">ATTENDERE PREGO</p>'+
				'			<p class="message">'+message+'</p>'+
				'		</div>'+
				'	</div>';
	$(popup).appendTo("body");
}

function removeWait(){
	$("#popup-container").remove();
}