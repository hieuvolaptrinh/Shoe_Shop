/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process_Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

/**
 *
 * @author lengo
 */
public class SQL_Giay {

    public static SQL_Giay instance;

    public static SQL_Giay instance() {
        if (instance == null) {
            instance = new SQL_Giay();
        }
        return instance;
    }

    public List<ENTITY.Entity_Giay> runQuery(String Query, Object... paramenter) {
        try {
            ResultSet rs = DataProvider.instance().ExcuteQuery(Query, paramenter);
            List<ENTITY.Entity_Giay> lists = new ArrayList<>();

            while (rs.next()) {
                ENTITY.Entity_Giay x = new ENTITY.Entity_Giay(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(5));
                lists.add(x);
            }
            return lists;
        } catch (SQLException ex) {
            System.out.println("get list loi SHOES dao");
        }
        return null;
    }

    public int getIDNext() {
        String query = "SELECT IDENT_CURRENT('SHOES')";
        return ((BigDecimal) DataProvider.instance().ExcuteScalaQuery(query)).intValue();
    }

    public boolean insert(ENTITY.Entity_Giay item) {
        String query = "INSERT INTO SHOES (NAMEE, DETAIL, ID_TRADEMARK) "
                + "VALUES (?,?,?)";
        return DataProvider.instance().ExcuteNoneQuery(query, item.getName(), item.getDetail(), item.getIdNhan()) > 0;
    }

    public boolean update(ENTITY.Entity_Giay item) {
        String query = "UPDATE SHOES SET NAMEe = ?, DETAIL = ?, ID_TRADEMARK = ? where id = ?";
        return DataProvider.instance().ExcuteNoneQuery(query,
                item.getName(),
                item.getDetail(),
                item.getIdNhan(),
                item.getId()) > 0;
    }

    public boolean delete(int id) {
        String query = "update SHOES set statuss = 0 where id = ?";
        return DataProvider.instance().ExcuteNoneQuery(query, id) > 0;
    }

    public List<ENTITY.Entity_Giay> getLists() {
        String query = "select * from SHOES where statuss = 1";
        List<ENTITY.Entity_Giay> lists = runQuery(query);
        return lists.size() > 0 ? lists : null;
    }

    public List<ENTITY.Entity_Giay> getListsBYDK(String queryCate, String queryColor, String querySize, String queryTextSearch) {
        String query = "select * from SHOES "
                + "       where statuss = 1 " + queryCate + queryTextSearch + querySize + queryColor;
        List<ENTITY.Entity_Giay> lists = runQuery(query);
        return lists.size() > 0 ? lists : null;
    }

    public ENTITY.Entity_Giay getByID(int id) {
        String query = "select * from SHOES where statuss = 1 and id =  ?";
        List<ENTITY.Entity_Giay> lists = runQuery(query, id);
        return lists.size() > 0 ? lists.get(0) : null;
    }

    public List<ENTITY.Entity_Giay> getListsByName(String name) {
        String query = "select * from SHOES where namee like ?";
        List<ENTITY.Entity_Giay> lists = runQuery(query, "%" + name + "%");
        return lists.size() > 0 ? lists : null;
    }

    
}
