package Game;

import render.Drawable;
import render.Drawable.Position;

/**
 * 
 * @author Michelle O'Neill ID:301025406
 *
 */

public class MovableItem extends Item {

	public MovableItem(String imgLoc, Position pos, Location loc, Room room){
		super(imgLoc, pos, loc, room);
	}
	/**
	 * moves the player
	 * @param currentLoc
	 * @param playerLoc
	 * @param currentRoom
	 * @param ch
	 */
	public void move(Location currentLoc, Location playerLoc, Room currentRoom, Character ch){
		int newX = currentLoc.getX() + (currentLoc.getX() - playerLoc.getX());
		int newY = currentLoc.getY() + (currentLoc.getY() - playerLoc.getY());
		if(newX < currentRoom.getWidth() && newY < currentRoom.getWidth()&& newX>=0 && newY>=0){
			Location newLoc = currentRoom.getFloor()[newX][newY];
			Drawable itemOnLoc = currentRoom.checkLocation(newLoc);
			if(itemOnLoc == null && !(newLoc instanceof Trapdoor) && !(newLoc instanceof Ladder) && !(newLoc instanceof EmptyTile)){
				currentRoom.getFloor()[newX][newY].setOccupier(this); 
				ch.moveSpace(currentLoc);
				currentLoc.setOccupier(ch);
				currentRoom.getFloor()[playerLoc.getX()][playerLoc.getY()].setOccupier(null);	
			}
		}
		else {
			return;
		}
	}
}
