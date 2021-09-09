import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class MineFieldButton extends JButton {
	
	private int colorIndex;
	private boolean isOnPath;
	private boolean isMine;
	private boolean isExploded;
	private boolean isNeighbor;
	private boolean isStart;
	private boolean isDestination;
	private boolean isClicked;
	private boolean isCurrent;
	private int currentX;
	private int currentY;
	
	/**
	 * Makes an array of colors
	 */
	
	public static final Color[] COLORS = new Color[] {
			Color.GREEN,   //0
			Color.YELLOW,  //1
			Color.ORANGE,  //2
			Color.RED,     //3
			Color.BLACK,   //4
			Color.magenta, //5
			Color.CYAN     //6
		};

	/**
	 * defines variables for each button
	 */
	
	public MineFieldButton(int x, int y) {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(50, 50));
		colorIndex = 0;
		isOnPath = false;
		isMine = false;
		isExploded = false;
		isNeighbor = false;
		isStart = false;
		isDestination = false;
		isClicked = false;
		isCurrent = false;
	}
	
	/**
	 * sets tile to isOnPath
	 */
	
	public void isOnPath() {
		isOnPath = true;
	}
	
	/**
	 * returns if tile is on path
	 * @return isOnePath
	 */
	
	public boolean getPathStatus() {
		return isOnPath;
	}
	
	/**
	 * sets path tile to blue
	 */
	
	public void showPathColorChange() {
	if (isOnPath == true) {
			setBackground(Color.BLUE);
		}
	}
	
	/**
	 * sets a tile to isMine
	 */
	
	public void isMine() {
		isMine = true;
	}
	
	/**
	 * returns if a tile is a mine
	 * @return isMine status
	 */
	
	public boolean getMineStatus() {
		return isMine;
	}
	
	/**
	 * sets mine to black
	 */
	
	public void showMinesColorChange() {
		if (isMine == true) {
			setBackground(COLORS[4]);
		}
	}
	
	/**
	 * sets a mine to is exploded
	 */
	
	public void mineExploded() {
		isExploded = true;
	}
	
	/**
	 * returns if mine is exploded
	 * @return isExploded
	 */
	
	public boolean mineExplodedStatus() {
		return isExploded;
	}
	
	/**
	 * sets neighbor status to true
	 */
	
	public void isNeighbor() {
		isNeighbor = true;
	}
	
	/**
	 * sets neighbor status to false
	 */
	
	public void isNotNeighbor() {
		isNeighbor = false;
	}
	
	/**
	 * returns if a tile is a neighbor
	 * @return isNeighbor
	 */
	
	public boolean getNeighborStatus() {
		return isNeighbor;
	} 
	
	/**
	 * sets tile to start tile
	 */
	
	public void isStart() {
		isStart = true;
		setBackground(COLORS[6]);
		isCurrent = true;
	}
	
	/**
	 * returns if tile is start
	 * @return if tile is start
	 */
	
	public boolean getStartStatus() {
		return isStart;
	}
	
	/**
	 * sets tile to destination tile
	 */
	
	public void isDestination() {
		isDestination = true;
		setBackground(COLORS[5]);
	}
	
	/**
	 * returns destination tile
	 * @return destination
	 */
	
	public boolean getDestinationStatus() {
		return isDestination;
	}
	
	/**
	 * sets isClicked to true
	 */
	
	public void isClicked() {
		isClicked = true;
		setBackground(Color.GREEN);
	}
	
	/**
	 * sets isClicked to false
	 */
	
	public void isNotClicked() {
		isClicked = false;
	}
	
	/**
	 * 
	 */
	
	public boolean getClickedStatus() {
		return isClicked;
	}
	
	/**
	 * sets isCurrent to true
	 */
	
	public void isCurrent() {
		isCurrent = true;
	}
	
	/**
	 * sets isCurrent to false
	 */
	
	public void isNotCurrent() {
		isCurrent = false;
	}
	
	/**
	 * returns current status 
	 * @return isCurrent
	 */
	
	public boolean getCurrentStatus() {
		return isCurrent;
	}
	
}
