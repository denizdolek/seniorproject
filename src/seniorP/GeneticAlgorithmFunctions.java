package seniorP;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class GeneticAlgorithmFunctions 
{
	public static Timetable bestofAll = new Timetable();
	private Timetable elite_Individual;
	private Population evolvedPop;
    private static final double uniformRate = 0.5;
    private static final int tournamentSize = 5;
    private double mutationRate;
    private Random random = new Random();
    private Timetable child,parent1,parent2;
 public Population evolution(Population population, ResultSet rsLab, ResultSet rs80, ResultSet rs45, ResultSet rs20,Connection conn, double mutationRate) throws Exception{
    	
    	this.mutationRate=mutationRate;
    	elite_Individual = population.getBestTimetable();
    	evolvedPop=new Population(population.getPopulationsize(),false,conn);
    	
    	evolvedPop.getTimetables().add(elite_Individual);
		evolvedPop.getTimetableTree().insert(elite_Individual.getTotal_cost(), elite_Individual); 
		
		for(int i=1;i<population.getPopulationsize();i++)
		{	
			child=new Timetable();
			parent1 = tournamentSelection(population.getTimetables());								// Choosing an individual foor production
			parent2 = tournamentSelection(population.getTimetables());
		
			child = Crossover(parent1, parent2,child);														// Generation process of new individual from winner parent1 and parent2
			child.fillRoom(rsLab, rs80, rs45, rs20); 															// This function creates room objects for new child object
			child = Mutation(child);
			child.setLecturerTree(parent1.getLecturerTree());
		
			child.handleHashmaps();																				// Detects constraint violation 

			evolvedPop.getTimetables().add(child);																// Adds new individual to the new Population
			evolvedPop.getTimetableTree().insert(child.getTotal_cost(), child);
			
		}
		evolvedPop.FindFittest();																				// Finds the best timetable
    	
    	
		return evolvedPop;
    	
    }
    
    
    
    
    
    public Timetable tournamentSelection(ArrayList<Timetable> timetables)
	{
		Timetable winner=null;
		int size=timetables.size();
		Timetable[] competitors=new Timetable[tournamentSize];
		
		for(int i=0;i<3;i++)
		{
			competitors[i]=timetables.get(ThreadLocalRandom.current().nextInt(0,size));
			if(Objects.equals(winner, null))
			{
				winner=competitors[i];
			}
			else
			{
				if(winner.getTotal_cost()>competitors[i].getTotal_cost())
				{
					winner=competitors[i];
				}
			}
		}		
		return winner;	
	}
	
	public Timetable Crossover(Timetable parent1 , Timetable parent2, Timetable child)
	{	
		int size=parent1.getChromosome().size();
		for(int i=0;i<size;i++)
		{
			Course newGene = null;
			//String roomcode = null;
			//int daycode;
			
			try{
				
				//System.out.println(i);
				// RANDOM # 1 resets everything
				if(Math.random()<=uniformRate)
				{
					//System.out.println("inside if");
					newGene = new Course(parent1.getChromosome().get(i)) ;
					
					//System.out.println("here : "+newGene.getCourseCode());
				}
				else
				{	
					//System.out.println("inside else");
					newGene = new Course(parent2.getChromosome().get(i));
				}
				
				// RANDOM # 2 resets the room
				/**
				if(ThreadLocalRandom.current().nextFloat()<=uniformRate)
				{
					roomcode = parent1.getChromosome().get(i).getRoomCodeOfCourse();
				}
				else
				{
					roomcode = parent2.getChromosome().get(i).getRoomCodeOfCourse();
				}
				**/
				/**
				//RANDOM # 3 resets the day time
				if(ThreadLocalRandom.current().nextFloat()<=uniformRate)
				{
					daycode = parent1.getChromosome().get(i).getDayOfCourse();
				}
				else
				{
					daycode = parent2.getChromosome().get(i).getDayOfCourse();
				}
				newGene.setDayOfCourse(daycode); **/
				//newGene.setRoomCodeOfCourse(roomcode); 
				
				child.getChromosome().add(newGene);
				
			}catch(NullPointerException e){
				e.printStackTrace();
			}

		}
		
		return child;
	}

    public Timetable Mutation(Timetable child) throws Exception
    {
    	int size = child.getChromosome().size();
    	for(int i=0;i<size;i++){

    		if(Math.random()<=mutationRate)
    		{
    			//System.out.println("Mut");
    			Course mutation = child.getChromosome().get(i);
    			mutation.setDay(random.nextInt(5)+1);
    			mutation.setHour(random.nextInt(7)+1);
    			String roomtype=mutation.getRoomType();
    			int roomSize;
    			
    			switch (roomtype) {
				case "lab":
					roomSize=child.getRoomsLab().size();
					mutation.setRoomID(child.getRoomsLab().get(random.nextInt(roomSize)).getRoomID());
					break;
				case "80":
					roomSize=child.getRooms80().size();
					mutation.setRoomID(child.getRooms80().get(random.nextInt(roomSize)).getRoomID());
					break;
				case "45":
					if(Math.random()<0.85)
					{
					roomSize=child.getRooms45().size();
					mutation.setRoomID(child.getRooms45().get(random.nextInt(roomSize)).getRoomID());
					}
					else
					{
					roomSize=child.getRooms80().size();
					mutation.setRoomID(child.getRooms80().get(random.nextInt(roomSize)).getRoomID());	
					}
					break;
				case "20":
					if(Math.random()<0.7)
					{
					roomSize=child.getRooms20().size();
					mutation.setRoomID(child.getRooms20().get(random.nextInt(roomSize)).getRoomID());
					}
					else
					{
					roomSize=child.getRooms45().size();
					mutation.setRoomID(child.getRooms45().get(random.nextInt(roomSize)).getRoomID());
					}
					break;
				default:
					System.out.println("DEF!!");
				}
    			
   			}
    	}	
    	return child;
    }
}
