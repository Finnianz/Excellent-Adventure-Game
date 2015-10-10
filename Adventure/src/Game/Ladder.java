package Game;

import render.Drawable;

public class Ladder extends Location {

	private final Room currentRoom;
	private final Room exitRoom;
	Boolean unlocked = false;

	public Ladder(Drawable wallNE, Drawable wallNW, Drawable wallSE,
			Drawable wallSW, Drawable floor, Drawable occupier, int x, int y, Room currentRoom, Room exitRoom){
		super(wallNE, wallNW, wallSE, wallSW, floor, occupier, x,y);
		this.currentRoom = currentRoom;
		this.exitRoom = exitRoom;
	}

	public Room getExit(){
		return exitRoom;
	}


	public void setLockRoom(Boolean locked){
		unlocked = locked;
	}

	public Boolean isLocked(){
		return unlocked;
	}

}

