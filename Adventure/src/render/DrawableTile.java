package render;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import render.RenderCanvas.Compass;

/**
 * A DrawableTile can hold many Drawable objects. It must have a floor. It may
 * have 0-4 walls and may or may not have an occupier. A tile may only have one
 * occupier at a time.
 * 
 * @author Paige Halliwell ID:300316022
 *
 */
public class DrawableTile {
	private Drawable wallNE;
	private Drawable wallNW;
	private Drawable wallSE;
	private Drawable wallSW;
	private Drawable floor;
	private Drawable occupier;

	private Drawable[] drawOrder;

	/**
	 * Constructor takes Drawable objects. Wall and occupier objects are
	 * optional, but it must have a floor.
	 * 
	 * @param wallNE
	 * @param wallNW
	 * @param wallSE
	 * @param wallSW
	 * @param floor
	 * @param occupier
	 */
	public DrawableTile(Drawable wallNE, Drawable wallNW, Drawable wallSE,
			Drawable wallSW, Drawable floor, Drawable occupier) {
		if (floor == null) {
			throw new IllegalArgumentException();
		}
		this.wallNE = wallNE;
		this.wallNW = wallNW;
		this.wallSE = wallSE;
		this.wallSW = wallSW;
		this.floor = floor;
		this.occupier = occupier;
		drawOrder = new Drawable[6];
	}

	/**
	 * Draw's this tile to the given graphics object.
	 * 
	 * @param direction
	 *            current viewing direction
	 * @param xPos
	 *            the x point for the floor tile to be drawn. Other Drawable
	 *            types are drawn in relation to this.
	 * @param yPos
	 *            the y point for the floor tile to be drawn. Other Drawable
	 *            types are drawn in relation to this.
	 * @param g
	 *            graphics object to draw to
	 * @param tileWidth
	 * @param tileHeight
	 */
	public void draw(Compass direction, int xPos, int yPos, Graphics g,
			int tileWidth, int tileHeight) {
		// An array to keep track of the draw order. Some indexs may remain null
		drawOrder = new Drawable[6];
		drawOrder[2] = floor;// Floor is always the third item in array

		// If wall isn't null, check the viewing direction to decide where in
		// the draw order wall should be. Same pattern followed for all walls
		if (wallNE != null) {

			switch (direction) {
			case NORTH:
				drawOrder[1] = wallNE;
				break;
			case EAST:
				drawOrder[0] = wallNE;
				break;
			case SOUTH:
				drawOrder[4] = wallNE;
				break;
			case WEST:
				drawOrder[5] = wallNE;
				break;
			}
		}
		if (wallNW != null) {
			switch (direction) {
			case NORTH:
				drawOrder[0] = wallNW;
				break;
			case EAST:
				drawOrder[4] = wallNW;
				break;
			case SOUTH:
				drawOrder[5] = wallNW;
				break;
			case WEST:
				drawOrder[1] = wallNW;
				break;

			}
		}

		if (wallSE != null) {
			switch (direction) {
			case NORTH:
				drawOrder[5] = wallSE;
				break;
			case EAST:
				drawOrder[1] = wallSE;
				break;
			case SOUTH:
				drawOrder[0] = wallSE;
				break;
			case WEST:
				drawOrder[4] = wallSE;
				break;
			}

		}
		if (wallSW != null) {
			switch (direction) {
			case NORTH:
				drawOrder[4] = wallSW;
				break;
			case EAST:
				drawOrder[5] = wallSW;
				break;
			case SOUTH:
				drawOrder[1] = wallSW;
				break;
			case WEST:
				drawOrder[0] = wallSW;
				break;

			}
		}
		if (occupier != null) {
			// Occupier doesn't change order in array
			drawOrder[3] = occupier;
		}
		// Finally iterate through array and call the draw method on all
		// objects. Entire array may not be filled, need to check if index is
		// null first
		for (int i = 0; i < drawOrder.length; i++) {

			if (drawOrder[i] != null) {
				drawOrder[i].draw(direction, xPos, yPos, g, tileWidth,
						tileHeight);
			}
		}

	}

	public void animationTick() {
		if (wallNE != null) {
			wallNE.animationTick();
		}
		if (wallNW != null) {
			wallNW.animationTick();
		}
		if (wallSE != null) {
			wallSE.animationTick();
		}
		if (wallSW != null) {
			wallSW.animationTick();
		}
		if (occupier != null) {
			occupier.animationTick();
		}
		floor.animationTick();
	}

	/**
	 * @return the wallNE
	 */
	public Drawable getWallNE() {
		return wallNE;
	}

	/**
	 * @param wallNE
	 *            the wallNE to set
	 */
	public void setWallNE(Drawable wallNE) {
		this.wallNE = wallNE;
	}

	/**
	 * @return the wallNW
	 */
	public Drawable getWallNW() {
		return wallNW;
	}

	/**
	 * @param wallNW
	 *            the wallNW to set
	 */
	public void setWallNW(Drawable wallNW) {
		this.wallNW = wallNW;
	}

	/**
	 * @return the wallSE
	 */
	public Drawable getWallSE() {
		return wallSE;
	}

	/**
	 * @param wallSE
	 *            the wallSE to set
	 */
	public void setWallSE(Drawable wallSE) {
		this.wallSE = wallSE;
	}

	/**
	 * @return the wallSW
	 */
	public Drawable getWallSW() {
		return wallSW;
	}

	/**
	 * @param wallSW
	 *            the wallSW to set
	 */
	public void setWallSW(Drawable wallSW) {
		this.wallSW = wallSW;
	}

	/**
	 * @return the floor
	 */
	public Drawable getFloor() {
		return floor;
	}

	/**
	 * @param floor
	 *            the floor to set
	 */
	public void setFloor(Drawable floor) {
		this.floor = floor;
	}

	/**
	 * @return the occupier
	 */
	public Drawable getOccupier() {
		return occupier;
	}

	/**
	 * @param occupier
	 *            the occupier to set
	 */
	public void setOccupier(Drawable occupier) {
		this.occupier = occupier;
	}

	/**
	 * checks if the floor tile of this drawableTile contains the point clickedOn
	 * @param x
	 * @param y
	 * @return
	 */
	public DrawableTile isClickedOn(int x, int y) {
		if (floor.isContained(x, y)) {
			return this;
		}
		return null;
	}
}
