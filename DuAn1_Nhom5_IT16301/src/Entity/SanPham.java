/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Admin
 */
public class SanPham {
    private String maSP;
    private String maDm;
    private String maLCL;
    private String tenSP;
    private float khoiLuong;
    private float giaMuaVao;
    private float giaBanRa;
    private float tienCong;
    private String trangThai;
    private int soLuong;
    private String moTa;

    public SanPham() {
    }

    public SanPham(String maSP, String maDm, String maLCL, String tenSP, float khoiLuong, float giaMuaVao, float giaBanRa, float tienCong, String trangThai, int soLuong, String moTa) {
        this.maSP = maSP;
        this.maDm = maDm;
        this.maLCL = maLCL;
        this.tenSP = tenSP;
        this.khoiLuong = khoiLuong;
        this.giaMuaVao = giaMuaVao;
        this.giaBanRa = giaBanRa;
        this.tienCong = tienCong;
        this.trangThai = trangThai;
        this.soLuong = soLuong;
        this.moTa = moTa;
    }

    public String getMaLCL() {
        return maLCL;
    }

    public void setMaLCL(String maLCL) {
        this.maLCL = maLCL;
    }

    public float getKhoiLuong() {
        return khoiLuong;
    }

    public void setKhoiLuong(float khoiLuong) {
        this.khoiLuong = khoiLuong;
    }

    public float getGiaMuaVao() {
        return giaMuaVao;
    }

    public void setGiaMuaVao(float giaMuaVao) {
        this.giaMuaVao = giaMuaVao;
    }

    public float getGiaBanRa() {
        return giaBanRa;
    }

    public void setGiaBanRa(float giaBanRa) {
        this.giaBanRa = giaBanRa;
    }

    public float getTienCong() {
        return tienCong;
    }

    public void setTienCong(float tienCong) {
        this.tienCong = tienCong;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

   

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMaDm() {
        return maDm;
    }

    public void setMaDm(String maDm) {
        this.maDm = maDm;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
}
