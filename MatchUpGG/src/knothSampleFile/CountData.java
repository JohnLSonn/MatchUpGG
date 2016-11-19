package knothSampleFile;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CountData
 */
public class CountData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String api_url = "";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CountData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		api_url = "https://jp.api.pvp.net/api/lol/jp/v2.5/league/master?type=RANKED_SOLO_5x5&api_key=RGAPI-5793cc78-43fd-498e-b399-b037cdb99034";

		request.setCharacterEncoding("UTF8");
		request.setAttribute("api_url", api_url);
		// サーブレット呼び出し
		RequestDispatcher dispatcher = request.getRequestDispatcher("/GetApiData");
		dispatcher.forward(request, response);
		// 出力テスト
		PrintWriter out = response.getWriter();
		out.println(request.getAttribute("api_result"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}