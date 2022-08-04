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
 * Servlet implementation class ManageTimetableServlet
 */
@WebServlet("/ManageTimetableServlet")
public class ManageTimetableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageTimetableServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		String url = "jdbc:mysql://localhost:3306/seniorproject";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "denz";
        String passwordd = "denz";
        try{
        	
        	Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url,userName,passwordd);
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM lecturertable");
            ResultSet rs = pstmt.executeQuery();
            
            
        	out.println("<loverrightsideinfo1>");
            out.println("<loverrightsideinfoinsideblock7>");
            out.println("<select id=\"lect\" class=\"rootpageselection\" >");
            out.println("<option value=\"all\">Select Lecturer </option>");
            while(rs.next()){
            	String lecturername=rs.getString("lecturername");
            	String lecturerid=Integer.toString(rs.getInt("lecturer_id"));
            	
            	out.println("<option value=\""+lecturerid+"\">"+lecturername+"</option>");
            }
            out.println("</select>");
            out.println("</loverrightsideinfoinsideblock7>");
            
            
            pstmt=conn.prepareStatement("SELECT * FROM roomtable");
            rs=pstmt.executeQuery();
            
            
            out.println("<loverrightsideinfoinsideblock7>");
            out.println("<select id=\"room\" class=\"rootpageselection\" >");
            out.println("<option value=\"all\">Select Room </option>");
            while(rs.next()){
            	
            	String roomid=Integer.toString(rs.getInt("room_id"));
            	
            	out.println("<option value=\""+roomid+"\">"+roomid+"</option>");
            }
            out.println("</select>");
            out.println("</loverrightsideinfoinsideblock7>");
            
            pstmt = conn.prepareStatement("SELECT MAX(Department) FROM coursetable");
            rs=pstmt.executeQuery();
            rs.next();
            int max = rs.getInt(1);
            
            out.println("<loverrightsideinfoinsideblock7>");
            out.println("<select id=\"dept\" class=\"rootpageselection\" >");
            out.println("<option value=\"all\">Select Department </option>");
            for(int i=1;i<=max;i++){
            	
            	out.println("<option value=\""+i+"\">Department : "+i+"</option>");
            	
            }
            out.println("</select>");
            out.println("</loverrightsideinfoinsideblock7>");
            
            out.println("<loverrightsideinfoinsideblock7>");
            out.println("<select id=\"year\" class=\"rootpageselection\" >");
            out.println("<option value=\"all\">Select Year </option>");
            for(int i=1;i<=5;i++){
            	if(i==5)
            	{
            		out.println("<option value=\""+i+"\">Elective</option>");
            	}
            	else
            	{
            		out.println("<option value=\""+i+"\">Year : "+i+"</option>");
            	}
            	
            	
            }
            out.println("</select>");
            out.println("</loverrightsideinfoinsideblock7>");
            
            out.println("<loverrightsideinfoinsideblock7>");
            out.println("<buttone class=\"hvr-grow-shadow\" onclick=\"bringGAresult()\">Bring Results</buttone>");
            out.println("</loverrightsideinfoinsideblock7>");

            
            out.println("</loverrightsideinfo1>");
            
            
            
            
            pstmt.close();
            conn.close();
        }catch(Exception e)
        {
        	e.printStackTrace();
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
