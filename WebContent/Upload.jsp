<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Upload file</title>

<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="css/navbar.css" />
<script src="js/jquery-1.10.2.js"></script>
</head>
<body>
		<%@ page import="it.unisa.upr.data.gestioneAutenticazione.Account" %>
		<%@ page import="it.unisa.upr.data.gestioneAutenticazione.Ricercatore"%>
		<% 
			Ricercatore ric = (Ricercatore) session.getAttribute("Ricercatore");
			if (ric == null) {
				response.sendRedirect("index.jsp");
			}
			else if(request.getParameter("URL")==null){ 	%>
				<form action="./UploadDownloadFileServlet" method="post" enctype="multipart/form-data" id='form'>
					Selezionare il file da caricare: <input type="file" name="fileName" onchange='$("#form").submit()'>
				</form>
			<% } 
			else { %>
				<label>Il file è stato caricato<br>
				<% out.print(request.getParameter("URL")); %>
				</label>
				<input type='hidden' value="<% out.print(request.getParameter("URL")); %>" id="URL"></input>
			<% } %>
			
</body>
</html>