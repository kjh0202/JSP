package member;

public class Calculator {
	public int add(int x, int y) {
		return x+y;
	}
	
	public int sub(int x, int y) {
		return x-y;
	}
	
	public int mul(int x, int y) {
		return x*y;
	}
	
	public double div(double x, double y) {
		if (y > 1e-30)
			return x/y;
		else
			return 0.0;
	}
}
