package xyz.nothost.matchupgg;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SetAllShow
 */
public class SetAllShow extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int championid;
	private String champingname;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetAllShow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String url = "jdbc:mysql://localhost:3306/sotusei";
		String user = "root";
		String password = "1234";
		String sql = "select * from champion";
		MysqlConnector mysqlConnector = new MysqlConnector();

		try {
			mysqlConnector.connectDB(url, user, password);

			// select文を使う場合
			ResultSet result = mysqlConnector.executeSQLGet(sql);
/*
			HashMap<String, String> champ = new HashMap<String, String>();
			// ここに処理したい内容を記述
			while(result.next()){
				champ.put(result.getString(2), result.getString(3));
			}

			for(String str : champ.keySet()){
				System.out.println(str + ":" + champ.get(str));
			}*/
//			request.setAttribute("champ", champ);

			// insert文やdelete文を使う場合
			// mysqlConnector.executeSQLSet(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnector.disconnectDB();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public int getChampionid() {
		return championid;
	}

	public void setChampionid(int championid) {
		this.championid = championid;
	}

	public String getChampingname() {
		return champingname;
	}

	public void setChampingname(String champingname) {
		this.champingname = champingname;
	}

}
