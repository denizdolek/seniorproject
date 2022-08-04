package seniorP;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class DataGenServlet
 */
@WebServlet("/DataGenServlet")
public class DataGenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int course_id;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataGenServlet() {
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
		int counter=0;
		ArrayList<Integer> lecturerList = new ArrayList<Integer>();
		try
		{
			
			String url = "jdbc:mysql://localhost:3306/seniorproject";
	        String driver = "com.mysql.jdbc.Driver";
	        String userName = "denz";
	        String password = "denz";
	        int profnum=1;

			
		
			int number_of_departments=Integer.parseInt(request.getParameter("numberofdepartments"));
			int Lecturer_number=Integer.parseInt(request.getParameter("numberoflecturers"));
			int room_20=Integer.parseInt(request.getParameter("capacity20"));
			int room_45=Integer.parseInt(request.getParameter("capacity45"));
			int room_80=Integer.parseInt(request.getParameter("capacity20"));
			int room_lab=Integer.parseInt(request.getParameter("typelab"));
			String departmentcourseall=request.getParameter("departmentcourseall");

			String[] departmentinfo=departmentcourseall.split("_");
			int length=departmentinfo.length;
			
			String[][] dumpInputx = new String[length][5];
			Integer[][] dumpInput = new Integer[length][5];
			for(int i=0;i<length;i++)
			{
				dumpInputx[i]=departmentinfo[i].split("/");
			
			}
			for(int j=0;j<length;j++)
			{
				for(int x=0;x<5;x++)
				{
					dumpInput[j][x]=Integer.parseInt(dumpInputx[j][x]);
				}
			}
		
			int i=0;
			int static_id=0;
		
			int size=0;
		
		
		
			Class.forName(driver).newInstance();
			Connection conn=DriverManager.getConnection(url,userName,password);
			
			PreparedStatement pstmt= conn.prepareStatement("Truncate roomtable");
			pstmt.executeUpdate();
			pstmt= conn.prepareStatement("Truncate lecturertable");
			pstmt.executeUpdate();
			pstmt= conn.prepareStatement("Truncate coursetable");
			pstmt.executeUpdate();
			
			
			size=20;
			int room_id=0;
			RoomDC room;
			for(i=0;i<room_20;i++)
			{
				if(i%10==0)
				{
					room_id+=100;
				}
				room_id++;
				room=new RoomDC(room_id,size,"20");
				pstmt=conn.prepareStatement("INSERT roomtable SET room_id=? ,room_size=? , room_type=?");
				pstmt.setInt(1, RoomDC.getRoom_id());
				pstmt.setInt(2, RoomDC.getRoom_size());
				pstmt.setString(3, RoomDC.getRoom_type());
				pstmt.executeUpdate();
			}
			size=45;
			for(i=0;i<room_45;i++)
			{
				if(i%10==0)
				{
					room_id+=100;
				}
				room_id++;
				room=new RoomDC(room_id,size,"45");
				pstmt=conn.prepareStatement("INSERT roomtable SET room_id=? ,room_size=? , room_type=?");
				pstmt.setInt(1, RoomDC.getRoom_id());
				pstmt.setInt(2, RoomDC.getRoom_size());
				pstmt.setString(3, RoomDC.getRoom_type());
				pstmt.executeUpdate();
			}
			size=80;
			for(i=0;i<room_80;i++)
			{
				if(i%10==0)
				{
					room_id+=100;
				}
				room_id++;
				room=new RoomDC(room_id,size,"80");
				pstmt=conn.prepareStatement("INSERT roomtable SET room_id=? ,room_size=? , room_type=?");
				pstmt.setInt(1, RoomDC.getRoom_id());
				pstmt.setInt(2, RoomDC.getRoom_size());
				pstmt.setString(3, RoomDC.getRoom_type());
				pstmt.executeUpdate();
			}
			size=45;
			for(i=0;i<room_lab;i++)
			{
				if(i%10==0)
				{
					room_id+=100;
				}
				room_id++;
				room=new RoomDC(room_id,size,"lab");
				pstmt=conn.prepareStatement("INSERT roomtable SET room_id=? ,room_size=? , room_type=?");
				pstmt.setInt(1, RoomDC.getRoom_id());
				pstmt.setInt(2, RoomDC.getRoom_size());
				pstmt.setString(3, RoomDC.getRoom_type());
				pstmt.executeUpdate();
			}
			
			int lecturer_id=100;
			   double math_ran;
			   String preferenceString;
			   for(i=0;i<Lecturer_number;i++)
			   {
			    math_ran = Math.random();
			       preferenceString = "null" ;
			    //lecturer preferences starts here
			    if(math_ran>=0.1 && math_ran<=0.13){
			     preferenceString="1-1&10/2-1&10/5-1&5";    
			    }     
			    else if(math_ran>=0.13 && math_ran <=0.17){
			     preferenceString="3-5&10/4-1&10";
			    }     
			    else if(math_ran>=0.17 && math_ran <=0.19){
			     preferenceString="1-1&10/2-1&5/3-1&5/4-5&10";
			    }
			    
			    
			    
			    //lecturer preferences ends here
			    lecturer_id+=1;
			    profnum++;
			    String name= "A"+Integer.toString(profnum) ; 
			    pstmt=conn.prepareStatement("INSERT lecturertable SET lecturer_id=?, lecturername=?, lecturer_preferences=?");
			    pstmt.setInt(1, lecturer_id);
			    pstmt.setString(2, name);
			    pstmt.setString(3, preferenceString);
			    pstmt.executeUpdate();
			    lecturerList.add(lecturer_id);
			    
			   }
			Iterator<Integer> iterator_lecList = lecturerList.iterator(); // define Lecturer list' iterator
			boolean is_mandatory = true;
			CourseDC myCourse;
			course_id=0;	
			boolean heavyballs;
			
			Integer[][] course_dep =  dumpInput;//new int[5][number_of_departments];
			for (i=0;i<number_of_departments;i++){
				static_id+=1000;
				course_id=static_id;
				for (int rows=0;rows<5;rows++){
					course_id+=100;
					for ( int element=0 ; element<course_dep[i][rows];element++ )
					{
						course_id+=1;
						if(rows == 4)
							is_mandatory=false;
						else
							is_mandatory=true;

						// COURSE HOUR
						double random_numb = Math.random();	
						int course_hour;
						if (random_numb<=0.05){
							course_hour=5;
						}
						else if(random_numb>0.1 && random_numb<0.3){
							course_hour=4;
						}
						else if(random_numb>=0.3 && random_numb<0.5){
							course_hour=2;
						}
						else if(random_numb>=0.5 && random_numb<0.99){
							course_hour=3;
						}
						else{
							course_hour=6;
						}
						// COURSE TYPE
						double thisRand = Math.random();
						String course_type = "";
						if (thisRand < 0.2)
							course_type="20";
						else if(thisRand>=0.2 && thisRand<=0.95)
							course_type="45";
						else if(thisRand>=0.95 && thisRand<=1.0)
							course_type="80";

						// SECTIONS
						random_numb = Math.random();
						
						 
						if(random_numb<0.15)
						       heavyballs=true;      
						      else
						       heavyballs=false;
						      
						
						int course_section;
						
						//int selectedlecturer=lectureridgiver(border, lecturer_idArray, rand, i+1, Lecturer_number);
						 int temp_lecturer_1=0;
						
						if (random_numb<=0.02){
							course_section=3;
							 if(!iterator_lecList.hasNext() )
						      {
						       iterator_lecList=lecturerList.iterator(); // new contact with iterator_leclist       
						      }
						      temp_lecturer_1 = iterator_lecList.next().intValue();
							for (int course_sec_iterator=1;course_sec_iterator<=course_section;course_sec_iterator++){							
								if(course_hour==4)
								{

									course_id++; 
									
									myCourse = new CourseDC(course_id,is_mandatory,i+1,rows+1,course_sec_iterator,2,course_type);
									pstmt=conn.prepareStatement("INSERT coursetable SET course_id=? ,is_Mandatory=? ,department=? ,year=? ,section=? ,hour=? ,course_type=?, course_lecturer=?,heavylec=?");
									pstmt.setInt(1, myCourse.getCourse_id());
									pstmt.setBoolean(2, myCourse.isMandatory() );
									pstmt.setInt(3, myCourse.getBelongs_Department_I());
									pstmt.setInt(4, myCourse.getYear());
									pstmt.setInt(5, myCourse.getSection());
									pstmt.setInt(6, myCourse.getHour());
									pstmt.setString(7, myCourse.getCourse_type());
									pstmt.setInt(8, temp_lecturer_1);
									pstmt.setBoolean(9, heavyballs);
									pstmt.executeUpdate();
									counter++;

									
									if(Math.random()<0.2)
									{			
										course_id++;
										myCourse = new CourseDC(course_id,is_mandatory,i+1,rows+1,course_sec_iterator,2,"lab");
										pstmt=conn.prepareStatement("INSERT coursetable SET course_id=? ,is_Mandatory=? ,department=? ,year=? ,section=? ,hour=? ,course_type=?,course_lecturer=?,heavylec=?");
										pstmt.setInt(1, myCourse.getCourse_id());
										pstmt.setBoolean(2, myCourse.isMandatory() );
										pstmt.setInt(3, myCourse.getBelongs_Department_I());
										pstmt.setInt(4, myCourse.getYear());
										pstmt.setInt(5, myCourse.getSection());
										pstmt.setInt(6, myCourse.getHour());
										pstmt.setString(7, myCourse.getCourse_type());
										pstmt.setInt(8, temp_lecturer_1);
										pstmt.setBoolean(9, heavyballs);
										pstmt.executeUpdate();
										counter++;
									}
									else{
										course_id++;
										myCourse = new CourseDC(course_id,is_mandatory,i+1,rows+1,course_sec_iterator,2,course_type);
										pstmt=conn.prepareStatement("INSERT coursetable SET course_id=? ,is_Mandatory=? ,department=? ,year=? ,section=? ,hour=? ,course_type=?, course_lecturer=?,heavylec=?");
										pstmt.setInt(1, myCourse.getCourse_id());
										pstmt.setBoolean(2, myCourse.isMandatory() );
										pstmt.setInt(3, myCourse.getBelongs_Department_I());
										pstmt.setInt(4, myCourse.getYear());
										pstmt.setInt(5, myCourse.getSection());
										pstmt.setInt(6, myCourse.getHour());
										pstmt.setString(7, myCourse.getCourse_type());
										pstmt.setInt(8, temp_lecturer_1);
										pstmt.setBoolean(9, heavyballs);
										pstmt.executeUpdate();
										counter++;
									}
								}
								else if(course_hour == 5){
									course_id++;
									myCourse = new CourseDC(course_id,is_mandatory,i+1,rows+1,course_sec_iterator,3,course_type);								
									pstmt=conn.prepareStatement("INSERT coursetable SET course_id=? ,is_Mandatory=? ,department=? ,year=? ,section=? ,hour=? ,course_type=?, course_lecturer=?,heavylec=?");
									pstmt.setInt(1, myCourse.getCourse_id());
									pstmt.setBoolean(2, myCourse.isMandatory() );
									pstmt.setInt(3, myCourse.getBelongs_Department_I());
									pstmt.setInt(4, myCourse.getYear());
									pstmt.setInt(5, myCourse.getSection());
									pstmt.setInt(6, myCourse.getHour());
									pstmt.setString(7, myCourse.getCourse_type());
									pstmt.setInt(8, temp_lecturer_1);
									pstmt.setBoolean(9, heavyballs);
									pstmt.executeUpdate();
									counter++;
									course_id++;
									myCourse = new CourseDC(course_id,is_mandatory,i+1,rows+1,course_sec_iterator,2,"lab");
									pstmt=conn.prepareStatement("INSERT coursetable SET course_id=? ,is_Mandatory=? ,department=? ,year=? ,section=? ,hour=? ,course_type=?, course_lecturer=?,heavylec=?");
									pstmt.setInt(1, myCourse.getCourse_id());
									pstmt.setBoolean(2, myCourse.isMandatory() );
									pstmt.setInt(3, myCourse.getBelongs_Department_I());
									pstmt.setInt(4, myCourse.getYear());
									pstmt.setInt(5, myCourse.getSection());
									pstmt.setInt(6, myCourse.getHour());
									pstmt.setString(7, myCourse.getCourse_type());
									pstmt.setInt(8, temp_lecturer_1);
									pstmt.setBoolean(9, heavyballs);
									pstmt.executeUpdate();
									counter++;
								}
								else{
									course_id++;
									myCourse = new CourseDC(course_id,is_mandatory,i+1,rows+1,course_sec_iterator,3,course_type);
									pstmt=conn.prepareStatement("INSERT coursetable SET course_id=? ,is_Mandatory=? ,department=? ,year=? ,section=? ,hour=? ,course_type=?, course_lecturer=?,heavylec=?");
									pstmt.setInt(1, myCourse.getCourse_id());
									pstmt.setBoolean(2, myCourse.isMandatory() );
									pstmt.setInt(3, myCourse.getBelongs_Department_I());
									pstmt.setInt(4, myCourse.getYear());
									pstmt.setInt(5, myCourse.getSection());
									pstmt.setInt(6, myCourse.getHour());
									pstmt.setString(7, myCourse.getCourse_type());
									pstmt.setInt(8, temp_lecturer_1);
									pstmt.setBoolean(9, heavyballs);
									pstmt.executeUpdate();
									counter++;
								}
							}
						}
						else if(random_numb>0.02 && random_numb<0.15){
							course_section=2;
							 if(!iterator_lecList.hasNext() )
						      {
						       iterator_lecList=lecturerList.iterator(); // new contact with iterator_leclist       
						      }
						      temp_lecturer_1 = iterator_lecList.next().intValue();
							for (int course_sec_it=1;course_sec_it<=course_section;course_sec_it++){
								if(course_hour==4)
								{

									course_id++; 
									myCourse = new CourseDC(course_id,is_mandatory,i+1,rows+1,course_sec_it,2,course_type);
									pstmt=conn.prepareStatement("INSERT coursetable SET course_id=? ,is_Mandatory=? ,department=? ,year=? ,section=? ,hour=? ,course_type=?, course_lecturer=?,heavylec=?");
									pstmt.setInt(1, myCourse.getCourse_id());
									pstmt.setBoolean(2, myCourse.isMandatory() );
									pstmt.setInt(3, myCourse.getBelongs_Department_I());
									pstmt.setInt(4, myCourse.getYear());
									pstmt.setInt(5, myCourse.getSection());
									pstmt.setInt(6, myCourse.getHour());
									pstmt.setString(7, myCourse.getCourse_type());
									pstmt.setInt(8, temp_lecturer_1);
									pstmt.setBoolean(9, heavyballs);
									pstmt.executeUpdate();
									counter++;

									
									double a=Math.random();
									if(a <0.2)
									{		
										course_id++;
										myCourse = new CourseDC(course_id,is_mandatory,i+1,rows+1,course_sec_it,2,"lab");
										pstmt=conn.prepareStatement("INSERT coursetable SET course_id=? ,is_Mandatory=? ,department=? ,year=? ,section=? ,hour=? ,course_type=?, course_lecturer=?,heavylec=?");
										pstmt.setInt(1, myCourse.getCourse_id());
										pstmt.setBoolean(2, myCourse.isMandatory() );
										pstmt.setInt(3, myCourse.getBelongs_Department_I());
										pstmt.setInt(4, myCourse.getYear());
										pstmt.setInt(5, myCourse.getSection());
										pstmt.setInt(6, myCourse.getHour());
										pstmt.setString(7, myCourse.getCourse_type());
										pstmt.setInt(8, temp_lecturer_1);
										pstmt.setBoolean(9, heavyballs);
										pstmt.executeUpdate();
										counter++;
									}
									else{
										course_id++;
										myCourse = new CourseDC(course_id,is_mandatory,i+1,rows+1,course_sec_it,2,course_type);
										pstmt=conn.prepareStatement("INSERT coursetable SET course_id=? ,is_Mandatory=? ,department=? ,year=? ,section=? ,hour=? ,course_type=?, course_lecturer=?,heavylec=?");
										pstmt.setInt(1, myCourse.getCourse_id());
										pstmt.setBoolean(2, myCourse.isMandatory() );
										pstmt.setInt(3, myCourse.getBelongs_Department_I());
										pstmt.setInt(4, myCourse.getYear());
										pstmt.setInt(5, myCourse.getSection());
										pstmt.setInt(6, myCourse.getHour());
										pstmt.setString(7, myCourse.getCourse_type());
										pstmt.setInt(8, temp_lecturer_1);
										pstmt.setBoolean(9, heavyballs);
										pstmt.executeUpdate();
										counter++;
									}
								}
								else if(course_hour == 5){
									course_id++;
									myCourse = new CourseDC(course_id,is_mandatory,i+1,rows+1,course_sec_it,3,course_type);								
									pstmt=conn.prepareStatement("INSERT coursetable SET course_id=? ,is_Mandatory=? ,department=? ,year=? ,section=? ,hour=? ,course_type=?, course_lecturer=?,heavylec=?");
									pstmt.setInt(1, myCourse.getCourse_id());
									pstmt.setBoolean(2, myCourse.isMandatory() );
									pstmt.setInt(3, myCourse.getBelongs_Department_I());
									pstmt.setInt(4, myCourse.getYear());
									pstmt.setInt(5, myCourse.getSection());
									pstmt.setInt(6, myCourse.getHour());
									pstmt.setString(7, myCourse.getCourse_type());
									pstmt.setInt(8, temp_lecturer_1);
									pstmt.setBoolean(9, heavyballs);
									pstmt.executeUpdate();
									counter++;

									course_id++;
									myCourse = new CourseDC(course_id,is_mandatory,i+1,rows+1,course_sec_it,2,"lab");
									pstmt=conn.prepareStatement("INSERT coursetable SET course_id=? ,is_Mandatory=? ,department=? ,year=? ,section=? ,hour=? ,course_type=?, course_lecturer=?,heavylec=?");
									pstmt.setInt(1, myCourse.getCourse_id());
									pstmt.setBoolean(2, myCourse.isMandatory() );
									pstmt.setInt(3, myCourse.getBelongs_Department_I());
									pstmt.setInt(4, myCourse.getYear());
									pstmt.setInt(5, myCourse.getSection());
									pstmt.setInt(6, myCourse.getHour());
									pstmt.setString(7, myCourse.getCourse_type());
									pstmt.setInt(8, temp_lecturer_1);
									pstmt.setBoolean(9, heavyballs);
									pstmt.executeUpdate();
									counter++;
								}
								else{
									course_id++;
									myCourse = new CourseDC(course_id,is_mandatory,i+1,rows+1,course_sec_it,3,course_type);
									pstmt=conn.prepareStatement("INSERT coursetable SET course_id=? ,is_Mandatory=? ,department=? ,year=? ,section=? ,hour=? ,course_type=?, course_lecturer=?,heavylec=?");
									pstmt.setInt(1, myCourse.getCourse_id());
									pstmt.setBoolean(2, myCourse.isMandatory() );
									pstmt.setInt(3, myCourse.getBelongs_Department_I());
									pstmt.setInt(4, myCourse.getYear());
									pstmt.setInt(5, myCourse.getSection());
									pstmt.setInt(6, myCourse.getHour());
									pstmt.setString(7, myCourse.getCourse_type());
									pstmt.setInt(8, temp_lecturer_1);
									pstmt.setBoolean(9, heavyballs);
									pstmt.executeUpdate();
									counter++;
								}
							}
						}
						else{
							course_section=1;
							 if(!iterator_lecList.hasNext() )
						      {
						       iterator_lecList=lecturerList.iterator(); // new contact with iterator_leclist       
						      }
						      temp_lecturer_1 = iterator_lecList.next().intValue();
							if(course_hour==4)
							{				
								course_id++; 
								myCourse = new CourseDC(course_id,is_mandatory,i+1,rows+1,course_section,2,course_type);
								pstmt=conn.prepareStatement("INSERT coursetable SET course_id=? ,is_Mandatory=? ,department=? ,year=? ,section=? ,hour=? ,course_type=?, course_lecturer=?,heavylec=?");
								pstmt.setInt(1, myCourse.getCourse_id());
								pstmt.setBoolean(2, myCourse.isMandatory() );
								pstmt.setInt(3, myCourse.getBelongs_Department_I());
								pstmt.setInt(4, myCourse.getYear());
								pstmt.setInt(5, myCourse.getSection());
								pstmt.setInt(6, myCourse.getHour());
								pstmt.setString(7, myCourse.getCourse_type());
								pstmt.setInt(8, temp_lecturer_1);
								pstmt.setBoolean(9, heavyballs);
								pstmt.executeUpdate();
								counter++;

								
								if(Math.random()<0.2)
								{		
									course_id++;
									myCourse = new CourseDC(course_id,is_mandatory,i+1,rows+1,course_section,2,"lab");
									pstmt=conn.prepareStatement("INSERT coursetable SET course_id=? ,is_Mandatory=? ,department=? ,year=? ,section=? ,hour=? ,course_type=?, course_lecturer=?,heavylec=?");
									pstmt.setInt(1, myCourse.getCourse_id());
									pstmt.setBoolean(2, myCourse.isMandatory() );
									pstmt.setInt(3, myCourse.getBelongs_Department_I());
									pstmt.setInt(4, myCourse.getYear());
									pstmt.setInt(5, myCourse.getSection());
									pstmt.setInt(6, myCourse.getHour());
									pstmt.setString(7, myCourse.getCourse_type());
									pstmt.setInt(8, temp_lecturer_1);
									pstmt.setBoolean(9, heavyballs);
									pstmt.executeUpdate();
									counter++;
								}
								else{
									course_id++;
									myCourse = new CourseDC(course_id,is_mandatory,i+1,rows+1,course_section,2,course_type);
									pstmt=conn.prepareStatement("INSERT coursetable SET course_id=? ,is_Mandatory=? ,department=? ,year=? ,section=? ,hour=? ,course_type=?, course_lecturer=?,heavylec=?");
									pstmt.setInt(1, myCourse.getCourse_id());
									pstmt.setBoolean(2, myCourse.isMandatory() );
									pstmt.setInt(3, myCourse.getBelongs_Department_I());
									pstmt.setInt(4, myCourse.getYear());
									pstmt.setInt(5, myCourse.getSection());
									pstmt.setInt(6, myCourse.getHour());
									pstmt.setString(7, myCourse.getCourse_type());
									pstmt.setInt(8, temp_lecturer_1);
									pstmt.setBoolean(9, heavyballs);
									pstmt.executeUpdate();
									counter++;
								}
							}
							else if(course_hour == 5){
								course_id++;
								myCourse = new CourseDC(course_id,is_mandatory,i+1,rows+1,course_section,3,course_type);								
								pstmt=conn.prepareStatement("INSERT coursetable SET course_id=? ,is_Mandatory=? ,department=? ,year=? ,section=? ,hour=? ,course_type=?, course_lecturer=?,heavylec=?");
								pstmt.setInt(1, myCourse.getCourse_id());
								pstmt.setBoolean(2, myCourse.isMandatory() );
								pstmt.setInt(3, myCourse.getBelongs_Department_I());
								pstmt.setInt(4, myCourse.getYear());
								pstmt.setInt(5, myCourse.getSection());
								pstmt.setInt(6, myCourse.getHour());
								pstmt.setString(7, myCourse.getCourse_type());
								pstmt.setInt(8, temp_lecturer_1);
								pstmt.setBoolean(9, heavyballs);
								pstmt.executeUpdate();
								counter++;

								course_id++;
								myCourse = new CourseDC(course_id,is_mandatory,i+1,rows+1,course_section,2,"lab");
								pstmt=conn.prepareStatement("INSERT coursetable SET course_id=? ,is_Mandatory=? ,department=? ,year=? ,section=? ,hour=? ,course_type=?, course_lecturer=?,heavylec=?");
								pstmt.setInt(1, myCourse.getCourse_id());
								pstmt.setBoolean(2, myCourse.isMandatory() );
								pstmt.setInt(3, myCourse.getBelongs_Department_I());
								pstmt.setInt(4, myCourse.getYear());
								pstmt.setInt(5, myCourse.getSection());
								pstmt.setInt(6, myCourse.getHour());
								pstmt.setString(7, myCourse.getCourse_type());
								pstmt.setInt(8, temp_lecturer_1);
								pstmt.setBoolean(9, heavyballs);
								pstmt.executeUpdate();
								counter++;
							}
							else{
								course_id++;
								myCourse = new CourseDC(course_id,is_mandatory,i+1,rows+1,course_section,3,course_type);
								pstmt=conn.prepareStatement("INSERT coursetable SET course_id=? ,is_Mandatory=? ,department=? ,year=? ,section=? ,hour=? ,course_type=?, course_lecturer=?,heavylec=?");
								pstmt.setInt(1, myCourse.getCourse_id());
								pstmt.setBoolean(2, myCourse.isMandatory() );
								pstmt.setInt(3, myCourse.getBelongs_Department_I());
								pstmt.setInt(4, myCourse.getYear());
								pstmt.setInt(5, myCourse.getSection());
								pstmt.setInt(6, myCourse.getHour());
								pstmt.setString(7, myCourse.getCourse_type());
								pstmt.setInt(8, temp_lecturer_1);
								pstmt.setBoolean(9, heavyballs);
								pstmt.executeUpdate();
								counter++;
							}						
						}
					}	
				course_id-=100;}	
			}
			
			
			
			conn.close();
			out.println(counter);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		
		
		
	}
	public int lectureridgiver(int border, int[] lecturerids, Random rand, int departmentnumber, int lecturernumber)
	{
		int low=border*(departmentnumber-1);
		int high=border*departmentnumber;
		int result=0,selected=0;
		boolean flag=true;
		if(Math.random()<0.75)
		{
		while(flag)
		{
			try{
				selected=rand.nextInt(high)+low;
				result=lecturerids[selected];
				flag=false;
				
			}catch(IndexOutOfBoundsException e)
			{
				high=selected;
				flag=true;
			}
		}
		}
		else
		{
			selected=rand.nextInt(lecturernumber);
			result=lecturerids[selected];
			
		}
		return result;
		
	}
	
	public void deleteTable(Connection conn,String tableName) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		
		PreparedStatement pstmt= conn.prepareStatement("TRUNCATE ? ");
		pstmt.setString(1, tableName);
		pstmt.executeUpdate();
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
