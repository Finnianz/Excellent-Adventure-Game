package Game;

import java.util.ArrayList;
import render.Drawable.Position;
import java.util.List;

public class Gameplay {
	
	private List<Room> rooms = new ArrayList<Room>();
	private List <Character> characters  = new ArrayList<Character>();
	
	
	public Gameplay() {
		
		Room dungeon = new Room(1,10,10);
		Room hallway = new Room(2,5,5);
		Doorway dungeondoor = new Doorway(dungeon, hallway);
		dungeon.addDoor(dungeondoor);
		hallway.addDoor(dungeondoor);
		Location loc = new Location(null, null, null, null, new Item("FloorBlock", Position.FLOOR), null, 0,0);
		Item wall = new Item("wall.png", Position.WALL_NE);
		dungeon.addItem(wall, loc);
		rooms.add(dungeon);
		rooms.add(hallway);
		Character player1 = new Character("sticky", Position.CENTER, "Player 1", dungeon);
		player1.setCurrentLocation(new Location(null, null, null, null, new Item("FloorBlock", Position.FLOOR), player1, 3, 4));
		characters.add(player1);
		
	}
	
	public void move(Location loc, Character character){
		boolean move = character.getCurrentRoom().checkLocation(loc);
		if(move){
			character.moveSpace(loc);
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

}
