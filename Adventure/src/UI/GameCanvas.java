package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import render.DrawableTile;
import render.RenderCanvas;

/**
 * 
 * @author Megan Davidson ID:300313759
 *
 */
public class GameCanvas extends JPanel implements Serializable {
	private JPanel outerPanel;
	private int xClick = -1;
	private int yClick = -1;
	private RenderCanvas canvasRen = new RenderCanvas();
	private JLabel[] bag;
	private String selectedItem;
	private Game.Item[] bagToDraw;
	private JPanel button;
	private JPanel handPanel;

	/**
	 * Creates a new Canvas and sets up the board Does this by creating a series
	 * of JPanels containing the different elements (renderCanvas/bag/buttons)
	 * and then adding them to the canvas
	 * 
	 */
	public GameCanvas() {
		setLayout(new BorderLayout());

		// creating panel and adding the render canvas
		outerPanel = new JPanel();
		outerPanel.setLayout(new BorderLayout());
		outerPanel.add(canvasRen, BorderLayout.CENTER);
		add(outerPanel);

		// Creates panel and adding buttons
		button = new JPanel();
		button.setLayout(new BoxLayout(button, BoxLayout.Y_AXIS));
		ImageIcon antiClock = new ImageIcon(getClass().getResource("anticl.png"));
		JButton RL = new JButton("AntiClockWise", antiClock);
		RL.setPreferredSize(new Dimension(50, 50));
		RL.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				canvasRen.rotateAntiClockwise();
				repaint();
			}
		});
		ImageIcon clockWise = new ImageIcon(getClass().getResource("clockw.png"));
		JButton RR = new JButton("ClockWise        ", clockWise);
		RR.setPreferredSize(new Dimension(50, 50));
		RR.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				canvasRen.rotateClockwise();
				repaint();
			}
		});
		button.add(Box.createRigidArea(new Dimension(0, 30)));
		button.add(RL);
		button.add(Box.createRigidArea(new Dimension(0, 10)));
		button.add(RR);
		button.add(Box.createRigidArea(new Dimension(0, 10)));
		JPanel text = new JPanel();
		JTextArea textArea = new JTextArea(20, 15);
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setEditable(false);
		text.add(textArea);
		button.add(text);
		add(button, BorderLayout.EAST);

		// Creates the panel and adding bag
		handPanel = new JPanel();
		handPanel.setLayout(new GridLayout(1, 9));
		bag = new JLabel[9];
		for (int t = 0; t < bag.length; t++) {
			ImageIcon icon2 = new ImageIcon(getClass().getResource("greentile.png"));
			// TODO above is for temp displaying bad, need gamelogic
			bag[t] = new JLabel(icon2);
			bag[t].setVisible(true);
			bag[t].addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.print("test");
				}

				public void mousePressed(MouseEvent e) {
				}

				public void mouseReleased(MouseEvent e) {
				}

				public void mouseEntered(MouseEvent e) {
				}

				public void mouseExited(MouseEvent e) {
				}
			});

			handPanel.add(bag[t], BorderLayout.CENTER);
			// drawBag(); TODO drawing the proper logic complete
		}
		handPanel.setVisible(true);
		add(handPanel, BorderLayout.SOUTH);

		outerPanel.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				repaint();
				DrawableTile objectClicked = canvasRen.clickedOn(e);
				// TODO check actions
			}
		});
		outerPanel.addComponentListener(new ComponentListener() {
			public void componentResized(ComponentEvent e) {
				canvasRen.setSize(e.getComponent().getSize());
			}

			public void componentHidden(ComponentEvent e) {
			}

			public void componentMoved(ComponentEvent e) {
			}

			public void componentShown(ComponentEvent e) {
			}
		});

	}

	/**
	 * returns the render Canvas
	 * 
	 * @return RenderCanvas
	 */
	public RenderCanvas getRenderCanvas() {
		return canvasRen;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(600, 600);
	}

	/**
	 * Draws the actual images in the bag in to the panel
	 */
	public void drawBag() {
		 if(true){
		for (int a = 0; a < bag.length; a++) {
			 if(bagToDraw[a]!=null){
			 ImageIcon card = getItem(bagToDraw[a]);
			 bag[a].setIcon(card);
		}
		 else{
		 bag[a].setIcon(null);
		 	}
		}
	}
}

	public ImageIcon getItem(Game.Item i) {
		return null; // remove for compiling
	}

	/**
	 * helper method for changing the size of image icons
	 * 
	 * @param image
	 * @param width
	 * @param height
	 * @return ImageIcon
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