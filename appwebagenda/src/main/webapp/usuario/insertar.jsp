<!doctype html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>En la Web de KAAF</title>

<link rel="stylesheet" href="/appwebagenda/resources/css/cssLayout.css">
<link rel="stylesheet"
	href="/appwebagenda/resources/css/cssComponente.css">
<link rel="stylesheet"
	href="/appwebagenda/resources/css/jquery-ui.min.css">

<script src="/appwebagenda/resources/js/jquery-2.1.3.min.js"></script>
<script src="/appwebagenda/resources/js/jquery-ui.min.js"></script>
</head>
<body>
	<%@include file="/parciales/header.jsp"%>
	<section>	
		<form id="frmInsertarUsuario"
			action="/appwebagenda/ServletUsuarioInsertar" method="post">

			<h2>Registrarme en el sistema</h2>
			<hr>
			<label for="txtNombre" class="label">Nombre</label> <input
				type="text" id="txtNombre" name="txtNombre" class="text" value="<%=(request.getAttribute("correcto")!=null && request.getAttribute("correcto").equals("No")) ? request.getParameter("txtNombre") : "" %>" >
			<hr>
			<label for="txtApellido" class="label">Apellido</label> <input
				type="text" id="txtApellido" name="txtApellido" class="text" value="<%=(request.getAttribute("correcto")!=null && request.getAttribute("correcto").equals("No")) ? request.getParameter("txtApellido") : "" %>" >
			<hr>
			<label for="dateFechaNacimiento" class="label">Fecha de
				Nacimiento</label> <input type="date" id="dateFechaNacimiento"
				name="dateFechaNacimiento" class="text" value="<%=(request.getAttribute("correcto")!=null && request.getAttribute("correcto").equals("No")) ? request.getParameter("dateFechaNacimiento") : "" %>">
			<hr>
			<label for="txtCorreoElectronico" class="label">Correo
				electronico</label> <input type="text" id="txtCorreoElectronico"
				name="txtCorreoElectronico" class="text" value="<%=(request.getAttribute("correcto")!=null && request.getAttribute("correcto").equals("No")) ? request.getParameter("txtCorreoElectronico") : "" %>">
			<hr>
			<label for="passContrasenia" class="label">Contraseña</label> <input
				type="password" id="passContrasenia" name="passContrasenia"
				class="password">
			<hr>
			<label for="passContraseniaRepita" class="label">Repita
				contraseña</label> <input type="password" id="passContraseniaRepita"
				name="passContraseniaRepita" class="password"> <input
				type="submit" value="Registrarme" class="button">

		</form>
	</section>

</body>


</html>