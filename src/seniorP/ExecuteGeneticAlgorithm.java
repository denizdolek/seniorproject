package seniorP;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;



public class ExecuteGeneticAlgorithm implements Runnable{

	
	private final static int populationSize=100;
	@Override
	// Run method initiates the Genetic Algorithm and tries to find the best possible result
	public void run() {
		String url = "jdbc:mysql://localhost:3306/seniorproject";
	    String driver = "com.mysql.jdbc.Driver";
	    String userName = "denz";
	    String password = "denz";
	    int rg=0; // number of repeated generations
	    int bestPastCost=9999; // cost of last elite individual
	    int bestcost=9998;
	    double mutationRate=0.001;
	    try
	    {
	    	Class.forName(driver).newInstance();
		    Connection conn = DriverManager.getConnection(url,userName,password);
		    Population population = new Population(populationSize,true,conn);
			Timetable bestResult=null;
			ResultSet rsLab=population.getRsLab();											// Program retrieves resultset of classrooms in order to use them in child generation 
			ResultSet rs80=population.getRs80();
			ResultSet rs45=population.getRs45();
			ResultSet rs20=population.getRs20();
			String a;
			File file;
			FileWriter fileWriter;
			BufferedWriter bWriter;
			PreparedStatement pstmt = null;
			System.out.println(population.getBestTimetable().getTotal_cost());
			GeneticAlgorithmFunctions geneticfunctions=new GeneticAlgorithmFunctions();
			int generation =1;
			while(population.getBestTimetable().getTotal_cost()!=0)
			{
				try {
					population=geneticfunctions.evolution(population, rsLab, rs80, rs45,rs20,conn,mutationRate);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				bestResult=population.getBestTimetable();
				//System.out.println("Generation : "+generation+" best cost : "+bestResult.getTotal_cost()+" Hard Constraint Cost : "+bestResult.getHC_cost()+" Soft Constraint Cost : "+bestResult.getSC_cost() );
				if(bestResult.getTotal_cost()==0)
				{
					a="Solution found!!";
					System.out.println("Solution found!!!");
					file = new File("result.txt");
					if (!file.exists()) {
			            file.createNewFile();
			        }
					fileWriter = new FileWriter(file, false);
			        bWriter = new BufferedWriter(fileWriter);
			        bWriter.write(a);
			        bWriter.close();
					
				}
				if((generation%10)==0)
				{
					
					file = new File("result.txt");
					System.out.println("Generation : "+generation+" best cost : "+bestResult.getTotal_cost()+" Hard Constraint Cost : "+bestResult.getHC_cost()+" Soft Constraint Cost : "+bestResult.getSC_cost() );
					
					a="Generation : "+generation+" best cost : "+bestResult.getTotal_cost()+" Hard Constraint Cost : "+bestResult.getHC_cost()+" Soft Constraint Cost : "+bestResult.getSC_cost();
					if (!file.exists()) {
			            file.createNewFile();
			        }
					fileWriter = new FileWriter(file, false);
					bWriter = new BufferedWriter(fileWriter);
			        bWriter.write(a);
			        bWriter.close();
					
					
				}
				
				
				
				if(generation>1500)
				{
					System.out.println("Generation limit reached...");
					System.out.println("Generation : "+generation+" best cost : "+bestResult.getTotal_cost()+" Hard Constraint Cost : "+bestResult.getHC_cost()+" Soft Constraint Cost : "+bestResult.getSC_cost() );
					
					file = new File("result.txt");
					a="Generation : "+generation+" best cost : "+bestResult.getTotal_cost()+" Hard Constraint Cost : "+bestResult.getHC_cost()+" Soft Constraint Cost : "+bestResult.getSC_cost();
					if (!file.exists()) {
			            file.createNewFile();
			        }
					fileWriter = new FileWriter(file, false);
			        bWriter = new BufferedWriter(fileWriter);
			        bWriter.write(a);
			        bWriter.close();
			        break;
					
				}
				bestcost=population.getBestTimetable().getTotal_cost();
			    // adjust mutation rate automatically according to changerate of bestcost
			    if(bestcost == bestPastCost){ 
			     rg++; 
			     
				if(rg==750)
			      mutationRate=0.005;
			     else if(rg==1250)
			      mutationRate=0.01;
			     else if(rg==0)
			      mutationRate=0.001;
			    }else{rg=0;}
			    bestPastCost=bestcost;
				
				generation++;
				
			}
			pstmt=conn.prepareStatement("TRUNCATE garesult");
			pstmt.executeUpdate();
			
			for(Course coursex:bestResult.getChromosome())
			{	
				pstmt=conn.prepareStatement("INSERT garesult SET courseID=? ,capacity=? ,section=? ,groupOfCourse=? ,departmentID=? ,duration=? ,lecturerID=?, roomID=?, roomType=?, day=?, hour=?");
				pstmt.setInt(1, coursex.getCourseID());
				pstmt.setInt(2, coursex.getCapacity());
				pstmt.setInt(3, coursex.getSection());
				pstmt.setString(4, coursex.getGroupOfCourse());
				pstmt.setInt(5, coursex.getDepartmentID());
				pstmt.setInt(6, coursex.getDuration());
				pstmt.setInt(7, coursex.getLecturerID());
				pstmt.setString(8, coursex.getRoomID());
				pstmt.setString(9, coursex.getRoomType());
				pstmt.setInt(10, coursex.getDay());
				pstmt.setInt(11, coursex.getHour());
				pstmt.executeUpdate();
				
				
				/**
				System.out.println("<===========================================================================================>");
				System.out.println(" ");
				System.out.println("Course ID => "+coursex.getCourseID());
				System.out.println("Course Group => "+coursex.getGroupOfCourse());
				System.out.println("Course Day => "+coursex.getDay());
				System.out.println("Course Hour => "+coursex.getHour());
				System.out.println("Course Duration => "+coursex.getDuration());
				System.out.println("Course Section => "+coursex.getSection());
				System.out.println("Course Lecturer => "+coursex.getLecturerID());
				System.out.println("Course Room => "+coursex.getRoomID());
				System.out.println("Course Room Type=> "+coursex.getRoomType());
				System.out.println(" ");
				System.out.println("<===========================================================================================>");
				**/
			}
			pstmt.close();
			conn.close();
	    }catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    
		
			
		
		
	}

}
