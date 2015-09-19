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
	private Compass direction = Compass.EAST;
	public IsometricRenderer renderer = new IsometricRenderer();
	private DrawableTile[][] board;
	private final String floorString = "resource/FloorBlock";
	private final String wallString = "resource/Wall";
	private final String figureString = "resource/sticky";

	public enum Compass {
		NORTH, EAST, SOUTH, WEST
	}

	public RenderCanvas(){
		
		//Creates a board with one stick figure and walls around the edges. For testing purposes only.
		/*
		  board = new DrawableTile[4][4];
		  for(int i=0;i<board.length;i++){
			for(int j= 0;j<board.length;j++){
				Drawable floor = new Floor(floorString);
				Drawable occupier = null;
				Drawable wallNE = null;
				Drawable wallNW = null;
				Drawable wallSE = null;
				Drawable wallSW = null;
				if(i==0){
					wallNW = new Wall(wallString, Position.WALL_NW);
				}
				if(j==0){
					wallSW = new Wall(wallString, Position.WALL_SW);
				}
				if(i==board.length-1){
					wallSE = new Wall(wallString, Position.WALL_SE);
				}
				if(j==board.length-1){
					wallNE = new Wall(wallString, Position.WALL_NE);
				}
				if(i==0 && j==board.length-1){
					occupier = new Person(figureString, Position.CENTER);
				}
				board[i][j] = new DrawableTile(wallNE, wallNW, wallSE,wallSW, floor, occupier);
			}
		  }*/
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
		if (board == null)
			return;
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
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
