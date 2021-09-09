import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author joelkoehler
 * Driver for mine walker 
 */

public class MineWalker extends JPanel {

	public static void main(String[] args) {
		
		JPanel MineWalkerPanel = new JPanel();
		
		JFrame frame = new JFrame("MineWalker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new MineWalkerPanel());
		frame.pack();
		frame.setVisible(true);
		 
	}
	
}
