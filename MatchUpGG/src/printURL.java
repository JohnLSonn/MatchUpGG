

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class printURL
 */
public class printURL extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String url_string;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public printURL() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// TODO Auto-generated method stub
		// requestからurlを取得
		request.setCharacterEncoding("UTF8");
		// サンプルデータ
		request.setAttribute("api_url", "https://jp.api.pvp.net/api/lol/jp/v2.5/league/master?type=RANKED_SOLO_5x5&api_key=RGAPI-5793cc78-43fd-498e-b399-b037cdb99034");
		url_string = request.getParameter("api_url");
		response.getWriter().append(url_string);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
