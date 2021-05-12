package com.koreait.board4.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.koreait.board4.DBUtils;
import com.koreait.board4.user.BoardVO;

public class BoardDAO {
	public static int insBoard(BoardVO param) {
		Connection con = null;
		PreparedStatement ps = null;

		String sql = "INSERT INTO t_board " + " (title, ctnt, iuser) " + " VALUES" + "(?,?,?)";

		try {
			con = DBUtils.getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, param.getTitle());
			ps.setString(2, param.getCtnt());
			ps.setInt(3, param.getIuser());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps);
		}
		return 0;
	}

	public static List<BoardVO> selBoardList() {
		List<BoardVO> list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = " SELECT A.iboard, A.title, A.regdt, B.unm " + " FROM t_board A " + " LEFT JOIN t_user B "
				+ " ON A.iuser = B.iuser " + " ORDER BY A.iboard DESC ";

		try {
			con = DBUtils.getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				BoardVO vo = new BoardVO();
				int iboard = rs.getInt("iboard");
				String title = rs.getString("title");
				String regdt = rs.getString("regdt");
				String unm = rs.getString("unm");

				vo.setIboard(iboard);
				vo.setTitle(title);
				vo.setRegdt(regdt);
				vo.setUnm(unm);

				list.add(vo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, rs);
		}
		return list;
	}

	public static BoardVO selBoard(int iboard) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		BoardVO data = null;

		String sql = " SELECT A.title, A.regdt, A.ctnt, A.iuser, B.unm " + " FROM t_board A " + " LEFT JOIN t_user B "
				+ " ON A.iuser = B.iuser " + "where iboard = ? ";
		// a.iuser, b.iuser 뭘 적든 상관 ㄴㄴ
		try {
			con = DBUtils.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, iboard);
			rs = ps.executeQuery();

			if (rs.next()) {
				data = new BoardVO();

				String title = rs.getString("title");
				String ctnt = rs.getString("ctnt");
				String regdt = rs.getString("regdt");
				String unm = rs.getString("unm");
				int iuser = rs.getInt("iuser");

				data.setCtnt(ctnt);
				data.setIboard(iboard);
				data.setTitle(title);
				data.setUnm(unm);
				data.setRegdt(regdt);
				data.setIuser(iuser);
				return data;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return data;
		} finally {
			DBUtils.close(con, ps, rs);
		}
		return null;
	}

	public static int delBoard(BoardVO param) {
		Connection con = null;
		PreparedStatement ps = null;

		String sql = "DELETE FROM t_board where iboard = ?";

		try {
			con = DBUtils.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getIboard());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps);
		}
		return 0;
	}
	public static int updBoard(BoardVO param) {
		Connection con = null;
		PreparedStatement ps = null;

		String sql = " UPDATE t_board " 
				+ " SET title = ? " 
				+ " , ctnt = ? " 
				+ " WHERE iBoard = ? "
				+ " and iuser = ?  ";

		try {
			con = DBUtils.getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, param.getTitle());
			ps.setString(2, param.getCtnt());
			ps.setInt(3, param.getIboard());
			ps.setInt(4, param.getIuser());
			return ps.executeUpdate(); 

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps);
		}

		return 0;
	}
}
