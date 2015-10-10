package Game;

import render.Drawable.Position;

public class EmptyItem extends Item{

	/**
	 * Used to stop objects from being put on certain tiles.  Characters can walk on these tiles, but you cannot push a
	 * movable item onto them.
	 * @param imgLoc
	 * @param pos
	 */
	public EmptyItem(String imgLoc, Position pos){
		super(imgLoc, pos);
	}
}
