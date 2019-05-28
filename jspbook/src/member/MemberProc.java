package member;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class MemberProc
 */
@WebServlet("/member/memberProcServlet")
public class MemberProc extends HttpServlet {
	private static final Logger LOG = LoggerFactory.getLogger(MemberProc.class);
	private static final long serialVersionUID = 1L;
       
    public MemberProc() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		System.out.println("doGet(): " + uri + ", " + conPath + ", " + command);
*/		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.trace("doAction_시작");
		MemberDAO mDao = null;
		MemberDTO member = null;
		RequestDispatcher rd = null;
		int id = 0;
		int curPage = 1;
		int memberId = 0;
		String password = null;
		String name = null;
		String birthday = null;
		String address = null;
		String message = null;
		String url = null;
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		List<String> pageList = new ArrayList<String>();
		
		switch(action) {
		case "list":
			LOG.trace("list_시작");
			if (!request.getParameter("page").equals("")) {
				curPage = Integer.parseInt(request.getParameter("page"));
			}
			mDao = new MemberDAO();
			int count = mDao.getCount();
			if (count == 0)			// 데이터가 없을 때 대비
				count = 1;
			int pageNo = (int)Math.ceil(count/10.0);
			if (curPage > pageNo)	// 경계선에 걸렸을 때 대비
				curPage--;
			session.setAttribute("currentMemberPage", curPage);
			// 리스트 페이지의 하단 페이지 데이터 만들어 주기
			String page = null;
			page = "<a href=#>&laquo;</a>&nbsp;";
			pageList.add(page);
			for (int i=1; i<=pageNo; i++) {
				if (curPage == i)
					page = "&nbsp;" + i + "&nbsp;";
				else
					page = "&nbsp;<a href=memberProcServlet?action=list&page=" + i + ">" + i + "</a>&nbsp;";
				pageList.add(page);
			}
			page = "&nbsp;<a href=#>&raquo;</a>";
			pageList.add(page);
			 
			List<MemberDTO> mList = mDao.selectAll(curPage);
			request.setAttribute("memberList", mList);
			request.setAttribute("memberPageList", pageList);
			rd = request.getRequestDispatcher("main.jsp");
	        rd.forward(request, response);
	        LOG.trace("main_이동 성공");
			break;
			
		case "update":		// 수정 버튼 클릭 시
			LOG.trace("update_시작");
			if (!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			if (id != (Integer)session.getAttribute("memberId")) {
				message = "id = " + id + " 에 대한 수정 권한이 없습니다.";
				request.setAttribute("message", message);
				curPage = (int)session.getAttribute("currentMemberPage");
				url = "memberProcServlet?action=list&page=" + curPage;
				request.setAttribute("url", url);
				rd = request.getRequestDispatcher("alertMsg.jsp");
				rd.forward(request, response);
				 LOG.trace("alertMsg.jsp_이동 성공");
				break;
			}
			mDao = new MemberDAO();
			member = mDao.searchById(id);
			mDao.close();
			request.setAttribute("member", member);
			rd = request.getRequestDispatcher("update.jsp");
	        rd.forward(request, response);
	        break;
	        
		case "delete":		// 삭제 버튼 클릭 시
			LOG.trace("delete_시작");
			if (!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			if (id != (Integer)session.getAttribute("memberId")) {
				message = "id = " + id + " 에 대한 삭제 권한이 없습니다.";
				request.setAttribute("message", message);
				curPage = (int)session.getAttribute("currentMemberPage");
				url = "memberProcServlet?action=list&page=" + curPage;
				request.setAttribute("url", url);
				rd = request.getRequestDispatcher("alertMsg.jsp");
				rd.forward(request, response);
				 LOG.trace("alertMsg_이동 성공");
				break;
			}
			mDao = new MemberDAO();
			mDao.deleteMember(id);
			mDao.close();
			//response.sendRedirect("loginMain.jsp");
			message = "id = " + id + " 이/가 삭제되었습니다.";
			request.setAttribute("message", message);
			curPage = (int)session.getAttribute("currentMemberPage");
			url = "memberProcServlet?action=list&page=" + curPage;
			request.setAttribute("url", url);
			rd = request.getRequestDispatcher("alertMsg.jsp");
			 LOG.trace("alertMsg_이동 성공");
			rd.forward(request, response);
			
		case "login":		// login 할 때
			LOG.trace("login_시작");
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
			default: errorMessage="login 실패";
			}
			
			
			if (result == MemberDAO.ID_PASSWORD_MATCH) {
				member = mDao.searchById(id);
				session.setAttribute("memberId", id);
				session.setAttribute("memberName", member.getName());
				response.sendRedirect("memberProcServlet?action=list&page=1");
			} else {
				request.setAttribute("message", errorMessage);
				request.setAttribute("url", "login.jsp");
				rd = request.getRequestDispatcher("alertMsg.jsp");
		        rd.forward(request, response);
		   	 	LOG.trace("alertMsg_이동 성공");
			}
			mDao.close();
			break;
		
		case "logout":			// 로그아웃할 때
			LOG.trace("logout_시작");
			session.removeAttribute("memberId");
			session.removeAttribute("memberName");
			response.sendRedirect("login.jsp");
			 LOG.trace("login_이동 성공");
			break;
			
		case "register":		// 회원 등록할 때
			LOG.trace("register_시작");
			password = request.getParameter("password");
			name = request.getParameter("name");
			birthday = request.getParameter("birthday");
			address = request.getParameter("address");
			member = new MemberDTO(password, name, birthday, address);
			LOG.trace("register_toString성공");
			
			mDao = new MemberDAO();
			mDao.insertMember(member);
			member = mDao.recentId();
			session.setAttribute("memberId", member.getId());
			session.setAttribute("memberName", name);
			
			message = "귀하의 아이디는 " + member.getId() + " 입니다.";
			request.setAttribute("message", message);
			url = "memberProcServlet?action=list&page=1";
			request.setAttribute("url", url);
			rd = request.getRequestDispatcher("alertMsg.jsp");
			rd.forward(request, response);
			LOG.trace("alertMsg_이동 성공");
			mDao.close();
			break;
			
		case "execute":			// 회원 수정정보 입력 후 실행할 때
			LOG.trace("execute_시작");
			if (!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			name = request.getParameter("name");
			birthday = request.getParameter("birthday");
			address = request.getParameter("address");
			
			member = new MemberDTO(id, "*", name, birthday, address);
			LOG.trace("execute_toString성공");
			
			mDao = new MemberDAO();
			mDao.updateMember(member);
			mDao.close();
			
			message = "다음과 같이 수정하였습니다.\\n" + member.toString();
			request.setAttribute("message", message);
			curPage = (int)session.getAttribute("currentMemberPage");
			url = "memberProcServlet?action=list&page=" + curPage;
			request.setAttribute("url", url);
			rd = request.getRequestDispatcher("alertMsg.jsp");
	        rd.forward(request, response);
	   	 	LOG.trace("alertMsg_이동 성공");
			break;
			
		default:
		}
	}
}