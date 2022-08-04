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
 * Servlet implementation class bringcourseServlet
 */
@WebServlet("/bringcourseServlet")
public class bringcourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
		

	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		
		int courseid=Integer.parseInt(request.getParameter("courseid"));
		String url = "jdbc:mysql://localhost:3306/seniorproject";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "denz";
        String passwordd = "denz";
        
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url,userName,passwordd);
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM coursetable WHERE course_id=?");
            pstmt.setInt(1, courseid);
            
            ResultSet rs=pstmt.executeQuery();
            rs.next();
            
            int department=rs.getInt("department");
            boolean is_mandatory=rs.getBoolean("is_Mandatory");
            int year=rs.getInt("year");
            String group;
            if(year==5)
            {
            	group="Elective";
            }
            else
            {
            	group="Year:"+year;
            }
            int section=rs.getInt("section");
            int duration=rs.getInt("hour");
            String type=rs.getString("course_type");
            int lecturer=rs.getInt("course_lecturer");
            boolean is_heavylec=rs.getBoolean("heavylec");
            
            
            out.println("<loverrightsideinfo1>");
            out.println("<loverrightsideinfoinsideblock7>");
            out.println("<loverrightsideinfoinsideblock6><p>Department of Course</p></loverrightsideinfoinsideblock6>");
            out.println("<loverrightsideinfoinsideblock6><p><input type=\"text\" id=\"department\" value=\""+department+"\"></p></loverrightsideinfoinsideblock6>");
            out.println("</loverrightsideinfoinsideblock7>");
            out.println("<loverrightsideinfoinsideblock7>");
            out.println("<loverrightsideinfoinsideblock6><p>Mandatory</p></loverrightsideinfoinsideblock6>");
            out.println("<loverrightsideinfoinsideblock6><p><input type=\"text\" id=\"mandatory\" value=\""+is_mandatory+"\"></p></loverrightsideinfoinsideblock6>");
            out.println("</loverrightsideinfoinsideblock7>");
            out.println("<loverrightsideinfoinsideblock7>");
            out.println("<loverrightsideinfoinsideblock6><p>Group of Course</p></loverrightsideinfoinsideblock6>");
            out.println("<loverrightsideinfoinsideblock6><p><input type=\"text\" id=\"group\" value=\""+group+"\"></p></loverrightsideinfoinsideblock6>");
            out.println("</loverrightsideinfoinsideblock7>");
            out.println("<loverrightsideinfoinsideblock7>");
            out.println("<loverrightsideinfoinsideblock6><p>Duration of Course</p></loverrightsideinfoinsideblock6>");
            out.println("<loverrightsideinfoinsideblock6><p><input type=\"text\" id=\"duration\" value=\""+duration+"\"></p></loverrightsideinfoinsideblock6>");
            out.println("</loverrightsideinfoinsideblock7>");
            out.println("<loverrightsideinfoinsideblock7>");
            out.println("<loverrightsideinfoinsideblock6><p>Section</p></loverrightsideinfoinsideblock6>");
            out.println("<loverrightsideinfoinsideblock6><p><input type=\"text\" id=\"section\" value=\""+section+"\"></p></loverrightsideinfoinsideblock6>");
            out.println("</loverrightsideinfoinsideblock7>");
            out.println("<loverrightsideinfoinsideblock7>");
            out.println("<loverrightsideinfoinsideblock6><p>Type of Course</p></loverrightsideinfoinsideblock6>");
            out.println("<loverrightsideinfoinsideblock6><p><input type=\"text\" id=\"type\" value=\""+type+"\"></p></loverrightsideinfoinsideblock6>");
            out.println("</loverrightsideinfoinsideblock7>");
            out.println("</loverrightsideinfo1>");
            out.println("<loverrightsideinfo1>");
            out.println("<loverrightsideinfoinsideblock7>");
            out.println("<loverrightsideinfoinsideblock6><p>Lecturer of Course</p></loverrightsideinfoinsideblock6>");
            out.println("<loverrightsideinfoinsideblock6><p><input type=\"text\" id=\"lecturer\" value=\""+lecturer+"\"></p></loverrightsideinfoinsideblock6>");
            out.println("</loverrightsideinfoinsideblock7>");
            out.println("<loverrightsideinfoinsideblock7>");
            out.println("<loverrightsideinfoinsideblock6><p>Heavy Workload</p></loverrightsideinfoinsideblock6>");
            out.println("<loverrightsideinfoinsideblock6><p><input type=\"text\" id=\"is_heavylec\" value=\""+is_heavylec+"\"></p></loverrightsideinfoinsideblock6>");
            out.println("</loverrightsideinfoinsideblock7>");
            out.println("<loverrightsideinfoinsideblock8>");
            out.println("<loverrightsideinfoinsideblock9><buttond class=\"hvr-grow-shadow\" id=\""+courseid+"\" onclick=\"updatecoursechanges(this.id)\">Update Changes</buttond></loverrightsideinfoinsideblock9>");
            
            out.println("</loverrightsideinfoinsideblock8>");
            out.println("<loverrightsideinfoinsideblock8>");
            out.println("<loverrightsideinfoinsideblock9><buttond class=\"hvr-grow-shadow\" id=\""+courseid+"\" onclick=\"Deletecourse(this.id)\">Delete Course</buttond></loverrightsideinfoinsideblock9>");
            
            out.println("</loverrightsideinfoinsideblock8>");
            out.println("</loverrightsideinfo1>");
            
            
            
           
            
            conn.close();
            rs.close();
	}catch(Exception e){
		out.println(e.getMessage());
	}

	

}}
