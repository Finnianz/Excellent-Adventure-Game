package render;

import render.Drawable.Position;
import render.RenderCanvas.Compass;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * @author Paige Halliwell ID:300316022
 *
 */
public class RenderCanvas extends JPanel {
	private Compass direction = Compass.NORTH;
	public IsometricRenderer renderer = new IsometricRenderer();
	private DrawableTile[][] board;
	private int height = 0;
	private int width = 0;

	public enum Compass {
		NORTH, EAST, SOUTH, WEST;

		public static Compass stringToCompass(String directionString) {
			switch (directionString) {
			case "NORTH":
				return NORTH;
			case "EAST":
				return EAST;
			case "SOUTH":
				return SOUTH;
			case "WEST":
				return WEST;
			default:
				return null;
			}
		}
	}

	public RenderCanvas() {

	}

	public void setRoom(DrawableTile[][] b) {
		this.board = b;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void setSize(Dimension d) {
		width = d.width;
		height = d.height;

	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, width, height);
		if (board == null)
			return;
		switch (direction) {
		case NORTH:
			renderer.drawNorthView(board, g);
			break;
		case EAST:
			renderer.drawEastView(board, g);
			break;
		case SOUTH:
			renderer.drawSouthView(board, g);
			break;
		case WEST:
			renderer.drawWestView(board, g);
			break;
		}
		repaint();

	}

	/**
	 * Changes the viewing direction to the left
	 */
	public void rotateLeft() {
		switch (direction) {
		case NORTH:
			direction = Compass.WEST;
			break;
		case EAST:
			direction = Compass.NORTH;
			break;
		case SOUTH:
			direction = Compass.EAST;
			break;
		case WEST:
			direction = Compass.SOUTH;
			break;
		}
		repaint();
	}

	/**
	 * Changes the viewing direction to the right
	 */
	public void rotateRight() {
		switch (direction) {
		case NORTH:
			direction = Compass.EAST;
			break;
		case EAST:
			direction = Compass.SOUTH;
			break;
		case SOUTH:
			direction = Compass.WEST;
			break;
		case WEST:
			direction = Compass.NORTH;
			break;
		}
		repaint();
	}

}
