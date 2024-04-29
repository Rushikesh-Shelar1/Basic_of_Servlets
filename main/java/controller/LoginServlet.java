package controller;

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
import java.sql.ResultSet;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		//void setContentType(String str) this is method of HTTpSerletResponse interface
//		//the above method is used to used to set the response type by setting the proper format
//		
//		response.setContentType("text/html");
//		//String getParameter (String parameterName):method of HttpServletRequest interface
//		String mubileNumber=request.getParameter("t1");
//		long ph=Long.parseLong(mubileNumber);
//		long[] password= {123456787,123456789,123456789L};
//		int flag=0;
//		for(int i=0;i<password.length;i++) {
//			if(password[i]==ph) {
//				flag=1;
//				break;
//			}
//		}
//		//to print on browser we need object of PrintWriter class
//		//to PrintWriter getWtiter() :HttppServletResponse interface
//		PrintWriter writer=response.getWriter();
//		if(flag==1) {
//			System.out.println("hi");
//			writer.println("<h2><font color='blue'>Login Success</font></h2>");
//		}else
//		{
//			writer.println("<h2><font color='blue'>Login Failed</font></h2>");
//
//		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		//void setContentType(String str) this is method of HTTpSerletResponse interface
		//the above method is used to used to set the response type by setting the proper format
		
		response.setContentType("text/html");
		//String getParameter (String parameterName):method of HttpServletRequest interface
		String emailId=request.getParameter("t1");
		String mobileNumber=request.getParameter("t2");
		PrintWriter writer=response.getWriter();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/my_database","root","tiger");
			String query1="select * from CustomerWebTable";
			
			PreparedStatement pst1=con.prepareStatement(query1);
			ResultSet rs=pst1.executeQuery();
			int count=0;
			while(rs.next()) {
				if(rs.getString(7).equals(emailId)) {
					count++;
					break;
					
					}
				}
			if (count>=1) {
				System.out.println("suceessfullly log in");
				writer.println("suceessfullly log in");
			}
			else {
				System.out.println("no success!");
				writer.println("no success!");
			}
		}catch(Exception e) {
			
		}

	}

}
