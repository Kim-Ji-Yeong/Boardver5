package com.koreait.board4.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;

import com.koreait.board4.MyUtils;

@WebServlet("/user/login")
public class LoginServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession hs = req.getSession();
		UserVO loginUser = (UserVO) hs.getAttribute("loginSuccess");
		if(loginUser != null) {
			res.sendRedirect("/board/list");
			return;
		}
		
		MyUtils.openJSP("user/login", req, res);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String upw = request.getParameter("upw");

		UserVO vo = new UserVO();
		vo.setUid(uid);
		vo.setUpw(upw);

		int result = UserDAO.loginUser(vo);
		System.out.println("result : " + result);

		// -------------doGet방법으로----------------------
		if (result == 1) { // 로그인 성공
			HttpSession hs = request.getSession();
			vo.setUpw(null);
			hs.setAttribute("loginSuccess", vo); // vo가 가리키는 UserVo객체는
													// (iuser, uid, unm 값만 담고 있다)

//			hs.setAttribute("loginSuccess", true);
			response.sendRedirect("/board/list");
			return;
		}

		String errMsg = null;
		switch (result) {
		case 0:
			errMsg = "에러가 발생했습니다";
			break;
		case 2:
			errMsg = "아이디를 확인해 주세요";
			break;
		case 3:
			errMsg = "비밀번호를 확인해 주세요";
			break;
		}
		request.setAttribute("errMsg", errMsg);
		// ----------------------------------------------
		switch (result) {
		case 1:
			response.sendRedirect("/board/list");
			break;
		default:
			doGet(request, response);
			// <- 이거랑 아래 중 택1로 사용하면됨.(메소드호출로 해결)
			// request로 값전달
			// 아래는 쿼리문으로
			// response.sendRedirect("login");

			break;
		}
		doGet(request, response);
	}

}
