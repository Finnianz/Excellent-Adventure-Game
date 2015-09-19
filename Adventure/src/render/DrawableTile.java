package render;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

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
		Drawable[] drawOrder = new Drawable[6];
		drawOrder[0] = floor;
		if(wallNE!=null){
			
			switch (direction) {
			case NORTH:
				drawOrder[2] = wallNE;
				break;
			case EAST:
				drawOrder[1] = wallNE;
				break;
			case SOUTH:
				drawOrder[4] = wallNE;
				break;
			case WEST:
				drawOrder[5] = wallNE;
				break;
			}
		}
		if(wallNW!=null){
			switch (direction) {
			case NORTH:
				drawOrder[1] = wallNW;
				break;
			case EAST:
				drawOrder[4] = wallNW;
				break;
			case SOUTH:
				drawOrder[5] = wallNW;
				break;
			case WEST:
				drawOrder[2] = wallNW;
				break;

			}
		}
		
		if(wallSE!=null){
			switch (direction) {
			case NORTH:
				drawOrder[5] = wallSE;
				break;
			case EAST:
				drawOrder[2] = wallSE;
				break;
			case SOUTH:
				drawOrder[1] = wallSE;
				break;
			case WEST:
				drawOrder[4] = wallSE;
				break;
			}

		}
		if(wallSW!=null){
			switch (direction) {
			case NORTH:
				drawOrder[4] = wallSW;
				break;
			case EAST:
				drawOrder[5] = wallSW;
				break;
			case SOUTH:
				drawOrder[2] = wallSW;
				break;
			case WEST:
				drawOrder[1] = wallSW;
				break;

			}
		}
		if(occupier!=null){
			drawOrder[3] = occupier;
		}
		for(int i = 0; i<drawOrder.length;i++){
			if(drawOrder[i]!=null){
				drawOrder[i].draw(direction, xPos, yPos, g, tileHeight, tileHeight);
			}
		}

	}

}
