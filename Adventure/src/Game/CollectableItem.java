package Game;

public class CollectableItem extends Item {

	private int iD;
	private StationaryItem hidingPlace;


	public CollectableItem(String imgLoc, Position pos, Room room,Location loc, int iD, StationaryItem hidingPlace){
		super(imgLoc, pos, loc, room);
		this.iD = iD;
		this.hidingPlace = hidingPlace;
	}

	public void use(Trapdoor door){
		if(door.getKeyID() == iD){
			door.setUnLockRoom(true);
			door.getFloor().changeAnimation();
			respawn();
		}
	}

	/**
	 * @return the iD
	 */
	public int getiD() {
		return iD;
	}

	public void respawn(){
		hidingPlace.setHiddenItem(this);
		hidingPlace.setItemFound(false);		
	}


}
