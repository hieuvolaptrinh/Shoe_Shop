/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process_Data;

import java.math.BigDecimal;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author lengo
 */
public class SQL_NhanHang {
    public static SQL_NhanHang instance ;
    
    public static SQL_NhanHang instance(){
        if(instance == null) instance = new SQL_NhanHang();
        return instance;
    }
    
    public List<ENTITY.Entity_NhanHang> runQuery(String Query, Object...paramenter){
        try {
            ResultSet rs = DataProvider.instance().ExcuteQuery(Query, paramenter);
            List<ENTITY.Entity_NhanHang> lists = new ArrayList<>();
            
            while(rs.next()){
                ENTITY.Entity_NhanHang x = new ENTITY.Entity_NhanHang(
                        rs.getInt(1),
                        rs.getString(2));
                lists.add(x);
            }
            return lists;
        } catch (SQLException ex) {
            System.out.println("get list loi trademarks dao");
        }
        return null;
    }
    
    public int getIDNext(){
        String query = "SELECT IDENT_CURRENT('trademarks')";
        return ((BigDecimal)DataProvider.instance().ExcuteScalaQuery(query)).intValue() + 1;
    }
    
    public boolean insert(ENTITY.Entity_NhanHang item){
        String query = "INSERT INTO trademarks(NAMEe) VALUES (?)";
        return DataProvider.instance().ExcuteNoneQuery(query, item.getName()) >0 ;
    }
    
    public boolean update(ENTITY.Entity_NhanHang item){
        String query = "UPDATE trademarks SET NAMEe = ? where id = ?";
        return DataProvider.instance().ExcuteNoneQuery(query, item.getName(), item.getId()) >0 ;        
    }
    
    public boolean delete(int id){
        String query = "update trademarks set statuss = 0 where id = ?";
        return DataProvider.instance().ExcuteNoneQuery(query, id)>0;
    }
    
    public List<ENTITY.Entity_NhanHang> getLists(){
        String query = "select * from trademarks where statuss = 1";
        List<ENTITY.Entity_NhanHang> lists = runQuery(query);
        return lists.size() > 0 ? lists: null;
    }
    
    public ENTITY.Entity_NhanHang getByID(int id){
        String query = "select * from trademarks where statuss = 1 and id =  ?";
        List<ENTITY.Entity_NhanHang> lists = runQuery(query, id);
        return lists.size() > 0 ? lists.get(0): null;
    }
    
    public List<ENTITY.Entity_NhanHang> getListsByName(String name){
        String query = "select * from trademarks where namee like ?";
        List<ENTITY.Entity_NhanHang> lists = runQuery(query,"%"+ name+"%");
        return lists.size() > 0 ? lists: null;
    }
    

}
