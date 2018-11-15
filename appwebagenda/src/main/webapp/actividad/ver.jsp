<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Actividades</title>

<link rel="stylesheet" href="/appwebagenda/resources/css/cssLayout.css">
<link rel="stylesheet"
	href="/appwebagenda/resources/css/cssComponente.css">
<link rel="stylesheet"
	href="/appwebagenda/resources/css/jquery-ui.min.css">

<script src="/appwebagenda/resources/js/jquery-2.1.3.min.js"></script>
<script src="/appwebagenda/resources/js/jquery-ui.min.js"></script>
</head>
<body>
	<jsp:useBean id="listaActividad" class="java.util.ArrayList<com.enlawebdekaaf.app.jb.JbActividad>" scope="request"></jsp:useBean>
	<%@include file="/parciales/header.jsp"%>
	<section>	
		<div>
		<a href="/appwebagenda/ServletActividadGetByIdUsuario">Todas las actividades</a>
		-
		<a href="/appwebagenda/ServletActividadGetByIdUsuarioAndEstado?estado=true">Actividades finalizadas</a>
		-
		<a href="/appwebagenda/ServletActividadGetByIdUsuarioAndEstado?estado=false">Actividades pendientes</a>
		</div>
		<table class="table">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Descripcion</th>
					<th>Lugar</th>
					<th>Estado</th>
					<th>Fecha inicio</th>
					<th>Fecha fin</th>
					<th>Fecha registro</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${listaActividad}" var="item">
				<tr>
                   <td>${item.getNombre()}</td>
                   <td>${item.getDescripcion()}</td>
                    <td>${item.getLugar()}</td>			
                   <td>${item.isEstado()? "Finalizado":"Pendiente"}</td>
                   <td>${item.getFechaHoraInicio()}</td>
                   <td>${item.getFechaHoraFin()}</td>
                   <td>${item.getFechaRegistro()}</td> 
                   <td>
                   	   <c:if test="${item.isEstado()==true}">
	                   		<input type="button" value="Restaurar actividad" onclick="restaurarActividad(${item.getIdActividad()});">
	                   </c:if> 	
	                   <c:if test="${item.isEstado()==false}">
	                   		<input type="button" value="Terminar actividad" onclick="terminarActividad(${item.getIdActividad()});">
	                   </c:if>                             
		   										
				   </td>	
				</tr>			
			</c:forEach>
			</tbody>
		</table>
	</section>

</body>
<script>
function terminarActividad(idActividad)
{
	window.location.href='/appwebagenda/ServletActividadCambiarEstado?idActividad='+ idActividad+ '&estado=true';
}

function restaurarActividad(idActividad)
{
	window.location.href='/appwebagenda/ServletActividadCambiarEstado?idActividad='+ idActividad+ '&estado=false';
}
</script>

</html>