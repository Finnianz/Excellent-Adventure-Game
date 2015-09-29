package Game;

import javax.swing.ImageIcon;

import render.Drawable.Position;

public class NonMovableItem extends Item {
	
	Boolean interactable;
	
	public NonMovableItem(String imgLoc, Position pos){
		super(imgLoc, pos);	}

	
	public void interact(){
		if(interactable){
			//TODO
			
		}
		
	}
}
