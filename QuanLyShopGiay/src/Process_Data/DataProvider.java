 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process_Data;
import java.sql.*;
/**
 *
 * @author anreal
 */
public class DataProvider {
    public static DataProvider instance;
    public static DataProvider instance(){
        if(instance == null) instance = new DataProvider();
        return instance;
    }
    
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private ResultSetMetaData rms = null;
    
    private String user =  "sa";
    private String pass = "123456";
    private String databaseName = "QUANLYSHOPGIAY";
    
    public DataProvider(){
        try {
            String url = "jdbc:sqlserver://localhost\\ANREALSERVER:1433;"
                    + "user="+user+";password="+pass+";databaseName="+databaseName+";"
                    + "encrypt=false";

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            conn =  (Connection) DriverManager.getConnection(url);
            System.out.println("ket noi thanh cong");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("ket noi khong thanh cong");
        }
    }
    
    
    public ResultSet ExcuteQuery(String query, Object...paramenter){
        try {
            ps = conn.prepareStatement(query);
            for(int i = 1; i<= paramenter.length; i++){
                ps.setObject(i, paramenter[i-1]);
            }
            rs =  ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Loi excutequery dataprovide");
            ex.printStackTrace();
        }
        return rs;
    }
    
    public int  ExcuteNoneQuery(String query, Object...paramenter){
        try {
            ps = conn.prepareStatement(query);
            for(int i = 1; i<= paramenter.length; i++)
                ps.setObject(i, paramenter[i-1]);
            
            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Loi excute query");
        }
        return 0;
    }
    
    public Object ExcuteScalaQuery(String query, Object...paramenter){
        try {
            ps = conn.prepareStatement(query);
            for(int i = 1; i<= paramenter.length; i++)
                ps.setObject(i, paramenter[i-1]);
            
            rs = ps.executeQuery();
            while(rs.next())
                return rs.getObject(1);
        } catch (SQLException ex) {
            System.out.println("Loi excute query");
        }
        return null;
    }
    
}
