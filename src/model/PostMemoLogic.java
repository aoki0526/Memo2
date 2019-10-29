package model;
// インスタンスからmemolistテーブルに追加する
import dao.MemolistDAO;

public class PostMemoLogic {
	public void execute(Memolist newmemo){
		MemolistDAO dao = new MemolistDAO();
		dao.create(newmemo);
	}

}
