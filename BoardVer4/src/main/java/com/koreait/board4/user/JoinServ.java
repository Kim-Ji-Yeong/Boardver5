package com.koreait.board4.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.koreait.board4.MyUtils;


@WebServlet("/user/join")
public class JoinServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MyUtils.openJSP("user/join", request, response);
	}
		// openJSP메소드에 "/"를 넣었기때문에 "user/join"의 앞부분에 /이 안들어감
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String upw = request.getParameter("upw");
		String unm = request.getParameter("unm");
		//String gender = request.getParameter("gender");
//		int Intgender = Integer.parseInt(gender);
		//int intGender = MyUtils.parseStringToInt(gender);
		int gender = MyUtils.getParamInt("gender", request);
		
		System.out.println("unm : " + unm);
		String hashedUpw = BCrypt.hashpw(upw, BCrypt.gensalt());
		System.out.println("hashedUpw : " + hashedUpw);
		
		UserVO user = new UserVO();
		user.setUid(uid);
		user.setUnm(unm);
		user.setUpw(hashedUpw);
		user.setGender(gender);
		
		UserDAO.joinUser(user);
//		response.sendRedirect("/user/login"); <- 이거랑 아래 둘 중 하나 쓰면됨
		response.sendRedirect("login");
	}

}
