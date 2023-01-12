import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Square{
	
	public int x;
	public int y;
	public int text;
	boolean isClicked;
	public char[][] notesText;
	public ArrayList<Integer> allowed;
	public char type;
	public ArrayList<Square> row;
	public ArrayList<Square> column;
	public ArrayList<Square> box;
	public boolean related;
	
	public Square(int x, int y) {
		this.allowed = new ArrayList<Integer>();
		for (int i = 1; i <= 9; i++) {
			allowed.add(i);
		}
		this.related = false;
		this.notesText = new char[3][3];
		this.text = 0;
		this.isClicked = false;
		this.x = x;
		this.y = y;
		this.type = 'e';
	}
	
	public String toString() {
		String str = "";
		str += "row: " + (x / 90 + 1); 
		str += " column: " + (y/90 + 1);
		str += " text: " + text;
		str += " type: " + this.type;
		str += " allowed: ";
		for (int i: this.allowed) {
			str += i + " ";
		}
		str += "\n";
		return str;
	}
	
	public Square(int x, int y, int t) {
		this.allowed = new ArrayList<Integer>();
		for (int i = 1; i < 9; i++) {
			allowed.add(i);
		}
		this.notesText = new char[3][3];
		this.text = t;
		this.isClicked = false;
		this.x = x;
		this.y = y;
		this.type = 'f';
	}
	
	public void autoNotesText() {
		this.resetNotes();
		for (int i = 0; i < this.allowed.size(); i++) {
			int j = this.allowed.get(i);
			if (j == 1) {
				this.notesText[0][0] = '1';
			}
			if (j == 2) {
				this.notesText[0][1] = '2';
			}
			if (j == 3) {
				this.notesText[0][2] = '3';
			}
			if (j == 4) {
				this.notesText[1][0] = '4';
			}
			if (j == 5) {
				this.notesText[1][1] = '5';
			}
			if (j == 6) {
				this.notesText[1][2] = '6';
			}
			if (j == 7) {
				this.notesText[2][0] = '7';
			}
			if (j == 8) {
				this.notesText[2][1] = '8';
			}
			if (j == 9) {
				this.notesText[2][2] = '9';
			}
		}
	}
	
	public void resetNotes() {
		this.notesText = new char[3][3];
	}
	
	public boolean fillIt() {
		if (this.type != 'e') {
			return false;
		}
		if (this.allowed.size() == 1) {
			this.text = this.allowed.get(0);
			for (int i = 1; i <= 9; i++) {
				allowed.add(i);
			}
			return true;
		}
		return false;
	}
	
	public void updateAllowed() {
		this.allowed = new ArrayList<Integer>();
		for (int i = 1; i <= 9; i++) {
			allowed.add(i);
		}
		for (Square s: this.box) {
			if (allowed.contains(s.text) && !s.equals(this)) {
				allowed.remove(allowed.indexOf(s.text));
			}
		}
		for (Square s: this.row) {
			if (allowed.contains(s.text) && !s.equals(this)) {
				allowed.remove(allowed.indexOf(s.text));
			}
		}
		for (Square s: this.column) {
			if (allowed.contains(s.text) && !s.equals(this)) {
				allowed.remove(allowed.indexOf(s.text));
			}
		}
	}


	public void drawOn(Graphics g) {
		if (this.isClicked) {
			g.setColor(Color.GREEN); 
		}
		else if (this.related) {
			g.setColor(Color.yellow);
			this.related = false;
		}
		else {
			g.setColor(Color.white);
		}
		g.fillRect(x, y, 90, 90);
		g.setColor(Color.black);
		g.fillRect(x, y, 90, 3);
		g.fillRect(x, y, 3, 90);
		if (this.type == 'f') {
			g.setFont(new Font("Arial", Font.PLAIN, 60));
			g.drawString("" + text, x + 30, y + 60);
			return;
		}
		g.setColor(Color.blue);
		if (this.text == 0) {
			g.setFont(new Font("Arial", Font.PLAIN, 15));
			int r = 0;
			int c = 0;
			while (r < 3) {
				while (c < 3) {
					if (notesText[r][c] != '\0') {
						String str = "" + notesText[r][c];
						g.drawString(str, x + 15 + 30* c, y + 18 + 30*r);
					}
					c += 1;
				}
				c = 0;
				r += 1;
			}
			return;
			
		}
		if (!this.allowed.contains(this.text)) {
			g.setColor(Color.RED);
		}
		else {
			g.setColor(Color.blue);
		}
		g.setFont(new Font("Arial", Font.PLAIN, 60));
		g.drawString("" + text, x + 30, y + 60);
	}

	public boolean onlyOneBox() {
		HashMap<Integer, ArrayList<Square>> visited = new HashMap<Integer, ArrayList<Square>>();
		for (int i = 1; i < 10; i++) {
			visited.put(i, new ArrayList<Square>());
		}
		for (Square s2: this.box) {
			for (int i: s2.allowed) {
				ArrayList<Square> list = visited.get(i);
				list.add(s2);
			}
		}
		for (int i: visited.keySet()) {
			if (visited.get(i).size() == 1 && visited.get(i).get(0).type == 'e' && visited.get(i).get(0).text == 0) {
				Square s2 = visited.get(i).get(0);
				s2.text = i;
				return true;
			}
				
		}
		return false;
	}

	public boolean onlyOneRow() {
		HashMap<Integer, ArrayList<Square>> visited = new HashMap<Integer, ArrayList<Square>>();
		for (int i = 1; i < 10; i++) {
			visited.put(i, new ArrayList<Square>());
		}
		for (Square s2: this.row) {
			for (int i: s2.allowed) {
				ArrayList<Square> list = visited.get(i);
				list.add(s2);
			}
		}

		for (int i: visited.keySet()) {
			if (visited.get(i).size() == 1 && visited.get(i).get(0).type == 'e' && visited.get(i).get(0).text == 0) {
				Square s2 = visited.get(i).get(0);
				s2.text = i;
				return true;
			}
				
		}
		return false;
	}

	public boolean onlyOneColumn() {
		HashMap<Integer, ArrayList<Square>> visited = new HashMap<Integer, ArrayList<Square>>();
		for (int i = 1; i < 10; i++) {
			visited.put(i, new ArrayList<Square>());
		}
		for (Square s2: this.column) {
			for (int i: s2.allowed) {
				ArrayList<Square> list = visited.get(i);
				list.add(s2);
			}
		}
		for (int i: visited.keySet()) {
			if (visited.get(i).size() == 1 && visited.get(i).get(0).type == 'e' && visited.get(i).get(0).text == 0) {
				Square s2 = visited.get(i).get(0);
				s2.text = i;
				return true;
			}
				
		}
		return false;
	}

}

