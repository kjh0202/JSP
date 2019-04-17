package jspbook.ch04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ScoreServlet
 */
@WebServlet("/ScoreServlet")
public class ScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ScoreServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int kor, eng, math;
		double result;
		String op;
		
		//클라이언트 응답시 전달될 컨텐트에 대한 mime type과 캐릿터셋 지정
		response.setContentType("text/html; charset=UTF-8");
		
		//클라이언트 응답을 위한 출력 스트림 확보
		PrintWriter out = response.getWriter();
		
		//HTML 폼을 통해 전달된 num1, num2 매개변수 값을 변수에 할당한다.
		/* 이때 getParameter() 메서드는 문자열을 반환하므로 숫자형 데이터의 경우 Integer.parseInt()를 통해 int로 변환한다. */
		kor = Integer.parseInt(request.getParameter("kor"));
		eng = Integer.parseInt(request.getParameter("eng"));
		math = Integer.parseInt(request.getParameter("math"));
		Score score = new Score();
		result = score.avg(kor, eng, math);
		
		//출력 스트림을 통해 화면을 구성한다.
		out.println("<html>");
		out.println("<head><title>성적 평균</title></head>");
		out.println("<body><center>");
		out.println("<h2>성적 평균</h2>");
		out.println("<hr>");
		out.println("("+"국어:"+kor+"+"+"영어:"+eng+"+"+"수학:"+math+")" +"&nbsp" + "평균 = "+result);
		out.println("</body></html>");
	}
	
/*	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));
		int sum = kor + eng + math;
		double avg = (double)sum / 3;
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>성적 계산</title></head>");
		out.println("<body><center>");
		out.println("<h2>성적계산 결과</h2><hr>");
		out.println("<table>");
		out.println("<thead><th>국어</th><th>영어</th><th>수학</th><th>합계</th><th>평균</th></thead>");
		out.println("<tr><td>"+kor+"</td><td>"+eng+"</td><td>"+math+"</td><td>"+sum+"</td><td>"+avg+"</td></tr>");
		out.println("</table>");
		out.println("</body></html>");
	}*/

}
