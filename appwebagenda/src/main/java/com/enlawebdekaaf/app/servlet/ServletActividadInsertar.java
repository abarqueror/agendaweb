package com.enlawebdekaaf.app.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enlawebdekaaf.app.ejb.EjbActividad;
import com.enlawebdekaaf.app.ejbinterface.IEjbActividad;

/**
 * Servlet implementation class ServletActividadInsertar
 */
@WebServlet("/ServletActividadInsertar")
public class ServletActividadInsertar extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//vid 46 - p21 - m29
    private IEjbActividad iEjbActividad;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ServletActividadInsertar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("actividad/insertar.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
try {
	
			//Necesito recuperar idUsuario del que depende la actividad.Esta guardado en httpSession
			HttpSession httpSession=request.getSession();
			
			//al ser iEjbActividad de tipo @Local he de instaciar la interfaz
			iEjbActividad=new EjbActividad();
			
			iEjbActividad.getActividad().getTusuario().setIdUsuario(Integer.parseInt(httpSession.getAttribute("idUsuario").toString()));
			
			iEjbActividad.getActividad().setNombre(request.getParameter("txtNombre"));
			
			iEjbActividad.getActividad().setDescripcion(request.getParameter("txtDescripcion"));
			
			iEjbActividad.getActividad().setLugar(request.getParameter("txtLugar"));
			
			//tratamiento de fechas pasadas por el formulario
			
			String fechaHoraInicio=request.getParameter("dateFechainicio")+" "+request.getParameter("selectHoraInicio")+":"
					+request.getParameter("selectMinutoInicio");
			
			iEjbActividad.getActividad().setFechaHoraInicio(fechaHoraInicio);
			
			String fechaHoraFin=request.getParameter("dateFechaFin")+" "+request.getParameter("selectHoraFin")+":"
					+request.getParameter("selectMinutoFin");
			
			iEjbActividad.getActividad().setFechaHoraFin(fechaHoraFin);
			
			
		
			
			
			
			
			Map<String,String> returnMap=iEjbActividad.insert();
			request.setAttribute("correcto",returnMap.get("correcto"));
			request.setAttribute("mensajeGeneral",returnMap.get("mensajeGeneral"));
			
			
			request.getRequestDispatcher("actividad/insertar.jsp").forward(request, response);
		}
		catch(Exception ex) {
			System.out.println("error caca"+ ex.getMessage());
		}
	}

}
