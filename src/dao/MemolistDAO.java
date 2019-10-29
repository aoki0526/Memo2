package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Memolist;

public class MemolistDAO {
    // JDBCドライバ設定
	final String url = "jdbc:mysql://localhost:3306/Memo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	final String user = "root";
	final String pass = "mikanneko0131";



	// すべてのメモを取得する
	public List<Memolist> findAll(){
		Connection conn = null;
		List<Memolist> memolist = new ArrayList<Memolist>();

		try { // jdbcドライバ読込
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,pass);

			// SELECT文
			String sql = "SELECT no,title,text,date FROM memolist ORDER BY date DESC";
			PreparedStatement ps = conn.prepareStatement(sql);
			// 結果を取得
			ResultSet rs = ps.executeQuery();
			// 結果をArrayListへ
			while (rs.next()){
				int no = rs.getInt("no");
				String title =rs.getString("title");
				String text = rs.getString("text");
				String date = rs.getString("date");
				Memolist memo = new Memolist(no,title,text,date);
				memolist.add(memo);
			}
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		} catch(ClassNotFoundException e){
			e.printStackTrace();
			return null;
		} finally {
			// DB切断
			if (conn != null){
				try{
					conn.close();
				} catch(SQLException e){
					e.printStackTrace();
					return null;
				}
			}
		}
		return memolist;
	}
	// 新規メモを保存する
	public boolean create (Memolist newmemo){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,user,pass);

			// 追加するSQL
			String sql = "INSERT INTO memolist(title,text,date) VALUES(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, newmemo.getTitle());
			ps.setString(2, newmemo.getText());
			ps.setString(3,newmemo.getDate());

			// 実行
			 int result = ps.executeUpdate();

			if (result != 0){
				return false;
			}
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} finally {
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return true;
	}
	// 編集画面を出す,ナンバーを元に1件分データ取得
	public Memolist editMemo(int no){
		Connection conn = null;
		Memolist memolist = null;
		try {
			conn = DriverManager.getConnection(url,user,pass);
			String sql = "SELECT * FROM memolist WHERE no = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			 ps.setInt(1,no);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				String title = rs.getString("title");
				String text = rs.getString("text");
				String date = rs.getString("date");
				memolist = new Memolist(no,title,text,date);
			}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return memolist;
	}

	// 編集画面で保存を押したら更新処理をする
	public void update(Memolist memolist){
		Connection conn = null;

		try{
		conn = DriverManager.getConnection(url,user,pass);
		String sql = "UPDATE memolist SET title=?,text=?,date=? WHERE no=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1,memolist.getTitle());
		ps.setString(2, memolist.getText());
		ps.setString(3, memolist.getDate());
		ps.setInt(4, memolist.getNo());
		ps.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		} finally{
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
		    }
	    }
	}
	// 削除処理,edit画面から受け付ける
	public void delete(int no){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,user,pass);
			String sql = "DELETE FROM memolist WHERE no =?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
		    }
		}
	}
}
