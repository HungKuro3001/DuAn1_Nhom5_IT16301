/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.ChatLieu;
import Helper.jdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class ChatLieu_DAO extends Dao<ChatLieu, String>{
    String selectAll = "Select *from CHATLIEU";
    String insert    = "INSERT CHATLIEU VALUES (?,?,?,?,?)";
    String update =    "UPDATE CHATLIEU SET TENCL=?,GIABAN=?,GIAMUA=?,MOTA=? WHERE MACL=?";
    String delete =    "DELETE FROM CHATLIEU WHERE MACL=?";
    

    @Override
    public void insert(ChatLieu entity) {
        jdbcHelper.Update(insert, entity.getMaCL(),entity.getTenCL(),entity.getGiaBan(),entity.getGiaMua(),entity.getMoTa());
    }

    @Override
    public void update(ChatLieu entity) {
        jdbcHelper.Update(update, entity.getTenCL(),entity.getGiaBan(),entity.getGiaMua(),entity.getMoTa(),entity.getMaCL());
    }

    @Override
    public void delete(String key) {
        jdbcHelper.Update(delete, key);
    }

    @Override
    public List<ChatLieu> selectAll() {
        List<ChatLieu> list = this.selectBySql(selectAll);
        return list;
    }

    @Override
    public ChatLieu selectById(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
        
  
    @Override
    protected List<ChatLieu> selectBySql(String sql, Object... args) {
        try {
            List<ChatLieu> list = new ArrayList<>();
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                ChatLieu cl = new ChatLieu();
                cl.setMaCL(rs.getString(1));
                cl.setTenCL(rs.getString(2));
                cl.setGiaBan(rs.getDouble(3));
                cl.setGiaMua(rs.getDouble(4));
                cl.setMoTa(rs.getString(5));

                list.add(cl);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    
}
