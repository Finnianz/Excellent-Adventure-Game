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

	public CustomisableCharacter(String characterImgLoc, String hatString) {
		super(characterImgLoc, Position.CENTER);
		hatImages = new AnimationSequence(hatString);
	}

	@Override
	public void draw(Compass direction, int x, int y, Graphics g, int tileWidth,
			int tileHeight) {
		super.draw(direction, x, y, g, tileWidth, tileHeight);
		BufferedImage hatImage = hatImages.getImage(direction);
		int xPos =x + (tileWidth / 2) - (hatImage.getWidth() / 2);
		int yPos =super.getYOnScreen()  - hatImage.getHeight();
		g.drawImage(hatImage, xPos, yPos, null);
	}

}
