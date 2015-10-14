package Game;


import javax.swing.ImageIcon;

import render.*;

/**
 * 
 * @author Michelle O'Neill ID:301025406
 *
 */

public class Item extends Drawable{

	public Location loc;
	public Room roomID;

	/**
	 * Sets up a new item
	 * @param imgLoc
	 * @param pos
	 * @param loc
	 * @param room
	 */
	public Item(String imgLoc, Position pos, Location loc, Room room){
		super(imgLoc, pos);
		this.loc = loc;
		this.roomID = room;
	}

	/**
	 * @return the loc
	 */
	public Location getLoc() {
		return loc;
	}

	/**
	 * @param loc the loc to set
	 */
	public void setLoc(Location loc) {
		this.loc = loc;
	}

	/**
	 * @return the roomID
	 */
	public Room getRoomID() {
		return roomID;
	}

	/**
	 * @param roomID the roomID to set
	 */
	public void setRoomID(Room roomID) {
		this.roomID = roomID;
	}

}
