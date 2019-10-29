package model;

import java.util.List;

import dao.MemolistDAO;

public class ShowAllmemolist {
	// memolistテーブルから全件取得
	public List<Memolist> execute(){
		MemolistDAO memoDAO = new MemolistDAO();
		List<Memolist> memolist = memoDAO.findAll();
		return memolist;
	}
}

