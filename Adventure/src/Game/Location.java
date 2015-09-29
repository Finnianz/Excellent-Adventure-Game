package Game;
import render.*;
import render.Drawable.Position;

public class Location extends DrawableTile{
	
	private int x;
	private int y;
	
	
	public Location(Drawable wallNE, Drawable wallNW, Drawable wallSE,
			Drawable wallSW, Drawable floor, Drawable occupier, int x, int y){
		super(wallNE, wallNW, wallSE, wallSW, floor, occupier);
		this.x = x;
		this.y = y;
	}

}
