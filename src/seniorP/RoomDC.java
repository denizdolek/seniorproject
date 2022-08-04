package seniorP;

public class RoomDC {
	
	private static int room_size;
	private static int room_id;
	private static String room_type;
	
	public RoomDC(int id,int size,String type){
		room_size=size;
		room_id=id;
		room_type = type;		
	}

	public static int getRoom_size() {
		return room_size;
	}

	public static void setRoom_size(int room_size) {
		RoomDC.room_size = room_size;
	}

	public static int getRoom_id() {
		return room_id;
	}

	public static void setRoom_id(int room_id) {
		RoomDC.room_id = room_id;
	}

	public static String getRoom_type() {
		return room_type;
	}

	public static void setRoom_type(String room_type) {
		RoomDC.room_type = room_type;
	}
	
}
