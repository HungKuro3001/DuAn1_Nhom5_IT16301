/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.PhieuNhap_DAO;
import DAO.chiTietPhieuNhap_DAO;
import Entity.ChiTietPhieuNhap;
import Entity.PhieuNhap;
import Utils.Msgbox;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class QLPhieuNhap extends javax.swing.JPanel {

    private PhieuNhap_DAO pnDao = new PhieuNhap_DAO();
    private List<PhieuNhap> listPn = new ArrayList<>();
    private chiTietPhieuNhap_DAO ctPNDao= new chiTietPhieuNhap_DAO();
    private List<ChiTietPhieuNhap> listCTPn= new ArrayList<>();

    /**
     * Creates new form QLPhieuNhap
     */
    public QLPhieuNhap() throws SQLException {
        initComponents();
        String maPn = pnDao.maPDT_TuSinh();
        txtMaPn.setText(maPn);
        txtThanhTien.setText("0");
        fillTable();
    }
    
    public void Clear() throws SQLException{
        String maPn = pnDao.maPDT_TuSinh();
        txtMaPn.setText(maPn);
        txtThanhTien.setText("0");
        txtNgayNhap.setText("");
        txtNoiNhap.setText("");
        txtGhiChu.setText("");
    }

    public PhieuNhap getForm() throws ParseException {
        PhieuNhap pn = new PhieuNhap();
        pn.setMaPN(txtMaPn.getText());
        pn.setThanhtien(Double.parseDouble(txtThanhTien.getText()));
        SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
        Date ngayNhap = sfd.parse(txtNgayNhap.getText());
        pn.setNgayNhap(ngayNhap);
        pn.setNoiNhap(txtNoiNhap.getText());
        pn.setGhiChu(txtGhiChu.getText());
        return pn;
    }
    public ChiTietPhieuNhap getFormCT(){
        ChiTietPhieuNhap ctpn= new ChiTietPhieuNhap();
        ctpn.setMaPN(txtMaPn.getText());
        ctpn.setMaSp(cbxMaSp.getSelectedItem()+"");
        ctpn.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        ctpn.setDonGia(Double.parseDouble(txtDonGia.getText()));
        ctpn.setCong(Double.parseDouble(txtTienCong.getText()));
        ctpn.setThanhTien(Double.parseDouble(txtTien.getText()));
        return ctpn;
    }

    public void setForm(PhieuNhap pn) {
        txtMaPn.setText(pn.getMaPN());
        txtNgayNhap.setText(pn.getNgayNhap() + "");
        txtThanhTien.setText(pn.getThanhtien() + "");
        txtNoiNhap.setText(pn.getNoiNhap());
        txtGhiChu.setText(pn.getGhiChu());
    }
    public void setFormCTPN(ChiTietPhieuNhap ctpn){
        cbxMaSp.setSelectedItem(ctpn.getMaPN());
        txtSoLuong.setText(ctpn.getSoLuong()+"");
        txtDonGia.setText(ctpn.getDonGia()+"");
        txtTienCong.setText(ctpn.getCong()+"");
        txtTien.setText(ctpn.getThanhTien()+"");
    }

    public void fillTable() {
        listPn = pnDao.selectAll();
        DefaultTableModel model = (DefaultTableModel) tblPhieuNhap.getModel();
        model.setRowCount(0);
        for (PhieuNhap phieuNhap : listPn) {
            Object[] row = new Object[]{
                phieuNhap.getMaPN(), phieuNhap.getThanhtien(), phieuNhap.getNgayNhap(),
                phieuNhap.getNoiNhap(), phieuNhap.getGhiChu()
            };
            model.addRow(row);
        }
    }
    public void fillTableChiTiet(){
        listCTPn= ctPNDao.selectByMAPN(txtMaPn.getText());
        DefaultTableModel model = (DefaultTableModel) tblChiTietPN.getModel();
        model.setRowCount(0);
        for (ChiTietPhieuNhap ctpn : listCTPn) {
            Object[] row= new Object[]{
                ctpn.getMaPN(),ctpn.getMaSp(),ctpn.getSoLuong(),ctpn.getDonGia(),
                ctpn.getCong(),ctpn.getThanhTien()
            };
            model.addRow(row);
        }
    }

    public void insert() {
        try {
            SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
            Date ngayNhap = sfd.parse(txtNgayNhap.getText());
            if (txtNgayNhap.getText().isEmpty()) {
                Msgbox.alert(this, "Không để trống ngày nhập");
                return;
            }
            if (txtNoiNhap.getText().isEmpty() || txtNoiNhap.getText().length() > 50) {
                Msgbox.alert(this, "không để trống nơi nhập và tối đa 50 kí tự");
                return;
            }
            PhieuNhap pn = getForm();
            pnDao.insert(pn);
             Msgbox.alert(this, "Thêm thành công");
            fillTable();
            Clear();
        } catch (Exception e) {
            e.printStackTrace();
            Msgbox.alert(this, "Ngày nhập định dạng: yyyy-MM-dd ");
            return;
        }

    }

    public void update() {
        try {
            SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
            Date ngayNhap = sfd.parse(txtNgayNhap.getText());
            if (txtNgayNhap.getText().isEmpty()) {
                Msgbox.alert(this, "Không để trống ngày nhập");
                return;
            }
            if (txtNoiNhap.getText().isEmpty() || txtNoiNhap.getText().length() > 50) {
                Msgbox.alert(this, "không để trống nơi nhập và tối đa 50 kí tự");
                return;
            }
            PhieuNhap pn = getForm();
            pnDao.update(pn);
            Msgbox.alert(this, "Cập nhật thành công");
            fillTable();
            Clear();
        } catch (Exception e) {
            e.printStackTrace();
            Msgbox.alert(this, "Ngày nhập định dạng: yyyy-MM-dd ");
            return;
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMaPn = new javax.swing.JTextField();
        txtThanhTien = new javax.swing.JTextField();
        txtNgayNhap = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNoiNhap = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPhieuNhap = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbxMaSp = new javax.swing.JComboBox<>();
        txtSoLuong = new javax.swing.JTextField();
        txtDonGia = new javax.swing.JTextField();
        txtTienCong = new javax.swing.JTextField();
        txtTien = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblChiTietPN = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 204, 204));
        jLabel11.setText("Quản lý phiếu nhập");

        jLabel1.setText("Mã phiếu nhập");

        jLabel2.setText("Thành tiền");

        jLabel3.setText("Ngày nhập");

        jLabel4.setText("Nơi nhập");

        jLabel5.setText("ghi chú");

        txtMaPn.setEditable(false);

        txtThanhTien.setEditable(false);

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane1.setViewportView(txtGhiChu);

        txtNoiNhap.setColumns(20);
        txtNoiNhap.setRows(5);
        jScrollPane2.setViewportView(txtNoiNhap);

        tblPhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Phiếu nhập", "Thành Tiền", "Ngày nhập", "Nơi nhập", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuNhapMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblPhieuNhap);

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Thông tin chi tiết");

        jLabel7.setText("Mã Sản Phẩm");

        jLabel8.setText("Số lượng");

        jLabel9.setText("Đơn giá");

        jLabel10.setText("Tiền công");

        jLabel12.setText("Thành tiền");

        tblChiTietPN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "MaPN", "Mã sản phẩm", "Số lượng", "Đơn giá", "Tiền công", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblChiTietPN);

        jButton1.setText("Thêm");

        jButton2.setText("Cập nhật");

        jButton3.setText("new");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(488, 488, 488)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4))
                        .addGap(83, 83, 83)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                                    .addComponent(txtThanhTien, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                                    .addComponent(txtMaPn)
                                    .addComponent(jScrollPane1)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(41, 41, 41)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnThem)
                                .addGap(28, 28, 28)
                                .addComponent(btnCapNhat)
                                .addGap(29, 29, 29)
                                .addComponent(btnNew))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(319, 319, 319)
                                .addComponent(cbxMaSp, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(149, 149, 149)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel12))
                                .addGap(104, 104, 104)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtTienCong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                        .addComponent(txtDonGia, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtSoLuong, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtTien))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addGap(27, 27, 27)
                                        .addComponent(jButton2)
                                        .addGap(31, 31, 31)
                                        .addComponent(jButton3)))))
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(333, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(13, 13, 13)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtMaPn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnCapNhat)
                    .addComponent(btnNew))
                .addGap(27, 27, 27)
                .addComponent(jLabel6)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cbxMaSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTienCong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(txtTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(156, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        insert();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        update();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void tblPhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuNhapMouseClicked
       int row= tblPhieuNhap.getSelectedRow();
        PhieuNhap pn = listPn.get(row);
        setForm(pn);
        fillTableChiTiet();
    }//GEN-LAST:event_tblPhieuNhapMouseClicked

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        try {
            Clear();
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
    }//GEN-LAST:event_btnNewActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnThem;
    private javax.swing.JComboBox<String> cbxMaSp;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tblChiTietPN;
    private javax.swing.JTable tblPhieuNhap;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtMaPn;
    private javax.swing.JTextField txtNgayNhap;
    private javax.swing.JTextArea txtNoiNhap;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTien;
    private javax.swing.JTextField txtTienCong;
    // End of variables declaration//GEN-END:variables
}
