package seniorP;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation.ANONYMOUS;

public class Room 
{
	private String roomID;							//It defines ID of room
	private String roomType;						//It defines type of room(Lab,normal 20, normal 80 etc also it indicates the capacity 
	
	//Default Constructor 
	public Room()
	{
		roomID="anonymous";
		roomType="anonymous";
	}
	
	//Parameterised Constructor
	public Room(String roomID, String roomType)
	{
		this.roomID=roomID;
		this.roomType=roomType;
		
	}
	
	//Getters and Setters
	public String getRoomID() {
		return roomID;
	}
	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	
}
