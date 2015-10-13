package Game;

import javax.swing.ImageIcon;

import render.Drawable.Position;

public class StationaryItem extends Item {

	Boolean interactable;
	CollectableItem hiddenItem;
	Boolean itemFound = false;
	Boolean unlockable;
	int keyID;

	//if interactable and unlockable are false, hiddenItem should be null
	public StationaryItem(String imgLoc, Position pos, Location loc, Room room, Boolean interactable, Boolean unlockable, int keyID, CollectableItem item){
		super(imgLoc, pos, loc, room);	
		this.interactable = interactable;
		this.unlockable = unlockable;
		this.keyID = keyID;
		hiddenItem = item;
	}


	public void interact(){
		if(interactable){
			//TODO
			if(!itemFound){
				Location dropLoc;
				if(this.getLoc().getX()+1<this.getRoomID().getFloor().length){
					dropLoc = this.getRoomID().getFloor()[this.getLoc().getX() + 1][this.getLoc().getY()];
				}
				else{
					dropLoc = this.getRoomID().getFloor()[this.getLoc().getX()-1][this.getLoc().getY()];
				}
				if(dropLoc.getOccupier() == null){
					dropLoc.setOccupier(hiddenItem);
					itemFound = true;
				}
				else{
					if(this.getLoc().getY() + 1 < this.getRoomID().getFloor().length){
						dropLoc = this.getRoomID().getFloor()[this.getLoc().getX()][this.getLoc().getY() + 1];
					}
					else{
						dropLoc = this.getRoomID().getFloor()[this.getLoc().getX()][this.getLoc().getY()-1];
					}					
					dropLoc.setOccupier(hiddenItem);	
					itemFound = true;
				}
			}
		}

	}
}
