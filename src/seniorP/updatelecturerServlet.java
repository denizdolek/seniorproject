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
 * Servlet implementation class updatelecturerServlet
 */
@WebServlet("/updatelecturerServlet")
public class updatelecturerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		
		int lecturerid=Integer.parseInt(request.getParameter("lecturerid"));
		String lecturersname=request.getParameter("lecturersname");
		String preferences =request.getParameter("preferences");
		System.out.println(lecturersname);
		System.out.println(preferences);
		System.out.println(lecturerid);
		String url = "jdbc:mysql://localhost:3306/seniorproject";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "denz";
        String passwordd = "denz";
        String pref="";
        String[] preferencelist;
		String[] daysHours;
		String[] hourlimits;
		String value;
		int x,y;
		
        if(!Objects.equals(preferences, "null"))
        {
        	preferencelist=preferences.split("/");
    		
    		for(String prefx:preferencelist)
    		{
        		
    			if(Objects.equals(prefx, "") || Objects.equals(prefx, null))
    			{
    				continue;
    			}
    			else
    			{
    				value="";
            		daysHours=prefx.split("-");    
            		value=value+daysHours[0]+"-";
            	
            		
            		hourlimits=daysHours[1].split(" ");
            		x=Integer.parseInt(hourlimits[0])-9;
            		y=Integer.parseInt(hourlimits[1])-9;
            		value=value+x+"&"+y;
            	
            		
            		pref=pref+value+"/";
    			}
        		
        }
        
		
    		
    		
		}else
		{
			pref="null";
		}
		
		 try {
	            Class.forName(driver).newInstance();
	            Connection conn = DriverManager.getConnection(url,userName,passwordd);
	            PreparedStatement pstmt = conn.prepareStatement("UPDATE lecturertable SET lecturername=? ,lecturer_preferences=? WHERE lecturer_id=? ");
	            
	            pstmt.setString(1, lecturersname);
	            pstmt.setString(2, pref);
	            pstmt.setInt(3, lecturerid);
	            
	            pstmt.executeUpdate();
	            out.println("Selected lecturer is Updated");
	            conn.close();
	            pstmt.close();
			}catch(Exception e){
					out.println("lecturer update process is failed, Please try again later...");
					out.println(e.getMessage());
			}
			
		
        
	}

	

}
