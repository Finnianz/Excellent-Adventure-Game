package Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import render.*;
import render.Drawable.Position;
public class Room {

//	private List<Trapdoor> doors = new ArrayList<Trapdoor>();
	private final int roomID;
	private int width;
	private int height;
	private List<Item> items = new ArrayList <Item>();
//	private Map<Location, Item> map = new HashMap<Location, Item>();
	private Location[][] floor; 
	
	/**
	 * rooms must be square at the moment.  ie. width == height
	 */
	public Room(int id, int width, int height){
		this.roomID = id;
		this.width = width;
		this.height = height;
		setFloor(new Location[width][height]);
		for(int i=0;i<floor.length;i++){
			for(int j= 0;j<floor.length;j++){
				Drawable floortile = new Item("FloorBlock", Position.FLOOR);
				Drawable occupier = null;
				Drawable wallNE = null;
				Drawable wallNW = null;
				Drawable wallSE = null;
				Drawable wallSW = null;
				if(i==0){
					wallNW = new Item("PlainWall1", Position.WALL_NW);
				}
				if(j==0){
					wallSW = new Item("PlainWall1", Position.WALL_SW);
				}
				if(i==floor.length-1){
					wallSE = new Item("PlainWall1", Position.WALL_SE);
				}
				if(j==floor.length-1){
					wallNE = new Item("PlainWall1", Position.WALL_NE);
				}
//				if(i==0 && j==floor.length-1){
//					occupier = new Chter(figureString, Position.CENTER);
//				}
				floor[i][j] = new Location(wallNE, wallNW, wallSE,wallSW, floortile, occupier, i , j);
			}
		  }
	}

//	/**
//	 * @return the doors
//	 */
//	public List<Trapdoor> getDoors() {
//		return doors;
//	}
//
//	/**
//	 * @param door -  the door to add
//	 */
//	public void addDoor(Trapdoor door) {
//		this.doors.add(door);
//	}
	
//	/**
//	 * @return the items in the room
//	 */
//	public List<Item> getItems() {
//		return items;
//	}
//
//	/**
//	 * @param items - the items to set in the room
//	 */
//	public void setItems(List<Item> items) {
//		this.items = items;
//	}

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
	
//	public void addItem(Item item, Location loc){
//		floor[loc.getX()][loc.getY()].setOccupier(item);
//	}

	public Drawable checkLocation(Location loc) {
//		if(floor[loc.getX()][loc.getY()].getOccupier() == null){
//			return null;
//		}
//		else if(floor[loc.getX()][loc.getY()].getOccupier() instanceof CollectableItem) {
//			return true;
//		}
//		else {
//			return false;
//		}
		return floor[loc.getX()][loc.getY()].getOccupier();
	}

	/**
	 * @return the floor
	 */
	public Location[][] getFloor() {
		return floor;
	}

	/**
	 * @param floor the floor to set
	 */
	public void setFloor(Location[][] floor) {
		this.floor = floor;
	}
	
}
