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

	private final String RESOURCE = "resource/";
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

		animation0 = new HashMap<Compass, BufferedImage[]>();
		animation1 = new HashMap<Compass, BufferedImage[]>();
		// Load sprite sheet
		try {
			spriteSheet = ImageIO.read(new File(RESOURCE + imgLoc + ".png"));
		} catch (IOException e1) {
			System.out.println("Error readings images for " + imgLoc);
		}
		try {
			loadSprites(imgLoc);
		} catch (IOException e) {
			System.out.println("Error reading txt file for " + imgLoc
					+ " sprites" + "\n" + e.toString());
			
		}

	}

	public BufferedImage getImage(Compass direction) {
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

		Scanner file = new Scanner(new File(RESOURCE + imgLoc + ".txt"));
		// Get animation length
		int animationLength = file.nextInt();
		file.nextLine();
		for (int i = 0; i<4; i++) {
			// get the direction and turn into a compass point
			Compass dir = Compass.stringToCompass(file.nextLine());
			animation0.put(dir, new BufferedImage[animationLength]);// create arrays for the first animation, in every direction
			// there should be a line for each image in the animation
			for (int j = 0; j < animationLength; j++) {
				Scanner line = new Scanner(file.nextLine());
				animation0.get(dir)[j] = getSprite(line);
			}
		}

		// if there is more, there must be a second anim
			if(file.hasNext()){
				animationLength = file.nextInt();
				file.nextLine();
				for (int i = 0; i<4; i++) {
					// get the direction and turn into a compass point
					Compass dir = Compass.stringToCompass(file.nextLine());
					animation1.put(dir, new BufferedImage[animationLength]);// create arrays for the first animation, in every direction
					// there should be a line for each image in the animation
					for (int j = 0; j < animationLength; j++) {
						Scanner line = new Scanner(file.nextLine());
						animation1.get(dir)[j] = getSprite(line);
					}
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

	public void animationTick() {
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
		BufferedImage sprite = spriteSheet.getSubimage(x, y, w, h);
		return sprite;
	}

}
