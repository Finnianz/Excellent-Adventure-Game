package Game;


import java.util.ArrayList;
import java.util.List;

import render.*;
import render.Drawable.Position;

/**
 * 
 * @author Michelle O'Neill ID:301025406
 *
 */

public class Character extends CustomisableCharacter{

	private final String name;
	private Room currentRoom;
	private Location currentLocation;
	private List<CollectableItem> items = new ArrayList<CollectableItem>();

	/**
	 * 
	 * @param imgLoc
	 * @param hatname
	 * @param name
	 * creates a new character
	 */
	public Character(String imgLoc, String hatname, String name){
		super(imgLoc, hatname);
		this.name = name;
	}
	/**
	 * moves character to new location
	 * @param newLoc
	 */
	public void moveSpace(Location newLoc){
		currentLocation = newLoc;
	}
	/**
	 * adds item picked up to items
	 * @param item
	 */
	public void pickUpItem(CollectableItem item){
		items.add(item);
	}
	/**
	 * returns items
	 * @return items
	 */
	public List<CollectableItem> getItems(){
		return items;
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
