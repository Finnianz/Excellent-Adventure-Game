package Game;

import javax.swing.ImageIcon;

public class NonMovableItem extends Item {
	
	Boolean interactable;
	
	public NonMovableItem(String type, Location loc, Room room, ImageIcon image){
		super(type, loc, room, image);
	}

	
	public void interact(){
		if(interactable){
			//TODO
			
		}
		
	}
}
