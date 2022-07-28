package com.swa.user;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

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
	    if (request.getParameter("Action").equals("Edit"))
	      // pass the request along the filter chain
	      chain.doFilter(request, response);
	    if (request.getParameter("Action").equals("Delete"))
	      // pass the request along the filter chain
	      chain.doFilter(request, response);
	  }
	  /**
	   * @see Filter#init(FilterConfig)
	   */
	  public void init(FilterConfig fConfig) throws ServletException {
	    // TODO Auto-generated method stub
	  }

	
}
