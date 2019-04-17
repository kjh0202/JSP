package jspbook.ch04;

public class Score {
	public double avg(int kor, int eng, int math) {
		int sum;
		double avg;
		sum = kor+eng+math;
		avg = sum / 3;
		
		return (double) avg;
	}
}
