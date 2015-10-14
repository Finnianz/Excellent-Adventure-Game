package Game;

import render.Drawable;

/**
 * 
 * @author Michelle O'Neill ID:301025406
 *
 */

public class Ladder extends Location {

	private final Room currentRoom;
	private final Room exitRoom;
	Boolean unlocked = false;
	/**
	 * sets up ladder 
	 * @param wallNE
	 * @param wallNW
	 * @param wallSE
	 * @param wallSW
	 * @param floor
	 * @param occupier
	 * @param x
	 * @param y
	 * @param currentRoom
	 * @param exitRoom
	 */
	public Ladder(Drawable wallNE, Drawable wallNW, Drawable wallSE,
			Drawable wallSW, Drawable floor, Drawable occupier, int x, int y, Room currentRoom, Room exitRoom){
		super(wallNE, wallNW, wallSE, wallSW, floor, occupier, x,y);
		this.currentRoom = currentRoom;
		this.exitRoom = exitRoom;
	}
	/**
	 * returns the room that is the exit
	 * @return
	 */
	public Room getExit(){
		return exitRoom;
	}

	/**
	 * sets the room to be locked
	 * @param locked
	 */
	public void setLockRoom(Boolean locked){
		unlocked = locked;
	}
	/**
	 * returns true if the room is unlocked
	 * @return
	 */
	public Boolean isLocked(){
		return unlocked;
	}

}

