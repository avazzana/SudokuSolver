import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BuzzfeedQuiz {
	public HashMap<Integer, String> codes;
	public int current;
	public ArrayList<String> questions;
	public ArrayList<String> answers;
	public int code;
	public Scanner sc;
	
	public BuzzfeedQuiz() {
		sc = new Scanner(System.in);
		codes = new HashMap<Integer, String>();
		questions = new ArrayList<String>();
		answers = new ArrayList<String>();
		codes.put(10, "Blumberg Hall");
		codes.put(20, "BSB Hall");
		codes.put(30, "Deming Hall");
		codes.put(40, "Mees Hall");
		codes.put(50, "Scharpenburg Hall");
		codes.put(60, "Speed Hall");
		questions.add("First off, how are you getting to campus for move in day?");
		answers.add("A = my parents are driving me\nB = My older sibling starting junior year here is taking me\nC = I'll drive myself\nD = I'll fly into Indy\nE = I'll fly into Chicago\nF = I've been here for a month already because of soccer");
		questions.add("Pick a major");
		answers.add("A = ECE\nB = BE\nC = ME\nD = ChemE\nE = CSSE\nF = Physics, Bio, Math, one of the obscure majors that doesn't have the word 'engineering' in it");
		questions.add("Pick an extracurricular");
		answers.add("A = Drama club (which everyone should join)\nB = club/intramural sports\nC = Greek Life\nD = SHPE/NSBE/SWE/SASE/SASA/Unity (which everyone should join at least one of at Rose)\nE = I'm going to try to join like 15 of them\nF = I have no intentions of ever leaving my room except for class");
		questions.add("Pick a programming language");
		answers.add("A = Python\nB = Java\nC = JavaScript or HTML\nD = Does Matlab count?\nE = Does Maple count?\nF = Please don't ever make me program");
		questions.add("It's Friday night. Where are you?");
		answers.add("A = a frat party\nB = Hours into a D&D session\nC = Studying, duh\nD = Hatfield, for rehearsal\nE = At Chava's eating burritos with my friends who are all drunk from frat parties\nF = Wasting time on Reddit and YouTube");
		questions.add("What's you go-to study spot?");
		answers.add("A = Main living room in the union\nB = Logan Library\nC = Academic buildings\nD = Green room at Hatfield\nE = RA/SA's room\nF = my own room");
		questions.add("What are you getting from Chauncey's?");
		answers.add("A = Power bowl(does anyone ever eat that?)\nB = Flatbread pizza\nC = Burger\nD = Quesadilla\nE = Loaded fries\nF = vegan special");
		questions.add("Speaking of food, it's 1 am and you're starving, but Chauncey's is closed. Where are you going to get food?");
		answers.add("A = Steak n Shake\nB = Sonic\nC = IHOP\nD = Culver's\nE = I'll order Chinese\nF = I'll just make ramen in my microwave");
		questions.add("You're starting to have trouble keeping up with your coursework. Where do you go to find help?");
		answers.add("A = Google\nB = the Learning Center\nC = the CS lounge\nD = I'll ask my friend who took this class last year\nE = Percopo Tutors\nF = Faculty Office hours\n(Hint: all of these are helpful resources that you should definitely use)");
		questions.add("Next quarter you get to pick your own schedule. What's your game plan?");
		answers.add("A = block 8am to noon\nB = Anything that avoids back-to-back classes\nC = class, power nap, more class\nD = I refuse to get up before noon\nE = I don't care as long as I have a weekend wednesday\nF = Whatever the registrar can get me into. I overslept registration");
		current = 0;
		code = 0;
	}
	
	public String doStuff() {
		while (current < questions.size()) {
			System.out.println(questions.get(current));
			System.out.println(answers.get(current));
			String s = sc.next();
			if (s.equals("a")) {
				code += 0;
				current += 1;
			}
			else if (s.equals("b")) {
				code += 1;
				current += 1;
			}
			else if (s.equals("c")) {
				code += 2;
				current += 1;
			}
			else if (s.equals("d")) {
				code += 3;
				current += 1;
			}
			else if (s.equals("e")) {
				code += 4;
				current += 1;
			}
			else if (s.equals("f")) {
				code += 5;
				current += 1;
			}
			else {
				System.out.println("Invalid key. Try again");
			}
		}
		for (int i: codes.keySet()) {
			if (i - code < 10) {
				return "You should live in " + codes.get(i);
			}
		}
		return "sorry, we can't seem to find a match for you";

		
	}
	public static void main(String[] args) {
		System.out.println("Disclaimer: the answers are completely random and have nothing to do with actual data or even stereotypes");
		System.out.println("Welcome to Rose");
		System.out.println("Before you move in, you'll need to decide which res hall to live in");
		System.out.println("Answer the following questions to decide which res hall is right for you");
		System.out.println("For each question, enter the letter corresponding to the answer that best suits you");
		System.out.println("Let's begin");
		BuzzfeedQuiz b = new BuzzfeedQuiz();
		System.out.println(b.doStuff());
		Scanner sc = new Scanner(System.in);
		System.out.println("press q to quit, or press any other key to play again");
		while(sc.hasNext()) {
			String s = sc.next();
			if (s.equals("q")) {
				System.out.println("thanks for playing");
				sc.close();
				return;
			}
			else {
				sc.close();
				b = new BuzzfeedQuiz();
				System.out.println(b.doStuff());
				sc = new Scanner(System.in);
				System.out.println("press q to quit, or press any other key to play again");
			}
		}
	}

}
