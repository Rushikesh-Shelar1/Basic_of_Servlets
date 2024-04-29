package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * Servlet implementation class RegistrationServlet
 */
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		String firstName=request.getParameter("t1");
		String LastName=request.getParameter("t2");
		String address=request.getParameter("t3");
		//to read checkbox we need to call
		//String [] getParameterVAlue(String parameter)
		//:HttpServletRequest interface
		
		String[] qualifications=request.getParameterValues("t4");
		String gender =request.getParameter("t5");
		String dob =request.getParameter("t6");
		String phone =request.getParameter("t7");
		String email =request.getParameter("t8");
		String na =request.getParameter("t9");
		
		String qualis=Arrays.toString(qualifications);
		
		PrintWriter writer=response.getWriter();
		writer.println("<h2><font color='green'>Congratulations!!!"+firstName+" "+LastName+"</font>");
		writer.println("you are from "+" "+address);
		writer.println("your qualification "+" "+Arrays.toString(qualifications));
		writer.println("your contact details is"+"<b>"+phone+"</b>"+"<b>"+email+"</b>");
		
		//
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/my_database","root","tiger");
			String query="insert into CustomerWebTable value(?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(query);	
			ps.setString(1,firstName );
			ps.setString(2,LastName );
			ps.setString(3,qualis );
			ps.setString(4,gender );
			ps.setLong(5, Long.parseLong(phone));
			LocalDate Id=LocalDate.parse(dob);
			java.sql.Date dob1=java.sql.Date.valueOf(Id);
			ps.setDate(6, dob1);
			ps.setString(7, email);
			ps.setString(8, na);
			ps.setString(9, address);
			int regStatus=ps.executeUpdate();
			if(regStatus>0) {
//				writer.println("<h2>Registration completed sucessfully!!!!!!</h2>");
				
				writer.println("<h1>Registration completed sucessfully!!!!</h1>");
				String uname = firstName+ " "+LastName;
				//bind the object using keyname with the request scope
				request.setAttribute("userKey", uname);
				//I want to send the user name in other servlet
				//I want to implement servlet chaining
				/*
				 * RequestDispatcher1 is an interface inside javax.servlet 
				 * RequestDispatcher1 is used to dispatch the
				 *  request from 1 servlet to another servlet JSP/HTML page
				 *  RequestDispatcher1 provides 2 important method 
				 *  void include(HTTPServletRequest request, HTTPServletResponse response);
				 *  --->incase of include with the response from next servlet or second servlet
				 *     will be included with first servlet or starter servlet
				 *     
				 *  void forward(HTTPServletRequest request, HttpServletResponse response);
				 *  -->incase of forward method the response from next servlet or second servlet
				 *  does not include with first servlet or starter servlet
				 */
				
				//getting the object of RequestDispatcher1 object
				RequestDispatcher rd= request.getRequestDispatcher("Welcome");
				rd.include(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}

}
