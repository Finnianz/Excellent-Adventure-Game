package Game;

import render.Drawable;

/**
 * 
 * @author Michelle O'Neill ID:301025406
 *
 */

public class Trapdoor extends Location {

	private final Room currentRoom;
	private final Room exitRoom;
	Boolean unlocked = false;
	private int keyID;
	/**
	 * sets up new trapdoor
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
	 * @param keyID
	 */
	public Trapdoor(Drawable wallNE, Drawable wallNW, Drawable wallSE,
			Drawable wallSW, Drawable floor, Drawable occupier, int x, int y, Room currentRoom, Room exitRoom, int keyID){
		super(wallNE, wallNW, wallSE, wallSW, floor, occupier, x,y);
		this.currentRoom = currentRoom;
		this.exitRoom = exitRoom;
		this.keyID = keyID;
	}
	/**
	 * 
	 * @return room that is the exit
	 */
	public Room getExit(){
		return exitRoom;
	}

	/**
	 * 
	 * @param boolean unlocked
	 */
	public void setUnLockRoom(Boolean unlocked){
		this.unlocked = unlocked;
	}
	/**
	 * 
	 * @return boolean unlocked
	 */
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

