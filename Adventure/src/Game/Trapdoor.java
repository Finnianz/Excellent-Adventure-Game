package Game;

import render.Drawable;

public class Trapdoor extends Location {

	private final Room currentRoom;
	private final Room exitRoom;
	Boolean unlocked = true;
	private int keyID;
	
	public Trapdoor(Drawable wallNE, Drawable wallNW, Drawable wallSE,
			Drawable wallSW, Drawable floor, Drawable occupier, int x, int y, Room currentRoom, Room exitRoom, int keyID){
		super(wallNE, wallNW, wallSE, wallSW, floor, occupier, x,y);
		this.currentRoom = currentRoom;
		this.exitRoom = exitRoom;
		this.keyID = keyID;
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

	/**
	 * @return the keyID
	 */
	public int getKeyID() {
		return keyID;
	}

}

