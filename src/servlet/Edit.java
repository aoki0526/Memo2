
package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemolistDAO;
import model.Memolist;
/**
 * Servlet implementation class Edit
 */
@WebServlet("/Edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no_e = request.getParameter("no"); // JSPから受け取る
		MemolistDAO dao = new MemolistDAO();
		Memolist memolist = dao.editMemo(Integer.parseInt(no_e));
		request.setAttribute("memolist", memolist);
		
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/edit.jsp");
		 dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String title= request.getParameter("title");
		String text = request.getParameter("text");
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = sdf.format(calender.getTime());
		Memolist memolist = new Memolist(Integer.parseInt(no),title,text,date);
		MemolistDAO dao = new MemolistDAO();
		dao.update(memolist);
		
		response.sendRedirect("/MEMO/MainServlet");

	}

}
