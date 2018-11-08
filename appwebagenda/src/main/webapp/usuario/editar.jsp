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
	<%
		// Vid 35-P10 Capa javabean
	%>
	<jsp:useBean id="usuario" class="com.enlawebdekaaf.app.jb.JbUsuario" scope="request"></jsp:useBean>
	<%@include file="/parciales/header.jsp"%>
	<section>
		
		<form id="frmEditarUsuario"
			action="/appwebagenda/ServletUsuarioEditar" method="post">

			<h2>Mis datos personales</h2>
			<hr>
			<label for="txtNombre" class="label">Nombre</label> 
			<input type="text" id="txtNombre" name="txtNombre" class="text" value="<%=usuario.getNombre()%>"> 
			<br>
			<label	for="txtApellido" class="label">Apellido</label> 
			<input type="text"	id="txtApellido" name="txtApellido" class="text" value="<%=usuario.getApellido()%>"> 
			<br> 
			<label	for="dateFechaNacimiento" class="label">Fecha de Nacimiento</label>
			<input type="date" id="dateFechaNacimiento"	name="dateFechaNacimiento" class="text"	value="<%=usuario.getFechaNacimiento()%>">
		    <br>
		    <label for="txtCorreoElectronico" class="label">Correo electronico</label>
			<input type="text" id="txtCorreoElectronico" name="txtCorreoElectronico" class="text" value="<%=usuario.getCorreoElectronico()%>"> <br> 
			<br>
			<label for="radioCambiarContrasenia" class="label">Cambiar contraseña</label> 
			<br>
			<label for="radioCambiarContraseniaSi">Si</label>
			<input type="radio" id="radioCambiarContraseniaSi" name="radioCambiarContrasenia" value="Si" onchange="onChangeRadioCambiarContrasenia();"> 
			<label for="radioCambiarContraseniaSi">No</label>
			<input type="radio" id="radioCambiarContraseniaNo" name="radioCambiarContrasenia" value="No" checked="checked" onchange="onChangeRadioCambiarContrasenia();">
			<br>
			<div id="divCambiarContrasenia" style="display: none;">
				<label for="passContraseniaAnterior" class="label">Contraseña anterior</label>
				<input type="password" id="passContraseniaAnterior"	name="passContraseniaAnterior" class="password"> <br>
				<br>
				<label for="passContraseniaNueva" class="label">Contraseña nueva</label>
				<input type="password" id="passContraseniaNueva" name="passContraseniaNueva" class="password">
			    <br>
				<label for="passContraseniaNuevaRepita" class="label">Repita contraseña nueva</label>
				<input type="password" id="passContraseniaNuevaRepita" name="passContraseniaNuevaRepita" class="password">
			</div>
			<input type="button" value="Registrarme" class="button" onclick="enviarFrmEditarUsuario();">

		</form>
	</section>
	<script>
		function onChangeRadioCambiarContrasenia() 
		{
			if ($('input[name=radioCambiarContrasenia]:checked').val() == 'Si') 
			{
				$('#divCambiarContrasenia').show();
			}
			else
			{
				$('#divCambiarContrasenia').hide();
				$('#passContraseniaAnterior').val('');
				$('#passContraseniaNueva').val('');
				$('#passContraseniaNuevaRepita').val('');
			}
		}
		
		function enviarFrmEditarUsuario()
		{
		  // == compara la igualdad sin importar el tipo ypuede dar problema con mayuscula minusculas
		  //=== importa que sea el mismo valor y tipo,mas preciso
		  if($('#passContraseniaNueva').val()===$('#passContraseniaNuevaRepita').val())
		  {
		    return $('#frmEditarUsuario').submit();
		  }
		  
		  alert('Las contraseñas no coinciden');
		}
	</script>


</body>


</html>