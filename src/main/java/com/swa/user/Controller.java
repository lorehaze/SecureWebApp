package com.swa.user;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Controller implements Filter {

	 /**
	   * Default constructor.
	   */
	  public Controller() {
	  }
	  /**
	   * @see Filter#destroy()
	   */
	  public void destroy() {
	  }
	  /**
	   * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	   */
	  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	      throws IOException, ServletException {
	    // TODO Auto-generated method stub
	    // place your code here
	    if (request.getParameter("Action").equals("Add"))
	      // pass the request along the filter chain
	      chain.doFilter(request, response);
	    if (request.getParameter("Action").equals("Login"))
		      // pass the request along the filter chain
		      chain.doFilter(request, response);
	    if (request.getParameter("Action").equals("UploadPicture"))
	    	chain.doFilter(request, response);
	    if (request.getParameter("Action").equals("UploadProject"))
	    	chain.doFilter(request, response);
	    if (request.getParameter("Action").equals("ShowAll"))
	    	chain.doFilter(request, response);
	  }
	  /**
	   * @see Filter#init(FilterConfig)
	   */
	  public void init(FilterConfig fConfig) throws ServletException {
	    // TODO Auto-generated method stub
	  }

	
}
