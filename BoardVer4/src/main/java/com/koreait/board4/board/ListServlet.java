package com.koreait.board4.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.board4.MyUtils;
import com.koreait.board4.user.BoardVO;
import com.koreait.board4.user.UserVO;


@WebServlet("/board/list")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO) hs.getAttribute("loginSuccess");
		//로그인 안되어있으면 로그인 화면으로 이동
		
		if(loginUser == null) {
			response.sendRedirect("/user/login"); // /"board"때문에 "/user"로 기입해야함 
			return;
		}
		
		
		List<BoardVO> list = BoardDAO.selBoardList();
		request.setAttribute("list", list);
		
		//로그인 했으면 board/list.jsp 파일 응답
		MyUtils.openJSP("board/list", request, response);
		

		
		
		
		
//		Boolean loginSuccess = (Boolean)hs.getAttribute("loginSuccess");
//		//boolean 은 자료형이기 때문에 오직 true, false 만 들어갈수있고 null 은 못넣는다
//		//null 을 넣기위해선 바로 참조형인 Boolean 으로 적어야한다.
//		//같은 맥락으로 int 에 null 을 못넣고 Integer 에 null 을 넣을 수 있는 것과 같다.
//		System.out.println("loginSuccess : " + loginSuccess);
//		if(loginSuccess == null || loginSuccess == false) {
//			response.sendRedirect("/user/login");
//			return;
//		}
//		
//		System.out.println("list-req : " + request.hashCode());
//		System.out.println("list-hs : " + hs.toString());
		
//		MyUtils.openJSP("board/list", request, response);
	}

	
	

}
