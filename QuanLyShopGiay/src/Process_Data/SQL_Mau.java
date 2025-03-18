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
public class SQL_Mau {
    public static SQL_Mau instance ;
    
    public static SQL_Mau instance(){
        if(instance == null) instance = new SQL_Mau();
        return instance;
    }
    
    public List<ENTITY.Entity_Mau> runQuery(String Query, Object...paramenter){
        try {
            ResultSet rs = DataProvider.instance().ExcuteQuery(Query, paramenter);
            List<ENTITY.Entity_Mau> lists = new ArrayList<>();
            
            while(rs.next()){
                ENTITY.Entity_Mau x = new ENTITY.Entity_Mau(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3));
                lists.add(x);
            }
            return lists;
        } catch (SQLException ex) {
            System.out.println("get list loi COLORS dao");
        }
        return null;
    }
    
    public int getIDNext(){
        String query = "SELECT IDENT_CURRENT('COLORS')";
        return ((BigDecimal)DataProvider.instance().ExcuteScalaQuery(query)).intValue() ;
    }
    
    public boolean insert( int idColor, int idDetailShoe){
        String query = "INSERT INTO DETAIL_COLORS (ID_DETAIL_SHOE, ID_COLOR) " +
                        "VALUES (?,?)";
        return DataProvider.instance().ExcuteNoneQuery(query, idDetailShoe, idColor) >0 ;
    }
    
//    public boolean update(ENTITY.Entity_Mau item){
//        String query = "UPDATE COLORS SET ID = ?, ID_DETAIL_SHOE = ? where id = ?";
//        return DataProvider.instance().ExcuteNoneQuery(query, item.getName(), item.getId()) >0 ;        
//    }
    
//    public boolean delete(int id){
//        String query = "update COLORS set statuss = 0 where id = ?";
//        return DataProvider.instance().ExcuteNoneQuery(query, id)>0;
//    }
    
    public List<ENTITY.Entity_Mau> getLists(int idShoesDetailt){
        String query = "select COLORS.* from DETAIL_COLORS, COLORS  where  DETAIL_COLORS.ID_COLOR = COLORS.ID and DETAIL_COLORS.ID_DETAIL_SHOE = ?";
        List<ENTITY.Entity_Mau> lists = runQuery(query, idShoesDetailt);
        return lists.size() > 0 ? lists: null;
    }
    
    public List<ENTITY.Entity_Mau> getLists(){
        String query = "select COLORS.* from COLORS ";
        List<ENTITY.Entity_Mau> lists = runQuery(query);
        return lists.size() > 0 ? lists: null;
    }
    
    public ENTITY.Entity_Mau getByID(int id){
        String query = "select * from COLORS where id =  ?";
        List<ENTITY.Entity_Mau> lists = runQuery(query, id);
        return lists.size() > 0 ? lists.get(0): null;
    }
    
    public ENTITY.Entity_Mau getByCode(String code){
        String query = "select * from COLORS where CODECOLOR =  ?";
        List<ENTITY.Entity_Mau> lists = runQuery(query, code);
        return lists.size() > 0 ? lists.get(0): null;
    }

}
