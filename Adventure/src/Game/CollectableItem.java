package Game;
/**
 * 
 * @author Michelle O'Neill ID:301025406
 *
 */

public class CollectableItem extends Item {

	private int iD;
	private StationaryItem hidingPlace;

	/**
	 * creates an items that can be collected
	 * @param imgLoc
	 * @param pos
	 * @param room
	 * @param loc
	 * @param iD
	 * @param hidingPlace
	 */
	public CollectableItem(String imgLoc, Position pos, Room room,Location loc, int iD, StationaryItem hidingPlace){
		super(imgLoc, pos, loc, room);
		this.iD = iD;
		this.hidingPlace = hidingPlace;
	}
	/**
	 * allows the character to use an item the selected in the bag
	 * @param door
	 * @param ch
	 */
	public void use(Trapdoor door, Character ch){
		if(door.getKeyID() == iD){
			door.setUnLockRoom(true);
			door.getFloor().changeAnimation();
			respawn();
			ch.getItems().remove(this);
		}
	}

	/**
	 * @return the iD
	 */
	public int getiD() {
		return iD;
	}
	/**
	 * respawns the item to its starting location
	 */
	public void respawn(){
		hidingPlace.setHiddenItem(this);
		hidingPlace.setItemFound(false);		
	}


}
