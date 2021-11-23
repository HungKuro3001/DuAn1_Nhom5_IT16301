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
public class LoaiChatLieu {
    private String maLCL;
    private String maCL;
    private String tenLCL;
    private double giaBan;
    private double giaMua;
    private int tuoi;

    public LoaiChatLieu() {
    }

    public LoaiChatLieu(String maLCL, String maCL, String tenLCL, double giaBan, double giaMua, int tuoi) {
        this.maLCL = maLCL;
        this.maCL = maCL;
        this.tenLCL = tenLCL;
        this.giaBan = giaBan;
        this.giaMua = giaMua;
        this.tuoi = tuoi;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    
    public String getMaLCL() {
        return maLCL;
    }

    public void setMaLCL(String maLCL) {
        this.maLCL = maLCL;
    }

    public String getMaCL() {
        return maCL;
    }

    public void setMaCL(String maCL) {
        this.maCL = maCL;
    }

    public String getTenLCL() {
        return tenLCL;
    }

    public void setTenLCL(String tenLCL) {
        this.tenLCL = tenLCL;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public double getGiaMua() {
        return giaMua;
    }

    public void setGiaMua(double giaMua) {
        this.giaMua = giaMua;
    }
    
}
