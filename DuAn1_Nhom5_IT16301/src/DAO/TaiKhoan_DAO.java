/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.TaiKhoan;
import Helper.jdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class TaiKhoan_DAO extends Dao<TaiKhoan, String> {

    String updateSql = "UPDATE TAIKHOAN SET USERNAME=?, PASSWORD=?,ROLE=?, MANV=?,TRANGTHAI=? where IDTK=?";
    String updatePassWord = "UPDATE TAIKHOAN SET PASSWORD=? where IDTK=?";
    String selectById = "SELECT*FROM TAIKHOAN WHERE MaNV=?";

    @Override
    public void insert(TaiKhoan entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TaiKhoan entity) {
        jdbcHelper.Update(updateSql, entity.getUserName(), entity.getPassWord(),
                entity.isRole(), entity.getMaNV(), entity.isTrangThai(), entity.getIDTK());
    }

    @Override
    public void delete(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TaiKhoan> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TaiKhoan selectById(String manv) {
        List<TaiKhoan> list = this.selectBySql(selectById, manv);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<TaiKhoan> selectBySql(String sql, Object... args) {
        try {
            List<TaiKhoan> list = new ArrayList<>();
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setIDTK(rs.getInt(1));
                tk.setUserName(rs.getString(2));
                tk.setPassWord(rs.getString(3));
                tk.setRole(rs.getBoolean(4));
                tk.setMaNV(rs.getString(5));
                tk.setTrangThai(rs.getBoolean(6));
                list.add(tk);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    
}
