package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GetMemologic;
import model.Memolist;
import model.PostMemoLogic;

/**
 * Servlet implementation class NewMemoServlet
 */
@WebServlet("/NewMemoServlet")
public class NewMemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewMemoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/newmemo.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String text = request.getParameter("text");
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = sdf.format(calender.getTime());
		String mes = null; // タイトルか本文が入力されてない場合のエラー用
		
		RequestDispatcher dispatcher = null;
		
		if (title.equals("") ||  text.equals("")){
			mes = "※タイトルと本文は必須入力です";
			request.setAttribute("mes", mes);
			dispatcher = request.getRequestDispatcher("/WEB-INF/newmemo.jsp");
			dispatcher.forward(request, response);
		} else {
			Memolist newmemo = new Memolist(title,text,date);
			PostMemoLogic postMemoLogic = new PostMemoLogic();
			postMemoLogic.execute(newmemo);
			
			GetMemologic getMemologic = new GetMemologic();
			List<Memolist> memolist = getMemologic.execute();
			request.setAttribute("memolist", memolist);
			dispatcher = request.getRequestDispatcher("/WEB-INF/main.jsp");
			dispatcher.forward(request, response);

		}
	}


}
