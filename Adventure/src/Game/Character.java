package Game;

import java.util.ArrayList;
import java.util.List;

import render.*;
import render.Drawable.Position;


public class Character extends CustomisableCharacter{
	
	private final String name;
	private Room currentRoom;
	private Location currentLocation;
	private List<Item> items = new ArrayList<Item>();
	
	
	public Character(String imgLoc, String hatname, String name){
		super(imgLoc, hatname);
		this.name = name;
	}
	
	public void moveSpace(Location newLoc){
		currentLocation = newLoc;
	}
	
	public void pickUpItem(CollectableItem item){
		items.add(item);
	}
	
	
	/**
	 * @return the currentRoom
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}


	/**
	 * @param currentRoom the currentRoom to set
	 */
	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}


	/**
	 * @return the currentLocation
	 */
	public Location getCurrentLocation() {
		return currentLocation;
	}


	/**
	 * @param currentLocation the currentLocation to set
	 */
	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	
	
	
}
