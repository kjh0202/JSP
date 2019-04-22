package member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class memberProc
 */
@WebServlet("/member/memberProcServlet")
public class MemberProc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberProc() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO mDao = null;
		MemberDTO member = null;
		RequestDispatcher rd = null;
		int id = 0; 
		String name = "";
		String birthday = "";
		String address = "";
		String password = ""; 
		String message = "";
		String url = null;
		member = null;
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		
		//수정, 삭제 등 확장성을 위해서
		switch(action) {
		case "update" :		//수정 버튼 클릭 시
			if (!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			if (id != (Integer)session.getAttribute("memberId")) {
				message = "id = " + id + " 에 대한 수정 권한이 없습니다";		//alert 메시지
				url = "loginMain.jsp";
				request.setAttribute("message", message);
				request.setAttribute("url", url);
				rd = request.getRequestDispatcher("alertMsg.jsp");
				rd.forward(request, response);
				break;
			}
			mDao = new MemberDAO();
			member = mDao.searchById(id);
			mDao.close();
			request.setAttribute("member", member);
			rd = request.getRequestDispatcher("update.jsp");
			rd.forward(request, response);
			break;
			
		case "delete" :		//삭제 버튼 클릭 시
			if (!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			
			if (id != (Integer)session.getAttribute("memberId")) {
				message = "id = " + id + " 에 대한 삭제 권한이 없습니다";		//alert 메시지
				url = "loginMain.jsp";
				request.setAttribute("message", message);
				request.setAttribute("url", url);
				rd = request.getRequestDispatcher("alertMsg.jsp");
				rd.forward(request, response);
				break;
			}
			mDao = new MemberDAO();
			mDao.deleteMember(id);
			mDao.close();
			//response.sendRedirect("loginMain.jsp");
			
			break;
			
		case "login" :		//login할때
			if (!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			password = request.getParameter("password");

			mDao = new MemberDAO();
			int result = mDao.verifyIdPassword(id, password);
			String errorMessage = null;
			switch (result) {
			case MemberDAO.ID_PASSWORD_MATCH:
				break;
			case MemberDAO.ID_DOES_NOT_EXIST:
				errorMessage = "ID가 없음"; break;
			case MemberDAO.PASSWORD_IS_WRONG:
				errorMessage = "패스워드가 틀렸음"; break;
			case MemberDAO.DATABASE_ERROR:
				errorMessage = "DB 오류";
			}
			
			//System.out.println(errorMessage);
			if (result == MemberDAO.ID_PASSWORD_MATCH) {
				member =  mDao.searchById(id);
				session.setAttribute("memberId", id);
				session.setAttribute("memberName", member.getName());
				response.sendRedirect("loginMain.jsp");
			} else {
				//방법1 - 상대방이 getParameter("error")로 받을 때
				//String uri = "login.jsp?error=" + URLEncoder.encode(errorMessage, "UTF-8");
				//response.sendRedirect(uri);
				/*방법2 - 상대방이 getParameter("error")로 받을 때
				String uri = "login.jsp" + "?error=" + errorMessage;
				RequestDispatcher dispatcher = request.getRequestDispatcher(uri);
				dispatcher.forward(request, response);*/
				//방법3 - 상대방이 getAttribute("error")로 받을 때
				request.setAttribute("error", errorMessage);
				rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
				//pageContext.forward("login.jsp");
			}
			mDao.close();
			break;
					
		case "logout" :			//로그아웃할 때
			session.removeAttribute("memberId");
			session.removeAttribute("memberName");
			response.sendRedirect("login.jsp");
			break;
			
		case "register" :		//회원 등록할 때
			password = request.getParameter("password");
			name = request.getParameter("name");
			birthday = request.getParameter("birthday");
			address = request.getParameter("address");
			member = new MemberDTO(password, name, birthday, address);
			System.out.println(member.toString());
			
			mDao = new MemberDAO();
			mDao.insertMember(member);
			member = mDao.recentId();
			session.setAttribute("memberId", member.getId());
			session.setAttribute("memberName", name);
			
			message = "귀하의 아이디는 " + member.getId() + " 입니다.";
			url = "loginMain.jsp";
			request.setAttribute("message", message);
			request.setAttribute("url", url);
			rd = request.getRequestDispatcher("alertMsg.jsp");
			rd.forward(request, response);
			mDao.close();
			break;
			
		case "execute" :
			if (!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
			}

			name = request.getParameter("name");
			birthday = request.getParameter("birthday");
			address = request.getParameter("address");
			
			member = new MemberDTO(id, "*", name, birthday, address);
			System.out.println(member.toString());
			
			mDao = new MemberDAO();
			mDao.updateMember(member);
			mDao.close();
			
			message = "다음과 같이 수정하였습니다.\\n" + member.toString();		//alert 메시지
			request.setAttribute("message", message);
			request.setAttribute("url", "loginMain.jsp");
			rd = request.getRequestDispatcher("alertMsg.jsp");
			rd.forward(request, response);
			//response.sendRedirect("loginMain.jsp");
			break;
		default :	
		}
	}

}
