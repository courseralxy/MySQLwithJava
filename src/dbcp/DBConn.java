package dbcp;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {
	static String driverName="com.mysql.jdbc.Driver";
	static String dbURL="jdbc:mysql://localhost:3306/lab2";
	static String userName="root";
	static String userPwd="1234";
	private static Connection dbConn = null;
    //获取一个数据库连接
    public static Connection getConnection() {
		try {    
			Class.forName(driverName);
			dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
			//System.out.println("连接数据库成功");
			//dbConn.close();
		} catch(Exception e) {
			e.printStackTrace();
			//System.out.print("连接失败");
		}
        return dbConn;
    }
    public static void main(String[] args) {
    	getConnection();
    }
}