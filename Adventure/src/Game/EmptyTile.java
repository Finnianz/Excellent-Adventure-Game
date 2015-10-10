package Game;

import render.Drawable;
import render.Drawable.Position;

public class EmptyTile extends Location{

	/**
	 * Used to stop objects from being put on certain tiles.  Characters can walk on these tiles, but you cannot push a
	 * movable item onto them.
	 * @param imgLoc
	 * @param pos
	 */
	public EmptyTile(Drawable wallNE, Drawable wallNW, Drawable wallSE,
			Drawable wallSW, Drawable floor, Drawable occupier, int x, int y, Room room1, Room room2){
		super(wallNE, wallNW, wallSE, wallSW, floor, occupier, x,y);
	}
}
