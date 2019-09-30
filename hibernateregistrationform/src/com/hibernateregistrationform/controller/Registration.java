package com.hibernateregistrationform.controller;

import java.io.IOException;

import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernateregistrationform.models.Registrationdetails;


@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Registration() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/views/Registration.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		BigDecimal phone=new BigDecimal(request.getParameter("phone"));
		String password=request.getParameter("password");
		String	gender=request.getParameter("gender");
		
		
	Registrationdetails reg=	new Registrationdetails();
	
	reg.setName(name);
	reg.setEmail(email);
	reg.setPhone(phone);
	reg.setPassword(password);
	reg.setGender(gender);
	
		SessionFactory sf=new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session=sf.openSession();
		session.beginTransaction();
		session.save(reg);
		RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/views/Registered.jsp");
		rd.forward(request, response);
		session.getTransaction().commit();
		session.close();
		
		
	}

}
