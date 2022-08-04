package seniorP;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class BringresultServlet
 */
@WebServlet("/BringresultServlet")
public class BringresultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BringresultServlet() {
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
        ArrayList<Course> chromosome = new ArrayList<Course>();
        HashMap<String, ArrayList<Course>> timetableHashMap = new HashMap<String, ArrayList<Course>>();
        ArrayList<Course> maparraylist;
        String lecturer=request.getParameter("lecturer");
        String room=request.getParameter("room");
        int department=Integer.parseInt(request.getParameter("department"));
        String year=request.getParameter("year");
        int yearx=Integer.parseInt(year);
        
        try
        {
        	Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url,userName,passwordd);
            PreparedStatement pstmt;
            ResultSet rs;
            
            if(Objects.equals(lecturer, "all") && Objects.equals(room, "all"))
            {
            	if(yearx>2 && yearx<5)
            	{
            		pstmt=conn.prepareStatement("SELECT * FROM garesult WHERE departmentID=? AND (groupOfCourse=? OR groupOfCourse=?)");
                	
                	pstmt.setInt(1, department);
                	pstmt.setString(2, year);
                	pstmt.setString(3, "Elective");
            	}
            	else
            	{
            		pstmt=conn.prepareStatement("SELECT * FROM garesult WHERE departmentID=? AND groupOfCourse=?");
                	if(Objects.equals(year, "5"))
                	{
                		year="Elective";
                	}
                	pstmt.setInt(1, department);
                	pstmt.setString(2, year);
            	}
            	
            }else if(Objects.equals(lecturer, "all"))
            {
            	if(yearx>2 && yearx<5)
            	{
            		pstmt=conn.prepareStatement("SELECT * FROM garesult WHERE departmentID=? AND (groupOfCourse=? OR groupOfCourse=?)  AND roomID=?");
            		pstmt.setInt(1, department);
                	pstmt.setString(2, year);
                	pstmt.setString(3, "Elective");
                	pstmt.setString(4, room);
            	}else
            	{
            		pstmt=conn.prepareStatement("SELECT * FROM garesult WHERE departmentID=? AND groupOfCourse=? AND roomID=?");
                	if(Objects.equals(year, "5"))
                	{
                		year="Elective";
                	}
                	pstmt.setInt(1, department);
                	pstmt.setString(2, year);
                	pstmt.setString(3, room);
            	}
            		
            	

            	
            	
            }else if(Objects.equals(room, "all"))
            {
            	
            	int lect=Integer.parseInt(lecturer);
            	if(yearx>2 && yearx<5)
            	{
            		pstmt=conn.prepareStatement("SELECT * FROM garesult WHERE departmentID=? AND (groupOfCourse=? OR groupOfCourse=?) AND lecturerID=?");
            		pstmt.setInt(1, department);
                	pstmt.setString(2, year);
                	pstmt.setString(3, "Elective");
                	pstmt.setInt(4, lect);
            	}else
            	{
            		pstmt=conn.prepareStatement("SELECT * FROM garesult WHERE departmentID=? AND groupOfCourse=? AND lecturerID=?");
                	if(Objects.equals(year, "5"))
                	{
                		year="Elective";
                	}
                	pstmt.setInt(1, department);
                	pstmt.setString(2, year);
                	pstmt.setInt(3, lect);
            	}
            	
            }else
            {
            	int lect=Integer.parseInt(lecturer);
            	if(yearx>2 && yearx<5)
            	{
            		pstmt=conn.prepareStatement("SELECT * FROM garesult WHERE departmentID=? AND (groupOfCourse=? OR groupOfCourse=?) AND lecturerID=? AND roomID=?");
            		pstmt.setInt(1, department);
                	pstmt.setString(2, year);
                	pstmt.setString(3, "Elective");
                	pstmt.setInt(4, lect);
                	pstmt.setString(5, room);
            	}else
            	{
            		pstmt=conn.prepareStatement("SELECT * FROM garesult WHERE departmentID=? AND groupOfCourse=? AND lecturerID=? AND roomID=?");
                	if(Objects.equals(year, "5"))
                	{
                		year="Elective";
                	}
                	pstmt.setInt(1, department);
                	pstmt.setString(2, year);
                	pstmt.setInt(3, lect);
                	pstmt.setString(4, room);
            	}
            	
            }
        	
        	rs=pstmt.executeQuery();
        	
        	Course newcourse;
        	
        	while(rs.next())
        	{
        		newcourse= new Course();
        		newcourse.setCapacity(rs.getInt("capacity"));
        		newcourse.setCourseID(rs.getInt("courseID"));
        		newcourse.setDay(rs.getInt("day"));
        		newcourse.setHour(rs.getInt("hour"));
        		newcourse.setDepartmentID(rs.getInt("departmentID"));
        		newcourse.setDuration(rs.getInt("duration"));
        		newcourse.setGroupOfCourse(rs.getString("groupOfCourse"));
        		newcourse.setLecturerID(rs.getInt("lecturerID"));
        		newcourse.setRoomID(rs.getString("roomID"));
        		newcourse.setSection(rs.getInt("section"));
        		newcourse.setRoomType(rs.getString("roomType"));
        		chromosome.add(newcourse);
        	}
        	
        	String key;
        	int day,hour,i,j,k,length;
        	
        	for(Course course:chromosome)
        	{
        		for(i=0;i<course.getDuration();i++)
        		{
        			day=course.getDay();
        			hour=course.getHour()+i;
        			key=day+""+hour;
        			if(Objects.equals(null, timetableHashMap.get(key)))
    				{
        				maparraylist=new ArrayList<Course>();
        				maparraylist.add(course);
    					timetableHashMap.put(key, maparraylist);
    					
    					
    				}
        			else
        			{
        				maparraylist=timetableHashMap.get(key);
        				maparraylist.add(course);
        				timetableHashMap.put(key, maparraylist);
        			}
        			
        		}
        		
        		
        	}
        	out.println("<tablecontainer>");
        	out.println("<table class=\"tablecont\"><tr><th>        </th>");
        	out.println("<th>Monday</th>");
        	out.println("<th>Tuesday</th>");
        	out.println("<th>Wednesday</th>");
        	out.println("<th>Thursday</th>");
        	out.println("<th>Friday</th></tr>");
        	int houx;
        	String x;
        	for(j=1;j<=10;j++)
        	{
        		houx=j+8;
        		out.println("<tr>");
        		out.println("<td>"+houx+":00</td>");
        		
        		for(i=1;i<=5;i++)
        		{
        			key=i+""+j;
        			//System.out.println("key : "+key);
        			x="";
        			if(Objects.equals(null, timetableHashMap.get(key)))
    				{
    				out.println("<td></td>");
    				}
        			else
        			{
        				maparraylist=timetableHashMap.get(key);
        				//System.out.println(" Key ======> "+key);
        				for(Course course:maparraylist)
        				{
        					/**System.out.println(course.getCourseID());
        					System.out.println(course.getDay());
        					System.out.println(course.getHour());
        					System.out.println(course.getLecturerID());
        					System.out.println(course.getSection());
        					**/
        					x=x+"<tdcontain><p>Course ID : "+course.getCourseID()+" Lecturer : "+course.getLecturerID()+"</p><p> Section :"+course.getSection()+" Room : "+course.getRoomID()+"</p><p>Group : "+course.getGroupOfCourse()+"</p></tdcontain>";
        					
        				}
        				out.println("<td>"+x+"</td>");
        			}
        		}
        		out.println("</tr>");
        	}
        	
        	
        	
        	
        	
            out.println("</table>");
            out.println("</tablecontainer>");
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
