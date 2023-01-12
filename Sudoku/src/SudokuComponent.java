import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JComponent;

public class SudokuComponent extends JComponent {
	
	public Square[][] squares;
	public boolean notesMode;
	public ArrayList<ArrayList<Square>> boxes;
	public HashMap<Square, ArrayList<Square>> squareToBox;
	public ArrayList<ArrayList<Square>> rows;
	public HashMap<Square, ArrayList<Square>> squareToRow;
	public ArrayList<ArrayList<Square>> columns;
	public boolean displayAllowed;
	public HashMap<Square, ArrayList<Square>> squareToColumn;
	
	public SudokuComponent(String fileName) {
		this.boxes = new ArrayList<ArrayList<Square>>();
		this.squareToBox = new HashMap<Square, ArrayList<Square>>();
		this.rows = new ArrayList<ArrayList<Square>>();
		this.squareToRow = new HashMap<Square, ArrayList<Square>>();
		this.columns = new ArrayList<ArrayList<Square>>();
		this.squareToColumn = new HashMap<Square, ArrayList<Square>>();
		this.displayAllowed = false;
		this.squares = new Square[9][9];
		int c = 0;
		Scanner scan = null;
		try {
			FileReader file = new FileReader(fileName);
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace(); 
		}
		
		while(scan.hasNext() && c < 9) {
			String current = scan.next();
			for (int r = 0; r < 9; r++) {
				if (current.charAt(r) == 'x') {
					this.squares[c][r] = new Square(90 * r, 90 * c);
				}
				else {
					int t = current.charAt(r) - 48;
					this.squares[c][r] = new Square(90 * r, 90 * c, t);
				}
			}
			c++;
		}
		ArrayList<Square> row = new ArrayList<Square>();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				row.add(squares[i][j]);
			}
			for (Square s: row) {
				squareToRow.put(s, row);
				s.row = row;
			}
			row = new ArrayList<Square>();
		}
		ArrayList<Square> column = new ArrayList<Square>();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				column.add(squares[j][i]);
			}
			for (Square s: column) {
				squareToColumn.put(s, column);
				s.column = column;
			}
			column = new ArrayList<Square>();
		}
		ArrayList<Square> box = new ArrayList<Square>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				box.add(squares[3* i][3 * j]);
				box.add(squares[3* i][3 * j + 1]);
				box.add(squares[3* i][3 * j + 2]);
				box.add(squares[3* i + 1][3 * j]);
				box.add(squares[3* i + 1][3 * j + 1]);
				box.add(squares[3* i + 1][3 * j + 2]);
				box.add(squares[3* i + 2][3 * j]);
				box.add(squares[3* i + 2][3 * j + 1]);
				box.add(squares[3* i + 2][3 * j + 2]);
				for (Square s: box) {
					squareToBox.put(s, box);
					s.box = box;
				}
				box = new ArrayList<Square>();
			}
		}
		
//		for (Square s: squareToBox.keySet()) {
//			System.out.println(s.toString());
//			String squareString = "";
//			for (Square s2: squareToBox.get(s)) {
//				squareString += s2.toString();
//			}
//			System.out.println("other squares: " + squareString);
//		}
		this.checkUpdateValidity();
	}
	
	public void updateDisplayStatus() {
		if (this.displayAllowed == true) {
			this.displayAllowed = false;
			for (Square[] s: this.squares) {
				for (Square s2: s) {
					s2.resetNotes();
				}
			}
		}
		else {
			this.displayAllowed = true;
			for (Square[] s: this.squares) {
				for (Square s2: s) {
					s2.autoNotesText();
				}
			}
		}
	}
	
	public void paintComponent(Graphics g2) {
		if (isComplete()) {
			for (Square[] s: this.squares) {
				for (Square s2: s) {
					s2.isClicked = true;
					s2.drawOn(g2);
				}
			}
			g2.setColor(Color.black);
			g2.fillRect(0, 0, 5, 815);
			g2.fillRect(0, 0, 815, 5);
			g2.fillRect(0, 810, 815, 5);
			g2.fillRect(810, 0, 5, 815);
			g2.fillRect(0, 270, 815, 5);
			g2.fillRect(0, 540, 815, 5);
			g2.fillRect(270, 0, 5, 815);
			g2.fillRect(540, 0, 5, 815);
		}
		for (Square[] s: this.squares) {
			for (Square s2: s) {
				s2.drawOn(g2);
			}
		}
		g2.setColor(Color.black);
		g2.fillRect(0, 0, 5, 815);
		g2.fillRect(0, 0, 815, 5);
		g2.fillRect(0, 810, 815, 5);
		g2.fillRect(810, 0, 5, 815);
		g2.fillRect(0, 270, 815, 5);
		g2.fillRect(0, 540, 815, 5);
		g2.fillRect(270, 0, 5, 815);
		g2.fillRect(540, 0, 5, 815);
		
	}
	
	public void checkUpdateValidity() {
		ArrayList<Integer> visited = new ArrayList<Integer>();

		for (Square[] s: this.squares) {
			for (Square s2: s) {
				ArrayList<Square> list = this.squareToBox.get(s2);
				for (Square s3: list) {
					if (!visited.contains(s3.text) && !s2.equals(s3)) {
						visited.add(s3.text);
					}
					if (s2.allowed.contains(s3.text) && !s2.equals(s3)) {
						s2.allowed.remove(s2.allowed.indexOf(s3.text));
					}
				}
				list = this.squareToColumn.get(s2);
				for (Square s3: list) {
					if (!visited.contains(s3.text) && !s2.equals(s3)) {
						visited.add(s3.text);
					}
					if (s2.allowed.contains(s3.text) && !s2.equals(s3)) {
						s2.allowed.remove(s2.allowed.indexOf(s3.text));
					}
				}
				list = this.squareToRow.get(s2);
				for (Square s3: list) {
					if (!visited.contains(s3.text) && !s2.equals(s3)) {
						visited.add(s3.text);
					}
					if (s2.allowed.contains(s3.text) && !s2.equals(s3)) {
						s2.allowed.remove(s2.allowed.indexOf(s3.text));
					}
				}
				for (int i = 1; i < 10; i++) {
					if (!visited.contains(i) && !s2.allowed.contains(i)) {
						s2.allowed.add(i);
					}
				}
				
			}
		}
		if (displayAllowed) {
			for (Square[] s: this.squares) {
				for (Square s2: s) {
					s2.autoNotesText();
				}
			}
		}
	}
	
	public String getSquareStuff(Square s) {
		ArrayList<Integer> visited = new ArrayList<Integer>();
		String str = "box:\n";
		for (Square s2: squareToBox.get(s)) {
			if (!visited.contains(s2.text)) {
				visited.add(s2.text);
			}
			str += s2.toString();
		}
		str += "row:\n";
		for (Square s2: squareToRow.get(s)) {
			if (!visited.contains(s2.text)) {
				visited.add(s2.text);
			}
			str += s2.toString();
		}
		str += "column:\n";
		for (Square s2: squareToColumn.get(s)) {
			if (!visited.contains(s2.text)) {
				visited.add(s2.text);
			}
			str += s2.toString();
		}
		
		str += "visited: ";
		for (int i: visited) {
			str += i + " ";
		}
		this.repaint();
		return str;
	}
	
	public boolean isComplete() {
		for (Square[] s: this.squares) {
			for (Square s2: s) {
				if (s2.text == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void nextMove() {
		if (onlyOne()) {
			return;
		}
		if (oneAllowed()) {
			return;
		}
	}
	
	public boolean onlyOne() {
		ArrayList<ArrayList<Square>> visited = new ArrayList<ArrayList<Square>>();
		for (Square s2: this.squareToBox.keySet()) {
			if (!visited.contains(squareToBox.get(s2))){
				if (s2.onlyOneBox()) {
					this.checkUpdateValidity();
					this.repaint();
					return true;
				}
				else if (s2.onlyOneRow()) {
					this.checkUpdateValidity();
					this.repaint();
					return true;
				}
				else if (s2.onlyOneColumn()) {
					this.checkUpdateValidity();
					this.repaint();
					return true;
				}
				visited.add(squareToBox.get(s2));
			}
		}
		return false;
	}

	public boolean oneAllowed() {
		for (Square[] s: this.squares) {
			for (Square s2: s) {
				if (s2.fillIt()) {
					this.checkUpdateValidity();
					this.repaint();
					return true;
				}
			}
		}
		return false;
	}
	


}
