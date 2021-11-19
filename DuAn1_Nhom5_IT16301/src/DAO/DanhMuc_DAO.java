/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.DanhMucSp;

import Helper.jdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class DanhMuc_DAO extends Dao<DanhMucSp, String> {

    String insert = "Insert DANHMUCSANPHAM values (?,?,?)";
    String selectAll = "Select *from DANHMUCSANPHAM";
    String updateSql = "UPDATE DANHMUCSANPHAM SET TENDM =?,MOTA =? where MADM=?";
    String deleteSQL = "Delete from DANHMUCSANPHAM where MADM=?";
    String selectById = "SELECT*FROM DANHMUCSANPHAM WHERE MADM=?";

    @Override
    public void insert(DanhMucSp entity) {
        jdbcHelper.Update(insert, entity.getMaDM(), entity.getTenDm(), entity.getMoTa());
    }

    @Override
    public void update(DanhMucSp entity) {
        jdbcHelper.Update(updateSql, entity.getTenDm(), entity.getMoTa(), entity.getMaDM());
    }

    @Override
    public void delete(String key) {
        jdbcHelper.Update(deleteSQL, key);
    }

    @Override
    public List<DanhMucSp> selectAll() {
        List<DanhMucSp> list = this.selectBySql(selectAll);
        return list;
    }

    @Override
    public DanhMucSp selectById(String maDM) {
        List<DanhMucSp> list = this.selectBySql(selectById, maDM);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<DanhMucSp> selectBySql(String sql, Object... args) {
        try {
            List<DanhMucSp> list = new ArrayList<>();
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                DanhMucSp dm = new DanhMucSp();
                dm.setMaDM(rs.getString(1));
                dm.setTenDm(rs.getString(2));
                dm.setMoTa(rs.getString(3));

                list.add(dm);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
