package render;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import render.RenderCanvas.Compass;

import javax.imageio.ImageIO;

public class AnimationSequence {

	// One array for each direction. Each array is a sequence of images that
	// create the animation
	private BufferedImage spriteSheet;
	private boolean animationState = false;// Which animation is the active one,
											// false is 0 true is1
	private HashMap<Compass, BufferedImage[]> animation0;// A map of four image
															// arrays,
	// one for each direction
	private HashMap<Compass, BufferedImage[]> animation1;// A second map to
															// support two
	// different states for an
	// object eg. light on/off
	// or a door that is
	// locked/unlocked

	private int currentImage = 0;// To keep track of where we are in the
									// sequence

	public AnimationSequence(String imgLoc) {

		// Load sprite sheet
		try {
			spriteSheet = ImageIO.read(new File(imgLoc + ".png"));
		} catch (IOException e1) {
			System.out.println("Error readings images for " + imgLoc);
			e1.printStackTrace();
		}

	}

	public Image getImage(Compass direction) {
		if (animationState) {
			return animation1.get(direction)[currentImage];

		} else {
			return animation0.get(direction)[currentImage];
		}
	}

	/**
	 * Helper method to load the images
	 * 
	 * @param imgLoc
	 * @throws IOException
	 */
	private void loadImages(String imgLoc) throws IOException {

		Scanner file = new Scanner(new File(imgLoc + ".txt"));
		// Get animation length
		int animationLength = file.nextInt();
		file.nextLine();
		// create arrays for the first animation, in every direction
		for (Compass c : Compass.values()) {
			animation0.put(c, new BufferedImage[animationLength]);
		}

		Scanner line;
		while (file.hasNext()) {
			String directionString = file.nextLine();
			Compass dir = Compass.stringToCompass(directionString);
			for (int i = 0; i < animationLength; i++) {
				line = new Scanner(file.nextLine());
				animation0.get(dir)[i] = getSprite(line);
			}
		}
	}

	public void changeState() {
		animationState = !animationState;
	}

	public void tick() {

	}

	private BufferedImage getSprite(Scanner line) {
		int x = line.nextInt();
		int y = line.nextInt();
		int w = line.nextInt();
		int h = line.nextInt();
		return spriteSheet.getSubimage(x, y, w, h);
	}
}
