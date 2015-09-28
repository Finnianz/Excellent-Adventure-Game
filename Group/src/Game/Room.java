package Game;

import java.util.ArrayList;
import java.util.List;

public class Room {

	private List<Doorway> doors = new ArrayList<Doorway>();
	private final int roomID;
	private int width;
	private int height;
	private List<Item> items = new ArrayList <Item>();
	
	/**
	 * @param locs - the list of locations to be contained in the room
	 */
	public Room(int id, int width, int height){
		this.roomID = id;
		this.width = width;
		this.height = height;
	}

	/**
	 * @return the doors
	 */
	public List<Doorway> getDoors() {
		return doors;
	}

	/**
	 * @param doors the doors to set
	 */
	public void setDoors(List<Doorway> doors) {
		this.doors = doors;
	}
	
	/**
	 * @return the items in the room
	 */
	public List<Item> getItems() {
		return items;
	}

	/**
	 * @param items - the items to set in the room
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}

	public int getWidth(){
		return this.width;
	}
	
	public int height(){
		return this.height;
	}

	/**
	 * @return the roomID
	 */
	public int getRoomID() {
		return roomID;
	}
	
}
