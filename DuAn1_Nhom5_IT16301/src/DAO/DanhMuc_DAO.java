/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.DanhMuc;

import Helper.jdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class DanhMuc_DAO extends Dao<DanhMuc, String> {

    String insert = "Insert DANHMUCSANPHAM values (?,?,0,?)";
    String selectAll = "Select *from DANHMUCSANPHAM";
    String updateSql = "UPDATE DANHMUCSANPHAM SET TENDM =?,MOTA =? where MADM=?";
    String deleteSQL = "Delete from DANHMUCSANPHAM where MADM=?";
    String selectById = "SELECT*FROM DANHMUCSANPHAM WHERE MADM=?";
    String updateTT = "Update DANHMUCSANPHAM set TRANGTHAI=? where MADM=? ";

    @Override
    public void insert(DanhMuc entity) {
        jdbcHelper.Update(insert, entity.getMaDM(), entity.getTenDm(),entity.getMoTa());
    }

    @Override
    public void update(DanhMuc entity) {
        jdbcHelper.Update(updateSql, entity.getTenDm(), entity.getMoTa(), entity.getMaDM());
    }
    public void updateTT(DanhMuc entity) {
        jdbcHelper.Update(updateTT, entity.isTrangThai(), entity.getMaDM());
    }
    @Override
    public void delete(String key) {
        jdbcHelper.Update(deleteSQL, key);
    }

    @Override
    public List<DanhMuc> selectAll() {
        List<DanhMuc> list = this.selectBySql(selectAll);
        return list;
    }

    @Override
    public DanhMuc selectById(String maDM) {
        List<DanhMuc> list = this.selectBySql(selectById, maDM);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<DanhMuc> selectBySql(String sql, Object... args) {
        try {
            List<DanhMuc> list = new ArrayList<>();
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                DanhMuc dm = new DanhMuc();
                dm.setMaDM(rs.getString(1));
                dm.setTenDm(rs.getString(2));
                dm.setMoTa(rs.getString(4));
                dm.setTrangThai(rs.getBoolean(3));
                list.add(dm);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
