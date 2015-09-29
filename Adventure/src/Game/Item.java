package Game;

import javax.swing.ImageIcon;

import render.*;

public class Item extends Drawable{
	
	public String itemType;
	public Location loc;
	public Room roomID;
	public ImageIcon image;
	
	public Item(String imgLoc, Position pos){
		super(imgLoc, pos);
	}
}
