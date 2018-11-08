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
import com.enlawebdekaaf.app.jb.JbUsuario;

/**
 * Servlet implementation class ServletUsuarioEditar
 */
@WebServlet("/ServletUsuarioEditar")
public class ServletUsuarioEditar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IEjbUsuario iEjbUsuario;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUsuarioEditar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Vid 38 - P13. Ini
		HttpSession httpSession=request.getSession();
		
		iEjbUsuario=new EjbUsuario();
		
		//extraigo la info de un usuario y el ejb la pasa a contener
		iEjbUsuario.getByIdUsuario((int)httpSession.getAttribute("idUsuario"));
		
		JbUsuario jbUsuario= new JbUsuario();
		
		
		
		jbUsuario.setIdUsuario(iEjbUsuario.getUsuario().getIdUsuario());
		jbUsuario.setNombre(iEjbUsuario.getUsuario().getNombre());
		jbUsuario.setApellido(iEjbUsuario.getUsuario().getApellido());
		jbUsuario.setFechaNacimiento(iEjbUsuario.getUsuario().getFechaNacimiento());
		jbUsuario.setCorreoElectronico(iEjbUsuario.getUsuario().getCorreoElectronico());
		jbUsuario.setContrasenia(iEjbUsuario.getUsuario().getContrasenia());
		jbUsuario.setFechaRegistro(iEjbUsuario.getUsuario().getFechaRegistro());
		jbUsuario.setFechaModificacion(iEjbUsuario.getUsuario().getFechaModificacion());
		
		
		
		request.setAttribute("usuario",jbUsuario);
		
		
		//Si esta definido returnMap en la httpSession,es decir, se cargo la info que update retorna en el returnMap
		 
		//if(httpSession.getAttribute("returnMap")!=null) preguntare por correcto ya que he modificado
		//lo que paso desde el DoPost,ya no es el Map<string,String> returnMap.Si está
		if(httpSession.getAttribute("correcto")!=null)	
		{
			//Necesito hacer casting de lo que devuelve httpSession.getAttribute("returnMap") a Map<string,...
			//pero lo modifico por aparecer un warning indeseado que puede funcionar mal.Mejor trabajo con String
			//Map<String,String> returnMap = (Map<String,String>)httpSession.getAttribute("returnMap");
			
			
			//request.setAttribute("correcto", returnMap.get("correcto"));
			//request.setAttribute("mensajeGeneral", returnMap.get("mensajeGeneral"));
			request.setAttribute("correcto", httpSession.getAttribute("correcto"));
			request.setAttribute("mensajeGeneral", httpSession.getAttribute("mensajeGeneral"));
			
			//httpSession.removeAttribute("returnMap");
			httpSession.removeAttribute("correcto");
			httpSession.removeAttribute("mensajeGeneral");

		}			
		//Vid 38 - P13. Fin
		
		request.getRequestDispatcher("usuario/editar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//vid 36 - P11
		iEjbUsuario=new EjbUsuario();
		
		HttpSession httpSession=request.getSession();
		
		iEjbUsuario.getByIdUsuario((int) httpSession.getAttribute("idUsuario"));
		//Vid 39 - 14
		//guardo el correo electronico que el usuario trae por defecto de BBDD en la variable local del Ejb,no del usuario
		iEjbUsuario.setCorreoElectronicoAnterior(iEjbUsuario.getUsuario().getCorreoElectronico());
		//Vid 39 - 14
		iEjbUsuario.getUsuario().setNombre(request.getParameter("txtNombre"));
		iEjbUsuario.getUsuario().setApellido(request.getParameter("txtApellido"));
		iEjbUsuario.getUsuario().setFechaNacimiento(request.getParameter("dateFechaNacimiento"));
		iEjbUsuario.getUsuario().setCorreoElectronico(request.getParameter("txtCorreoElectronico"));
		//Fecha de modificacion no se actualiza desde el Servlet.Aqui solo recibimos la info y la mandamos al EJB
		
		//En la vista de editar ya mandamos .val('') Vid 37 - P12 -Inicio
		iEjbUsuario.setContraseniaAnterior("");
		if(request.getParameter("radioCambiarContrasenia").equals("Si"))
		{
			//En el ejb que implementala logica de negocio podemos comprobar directamente con la entidad 
			//la contraseña nueva
			iEjbUsuario.setContraseniaAnterior(request.getParameter("passContraseniaAnterior"));
			iEjbUsuario.setContraseniaNueva(request.getParameter("passContraseniaNueva"));
			iEjbUsuario.setContraseniaRepita(request.getParameter("passContraseniaNuevaRepita"));
			
		}
		//Vid 37 - P12 -Fin
		
		//Vid 38 - P13. Ini
		//Hasta ahora no retornaba returnMap al invocar update
		Map<String, String> returnMap = iEjbUsuario.update();
		
		//El redirect nos manda via GET
		//con sendRedirect se pierden los datos en la redireccion aunque haga p.ej request.setAttribute..
		//no es como request.getRequestDispatcher("usuario/editar.jsp").forward(request, response) que mantiene
		//el valor guardado con setAttribute.Para pasar valores lo hago con httpSession
		
		
		
		//httpSession.setAttribute("returnMap", returnMap ); Lo cambio para evitar un warning al 
		//recuperar en el DoGet lin 67.Los paso como cadena o String y evito el casting
		httpSession.setAttribute("correcto", returnMap.get("correcto"));
		httpSession.setAttribute("mensajeGeneral", returnMap.get("mensajeGeneral"));
		//Vid 38 - P13. Fin
		
		//y redirecciono aqui mismo pero al Get
		response.sendRedirect("/appwebagenda/ServletUsuarioEditar");
	}

}
