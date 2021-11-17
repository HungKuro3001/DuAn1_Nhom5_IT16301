/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class HoaDon {
    private String MaHD;
    private String maNV;
    private String maKH;
    private Date ngayGD;
    private boolean hinhThucThanhToan;
    private boolean hinhthucmua;
    private BigDecimal khachTra;
    private BigDecimal tongTien;
    private String TrangThaiHD;

    public HoaDon() {
    }

    public HoaDon(String MaHD, String maNV, String maKH, Date ngayGD, boolean hinhThucThanhToan, boolean hinhthucmua, BigDecimal khachTra, BigDecimal tongTien, String TrangThaiHD) {
        this.MaHD = MaHD;
        this.maNV = maNV;
        this.maKH = maKH;
        this.ngayGD = ngayGD;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.hinhthucmua = hinhthucmua;
        this.khachTra = khachTra;
        this.tongTien = tongTien;
        this.TrangThaiHD = TrangThaiHD;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public Date getNgayGD() {
        return ngayGD;
    }

    public void setNgayGD(Date ngayGD) {
        this.ngayGD = ngayGD;
    }

    public boolean isHinhThucThanhToan() {
        return hinhThucThanhToan;
    }

    public void setHinhThucThanhToan(boolean hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    public boolean isHinhthucmua() {
        return hinhthucmua;
    }

    public void setHinhthucmua(boolean hinhthucmua) {
        this.hinhthucmua = hinhthucmua;
    }

    public BigDecimal getKhachTra() {
        return khachTra;
    }

    public void setKhachTra(BigDecimal khachTra) {
        this.khachTra = khachTra;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public String getTrangThaiHD() {
        return TrangThaiHD;
    }

    public void setTrangThaiHD(String TrangThaiHD) {
        this.TrangThaiHD = TrangThaiHD;
    }
    
}
