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
public class SQL_Sale {
    public static SQL_Sale instance ;
    
    public static SQL_Sale instance(){
        if(instance == null) instance = new SQL_Sale();
        return instance;
    }
    
    public List<ENTITY.Entity_Sale> runQuery(String Query, Object...paramenter){
        try {
            ResultSet rs = DataProvider.instance().ExcuteQuery(Query, paramenter);
            List<ENTITY.Entity_Sale> lists = new ArrayList<>();
            
            while(rs.next()){
                ENTITY.Entity_Sale x = new ENTITY.Entity_Sale(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDouble(4),
                        rs.getDate(6).toLocalDate(),
                        rs.getDate(7).toLocalDate(),
                        rs.getString(8),
                        rs.getString(5));
                lists.add(x);
            }
            return lists;
        } catch (SQLException ex) {
            System.out.println("get list loi sell dao");
        }
        return null;
    }
    
    public int getIDNext(){
        String query = "SELECT IDENT_CURRENT('SELLS_VOUCHERS')";
        return ((BigDecimal)DataProvider.instance().ExcuteScalaQuery(query)).intValue() + 1;
    }
    
    public boolean insert(ENTITY.Entity_Sale item){
        String query = "insert into SELLS_VOUCHERS (namee, COUNTT, giam, EFFECTIVE_TIME, EXPIRATION_TIME, TYPEE, CONDITION_APPLY) " +
"values (?,?,?,?,?,?, ?)";
        return DataProvider.instance().ExcuteNoneQuery(query,
                item.getName(),
                item.getCount(),
                item.getGiam(),
                item.getDateB(),
                item.getDateE(),
                item.getType(),
                item.getMoTa()) >0 ;
    }
    
    public boolean update(ENTITY.Entity_Sale item){
        String query = "UPDATE SELLS_VOUCHERS "
                + "SET NAMEe = ? ,"
                + "countt = ? ,"
                + "giam = ? ,"
                + "EFFECTIVE_TIME = ? ,"
                + "EXPIRATION_TIME = ? ,"
                + "TYPEE = ? ,"
                + "CONDITION_APPLY = ? "
                + " where id = ?";
        return DataProvider.instance().ExcuteNoneQuery(query,
                item.getName(),
                item.getCount(),
                item.getGiam(),
                item.getDateB(),
                item.getDateE(),
                item.getType(),
                item.getMoTa(),
                item.getId()) >0 ;        
    }
    
    public boolean delete(int id){
        String query = "update SELLS_VOUCHERS set statuss = 0 where id = ?";
        return DataProvider.instance().ExcuteNoneQuery(query, id)>0;
    }
    
    public List<ENTITY.Entity_Sale> getLists(){
        String query = "select * from SELLS_VOUCHERS where statuss = 1 and EXPIRATION_TIME >= getdate()";
        List<ENTITY.Entity_Sale> lists = runQuery(query);
        return lists.size() > 0 ? lists: null;
    }
    
    public List<ENTITY.Entity_Sale> getListsHetHan(){
        String query = "select * from SELLS_VOUCHERS where statuss = 1 and EXPIRATION_TIME < getdate()";
        List<ENTITY.Entity_Sale> lists = runQuery(query);
        return lists.size() > 0 ? lists: null;
    }
    
    public ENTITY.Entity_Sale getByID(int id){
        String query = "select * from SELLS_VOUCHERS where statuss = 1 and id =  ?";
        List<ENTITY.Entity_Sale> lists = runQuery(query, id);
        return lists.size() > 0 ? lists.get(0): null;
    }
    
    public List<ENTITY.Entity_Sale> getListsByQuery(String query1){
        String query = "select * from SELLS_VOUCHERS where statuss = 1 " + query1;
        List<ENTITY.Entity_Sale> lists = runQuery(query);
        return lists.size() > 0 ? lists: null;
    }
    

}
