package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Game.Game;
import render.RenderCanvas;
import render.RenderFrame;

public class GameCanvas extends JPanel {
	private Game game;
	private JPanel outerPanel;
	private RenderFrame GamePanel;
	private JLabel[][] grid;
	private int xClick = -1;
	private int yClick = -1;
	private RenderCanvas canvasRen = new RenderCanvas();
	
	/**
	 * Creates a new Canvas and sets up the board
	 *
	 * @param gui
	 */
	public GameCanvas() {
		setLayout(new BorderLayout());
		outerPanel = new JPanel();
		outerPanel.setSize(1500, 1500);
		outerPanel.setLayout(null);
		outerPanel.add(canvasRen);
		drawBoard();
		this.addComponentListener(new ComponentListener() {

    		public void componentResized(ComponentEvent e) {
    			canvasRen.setSize(e.getComponent().getSize());
    		}

    		public void componentHidden(ComponentEvent e) {}

    		public void componentMoved(ComponentEvent e) {}

    		public void componentShown(ComponentEvent e) {}
    	});

	}
	public RenderCanvas getRenderCanvas(){
		return canvasRen;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(600, 600);
	}

	public void drawBoard() {
	}

	@Override
	public void paint(Graphics g) {

		canvasRen.paint(g);
	}

	/**
	 * @return the xClick
	 */
	public int getxClick() {
		return xClick;
	}

	/**
	 * @return the yClick
	 */
	public int getyClick() {
		return yClick;
	}

}
