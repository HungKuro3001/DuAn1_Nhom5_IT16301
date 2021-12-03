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
    private double thanhtien;
    private Date ngayNhap;
    private String noiNhap;
    private String ghiChu;

    public PhieuNhap() {
    }

    public PhieuNhap(String maPN, double thanhtien, Date ngayNhap, String noiNhap, String ghiChu) {
        this.maPN = maPN;
        this.thanhtien = thanhtien;
        this.ngayNhap = ngayNhap;
        this.noiNhap = noiNhap;
        this.ghiChu = ghiChu;
    }

    public String getMaPN() {
        return maPN;
    }

    public void setMaPN(String maPN) {
        this.maPN = maPN;
    }

    public double getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(double thanhtien) {
        this.thanhtien = thanhtien;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getNoiNhap() {
        return noiNhap;
    }

    public void setNoiNhap(String noiNhap) {
        this.noiNhap = noiNhap;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    
    
}
