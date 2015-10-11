package render;

import render.Drawable.Position;
import render.RenderCanvas.Compass;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * @author Paige Halliwell ID:300316022
 *
 */
public class RenderCanvas extends JPanel implements MouseListener {
	private Drawable selectedObject;
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

	/**
	 * 
	 * @param b array of drawable objects that represent the current room
	 */
	public void setRoom(DrawableTile[][] b) {
		this.board = b;
		selectedObject = null;
	}

	@Override
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
			renderer.drawNorthView(board, g, width / 2);
			break;
		case EAST:
			renderer.drawEastView(board, g, width / 2);
			break;
		case SOUTH:
			renderer.drawSouthView(board, g, width / 2);
			break;
		case WEST:
			renderer.drawWestView(board, g, width / 2);
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
	public void clickedOn(MouseEvent e){
		System.out.println("Hi");
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if (board != null) {
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					Drawable clickedOn = board[i][j].isClickedOn(arg0.getX(),
							arg0.getY());// the isClickedOn method returns to
											// topmost object of the draw order
					if (clickedOn != null) {// If clickedOn isn't null an object
											// has been found, assign selected
											// object and return from method
						selectedObject = clickedOn;
						return;
					}
				}
			}
		}

	}

	/**
	 * @return the selectedObject, may be null
	 */
	public Drawable getSelectedObject() {
		return selectedObject;
	}

}
