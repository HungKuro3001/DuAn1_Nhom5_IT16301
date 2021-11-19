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
public class PhieuNhap {
    private String maPN;
    private String maSp;
    private int soluong;
    private BigDecimal donGia;
    private BigDecimal cong;
    private BigDecimal thanhtien;
    private Date ngayNhap;

    public PhieuNhap() {
    }

    public PhieuNhap(String maPN, String maSp, int soluong, BigDecimal donGia, BigDecimal cong, BigDecimal thanhtien, Date ngayNhap) {
        this.maPN = maPN;
        this.maSp = maSp;
        this.soluong = soluong;
        this.donGia = donGia;
        this.cong = cong;
        this.thanhtien = thanhtien;
        this.ngayNhap = ngayNhap;
    }

    public String getMaPN() {
        return maPN;
    }

    public void setMaPN(String maPN) {
        this.maPN = maPN;
    }

    public String getMaSp() {
        return maSp;
    }

    public void setMaSp(String maSp) {
        this.maSp = maSp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public BigDecimal getCong() {
        return cong;
    }

    public void setCong(BigDecimal cong) {
        this.cong = cong;
    }

    public BigDecimal getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(BigDecimal thanhtien) {
        this.thanhtien = thanhtien;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
    
}
