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
public class SQL_VanChuyen {
    public static SQL_VanChuyen instance ;
    
    public static SQL_VanChuyen instance(){
        if(instance == null) instance = new SQL_VanChuyen();
        return instance;
    }
    
    public List<ENTITY.Entity_VanChuyen> runQuery(String Query, Object...paramenter){
        try {
            ResultSet rs = DataProvider.instance().ExcuteQuery(Query, paramenter);
            List<ENTITY.Entity_VanChuyen> lists = new ArrayList<>();
            
            while(rs.next()){
                ENTITY.Entity_VanChuyen x = new ENTITY.Entity_VanChuyen(
                        rs.getInt(1),
                        rs.getString(2));
                lists.add(x);
            }
            return lists;
        } catch (SQLException ex) {
            System.out.println("get list loi SHIP dao");
        }
        return null;
    }
    
    public int getIDNext(){
        String query = "SELECT IDENT_CURRENT('SHIPS')";
        return ((BigDecimal)DataProvider.instance().ExcuteScalaQuery(query)).intValue() + 1;
    }
    
    public boolean insert(ENTITY.Entity_VanChuyen item){
        String query = "INSERT INTO SHIPS(NAMEe) VALUES (?)";
        return DataProvider.instance().ExcuteNoneQuery(query, item.getName()) >0 ;
    }
    
    public boolean update(ENTITY.Entity_VanChuyen item){
        String query = "UPDATE SHIPS SET NAMEe = ? where id = ?";
        return DataProvider.instance().ExcuteNoneQuery(query, item.getName(), item.getId()) >0 ;        
    }
    
    public boolean delete(int id){
        String query = "update SHIPS set statuss = 0 where id = ?";
        return DataProvider.instance().ExcuteNoneQuery(query, id)>0;
    }
    
    public List<ENTITY.Entity_VanChuyen> getLists(){
        String query = "select * from SHIPS where statuss = 1";
        List<ENTITY.Entity_VanChuyen> lists = runQuery(query);
        return lists.size() > 0 ? lists: null;
    }
    
    public ENTITY.Entity_VanChuyen getByID(int id){
        String query = "select * form SHIPS where statuss = 1 and id =  ?";
        List<ENTITY.Entity_VanChuyen> lists = runQuery(query, id);
        return lists.size() > 0 ? lists.get(0): null;
    }
    
    public List<ENTITY.Entity_VanChuyen> getListsByName(String name){
        String query = "select * from SHIPS where namee like ?";
        List<ENTITY.Entity_VanChuyen> lists = runQuery(query,"%"+ name+"%");
        return lists.size() > 0 ? lists: null;
    }
    

}
