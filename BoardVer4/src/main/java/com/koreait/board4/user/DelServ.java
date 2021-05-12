package com.koreait.board4.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board4.MyUtils;
import com.koreait.board4.board.BoardDAO;


@WebServlet("/board/del")
public class DelServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String iboard = request.getParameter("iboard");
//		int intIboard = Integer.parseInt(iboard);
		int iboard = MyUtils.getParamInt("iboard", request);
		int iuser = MyUtils.getLoginUserPk(request);
		
		BoardVO vo = new BoardVO();
//		vo.setIboard(intIboard);
		vo.setIboard(iboard);
		vo.setIuser(iuser);
		
		BoardDAO.delBoard(vo);
		
		response.sendRedirect("/board/list"); //list
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
