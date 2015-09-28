package Game;

import javax.swing.ImageIcon;

public class Item {
	
	public String itemType;
	public Location loc;
	public int roomID;
	public ImageIcon image;
	
	public Item(String type, Location loc, int room, ImageIcon image){
		this.itemType = type;
		this.loc = loc;
		this.roomID = room;
		this.image = image;
	}
}
