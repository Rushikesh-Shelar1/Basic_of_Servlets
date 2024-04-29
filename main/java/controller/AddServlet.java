package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class AddServlet
 */
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServlet() {
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
		String firstNo=request.getParameter("t1");
		String secondNo=request.getParameter("t2");
		String option=request.getParameter("t3");
		int a=Integer.parseInt(firstNo);
		int b=Integer.parseInt(secondNo);
		PrintWriter writer=response.getWriter();
		if(option.equals("Add")) {
			int sum=a+b;
			writer.println("<h2> Addition of two no.s is </h2>"+sum);
		}
		else {
			int sub=a-b;
			writer.println("<h2> substraction of two no.s is </h2>"+sub);
		}
		
		
		
	}

}
