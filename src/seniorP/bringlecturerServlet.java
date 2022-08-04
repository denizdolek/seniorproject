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
 * Servlet implementation class bringlecturerServlet
 */
@WebServlet("/bringlecturerServlet")
public class bringlecturerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM lecturertable WHERE lecturer_id=?");
            pstmt.setInt(1, lecturerid);
            ResultSet rs=pstmt.executeQuery();
            rs.next();
            String lecturersname=rs.getString("lecturername");
            String preference=rs.getString("lecturer_preferences");
            String[] preferencelist;
			String[] daysHours;
			String[] hourlimits;
            String dayandhour;
            String value;
            int x,y,i;
            out.println("<loverrightsideinfo1>");
            out.println("<loverrightsideinfoinsideblock>");
            out.println("<loverrightsideinfoinsideblock2><p>Name of Lecturer</p></loverrightsideinfoinsideblock2>");
            out.println("<loverrightsideinfoinsideblock2><p><input type=\"text\" id=\"lecturersname\" value=\""+lecturersname+"\"></p></loverrightsideinfoinsideblock2>");
            out.println("</loverrightsideinfoinsideblock>");
            out.println("<loverrightsideinfoinsideblock>");
            out.println("<loverrightsideinfoinsideblock2><p>Preferences of Lecturer</p></loverrightsideinfoinsideblock2>");
            out.println("<loverrightsideinfoinsideblock2>");
            out.println("<select class=\"rootpageselection\" id=\"prefselector\" onchange=\"bringpref(this.value)\">");
            
            if(Objects.equals(preference, "null"))
            {
            	 out.println("<option value=\"null\">"+preference+"</option>");
            }
            else
            {
            	i=0;
            	preferencelist=preference.split("/");
            	for(String pref:preferencelist)
				{
            		i++;
            		dayandhour="";
            		value="";
            		daysHours=pref.split("-");    
            		value=value+daysHours[0]+"-";
            		dayandhour=dayandhour+"Day : "+daysHours[0];
            		
            		hourlimits=daysHours[1].split("&");
            		x=Integer.parseInt(hourlimits[0])+9;
            		y=Integer.parseInt(hourlimits[1])+9;
            		value=value+x+"+"+y;
            		dayandhour=dayandhour+" Between "+x+" and "+y;
            		
            		out.println("<option value=\""+value+"\" id=\""+i+"\">"+dayandhour+"</option>");
            		
            		
				}
            }
            out.println("</select>");
            out.println("</loverrightsideinfoinsideblock2>");
            out.println("</loverrightsideinfoinsideblock>");
            out.println("<loverrightsideinfoinsideblock>");
            out.println("<loverrightsideinfoinsideblock2>");
            out.println("<loverrightsideinfoinsideblock5><p>Day</p></loverrightsideinfoinsideblock5>");
            out.println("<loverrightsideinfoinsideblock5><p>Hour</p></loverrightsideinfoinsideblock5>");
            out.println("<loverrightsideinfoinsideblock5><p><input type=\"text\" id=\"day\" value=\"Day\"></p></loverrightsideinfoinsideblock5>");
            out.println("<loverrightsideinfoinsideblock5><p><input type=\"text\" id=\"hour\" value=\"Hour - Hour\"></p></loverrightsideinfoinsideblock5>");
            out.println("</loverrightsideinfoinsideblock2>");
            out.println("<loverrightsideinfoinsideblock2>");
            out.println("<loverrightsideinfoinsideblock3><buttonc1 class=\"hvr-grow-shadow\" onclick=\"Addpref()\">Add</buttonc1></loverrightsideinfoinsideblock3>");
            out.println("<loverrightsideinfoinsideblock3></loverrightsideinfoinsideblock3>");
            out.println("<loverrightsideinfoinsideblock3><buttonc2 class=\"hvr-grow-shadow\" onclick=\"Deletepref()\">Delete</buttonc2></loverrightsideinfoinsideblock3>");
            out.println("</loverrightsideinfoinsideblock2>");
            out.println("</loverrightsideinfoinsideblock>");
            out.println("</loverrightsideinfo1>");
            out.println("<loverrightsideinfo1>");
            out.println("<loverrightsideinfoinsideblock4><buttond class=\"hvr-grow-shadow\" id=\""+lecturerid+"\" onclick=\"updatelecturerchanges(this.id)\">Update Changes</buttond></loverrightsideinfoinsideblock4>");
            out.println("<loverrightsideinfoinsideblock4><buttond class=\"hvr-grow-shadow\" id=\""+lecturerid+"\" onclick=\"deletelecturer(this.id)\">Delete Lecturer</buttond></loverrightsideinfoinsideblock4>");
            out.println("</loverrightsideinfo1>");
           
           
            conn.close();
            rs.close();
            
        }catch(Exception e){
        	out.println(e.getMessage());
        }
		
		
		
		
	}

}
