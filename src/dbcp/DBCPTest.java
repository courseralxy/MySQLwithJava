package dbcp;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class DBCPTest {
    //清空数据表
	public void initialize() throws Exception{
        for(int i = 0; i < 2000; i++){
            initialize(i);
        }
        System.out.println("DONE");
        
    }
	public void initialize(int id) {
		String sql = "INSERT INTO "
        		+ "STUDENT "
        		+ "values "
        		+ "('测试',"
        		+ "'男',"
        		+ id
        		+",99,1)";
		Connection conn = DBConn.getConnection();
		try{    
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql);            
        }catch(Exception e){
            e.printStackTrace() ; 
        }finally{
            try {    
                conn.close();    
            } catch (SQLException e) {
                e.printStackTrace();
            }          
        }
	}
	//直接连接模式，每次写数据都需要创建新连接
    public void DBConnWrite1Test() throws Exception{
        for(int i = 0; i < 2000; i++){
            DBConnWrite1(i);
        }
        System.out.println("DONE");
        
    }
    public void DBConnWrite1(int id){
        //String sql = "insert into dbcp values (" + id + ")"; 
        String sql = "INSERT INTO "
        		+ "STUDENT "
        		+ "values "
        		+ "('测试',"
        		+ "'男',"
        		+ id
        		+",99,1)";
        Connection conn = DBConn.getConnection();
        try{    
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql);            
        }catch(Exception e){
            e.printStackTrace() ;
        }finally{
            try {    
                conn.close();    
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        }
    }
    
    
    
    //连接池模式
    public void DBCPConnWriteTest() throws Exception{
        for(int i = 0; i < 2000; i++){
            DBCPConnWrite(i);
        }
        System.out.println("DONE");
    }
    public void DBCPConnWrite(int id){ 
    	String sql = "INSERT INTO "
        		+ "STUDENT "
        		+ "values "
        		+ "('测试',"
        		+ "'男',"
        		+ id
        		+",99,1)";
        try {
            Connection conn = DBCPConn.getConnection();  
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql);
            conn.commit();
            conn.close();
        } catch (SQLException e) {   
            e.printStackTrace();  
        }
    }
    
    //直接连接模式，所有数据插入都使用同一条连接
    public void DBConnWrite2Test() throws Exception{
        Connection conn = DBConn.getConnection();
        Statement stat = conn.createStatement();
        for(int i = 0; i < 2000; i++){
            DBConnWrite2(i, stat);
        }
        conn.close();
        System.out.println("DONE");
    }
    public void DBConnWrite2(int id, Statement stat){
    	String sql = "INSERT INTO "
        		+ "STUDENT "
        		+ "values "
        		+ "('测试',"
        		+ "'男',"
        		+ id
        		+",99,1)";
        try{    
            stat.executeUpdate(sql);            
        }catch(Exception e){
            e.printStackTrace() ;
        }
    }
    
    
    public void DBCPDelete() {
    	
    }
    public static void main(String[] args) {
    	DBCPTest dbt = new DBCPTest();
    	try {
			dbt.initialize();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	long startMili1=System.currentTimeMillis();// 当前时间对应的毫秒数
    	
    	try {
			dbt.DBCPConnWriteTest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	long endMili1=System.currentTimeMillis();
    	try {
			dbt.initialize();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	long startMili2=System.currentTimeMillis();// 当前时间对应的毫秒数

    	try {
			dbt.DBConnWrite1Test();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	long endMili2=System.currentTimeMillis();
    	try {
			dbt.initialize();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	long startMili3=System.currentTimeMillis();// 当前时间对应的毫秒数

    	try {
			dbt.DBConnWrite2Test();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	long endMili3=System.currentTimeMillis();
    	try {
			dbt.initialize();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	System.out.println("连接池模式总耗时为："+(endMili1-startMili1)+"毫秒");
    	System.out.println("直接连接每次插入数据使用一条连接总耗时为："+(endMili2-startMili2)+"毫秒");
    	System.out.println("直接连接始终使用同一条连接总耗时为："+(endMili3-startMili3)+"毫秒");
    }

}