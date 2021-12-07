/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.DanhMuc_DAO;
import Entity.DanhMuc;
import Utils.Msgbox;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tiến Mạnh
 */
public class pannelQLDMSP extends javax.swing.JPanel {

    private DanhMuc_DAO dao = new DanhMuc_DAO();
    private List<DanhMuc> list = new ArrayList();

    public pannelQLDMSP() {
        initComponents();
        fillTable();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhMuc = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtMaDM = new javax.swing.JTextField();
        txtTenDM = new javax.swing.JTextField();
        btnKhoa = new javax.swing.JButton();

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 204, 204));
        jLabel11.setText("Quản lý Danh mục sản phẩm");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane2.setViewportView(txtMoTa);

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Icon_Logo/add-button.png"))); // NOI18N
        btnThem.setText("THÊM");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Icon_Logo/update.png"))); // NOI18N
        btnSua.setText("SỬA");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        jLabel2.setText("MÃ DANH MỤC");

        jLabel3.setText("TÊN DANH MỤC");

        tblDanhMuc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Danh Mục", "Tên Danh Mục", "Mô tả", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhMuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhMucMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDanhMuc);

        jLabel4.setText("MÔ TẢ");

        txtMaDM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaDMActionPerformed(evt);
            }
        });

        txtTenDM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenDMActionPerformed(evt);
            }
        });

        btnKhoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Icon_Logo/update.png"))); // NOI18N
        btnKhoa.setText("KHÓA");
        btnKhoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(607, 607, 607)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(400, 400, 400)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(42, 42, 42)
                                .addComponent(txtTenDM, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtMaDM, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThem))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(951, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnKhoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(378, 378, 378))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(403, 403, 403)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel2)
                        .addComponent(jLabel4))
                    .addContainerGap(428, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel11)
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtMaDM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSua)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTenDM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(btnKhoa))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(btnThem))
                .addContainerGap(430, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(125, 125, 125)
                    .addComponent(jLabel2)
                    .addGap(119, 119, 119)
                    .addComponent(jLabel4)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(125, 125, 125)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        try {
            insert();
        } catch (ParseException ex) {
            Logger.getLogger(QuanLiDanhMuc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        try {
            // TODO add your handling code here:
            update();
        } catch (ParseException ex) {
            Logger.getLogger(QuanLiDanhMuc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void tblDanhMucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhMucMouseClicked
        // TODO add your handling code here:
        int row = tblDanhMuc.getSelectedRow();
        DanhMuc dm = list.get(row);
        setForm(dm);
    }//GEN-LAST:event_tblDanhMucMouseClicked

    private void txtMaDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaDMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaDMActionPerformed

    private void txtTenDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenDMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenDMActionPerformed

    private void btnKhoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoaActionPerformed
        // TODO add your handling code here:
        int row = tblDanhMuc.getSelectedRow();

        if (row < 0) {
            Msgbox.alert(this, "Chọn danh mục để khóa");
            return;
        }

        DanhMuc dm = list.get(row);
        if (dm.isTrangThai() == true) {
            Msgbox.alert(this, "Khóa thành công");
            dm.setTrangThai(false);

        } else {
            Msgbox.alert(this, "Mở khóa thành công");
            dm.setTrangThai(true);
        }
        dao.updateTT(dm);
        fillTable();

    }//GEN-LAST:event_btnKhoaActionPerformed
    private void insert() throws ParseException {
        if (txtMaDM.getText().isEmpty()) {
            Msgbox.alert(this, "Mã danh mục không được bỏ trống");
            return;
        }
        for (DanhMuc danhMuc : list) {
            if (danhMuc.getMaDM().equalsIgnoreCase(txtMaDM.getText())) {
                Msgbox.alert(this, "Mã danh mục đã tồn tại");
                return;
            }
        }
        if (txtTenDM.getText().isEmpty()) {
            Msgbox.alert(this, "Tên danh mục không được bỏ trống");
            return;
        }
        DanhMuc dm = getForm();
        dao.insert(dm);
        Msgbox.alert(this, "Thêm thành công");
        fillTable();
    }

    private void update() throws ParseException {
        if (txtMaDM.getText().isEmpty()) {
            Msgbox.alert(this, "Mã danh mục không được bỏ trống");
            return;
        }

        if (txtTenDM.getText().isEmpty()) {
            Msgbox.alert(this, "Tên danh mục không được bỏ trống");
            return;
        }
        DanhMuc dm = getForm();
        dao.update(dm);
         Msgbox.alert(this, "Cập nhật thành công");
        fillTable();
    }

    private void delete() throws ParseException {

        DanhMuc dm = getForm();
        dao.delete(dm.getMaDM());
        fillTable();
    }

    public DanhMuc getForm() {
        DanhMuc dm = new DanhMuc();
        dm.setMaDM(txtMaDM.getText());
        dm.setTenDm(txtTenDM.getText());
        dm.setMoTa(txtMoTa.getText());
        return dm;
    }

    public void fillTable() {
        list = dao.selectAll();
        DefaultTableModel model = (DefaultTableModel) tblDanhMuc.getModel();
        model.setRowCount(0);
        for (DanhMuc danhMuc : list) {
            Object[] row = new Object[]{
                danhMuc.getMaDM(), danhMuc.getTenDm(), danhMuc.getMoTa(), danhMuc.isTrangThai() == false ? "KHÔNG KHÓA" : "KHÓA"
            };
            model.addRow(row);
        }
    }

    public void setForm(DanhMuc dm) {
        txtMaDM.setText(dm.getMaDM());
        txtTenDM.setText(dm.getTenDm());
        txtMoTa.setText(dm.getMoTa());

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btnKhoa;
    javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblDanhMuc;
    private javax.swing.JTextField txtMaDM;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtTenDM;
    // End of variables declaration//GEN-END:variables
}
