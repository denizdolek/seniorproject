package seniorP;

public class CourseDC {
	
	private int course_id;
	private boolean mandatory;
	private boolean elective;
	private int belongs_Department_I;
	private int year;
	private int hour;
	private int section;
	private String course_type;
	
	public CourseDC(int id,boolean mandatory_,int department,int year_,int section_,int hour_,String type_){
		course_id=id; 
		mandatory=false;
		elective=false;
		if (mandatory_ == true){
			mandatory = mandatory_;}
		else{
			this.elective = true;
		}
		
		belongs_Department_I=department;
		year=year_;
		hour=hour_;
		section=section_;		
		course_type=type_;			
		
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	public boolean isElective() {
		return elective;
	}

	public void setElective(boolean elective) {
		this.elective = elective;
	}

	public int getBelongs_Department_I() {
		return belongs_Department_I;
	}

	public void setBelongs_Department_I(int belongs_Department_I) {
		this.belongs_Department_I = belongs_Department_I;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getSection() {
		return section;
	}

	public void setSection(int section) {
		this.section = section;
	}

	public String getCourse_type() {
		return course_type;
	}

	public void setCourse_type(String course_type) {
		this.course_type = course_type;
	}
	
	
}
