package Game;

import java.util.ArrayList;

import render.Drawable;
import render.RenderCanvas;
import render.Drawable.Position;

import java.util.List;

import UI.GameFrame;

public class Gameplay {

	private RenderCanvas canvas;
	private GameFrame frame;

	private List<Room> rooms = new ArrayList<Room>();
	private List<Character> characters = new ArrayList<Character>();

	public Gameplay() {

		Room dungeon = new Room(1, 10, 10);
		Room hallway = new Room(2, 10, 10);
		Location doorLoc = dungeon.getFloor()[4][4];
		Trapdoor t1 = new Trapdoor(doorLoc.getWallNE(), doorLoc.getWallNW(), doorLoc.getWallSE(), doorLoc.getWallSW(), doorLoc.getFloor(), doorLoc.getOccupier(),4 ,4 , dungeon, hallway);
//		Trapdoor dungeondoor = new Trapdoor(dungeon, hallway);
//		dungeon.addDoor(dungeondoor);
//		hallway.addDoor(dungeondoor);
//		Location loc = new Location(null, null, null, null, new Item("FloorBlock", Position.FLOOR), null, 0, 0);
//		Item wall = new Item("PlainWall", Position.WALL_NE);
//		dungeon.addItem(wall, loc);
		rooms.add(dungeon);
		rooms.add(hallway);
		Character player1 = new Character("StickFig", Position.CENTER, "Player 1", dungeon);
		player1.setCurrentLocation(
				new Location(null, null, null, null, new Item("FloorBlock", Position.FLOOR), player1, 7, 8));
		characters.add(player1);
		System.out.println(player1.getCurrentLocation().getX() + " " + player1.getCurrentLocation().getY());
		moveEast(player1);
		System.out.println(player1.getCurrentLocation().getX() + " " + player1.getCurrentLocation().getY());
		moveWest(player1);
		System.out.println(player1.getCurrentLocation().getX() + " " + player1.getCurrentLocation().getY());
		moveNorth(player1);
		System.out.println(player1.getCurrentLocation().getX() + " " + player1.getCurrentLocation().getY());
		
	}

	public Gameplay(int result) {
		Room dungeon = new Room(1, 10, 10);
		Room hallway = new Room(2, 5, 5);
//		Trapdoor dungeondoor = new Trapdoor(dungeon, hallway);
//		dungeon.addDoor(dungeondoor);
//		hallway.addDoor(dungeondoor);
		Location loc = new Location(null, null, null, null, new Item("FloorBlock", Position.FLOOR), null, 0, 0);
		Item wall = new Item("PlainWall", Position.WALL_NE);
		dungeon.addItem(wall, loc);
		rooms.add(dungeon);
		rooms.add(hallway);
		Character player1 = new Character("StickFig", Position.CENTER, "Player 1", dungeon);
		player1.setCurrentLocation(
				new Location(null, null, null, null, new Item("FloorBlock", Position.FLOOR), player1, 3, 4));
		characters.add(player1);
		System.out.println("moving right");
		
		
		
		
		
	}

	public void moveEast(Character character) {
		Location current = character.getCurrentLocation();
		Location newLoc = character.getCurrentRoom().getFloor()[character.getCurrentLocation().getX() + 1][character.getCurrentLocation().getY()];
		Drawable itemOnNewLoc = character.getCurrentRoom().checkLocation(newLoc);
		if (itemOnNewLoc == null || itemOnNewLoc instanceof MovableItem) {
			character.moveSpace(newLoc);
			if(itemOnNewLoc !=null ){
				character.pickUpItem((MovableItem) newLoc.getOccupier());
			}
			current.setOccupier(null);
			newLoc.setOccupier(character);
			//TODO Remove println
			System.out.println("moving east");
			
		}
		else if (itemOnNewLoc instanceof NonMovableItem){
			((NonMovableItem) itemOnNewLoc).interact();
		}
	}
	
	public void moveWest(Character character) {
		Location current = character.getCurrentLocation();
		Location newLoc = character.getCurrentRoom().getFloor()[character.getCurrentLocation().getX() - 1][character.getCurrentLocation().getY()];
		Drawable itemOnNewLoc = character.getCurrentRoom().checkLocation(newLoc);
		if (itemOnNewLoc == null || itemOnNewLoc instanceof MovableItem) {
			character.moveSpace(newLoc);
			if(itemOnNewLoc !=null ){
				character.pickUpItem((MovableItem) newLoc.getOccupier());
			}
			current.setOccupier(null);
			newLoc.setOccupier(character);
			//TODO Remove println
			System.out.println("moving east");
		}
		else if (itemOnNewLoc instanceof NonMovableItem){
			((NonMovableItem) itemOnNewLoc).interact();
		}
	}
	
	public void moveSouth(Character character) {
		Location current = character.getCurrentLocation();
		Location newLoc = character.getCurrentRoom().getFloor()[character.getCurrentLocation().getX()][character.getCurrentLocation().getY() + 1];
		Drawable itemOnNewLoc = character.getCurrentRoom().checkLocation(newLoc);
		if (itemOnNewLoc == null || itemOnNewLoc instanceof MovableItem) {
			character.moveSpace(newLoc);
			if(itemOnNewLoc !=null ){
				character.pickUpItem((MovableItem) newLoc.getOccupier());
			}
			current.setOccupier(null);
			newLoc.setOccupier(character);
			//TODO Remove println
			System.out.println("moving east");
		}
		else if (itemOnNewLoc instanceof NonMovableItem){
			((NonMovableItem) itemOnNewLoc).interact();
		}
	}
	
	public void moveNorth(Character character) {
		Location current = character.getCurrentLocation();
		Location newLoc = character.getCurrentRoom().getFloor()[character.getCurrentLocation().getX()][character.getCurrentLocation().getY() - 1];
		Drawable itemOnNewLoc = character.getCurrentRoom().checkLocation(newLoc);
		if (itemOnNewLoc == null || itemOnNewLoc instanceof MovableItem) {
			character.moveSpace(newLoc);
			if(itemOnNewLoc !=null ){
				character.pickUpItem((MovableItem) newLoc.getOccupier());
			}
			current.setOccupier(null);
			newLoc.setOccupier(character);
			//TODO Remove println
			System.out.println("moving east");
		}
		else if (itemOnNewLoc instanceof NonMovableItem){
			((NonMovableItem) itemOnNewLoc).interact();
		}
	}



	/**
	 * @return the rooms
	 */
	public List<Room> getRooms() {
		return rooms;
	}

	/**
	 * @return the characters
	 */
	public List<Character> getCharacters() {
		return characters;
	}

	/**
	 * @return the canvas
	 */
	public RenderCanvas getCanvas() {
		return canvas;
	}

	/**
	 * @param canvas
	 *            the canvas to set
	 */
	public void setCanvas(RenderCanvas canvas) {
		this.canvas = canvas;
	}

	public static void main(String[] args) {

	}

	/**
	 * @return the frame
	 */
	public GameFrame getFrame() {
		return frame;
	}

	/**
	 * @param frame
	 *            the frame to set
	 */
	public void setFrame(GameFrame frame) {
		this.frame = frame;
	}

}
