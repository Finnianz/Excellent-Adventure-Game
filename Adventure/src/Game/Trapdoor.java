package Game;

import render.Drawable;

public class Trapdoor extends Location {

	private final Room currentRoom;
	private final Room exitRoom;
	Boolean unlocked = false;

	public Trapdoor(Drawable wallNE, Drawable wallNW, Drawable wallSE,
			Drawable wallSW, Drawable floor, Drawable occupier, int x, int y, Room currentRoom, Room exitRoom){
		super(wallNE, wallNW, wallSE, wallSW, floor, occupier, x,y);
		this.currentRoom = currentRoom;
		this.exitRoom = exitRoom;
	}

	public Room getExit(Room entry){
		return exitRoom;
	}


	public void setLockRoom(Boolean locked){
		unlocked = locked;
	}

	public Boolean isLocked(){
		return unlocked;
	}

}

