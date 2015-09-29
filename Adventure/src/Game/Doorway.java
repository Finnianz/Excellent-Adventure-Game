package Game;

public class Doorway {
	
	private final Room room1;
	private final Room room2;
	
	public Doorway(Room room1, Room room2){
		this.room1 = room1;
		this.room2 = room2;
	}
	
	public Room getExit(Room entry){
		if(entry.getRoomID() == room1.getRoomID()){
			return room2;
		}
		else {
			return room1;
		}
		
	}

}
