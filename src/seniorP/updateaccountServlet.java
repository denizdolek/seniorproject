package seniorP;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class updateaccountServlet
 */
@WebServlet("/updateaccountServlet")
public class updateaccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String oldusername=request.getParameter("oldusername");
		String username=request.getParameter("username");
		String oldpassword=request.getParameter("oldpassword");
		String newpassword=request.getParameter("newpassword");
		String name=request.getParameter("name");
		String surname=request.getParameter("surname");
		String email=request.getParameter("email");
		String department=request.getParameter("department");
		
		String url = "jdbc:mysql://localhost:3306/seniorproject";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "denz";
        String passwordd = "denz";
       
        
		
		if(Objects.equals("", newpassword)){
			
	        
	        try {
	        	Class.forName(driver).newInstance();
				Connection conn = DriverManager.getConnection(url,userName,passwordd);
	            PreparedStatement pstmt = conn.prepareStatement("UPDATE user SET username=? ,name=? ,surname=? ,email=? ,department=? WHERE username=? ");
	            pstmt.setString(1, username);
	            pstmt.setString(2, name);
	            pstmt.setString(3, surname);
	            pstmt.setString(4, email);
	            pstmt.setString(5, department);
	            pstmt.setString(6, oldusername);
	            
	            out.println(username);
	            pstmt.executeUpdate();
	            conn.close();
		}catch(Exception e){
			
			out.println(0);
		}
	     
		
	}else{
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url,userName,passwordd);
            PreparedStatement pstmt = conn.prepareStatement("UPDATE user SET username=? ,name=? ,surname=? ,email=? ,department=? ,password=? WHERE username=? ");
            pstmt.setString(1, username);
            pstmt.setString(2, name);
            pstmt.setString(3, surname);
            pstmt.setString(4, email);
            pstmt.setString(5, department);
            pstmt.setString(6, newpassword);
            pstmt.setString(7, oldusername);
           
            out.println(username);
            pstmt.executeUpdate();
            conn.close();
	}catch(Exception e){
		out.println(0);
		
	}
		
	}
		
	}}


