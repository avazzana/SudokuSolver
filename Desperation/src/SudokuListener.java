import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SudokuListener implements KeyListener, MouseListener{
	SudokuComponent sc;
	int cr;
	int cc;
	
	public SudokuListener(SudokuComponent sc) {
		this.sc = sc;
		this.cr = 0;
		this.cc = 0;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int r = -1;
		int c = -1;
		while (x > 0) {
			c += 1;
			x -= 90;
		}
		while (y > 0) {
			r += 1;
			y -= 90;
		}
		sc.squares[cr][cc].isClicked = false;
		for (Square s: sc.squares[cr][cc].row) {
			s.related = false;
		}
		for (Square s: sc.squares[cr][cc].column) {
			s.related = false;
		}
		for (Square s: sc.squares[cr][cc].column) {
			s.related = false;
		}
		if (sc.squares[r][c].type == 'e') {
			sc.squares[r][c].isClicked = true;
			cr = r;
			cc = c;
			for (Square s: sc.squares[cr][cc].row) {
				s.related = true;
			}
			for (Square s: sc.squares[cr][cc].column) {
				s.related = true;
			}
			for (Square s: sc.squares[cr][cc].box) {
				s.related = true;
			}
		}
		sc.repaint();
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

		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyChar() == 'n') {
			if (sc.notesMode) {
				sc.notesMode = false;
				return;
			}
			sc.notesMode = true;
			return;
		}
		if (arg0.getKeyChar() == 'c') {
			sc.checkUpdateValidity();
			sc.updateDisplayStatus();
			sc.repaint();
			return;
		}
		Square s = sc.squares[cr][cc];
		if (arg0.getKeyChar() == 'h') {
			sc.nextMove();
			sc.repaint();
			return;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_UP && cr > 0) {
			sc.squares[cr][cc].isClicked = false;
			cr -= 1;
			sc.squares[cr][cc].isClicked = true;
			for (Square s2: sc.squares[cr][cc].row) {
				s2.related = true;
			}
			for (Square s2: sc.squares[cr][cc].column) {
				s2.related = true;
			}
			for (Square s2: sc.squares[cr][cc].box) {
				s2.related = true;
			}
			sc.repaint();
			return;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_DOWN && cr < 8) {
			sc.squares[cr][cc].isClicked = false;
			cr += 1;
			sc.squares[cr][cc].isClicked = true;
			for (Square s2: sc.squares[cr][cc].row) {
				s2.related = true;
			}
			for (Square s2: sc.squares[cr][cc].column) {
				s2.related = true;
			}
			for (Square s2: sc.squares[cr][cc].box) {
				s2.related = true;
			}
			sc.repaint();
			return;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT && cc > 0) {
			sc.squares[cr][cc].isClicked = false;
			cc -= 1;
			sc.squares[cr][cc].isClicked = true;
			for (Square s2: sc.squares[cr][cc].row) {
				s2.related = true;
			}
			for (Square s2: sc.squares[cr][cc].column) {
				s2.related = true;
			}
			for (Square s2: sc.squares[cr][cc].box) {
				s2.related = true;
			}
			sc.repaint();
			return;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT && cc < 8) {
			sc.squares[cr][cc].isClicked = false;
			cc += 1;
			sc.squares[cr][cc].isClicked = true;
			for (Square s2: sc.squares[cr][cc].row) {
				s2.related = true;
			}
			for (Square s2: sc.squares[cr][cc].column) {
				s2.related = true;
			}
			for (Square s2: sc.squares[cr][cc].box) {
				s2.related = true;
			}
			sc.repaint();
			return;
		}
		if (s.type != 'e') {
			return;
		}
		if (arg0.getKeyChar() == '1') {
			if (sc.notesMode) {
				if (s.notesText[0][0] == '1') {
					s.notesText[0][0] = '\0';
					sc.repaint();
					return;
				}
				s.notesText[0][0] = '1';
				sc.repaint();
				return;
			}
			s.text = 1;
			sc.checkUpdateValidity();
			sc.repaint();
			return;
		}
		if (arg0.getKeyChar() == '2') {
			if (sc.notesMode) {
				if (s.notesText[0][1] == '2') {
					s.notesText[0][1] = '\0';
					sc.repaint();
					return;
				}
				s.notesText[0][1] = '2';
				sc.repaint();
				return;
			}
			s.text = 2;
			sc.checkUpdateValidity();
			sc.repaint();
			return;
		}
		if (arg0.getKeyChar() == '3') {
			if (sc.notesMode) {
				if (s.notesText[0][2] == '3') {
					s.notesText[0][2] = '\0';
					sc.repaint();
					return;
				}
				s.notesText[0][2] = '3';
				sc.repaint();
				return;
			}
			s.text = 3;
			sc.checkUpdateValidity();
			sc.repaint();
			return;
		}
		if (arg0.getKeyChar() == '4') {
			if (sc.notesMode) {
				if (s.notesText[1][0] == '4') {
					s.notesText[1][0] = '\0';
					sc.repaint();
					return;
				}
				s.notesText[1][0] = '4';
				sc.repaint();
				return;
			}
			s.text = 4;
			sc.checkUpdateValidity();
			sc.repaint();
			return;
		}
		if (arg0.getKeyChar() == '5') {
			if (sc.notesMode) {
				if (s.notesText[1][1] == '5') {
					s.notesText[1][1] = '\0';
					sc.repaint();
					return;
				}
				s.notesText[1][1] = '5';
				sc.repaint();
				return;
			}
			s.text = 5;
			sc.checkUpdateValidity();
			sc.repaint();
			return;
		}
		if (arg0.getKeyChar() == '6') {
			if (sc.notesMode) {
				if (s.notesText[1][2] == '6') {
					s.notesText[1][2] = '\0';
					sc.repaint();
					return;
				}
				s.notesText[1][2] = '6';
				sc.repaint();
				return;
			}
			s.text = 6;
			sc.checkUpdateValidity();
			sc.repaint();
			return;
		}
		if (arg0.getKeyChar() == '7') {
			if (sc.notesMode) {
				if (s.notesText[2][0] == '7') {
					s.notesText[2][0] = '\0';
					sc.repaint();
					return;
				}
				s.notesText[2][0] = '7';
				sc.repaint();
				return;
			}
			s.text = 7;
			sc.checkUpdateValidity();
			sc.repaint();
			return;
		}
		if (arg0.getKeyChar() == '8') {
			if (sc.notesMode) {
				if (s.notesText[2][1] == '8') {
					s.notesText[2][1] = '\0';
					sc.repaint();
					return;
				}
				s.notesText[2][1] = '8';
				sc.repaint();
				return;
			}
			s.text = 8;
			sc.checkUpdateValidity();
			sc.repaint();
			return;
		}
		if (arg0.getKeyChar() == '9') {
			if (sc.notesMode) {
				if (s.notesText[2][2] == '9') {
					s.notesText[2][2] = '\0';
					sc.repaint();
					return;
				}
				s.notesText[2][2] = '9';
				sc.repaint();
				return;
			}
			s.text = 9;
			sc.checkUpdateValidity();
			sc.repaint();
			return;
		}
		s.text = 0;
		sc.checkUpdateValidity();
		sc.repaint();
		return;
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
