package render;

import render.Drawable.Position;
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
	private final int MAX_WIDTH = 1000;
	private final int MAX_HEIGHT = 1000;

	public enum Compass {
		NORTH, EAST, SOUTH, WEST
	}

	public RenderCanvas(){
	}
	
	public void setRoom(DrawableTile[][] b){
		this.board=b;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(600, 600);
	}
	

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, MAX_WIDTH, MAX_HEIGHT);
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
