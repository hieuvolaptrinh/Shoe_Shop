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
public class SQL_DanhMuc {
    public static SQL_DanhMuc instance ;
    
    public static SQL_DanhMuc instance(){
        if(instance == null) instance = new SQL_DanhMuc();
        return instance;
    }
    
    public List<ENTITY.Entity_DanhMuc> runQuery(String Query, Object...paramenter){
        try {
            ResultSet rs = DataProvider.instance().ExcuteQuery(Query, paramenter);
            List<ENTITY.Entity_DanhMuc> lists = new ArrayList<>();
            
            while(rs.next()){
                ENTITY.Entity_DanhMuc x = new ENTITY.Entity_DanhMuc(
                        rs.getInt(1),
                        rs.getString(2));
                lists.add(x);
            }
            return lists;
        } catch (SQLException ex) {
            System.out.println("get list loi category dao");
        }
        return null;
    }
    
    public int getIDNext(){
        String query = "SELECT IDENT_CURRENT('categories')";
        return ((BigDecimal)DataProvider.instance().ExcuteScalaQuery(query)).intValue() + 1;
    }
    
    public boolean insert(ENTITY.Entity_DanhMuc item){
        String query = "INSERT INTO CATEGORiES(NAMEe) VALUES (?)";
        return DataProvider.instance().ExcuteNoneQuery(query, item.getName()) >0 ;
    }
    public boolean insert(ENTITY.Entity_DanhMuc item, int idShoe){
        String query = "INSERT INTO DETAIL_CATEGORIES (ID_CATEGORY, ID_SHOE) VALUES (?, ?)";
        return DataProvider.instance().ExcuteNoneQuery(query, item.getId(), idShoe) >0 ;
    }
    
    public boolean update(ENTITY.Entity_DanhMuc item){
        String query = "UPDATE CATEGORiES SET NAMEe = ? where id = ?";
        return DataProvider.instance().ExcuteNoneQuery(query, item.getName(), item.getId()) >0 ;        
    }
    
    public boolean delete(int id){
        String query = "update CATEGORiES set statuss = 0 where id = ?";
        return DataProvider.instance().ExcuteNoneQuery(query, id)>0;
    }
    
    public List<ENTITY.Entity_DanhMuc> getLists(){
        String query = "select * from CATEGORiES where statuss = 1";
        List<ENTITY.Entity_DanhMuc> lists = runQuery(query);
        return lists.size() > 0 ? lists: null;
    }
    public List<ENTITY.Entity_DanhMuc> getLists(int id){
        String query = "select * from CATEGORiES, DETAIL_CATEGORIES where CATEGORIES.id = DETAIL_CATEGORIES.ID_CATEGORY and statuss = 1 and DETAIL_CATEGORIES.ID_SHOE = ?";
        List<ENTITY.Entity_DanhMuc> lists = runQuery(query, id);
        return lists.size() > 0 ? lists: null;
    }
    
    public ENTITY.Entity_DanhMuc getByID(int id){
        String query = "select * from CATEGORiES where statuss = 1 and id =  ?";
        List<ENTITY.Entity_DanhMuc> lists = runQuery(query, id);
        return lists.size() > 0 ? lists.get(0): null;
    }
    
    public List<ENTITY.Entity_DanhMuc> getListsByName(String name){
        String query = "select * from categories where namee like ?";
        List<ENTITY.Entity_DanhMuc> lists = runQuery(query,"%"+ name+"%");
        return lists.size() > 0 ? lists: null;
    }
    

}
