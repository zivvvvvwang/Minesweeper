import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
/**
 * This program is written by Wang Ziwen(Ziv)
 * Use the technology of SWING GUI and OO design
 * @author Ziv
 *
 */

public class About extends JFrame implements MouseListener {
	
	/** The about pane. */
	private JPanel aboutPane;
	
	/** The msg. */
	private JLabel msg;
	
	/** The msg 1. */
	private JLabel msg1;
	
	/** The exit. */
	private JButton exit;

	/**
	 * Instantiates a new about.
	 *
	 * @param strName
	 *            the str name
	 */
	public About(String strName) {
		super(strName);
		setSize(270, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		aboutPane = new JPanel();
		msg = new JLabel("Minesweeper written by Ziwen Wang (Ziv).");
		msg1 = new JLabel("                            Enjoy!                              ");
		exit = new JButton("Exit");
		exit.addMouseListener(this);
		aboutPane.add(msg);
		aboutPane.add(msg1);
		aboutPane.add(exit);

		setContentPane(aboutPane);

	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		About about = new About("Win");
		about.show();
	}

	
	// the event handle to deal with the mouse click
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}

	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
