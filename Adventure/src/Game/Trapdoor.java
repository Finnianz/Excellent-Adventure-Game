package Game;

import render.Drawable;

public class Trapdoor extends Location {
	
	private final Room room1;
	private final Room room2;
	Boolean unlocked = false;
	
//	public Trapdoor(Room room1, Room room2){
//		this.room1 = room1;
//		this.room2 = room2;
//	}
	
	public Trapdoor(Drawable wallNE, Drawable wallNW, Drawable wallSE,
			Drawable wallSW, Drawable floor, Drawable occupier, int x, int y, Room room1, Room room2){
		super(wallNE, wallNW, wallSE, wallSW, floor, occupier, x,y);
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
	
	public void setLockRoom(Boolean locked){
		unlocked = locked;
	}
	
	public Boolean isLocked(){
		return unlocked;
	}

}
