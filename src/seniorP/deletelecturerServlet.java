package seniorP;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class deletelecturerServlet
 */
@WebServlet("/deletelecturerServlet")
public class deletelecturerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		
		int lecturerid=Integer.parseInt(request.getParameter("lecturerid"));
		String url = "jdbc:mysql://localhost:3306/seniorproject";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "denz";
        String passwordd = "denz";
        
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url,userName,passwordd);
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM lecturer WHERE LECTURER_ID=?");
            pstmt.setInt(1, lecturerid);
            pstmt.executeUpdate();
            out.println("Selected Lecturer is Deleted");
	}catch(Exception e){
			out.println("Lecturer deletion process failed, Please try again later...");
	}

		
	}

	

}
