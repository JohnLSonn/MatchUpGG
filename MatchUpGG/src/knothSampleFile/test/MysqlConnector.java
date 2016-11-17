package xyz.nothost.matchupgg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlConnector {

	private static Connection conn;
	private static Statement stmt;
	private static ResultSet rset;
	private final String driver_url = "com.mysql.jdbc.Driver";
	private String connect_url;
	private String user_id;
	private String password;

	public String getDriverUrl(){
		return this.driver_url;
	}
	public void setConnectUrl(String url){
		this.connect_url = url;
	}
	public String getConnectUrl(){
		return this.connect_url;
	}

	public void setUserId(String id){
		this.user_id = id;
	}
	public String getUserId(){
		return this.user_id;
	}

	public void setPassword(String pw){
		this.password = pw;
	}
	public String getPassword(){
		return this.password;
	}



	public void connectDB(String connect_url,String user_id, String password){

		setConnectUrl(connect_url);
		setUserId(user_id);
		setPassword(password);

		try{
		    Class.forName(getDriverUrl());
		    System.out.println("driver loaded.");
			conn = DriverManager.getConnection(getConnectUrl() + "?user=" + getUserId() + "&password=" + getPassword());
			System.out.println("connected your database.");
			stmt = conn.createStatement();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	// 終了時実行
	public void disconnectDB(){

		try{
			rset.close();
			stmt.close();
			conn.close();

		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	// データ取得
	public ResultSet executeSQLGet(String sql){

		System.out.println("starting to get data...");

		try{
			rset = stmt.executeQuery(sql);

		}catch (Exception e){
			e.printStackTrace();
		}

		System.out.println("ended to get data...");

		return rset;
	}
	// データ操作
	public void executeSQLSet(String sql){

		System.out.println("starting to set data...");
		try{
			stmt.executeUpdate(sql);

		}catch(SQLException e){
			e.printStackTrace();
		}

		System.out.println("ended to set data...");
	}


	public static void main(String args[]){

		// 必要情報を記入
		String url = "jdbc:mysql://localhost:3306/matchupgg_db";
		String user = "root";
		String password = "Kesuike0191";
		String sql = "select * from champion_master";
		String sql2 = "select champ_id from champion_master";

		MysqlConnector mysqlConnector = new MysqlConnector();

		try {
			mysqlConnector.connectDB(url, user, password);

			// select文を使う場合
			ResultSet result = mysqlConnector.executeSQLGet(sql);

			// ここに処理したい内容を記述
			while(result.next()){
				System.out.println("champ_id  : " + result.getInt(1));
				System.out.println("champ_name: " + result.getString(2));
				System.out.println("champ_key : " + result.getString(3) + "\n");
			}


			// select文を使う場合
			ResultSet result2 = mysqlConnector.executeSQLGet(sql2);

			// ここに処理したい内容を記述
			while(result2.next()){
				System.out.println("champ_id  : " + result2.getInt(1));
			}

			// insert文やdelete文を使う場合
			// mysqlConnector.executeSQLSet(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnector.disconnectDB();
		}
		
	}

}
