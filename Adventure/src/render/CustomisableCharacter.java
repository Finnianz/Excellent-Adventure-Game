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
	private final int HAT_DISPLACEMENT = 13;

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
		int xPos = x + (tileWidth / 2) - (hatImage.getWidth()/2);
		int yPos = super.getYOnScreen() - hatImage.getHeight() + HAT_DISPLACEMENT;
		g.drawImage(hatImage, xPos, yPos, null);
	}

	private Compass getRelativeDirectionFromCompass(Compass direction) {
		int intValue;
		switch (dir) {
		case UP:
			return direction;
		case LEFT:
			intValue = direction.ordinal() -1;
			if(intValue <0){
				intValue = 3;
			}
			return Compass.values()[intValue];
		case RIGHT:
			intValue = direction.ordinal() +1;
			if(intValue >3){
				intValue = 0;
			}
			return Compass.values()[intValue];
		case DOWN:
			intValue = direction.ordinal() +2;
			if(intValue ==4){
				intValue = 0;
			}
			if(intValue ==5){
				intValue = 1;
			}
			return Compass.values()[intValue];
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

}
