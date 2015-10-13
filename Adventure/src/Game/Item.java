package Game;

import javax.swing.ImageIcon;

import render.*;

public class Item extends Drawable{
	
//	public String itemType;
	public Location loc;
	public Room roomID;
	public ImageIcon image;
	
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

	/**
	 * @return the image
	 */
	public ImageIcon getImage() {
		return image;
	}
}
