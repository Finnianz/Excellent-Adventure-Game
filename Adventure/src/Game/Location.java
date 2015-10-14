package Game;
import render.*;
import render.Drawable.Position;

/**
 * 
 * @author Michelle O'Neill ID:301025406
 *
 */

public class Location extends DrawableTile{
	
	private int x;
	private int y;
	
	public Location(Drawable wallNE, Drawable wallNW, Drawable wallSE,
			Drawable wallSW, Drawable floor, Drawable occupier, int x, int y){
		super(wallNE, wallNW, wallSE, wallSW, floor, occupier);
		this.x = x;
		this.y = y;
	}


	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

}
