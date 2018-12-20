import java.sql.*;
public class DBCon {  
	public static void output(ResultSet worths) throws SQLException {
		ResultSetMetaData data= worths.getMetaData();
		String output1=new String();
		for(int i = 1; i <= data.getColumnCount();i++)
			output1+=data.getColumnName(i)+"\t";
		System.out.println(output1);
		String output2=new String();
		while(worths.next()) {
			for(int i = 1; i <= data.getColumnCount();i++)
				output2+=worths.getString(i)+"\t";
			output2+="\n";
			//System.out.println(worths.getString(1)+"\t"+worths.getInt(2)+"\t"+worths.getInt(3)+"\t"+worths.getInt(4)+"\t"+worths.getInt(5));
		}
		System.out.println(output2);
	}
	public static void main(String[] args) throws SQLException {
		String driverName="com.mysql.jdbc.Driver";
		String dbURL="jdbc:mysql://localhost:3306/lab2";
		String userName="root";
		String userPwd="1234";
		Connection dbConn = null;
		try {    
			Class.forName(driverName);
			dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
			System.out.println("连接数据库成功");
			//dbConn.close();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.print("连接失败");
		}
		System.out.println("初始状态");
		Statement execStat = dbConn.createStatement();
		ResultSet worths = execStat.executeQuery("SELECT * FROM STUDENT");
		DBCon.output(worths);
		System.out.println("插入操作");
		execStat.executeUpdate(
				"INSERT INTO "
				+ "STUDENT(NAME,SEX,ID,AGE,DID) "
				+ "VALUES "
				+ "('乌蝇哥','男',0,40,2),"
				+ "('非凡哥','男',1,42,3),"
				+ "('醒哥','男',2,35,4);");
		worths = execStat.executeQuery("SELECT * FROM STUDENT");
		DBCon.output(worths);
		
		System.out.println("修改操作");
		execStat.executeUpdate(
				"UPDATE "
				+ "STUDENT "
				+ "SET "
				+ "NAME = '刘醒' "
				+ "WHERE NAME = '醒哥';");
		execStat.executeUpdate(
				"UPDATE "
				+ "STUDENT "
				+ "SET "
				+ "NAME = '梁非凡' "
				+ "WHERE NAME = '非凡哥';");
		
		worths = execStat.executeQuery("SELECT * FROM STUDENT");
		DBCon.output(worths);
		
		System.out.println("删除操作");
		execStat.executeUpdate(
				"DELETE FROM "
				+ "STUDENT "
				+ "WHERE "
				+ "ID = 0 OR "
				+ "ID = 1 OR "
				+ "ID = 2;");
		worths = execStat.executeQuery("SELECT * FROM STUDENT");
		DBCon.output(worths);
	}
} 