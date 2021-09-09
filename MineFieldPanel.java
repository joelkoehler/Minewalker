import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MineFieldPanel extends JPanel {

	private MineFieldButton[][] gridPopulation;
	private RandomWalk walk;
	private Random rand;
	private double numMines;
	private MineFieldButton mineCandidate;
	private int counter;
	private Point current;
	private MineFieldButton currentButton;
	private final int DELAY = 500;
	private boolean currentTextToggle;
	private int numMinesCounter;
	private int total;
	private Color oldColor;

	/**
	 * makes a new mine feild panel and random walk
	 * @param listener
	 * @param width
	 * @param height
	 */
	
	public MineFieldPanel(ActionListener listener, int width, int height) { 
		
		currentTextToggle = false;
		walk = new RandomWalk(width);
		walk.createWalk();
		ArrayList<Point> path = walk.getPath();

		current = new Point(0, width - 1);
		System.out.println(current);

		setLayout(new GridLayout(width, height, 1, 1));
		setPreferredSize(new Dimension(600, 600));

		gridPopulation = new MineFieldButton[width][height];

		for (int i = 0; i < gridPopulation.length; i++) {
			for (int j = 0; j < gridPopulation[i].length; j++) {
				// Create option button for color at index [i][j].
				gridPopulation[i][j] = new MineFieldButton(j,i);
				add(gridPopulation[i][j]);
				// Add the ActionListener to the button at index [i][j]
				gridPopulation[i][j].addActionListener(listener);

				if (path.contains(new Point(j, i))) {
					gridPopulation[i][j].isOnPath();
				}
				if (gridPopulation[i][j] == gridPopulation[gridPopulation.length - 1][0]) {
					gridPopulation[i][j].isStart();
				}
				if (gridPopulation[i][j] == gridPopulation[0][gridPopulation.length - 1]) {
					gridPopulation[i][j].isDestination();
				}
			}
		}

		numMines = (double) (((width * height) - path.size()) / 4);

		counter = 0;
		rand = new Random();

		while (counter < Math.round(numMines)) {

			mineCandidate = gridPopulation[rand.nextInt(width)][rand.nextInt(width)];

			if (mineCandidate.getPathStatus() == false && mineCandidate.getMineStatus() == false) {
				mineCandidate.isMine();
				counter++;
			}
		} startAnimation();
	}

	/**
	 * shows path
	 */
	
	public void showPath() {
		for (int i = 0; i < gridPopulation.length; i++) {
			for (int j = 0; j < gridPopulation[i].length; j++) {
				if (gridPopulation[i][j].getStartStatus() == false
						&& gridPopulation[i][j].getDestinationStatus() == false && gridPopulation[i][j].getBackground() == Color.WHITE) {
					gridPopulation[i][j].showPathColorChange();
				}
			}
		}
	}

	/**
	 * hides path
	 */
	
	public void hidePath() {
		for (int i = 0; i < gridPopulation.length; i++) {
			for (int j = 0; j < gridPopulation[i].length; j++) {
				if (gridPopulation[i][j].getBackground() == Color.BLUE) {
					gridPopulation[i][j].setBackground(Color.WHITE);
				}
			}
		}
	}

	/**
	 * shows mines
	 */
	
	public void showMines() {
		for (int i = 0; i < gridPopulation.length; i++) {
			for (int j = 0; j < gridPopulation[i].length; j++) {
				gridPopulation[i][j].showMinesColorChange();
			}
		}
	}

	/**
	 * hides mines
	 */
	
	public void hideMines() {
		for (int i = 0; i < gridPopulation.length; i++) {
			for (int j = 0; j < gridPopulation[i].length; j++) {
				if (gridPopulation[i][j].getBackground() == Color.BLACK
						&& gridPopulation[i][j].mineExplodedStatus() == false) {
					gridPopulation[i][j].setBackground(Color.WHITE);
				}
			}
		}
	}

	/**
	 * Determines neighboring buttons
	 */
	
	public void determineNeighbor() {

		numMinesCounter = 0;
		
		if (current.y > 0) { // North
			gridPopulation[current.y - 1][current.x].isNeighbor();
			
			if (gridPopulation[current.y - 1][current.x].getMineStatus() == true) {
				numMinesCounter++;
				total = numMinesCounter;
				
				if (total == 1 && gridPopulation[current.y][current.x].getMineStatus() == false) {
					gridPopulation[current.y][current.x].setBackground(Color.YELLOW);
				}
				
				if (total == 2 && gridPopulation[current.y][current.x].getMineStatus() == false) {
					gridPopulation[current.y][current.x].setBackground(Color.ORANGE);
				}
				
				if (total == 3 && gridPopulation[current.y][current.x].getMineStatus() == false) {
					gridPopulation[current.y][current.x].setBackground(Color.RED);
				}
				
			}
		}
		if (current.x < gridPopulation.length - 1) { // East
			gridPopulation[current.y][current.x + 1].isNeighbor();
			
			if (gridPopulation[current.y][current.x + 1].getMineStatus() == true) {
				numMinesCounter++;
				total = numMinesCounter;
				
				if (total == 1 && gridPopulation[current.y][current.x].getMineStatus() == false) {
					gridPopulation[current.y][current.x].setBackground(Color.YELLOW);
				}
				
				if (total == 2 && gridPopulation[current.y][current.x].getMineStatus() == false) {
					gridPopulation[current.y][current.x].setBackground(Color.ORANGE);
				}
				
				if (total == 3 && gridPopulation[current.y][current.x].getMineStatus() == false) {
					gridPopulation[current.y][current.x].setBackground(Color.RED);
				}
				
			}
		}
		if (current.y < gridPopulation.length - 1) { // South
			gridPopulation[current.y + 1][current.x].isNeighbor();
			
			if (gridPopulation[current.y + 1][current.x].getMineStatus() == true) {
				numMinesCounter++;
				total = numMinesCounter;
				
				if (total == 1 && gridPopulation[current.y][current.x].getMineStatus() == false) {
					gridPopulation[current.y][current.x].setBackground(Color.YELLOW);
				}
				
				if (total == 2 && gridPopulation[current.y][current.x].getMineStatus() == false) {
					gridPopulation[current.y][current.x].setBackground(Color.ORANGE);
				}
				
				if (total == 3 && gridPopulation[current.y][current.x].getMineStatus() == false) {
					gridPopulation[current.y][current.x].setBackground(Color.RED);
				}
				
			}
		}
		if (current.x > 0) { // West
			gridPopulation[current.y][current.x - 1].isNeighbor();
			
			if (gridPopulation[current.y][current.x - 1].getMineStatus() == true) {
				numMinesCounter++;
				total = numMinesCounter;
				
				if (total == 1 && gridPopulation[current.y][current.x].getMineStatus() == false) {
					gridPopulation[current.y][current.x].setBackground(Color.YELLOW);
				}
				
				if (total == 2 && gridPopulation[current.y][current.x].getMineStatus() == false) {
					gridPopulation[current.y][current.x].setBackground(Color.ORANGE);
				}
				
				if (total == 3 && gridPopulation[current.y][current.x].getMineStatus() == false) {
					gridPopulation[current.y][current.x].setBackground(Color.RED);
				}
				
			}
		}
		gridPopulation[current.y][current.x].setEnabled(true);
	}

	/**
	 * disables all buttons except for neighbors
	 */
	
	public void enableNeighbor() {
		for (int i = 0; i < gridPopulation.length; i++) {
			for (int j = 0; j < gridPopulation[i].length; j++) {
				if (gridPopulation[i][j].getNeighborStatus() == true && gridPopulation[i][j].mineExplodedStatus() == false) {
					gridPopulation[i][j].setEnabled(true);
				} else {
					gridPopulation[i][j].setEnabled(false);
				}
			}
		}
	}

	/**
	 * disables all buttons
	 */
	
	public void disableAllButtons() {
		for (int i = 0; i < gridPopulation.length; i++) {
			for (int j = 0; j < gridPopulation[i].length; j++) {
				gridPopulation[i][j].setEnabled(false);
			}
		}
	}
	
	/**
	 * updates current point
	 */
	
	public void updateCurrent() {
		
		for (int i = 0; i < gridPopulation.length; i++) {
			for (int j = 0; j < gridPopulation[i].length; j++) {
				gridPopulation[i][j].setEnabled(false);
				gridPopulation[i][j].isNotNeighbor();
				gridPopulation[i][j].isNotCurrent();
			}
		}
		
		for (int i = 0; i < gridPopulation.length; i++) {
			for (int j = 0; j < gridPopulation[i].length; j++) {
				if (gridPopulation[i][j].getClickedStatus() == true) {
					gridPopulation[i][j].isCurrent();
					current = new Point(j,i);
					System.out.println("Current: " + current);
				}
			}
		}
		
		for (int i = 0; i < gridPopulation.length; i++) {
			for (int j = 0; j < gridPopulation[i].length; j++) {
				gridPopulation[i][j].isNotClicked();
				}
		}
		
		determineNeighbor();
		enableNeighbor();
		
		}

	    
	/**
     * Performs action when timer event fires.
     */
	private class TimerActionListener implements ActionListener
{
	public void actionPerformed(ActionEvent evt)
	{
		currentButton = gridPopulation[current.y][current.x];
		if (currentButton.getText().equals("")) { 
			currentButton.setText("X");
		} else {
			currentButton.setText("");
		}
		for (int i = 0; i < gridPopulation.length; i++) {
			for (int j = 0; j < gridPopulation[i].length; j++) {
				if (gridPopulation[i][j].getCurrentStatus() == false) {
				gridPopulation[i][j].setText("");
				}
			}
		}
	}
}

   /**
    * Create an animation thread that runs periodically
    */
    private void startAnimation()
    {
	    TimerActionListener taskPerformer = new TimerActionListener();
	    new Timer(DELAY, taskPerformer).start();
    }
	
}	
	