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
public class SQL_Anh {
    public static SQL_Anh instance ;
    
    public static SQL_Anh instance(){
        if(instance == null) instance = new SQL_Anh();
        return instance;
    }
    
    public List<ENTITY.Entity_Anh> runQuery(String Query, Object...paramenter){
        try {
            ResultSet rs = DataProvider.instance().ExcuteQuery(Query, paramenter);
            List<ENTITY.Entity_Anh> lists = new ArrayList<>();
            
            while(rs.next()){
                ENTITY.Entity_Anh x = new ENTITY.Entity_Anh(
                        rs.getString(1),
                        rs.getInt(2));
                lists.add(x);
            }
            return lists;
        } catch (SQLException ex) {
            System.out.println("get list loi IMAGES dao");
        }
        return null;
    }
    
    public int getIDNext(){
        String query = "SELECT IDENT_CURRENT('IMAGES')";
        return ((BigDecimal)DataProvider.instance().ExcuteScalaQuery(query)).intValue() ;
    }
    
    public boolean insert(ENTITY.Entity_Anh item){
        String query = "INSERT INTO IMAGES " +
            "VALUES (?,?)";
        return DataProvider.instance().ExcuteNoneQuery(query, item.getId(), item.getIdGiay()) >0 ;
    }
    
//    public boolean update(ENTITY.Entity_Anh item){
//        String query = "UPDATE IMAGES SET ID = ?, ID_DETAIL_SHOE = ? where id = ?";
//        return DataProvider.instance().ExcuteNoneQuery(query, item.getName(), item.getId()) >0 ;        
//    }
    
//    public boolean delete(int id){
//        String query = "update IMAGES set statuss = 0 where id = ?";
//        return DataProvider.instance().ExcuteNoneQuery(query, id)>0;
//    }
    
    public List<ENTITY.Entity_Anh> getLists(int idShoesDetailt){
        String query = "select * from IMAGES where ID_DETAIL_SHOE = ?";
        List<ENTITY.Entity_Anh> lists = runQuery(query, idShoesDetailt);
        return lists.size() > 0 ? lists: null;
    }
    
    public ENTITY.Entity_Anh getByID(int id){
        String query = "select * from IMAGES where id =  ?";
        List<ENTITY.Entity_Anh> lists = runQuery(query, id);
        return lists.size() > 0 ? lists.get(0): null;
    }
    
    

}
