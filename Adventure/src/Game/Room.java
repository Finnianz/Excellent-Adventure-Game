package Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {

	private List<Doorway> doors = new ArrayList<Doorway>();
	private final int roomID;
	private int width;
	private int height;
	private List<Item> items = new ArrayList <Item>();
	private Map<Location, Item> map = new HashMap<Location, Item>();
	
	/**
	 * @param locs - the list of locations to be contained in the room
	 */
	public Room(int id, int width, int height){
		this.roomID = id;
		this.width = width;
		this.height = height;
		for(int i =0; i<width;i++){
			for(int k = 0; k<height;k++){
				Location loc = new Location(i,k);
				map.put(loc, null);
			}
		}
	}

	/**
	 * @return the doors
	 */
	public List<Doorway> getDoors() {
		return doors;
	}

	/**
	 * @param door -  the door to add
	 */
	public void addDoor(Doorway door) {
		this.doors.add(door);
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
	
	public void addItem(Item item, Location loc){
		map.put(loc, item);
	}

	public boolean checkLocation(Location loc) {
		if(map.get(loc)!=null){
			return false;
		}
		else {
			return true;
		}
	}
	
}
