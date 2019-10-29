package model;

import java.util.List;

import dao.MemolistDAO;

public class GetMemologic {
	public List<Memolist> execute(){
		MemolistDAO dao = new MemolistDAO();
		List<Memolist> memolist = dao.findAll();
		return memolist;
	}

}
