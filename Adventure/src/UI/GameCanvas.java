package UI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Game.Gameplay;
import render.Drawable;
import render.RenderCanvas;
import render.RenderFrame;

public class GameCanvas extends JPanel {
	private Gameplay game;
	private JPanel outerPanel;
	private RenderFrame GamePanel;
	private JLabel[][] grid;
	private int xClick = -1;
	private int yClick = -1;
	private RenderCanvas canvasRen = new RenderCanvas();
	private JLabel[] bag;
	private String selectedItem;
	private Game.Item[] bagToDraw;
	private ImageIcon benie = resize(makeImageIcon("Beanie.png"),70,70);
	/**
	 * Creates a new Canvas and sets up the board
	 *
	 * @param gui
	 */
	public GameCanvas() {
		setLayout(new BorderLayout());
		outerPanel = new JPanel();
		outerPanel.setSize(300,300);
		outerPanel.setLayout(null);
		outerPanel.add(canvasRen, BorderLayout.CENTER);
		
		
		//Creates panel for buttons
		JPanel button = new JPanel();
		button.setLayout(new BoxLayout(button, BoxLayout.Y_AXIS));
		JButton useItem = new JButton("Use Item");
		useItem.setPreferredSize(new Dimension(150, 100));
		JButton rotateLeft = new JButton("Rotate Left");
		rotateLeft.setPreferredSize(new Dimension(150, 100));
		button.add(Box.createRigidArea(new Dimension(0,150)));
		button.add(useItem);
		button.add(Box.createRigidArea(new Dimension(0,10)));
		button.add(rotateLeft);
		add(button, BorderLayout.LINE_END);
		
		//Creates the panel that will contain in the bag
		JPanel handPanel = new JPanel();
		handPanel.setLayout(new GridLayout(1,6));
		bag = new JLabel[6];
		for(int t = 0; t<bag.length;t++){
				bag[t] = new JLabel(benie);
				bag[t].setText("bob");
				bag[t].setForeground(Color.BLUE);
				//bag[t].setIcon(null);
				//bag[t].setVisible(true);
//				bag[t].addMouseListener(new MouseListener(){
//					@Override
//					public void mouseClicked(MouseEvent e) {
//					
//					}
//					public void mousePressed(MouseEvent e) {
//					}
//					public void mouseReleased(MouseEvent e) {
//					}
//					public void mouseEntered(MouseEvent e) {
//					}
//					public void mouseExited(MouseEvent e) {
//					}});
			
				handPanel.add(bag[t]);
				//drawBag();
				//handPanel.add(useItem);
		}		
		handPanel.setVisible(true);
		add(handPanel, BorderLayout.SOUTH);

		setVisible(true);
		add(outerPanel);
		drawBoard();
		outerPanel.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {
				Drawable objectClicked = canvasRen.clickedOn(e);
				//TODO check actions
			}
		});
		outerPanel.addComponentListener(new ComponentListener() {

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
	public void drawBag(){
//		if(bagToDraw!=null){
		for(int a = 0; a<bag.length; a++){
//					if(bagToDraw[a]!=null){
//						ImageIcon card = getItem(bagToDraw[a]);
//						bag[a].setIcon(benie);
					}
//					else{
//						bag[a].setIcon(null);
//					}
//				}
//			}
	
		}
	public ImageIcon getItem(Game.Item i){
		return null; //remove for compiling
	}
	private static ImageIcon makeImageIcon(String filename) {
		// using the URL means the image loads when stored
		// in a jar or expanded into individual files.
		java.net.URL imageURL = GameCanvas.class.getResource(filename);

		ImageIcon icon = null;
		System.out.print("N");
		if (imageURL != null) {
			icon = new ImageIcon(imageURL);
			System.out.print("Y");
		}
		return icon;
	}
	/**
	 * helper method for changing the size of image icons
	 * @param image
	 * @param width
	 * @param height
	 * 
	 */
	public static ImageIcon resize(ImageIcon image, int width, int height) {
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
		Graphics2D g2d = (Graphics2D) bi.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(image.getImage(), 0, 0, width, height, null);
		g2d.dispose();
		return new ImageIcon(bi);
	}
}
