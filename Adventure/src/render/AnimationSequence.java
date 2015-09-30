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
		}
		try {
			loadSprites(imgLoc);
		} catch (IOException e) {
			System.out.println("Error reading txt file for " + imgLoc
					+ " sprites");
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
	private void loadSprites(String imgLoc) throws IOException {

		Scanner file = new Scanner(new File(imgLoc + ".txt"));
		// Get animation length
		int animationLength = file.nextInt();
		file.nextLine();
		// create arrays for the first animation, in every direction
		for (Compass c : Compass.values()) {
			animation0.put(c, new BufferedImage[animationLength]);
		}
		// get the direction and turn into a compass point
		Compass dir = Compass.stringToCompass(file.nextLine());
		// there should be a line for each image in the animation
		for (int i = 0; i < animationLength; i++) {
			Scanner line = new Scanner(file.nextLine());
			animation0.get(dir)[i] = getSprite(line);
		}
		// if there is more, there must be a second animation state
		if (file.hasNextLine()) {
			animationLength = file.nextInt();
			file.nextLine();
			// create arrays for the first animation, in every direction
			for (Compass c : Compass.values()) {
				animation1.put(c, new BufferedImage[animationLength]);
			}
			// get the direction and turn into a compass point
			dir = Compass.stringToCompass(file.nextLine());
			// there should be a line for each image in the animation
			for (int i = 0; i < animationLength; i++) {
				Scanner line = new Scanner(file.nextLine());
				animation1.get(dir)[i] = getSprite(line);
			}
		}
		// else there is only one set of animations.
		// set animation1 to be the same as animation0 to prevent null pointer
		// exceptions
		else {
			animation1 = animation0;
		}
		file.close();

	}

	public void changeState() {
		animationState = !animationState;
		currentImage = 0;// reset to zero, the different states may not be the
							// same length
	}

	public boolean getState() {
		return animationState;
	}

	public void tick() {
		if (animationState) {
			// if at end of animation cycle back to beginning
			if (currentImage == animation0.get(Compass.NORTH).length - 1) {
				currentImage = 0;
			}
			// else just increment by 1
			else {
				currentImage++;
			}
		}

	}

	private BufferedImage getSprite(Scanner line) {
		int x = line.nextInt();
		int y = line.nextInt();
		int w = line.nextInt();
		int h = line.nextInt();
		return spriteSheet.getSubimage(x, y, w, h);
	}
}
