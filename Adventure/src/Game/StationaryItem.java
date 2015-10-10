package Game;

import javax.swing.ImageIcon;

import render.Drawable.Position;

public class StationaryItem extends Item {
	
	Boolean interactable;
	CollectableItem hiddenItem;
	
	//if interactable is false, hiddenItem should be null
	public StationaryItem(String imgLoc, Position pos, Boolean interactable, CollectableItem item){
		super(imgLoc, pos);	
		this.interactable = interactable;
		hiddenItem = item;
	}

	
	public void interact(){
		if(interactable){
			//TODO
			Location dropLoc = this.getRoomID().getFloor()[this.getLoc().getX() + 1][this.getLoc().getY()];
			if(dropLoc.getOccupier() == null){
				dropLoc.setOccupier(hiddenItem);
			}
			else{
				dropLoc = this.getRoomID().getFloor()[this.getLoc().getX()][this.getLoc().getY() + 1];
				dropLoc.setOccupier(hiddenItem);		
			}
		}
		
	}
}
