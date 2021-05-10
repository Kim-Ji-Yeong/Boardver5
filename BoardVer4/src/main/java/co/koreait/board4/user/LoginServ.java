package co.koreait.board4.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.koreait.board4.MyUtils;

@WebServlet("/user/login")
public class LoginServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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
		switch (result) {
		case 1:
			response.sendRedirect("/board/list");
			break;
		default:
			doGet(request, response);
			//	<- 이거랑 아래 중 택1로 사용하면됨.(메소드호출로 해결)
			// request로 값전달
			// 아래는 쿼리문으로
			//response.sendRedirect("login");

			break;
		}
	}

}
