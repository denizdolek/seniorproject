package seniorP;

public class Lecturer {

	int lecturer_id;
	String lecturername;
	String lecturer_preferences;
	
	
	public Lecturer()
	{
		
	}
	
	public Lecturer(int lecturer_id, String lecturername, String lecturer_preferences)
	{
		
		this.lecturer_id=lecturer_id;
		this.lecturername=lecturername;
		this.lecturer_preferences=lecturer_preferences;
		
		
		
	}

	public int getLecturer_id() {
		return lecturer_id;
	}

	public void setLecturer_id(int lecturer_id) {
		this.lecturer_id = lecturer_id;
	}

	public String getLecturername() {
		return lecturername;
	}

	public void setLecturername(String lecturername) {
		this.lecturername = lecturername;
	}

	public String getLecturer_preferences() {
		return lecturer_preferences;
	}

	public void setLecturer_preferences(String lecturer_preferences) {
		this.lecturer_preferences = lecturer_preferences;
	}
	
	
	
	
}
