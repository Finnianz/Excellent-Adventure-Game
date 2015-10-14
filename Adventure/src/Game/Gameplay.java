package Game;

import java.io.Serializable;
import java.util.ArrayList;

import render.Drawable;
import render.DrawableTile;
import render.RenderCanvas;
import render.Drawable.Position;

import java.util.List;

import Main.Main;
import UI.GameFrame;

public class Gameplay implements Serializable {

	private RenderCanvas canvas;
	private GameFrame frame;

	private List<Room> rooms = new ArrayList<Room>();
	private List<Character> characters = new ArrayList<Character>();
	private boolean multi;

	public Gameplay(List<Character> characters, boolean multi) {

		this.characters = characters;
		this.multi = multi;
		// Set up first 2 rooms

		Room tower = new Room(1, 10, 10);
		Room dungeon = new Room(2, 10, 10);
		// set up trapdoor/ladder with empty tile next to each one
		Location doorLoc = tower.getFloor()[4][4];
		Location empty = tower.getFloor()[5][4];
		Trapdoor t1 = new Trapdoor(doorLoc.getWallNE(), doorLoc.getWallNW(), doorLoc.getWallSE(), doorLoc.getWallSW(),
				new Item("SkullDoor", Position.FLOOR, tower.getFloor()[4][4], tower), doorLoc.getOccupier(), 4, 4,
				tower, dungeon, 1);
		tower.getFloor()[4][4] = t1;
		tower.addDoor(t1);
		EmptyTile e1 = new EmptyTile(empty.getWallNE(), empty.getWallNW(), empty.getWallSE(), empty.getWallSW(),
				new Item("FloorBlock", Position.FLOOR, tower.getFloor()[5][4], tower), empty.getOccupier(), 5, 4);
		tower.getFloor()[5][4] = e1;
		Location ladderLoc = dungeon.getFloor()[4][4];
		empty = dungeon.getFloor()[5][4];
		Ladder l1 = new Ladder(ladderLoc.getWallNE(), ladderLoc.getWallNW(), ladderLoc.getWallSE(),
				ladderLoc.getWallSW(), ladderLoc.getFloor(), new Item("Ladder", Position.CENTER, tower.getFloor()[5][4], dungeon), 4, 4, dungeon, tower);
		dungeon.getFloor()[4][4] = l1;
		e1 = new EmptyTile(empty.getWallNE(), empty.getWallNW(), empty.getWallSE(), empty.getWallSW(), empty.getFloor(),
				empty.getOccupier(), 5, 5);
		dungeon.getFloor()[5][4] = e1;
		// Set up items in Tower
		Location boxLoc = tower.getFloor()[7][7];
		MovableItem box = new MovableItem("Box", Position.SQUARE, boxLoc, tower);
		tower.getFloor()[7][7].setOccupier(box);
		// tower.addItem(box, tower.getFloor()[7][7]);
		Location book = tower.getFloor()[9][6];
		StationaryItem bookshelf = new StationaryItem("RedBook", Position.CENTER, book, tower, true, false, 2,
				new CollectableItem("SkelKey", Position.CENTER, tower, null, 1));
		tower.getFloor()[9][6].setOccupier(bookshelf);
		rooms.add(tower);
		rooms.add(dungeon);

		// add player
		Location playerLoc = tower.getFloor()[3][7];
		Character player1 = characters.get(0);

		player1.setCurrentRoom(tower);
		player1.setCurrentLocation(playerLoc);

		playerLoc.setOccupier(player1);

		if (multi) {
			Location playerLoc2 = tower.getFloor()[4][7];
			Character player2 = characters.get(1);
			player2.setCurrentRoom(tower);
			player2.setCurrentLocation(playerLoc2);

			playerLoc2.setOccupier(player2);
		}

	}

	public void moveEast(Character character) {
		Location current = character.getCurrentLocation();
		if (current.getY() + 1 < character.getCurrentRoom().getFloor().length) {
			Location newLoc = character.getCurrentRoom().getFloor()[character.getCurrentLocation().getX()][character
					.getCurrentLocation().getY() + 1];
			Drawable itemOnNewLoc = character.getCurrentRoom().checkLocation(newLoc);
			if (newLoc instanceof Trapdoor) {
				if (!((Trapdoor) newLoc).isLocked()) {
					character.moveSpace(newLoc);
					current.setOccupier(null);
					newLoc.setOccupier(character);
					// TODO Remove println
					System.out.println("moving east");
					return;
				} else {
					character.setCurrentRoom(((Trapdoor) newLoc).getExit());
					Location newRoomLoc = character.getCurrentRoom().getFloor()[newLoc.getX()][newLoc.getY() + 1];
					character.setCurrentLocation(newRoomLoc);
					newRoomLoc.setOccupier(character);
					current.setOccupier(null);
					((Trapdoor) newLoc).setLockRoom(true);
					Main.setRoom(character.getCurrentRoom());
					
				}
			}
			if (newLoc instanceof Ladder) {
				character.setCurrentRoom(((Ladder) newLoc).getExit());
				Location newRoomLoc = character.getCurrentRoom().getFloor()[newLoc.getX()][newLoc.getY() + 1];
				character.setCurrentLocation(newRoomLoc);
				newRoomLoc.setOccupier(character);
				current.setOccupier(null);
				Main.setRoom(character.getCurrentRoom());
			}
			if (itemOnNewLoc == null || itemOnNewLoc instanceof CollectableItem) {
				character.moveSpace(newLoc);
				if (itemOnNewLoc != null) {
					character.pickUpItem((CollectableItem) newLoc.getOccupier());
				}
				current.setOccupier(null);
				newLoc.setOccupier(character);
				// TODO Remove println
				System.out.println("moving east");

			} else if (itemOnNewLoc instanceof StationaryItem) {
				((StationaryItem) itemOnNewLoc).interact();
			} else if (itemOnNewLoc instanceof MovableItem) {
				((MovableItem) itemOnNewLoc).move(newLoc, current, character.getCurrentRoom(), character);
			}
		}
	}

	public void moveWest(Character character) {
		Location current = character.getCurrentLocation();
		if (current.getY() - 1 >= 0) {
			Location newLoc = character.getCurrentRoom().getFloor()[character.getCurrentLocation().getX()][character
					.getCurrentLocation().getY() - 1];
			Drawable itemOnNewLoc = character.getCurrentRoom().checkLocation(newLoc);
			if (newLoc instanceof Trapdoor) {
				if (!((Trapdoor) newLoc).isLocked()) {
					character.moveSpace(newLoc);
					current.setOccupier(null);
					newLoc.setOccupier(character);
					// TODO Remove println
					System.out.println("moving east");
					return;
					// TODO stand on locked door
				} else {
					character.setCurrentRoom(((Trapdoor) newLoc).getExit());
					Location newRoomLoc = character.getCurrentRoom().getFloor()[newLoc.getX()][newLoc.getY() - 1];
					character.setCurrentLocation(newRoomLoc);
					newRoomLoc.setOccupier(character);
					current.setOccupier(null);
					((Trapdoor) newLoc).setLockRoom(true);
					Main.setRoom(character.getCurrentRoom());
				}
			}
			if (newLoc instanceof Ladder) {
				character.setCurrentRoom(((Ladder) newLoc).getExit());
				Location newRoomLoc = character.getCurrentRoom().getFloor()[newLoc.getX()][newLoc.getY() - 1];
				character.setCurrentLocation(newRoomLoc);
				newRoomLoc.setOccupier(character);
				current.setOccupier(null);
				Main.setRoom(character.getCurrentRoom());
			}
			if (itemOnNewLoc == null || itemOnNewLoc instanceof CollectableItem) {
				character.moveSpace(newLoc);
				if (itemOnNewLoc != null) {
					character.pickUpItem((CollectableItem) newLoc.getOccupier());
				}
				current.setOccupier(null);
				newLoc.setOccupier(character);
				// TODO Remove println
				System.out.println("moving west");
			} else if (itemOnNewLoc instanceof StationaryItem) {
				((StationaryItem) itemOnNewLoc).interact();
			} else if (itemOnNewLoc instanceof MovableItem) {
				((MovableItem) itemOnNewLoc).move(newLoc, current, character.getCurrentRoom(), character);
			}
		}
	}

	public void moveSouth(Character character) {
		Location current = character.getCurrentLocation();
		if (current.getX() + 1 < character.getCurrentRoom().getFloor().length) {
			Location newLoc = character.getCurrentRoom().getFloor()[character.getCurrentLocation().getX() + 1][character
					.getCurrentLocation().getY()];
			Drawable itemOnNewLoc = character.getCurrentRoom().checkLocation(newLoc);
			if (newLoc instanceof Trapdoor) {
				if (!((Trapdoor) newLoc).isLocked()) {
					character.moveSpace(newLoc);
					current.setOccupier(null);
					newLoc.setOccupier(character);
					// TODO Remove println
					System.out.println("moving east");
					return;
					// TODO stand on locked door
				} else {
					character.setCurrentRoom(((Trapdoor) newLoc).getExit());
					Location newRoomLoc = character.getCurrentRoom().getFloor()[newLoc.getX() + 1][newLoc.getY()];
					character.setCurrentLocation(newRoomLoc);
					newRoomLoc.setOccupier(character);
					current.setOccupier(null);
					((Trapdoor) newLoc).setLockRoom(true);

					Main.setRoom(character.getCurrentRoom());
				}
			}
			if (newLoc instanceof Ladder) {
				character.setCurrentRoom(((Ladder) newLoc).getExit());
				Location newRoomLoc = character.getCurrentRoom().getFloor()[newLoc.getX() + 1][newLoc.getY()];
				character.setCurrentLocation(newRoomLoc);
				newRoomLoc.setOccupier(character);
				current.setOccupier(null);
				Main.setRoom(character.getCurrentRoom());
			}
			if (itemOnNewLoc == null || itemOnNewLoc instanceof CollectableItem) {
				character.moveSpace(newLoc);
				if (itemOnNewLoc != null) {
					character.pickUpItem((CollectableItem) newLoc.getOccupier());
				}
				current.setOccupier(null);
				newLoc.setOccupier(character);
				// TODO Remove println
				System.out.println("moving South");
			} else if (itemOnNewLoc instanceof StationaryItem) {
				((StationaryItem) itemOnNewLoc).interact();
			} else if (itemOnNewLoc instanceof MovableItem) {
				((MovableItem) itemOnNewLoc).move(newLoc, current, character.getCurrentRoom(), character);
			}
		}
	}

	public void moveNorth(Character character) {
		Location current = character.getCurrentLocation();
		if (current.getX() - 1 >= 0) {
			Location newLoc = character.getCurrentRoom().getFloor()[character.getCurrentLocation().getX() - 1][character
					.getCurrentLocation().getY()];
			Drawable itemOnNewLoc = character.getCurrentRoom().checkLocation(newLoc);
			if (newLoc instanceof Trapdoor) {
				if (!((Trapdoor) newLoc).isLocked()) {
					character.moveSpace(newLoc);
					current.setOccupier(null);
					newLoc.setOccupier(character);
					// TODO Remove println
					System.out.println("moving east");
					return;
					// TODO stand on locked door
				} else {
					character.setCurrentRoom(((Trapdoor) newLoc).getExit());
					Location newRoomLoc = character.getCurrentRoom().getFloor()[newLoc.getX() - 1][newLoc.getY()];
					character.setCurrentLocation(newRoomLoc);
					newRoomLoc.setOccupier(character);
					current.setOccupier(null);
					((Trapdoor) newLoc).setLockRoom(true);

					Main.setRoom(character.getCurrentRoom());
				}
			}
			if (newLoc instanceof Ladder) {
				character.setCurrentRoom(((Ladder) newLoc).getExit());
				Location newRoomLoc = character.getCurrentRoom().getFloor()[newLoc.getX() - 1][newLoc.getY()];
				character.setCurrentLocation(newRoomLoc);
				newRoomLoc.setOccupier(character);
				current.setOccupier(null);
				Main.setRoom(character.getCurrentRoom());
			}
			if (itemOnNewLoc == null || itemOnNewLoc instanceof CollectableItem) {
				character.moveSpace(newLoc);
				if (itemOnNewLoc != null) {
					character.pickUpItem((CollectableItem) newLoc.getOccupier());
				}
				current.setOccupier(null);
				newLoc.setOccupier(character);
				// TODO Remove println
				System.out.println("moving north");
			} else if (itemOnNewLoc instanceof StationaryItem) {
				((StationaryItem) itemOnNewLoc).interact();
			} else if (itemOnNewLoc instanceof MovableItem) {
				((MovableItem) itemOnNewLoc).move(newLoc, current, character.getCurrentRoom(), character);
			}
		}
	}

	public void useItem(CollectableItem selectedFromBag, DrawableTile selectedOnBoard, Character player) {
		int key;
		for (int i = 0; i < player.getCurrentRoom().getDoors().size(); i++) {
			if (selectedOnBoard instanceof Trapdoor) {
				key = ((Trapdoor) selectedOnBoard).getKeyID();
				if (selectedFromBag.getiD() == key) {
					((Trapdoor) selectedOnBoard).setLockRoom(false);
					// TODO respawn keys here or when door is used?
				}
			}
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
	 * 
	 */
	public void addCharacter(Game.Character character) {
		characters.add(character);
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
