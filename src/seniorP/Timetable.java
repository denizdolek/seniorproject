package seniorP;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import java.util.Objects;


public class Timetable 
{
	
	ResultSet rsRoomLab;														//Holds room informations with type "lab"
	ResultSet rsRoom80;															//Holds room informations with type "normal-80"
	ResultSet rsRoom45;															//Holds room informations with type "normal-45"
	ResultSet rsRoom20;															//Holds room informations with type "normal-20"
	ResultSet rsCourses; 
	ResultSet rslecturers;
	private ArrayList<Course> chromosome;										//Holds the course objects. This chromosome is timetable, holds all necessary information in course objects
	private int HC_cost;														//Hard Constraint cost of this timetable
	private int SC_cost;														//Soft Constraint cost of this timetable
	private int Total_cost;														//Hard Constraint cost + Soft Constraint Cost
	private AvlTree2 lecturerTree;
	private Random random= new Random();										//It is called when program needs a random value
	private ArrayList<Room> roomsLab;											//It hold room objects with type "Lab"
	private ArrayList<Room> rooms80;											//It hold room objects with type "normal-80"
	private ArrayList<Room> rooms45;											//It hold room objects with type "normal-45"
	private ArrayList<Room> rooms20;											//It hold room objects with type "normal-20"
	private ArrayList<String> keyHolder;										//keyholder is the part of constraint violation detection
	private HashMap<String, Integer> lecturersHashMap;							//lecturersHashMap is the part of constraint violation detection
	private HashMap<String, Integer> roomsHashMap;								//roomsHashMap is the part of constraint violation detection
	private HashMap<String, Integer> departmentHashMap;							//departmentHashMap is the part of constraint violation detection
	private Integer[] costs=new Integer[2];			
	
	//Default Constructor
	public Timetable()
	{
		lecturersHashMap= new HashMap<String, Integer>();
		roomsHashMap=new HashMap<String, Integer>();
		departmentHashMap=new HashMap<String, Integer>();
		chromosome= new ArrayList<Course>();
		keyHolder=new ArrayList<String>();
		

		
		
	}
	//Parameterised Constructor
	public Timetable(ResultSet rsRoomLab, ResultSet rsRoom80, ResultSet rsRoom45, ResultSet rsRoom20, ResultSet rsCourses, ResultSet rslecturers)
	{
		this.rsRoomLab=rsRoomLab;
		this.rsRoom20=rsRoom20;
		this.rsRoom45=rsRoom45;
		this.rsRoom80=rsRoom80;
		this.rsCourses=rsCourses;
		this.rslecturers=rslecturers;
		roomsLab=new ArrayList<Room>();
		rooms80=new ArrayList<Room>();
		rooms45=new ArrayList<Room>();
		rooms20=new ArrayList<Room>();
		
		
		
		lecturersHashMap= new HashMap<String, Integer>();
		roomsHashMap=new HashMap<String, Integer>();
		departmentHashMap=new HashMap<String, Integer>();
		chromosome= new ArrayList<Course>();
		keyHolder=new ArrayList<String>();
		roomsLab=new ArrayList<Room>();
		rooms80=new ArrayList<Room>();
		rooms45=new ArrayList<Room>();
		rooms20=new ArrayList<Room>();
		
		
		
		try
		{
			
			
			fillRoom(rsRoomLab, rsRoom80, rsRoom45, rsRoom20);			// This function creates room objects for Timetable
			fillLecturerTree(rslecturers);
			createCourses();
			
			
			
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		
	}
	public void createCourses() throws Exception
	{
		rsCourses.first();
		int courseID=rsCourses.getInt("course_id");
		boolean heavyballs=rsCourses.getBoolean("heavylec");
		//System.out.println("heavy:"+heavyballs);
		//System.out.println(courseID);
		int duration=rsCourses.getInt("hour");
		int lecturerID=rsCourses.getInt("course_lecturer");
		int year=rsCourses.getInt("year");
		int section=rsCourses.getInt("section");
		String groupOfCourse;
		if(year==5)
		{
			groupOfCourse="Elective";
		}
		else
		{
			groupOfCourse=Integer.toString(year);
		}
		int capacity;
		String roomtype=rsCourses.getString("course_type");
		
		if(Objects.equals(roomtype, "lab"))
		{
			capacity=45;
		}else if(Objects.equals(roomtype, "80"))
		{
			capacity=80;
		}else if(Objects.equals(roomtype, "45"))
		{
			capacity=45;
		}else if(Objects.equals(roomtype, "20"))
		{
			capacity=20;
		}else
		{
			Exception e=new Exception("room type should be filled");
			throw e;
		}
		
		
		
		int departmentID=rsCourses.getInt("department");
		
		
		String roomID=null;										
		
																// Until this line program assigns general information of course object. After this line, program will assign random suitable room , day and hour to course object
		if(Objects.equals(roomtype, "lab"))
		{
			int size=roomsLab.size();
			int randomvalue=random.nextInt(size);
			roomID=roomsLab.get(randomvalue).getRoomID();
		}
		else if(Objects.equals(roomtype, "20"))
		{
			if(Math.random()<0.7)
			{
			int size=rooms20.size();
			int randomvalue=random.nextInt(size);
			roomID=rooms20.get(randomvalue).getRoomID();
			}else
			{
				int size=rooms45.size();
				int randomvalue=random.nextInt(size);
				roomID=rooms45.get(randomvalue).getRoomID();
			}
		}
		else if(Objects.equals(roomtype, "45"))
		{
			if(Math.random()<0.85)
			{
			int size=rooms45.size();
			int randomvalue=random.nextInt(size);
			roomID=rooms45.get(randomvalue).getRoomID();
			}
			else {
				int size=rooms80.size();
				int randomvalue=random.nextInt(size);
				roomID=rooms80.get(randomvalue).getRoomID();
			}
		}
		else if(Objects.equals(roomtype, "80"))
		{
			int size=rooms80.size();
			int randomvalue=random.nextInt(size);
			roomID=rooms80.get(randomvalue).getRoomID();
		}
		else
		{
			
			Exception e=new Exception("room type should be filled");
			throw e;
		}
		
		int day=random.nextInt(5)+1;
		int hour=random.nextInt(7)+1;
		/**System.out.println("Course ==========");
		System.out.println(courseID);
		System.out.println(capacity);
		System.out.println(groupOfCourse);
		System.out.println(departmentID);
		System.out.println(duration);
		System.out.println(lecturerID);
		System.out.println(roomID);
		System.out.println(day);
		System.out.println(hour);
		System.out.println(roomtype); **/
		Course newCourse= new Course(courseID, capacity, groupOfCourse, departmentID, duration, lecturerID, roomID, day, hour, roomtype, section,heavyballs);
		chromosome.add(newCourse); 							//Course object is created and it takes place on chromosome 
		while(rsCourses.next())										// Course object will be created at this while loop		
		{
			courseID=rsCourses.getInt("course_id");
			heavyballs=rsCourses.getBoolean("heavylec");
			//System.out.println("heavy:"+heavyballs);
			//System.out.println(courseID);
			duration=rsCourses.getInt("hour");
			lecturerID=rsCourses.getInt("course_lecturer");
			year=rsCourses.getInt("year");
			section=rsCourses.getInt("section");
			
			if(year==5)
			{
				groupOfCourse="Elective";
			}
			else
			{
				groupOfCourse=Integer.toString(year);
			}
			
			roomtype=rsCourses.getString("course_type");
			
			if(Objects.equals(roomtype, "lab"))
			{
				capacity=45;
			}else if(Objects.equals(roomtype, "80"))
			{
				capacity=80;
			}else if(Objects.equals(roomtype, "45"))
			{
				capacity=45;
			}else if(Objects.equals(roomtype, "20"))
			{
				capacity=20;
			}else
			{
				Exception e=new Exception("room type should be filled");
				throw e;
			}
			
			
			
			departmentID=rsCourses.getInt("department");
			
			
			roomID=null;										
			
																	// Until this line program assigns general information of course object. After this line, program will assign random suitable room , day and hour to course object
			if(Objects.equals(roomtype, "lab"))
			{
				int size=roomsLab.size();
				int randomvalue=random.nextInt(size);
				roomID=roomsLab.get(randomvalue).getRoomID();
			}
			else if(Objects.equals(roomtype, "20"))
			{
				if(Math.random()<0.7)
				{
				int size=rooms20.size();
				int randomvalue=random.nextInt(size);
				roomID=rooms20.get(randomvalue).getRoomID();
				}else
				{
					int size=rooms45.size();
					int randomvalue=random.nextInt(size);
					roomID=rooms45.get(randomvalue).getRoomID();
				}
			}
			else if(Objects.equals(roomtype, "45"))
			{
				if(Math.random()<0.85)
				{
				int size=rooms45.size();
				int randomvalue=random.nextInt(size);
				roomID=rooms45.get(randomvalue).getRoomID();
				}
				else {
					int size=rooms80.size();
					int randomvalue=random.nextInt(size);
					roomID=rooms80.get(randomvalue).getRoomID();
				}
			}
			else if(Objects.equals(roomtype, "80"))
			{
				int size=rooms80.size();
				int randomvalue=random.nextInt(size);
				roomID=rooms80.get(randomvalue).getRoomID();
			}
			else
			{
				
				Exception e=new Exception("room type should be filled");
				throw e;
			}
			
			day=random.nextInt(5)+1;
			hour=random.nextInt(7)+1;
			/**System.out.println("Course ==========");
			System.out.println(courseID);
			System.out.println(capacity);
			System.out.println(groupOfCourse);
			System.out.println(departmentID);
			System.out.println(duration);
			System.out.println(lecturerID);
			System.out.println(roomID);
			System.out.println(day);
			System.out.println(hour);
			System.out.println(roomtype); **/
			newCourse= new Course(courseID, capacity, groupOfCourse, departmentID, duration, lecturerID, roomID, day, hour, roomtype, section, heavyballs);
			chromosome.add(newCourse); 							//Course object is created and it takes place on chromosome 
		
		}
	}
	
	public void fillLecturerTree(ResultSet rslecturers) throws SQLException
	{
		lecturerTree=new AvlTree2();
		System.out.println("len"+rslecturers.getFetchSize());
		int lecturer_id;
		String lecturername;
		String lecturer_preferences;
		Lecturer lec;
		rslecturers.first();
		lecturer_id=rslecturers.getInt("lecturer_id");
		lecturername=rslecturers.getString("lecturername");
		lecturer_preferences=rslecturers.getString("lecturer_preferences");
		//System.out.println(lecturer_preferences);
		lec=new Lecturer(lecturer_id,lecturername,lecturer_preferences);
		lecturerTree.insert(lecturer_id,lec);
		//System.out.println("Lecturer id inserted :"+lecturer_id);
		
		while(rslecturers.next())
		{
			
			lecturer_id=rslecturers.getInt("lecturer_id");
			lecturername=rslecturers.getString("lecturername");
			lecturer_preferences=rslecturers.getString("lecturer_preferences");
			//System.out.println(lecturer_preferences);
			lec=new Lecturer(lecturer_id,lecturername,lecturer_preferences);
			lecturerTree.insert(lecturer_id,lec);
			//System.out.println("Lecturer id inserted :"+lecturer_id);
			
		}
		
	}
	public void fillRoom(ResultSet rsRoomLab, ResultSet rsRoom80, ResultSet rsRoom45, ResultSet rsRoom20)
	{
		try
		{
			roomsLab=new ArrayList<Room>();
			rooms80=new ArrayList<Room>();
			rooms45=new ArrayList<Room>();
			rooms20=new ArrayList<Room>();
		//This Loop creates room objects with type "LAB"
		rsRoomLab.first();
		String roomID=Integer.toString(rsRoomLab.getInt("room_id"));
		String roomType=rsRoomLab.getString("room_type");
		Room newRoom=new Room(roomID, roomType);
		roomsLab.add(newRoom);	
		while(rsRoomLab.next())
		{
			
			roomID=Integer.toString(rsRoomLab.getInt("room_id"));
			roomType=rsRoomLab.getString("room_type");
			newRoom=new Room(roomID, roomType);
			roomsLab.add(newRoom);									// Room object with type lab is created and added to the specific arraylist
		}
		
		
		rsRoom80.first();
		roomID=Integer.toString(rsRoom80.getInt("room_id"));
		roomType=rsRoom80.getString("room_type");
		newRoom=new Room(roomID, roomType);
		rooms80.add(newRoom);
		while(rsRoom80.next())
		{
			
			roomID=Integer.toString(rsRoom80.getInt("room_id"));
			roomType=rsRoom80.getString("room_type");
			newRoom=new Room(roomID, roomType);
			rooms80.add(newRoom);									// Room object with type normal-80 is created and added to the specific arraylist
		}
		
		rsRoom45.first();
		roomID=Integer.toString(rsRoom45.getInt("room_id"));
		roomType=rsRoom45.getString("room_type");
		newRoom=new Room(roomID, roomType);
		rooms45.add(newRoom);
		while(rsRoom45.next())
		{
			
			roomID=Integer.toString(rsRoom45.getInt("room_id"));
			roomType=rsRoom45.getString("room_type");
			newRoom=new Room(roomID, roomType);
			rooms45.add(newRoom);									// Room object with type normal-45 is created and added to the specific arraylist
		}
		
		rsRoom20.first();
		roomID=Integer.toString(rsRoom20.getInt("room_id"));
		roomType=rsRoom20.getString("room_type");
		newRoom=new Room(roomID, roomType);
		rooms20.add(newRoom);
		while(rsRoom20.next())
		{
			
			roomID=Integer.toString(rsRoom20.getInt("room_id"));
			roomType=rsRoom20.getString("room_type");
			newRoom=new Room(roomID, roomType);
			rooms20.add(newRoom);									// Room object with type normal-20 is created and added to the specific arraylist
		} 
		}
		catch(Exception e)
		{
			System.out.println("Error 1");
			e.printStackTrace();
		}
	}
	
	public void handleHashmaps()								// This function finds all double booking constraint violations of timetable and calculates a cost to indicate the violation level
	{	
		int HCcost=0;
		int SCcost=0;
		costs[0]=0; // hard constraints cost
		costs[1]=0; // soft constraints cost
		
		for(Course course:chromosome)
		{
			
			keyHolder=generateKeyForHashMaps(1, course);
			modifyHashMaps(keyHolder, 1);
			keyHolder=generateKeyForHashMaps(2, course);
			modifyHashMaps(keyHolder, 2);
			keyHolder=generateKeyForHashMaps(3, course);
			modifyHashMaps(keyHolder, 3);
			keyHolder=generateKeyForHashMaps(4, course);
			modifyHashMaps(keyHolder, 4);
			if(Objects.equals("Elective",course.getGroupOfCourse())||Objects.equals("3",course.getGroupOfCourse()))
			{
			keyHolder=generateKeyForHashMaps(5, course);
			modifyHashMaps(keyHolder, 4);
			}
			if(Objects.equals("Elective",course.getGroupOfCourse())||Objects.equals("4",course.getGroupOfCourse()))
			{
			keyHolder=generateKeyForHashMaps(6, course);
			modifyHashMaps(keyHolder, 4);
			}
			if(Objects.equals(true, course.getHeavyballs()))
			{
				
				
				keyHolder=generateKeyForHashMaps(7, course);
				modifyHashMaps(keyHolder,5);
			}
			
			
			checkLecturerPreference(course);
			
			
			
			
			
			
			
		}
		
		
			try {
				HCcost=HCcost+costs[0];
				SCcost=SCcost+costs[1];
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

		HC_cost=HCcost;
		SC_cost=SCcost;
		
		Total_cost=HC_cost+(SC_cost/2);
		
		
		
	}
	

	public void checkLecturerPreference(Course course)
	{
			Lecturer preferenceLecturer = lecturerTree.find(course.getLecturerID());
			
			
			int courseday=course.getDay();
			int coursehour=course.getHour();
			int courseduration=course.getDuration();
			
			//System.out.println("Courseday : "+courseday);
			//System.out.println("Course hour: "+coursehour);
			//System.out.println("Course duration :"+courseduration);
			//System.out.println("Course lecturer : "+course.getLecturerID());
			String preference = preferenceLecturer.getLecturer_preferences();
			
			//System.out.println("Pref : "+preference);
			
			if(!Objects.equals("null", preference))
			{
				
				String[] preferencelist=preference.split("/");
				String[] daysHours;
				String[] hourlimits;
				for(String pref:preferencelist)
				{
					
					daysHours=pref.split("-");
					if(Integer.parseInt(daysHours[0])==courseday)
					{
						
						
						hourlimits=daysHours[1].split("&");
						
						for(int i=1;i<=courseduration;i++)
						{
							
							if(Integer.parseInt(hourlimits[0])<coursehour && Integer.parseInt(hourlimits[1])>coursehour)
							{
								//System.out.println("DETECTED HOUR VIOLATION");
								costs[1]=costs[1]+6;	
							}
							coursehour++;
						}
					}
						
				}
			}else
			{
				//System.out.println("No violation");
			}
			
		
	}
	
	
	
	
	public ArrayList<String> generateKeyForHashMaps(int value,Course course)
	{	
		ArrayList<String> listOfKeys = new ArrayList<String>();
		String GeneratedKey;
		if(value==1)
		{
			for(int i=0;i<course.getDuration();i++)
			{
				int hour = course.getHour()+i;
				GeneratedKey = course.getLecturerID()+""+course.getDay()+""+hour;
				listOfKeys.add(GeneratedKey);
	
			}
			return listOfKeys;
		}
		else if(value==2)
		{
			for(int i=0;i<course.getDuration();i++)
			{
				int hour=course.getHour()+i;
				GeneratedKey = course.getRoomID()+""+course.getDay()+""+hour;
				listOfKeys.add(GeneratedKey);
			}
		}
		else if(value==3)
		{	
			for(int i=0;i<course.getDuration();i++)
			{
				int hour=course.getHour()+i;
				GeneratedKey =""+course.getDay()+"/"+hour+"/"+course.getGroupOfCourse()+"/"+course.getDepartmentID()+"/"+course.getSection();
				//System.out.println("Key => "+GeneratedKey);
				listOfKeys.add(GeneratedKey);
			}
		}else if(value==4)
		{	
			for(int i=0;i<course.getDuration();i++)
			{
				int hour=course.getHour()+i;
				GeneratedKey =""+course.getDay()+"/"+hour+"/"+course.getGroupOfCourse()+"/"+course.getDepartmentID()+"/Soft";
				listOfKeys.add(GeneratedKey);

			}
		}else if(value==5)
		{	
			for(int i=0;i<course.getDuration();i++)
			{
				int hour=course.getHour()+i;
				GeneratedKey =""+course.getDay()+"/"+hour+"/"+course.getSection()+"/"+course.getDepartmentID()+"/3=E";
				listOfKeys.add(GeneratedKey);

			}
		}
		else if(value==6)
		{	
			for(int i=0;i<course.getDuration();i++)
			{
				int hour=course.getHour()+i;
				GeneratedKey =""+course.getDay()+"/"+hour+"/"+course.getSection()+"/"+course.getDepartmentID()+"/4=E";
				listOfKeys.add(GeneratedKey);

			}
		}else if(value==7)
		{	
			for(int i=-2;i<course.getDuration()+2;i++)
			{
				int hour=course.getHour()+i;
				if(hour>0 && hour<10)
				{
				GeneratedKey =""+course.getDay()+"/"+hour+"/"+course.getGroupOfCourse()+"/"+course.getDepartmentID()+"/Heavy";
				listOfKeys.add(GeneratedKey);
				}
			}
		}
		
		
		else{
			System.out.println("\n\n NOT_FOUND/UNEXPECTED VALUE EXCEPTION, HASHMAP GENERATOR IS CORRUPT");
		}
		return listOfKeys;
	}
	
	public void modifyHashMaps(ArrayList<String> keyholder,int value)
	{
		
		if(value==1)
		{
			for(String key:keyholder)
			{
				if(Objects.equals(null, lecturersHashMap.get(key)))
				{	
					
					lecturersHashMap.put(key, 1);
					
				}
				
				else
				{	
					
					int valueAtHashMap = lecturersHashMap.get(key);
					costs[0]=costs[0]+(valueAtHashMap*11);
					valueAtHashMap++;
					lecturersHashMap.put(key, valueAtHashMap);
					
				}
			}
		}
		else if(value == 2)
		{

			for(String key:keyholder)
			{
				if(Objects.equals(null, roomsHashMap.get(key)))
				{	
					
					roomsHashMap.put(key, 1);
					
				}
				else
				{	
					
					int valueAtHashMap = roomsHashMap.get(key);
					costs[0]=costs[0]+(valueAtHashMap*11);
					valueAtHashMap++;
					roomsHashMap.put(key, valueAtHashMap);
					
				}
			}
		}
		else if(value == 3)
		{
			for(String key:keyholder)
			{
				if(Objects.equals(null, departmentHashMap.get(key)))
				{
					departmentHashMap.put(key, 1);
					
					
				}
				else
				{	
					
					int valueAtHashMap = departmentHashMap.get(key);
					costs[0]=costs[0]+(valueAtHashMap*4);
					valueAtHashMap++;
					departmentHashMap.put(key, valueAtHashMap);
					
				}
			}
		}else if(value == 4)
		{
			for(String key:keyholder)
			{
				if(Objects.equals(null, departmentHashMap.get(key)))
				{
					departmentHashMap.put(key, 1);
					
					
				}
				else
				{	
					
					int valueAtHashMap = departmentHashMap.get(key);
					costs[1]=costs[1]+(valueAtHashMap*3);
					valueAtHashMap++;
					departmentHashMap.put(key, valueAtHashMap);
					
				}
			}
		}else if(value == 5)
		{
			for(String key:keyholder)
			{
				if(Objects.equals(null, departmentHashMap.get(key)))
				{
					departmentHashMap.put(key, 1);
					
					
				}
				else
				{	
					
					int valueAtHashMap = departmentHashMap.get(key);
					costs[1]=costs[1]+(valueAtHashMap*6);
					valueAtHashMap++;
					departmentHashMap.put(key, valueAtHashMap);
					
				}
			}
		}
		
		
		
	}
	
	public ArrayList<Course> getChromosome() {
		return chromosome;
	}
	public void setChromosome(ArrayList<Course> chromosome) {
		this.chromosome = chromosome;
	}
	public int getHC_cost() {
		return HC_cost;
	}
	public void setHC_cost(int hC_cost) {
		HC_cost = hC_cost;
	}
	public int getSC_cost() {
		return SC_cost;
	}
	public void setSC_cost(int sC_cost) {
		SC_cost = sC_cost;
	}
	public int getTotal_cost() {
		return Total_cost;
	}
	public void setTotal_cost(int total_cost) {
		Total_cost = total_cost;
	}
	public HashMap<String, Integer> getLecturersHashMap() {
		return lecturersHashMap;
	}
	public void setLecturersHashMap(HashMap<String, Integer> lecturersHashMap) {
		this.lecturersHashMap = lecturersHashMap;
	}
	public HashMap<String, Integer> getRoomsHashMap() {
		return roomsHashMap;
	}
	public void setRoomsHashMap(HashMap<String, Integer> roomsHashMap) {
		this.roomsHashMap = roomsHashMap;
	}
	public HashMap<String, Integer> getDepartmentHashMap() {
		return departmentHashMap;
	}
	public void setDepartmentHashMap(HashMap<String, Integer> departmentHashMap) {
		this.departmentHashMap = departmentHashMap;
	}
	public ArrayList<Room> getRoomsLab() {
		return roomsLab;
	}
	public void setRoomsLab(ArrayList<Room> roomsLab) {
		this.roomsLab = roomsLab;
	}
	public ArrayList<Room> getRooms80() {
		return rooms80;
	}
	public void setRooms80(ArrayList<Room> rooms80) {
		this.rooms80 = rooms80;
	}
	public ArrayList<Room> getRooms45() {
		return rooms45;
	}
	public void setRooms45(ArrayList<Room> rooms45) {
		this.rooms45 = rooms45;
	}
	public ArrayList<Room> getRooms20() {
		return rooms20;
	}
	public void setRooms20(ArrayList<Room> rooms20) {
		this.rooms20 = rooms20;
	}
	public AvlTree2 getLecturerTree() {
		return lecturerTree;
	}
	public void setLecturerTree(AvlTree2 lecturerTree) {
		this.lecturerTree = lecturerTree;
	}
	
	
	
	
}
