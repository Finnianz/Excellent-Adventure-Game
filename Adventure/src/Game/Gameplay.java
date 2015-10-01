package Game;

import java.util.ArrayList;

import render.RenderCanvas;
import render.Drawable.Position;

import java.util.List;

import UI.GameFrame;

public class Gameplay {
	
	private RenderCanvas canvas;
	private GameFrame frame;
	
	private List<Room> rooms = new ArrayList<Room>();
	private List <Character> characters  = new ArrayList<Character>();
	
	
	public Gameplay() {
		
		Room dungeon = new Room(1,10,10);
		Room hallway = new Room(2,5,5);
		Doorway dungeondoor = new Doorway(dungeon, hallway);
		dungeon.addDoor(dungeondoor);
		hallway.addDoor(dungeondoor);
		Location loc = new Location(null, null, null, null, new Item("FloorBlock", Position.FLOOR), null, 0,0);
		Item wall = new Item("PlainWall", Position.WALL_NE);
		dungeon.addItem(wall, loc);
		rooms.add(dungeon);
		rooms.add(hallway);
		Character player1 = new Character("StickFig", Position.CENTER, "Player 1", dungeon);
		player1.setCurrentLocation(new Location(null, null, null, null, new Item("FloorBlock", Position.FLOOR), player1, 3, 4));
		characters.add(player1);
		move(new Location(null, null, null, null, new Item("FloorBlock", Position.FLOOR), player1, 3, 5), player1);
		
	}
	
	public void move(Location loc, Character character){
		boolean move = character.getCurrentRoom().checkLocation(loc);
		if(move){
			character.moveSpace(loc);
			System.out.println("moving left");
		}
	}
	
//	public void moveLeft( Character character){
//		new Location(null, null, null, null, new Item("FloorBlock", Position.FLOOR), character, character.getCurrentLocation()., 5)
//		boolean move = character.getCurrentRoom().checkLocation(loc);
//		if(move){
//			character.moveSpace(loc);
//			System.out.println("moving left");
//		}
//	}
	
	
	
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
	 * @param canvas the canvas to set
	 */
	public void setCanvas(RenderCanvas canvas) {
		this.canvas = canvas;
	}
	
	
	public static void main(String[]args){
		Gameplay game = new Gameplay();
		game.setCanvas(new RenderCanvas());
		game.setFrame(new GameFrame());
		game.getFrame().getC().getRenderCanvas().setRoom(game.getRooms().get(0).getFloor());
		
		
		
		
	}

	/**
	 * @return the frame
	 */
	public GameFrame getFrame() {
		return frame;
	}

	/**
	 * @param frame the frame to set
	 */
	public void setFrame(GameFrame frame) {
		this.frame = frame;
	}

}
