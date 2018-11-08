package com.enlawebdekaaf.app.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enlawebdekaaf.app.ejb.EjbUsuario;
import com.enlawebdekaaf.app.ejbinterface.IEjbUsuario;

/**
 * Servlet implementation class ServletUsuarioInsertar
 */
@WebServlet("/ServletUsuarioInsertar")
public class ServletUsuarioInsertar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Se podria usar la anotacion @Ejb pero se crea una instacia y se pierde informacion
	private IEjbUsuario iEjbUsuario;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUsuarioInsertar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("usuario/insertar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			//al ser iEjbUsuario de tipo @Local he de instaciar la interfaz
			iEjbUsuario = new EjbUsuario();
			iEjbUsuario.getUsuario().setNombre(request.getParameter("txtNombre"));
			
			iEjbUsuario.getUsuario().setApellido(request.getParameter("txtApellido"));
			iEjbUsuario.getUsuario().setFechaNacimiento(request.getParameter("dateFechaNacimiento"));
			iEjbUsuario.getUsuario().setCorreoElectronico(request.getParameter("txtCorreoElectronico"));
			iEjbUsuario.getUsuario().setContrasenia(request.getParameter("passContrasenia"));
			
			iEjbUsuario.setContraseniaRepita(request.getParameter("passContraseniaRepita"));
			
			Map<String,String> returnMap=iEjbUsuario.insert();
			request.setAttribute("correcto",returnMap.get("correcto"));
			request.setAttribute("mensajeGeneral",returnMap.get("mensajeGeneral"));
			
			
			request.getRequestDispatcher("usuario/insertar.jsp").forward(request, response);
		}
		catch(Exception ex) {
			System.out.println("error caca"+ ex.getMessage());
		}
		
	}

}
