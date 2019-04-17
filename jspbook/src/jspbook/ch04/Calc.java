package jspbook.ch04;

public class Calc {
	//실제 계산 기능을 수행하는 메서드
	public int calculate(int num1, int num2, String op) {
		int result = 0;
		
		switch(op) {
		case "+":
			result = num1 + num2; break;
		case "-":
			result = num1 - num2; break;
		case "*":
			result = num1 * num2; break;
		case "/":
			result = num1 / num2; break;
		}
		return result;
	}
}
