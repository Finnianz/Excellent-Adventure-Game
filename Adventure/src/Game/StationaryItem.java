package Game;

import javax.swing.ImageIcon;

import render.Drawable.Position;

/**
 * 
 * @author Michelle O'Neill ID:301025406
 *
 */

public class StationaryItem extends Item {

	Boolean interactable;
	Item hiddenItem;
	Boolean itemFound = false;
	Boolean unlockable;
	int keyID;

	/**
	 * if interactable and unlockable are false, hiddenItem should be null
	 * @param imgLoc
	 * @param pos
	 * @param loc
	 * @param room
	 * @param interactable
	 * @param unlockable
	 * @param keyID
	 * @param item
	 */
	public StationaryItem(String imgLoc, Position pos, Location loc, Room room, Boolean interactable, Boolean unlockable, int keyID, CollectableItem item){
		super(imgLoc, pos, loc, room);	
		this.interactable = interactable;
		this.unlockable = unlockable;
		this.keyID = keyID;
		hiddenItem = item;
	}

	/**
	 * lets the player interact with the item if interactable
	 */
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
					if(this.getLoc().getY() + 1 < this.getRoomID().getFloor().length && this.getRoomID().getFloor()[this.getLoc().getX()][this.getLoc().getY()+1].getOccupier()==null ){
						dropLoc = this.getRoomID().getFloor()[this.getLoc().getX()][this.getLoc().getY() + 1];
					}
					else{
						if(this.getLoc().getY()-1 >=0){
							dropLoc = this.getRoomID().getFloor()[this.getLoc().getX()][this.getLoc().getY()-1];
						}					
					}
					dropLoc.setOccupier(hiddenItem);	
					itemFound = true;
				}
			}
		}

	}

	/**
	 * @return the hiddenItem
	 */
	public Item getHiddenItem() {
		return hiddenItem;
	}


	/**
	 * @param hiddenItem the hiddenItem to set
	 */
	public void setHiddenItem(Item hiddenItem) {
		this.hiddenItem = hiddenItem;
	}


	/**
	 * @return the itemFound
	 */
	public Boolean getItemFound() {
		return itemFound;
	}


	/**
	 * @param itemFound the itemFound to set
	 */
	public void setItemFound(Boolean itemFound) {
		this.itemFound = itemFound;
	}


}
