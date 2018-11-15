package com.enlawebdekaaf.app.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enlawebdekaaf.app.ejb.EjbActividad;
import com.enlawebdekaaf.app.ejbinterface.IEjbActividad;

/**
 * Servlet implementation class ServletActividadCambiarEstado
 */
@WebServlet("/ServletActividadCambiarEstado")
public class ServletActividadCambiarEstado extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private IEjbActividad iEjbActividad;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletActividadCambiarEstado() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		iEjbActividad=new EjbActividad();
		
		iEjbActividad.changeStatusActividad(Integer.parseInt(request.getParameter("idActividad")), Boolean.parseBoolean(request.getParameter("estado")));
		
		//request.getRequestDispatcher("actividad")...No lo hago porque no quiero volver a 
		//hacer toda una consulta de las actividades.Utilizo response
		
		String estadoUrlQuery=request.getParameter("estado").equals("true") ? "false" : "true";
		
		response.sendRedirect("/appwebagenda/ServletActividadGetByIdUsuarioAndEstado?estado="+estadoUrlQuery);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
