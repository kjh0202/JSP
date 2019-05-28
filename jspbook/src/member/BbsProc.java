package member;

import java.io.IOException;
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
 * Servlet implementation class BbsProc
 */
@WebServlet("/member/bbsServlet")
public class BbsProc extends HttpServlet {
	private static final Logger LOG = LoggerFactory.getLogger(BbsProc.class);

	private static final long serialVersionUID = 1L;
       
    public BbsProc() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.trace("doAction_시작");
		BbsDAO bDao = null;
		BbsMember bMem = null;
		BbsDTO bDto = null;
		RequestDispatcher rd = null;
		int id = 0;
		int memberId = 0;
		String title = null;
		String content = null;
		String message = null;
		String url = null;
		int curPage = 1;
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		// 세션이 만료되었으면 다시 로그인하게 만들어 줌
		try {
			memberId = (int)session.getAttribute("memberId");
		} catch (NullPointerException e) {
			LOG.trace("세션만료");
		}
		if (memberId == 0) {
			rd = request.getRequestDispatcher("login.jsp");
	        rd.forward(request, response);
		}
		
		String action = request.getParameter("action");
		List<String> pageList = new ArrayList<String>();
		
		switch(action) {
		case "list":
			LOG.trace("list_시작");
			if (!request.getParameter("page").equals("")) {
				curPage = Integer.parseInt(request.getParameter("page"));
			}
			bDao = new BbsDAO();
			int count = bDao.getCount();
			if (count == 0)			// 데이터가 없을 때 대비
				count = 1;
			int pageNo = (int)Math.ceil(count/10.0);
			if (curPage > pageNo)	// 경계선에 걸렸을 때 대비
				curPage--;
			session.setAttribute("currentBbsPage", curPage);
			// 리스트 페이지의 하단 페이지 데이터 만들어 주기
			String page = null;
			page = "<a href=#>&laquo;</a>&nbsp;";
			pageList.add(page);
			for (int i=1; i<=pageNo; i++) {
				if (curPage == i)
					page = "&nbsp;" + i + "&nbsp;";
				else
					page = "&nbsp;<a href=bbsServlet?action=list&page=" + i + ">" + i + "</a>&nbsp;";
				pageList.add(page);
			}
			page = "&nbsp;<a href=#>&raquo;</a>";
			pageList.add(page);
			
			List<BbsMember> bmList = bDao.selectJoinAll(curPage);
			request.setAttribute("bbsMemberList", bmList);
			request.setAttribute("pageList", pageList);
			rd = request.getRequestDispatcher("bbsList.jsp");
	        rd.forward(request, response);
	        LOG.trace("bbsList_이동 성공");
			break;
			
		case "write":
			LOG.trace("write_시작");
			title = request.getParameter("title");
			content = lf2Br(request.getParameter("content"));
			bDto = new BbsDTO(memberId, title, content);
			bDao = new BbsDAO();
			bDao.insertBbs(bDto);
			bDao.close();
			response.sendRedirect("bbsServlet?action=list&page=1");
			//response.sendRedirect("bbsList.jsp");
			LOG.trace("bbsServlet?action=list&page=1_이동 성공");
			break;
			
		case "view":
			LOG.trace("view_시작");
			if (!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			bDao = new BbsDAO();
			bMem = bDao.ViewData(id);
			bDao.close();
			
			request.setAttribute("bbsMember", bMem);
			rd = request.getRequestDispatcher("bbsView.jsp");
	        rd.forward(request, response);
	        LOG.trace("bbsView_이동 성공");
			break;
		
		case "update":		// 수정 버튼 클릭 시
			LOG.trace("update_시작");
			if (!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			bDao = new BbsDAO();
			bDto = bDao.selectOne(id);
			if (bDto.getMemberId() != memberId) {
				message = "id = " + id + " 에 대한 수정 권한이 없습니다.";
				url = "bbsServlet?action=list&page=1";
				request.setAttribute("message", message);
				request.setAttribute("url", url);
				rd = request.getRequestDispatcher("alertMsg.jsp");
				rd.forward(request, response);
			    LOG.trace("alertMsg_이동 성공");
				bDao.close();
				break;
			}
			bMem = bDao.ViewData(id);
			bDao.close();
			
			bMem.setContent(br2Lf(bMem.getContent()));
			request.setAttribute("bbsMember", bMem);
			rd = request.getRequestDispatcher("bbsUpdate.jsp");
	        rd.forward(request, response);
	        LOG.trace("bbsUpdate_이동 성공");
			break;
			
		case "execute":		// 데이터 수정 후 실행할 때
			LOG.trace("execute_시작");
			if (!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			title = request.getParameter("title");
			content = lf2Br(request.getParameter("content"));
			bDto = new BbsDTO(id, memberId, title, "", content);
			bDao = new BbsDAO();
			bDao.updateBbs(bDto);
			bDao.close();
			curPage = (int)session.getAttribute("currentBbsPage");
			response.sendRedirect("bbsServlet?action=list&page=" + curPage);
			LOG.trace("bbsServlet?action=list&page=\" + curPage_이동 성공");
			break;
			
		case "delete":		// 삭제 버튼 클릭 시
			LOG.trace("delete_시작");
			if (!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			bDao = new BbsDAO();
			bDto = bDao.selectOne(id);
			curPage = (int)session.getAttribute("currentBbsPage");
			if (bDto.getMemberId() != (Integer)session.getAttribute("memberId")) {
				message = "id = " + id + " 에 대한 삭제 권한이 없습니다.";
				url = "bbsServlet?action=list&page=" + curPage;
				request.setAttribute("message", message);
				request.setAttribute("url", url);
				rd = request.getRequestDispatcher("alertMsg.jsp");
				rd.forward(request, response);
				LOG.trace("alertMsg_이동 성공");
				bDao.close();
				break;
			}
			bDao.deleteBbs(id);
			bDao.close();
			message = "id = " + id + " 이/가 삭제되었습니다.";
			url = "bbsServlet?action=list&page=1";
			request.setAttribute("message", message);
			request.setAttribute("url", url);
			rd = request.getRequestDispatcher("alertMsg.jsp");
			rd.forward(request, response);	
			LOG.trace("alertMsg_이동 성공");
			break;
			
		default:
		}
	}
	
	protected String lf2Br(String content) {
		LOG.trace("lf2Br_시작");
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<content.length(); i++) {
			if (content.charAt(i) == '\r') {
				sb.append("<br>");
				sb.append(content.charAt(i));
			} else
				sb.append(content.charAt(i));
		}
		return sb.toString();
	}
	protected String br2Lf(String content) {
		LOG.trace("br2Lf_시작");
		StringBuffer sb = new StringBuffer(content);
		int count = 0;
		while (true) {
			int index = sb.indexOf("<br>", count);
			if (index < 0)
				break;
			sb.delete(index, index+4);
			count += 4;
		}
		LOG.trace("결과 성공");
		return sb.toString();
	}
}