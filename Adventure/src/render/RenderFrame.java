package render;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class RenderFrame extends javax.swing.JFrame implements
		java.awt.event.KeyListener {

	private RenderCanvas canvas;
	private Thread timerThread;

	private double x_accel = 0;

	public RenderFrame() {
		super("Renderer");
		canvas = new RenderCanvas();
		setLayout(new BorderLayout());
		add(canvas, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setResizable(false);
		setVisible(true);
		timerThread = new Timer();
		timerThread.start();
		addKeyListener(this);

	}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_KP_RIGHT) {

		} else if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_KP_LEFT) {

		} else if (code == KeyEvent.VK_UP || code == KeyEvent.VK_KP_UP) {

		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public class Timer extends Thread {

		public void run() {
			while (1 == 1) {
				try {
					Thread.sleep(100); // 0.1s delay

				} catch (InterruptedException e) {
				}
			}
		}
	}

	public static void main(String[] args) {
		new RenderFrame();
	}

}
