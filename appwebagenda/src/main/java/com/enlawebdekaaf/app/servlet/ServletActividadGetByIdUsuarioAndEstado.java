package com.enlawebdekaaf.app.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enlawebdekaaf.app.ejb.EjbActividad;
import com.enlawebdekaaf.app.ejbinterface.IEjbActividad;
import com.enlawebdekaaf.app.entity.Tactividad;
import com.enlawebdekaaf.app.jb.JbActividad;

/**
 * Servlet implementation class ServletActividadGetByIdUsuarioAndEstado
 */
@WebServlet("/ServletActividadGetByIdUsuarioAndEstado")
public class ServletActividadGetByIdUsuarioAndEstado extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private IEjbActividad iEjbActividad;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletActividadGetByIdUsuarioAndEstado() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
iEjbActividad= new EjbActividad();
		
		iEjbActividad.getByIdUsuarioAndEstado(Integer.parseInt(request.getSession().getAttribute("idUsuario").toString()),Boolean.parseBoolean(request.getParameter("estado")));
		
		List<JbActividad> listaJbActividad= new ArrayList<JbActividad>();
		
		for (Tactividad item : iEjbActividad.getListaActividad()) {
			JbActividad jbActividad = new JbActividad();
			
			jbActividad.setIdActividad(item.getIdActividad());
			//jbActividad.setIdUsuario(item.getIdUsuario());
			jbActividad.setNombre(item.getNombre());
			jbActividad.setDescripcion(item.getDescripcion());
			jbActividad.setEstado(item.getEstado());
			jbActividad.setFechaHoraInicio(item.getFechaHoraInicio());
			jbActividad.setFechaHoraFin(item.getFechaHoraFin());
			jbActividad.setFechaRegistro(item.getFechaRegistro());
			//jbActividad.setFechaModificacion(item.getFechaModificacion());
			jbActividad.setLugar(item.getLugar());
			
		    listaJbActividad.add(jbActividad);
		}
		
		request.setAttribute("listaActividad", listaJbActividad);
		
		request.getRequestDispatcher("actividad/ver.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
