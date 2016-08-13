<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



<!-- Button trigger modal -->

<button class="btn btn-danger" data-toggle="modal" data-target="#myModal">Elimina</button>

<div class="row">

      <div class="col-xs-6 col-xs-offset-3 alert alert-success fade in" hidden>

        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">x</button>

        <div class="alert-msg"></div>

    </div>





<!-- Modal -->

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="vertical-align:middle">

  <div class="modal-dialog" style="padding-top: 15%;">

    <div class="modal-content">

      <div class="modal-header">

        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>

        <h4 class="modal-title" id="myModalLabel">Errore Autenticazione</h4>

      </div>

      <div class="modal-body">

        <p>Sei sicuro di voler eliminare il prodotto "Software & Tecnology" ?</p>

      </div>

      <div class="modal-footer">

        <button type="button" class="btn btn-primary">OK</button>

      </div>

    </div><!-- /.modal-content -->

  </div><!-- /.modal-dialog -->

</div><!-- /.modal -->





<script src="js/jquery.js"></script> 

<script src="js/bootstrap.js"></script> 

<script type="text/javascript">

			

		 $(document).ready(function () {

			 // close all alerts

			// $(".alert").alert('close');

			 

			 });

			

		function lanciaNotifica(){

			$(".alert").show();

			$(".alert-msg").html('<strong>Ben fatto!</strong> Il tuo prodotto è stato inserito correttamente.');

			window.setTimeout(function(){ $(".alert").alert('close'); }, 3000);

		}

				  			  

            </script>





</body>
</html>