package Game;

import render.Drawable;
import render.Drawable.Position;

public class MovableItem extends Item {

	public MovableItem(String imgLoc, Position pos){
		super(imgLoc, pos);
	}
	
	public void move(Location currentLoc, Location playerLoc, Room currentRoom, Character ch){
		int newX = currentLoc.getX() + (currentLoc.getX() - playerLoc.getX());
		int newY = currentLoc.getY() + (currentLoc.getY() - playerLoc.getY());
		if(newX <= currentRoom.getWidth() && newY <= currentRoom.getWidth()){
			Location newLoc = currentRoom.getFloor()[newX][newY];
			Drawable itemOnLoc = currentRoom.checkLocation(newLoc);
			if(itemOnLoc == null){
				currentRoom.getFloor()[newX][newY].setOccupier(this); 
				currentRoom.getFloor()[currentLoc.getX()][currentLoc.getY()].setOccupier(ch);
				currentRoom.getFloor()[playerLoc.getX()][playerLoc.getY()].setOccupier(null);	
			}
		}
		else {
			return;
		}
	}
}
