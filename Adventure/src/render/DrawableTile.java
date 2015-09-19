package render;

import java.awt.Graphics;

import render.RenderCanvas.Compass;

public class DrawableTile {
	private Drawable wallNE;
	private Drawable wallNW;
	private Drawable wallSE;
	private Drawable wallSW;
	private Drawable floor;
	private Drawable occupier;

	public DrawableTile(Drawable wallNE, Drawable wallNW, Drawable wallSE, Drawable wallSW, Drawable floor, Drawable occupier){
		this.wallNE = wallNE;
		this.wallNW = wallNW;
		this.wallSE = wallSE;
		this.wallSW = wallSW;
		this.floor = floor;
		this.occupier = occupier;
	}

	public void draw(Compass direction, int xPos, int yPos, Graphics g,
			int tileWidth, int tileHeight) {
		floor.draw(direction, xPos, yPos, g, tileWidth, tileHeight);
		if(wallNE!=null){
			wallNE.draw(direction, xPos, yPos, g, tileHeight, tileHeight);
		}
		if(wallNW!=null){
			wallNW.draw(direction, xPos, yPos, g, tileHeight, tileHeight);
		}
		if(occupier!=null){
			occupier.draw(direction, xPos, yPos, g, tileHeight, tileHeight);
		}
		if(wallSW!=null){
			wallSW.draw(direction, xPos, yPos, g, tileHeight, tileHeight);
		}
		if(wallSE!=null){
			wallSE.draw(direction, xPos, yPos, g, tileHeight, tileHeight);
		}
		
		

	}

}
