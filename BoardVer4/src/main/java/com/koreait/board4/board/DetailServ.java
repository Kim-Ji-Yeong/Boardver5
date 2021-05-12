package com.koreait.board4.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board4.MyUtils;
import com.koreait.board4.user.BoardVO;


@WebServlet("/board/detail")
public class DetailServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int iboard = MyUtils.getParamInt("iboard", req);
		BoardVO data = BoardDAO.selBoard(iboard);
		
		req.setAttribute("data", data);
		
		MyUtils.openJSP("board/detail", req, res);
		
	}



}
