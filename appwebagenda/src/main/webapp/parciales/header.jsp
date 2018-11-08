<header>
	<nav class="menu">
		<ul>
			<li><a href="#">Inicio</a></li>
			<li><a href="#">Registrar datos en el sistema</a></li>
			<li><a href="#">Ver los datos registrados</a></li>
		</ul>
	</nav>
	<div id="divLogin">
		<b>Usuario: </b>
		<%//usaba esta sentencia mientras no tenia implementado el cierre de sesion %>
		<%//request.getSession().getAttribute("correoElectronico")!=null ? request.getSession().getAttribute("correoElectronico") : "Anónimo"%>
		
		
		<%
		  if(request.getSession().getAttribute("correoElectronico")!=null) 
		   {
		 %> 
		   
		
		<%=request.getSession().getAttribute("correoElectronico")%> |
	    <!-- la etiqueta <a href> va via get a ServletUsuarioLogout salvo que con ajax etc definamos onclick... -->
		<a href="/appwebagenda/ServletUsuarioLogout">Cerrar sesión</a>
		<% 
	       }
	       else
	       {
	     %>    
	          <%="Anónimo"%> 
	     <%
	       } 
	     %> 
	</div>
</header>
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