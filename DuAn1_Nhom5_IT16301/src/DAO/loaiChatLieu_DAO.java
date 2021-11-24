/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.ChatLieu;
import Entity.LoaiChatLieu;
import Helper.jdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class loaiChatLieu_DAO extends Dao<LoaiChatLieu, String> {

    String selectbycl = "SELECT *FROM LOAICHATLIEU WHERE MACL=?";
    String insert = "INSERT LOAICHATLIEU VALUES (?,?,?,?,?,?)";
    String update = "UPDATE LOAICHATLIEU SET MACL=?, TENLCL=?, GIABANRA=?,GIAMUAVAO=?, TUOI=? WHERE MALCL=?";
    String delete = "DELETE FROM LOAICHATLIEU WHERE MALCL=?";
    String selectbyMalcl = "SELECT * FROM LOAICHATLIEU WHERE MALCL=?";
    @Override

    public void insert(LoaiChatLieu entity) {
        jdbcHelper.Update(insert, entity.getMaLCL(),entity.getMaCL(),entity.getTenLCL(),entity.getGiaBan(),entity.getGiaMua(),entity.getTuoi());
    }

    @Override
    public void update(LoaiChatLieu entity) {
        jdbcHelper.Update(update, entity.getMaCL(),entity.getTenLCL(),entity.getGiaBan(),entity.getGiaMua(),entity.getTuoi(),entity.getMaLCL());
    }

    @Override
    public void delete(String key) {
        jdbcHelper.Update(delete, key);
    }

    @Override
    public List<LoaiChatLieu> selectAll() {
        return null;       
    }

    public List<LoaiChatLieu> selectBycl(String key) {
        List<LoaiChatLieu> list = this.selectBySql(selectbycl, key);
        return list;
    }

    @Override
    protected List<LoaiChatLieu> selectBySql(String sql, Object... args) {
        try {
            List<LoaiChatLieu> list = new ArrayList<>();
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                LoaiChatLieu lcl = new LoaiChatLieu();
                lcl.setMaLCL(rs.getString(1));
                lcl.setMaCL(rs.getString(2));
                lcl.setTenLCL(rs.getString(3));
                lcl.setGiaBan(rs.getDouble(4));
                lcl.setGiaMua(rs.getDouble(5));
                lcl.setTuoi(rs.getInt(6));

                list.add(lcl);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public LoaiChatLieu selectById(String key) {
        List<LoaiChatLieu> list = this.selectBySql(selectbyMalcl, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

}
