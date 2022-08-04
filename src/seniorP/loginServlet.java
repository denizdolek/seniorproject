package seniorP;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		

		String url = "jdbc:mysql://localhost:3306/seniorproject";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "denz";
        String passwordd = "denz";
        
        
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url,userName,passwordd);
            PreparedStatement pstmt = conn.prepareStatement("SELECT password,authorisation FROM user WHERE username=?");
            pstmt.setString(1, username);
            
            
            
            ResultSet rs = pstmt.executeQuery();
            
            rs.next();
            
            String passwordretrieved=rs.getString("password");
            int authority=rs.getInt("authorisation");
           if(Objects.equals(passwordretrieved, password)){
        	   
        	   if(Objects.equals(authority, 1)){
        		   out.println(2);
        	   }else{
        		   out.println(1);
        	   }
           }else{
        	   out.println("Password is incorrect");
           }
          

           
        }catch(Exception e){
        	
        	out.println("This user does not exists");
        }
		
	}

}
