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
public class ChatLieu_DAO extends Dao<ChatLieu, String> {

    String selectAll = "Select *from CHATLIEU";
    String insert = "INSERT CHATLIEU VALUES (?,?,1,?)";
    String update = "UPDATE CHATLIEU SET TENCL=?,MOTA=? WHERE MACL=?";
    String block = "UPDATE CHATLIEU SET TRANGTHAI=? WHERE MACL=?";
    String selectByTT = "Select *from CHATLIEU WHERE TRANGTHAI =1";
    public void block(ChatLieu entity) {
        jdbcHelper.Update(block,entity.isTrangThai(), entity.getMaCL());
    }

    @Override
    public void insert(ChatLieu entity) {
        jdbcHelper.Update(insert, entity.getMaCL(), entity.getTenCL(), entity.getMoTa());
    }

    @Override
    public void update(ChatLieu entity) {
        jdbcHelper.Update(update, entity.getTenCL(), entity.getMoTa(), entity.getMaCL());
    }

    @Override
    public void delete(String key) {

    }

    @Override
    public List<ChatLieu> selectAll() {
        List<ChatLieu> list = this.selectBySql(selectAll);
        return list;
    }

    @Override
    public ChatLieu selectById(String key) {
        return null;
    }
   public List<ChatLieu> selectByTT() {
        List<ChatLieu> list = this.selectBySql(selectByTT);
        return list;
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
                cl.setTrangThai(rs.getBoolean(3));
                cl.setMoTa(rs.getString(4));

                list.add(cl);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
