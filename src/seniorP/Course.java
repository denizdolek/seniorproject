package seniorP;

/**
 * @author baris
 *
 */
public class Course {
	private int courseID;					//The ID of Course
	private int capacity;  					//The max student can this course take
	private int section;					//
	private String groupOfCourse; 			//It identifies the group of course which may be like "1-2-3-4 or Elective"
	private int departmentID;			//It identifies which department the course belongs
	private int duration;					//It defines how long the course will take
	private int lecturerID;				//It defines the lecturer of this course		
	private String roomID;					//It defines where this course will be presented
	private String roomType;				//It defines room type of this course, "Lab or normal-45 etc";
	private int day;						//It defines the day of this course
	private int hour;						//It defines the starting hour of this course
	private boolean heavyballs;
	
	public Course()
	{
		
	}
	public Course(Course copy)
	{
		this.courseID=copy.getCourseID();
		this.capacity=copy.getCapacity();
		this.groupOfCourse=copy.getGroupOfCourse();
		this.departmentID=copy.getDepartmentID();
		this.duration=copy.getDuration();
		this.lecturerID=copy.getLecturerID();
		this.roomID=copy.getRoomID();
		this.roomType=copy.getRoomType();
		this.day=copy.getDay();
		this.hour=copy.getHour();
		this.section=copy.getSection();
		this.heavyballs=copy.getHeavyballs();
	}
	public Course(int courseID, int capacity, String groupOfCourse, int departmentID, int duration, int lecturerID, String roomID, int day, int hour, String roomType, int section, boolean heavyballs)
	{
		this.courseID=courseID;
		this.capacity=capacity;
		this.groupOfCourse=groupOfCourse;
		this.departmentID=departmentID;
		this.duration=duration;
		this.lecturerID=lecturerID;
		this.roomID=roomID;
		this.day=day;
		this.hour=hour;
		this.roomType=roomType;
		this.section=section;
		this.heavyballs=heavyballs;
	}
	
	public int getSection() {
		return section;
	}
	public void setSection(int section) {
		this.section = section;
	}
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getGroupOfCourse() {
		return groupOfCourse;
	}
	public void setGroupOfCourse(String groupOfCourse) {
		this.groupOfCourse = groupOfCourse;
	}
	
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getLecturerID() {
		return lecturerID;
	}
	public void setLecturerID(int lecturerID) {
		this.lecturerID = lecturerID;
	}
	public String getRoomID() {
		return roomID;
	}
	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}
	public boolean isHeavyballs() {
		return heavyballs;
	}
	public void setHeavyballs(boolean heavyballs) {
		this.heavyballs = heavyballs;
	}
	public boolean getHeavyballs() {
		return heavyballs;
	}
	
	
}
