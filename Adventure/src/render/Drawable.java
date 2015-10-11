package render;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import render.RenderCanvas.Compass;

import javax.imageio.ImageIO;

/**
 * Represents a drawable object
 * 
 * @author Paige Halliwell ID:300316022
 *
 */
public abstract class Drawable {
	
	private final double SCALE = .6;
	private boolean selected = false;
	private BufferedImage selectedImg;
	private final String SELECT_IMG_STRING = "resource/Star.png";
	private Compass direction = Compass.NORTH;
	private int xOnScreen =0;
	private int yOnScreen = 0;
	private AnimationSequence images; // The images
	public final Position POSITION;// where this object sits on the tile, used
									// to calculate where to draw.

	// Wall image constants. Walls don't follow the N,E,S,W pattern for the
	// image array, instead the image correlate to the wall being at the top or
	// bottom of the tile, and on the left or right edge.
	private final Compass TOP_LEFT = Compass.NORTH;
	private final Compass TOP_RIGHT = Compass.EAST;
	private final Compass BOTTOM_RIGHT = Compass.SOUTH;
	private final Compass BOTTOM_LEFT = Compass.WEST;
	


	/**
	 * Floor - the floor tiles Center - any object to be drawn in the center of
	 * the tile, keys, players, tools etc fall under this category Square -
	 * Objects that are designed to take up the entire tile, chairs, boxes,
	 * tables etc are Square objects Walls - each one of these represent what
	 * edge of the room the wall sits on. Walls include doors and bookshelves.
	 */
	public enum Position {
		FLOOR, CENTER, SQUARE, WALL_NE, WALL_SE, WALL_SW, WALL_NW
	}

	/**
	 * Creates a drawable object. Requires four images named example0.png,
	 * example1.png, example2.png, example3.png, where 'example' is the string
	 * passed. These images should correlate to the object being viewed to the
	 * North, East, South and West, with the exception of walls.
	 * 
	 * @param imgLoc
	 *            location of base image, without the numerical identifier
	 * @param pos
	 *            Position the object should be drawn at. Refer to the Position
	 *            enum for more details.
	 */
	public Drawable(String imgLoc, Position pos) {
		
		images = new AnimationSequence(imgLoc);
		POSITION = pos;
		try {
			selectedImg = ImageIO.read(new File(SELECT_IMG_STRING));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Draws the object. The coordinate parameters passed in are the position to
	 * draw a floor tile from. All other types of drawables are drawn in
	 * relation to their tiles coordinates.
	 * 
	 * @param direction
	 *            the current view
	 * @param x
	 * @param y
	 * @param g
	 *            graphics object to draw to
	 * @param scaledTileW
	 *            The width of the tiles
	 * @param scaledTileH
	 *            The height of the tiles, note that this is not the same as the
	 *            image height of the tiles.
	 */
	public void draw(Compass direction, int x, int y, Graphics g,
			int tileWidth, int tileHeight) {
		xOnScreen = x;
		yOnScreen = y;
		BufferedImage sprite = images.getImage(direction);
		if(sprite!=null);
		int height = (int) (sprite.getHeight()*SCALE);
		int width = (int) (sprite.getWidth()*SCALE);
		switch (POSITION) {
		// No change to the coords for floor
		case FLOOR:
			break;
		// Adjust coordinate for center drawables
		case CENTER:
			xOnScreen += (tileWidth / 2) - (width / 2);
			yOnScreen += (tileHeight / 2) - height;
			break;
		// adjust coords for square objects
		case SQUARE:
			yOnScreen -= (height - tileHeight);
			break;
		// Wall cases below. These are a bit more complicated than the above
		// ones. Which wall image to draw and where it is in relation to it's
		// tile depends on both the wall type and the viewing direction.
		case WALL_NW:
			switch (direction) {
			case NORTH:
				yOnScreen += (tileHeight / 2) - height;
				xOnScreen -= (width - tileWidth/2);
				sprite = images.getImage(TOP_LEFT);
				break;
			case EAST:
				sprite = images.getImage(BOTTOM_LEFT);
				yOnScreen += tileHeight - (sprite.getHeight(null)*SCALE);
				break;
			case SOUTH:
				sprite = images.getImage(BOTTOM_RIGHT);
				yOnScreen += (tileHeight) - (sprite.getHeight(null)*SCALE);
				xOnScreen += (tileWidth - (sprite.getWidth(null)*SCALE));
				break;
			case WEST:
				sprite = images.getImage(TOP_RIGHT);
				yOnScreen += (tileHeight / 2) - height;
				xOnScreen += (tileWidth/2);
				break;
			}

			break;
		case WALL_NE:

			switch (direction) {
			case NORTH:
				sprite = images.getImage(TOP_RIGHT);
				yOnScreen += (tileHeight / 2) - height;
				xOnScreen += (tileWidth/2);
				break;
			case EAST:
				yOnScreen += (tileHeight / 2) - height;
				xOnScreen -= (width - tileWidth/2);
				sprite = images.getImage(TOP_LEFT);
				break;
			case SOUTH:
				sprite = images.getImage(BOTTOM_LEFT);
				yOnScreen += tileHeight - (sprite.getHeight(null)*SCALE);
				break;
			case WEST:
				sprite = images.getImage(BOTTOM_RIGHT);
				yOnScreen += (tileHeight) - (sprite.getHeight(null)*SCALE);
				xOnScreen += (tileWidth - (sprite.getWidth(null)*SCALE));
				break;
			}
			break;

		case WALL_SW:
			switch (direction) {
			case NORTH:
				sprite = images.getImage(BOTTOM_LEFT);
				yOnScreen += tileHeight - (sprite.getHeight(null)*SCALE);
				break;
			case EAST:
				sprite = images.getImage(BOTTOM_RIGHT);
				yOnScreen += (tileHeight) - (sprite.getHeight(null)*SCALE);
				xOnScreen += (tileWidth - (sprite.getWidth(null)*SCALE));
				break;
			case SOUTH:
				sprite = images.getImage(TOP_RIGHT);
				yOnScreen += (tileHeight / 2) - height;
				xOnScreen += (tileWidth/2);
				break;
			case WEST:
				yOnScreen += (tileHeight / 2) - height;
				xOnScreen -= (width - tileWidth/2);
				sprite = images.getImage(TOP_LEFT);
				break;

			}
			break;
		case WALL_SE:
			switch (direction) {
			case NORTH:
				sprite = images.getImage(BOTTOM_RIGHT);
				yOnScreen += (tileHeight) - (sprite.getHeight(null)*SCALE);
				xOnScreen += (tileWidth - (sprite.getWidth(null)*SCALE));
				break;
			case EAST:
				sprite = images.getImage(TOP_RIGHT);
				yOnScreen += (tileHeight / 2) - height;
				xOnScreen += (tileWidth/2);
				break;
			case SOUTH:
				sprite = images.getImage(TOP_LEFT);
				yOnScreen += (tileHeight / 2) - height;
				xOnScreen -= (width - tileWidth/2);
				break;
			case WEST:
				sprite = images.getImage(BOTTOM_LEFT);
				yOnScreen += tileHeight - (sprite.getHeight(null)*SCALE);
				break;
			}
			break;
		}
		// Finally draw the image
		int imgHeight = (int) (sprite.getHeight()*SCALE);
		int imgWidth = (int) (sprite.getWidth()*SCALE);
		g.drawImage(sprite, xOnScreen, yOnScreen, imgWidth, imgHeight,  null);
		if(selected){
			int selectWidth = (int) (selectedImg.getWidth()*SCALE);
			int selectHeight = (int) (selectedImg.getHeight()*SCALE);
			int selectX = xOnScreen;
			int selectY = yOnScreen - selectHeight;
			g.drawImage(selectedImg, selectX, selectY, selectWidth, selectHeight, null);
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((POSITION == null) ? 0 : POSITION.hashCode());
		result = prime * result + (selected ? 1231 : 1237);
		result = prime * result + xOnScreen;
		result = prime * result + yOnScreen;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Drawable other = (Drawable) obj;
		if (POSITION != other.POSITION)
			return false;
		if (selected != other.selected)
			return false;
		if (xOnScreen != other.xOnScreen)
			return false;
		if (yOnScreen != other.yOnScreen)
			return false;
		return true;
	}

	public void animationTick(){
		images.animationTick();
	}
	
	public void changeAnimation(){
		images.changeState();
	}
	
	public boolean isContained(int x, int y){
		return (x>xOnScreen && x<(xOnScreen+images.getImage(direction).getWidth()) &&y>yOnScreen && y< (yOnScreen + images.getImage(direction).getHeight()));
	}

	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public int getYOnScreen() {
		return yOnScreen;
	}
}
