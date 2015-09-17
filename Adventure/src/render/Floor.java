package render;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Floor {
	public final int HEIGHT = 64;
	public final int WIDTH = 128;
	private Image[] images = new Image[4];// An array of images for the object
											// viewed from the different
											// directions N,E,S,W, in that
											// order

	public Floor(String imgLoc){
		for(int i = 0;i<images.length;i++){
			try {
				images[i] = ImageIO.read(new File(imgLoc + i+ ".png"));
			} catch (IOException e) {
				System.out.println("Image: " + imgLoc+ i + ".png couldn't be found");
				e.printStackTrace();
			}
		}
	}
	public void draw(Isometric.Compass direction, int x, int y, Graphics g) {
		g.drawImage(images[direction.ordinal()], x, y, null);
	}
}
