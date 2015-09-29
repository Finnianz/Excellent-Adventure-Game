package Game;

import java.util.ArrayList;
import java.util.List;

public class Gameplay {
	
	private List<Room> rooms = new ArrayList<Room>();
	private List <Character> characters  = new ArrayList<Character>();
	
	
	public Gameplay() {
		
		Room dungeon = new Room(1,10,10);
		Room hallway = new Room(2,10,5);
		Doorway dungeondoor = new Doorway(dungeon, hallway);
		dungeon.addDoor(dungeondoor);
		hallway.addDoor(dungeondoor);
		Location loc = new Location(0,0);
		Item wall = new Item("Wall", loc, dungeon, null);
		dungeon.addItem(wall, loc);
		rooms.add(dungeon);
		rooms.add(hallway);
		Character player1 = new Character("Player 1");
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
