package DAO;

import Entity.SanPham;
import Helper.jdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tiến Mạnh
 */
public class SanPham_DAO extends Dao<SanPham, String> {

    String maLCL;
    double khoiLuong;
    String insert = "INSERT SANPHAM VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    String selectAll = "SELECT *FROM SANPHAM";
    String update = "UPDATE SANPHAM SET MALCL=?,MADM=?,TENSP=?,KHOILUONG=?,GIABAN=?,GIAMUAVAO=?,\n"
            + "TIENCONG=?,TRANGTHAI=?,SOLUONG=?,MOTA=? WHERE MASP=?";
    String deleteMem = "UPDATE SANPHAM SET TRANGTHAI =N'NGHỈ BÁN'WHERE MASP=?";
    String selectByTT ="SELECT*FROM SANPHAM WHERE TRANGTHAI=?";
    @Override
    public void insert(SanPham sp) {
        jdbcHelper.Update(insert, sp.getMaSP(), sp.getMaLCL(), sp.getMaDm(), sp.getTenSP(),
                sp.getKhoiLuong(), sp.getGiaBanRa(), sp.getGiaMuaVao(), sp.getTienCong(), sp.getTrangThai(), 0, sp.getMoTa());
    }

    public String getCL(String maLCL) throws SQLException {
        String maCL = null;
        String getCL = "SELECT MACL FROM LOAICHATLIEU\n"
                + "WHERE MALCL ="+ "'"+maLCL+"'";
       
        ResultSet rs = jdbcHelper.query(getCL);
        while (rs.next()) {            
            maCL =rs.getString(1);
        }
        return maCL;
    }
     
        public void updateSoLuong(String soLuong,String maSP) throws SQLException {
            
            String updateSoLuong ="UPDATE SANPHAM set SOLUONG=SOLUONG+" +soLuong+" where MASP = '"+maSP+"'";
            //System.out.println(""+updateSoLuong);
            jdbcHelper.Update(updateSoLuong);
        }
            public int selectSL(String maSP) throws SQLException {
                String sql="Select soluong from sanpham where masp='"+maSP+"'";
               ResultSet rs = jdbcHelper.query(sql);
               int SL =0;
            while (rs.next()) {
                SL = rs.getInt(1);
            }
            rs.getStatement().getConnection().close();
            return SL; 
 
    }
            
    
    
    public List<SanPham> selectByTT(String trangThai) {
        List<SanPham> list = this.selectBySql(selectByTT, trangThai);
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }
        public List<SanPham> search(String tenSP){
         String search ="EXEC SP_SEARCH "+"N'%"+tenSP+"%'";
         
        List<SanPham> listSP =this.selectBySql(search);
        return listSP;
    }

    public double[] calculateMoney(double khoiLuong, String maLCL) throws SQLException {
        double giaBanRa = 0, giaMuaVao = 0;
        String calculateMoney = "select (SELECT CONVERT(float,lcl.TUOI))/10000 *" + khoiLuong + " *lcl.GIABANRA as BANRA, "
                + "(SELECT CONVERT(float,lcl.TUOI))/10000 *" + khoiLuong + "*lcl.GIAMUAVAO AS MUAVAO "
                + "from LOAICHATLIEU lcl\n"
                + "where lcl.MALCL =" +"'"+maLCL+"'";
//        System.out.println(calculateMoney);
        ResultSet rs = jdbcHelper.query(calculateMoney);
        while (rs.next()) {
            giaBanRa = rs.getDouble(1);
            giaMuaVao = rs.getDouble(2);
        }
        return new double[]{giaBanRa, giaMuaVao};
    }
    public List<SanPham> top5SP(String ngayBatDau,String ngayKetThuc) throws SQLException{
        List<SanPham> listSP= new ArrayList<>();
        String top5SP ="";
        int soLuong;
        String sql ="exec SP_BCTK1 @NGAYBATDAU = '"+ngayBatDau+"',@NGAYKETTHUC ='"+ngayKetThuc+" 23:59:59.999'";
       // System.out.println(sql);
        ResultSet rs = jdbcHelper.query(sql);
        while(rs.next()){
            SanPham sp =new SanPham();
            top5SP =rs.getString(1);
            soLuong =rs.getInt(2);
            sp.setTenSP(top5SP);
            sp.setSoLuong(soLuong);
            listSP.add(sp);
        }
      return listSP;
    }

    @Override
    public void update(SanPham sp) {
        jdbcHelper.Update(update, sp.getMaLCL(), sp.getMaDm(), sp.getTenSP(),
                sp.getKhoiLuong(), sp.getGiaBanRa(), sp.getGiaMuaVao(), sp.getTienCong(), sp.getTrangThai(), 0, sp.getMoTa(), sp.getMaSP());
    }

    public void deleteMem(SanPham sp) {
        jdbcHelper.Update(deleteMem, sp.getTrangThai(), sp.getMaSP());
    }

    @Override
    public void delete(String key) {
    }

    @Override
    public List<SanPham> selectAll() {
        List<SanPham> listSP = this.selectBySql(selectAll);
        return listSP;
    }

    @Override
    public SanPham selectById(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List<SanPham> selectBySql(String sql, Object... args) {
        try {
            List<SanPham> listSP = new ArrayList<>();
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString(1));
                sp.setMaLCL(rs.getString(2));
                sp.setMaDm(rs.getString(3));
                sp.setTenSP(rs.getString(4));
                sp.setKhoiLuong(rs.getFloat(5));
                sp.setGiaBanRa(rs.getFloat(6));
                sp.setGiaMuaVao(rs.getFloat(7));
                sp.setTienCong(rs.getFloat(8));
                sp.setTrangThai(rs.getString(9));
                sp.setSoLuong(rs.getInt(10));
                sp.setMoTa(rs.getString(11));
                listSP.add(sp);
            }
            rs.getStatement().getConnection().close();
            return listSP;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private String getListOfArray(String sql, String[] cols, Object... args) {
        String maSP = null;
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                maSP = rs.getString(1);

            }
            rs.getStatement().getConnection().close();
            return maSP;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String maSP_TuSinh() {
        String sql = "{CALL MA_SP_TUSINH}";
        String[] cols = {"ma"};
        return this.getListOfArray(sql, cols);
    }

}
