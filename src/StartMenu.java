import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * This program is written by Wang Ziwen(Ziv)
 * Use the technology of SWING GUI and OO design
 * @author Ziv
 *
 */

public class StartMenu {
	
	/** The start menu. */
	ImageIcon startMenu;

	/**
	 * Instantiates a new start menu.
	 *
	 * @param width
	 *            the width of image 
	 * @param height
	 *            the height of image 
	 */
	public StartMenu(int width, int height) {
		startMenu = new ImageIcon(getClass().getResource("StartMenu.jpg"));
		startMenu = new ImageIcon(startMenu.getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING));
	}

	/**
	 * Gets the img.
	 *
	 * @return the img
	 */
	public ImageIcon getImg() {
		return startMenu;
	}

}
