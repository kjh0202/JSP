package jspbook.ch04;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberJoin
 */
@WebServlet("/ch04/MemberJoinServlet")
public class MemberJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberJoinServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		int id = Objects.hash(name, email, tel);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>회원가입 결과</title></head>");
		out.println("<h3>회원가입 결과</h3>");
		out.println("<hr>");
		out.println("회원가입을 축하합니다.<br>");
		out.println("이름: " + name + "<br>");
		out.println("이메일: " + email + "<br>");
		out.println("전화번호: " + tel + "<br>");
		out.println("귀하의 회원 아이디는 " + id + " 입니다.<br>");
	}
}