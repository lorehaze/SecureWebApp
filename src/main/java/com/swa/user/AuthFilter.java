package com.swa.user;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthFilter {
	private ServletContext context;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	}

	public void destroy() {
		// close any resources here
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String uri = req.getRequestURI();
		this.context.log("Requested Resource::" + uri);

		HttpSession session = req.getSession(false);
		Object email = req.getSession().getAttribute("email");
		this.context.log("Authentication Filter, email:" + email);

		if (email == null && !(uri.endsWith("index.jsp") || uri.endsWith("LoginUser"))) {
			this.context.log("None authenticatied request, session:: " + session);
			res.sendRedirect("login.jsp");

		} else {
			this.context.log("Authenticatied request, session:: " + session);
			chain.doFilter(request, response);
		}

	}
}
