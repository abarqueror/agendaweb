<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
		<form id="frmInsertarActividad" action="/appwebagenda/ServletActividadInsertar" method="post">

			<h2>Registrar actividad</h2>
			<hr>
			<label for="txtNombre" class="label">Nombre</label> <input
				type="text" id="txtNombre" name="txtNombre" class="text" value="<%=(request.getAttribute("correcto")!=null && request.getAttribute("correcto").equals("No")) ? request.getParameter("txtNombre") : "" %>" >
			<br>
			<label for="txtDescripcion" class="label">Descripción</label>
			<textarea id="txtDescripcion" name="txtDescripcion" class="textArea"></textarea>
			<br>
			<label for="txtLugar" class="label">Lugar</label> <input
				type="text" id="txtLugar" name="txtLugar" class="text" value="<%=(request.getAttribute("correcto")!=null && request.getAttribute("correcto").equals("No")) ? request.getParameter("txtLugar") : "" %>" >
			<br>
			<label for="dateFechaInicio" class="label">Fecha de Inicio</label> <input type="date" id="dateFechaInicio"
				name="dateFechainicio" class="text" value="<%=(request.getAttribute("correcto")!=null && request.getAttribute("correcto").equals("No")) ? request.getParameter("dateFechaInicio") : "" %>">
			<select id="selectHoraInicio" name="selectHoraInicio" class="selectTiempo">
			    <option value="">Hora</option>
				<c:forEach var="i" begin="0" end="23">
					<option value="${i}">${i}</option>
				</c:forEach>
				
			</select>
			<select id="selectMinutoInicio" name="selectMinutoInicio" class="selectTiempo">
			    <option value="">Minuto</option>
				<c:forEach var="i" begin="0" end="59">
					<option value="${i}">${i}</option>
				</c:forEach>
				
			</select>
			<br>
			<label for="dateFechaFin" class="label">Fecha de finalización</label> 
			<input type="date" id="dateFechaFin"
				name="dateFechaFin" class="text" value="<%=(request.getAttribute("correcto")!=null && request.getAttribute("correcto").equals("No")) ? request.getParameter("dateFechaFin") : "" %>">
			
			<select id="selectHoraFin" name="selectHoraFin" class="selectTiempo">
			    <option value="">Hora</option>
				<c:forEach var="i" begin="0" end="23">
					<option value="${i}">${i}</option>
				</c:forEach>
				
			</select>
			<select id="selectMinutoFin" name="selectMinutoFin" class="selectTiempo">
			    <option value="">Minuto</option>
				<c:forEach var="i" begin="0" end="59">
					<option value="${i}">${i}</option>
				</c:forEach>
				
			</select>
			<br>
			 <input type="submit" value="Registrar actividad" class="button">

		</form>
	</section>

</body>


</html>