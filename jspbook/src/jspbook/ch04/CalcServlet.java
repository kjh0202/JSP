package jspbook.ch04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalcServlet
 */
@WebServlet("/ch04/CalcServlet")
public class CalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public CalcServlet() {
        super();
    }
	
	//GET 요청을 처리하기 위한 메서드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//doPost()로 포워딩
		doPost(request, response);
	}
	//POST 요청을 처리하기 위한 메서드
	//doGet()에서도 호출했으므로 모든 요청은 doPost()에서 처리되는 구조다.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 변수 선언
		int num1, num2;
		int result;
		String op;
		
		//클라이언트 응답시 전달될 컨텐트에 대한 mime type과 캐릿터셋 지정
		response.setContentType("text/html; charset=UTF-8");
		
		//클라이언트 응답을 위한 출력 스트림 확보
		PrintWriter out = response.getWriter();
		
		//HTML 폼을 통해 전달된 num1, num2 매개변수 값을 변수에 할당한다.
		/* 이때 getParameter() 메서드는 문자열을 반환하므로 숫자형 데이터의 경우 Integer.parseInt()를 통해 int로 변환한다. */
		num1 = Integer.parseInt(request.getParameter("num1"));
		num2 = Integer.parseInt(request.getParameter("num2"));
		op = request.getParameter("operator");
		Calc calc = new Calc();
		//calc() 메서드 호출로 결과를 받아 온다.
		result = calc.calculate(num1, num2, op);
		
		//출력 스트림을 통해 화면을 구성한다.
		out.println("<html>");
		out.println("<head><title>계산기</title></head>");
		out.println("<body><center>");
		out.println("<h2>계산결과</h2>");
		out.println("<hr>");
		out.println(num1+" "+op+" "+num2+" ="+result);
		out.println("</body></html>");
	}
}
