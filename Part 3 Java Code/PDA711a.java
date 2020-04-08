import java.util.Stack; //We need to import the Stack Library


//Eric Einhaus
//Part 3 of Computer Algorithms Project: PDA Representation in Java
//7-11a) L:{10^n10^n1}

public class PDA711a {
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
	public PDA711a() {
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
		stack.push('D'); //I represent delta with a capital 'D'
		A(str);
		pointOnS = false;
	}
	
	//State A
	private void A(String str) {
		pointOnA = true;
		if(!str.isEmpty()) { //A ! is a negator, so this checks if str is NOT empty
			if(str.charAt(0) == '1') {
				pointOnA = false;
				B(str.substring(i+1, str.length()));
			}
		}
		pointOnA = false;
	}
	
	//State B
	private void B(String str) {
		pointOnB = true;
		if (!str.isEmpty()) {
		if(str.charAt(0) == '0') {
			stack.push('0');
			B(str.substring(i+1, str.length()));
		} else if (str.charAt(0) == '1') {
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
		if(str.charAt(0) == '0' && stack.peek() == '0') {
			stack.pop();
			C(str.substring(i+1, str.length()));
		} else if (str.charAt(0) == '1') {
			pointOnB = false;
			D(str.substring(i+1, str.length()));
		} else {
			pointOnB = false;
		}
		}
	}
	
	//State D
	private void D(String str) {
		//If String is empty and stack only contains delta
		if(str.isEmpty() && stack.peek() == 'D') { //&& == AND
			stack.pop();
			E(str);
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
		String t1 = "111";
		String t2 = "10101";
		String t3 = "1000001000001";
		//Rejectable Strings for the Language
		String t4 = "100101";
		String t5 = "101001";
		String t6 = "1101";
		String t7 = "1111";
		String t8 = "01001001";
		String t9 = "101010";
		String t10 = "10000010000011";
		String t11 = "";
		
		//Printing out the results here...
		PDA711a tester = new PDA711a();
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
