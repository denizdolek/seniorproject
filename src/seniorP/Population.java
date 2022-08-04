package seniorP;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;





public class Population 
{
	private ArrayList<Timetable> timetables; 
	private AvlTree timetableTree;
	
	private int populationsize;
    private Timetable bestTimetable;
    private ResultSet rsLab;
    private ResultSet rs80;
    private ResultSet rs45;
    private ResultSet rs20;
    private ResultSet rsCourse;
    private ResultSet rslecturers;
    private Connection conn;

    
    public Population(int populationsize,Boolean value,Connection conn){
    	
    	if(Objects.equals(true, value))
    	{	
    		
    	
    		timetables = new ArrayList<Timetable>(); 
        	timetableTree = new AvlTree();
    		this.populationsize=populationsize;
    		this.conn=conn;
    		try {
    			
    		    PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM roomtable WHERE room_type=? ");
    		    pstmt.setString(1, "lab");
    		    rsLab=pstmt.executeQuery();
    		    pstmt = conn.prepareStatement("SELECT * FROM roomtable WHERE room_type=? ");
    		    pstmt.setString(1, "80");
    		    rs80=pstmt.executeQuery();
    		    pstmt = conn.prepareStatement("SELECT * FROM roomtable WHERE room_type=? ");
    		    pstmt.setString(1, "45");
    		    rs45=pstmt.executeQuery();
    		    pstmt = conn.prepareStatement("SELECT * FROM roomtable WHERE room_type=? ");
    		    pstmt.setString(1, "20");
    		    rs20=pstmt.executeQuery();
    		       
    		    pstmt = conn.prepareStatement("SELECT * FROM coursetable");
    		    
    		    rsCourse=pstmt.executeQuery();
    		    
    		    pstmt = conn.prepareStatement("SELECT * FROM lecturertable");
    		    rslecturers=pstmt.executeQuery();
    		    for(int i=0;i<populationsize;i++)
    	        	{
    	        		Timetable timetable =new Timetable(rsLab, rs80, rs45, rs20, rsCourse, rslecturers);
    	        		timetable.handleHashmaps();
    	        		timetables.add(timetable);	
    	        		System.out.println("Timetable : "+i+" cost : "+timetable.getTotal_cost());
    	        		timetableTree.insert(timetable.getTotal_cost(), timetable);
    	        		System.out.println("------------------------------------------------------------------------------------------------------");
    					
    	        	}
    		   
    	        
    			
    	        FindFittest();
    	        System.out.println("Size ==================>"+bestTimetable.getChromosome().size());
    		} catch ( Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
           
            
            /** Resultsetlere gelen veriler test edildi veriler database den çekiliyor **/
         	
    	
    	}
    	else
    		{
    		//System.out.println("false section");
    		timetables = new ArrayList<Timetable>(); 
        	timetableTree = new AvlTree();
    		this.populationsize=populationsize;
    		}
    	}
    		

    	public void closeConnection() throws SQLException
    	{
    		conn.close();
    	}
    	
    	public void FindFittest()
    	{	
    			bestTimetable=timetableTree.findBestTimetable();
    			//System.out.println("Best Timetable : "+bestTimetable.getTotal_cost());
    	}

    	public ArrayList<Timetable> getTimetables() {
    		return timetables;
    	}

    	public void setTimetables(ArrayList<Timetable> timetables) {
    		this.timetables = timetables;
    	}

    	public AvlTree getTimetableTree() {
    		return timetableTree;
    	}

    	public void setTimetableTree(AvlTree timetableTree) {
    		this.timetableTree = timetableTree;
    	}

    	public int getPopulationsize() {
    		return populationsize;
    	}

    	public void setPopulationsize(int populationsize) {
    		this.populationsize = populationsize;
    	}

    	public Timetable getBestTimetable() {
    		return bestTimetable;
    	}

    	public void setBestTimetable(Timetable bestTimetable) {
    		this.bestTimetable = bestTimetable;
    	}
		public ResultSet getRsLab() {
			return rsLab;
		}
		public void setRsLab(ResultSet rsLab) {
			this.rsLab = rsLab;
		}
		public ResultSet getRs80() {
			return rs80;
		}
		public void setRs80(ResultSet rs80) {
			this.rs80 = rs80;
		}
		public ResultSet getRs45() {
			return rs45;
		}
		public void setRs45(ResultSet rs45) {
			this.rs45 = rs45;
		}
		public ResultSet getRs20() {
			return rs20;
		}
		public void setRs20(ResultSet rs20) {
			this.rs20 = rs20;
		}
		public ResultSet getRsCourse() {
			return rsCourse;
		}
		public void setRsCourse(ResultSet rsCourse) {
			this.rsCourse = rsCourse;
		}
    
}
