package com.enlawebdekaaf.app.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enlawebdekaaf.app.ejb.EjbUsuario;
import com.enlawebdekaaf.app.ejbinterface.IEjbUsuario;

/**
 * Servlet implementation class ServletUsuarioLogin
 */
@WebServlet("/ServletUsuarioLogin")
public class ServletUsuarioLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Se podria usar la anotacion @Ejb pero se crea una instacia y se pierde informacion
	private IEjbUsuario iEjbUsuario;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUsuarioLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("usuario/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//vid 41 - p17
		iEjbUsuario = new EjbUsuario();
		//primero verificamos si existe el correo/usuario
		//Si el existe el correo,la entidad usuario que es atributo del Ejb 
		//se rellena tras la llamada a getByCorreoElectronico
		iEjbUsuario.getByCorreoElectronico(request.getParameter("txtCorreoElectronico"));
		
		
		Map<String,String> returnMap=iEjbUsuario.login(request.getParameter("passContrasenia"));
		
		//Cargo datos en el request para enviar al formulario de ingreso
		request.setAttribute("correcto",returnMap.get("correcto"));
		request.setAttribute("mensajeGeneral",returnMap.get("mensajeGeneral"));
		
		if(returnMap.get("correcto").equals("Si"))
		{	//Inicio sesion pasandole info de idUsuario y el correo electronico
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("idUsuario", iEjbUsuario.getUsuario().getIdUsuario());
			httpSession.setAttribute("correoElectronico", request.getParameter("txtCorreoElectronico"));
			response.sendRedirect("/appwebagenda/ServletUsuarioEditar");
		}
		else
		{
			request.getRequestDispatcher("usuario/login.jsp").forward(request, response);
		}
		
	}

}
