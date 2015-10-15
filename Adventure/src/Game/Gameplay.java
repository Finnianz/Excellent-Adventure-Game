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

/**
 * 
 * @author Michelle O'Neill ID:301025406
 *
 */

public class Gameplay implements Serializable {

	private RenderCanvas canvas;
	private GameFrame frame;
	private List<Room> rooms = new ArrayList<Room>();
	private List<Character> characters = new ArrayList<Character>();
	private boolean multi;

	/**
	 * starts a new gameplay
	 * @param characters
	 * @param multi
	 */
	public Gameplay(List<Character> characters, boolean multi) {

		this.characters = characters;
		this.multi = multi;
		// Set up first 2 rooms

		Room room1 = new Room(1, 10, 10);
		Room room2 = new Room(2, 10, 10);
		Room room3 = new Room(3, 10, 10);
		Room room4 = new Room(4, 10, 10);
		Room room5 = new Room(5, 10, 10);
		Room room6 = new Room(6, 10, 10);
		Room room7 = new Room(7, 10, 10);
		Room room8 = new Room(8, 10, 10);
		// set up trapdoor/ladder with empty tile next to each one
		//room one
		Location doorLoc = room1.getFloor()[4][4];
		Location empty = room1.getFloor()[5][4];
		Trapdoor t1 = new Trapdoor(doorLoc.getWallNE(), doorLoc.getWallNW(), doorLoc.getWallSE(), doorLoc.getWallSW(),
				new Item("SkullDoor", Position.FLOOR, room1.getFloor()[4][4], room1), doorLoc.getOccupier(), doorLoc.getX(), doorLoc.getY(),
				room1, room2, 1);
		room1.getFloor()[doorLoc.getX()][doorLoc.getY()] = t1;
		room1.addDoor(t1);
		EmptyTile e1 = new EmptyTile(empty.getWallNE(), empty.getWallNW(), empty.getWallSE(), empty.getWallSW(),
				new Item("FloorBlock", Position.FLOOR, room1.getFloor()[5][4], room1), empty.getOccupier(), empty.getX(), empty.getY());
		room1.getFloor()[empty.getX()][empty.getY()] = e1;
		Location ladderLoc = room2.getFloor()[doorLoc.getX()][doorLoc.getY()];
		empty = room2.getFloor()[empty.getX()][empty.getY()];
		Ladder l1 = new Ladder(ladderLoc.getWallNE(), ladderLoc.getWallNW(), ladderLoc.getWallSE(),
				ladderLoc.getWallSW(), ladderLoc.getFloor(), new Item("Ladder", Position.CENTER, room1.getFloor()[4][4], room2), doorLoc.getX(), doorLoc.getY(), room2, room1);
		room2.getFloor()[doorLoc.getX()][doorLoc.getY()] = l1;
		e1 = new EmptyTile(empty.getWallNE(), empty.getWallNW(), empty.getWallSE(), empty.getWallSW(), new Item("FloorBlock", Position.FLOOR, room2.getFloor()[5][4], room2),
				empty.getOccupier(), empty.getX(), empty.getY());
		room2.getFloor()[empty.getX()][empty.getY()] = e1;
		// Set up items in room1
		Location boxLoc = room1.getFloor()[7][7];
		MovableItem box = new MovableItem("Box", Position.SQUARE, boxLoc, room1);
		room1.getFloor()[boxLoc.getX()][boxLoc.getY()].setOccupier(box);
		Location itemLoc = room1.getFloor()[9][6];
		StationaryItem bookshelf = new StationaryItem("RedBook", Position.CENTER, itemLoc, room1, true, false, 2,
				null);
		CollectableItem key = new CollectableItem("SkelKey", Position.CENTER, room1, null, 1, bookshelf);
		bookshelf.setHiddenItem(key);
		room1.getFloor()[9][6].setOccupier(bookshelf);
		//========================
		//set up 2nd room
		//========================
		doorLoc = room2.getFloor()[7][7];
		empty = room2.getFloor()[8][7];
		t1 = new Trapdoor(doorLoc.getWallNE(), doorLoc.getWallNW(), doorLoc.getWallSE(), doorLoc.getWallSW(),
				new Item("SkullDoor", Position.FLOOR, room2.getFloor()[doorLoc.getX()][doorLoc.getY()], room2), doorLoc.getOccupier(), doorLoc.getX(), doorLoc.getY(),
				room2, room3, 3);
		room2.getFloor()[doorLoc.getX()][doorLoc.getY()] = t1;
		room1.addDoor(t1);
		e1 = new EmptyTile(empty.getWallNE(), empty.getWallNW(), empty.getWallSE(), empty.getWallSW(),
				new Item("FloorBlock", Position.FLOOR, room2.getFloor()[empty.getX()][empty.getY()], room2), empty.getOccupier(), empty.getX(), empty.getY()); 
		room1.getFloor()[empty.getX()][empty.getY()] = e1;
		ladderLoc = room3.getFloor()[doorLoc.getX()][doorLoc.getY()];
		empty = room3.getFloor()[empty.getX()][empty.getY()];
		l1 = new Ladder(ladderLoc.getWallNE(), ladderLoc.getWallNW(), ladderLoc.getWallSE(),
				ladderLoc.getWallSW(), ladderLoc.getFloor(), new Item("Ladder", Position.CENTER, room3.getFloor()[ladderLoc.getX()][ladderLoc.getY()], room2), doorLoc.getX(), doorLoc.getY(), room3, room2);
		room3.getFloor()[doorLoc.getX()][doorLoc.getY()] = l1;
		e1 = new EmptyTile(empty.getWallNE(), empty.getWallNW(), empty.getWallSE(), empty.getWallSW(), new Item("FloorBlock", Position.FLOOR, room2.getFloor()[empty.getX()][empty.getY()], room3),
				empty.getOccupier(), empty.getX(), empty.getY());
		room2.getFloor()[empty.getX()][empty.getY()] = e1;
		//add items to second room
		itemLoc = room2.getFloor()[5][0];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room2, true, false, 4,
				null);
		StationaryItem novel =  new StationaryItem("RedBook", Position.CENTER, itemLoc, room2, true, false, 4,
				null);
		bookshelf.setHiddenItem(novel);
		key = new CollectableItem("SkelKey", Position.CENTER, room2, null, 3, novel);
		novel.setHiddenItem(key);
		room2.getFloor()[5][0].setOccupier(bookshelf);
		//======================
		//set up 3rd room
		//======================
		doorLoc = room3.getFloor()[3][9];
		empty = room3.getFloor()[4][9];
		t1 = new Trapdoor(doorLoc.getWallNE(), doorLoc.getWallNW(), doorLoc.getWallSE(), doorLoc.getWallSW(),
				new Item("BatDoor", Position.FLOOR, room3.getFloor()[doorLoc.getX()][doorLoc.getY()], room3), doorLoc.getOccupier(), doorLoc.getX(), doorLoc.getY(),
				room3, room4, 4);
		room3.getFloor()[doorLoc.getX()][doorLoc.getY()] = t1;
		room3.addDoor(t1);
		e1 = new EmptyTile(empty.getWallNE(), empty.getWallNW(), empty.getWallSE(), empty.getWallSW(),
				new Item("FloorBlock", Position.FLOOR, room3.getFloor()[empty.getX()][empty.getY()], room3), empty.getOccupier(), empty.getX(), empty.getY()); 
		room3.getFloor()[empty.getX()][empty.getY()] = e1;
		ladderLoc = room4.getFloor()[doorLoc.getX()][doorLoc.getY()];
		empty = room4.getFloor()[empty.getX()][empty.getY()];
		l1 = new Ladder(ladderLoc.getWallNE(), ladderLoc.getWallNW(), ladderLoc.getWallSE(),
				ladderLoc.getWallSW(), ladderLoc.getFloor(), new Item("Ladder", Position.CENTER, room4.getFloor()[ladderLoc.getX()][ladderLoc.getY()], room4), doorLoc.getX(), doorLoc.getY(), room4, room3);
		room4.getFloor()[doorLoc.getX()][doorLoc.getY()] = l1;
		e1 = new EmptyTile(empty.getWallNE(), empty.getWallNW(), empty.getWallSE(), empty.getWallSW(), new Item("FloorBlock", Position.FLOOR, room4.getFloor()[empty.getX()][empty.getY()], room4),
				empty.getOccupier(), empty.getX(), empty.getY());
		room4.getFloor()[empty.getX()][empty.getY()] = e1;
		//set up items in 3rd room
		itemLoc = room3.getFloor()[5][4];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room3, false, false, 0, null);
		room3.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		itemLoc = room3.getFloor()[9][7];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room3, false, false, 0, null);
		room3.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		itemLoc = room3.getFloor()[0][2];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room3, true, false, 6, null);
		key = new CollectableItem("Batkey", Position.CENTER, room3, null, 4, bookshelf);
		bookshelf.setHiddenItem(key);
		room3.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		//==================
		//set up room4
		//==================
		doorLoc = room4.getFloor()[7][4];
		empty = room4.getFloor()[8][4];
		t1 = new Trapdoor(doorLoc.getWallNE(), doorLoc.getWallNW(), doorLoc.getWallSE(), doorLoc.getWallSW(),
				new Item("GhostDoor", Position.FLOOR, room4.getFloor()[doorLoc.getX()][doorLoc.getY()], room4), doorLoc.getOccupier(), doorLoc.getX(), doorLoc.getY(),
				room4, room5, 5);
		room4.getFloor()[doorLoc.getX()][doorLoc.getY()] = t1;
		room4.addDoor(t1);
		e1 = new EmptyTile(empty.getWallNE(), empty.getWallNW(), empty.getWallSE(), empty.getWallSW(),
				new Item("FloorBlock", Position.FLOOR, room4.getFloor()[empty.getX()][empty.getY()], room4), empty.getOccupier(), empty.getX(), empty.getY()); 
		room4.getFloor()[empty.getX()][empty.getY()] = e1;
		ladderLoc = room5.getFloor()[doorLoc.getX()][doorLoc.getY()];
		empty = room5.getFloor()[empty.getX()][empty.getY()];
		l1 = new Ladder(ladderLoc.getWallNE(), ladderLoc.getWallNW(), ladderLoc.getWallSE(),
				ladderLoc.getWallSW(), ladderLoc.getFloor(), new Item("Ladder", Position.CENTER, room5.getFloor()[ladderLoc.getX()][ladderLoc.getY()], room5), doorLoc.getX(), doorLoc.getY(), room5, room4);
		room5.getFloor()[doorLoc.getX()][doorLoc.getY()] = l1;
		e1 = new EmptyTile(empty.getWallNE(), empty.getWallNW(), empty.getWallSE(), empty.getWallSW(), new Item("FloorBlock", Position.FLOOR, room5.getFloor()[empty.getX()][empty.getY()], room5),
				empty.getOccupier(), empty.getX(), empty.getY());
		room5.getFloor()[empty.getX()][empty.getY()] = e1;
		//set up items in room4
		itemLoc = room4.getFloor()[5][0];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room4, true, false, 8, null);
		key = new CollectableItem("ghostkey", Position.CENTER, room3, null, 5, bookshelf);
		bookshelf.setHiddenItem(key);
		room4.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		itemLoc = room4.getFloor()[3][0];
		box = new MovableItem("box", Position.CENTER, itemLoc, room4);
		room4.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(box);
		itemLoc = room4.getFloor()[3][1];
		box = new MovableItem("box", Position.CENTER, itemLoc, room4);
		room4.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(box);
		itemLoc = room4.getFloor()[4][2];
		box = new MovableItem("box", Position.CENTER, itemLoc, room4);
		room4.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(box);
		itemLoc = room4.getFloor()[5][2];
		box = new MovableItem("box", Position.CENTER, itemLoc, room4);
		room4.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(box);
		itemLoc = room4.getFloor()[6][2];
		bookshelf = new StationaryItem("box", Position.CENTER, itemLoc, room4, false, false, 0, null);
		room4.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		itemLoc = room4.getFloor()[7][1];
		box = new MovableItem("box", Position.CENTER, itemLoc, room4);
		room4.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(box);
		//====================
		//set up room5
		//====================
		doorLoc = room5.getFloor()[2][2];
		empty = room5.getFloor()[3][2];
		t1 = new Trapdoor(doorLoc.getWallNE(), doorLoc.getWallNW(), doorLoc.getWallSE(), doorLoc.getWallSW(),
				new Item("GhostDoor", Position.FLOOR, room5.getFloor()[doorLoc.getX()][doorLoc.getY()], room5), doorLoc.getOccupier(), doorLoc.getX(), doorLoc.getY(),
				room5, room6, 6);
		room5.getFloor()[doorLoc.getX()][doorLoc.getY()] = t1;
		room5.addDoor(t1);
		e1 = new EmptyTile(empty.getWallNE(), empty.getWallNW(), empty.getWallSE(), empty.getWallSW(),
				new Item("FloorBlock", Position.FLOOR, room5.getFloor()[empty.getX()][empty.getY()], room5), empty.getOccupier(), empty.getX(), empty.getY()); 
		room5.getFloor()[empty.getX()][empty.getY()] = e1;
		ladderLoc = room6.getFloor()[doorLoc.getX()][doorLoc.getY()];
		empty = room6.getFloor()[empty.getX()][empty.getY()];
		l1 = new Ladder(ladderLoc.getWallNE(), ladderLoc.getWallNW(), ladderLoc.getWallSE(),
				ladderLoc.getWallSW(), ladderLoc.getFloor(), new Item("Ladder", Position.CENTER, room6.getFloor()[ladderLoc.getX()][ladderLoc.getY()], room6), doorLoc.getX(), doorLoc.getY(), room6, room5);
		room6.getFloor()[doorLoc.getX()][doorLoc.getY()] = l1;
		e1 = new EmptyTile(empty.getWallNE(), empty.getWallNW(), empty.getWallSE(), empty.getWallSW(), new Item("FloorBlock", Position.FLOOR, room6.getFloor()[empty.getX()][empty.getY()], room6),
				empty.getOccupier(), empty.getX(), empty.getY());
		room6.getFloor()[empty.getX()][empty.getY()] = e1;

		doorLoc = room5.getFloor()[8][8];
		empty = room5.getFloor()[9][8];
		t1 = new Trapdoor(doorLoc.getWallNE(), doorLoc.getWallNW(), doorLoc.getWallSE(), doorLoc.getWallSW(),
				new Item("Batdoor", Position.FLOOR, room5.getFloor()[doorLoc.getX()][doorLoc.getY()], room5), doorLoc.getOccupier(), doorLoc.getX(), doorLoc.getY(),
				room5, room6, 7);
		room5.getFloor()[doorLoc.getX()][doorLoc.getY()] = t1;
		room5.addDoor(t1);
		e1 = new EmptyTile(empty.getWallNE(), empty.getWallNW(), empty.getWallSE(), empty.getWallSW(),
				new Item("FloorBlock", Position.FLOOR, room5.getFloor()[empty.getX()][empty.getY()], room5), empty.getOccupier(), empty.getX(), empty.getY()); 
		room5.getFloor()[empty.getX()][empty.getY()] = e1;
		ladderLoc = room6.getFloor()[doorLoc.getX()][doorLoc.getY()];
		empty = room6.getFloor()[empty.getX()][empty.getY()];
		l1 = new Ladder(ladderLoc.getWallNE(), ladderLoc.getWallNW(), ladderLoc.getWallSE(),
				ladderLoc.getWallSW(), ladderLoc.getFloor(), new Item("Ladder", Position.CENTER, room6.getFloor()[ladderLoc.getX()][ladderLoc.getY()], room6), doorLoc.getX(), doorLoc.getY(), room6, room5);
		room6.getFloor()[doorLoc.getX()][doorLoc.getY()] = l1;
		e1 = new EmptyTile(empty.getWallNE(), empty.getWallNW(), empty.getWallSE(), empty.getWallSW(), new Item("FloorBlock", Position.FLOOR, room6.getFloor()[empty.getX()][empty.getY()], room6),
				empty.getOccupier(), empty.getX(), empty.getY());
		room6.getFloor()[empty.getX()][empty.getY()] = e1;
		//set up items
		itemLoc = room5.getFloor()[0][8];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room5, false, false, 0, null);
		room5.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		itemLoc = room5.getFloor()[9][7];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room5, false, false, 0, null);
		room5.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		itemLoc = room5.getFloor()[6][1];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room5, true, false, 6, null);
		key = new CollectableItem("Batkey", Position.CENTER, room5, null, 7, bookshelf);
		bookshelf.setHiddenItem(key);
		room5.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		itemLoc = room5.getFloor()[8][0];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room5, false, false, 0, null);
		room5.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		itemLoc = room5.getFloor()[4][5];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room5, true, false, 6, null);
		key = new CollectableItem("ghostkey", Position.CENTER, room5, null, 6, bookshelf);
		bookshelf.setHiddenItem(key);
		room5.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		//===================
		//set up room 6
		//===================
		doorLoc = room6.getFloor()[6][6];
		empty = room6.getFloor()[7][6];
		t1 = new Trapdoor(doorLoc.getWallNE(), doorLoc.getWallNW(), doorLoc.getWallSE(), doorLoc.getWallSW(),
				new Item("Batdoor", Position.FLOOR, room6.getFloor()[doorLoc.getX()][doorLoc.getY()], room6), doorLoc.getOccupier(), doorLoc.getX(), doorLoc.getY(),
				room6, room7, 8);
		room6.getFloor()[doorLoc.getX()][doorLoc.getY()] = t1;
		room6.addDoor(t1);
		e1 = new EmptyTile(empty.getWallNE(), empty.getWallNW(), empty.getWallSE(), empty.getWallSW(),
				new Item("FloorBlock", Position.FLOOR, room6.getFloor()[empty.getX()][empty.getY()], room6), empty.getOccupier(), empty.getX(), empty.getY()); 
		room6.getFloor()[empty.getX()][empty.getY()] = e1;
		ladderLoc = room7.getFloor()[doorLoc.getX()][doorLoc.getY()];
		empty = room7.getFloor()[empty.getX()][empty.getY()];
		l1 = new Ladder(ladderLoc.getWallNE(), ladderLoc.getWallNW(), ladderLoc.getWallSE(),
				ladderLoc.getWallSW(), ladderLoc.getFloor(), new Item("Ladder", Position.CENTER, room7.getFloor()[ladderLoc.getX()][ladderLoc.getY()], room7), doorLoc.getX(), doorLoc.getY(), room7, room6);
		room7.getFloor()[doorLoc.getX()][doorLoc.getY()] = l1;
		e1 = new EmptyTile(empty.getWallNE(), empty.getWallNW(), empty.getWallSE(), empty.getWallSW(), new Item("FloorBlock", Position.FLOOR, room7.getFloor()[empty.getX()][empty.getY()], room7),
				empty.getOccupier(), empty.getX(), empty.getY());
		room7.getFloor()[empty.getX()][empty.getY()] = e1;
		//set up items in room6
		itemLoc = room6.getFloor()[0][4];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room6, false, false, 0, null);
		room6.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		itemLoc = room6.getFloor()[1][4];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room6, false, false, 0, null);
		room6.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		itemLoc = room6.getFloor()[2][4];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room6, false, false, 0, null);
		room6.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		itemLoc = room6.getFloor()[3][4];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room6, false, false, 0, null);
		room6.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		itemLoc = room6.getFloor()[4][4];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room6, false, false, 0, null);
		room6.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		itemLoc = room6.getFloor()[4][3];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room6, false, false, 0, null);
		room6.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		itemLoc = room6.getFloor()[4][2];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room6, false, false, 0, null);
		room6.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		itemLoc = room6.getFloor()[4][1];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room6, false, false, 0, null);
		room6.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		itemLoc = room6.getFloor()[4][0];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room6, false, false, 0, null);
		room6.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		itemLoc = room6.getFloor()[0][4];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room6, false, false, 0, null);
		room6.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		itemLoc = room6.getFloor()[0][2];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room6, true, false, 6, null);
		key = new CollectableItem("batkey", Position.CENTER, room6, null, 8, bookshelf);
		bookshelf.setHiddenItem(key);
		room6.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		//========================
		//set up room 7
		//========================
		doorLoc = room7.getFloor()[2][1];
		empty = room7.getFloor()[3][1];
		t1 = new Trapdoor(doorLoc.getWallNE(), doorLoc.getWallNW(), doorLoc.getWallSE(), doorLoc.getWallSW(),
				new Item("Skulldoor", Position.FLOOR, room7.getFloor()[doorLoc.getX()][doorLoc.getY()], room7), doorLoc.getOccupier(), doorLoc.getX(), doorLoc.getY(),
				room7, room8, 10);
		room7.getFloor()[doorLoc.getX()][doorLoc.getY()] = t1;
		room7.addDoor(t1);
		e1 = new EmptyTile(empty.getWallNE(), empty.getWallNW(), empty.getWallSE(), empty.getWallSW(),
				new Item("FloorBlock", Position.FLOOR, room7.getFloor()[empty.getX()][empty.getY()], room7), empty.getOccupier(), empty.getX(), empty.getY()); 
		room7.getFloor()[empty.getX()][empty.getY()] = e1;
		ladderLoc = room8.getFloor()[doorLoc.getX()][doorLoc.getY()];
		empty = room8.getFloor()[empty.getX()][empty.getY()];
		l1 = new Ladder(ladderLoc.getWallNE(), ladderLoc.getWallNW(), ladderLoc.getWallSE(),
				ladderLoc.getWallSW(), ladderLoc.getFloor(), new Item("Ladder", Position.CENTER, room8.getFloor()[ladderLoc.getX()][ladderLoc.getY()], room8), doorLoc.getX(), doorLoc.getY(), room8, room7);
		room8.getFloor()[doorLoc.getX()][doorLoc.getY()] = l1;
		e1 = new EmptyTile(empty.getWallNE(), empty.getWallNW(), empty.getWallSE(), empty.getWallSW(), new Item("FloorBlock", Position.FLOOR, room8.getFloor()[empty.getX()][empty.getY()], room8),
				empty.getOccupier(), empty.getX(), empty.getY());
		room8.getFloor()[empty.getX()][empty.getY()] = e1;
		//set up items in room7
		doorLoc = room7.getFloor()[2][1];
		empty = room7.getFloor()[3][1];
		t1 = new Trapdoor(doorLoc.getWallNE(), doorLoc.getWallNW(), doorLoc.getWallSE(), doorLoc.getWallSW(),
				new Item("Skulldoor", Position.FLOOR, room7.getFloor()[doorLoc.getX()][doorLoc.getY()], room7), doorLoc.getOccupier(), doorLoc.getX(), doorLoc.getY(),
				room7, room8, 10);
		room7.getFloor()[doorLoc.getX()][doorLoc.getY()] = t1;
		room7.addDoor(t1);
		e1 = new EmptyTile(empty.getWallNE(), empty.getWallNW(), empty.getWallSE(), empty.getWallSW(),
				new Item("FloorBlock", Position.FLOOR, room7.getFloor()[empty.getX()][empty.getY()], room7), empty.getOccupier(), empty.getX(), empty.getY()); 
		room7.getFloor()[empty.getX()][empty.getY()] = e1;
		ladderLoc = room8.getFloor()[doorLoc.getX()][doorLoc.getY()];
		empty = room8.getFloor()[empty.getX()][empty.getY()];
		l1 = new Ladder(ladderLoc.getWallNE(), ladderLoc.getWallNW(), ladderLoc.getWallSE(),
				ladderLoc.getWallSW(), ladderLoc.getFloor(), new Item("Ladder", Position.CENTER, room8.getFloor()[ladderLoc.getX()][ladderLoc.getY()], room8), doorLoc.getX(), doorLoc.getY(), room8, room7);
		room8.getFloor()[doorLoc.getX()][doorLoc.getY()] = l1;
		e1 = new EmptyTile(empty.getWallNE(), empty.getWallNW(), empty.getWallSE(), empty.getWallSW(), new Item("FloorBlock", Position.FLOOR, room8.getFloor()[empty.getX()][empty.getY()], room8),
				empty.getOccupier(), empty.getX(), empty.getY());
		room8.getFloor()[empty.getX()][empty.getY()] = e1;
		//set up items in room7
		itemLoc = room7.getFloor()[4][9];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room7, false, false, 0, null);
		room7.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		itemLoc = room7.getFloor()[4][8];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room7, false, false, 0, null);
		room7.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		itemLoc = room7.getFloor()[4][7];
		box = new MovableItem("Bookcase", Position.CENTER, itemLoc, room7);
		room7.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(box);
		itemLoc = room7.getFloor()[4][6];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room7, false, false, 0, null);
		room7.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		itemLoc = room7.getFloor()[4][5];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room7, false, false, 0, null);
		room7.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		itemLoc = room7.getFloor()[4][4];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room7, false, false, 0, null);
		room7.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		itemLoc = room7.getFloor()[5][4];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room7, false, false, 0, null);
		room7.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		itemLoc = room7.getFloor()[6][4];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room7, false, false, 0, null);
		room7.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		itemLoc = room7.getFloor()[7][4];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room7, false, false, 0, null);
		room7.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		itemLoc = room7.getFloor()[8][4];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room7, false, false, 0, null);
		room7.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		itemLoc = room7.getFloor()[9][4];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room7, false, false, 0, null);
		room7.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		itemLoc = room7.getFloor()[5][9];
		bookshelf = new StationaryItem("Bookcase", Position.CENTER, itemLoc, room7, true, false, 6, null);
		key = new CollectableItem("Skelkey", Position.CENTER, room7, null, 10, bookshelf);
		bookshelf.setHiddenItem(key);
		room7.getFloor()[itemLoc.getX()][itemLoc.getY()].setOccupier(bookshelf);
		rooms.add(room1);
		rooms.add(room2);
		rooms.add(room3);
		rooms.add(room4);
		rooms.add(room5);
		rooms.add(room6);
		rooms.add(room7);
		rooms.add(room8);

		// add player
		Location playerLoc = room1.getFloor()[3][7];
		Character player1 = characters.get(0);

		player1.setCurrentRoom(room1);
		player1.setCurrentLocation(playerLoc);

		playerLoc.setOccupier(player1);

		if (multi) {
			Location playerLoc2 = room1.getFloor()[4][7];
			Character player2 = characters.get(1);
			player2.setCurrentRoom(room1);
			player2.setCurrentLocation(playerLoc2);

			playerLoc2.setOccupier(player2);
		}

	}
	/**
	 * moves the character east
	 * @param character
	 */
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
				} 
				else {
					character.setCurrentRoom(((Trapdoor) newLoc).getExit());
					Location newRoomLoc = character.getCurrentRoom().getFloor()[newLoc.getX()+1][newLoc.getY()];
					character.setCurrentLocation(newRoomLoc);
					newRoomLoc.setOccupier(character);
					current.setOccupier(null);
					((Trapdoor) newLoc).setUnLockRoom(false);
					newLoc.getFloor().changeAnimation();
					Main.setRoom(character.getCurrentRoom());

				}
			}
			else if (newLoc instanceof Ladder) {
				character.setCurrentRoom(((Ladder) newLoc).getExit());
				Location newRoomLoc = character.getCurrentRoom().getFloor()[newLoc.getX()+1][newLoc.getY()];
				character.setCurrentLocation(newRoomLoc);
				newRoomLoc.setOccupier(character);
				current.setOccupier(null);
				Main.setRoom(character.getCurrentRoom());
			}
			else if (itemOnNewLoc == null || itemOnNewLoc instanceof CollectableItem) {
				character.moveSpace(newLoc);
				if (itemOnNewLoc != null) {
					character.pickUpItem((CollectableItem) newLoc.getOccupier());
				}
				current.setOccupier(null);
				newLoc.setOccupier(character);
			} 
			else if (itemOnNewLoc instanceof StationaryItem) {
				((StationaryItem) itemOnNewLoc).interact();
			}
			else if (itemOnNewLoc instanceof MovableItem) {
				((MovableItem) itemOnNewLoc).move(newLoc, current, character.getCurrentRoom(), character);
			}
		}
	}
	/**
	 * moves character west
	 * @param character
	 */
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
				} 
				else {
					character.setCurrentRoom(((Trapdoor) newLoc).getExit());
					Location newRoomLoc = character.getCurrentRoom().getFloor()[newLoc.getX()+1][newLoc.getY()];
					character.setCurrentLocation(newRoomLoc);
					newRoomLoc.setOccupier(character);
					current.setOccupier(null);
					((Trapdoor) newLoc).setUnLockRoom(false);
					newLoc.getFloor().changeAnimation();
					Main.setRoom(character.getCurrentRoom());
				}
			}
			else if (newLoc instanceof Ladder) {
				character.setCurrentRoom(((Ladder) newLoc).getExit());
				Location newRoomLoc = character.getCurrentRoom().getFloor()[newLoc.getX()+1][newLoc.getY()];
				character.setCurrentLocation(newRoomLoc);
				newRoomLoc.setOccupier(character);
				current.setOccupier(null);
				Main.setRoom(character.getCurrentRoom());
			}
			else if (itemOnNewLoc == null || itemOnNewLoc instanceof CollectableItem) {
				character.moveSpace(newLoc);
				if (itemOnNewLoc != null) {
					character.pickUpItem((CollectableItem) newLoc.getOccupier());
				}
				current.setOccupier(null);
				newLoc.setOccupier(character);
			} 
			else if (itemOnNewLoc instanceof StationaryItem) {
				((StationaryItem) itemOnNewLoc).interact();
			} 
			else if (itemOnNewLoc instanceof MovableItem) {
				((MovableItem) itemOnNewLoc).move(newLoc, current, character.getCurrentRoom(), character);
			}
		}
	}
	/**
	 * moves character South
	 * @param character
	 */
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
				} else {
					character.setCurrentRoom(((Trapdoor) newLoc).getExit());
					Location newRoomLoc = character.getCurrentRoom().getFloor()[newLoc.getX() + 1][newLoc.getY()];
					character.setCurrentLocation(newRoomLoc);
					newRoomLoc.setOccupier(character);
					current.setOccupier(null);
					((Trapdoor) newLoc).setUnLockRoom(false);
					newLoc.getFloor().changeAnimation();
					Main.setRoom(character.getCurrentRoom());
				}
			}
			else if (newLoc instanceof Ladder) {
				character.setCurrentRoom(((Ladder) newLoc).getExit());
				Location newRoomLoc = character.getCurrentRoom().getFloor()[newLoc.getX()+1][newLoc.getY()];
				character.setCurrentLocation(newRoomLoc);
				newRoomLoc.setOccupier(character);
				current.setOccupier(null);
				Main.setRoom(character.getCurrentRoom());
			}
			else if (itemOnNewLoc == null || itemOnNewLoc instanceof CollectableItem) {
				character.moveSpace(newLoc);
				if (itemOnNewLoc != null) {
					character.pickUpItem((CollectableItem) newLoc.getOccupier());
				}
				current.setOccupier(null);
				newLoc.setOccupier(character);
			} 
			else if (itemOnNewLoc instanceof StationaryItem) {
				((StationaryItem) itemOnNewLoc).interact();
			} 
			else if (itemOnNewLoc instanceof MovableItem) {
				((MovableItem) itemOnNewLoc).move(newLoc, current, character.getCurrentRoom(), character);
			}
		}
	}
	/**
	 * moves character north
	 * @param character
	 */
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
				} 
				else {
					character.setCurrentRoom(((Trapdoor) newLoc).getExit());
					Location newRoomLoc = character.getCurrentRoom().getFloor()[newLoc.getX()+1][newLoc.getY()];
					character.setCurrentLocation(newRoomLoc);
					newRoomLoc.setOccupier(character);
					current.setOccupier(null);
					((Trapdoor) newLoc).setUnLockRoom(false);
					newLoc.getFloor().changeAnimation();
					Main.setRoom(character.getCurrentRoom());
				}
			}
			else if (newLoc instanceof Ladder) {
				character.setCurrentRoom(((Ladder) newLoc).getExit());
				Location newRoomLoc = character.getCurrentRoom().getFloor()[newLoc.getX()+1][newLoc.getY()];
				character.setCurrentLocation(newRoomLoc);
				newRoomLoc.setOccupier(character);
				current.setOccupier(null);
				Main.setRoom(character.getCurrentRoom());
			}
			else if (itemOnNewLoc == null || itemOnNewLoc instanceof CollectableItem) {
				character.moveSpace(newLoc);
				if (itemOnNewLoc != null) {
					character.pickUpItem((CollectableItem) newLoc.getOccupier());
				}
				current.setOccupier(null);
				newLoc.setOccupier(character);
			} 
			else if (itemOnNewLoc instanceof StationaryItem) {
				((StationaryItem) itemOnNewLoc).interact();
			} 
			else if (itemOnNewLoc instanceof MovableItem) {
				((MovableItem) itemOnNewLoc).move(newLoc, current, character.getCurrentRoom(), character);
			}
		}
	}
	/**
	 * allows character to use item
	 * @param selectedFromBag
	 * @param selectedOnBoard
	 * @param player
	 */
	public void useItem(CollectableItem selectedFromBag, DrawableTile selectedOnBoard, Character player) {
		int key;
		for (int i = 0; i < player.getCurrentRoom().getDoors().size(); i++) {
			if (selectedOnBoard instanceof Trapdoor) {
				key = ((Trapdoor) selectedOnBoard).getKeyID();
				if (selectedFromBag.getiD() == key) {
					((Trapdoor) selectedOnBoard).setUnLockRoom(false);
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
	 * adds character to the game
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
