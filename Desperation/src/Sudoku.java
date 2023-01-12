import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Timer;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Sudoku{
	
	public void makeWindow() {
		JFrame startFrame = new JFrame();

		JLabel startScreen = new JLabel();
		startScreen.setIcon(new ImageIcon("C:/Users/vazzanam/git/Audracious/Desperation/SudokuMain.png"));
		startFrame.add(startScreen, BorderLayout.CENTER);
		
		// When pressed, will dispose of current frame and load the game
		JButton startButton = new JButton();
		startButton.setText("Start the Game");
		startFrame.add(startButton, BorderLayout.SOUTH);
		startFrame.setVisible(true);
		startFrame.pack();
		startFrame.setSize(900, 900);
		
		startButton.addMouseListener(new MouseListener() {


		@Override
	public void mouseClicked(MouseEvent arg0) {
		startFrame.dispose();
		
		JFrame frame = new JFrame("Sudoku");
		SudokuComponent sc = new SudokuComponent("C:/Users/vazzanam/git/Audracious/Desperation/puzzle44.txt");
		SudokuListener sl = new SudokuListener(sc);
		sc.addMouseListener(sl);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(sc);
		frame.addKeyListener(sl);
		frame.setVisible(true);
		frame.pack();
		frame.setSize(new Dimension(900, 900));
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}

		});
	}
	
	public static void main(String[] args) {
		Sudoku s = new Sudoku();
		s.makeWindow();
	}
	
	
}