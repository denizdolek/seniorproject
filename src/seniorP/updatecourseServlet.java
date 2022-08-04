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
 * Servlet implementation class updatecourseServlet
 */
@WebServlet("/updatecourseServlet")
public class updatecourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		
		PrintWriter out=response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		int courseid=Integer.parseInt(request.getParameter("courseid"));
		int department=Integer.parseInt(request.getParameter("department"));
		String mand=request.getParameter("mandatory");
		boolean mandatory;
		if(Objects.equals(mand, "false"))
		{
			mandatory=false;
		}else
		{
			mandatory=true;
		}
		
		String group=request.getParameter("group");
		int year;
		if(Objects.equals(group, "Elective"))
		{
			year=5;
		}else
		{
			String[] yearx=group.split(":");
			year=Integer.parseInt(yearx[1]);
		}
		int hour=Integer.parseInt(request.getParameter("duration"));
		String course_type=request.getParameter("type");
		int section=Integer.parseInt(request.getParameter("section"));
		int course_lecturer=Integer.parseInt(request.getParameter("lecturer"));
		String is_heavylec=request.getParameter("is_heavylec");
		boolean heavylec;
		if(Objects.equals(is_heavylec, "false"))
		{
			heavylec=false;
		}else
		{
			heavylec=true;
		}
		
		
		
		
		String url = "jdbc:mysql://localhost:3306/seniorproject";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "baris";
        String passwordd = "kakale11";
        
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url,userName,passwordd);
            PreparedStatement pstmt = conn.prepareStatement("UPDATE coursetable SET is_Mandatory=? ,department=? ,year=? ,section=? ,hour=? ,course_type=? ,course_lecturer=? ,heavylec=?  WHERE course_id=? ");
            pstmt.setBoolean(1, mandatory);
            pstmt.setInt(2, department);
            pstmt.setInt(3, year);
            pstmt.setInt(4, section);
            pstmt.setInt(5, hour);
            pstmt.setString(6, course_type);
            pstmt.setInt(7, course_lecturer);
            pstmt.setBoolean(8, heavylec);
            pstmt.setInt(9, courseid);
            pstmt.executeUpdate();
            out.println("Selected Course is Updated");
	}catch(Exception e){
			out.println("Course update process is failed, Please try again later...");
			out.println(e.getMessage());
	}
        
	}

	

}
