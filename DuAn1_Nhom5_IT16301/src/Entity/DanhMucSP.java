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
public class DanhMucSP {
    private String maDM;
    private String tenDm;
    private String moTa;

    public DanhMucSP() {
    }

    public DanhMucSP(String maDM, String tenDm, String moTa) {
        this.maDM = maDM;
        this.tenDm = tenDm;
        this.moTa = moTa;
    }

    public String getMaDM() {
        return maDM;
    }

    public void setMaDM(String maDM) {
        this.maDM = maDM;
    }

    public String getTenDm() {
        return tenDm;
    }

    public void setTenDm(String tenDm) {
        this.tenDm = tenDm;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
}
