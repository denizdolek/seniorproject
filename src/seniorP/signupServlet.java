package seniorP;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class signupServlet
 */
@WebServlet("/signupServlet")
public class signupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String surname=request.getParameter("surname");
		String email=request.getParameter("email");
		String department=request.getParameter("department");
		
		
		String url = "jdbc:mysql://localhost:3306/seniorproject";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "denz";
        String passwordd = "denz";

        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url,userName,passwordd);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO user VALUES(?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, name);
            pstmt.setString(4, surname);
            pstmt.setString(5, email);
            pstmt.setString(6, department);
            pstmt.setInt(7, 0);
            
            pstmt.executeUpdate();
            
            
            
            out.println(1);
        }
        catch(SQLException e){
            if(e.getErrorCode() == 1062 ){
                out.println("This user name already exists");
            }else{
            	out.println("Password must be filled");
            }}
		catch(Exception e){
			out.println("System is not available right now, please try again later");
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
