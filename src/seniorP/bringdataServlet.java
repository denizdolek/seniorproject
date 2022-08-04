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
 * Servlet implementation class bringdataServlet
 */
@WebServlet("/bringdataServlet")
public class bringdataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out= response.getWriter();
		String url = "jdbc:mysql://localhost:3306/seniorproject";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "denz";
        String passwordd = "denz";
        
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url,userName,passwordd);
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM lecturertable");
           
            ResultSet rs = pstmt.executeQuery();
            out.println("<bringdataselection>");
            out.println("<select class=\"rootpageselection\" onchange=\"bringlecturer(this.value)\">");
            out.println("<option value=\"selectlecturer\">Select lecturer to bring detailed information</option>");
            while(rs.next()){
            	String lecturername=rs.getString("lecturername");
            	String lecturerid=Integer.toString(rs.getInt("lecturer_id"));
            	
            	out.println("<option value=\""+lecturerid+"\">"+lecturername+"</option>");
            }
            out.println("</select>");
            out.println("</bringdataselection>");
            
            pstmt=conn.prepareStatement("SELECT * FROM coursetable");
            ResultSet rsx = pstmt.executeQuery();
            out.println("<bringdataselection>");
            out.println("<select class=\"rootpageselection\"  onchange=\"bringcourse(this.value)\">");
            out.println("<option value=\"selectlecturer\">Course ID/option>");
            while(rsx.next()){
            	

            	String courseid=Integer.toString(rsx.getInt("course_id"));
            	out.println("<option value=\""+courseid+"\">"+courseid+"</option>");
            	
            }
            out.println("</select>");
            out.println("</bringdataselection>");
            conn.close();
            rs.close();
            rsx.close();
            
        }catch(Exception e){
        	out.println(e.getMessage());
        }
		
		
	}

	
}
