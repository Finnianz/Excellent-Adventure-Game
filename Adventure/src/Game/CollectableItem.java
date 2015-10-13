package Game;

import javax.swing.ImageIcon;

public class CollectableItem extends Item {
	
	private int iD;
	

	public CollectableItem(String imgLoc, Position pos, Room room,Location loc, int iD){
		super(imgLoc, pos, loc, room);
		this.iD = iD;
	}
		
	public void use(){
		
	}

	/**
	 * @return the iD
	 */
	public int getiD() {
		return iD;
	}


}
