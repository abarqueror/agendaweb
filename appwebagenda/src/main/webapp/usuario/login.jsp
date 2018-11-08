<!doctype html>

<html lang="es">
<head>
<meta charset="UTF-8">
<title>En la Web de KAAF</title>


<link rel="stylesheet"
	href="/appwebagenda/resources/css/cssComponente.css">
<link rel="stylesheet"
	href="/appwebagenda/resources/css/jquery-ui.min.css">

<script src="/appwebagenda/resources/js/jquery-2.1.3.min.js"></script>
<script src="/appwebagenda/resources/js/jquery-ui.min.js"></script>
</head>
<body>
	<%
		// Vid 40-P15 Capa javabean
	%>
	<%
	if(request.getAttribute("correcto")!=null && request.getAttribute("correcto").equals("Si"))
	{
		%>
		<div class="divAlertaCorrecto">
			<%=request.getAttribute("mensajeGeneral")%>
		</div>
		<%
	}
	if(request.getAttribute("correcto")!=null && request.getAttribute("correcto").equals("No"))
	{
		%>
		<div class="divAlertaError">
			<%=request.getAttribute("mensajeGeneral")%>
		</div>
		<%
	}
%>
	<section>
		
		<form id="frmLogin" action="/appwebagenda/ServletUsuarioLogin" method="post">

			<h2>Iniciar sesión</h2>
			<hr>
			<label for="txtCorreoelectronico" class="label">Correo electronico</label> 
			<input type="text" id="txtCorreoelectronico" name="txtCorreoElectronico" class="text" > 
			<br>
			
			<label for="passContrasenia" class="label">Contraseña</label>
			<input type="password" id="passContrasenia"	name="passContrasenia" class="password"> <br>
				
			<br>
			<input type="submit" value="Ingresar" class="button">

		</form>
	</section>
	


</body>
</html>