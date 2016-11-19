package knothSampleFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetApiData
 */
public class GetApiData extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String api_result = "";
	private String url_string;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetApiData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// requestからurlを取得
		request.setCharacterEncoding("UTF8");
		// サンプルデータ
		request.setAttribute("api_url", "https://jp.api.pvp.net/api/lol/jp/v2.5/league/master?type=RANKED_SOLO_5x5&api_key=RGAPI-5793cc78-43fd-498e-b399-b037cdb99034");
		url_string = request.getParameter("api_url");
		System.out.println(url_string);
		// apiからデータを取得
		api_result = this.getResult();
		// jspにデータを送る準備
		request.setAttribute("api_result", api_result);
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

	// apiからデータを取得
	private String getResult(){
		String result = "";
		try{
			URL url = new URL(url_string);
			HttpURLConnection connection = null;
			try{
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
					try(
					InputStreamReader isr = new InputStreamReader(connection.getInputStream(),StandardCharsets.UTF_8);
					BufferedReader reader = new BufferedReader(isr)){
						String line;
						while ((line = reader.readLine()) != null){
							System.out.println(line);
						}
					}
				}
			}finally{
				if(connection != null){
					connection.disconnect();
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return result;
	}


}
