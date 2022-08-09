package bo.service;

import bo.vo.BoardVO;
import static bo.db.JdbcUtil.*;

import java.sql.Connection;

import bo.dao.BoardDAO;
public class BoardUpdateProService {

	public boolean modifyArticle(BoardVO article) throws Exception {
		// TODO Auto-generated method stub
		boolean modifySuccess = false;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int updateCount = boardDAO.updateArticle(article);
		if(updateCount > 0) {
			commit(con);
			modifySuccess = true;
		}
		else {
			rollback(con);
		}
		//�����͸� �����ϴ� �۾��� ���� commit�� rollback ����� �Ѵ�.
		close(con);
		return modifySuccess;
	}

}
