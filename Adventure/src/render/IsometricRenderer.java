package render;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import render.RenderCanvas.Compass;

/**
 * 
 * @author Paige Halliwell ID:300316022
 *
 */
public class IsometricRenderer implements Serializable {
	public final int TOP_PADDING = 150;
	public final int TILE_HEIGHT = 38;
	public final int TILE_WIDTH = 76;

	/**
	 * Draws the array from back to front if looking to the north
	 */
	public void drawNorthView(DrawableTile[][] board, Graphics g, int centerLine) {
		int maxLength = board.length;
		int currentCol = 0;// Keep track of current column number
		int maxCol = 1; // Max number of columns for this row, starts at one
		int currentRow = 0;
		int x = 0;// Reference int to first array
		int y = maxLength - 1;// Reference int to second array
		int startingX = x;// number x starts at for current row
		int startingY = y;// number y starts at for current row
		// while current row < than the max
		while (currentRow < maxLength * 2) {

			while (currentCol < maxCol) {

				int xPos = getXPosition(currentCol, maxCol, currentRow,
						TILE_WIDTH, centerLine);
				int yPos = TOP_PADDING + (TILE_HEIGHT * currentRow / 2);
				board[x][y].draw(Compass.NORTH, xPos, yPos, g, TILE_WIDTH,
						TILE_HEIGHT);
				x++;
				y++;
				currentCol++;
			}
			// If past the halfway point
			if (x == maxLength) {
				startingX++;// Increment X's starting point
				startingY = 0; // Reset Y's starting point to 0
				maxCol--;// Decrease the max columns for this row
			}
			// Else the top half of the board
			else {
				startingY--;// Decrease y's starting point
				startingX = 0;// Reset X's starting point to 0
				maxCol++;// Increase max columns for this row

			}
			// Set the counters
			x = startingX;
			y = startingY;
			currentRow++;
			currentCol = 0;
		}
	}

	/**
	 * Draws the array from back to front if looking to the east
	 */
	public void drawEastView(DrawableTile[][] board, Graphics g, int centerLine) {
		int maxLength = board.length;
		int currentCol = 0;// Keep track of current column number
		int maxCol = 1; // Max number of columns for this row, starts at one
		int currentRow = 0;
		int x = maxLength - 1;// Reference int to first array
		int y = maxLength - 1;// Reference int to second array
		int startingX = x;// number x starts at for current row
		int startingY = y;// number y starts at for current row
		// while current row < than the max
		while (currentRow < maxLength * 2) {

			while (currentCol < maxCol) {
				int xPos = getXPosition(currentCol, maxCol, currentRow,
						TILE_WIDTH, centerLine);
				int yPos = TOP_PADDING + (TILE_HEIGHT * currentRow / 2);
				board[x][y].draw(Compass.EAST, xPos, yPos, g, TILE_WIDTH,
						TILE_HEIGHT);
				x++;
				y--;
				currentCol++;
			}
			// If past the halfway point
			if (y < 0) {
				startingX = 0;
				startingY--;
				maxCol--;
			}
			// Else the top half of the board
			else {
				startingY = maxLength - 1;
				startingX--;
				maxCol++;

			}
			// Set the counters
			x = startingX;
			y = startingY;
			currentRow++;
			currentCol = 0;
		}
	}

	/**
	 * Draws the array from back to front if looking to the South
	 */
	public void drawSouthView(DrawableTile[][] board, Graphics g, int centerLine) {
		int maxLength = board.length;
		int currentCol = 0;// Keep track of current column number
		int maxCol = 1; // Max number of columns for this row, starts at one
		int currentRow = 0;
		int x = maxLength - 1;// Reference int to first array
		int y = 0;// Reference int to second array
		int startingX = x;// number x starts at for current row
		int startingY = y;// number y starts at for current row
		// while current row < than the max
		while (currentRow < maxLength * 2) {

			while (currentCol < maxCol) {
				int xPos = getXPosition(currentCol, maxCol, currentRow,
						TILE_WIDTH, centerLine);
				int yPos = TOP_PADDING + (TILE_HEIGHT * currentRow / 2);
				board[x][y].draw(Compass.SOUTH, xPos, yPos, g, TILE_WIDTH,
						TILE_HEIGHT);
				x--;
				y--;
				currentCol++;
			}
			// If past the halfway point
			if (x < 0) {
				startingX--;
				startingY = maxLength - 1;
				maxCol--;
			}
			// Else the top half of the board
			else {
				startingY++;
				startingX = maxLength - 1;
				maxCol++;

			}
			// Set the counters
			x = startingX;
			y = startingY;
			currentRow++;
			currentCol = 0;
		}
	}

	/**
	 * Draws the array from back to front if looking to the west
	 */

	public void drawWestView(DrawableTile[][] board, Graphics g, int centerLine) {
		int maxLength = board.length;
		int currentCol = 0;// Keep track of current column number
		int maxCol = 1; // Max number of columns for this row, starts at one
		int currentRow = 0;
		int x = 0;// Reference int to first array
		int y = 0;// Reference int to second array
		int startingX = x;// number x starts at for current row
		int startingY = y;// number y starts at for current row
		// while current row < than the max
		while (currentRow < maxLength * 2) {

			while (currentCol < maxCol) {
				int xPos = getXPosition(currentCol, maxCol, currentRow,
						TILE_WIDTH, centerLine);
				int yPos = TOP_PADDING + (TILE_HEIGHT * currentRow / 2);
				board[x][y].draw(Compass.WEST, xPos, yPos, g, TILE_WIDTH,
						TILE_HEIGHT);
				x--;
				y++;
				currentCol++;
			}
			// If past the halfway point
			if (y == maxLength) {
				startingX = maxLength - 1;
				startingY++;
				maxCol--;
			}
			// Else the top half of the board
			else {
				startingY = 0;
				startingX++;
				maxCol++;

			}
			// Set the counters
			x = startingX;
			y = startingY;
			currentRow++;
			currentCol = 0;
		}
	}

	/**
	 * Helper method to get the x postion of a tile.
	 * 
	 * @param currentCol
	 *            the current column
	 * @param maxCol
	 *            the max columns for this row
	 * @param currentRow
	 * 
	 * @param tileWidth
	 * 
	 * @return
	 */
	private int getXPosition(int currentCol, int maxCol, int currentRow,
			int tileWidth, int centerLine) {
		int xPos = centerLine;
		double drawingCol = (currentCol);
		// If current row is divided by 2
		if (currentRow % 2 == 0) {
			drawingCol = currentCol - (maxCol / 2.0);
			xPos += tileWidth * (drawingCol);
		} else {
			drawingCol = (currentCol - (maxCol / 2));
			xPos += tileWidth * (drawingCol);
		}
		return (int) xPos;
	}

}
