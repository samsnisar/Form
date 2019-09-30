package com.hibernateregistrationform.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.hibernate.query.Query;




@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   
    public login() {
        super();
        
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/views/login.jsp");
		rd.forward(request, response);
	}

	
	@SuppressWarnings({ "rawtypes" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username=request.getParameter("email");
		String password=request.getParameter("password");
		
		
		SessionFactory sf=new Configuration().configure().buildSessionFactory();
		Session session=sf.openSession();
		session.beginTransaction();
		Query  query=session.createQuery("from Registrationdetails where email='"+username+"'and password='"+password+"'");
		List info=query.list();
		Iterator itr=info.iterator();
		if(itr.hasNext()) {
				RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/views/welcome.jsp");
				rd.forward(request, response);
			}else {
				PrintWriter pw=response.getWriter();
				pw.println("<p>Invalid username/password</p>");
				RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/views/login.jsp");
				rd.include(request, response);
			}
		
		
	session.getTransaction().commit();
	session.close();
	
	}

}
