package com.koreait.board4;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.board4.user.UserVO;

public class MyUtils {
	public static int getLoginUserPk(HttpServletRequest req) {
		if(req == null) {return 0;}
			return getLoginUser(req).getIuser();
		}
		
	
	public static UserVO getLoginUser(HttpServletRequest req) {
		if(req == null) { return null; }
		HttpSession hs = req.getSession();
		return (UserVO) hs.getAttribute("loginSuccess");
	}
	
	public static int getParamInt(String key, HttpServletRequest req) {
		String val = req.getParameter(key);
//		int intVal = MyUtils.parseStringToInt(val);
		int intVal = parseStringToInt(val); //같은 class라서 MyUtils생략가능
		return intVal;
	}

	public static int parseStringToInt(String val) {
		try {
			int result = Integer.parseInt(val);
			return result;
//			return Integer.parseInt(val);
		} catch (Exception e) {
			return 0;
		}
	}

	public static void openJSP(String fileNm, HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String jsp = "/WEB-INF/view/" + fileNm + ".jsp"; // board/list - > String fileNm으로 넘어간다고 생각하면 될듯
		req.getRequestDispatcher(jsp).forward(req, res);

	}
}
