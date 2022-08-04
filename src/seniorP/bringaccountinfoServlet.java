package seniorP;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class bringaccountinfoServlet
 */
@WebServlet("/bringaccountinfoServlet")
public class bringaccountinfoServlet extends HttpServlet {
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
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM user WHERE username=? AND password=?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            
            
            ResultSet rs = pstmt.executeQuery();
            
            rs.next();
            
            String usernameretr=rs.getString("username");
            String passwordretr=rs.getString("password");
            String name=rs.getString("name");
            String surname=rs.getString("surname");
            String email=rs.getString("email");
            String department=rs.getString("department");
            
            out.println("<lgnx>"+
             "<p>Username</p>"+
             "<p>Old-Password</p>"+	
             "<p>New Password</p>"+
             "<p>Name</p>"+
             "<p>Surname</p>"+
             "<p>Email</p>"+
             "<p>Department</p>"+
             "</lgnx>"+
             "<lgnx class=\"style-4\">"+
             "<input type=\"text\" id=\"updateusername\" value=\""+usernameretr+"\">"+
             "<input type=\"password\" id=\"updateoldpassword\" >"+
             "<input type=\"password\" id=\"updatenewpassword\" >"+
             "<input type=\"text\" id=\"updatename\" value=\""+name+"\">"+
             "<input type=\"text\" id=\"updatesurname\" value=\""+surname+"\">"+
             "<input type=\"text\" id=\"updateemail\" value=\""+email+"\">"+
             "<input type=\"text\" id=\"updatedepartment\" value=\""+department+"\">"+
             "</lgnx>"+
        	 "<buttonupdate class=\"hvr-grow-shadow\" onclick=\"Update()\">Update Changes</buttonupdate>"
            		);
            
            
            
            
            
           
           
          

           
        }catch(Exception e){
        	
        	out.println("This user does not exists");
        }
		
	}

}
