package render;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import render.RenderCanvas.Compass;

/**
 * 
 * @author Paige Halliwell ID:300316022
 *
 */
public class CustomisableCharacter extends Drawable {

	private AnimationSequence hatImages;
	private Direction dir = Direction.DOWN;
	private final int HAT_DISPLACEMENT = 30;

	/**
	 * 
	 * @param characterImgLoc
	 * @param hatString
	 */
	public CustomisableCharacter(String characterImgLoc, String hatString) {
		super(characterImgLoc, Position.CENTER);
		hatImages = new AnimationSequence(hatString);
	}

	public enum Direction {
		UP, LEFT, RIGHT, DOWN
	}

	@Override
	public void draw(Compass direction, int x, int y, Graphics g,
			int tileWidth, int tileHeight) {
		Compass realDirection = getRelativeDirectionFromCompass(direction);
		super.draw(realDirection, x, y, g, tileWidth, tileHeight);
		BufferedImage hatImage = hatImages.getImage(realDirection);
		int xPos = (int) (x + (tileWidth / 2) - (hatImage.getWidth()/2)*super.SCALE);
		int yPos = (int) (super.getYOnScreen() - hatImage.getHeight() + HAT_DISPLACEMENT*super.SCALE);
		int width = (int) (hatImage.getWidth()*super.SCALE);
		int height = (int) (hatImage.getHeight()*super.SCALE);
		g.drawImage(hatImage, xPos, yPos, width, height, null);
	}

	/**
	 * 
	 * @param direction
	 * @return
	 */
	private Compass getRelativeDirectionFromCompass(Compass direction) {
		int intValue;
		switch (dir) {
		case UP:
			return Compass.NORTH;
		case LEFT:
			return Compass.WEST;
		case RIGHT:
			return Compass.EAST;
		case DOWN:
			return Compass.SOUTH;
		default:
			return direction;
		}
		
			
	}

	/**
	 * @return the direction the character if facing (Different to cardinal
	 *         direction)
	 */
	public Direction getDirection() {
		return dir;
	}

	/**
	 * @param dir
	 *            the direction to set
	 */
	public void setDirection(Direction dir) {
		this.dir = dir;
	}
	
	public void rotateRight(){
		switch(dir){
		case LEFT:
			dir = Direction.UP;
			return;
		case UP:
			dir = Direction.RIGHT;
			return;
		case RIGHT:
			dir = Direction.DOWN;
			return;
		case DOWN:
			dir = Direction.LEFT;
			return;
		}
	}
	
	public void rotateLeft(){
		switch(dir){
		case LEFT:
			dir=Direction.DOWN;
			return;
		case DOWN:
			dir = Direction.RIGHT;
			return;
		case RIGHT:
			dir = Direction.UP;
			return;
		case UP:
			dir = Direction.LEFT;
			return;
		}
	}

}
