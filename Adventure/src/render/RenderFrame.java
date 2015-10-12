package render;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class RenderFrame extends javax.swing.JFrame implements
		java.awt.event.KeyListener {

	private RenderCanvas canvas;

	public RenderFrame() {
		super("Renderer");
		canvas = new RenderCanvas();
		setLayout(new BorderLayout());
		add(canvas, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setResizable(false);
		setVisible(true);
		addKeyListener(this);

	}

	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_KP_RIGHT) {
			if(canvas!=null){
				//canvas.rotateRight();
			}
		} else if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_KP_LEFT) {
			if(canvas!=null){
				//canvas.rotateLeft();
			}
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public static void main(String[] args) {
		new RenderFrame();
	}

}
