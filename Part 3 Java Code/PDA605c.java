import java.util.Stack; //We need to import the Stack Library

//Eric Einhaus
//Part 3 of Computer Algorithms Project: PDA Representation in Java
//6-05c) L:{0^n1^m0^m1^n : n,m >= 1} 

public class PDA605c {
	//Global variables, i is an incrementor to help with substrings, 6 boolean values checking
	//if there is a pointer on each state, and a Stack.
	int i;
	boolean pointOnS;
	boolean pointOnA;
	boolean pointOnB;
	boolean pointOnC;
	boolean pointOnD;
	boolean pointOnE;
	Stack<Character> stack; //A stack containing characters
	
	//Constructor initializing the variables above
	public PDA605c() {
		i = 0;
		pointOnS = false;
		pointOnA = false;
		pointOnB = false;
		pointOnC = false;
		pointOnD = false;
		pointOnE = false;
		stack = new Stack<>();
	}
	
	//Start state
	private void S(String str) {
		pointOnS = true;
		if(!str.isEmpty()) {
			stack.push('D'); //I represent delta with a capital 'D'
			pointOnS = false;
			A(str);
		}
	}
	
	//State A
	private void A(String str) {
		pointOnA = true;
		if (!str.isEmpty()) {
			if (str.charAt(0) == '0') {
				stack.push('x');
				A(str.substring(i+1, str.length()));
			} else if(str.charAt(0) == '1') {
				stack.push('y');
				pointOnA = false;
				B(str.substring(i+1, str.length()));
			} else {
				pointOnA = false;
			}
		} 
	}
	
	//State B
	private void B(String str) {
		pointOnB = true;
		if (!str.isEmpty()) {
			if(str.charAt(0) == '1') {
				stack.push('y');
				B(str.substring(i+1, str.length()));
			} else if (str.charAt(0) == '0' && stack.peek() == 'y') {
				stack.pop();
				pointOnB = false;
				C(str.substring(i+1, str.length()));
			} else {
			pointOnB = false;
			}
		} 
	}
	
	//State C
	private void C(String str) {
		pointOnC = true;
		if (!str.isEmpty()) {
		if (str.charAt(0) == '0' && stack.peek() == 'y') {
			stack.pop();
			C(str.substring(i+1, str.length()));
		} else if (str.charAt(0) == '1' && stack.peek() == 'x') {
			stack.pop();
			pointOnC = false;
			D(str.substring(i+1, str.length()));
		} else {
			pointOnC = false;
		}
		} 
	}
	
	//State D
	private void D(String str) {
		//If String is empty and stack only contains delta
		if(str.isEmpty() && stack.peek() == 'D') { //&& == AND
			stack.pop();
			E(str);
		} else if (str.charAt(0) == '1' && stack.peek() == 'x') {
			stack.pop();
			D(str.substring(i+1, str.length()));
		} else {
			pointOnD = false;
		}
	}
	
	//State E (Acceptable state)
	private void E(String str) {
		pointOnE = true;
	}
	
	//Main function call, returns 'Acceptable!' or 'Rejectable'
	private String pdaResult(String str) {
		S(str);
		if (pointOnE && stack.isEmpty()) { 
			return "Acceptable!";
		} else {
			return "Rejectable";
		}
	}
	
	//Resets all variables to previous initialized values
	private void reset() {
		stack.clear(); //clears stack (if not empty)
		i = 0;
		pointOnS = false;
		pointOnA = false;
		pointOnB = false;
		pointOnC = false;
		pointOnD = false;
		pointOnE = false;
	}
	
	public static void main(String[] args) {
		//Acceptable Strings for the Language
		String t1 = "0101";
		String t2 = "011001";
		String t3 = "0001100111";
		String t4 = "0111111100000001";
		String t5 = "000011111000001111";
		//Rejectable Strings for the Language
		String t6 = "01001";
		String t7 = "001100111";
		String t8 = "011011";
		String t9 = "01";
		String t10 = "000011110000011111";
		String t11 = "";
		
		//Printing out the results here...
		PDA605c tester = new PDA605c();
		System.out.println("Test Case 1: " + t1 + "\n" + "PDA Result: " + tester.pdaResult(t1) + "\n");
		tester.reset();
		System.out.println("Test Case 2: " + t2 + "\n" + "PDA Result: " + tester.pdaResult(t2) + "\n");
		tester.reset();
		System.out.println("Test Case 3: " + t3 + "\n" + "PDA Result: " + tester.pdaResult(t3) + "\n");
		tester.reset();
		System.out.println("Test Case 4: " + t4 + "\n" + "PDA Result: " + tester.pdaResult(t4) + "\n");
		tester.reset();
		System.out.println("Test Case 5: " + t5 + "\n" + "PDA Result: " + tester.pdaResult(t5) + "\n");
		tester.reset();
		System.out.println("Test Case 6: " + t6 + "\n" + "PDA Result: " + tester.pdaResult(t6) + "\n");
		tester.reset();
		System.out.println("Test Case 7: " + t7 + "\n" + "PDA Result: " + tester.pdaResult(t7) + "\n");
		tester.reset();
		System.out.println("Test Case 8: " + t8 + "\n" + "PDA Result: " + tester.pdaResult(t8) + "\n");
		tester.reset();
		System.out.println("Test Case 9: " + t9 + "\n" + "PDA Result: " + tester.pdaResult(t9) + "\n");
		tester.reset();
		System.out.println("Test Case 10: " + t10 + "\n" + "PDA Result: " + tester.pdaResult(t10) + "\n");
		tester.reset();
		System.out.println("Test Case 11: " + "\"\"" + "\n" + "PDA Result: " + tester.pdaResult(t11) + "\n");
		tester.reset();
	}
}
