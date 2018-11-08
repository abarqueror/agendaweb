package com.enlawebdekaaf.app.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FilterSession
 */
@WebFilter("/FilterSession")
public class FilterSession implements Filter {

    /**
     * Default constructor. 
     */
    public FilterSession() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest=((HttpServletRequest)request);
		
		//prueba para ver que funciona el filtro.Imprimo la url de donde viene la peticion
		String urlRequest=httpServletRequest.getRequestURL().toString();
		
		HttpSession httpSession=httpServletRequest.getSession();
		//puedo trabajar con idUsuario o correoElectronico
		
		//String sessionUsuario=httpSession.getAttribute("correoElectronico").toString(); peta al hacer toString siendo null
		if(!urlRequest.equals("http://localhost:8080/appwebagenda/ServletUsuarioLogin") && httpSession.getAttribute("correoElectronico")==null)
		{
			//casting porque el response viene de un HttpServletResponse
			//return ((HttpServletResponse)response).sendRedirect("/appwebagenda/ServletUsuarioLogin");
			 ((HttpServletResponse)response).sendRedirect("/appwebagenda/ServletUsuarioLogin");
		}
		else
		{
			chain.doFilter(request, response);
		}
		
		//http://localhost:8080/appwebagenda/ServletUsuarioLogin
		
		//System.out.println(urlRequest);
		//hay que maper el filtro en webxml
		
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
