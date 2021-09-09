
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

//import LiteBritePanel.LitePegListener;
//import LiteBritePanel.ResetButtonListener;

/**
 * 
 * @author joelkoehler
 * 
 * Makes a JPanel and populates it with panels and buttons 
 * 
 */

public class MineWalkerPanel extends JPanel {

	private JButton showPathButton;
	private JButton showMinesButton;
	private JButton giveUpButton;
	private MineFieldPanel mineField;
	private boolean showPathToggle;
	private boolean showMinesToggle;
	private boolean giveUpToggle;
	private JTextField gridEntry;
	private int gridSize;
	private int livesCounter;
	private int scoreCounter;
	private JLabel livesLabel;
	private JLabel scoreLabel;

	/**
	 * Constructor that makes a grid panel
	 */
	
	public MineWalkerPanel() {

		gridSize = 10;
		livesCounter = 5;
		scoreCounter = 0;

		setLayout(new BorderLayout());

		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));

		Color colors[] = MineFieldButton.COLORS;
		for (int i = 0; i < 4; i++) {

			JLabel label = new JLabel(i + " Nearby Mines");
			label.setBackground(colors[i]);
			label.setOpaque(true);
			sidePanel.add(label);

		}

		JLabel explodedMineLabel = new JLabel("Exploded Mine");
		explodedMineLabel.setBackground(colors[4]);
		explodedMineLabel.setForeground(Color.WHITE);
		explodedMineLabel.setOpaque(true);
		sidePanel.add(explodedMineLabel);

		JLabel destinationLabel = new JLabel("Destination");
		destinationLabel.setBackground(colors[5]);
		destinationLabel.setOpaque(true);
		sidePanel.add(destinationLabel);

		JLabel startLabel = new JLabel("Start");
		startLabel.setBackground(colors[6]);
		startLabel.setOpaque(true);
		sidePanel.add(startLabel);
		sidePanel.add(Box.createRigidArea(new Dimension(20, 0)));
		sidePanel.add(Box.createVerticalGlue());

		showPathToggle = true;
		showPathButton = new JButton("Show Path");
		sidePanel.add(showPathButton);
		showPathButton.addActionListener(new ShowPathButtonListener());

		showMinesToggle = true;
		showMinesButton = new JButton("Show Mines");
		sidePanel.add(showMinesButton);
		showMinesButton.addActionListener(new ShowMinesButtonListener());

		giveUpToggle = true;
		giveUpButton = new JButton("Give Up");
		sidePanel.add(giveUpButton);
		giveUpButton.addActionListener(new GiveUpButtonListener());

		gridEntry = new JTextField(5);
		gridEntry.setMaximumSize(gridEntry.getPreferredSize());

		JLabel sizeLabel = new JLabel("Size");
		sidePanel.add(new JLabel("Size"));
		sidePanel.add(gridEntry);
		gridEntry.setText(Integer.toString(gridSize));

		mineField = new MineFieldPanel(new GridListener(), gridSize, gridSize);
		mineField.determineNeighbor();
		mineField.enableNeighbor();

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		livesLabel = new JLabel("Lives: " + livesCounter);
		bottomPanel.add(Box.createHorizontalGlue());
		bottomPanel.add(livesLabel);
		bottomPanel.add(Box.createHorizontalGlue());
		scoreLabel = new JLabel("Score: " + scoreCounter);
		bottomPanel.add(Box.createHorizontalGlue());
		bottomPanel.add(scoreLabel);
		bottomPanel.add(Box.createHorizontalGlue());

		add(sidePanel, BorderLayout.WEST);
		add(mineField, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

	}

	/**
	 * 
	 * @author joelkoehler
	 * Button listener for the show path button
	 */
	
	private class ShowPathButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (showPathToggle == true) {
				mineField.showPath();
				showPathButton.setText("Hide Path");
				showPathToggle = false;
			} else if (showPathToggle == false) {
				mineField.hidePath();
				showPathButton.setText("Show Path");
				showPathToggle = true;
			}
		}
	}

	/**
	 * 
	 * @author joelkoehler
	 * Button listener for the show mines button
	 */
	
	private class ShowMinesButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (showMinesToggle == true) {
				mineField.showMines();
				showMinesButton.setText(" Hide Mines ");
				showMinesToggle = false;
			} else if (showMinesToggle == false) {
				mineField.hideMines();
				showMinesButton.setText("Show Mines");
				showMinesToggle = true;
			}
		}
	}

	/**
	 * 
	 * @author joelkoehler
	 * Button listener for the give up/ new game button
	 */
	
	private class GiveUpButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (giveUpToggle == true) {
				JOptionPane.showMessageDialog(null,
						"You're bad. \nLives: " + livesCounter + "\nScore: " + scoreCounter);
				mineField.showMines();
				mineField.disableAllButtons();
				giveUpButton.setText("New Game");
				giveUpToggle = false;

			} else if (giveUpToggle == false) {
				giveUpButton.setText("Give Up");
				giveUpToggle = true;
				configureGridPanel();
				livesCounter = 5;
				livesLabel.setText("Lives: " + livesCounter);
				scoreCounter = 0;
				scoreLabel.setText("Score: " + scoreCounter);
				showPathToggle = true;
				mineField.hidePath();
				showPathButton.setText("Show Path");
				showMinesToggle = true;
				mineField.hideMines();
				showMinesButton.setText("Show Mines");
			}
		}
	}

	/**
	 * 
	 * @author joelkoehler
	 * Button listener for each button on the grid
	 */
	
	private class GridListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			MineFieldButton clicked = (MineFieldButton) (e.getSource());
			clicked.isClicked();
			clicked.isCurrent();
			if (clicked.getMineStatus() == false) {
				clicked.setBackground(Color.GREEN);
				scoreCounter += 10;
				scoreLabel.setText("Score: " + scoreCounter);
			} 
			
			if (clicked.getMineStatus() == true) {
				clicked.setBackground(Color.BLACK);
				clicked.mineExploded();
				if (livesCounter > 0) {
					livesCounter--;
					livesLabel.setText("Lives: " + livesCounter);
					if (livesCounter == 0) {
						JOptionPane.showMessageDialog(null,
								"You're bad. \nLives: " + livesCounter + "\nScore: " + scoreCounter);
						mineField.disableAllButtons();
						mineField.showMines();
						giveUpButton.setText("New Game");
						giveUpToggle = false;
					}
				}

			}
			
			if (clicked.getDestinationStatus() == true) {
				mineField.disableAllButtons();
				JOptionPane.showMessageDialog(null, "You won. \nLives: " + livesCounter + "\nScore: " + scoreCounter);
				giveUpButton.setText("New Game");
				giveUpToggle = false;
			}
			mineField.updateCurrent();
		}
	}

	
	/**
	 * 
	 * resets board
	 */
	
	private void configureGridPanel() {
		int oldSize = gridSize; // store in case invalid value in gridDimText

		try {
			if (gridEntry.getText() == null) {
				if (mineField != null) {
					remove(mineField);
				}
				mineField = new MineFieldPanel(new GridListener(), gridSize, gridSize);
				mineField.setLayout(new GridLayout(gridSize, gridSize));
				mineField.setPreferredSize(new Dimension(gridSize, gridSize));
				mineField.determineNeighbor();
				mineField.enableNeighbor();

				// ** IMPORTANT: must add the new panel to the main panel
				add(mineField, BorderLayout.CENTER);

				// ** IMPORTANT: forces panel to refresh itself with new contents
				revalidate();
			}

			gridSize = Integer.parseInt(gridEntry.getText().trim());

			if (gridSize < 1) { // invalid input
				// reset gridDim and text field to previous values
				gridSize = oldSize;
				gridEntry.setText(Integer.toString(gridSize));
			} else { // valid input, so configure a new gridPanel

				// ** IMPORTANT: If we already had a grid created, we must remove it before
				// adding the new one. Otherwise, it will be under our new panel.
				if (mineField != null) {
					remove(mineField);
				}

				// re-create the entire grid panel.
				mineField = new MineFieldPanel(new GridListener(), gridSize, gridSize);
				mineField.setLayout(new GridLayout(gridSize, gridSize));
				mineField.setPreferredSize(new Dimension(gridSize, gridSize));
				mineField.determineNeighbor();
				mineField.enableNeighbor();

				// ** IMPORTANT: must add the new panel to the main panel
				add(mineField, BorderLayout.CENTER);

				// ** IMPORTANT: forces panel to refresh itself with new contents
				revalidate();
			}
		} catch (NumberFormatException nfe) { // invalid input in text field
			// reset gridDim and text field to previous values
			gridSize = oldSize;
			gridEntry.setText(Integer.toString(gridSize));
		}
	}
}
