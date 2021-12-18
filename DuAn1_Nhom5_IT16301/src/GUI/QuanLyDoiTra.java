/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.ChiTietHD_DAO;
import DAO.DoiTra_Dao;
import DAO.HoaDon_DAO;
import DAO.SanPham_DAO;
import Entity.ChiTietHD;
import Entity.DoiTra;
import Entity.HoaDon;
import Entity.SanPham;
import Utils.Msgbox;
import java.awt.Color;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class QuanLyDoiTra extends javax.swing.JPanel {

    private DoiTra_Dao dtDao = new DoiTra_Dao();
    private HoaDon_DAO hdDao = new HoaDon_DAO();
    private ChiTietHD_DAO ctDao = new ChiTietHD_DAO();
    private SanPham_DAO spDao = new SanPham_DAO();
    private List<HoaDon> listHD = new ArrayList<>();
    private List<ChiTietHD> listSp = new ArrayList<>();
    private List<DoiTra> listDt = new ArrayList<>();

    /**
     * Creates new form QuanLyDoiTra
     */
    public QuanLyDoiTra() throws SQLException {
        initComponents();
        String maPDT = dtDao.maPDT_TuSinh();
        txtMaPDT.setText(maPDT);
        fillCbx();
        fillTable();
        setBackground(new Color(240, 240, 240));
        rdoDoi.setSelected(true);

    }

    private DoiTra getForm() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Calendar cal = Calendar.getInstance();
        java.sql.Timestamp ngayGD = new java.sql.Timestamp(cal.getTimeInMillis());
        DoiTra dt = new DoiTra();
        dt.setMaPDT(txtMaPDT.getText());
        dt.setMaHD(cbxMaHD.getSelectedItem() + "");
        dt.setMaSP(cbxMaSp.getSelectedItem() + "");
        if (rdoDoi.isSelected()) {
            dt.setHinhThuc(true);
        } else {
            dt.setHinhThuc(false);
        }
        dt.setLiDo(txtLiDo.getText());
        dt.setGhiChu(txtGhiChu.getText());
        dt.setNgayDT(ngayGD);
        return dt;
    }

    private void setForm(DoiTra dt) {
        txtMaPDT.setText(dt.getMaPDT());
        cbxMaHD.setSelectedItem(dt.getMaHD());
        cbxMaSp.setSelectedItem(dt.getMaSP());
        txtLiDo.setText(dt.getLiDo());
        txtGhiChu.setText(dt.getGhiChu());
        if (dt.isHinhThuc() == true) {
            rdoDoi.setSelected(true);
        } else {
            rdoTra.setSelected(true);
        }
    }

    private void fillCbx() {
        Calendar cal = Calendar.getInstance();
        java.sql.Timestamp ngayGD = new java.sql.Timestamp(cal.getTimeInMillis());
//        cbxMaHD.removeAllItems();
//        cbxMaSp.removeAllItems();
        listHD = hdDao.selectBYDate(ngayGD);
       

        for (HoaDon hoaDon : listHD) {
            cbxMaHD.addItem(hoaDon.getMaHD());
        }
         cbxMaSp.removeAllItems();
        listSp = ctDao.selectByMAHD(cbxMaHD.getSelectedItem() + "");
        for (ChiTietHD chiTiet : listSp) {
            cbxMaSp.addItem(chiTiet.getMaSp());
        }
    }

    private void fillTable() {
        try {
            listDt = dtDao.selectAll();
            DefaultTableModel model = (DefaultTableModel) tblDoiTra.getModel();
            model.setRowCount(0);
            for (DoiTra doiTra : listDt) {
                Object[] row = new Object[]{
                    doiTra.getMaPDT(), doiTra.getMaHD(), doiTra.getMaSP(),
                    doiTra.isHinhThuc() == true ? "Đổi" : "Trả", doiTra.getLiDo(), doiTra.getGhiChu(),
                    doiTra.getNgayDT()
                };
                model.addRow(row);

            }
        } catch (Exception e) {
        }
    }

    public void insert() throws SQLException {
        if (rdoDoi.isSelected() == false && rdoTra.isSelected() == false) {
            Msgbox.alert(this, "Chọn hình thức đổi hoặc trả");
            return;
        }
        DoiTra dt = getForm();
        dtDao.insert(dt);
        fillTable();
        Msgbox.alert(this, "Thêm phiếu đổi trả thành công");
        New();
    }

    public void update() throws SQLException {
        if (rdoDoi.isSelected() == false && rdoTra.isSelected() == false) {
            Msgbox.alert(this, "Chọn hình thức đổi hoặc trả");
            return;
        }
        DoiTra dt = getForm();
        dtDao.update(dt);
        fillTable();
        Msgbox.alert(this, "Cập nhật phiếu đổi trả thành công");
        New();
    }

    public void New() throws SQLException {
        String maPDT = dtDao.maPDT_TuSinh();
        txtMaPDT.setText(maPDT);
        cbxMaHD.setSelectedIndex(0);
        cbxMaSp.setSelectedIndex(0);
        rdoDoi.setSelected(true);
        txtGhiChu.setText("");
        txtLiDo.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtMaPDT = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbxMaHD = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cbxMaSp = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        rdoDoi = new javax.swing.JRadioButton();
        rdoTra = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtLiDo = new javax.swing.JTextArea();
        btnthem = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDoiTra = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        btnNew = new javax.swing.JButton();

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 204, 204));
        jLabel11.setText("Quản lý đổi trả");

        jLabel1.setText("MaPDT");

        txtMaPDT.setEditable(false);

        jLabel2.setText("MaHD");

        cbxMaHD.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxMaHDItemStateChanged(evt);
            }
        });

        jLabel3.setText("MaSP");

        jLabel4.setText("Hình thức");

        buttonGroup1.add(rdoDoi);
        rdoDoi.setText("Đổi");

        buttonGroup1.add(rdoTra);
        rdoTra.setText("Trả");

        jLabel5.setText("Lý do");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane1.setViewportView(txtGhiChu);

        jLabel6.setText("Ghi chú");

        txtLiDo.setColumns(20);
        txtLiDo.setRows(5);
        jScrollPane2.setViewportView(txtLiDo);

        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        jButton2.setText("Cập nhật");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tblDoiTra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "MAPDT", "MaHD", "MaSP", "Hình thức", "Lí do", "Ghi chú", "Thời gian"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDoiTra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDoiTraMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDoiTra);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 204, 204));
        jLabel12.setText("Danh sách phiếu đổi trả");

        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(jLabel4)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(19, 19, 19)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxMaSp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxMaHD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaPDT, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rdoDoi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(rdoTra, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnthem)
                            .addGap(40, 40, 40)
                            .addComponent(jButton2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNew))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(256, 256, 256)
                        .addComponent(jLabel12)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(151, 151, 151))))
            .addGroup(layout.createSequentialGroup()
                .addGap(601, 601, 601)
                .addComponent(jLabel11)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel11)
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtMaPDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jLabel12)
                        .addGap(4, 4, 4)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbxMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbxMaSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(rdoDoi)
                            .addComponent(rdoTra)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(85, 85, 85)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(btnthem))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNew)
                            .addComponent(jButton2))))
                .addContainerGap(317, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        try {
            insert();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnthemActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        try {
            New();
            btnthem.setEnabled(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnNewActionPerformed

    private void tblDoiTraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDoiTraMouseClicked
        int row = tblDoiTra.getSelectedRow();
        DoiTra dt = listDt.get(row);
        setForm(dt);
        btnthem.setEnabled(false);

    }//GEN-LAST:event_tblDoiTraMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            update();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbxMaHDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxMaHDItemStateChanged
        cbxMaSp.removeAllItems();
        listSp = ctDao.selectByMAHD(cbxMaHD.getSelectedItem() + "");
        for (ChiTietHD chiTiet : listSp) {
            cbxMaSp.addItem(chiTiet.getMaSp());
        }
    }//GEN-LAST:event_cbxMaHDItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnthem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbxMaHD;
    private javax.swing.JComboBox<String> cbxMaSp;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton rdoDoi;
    private javax.swing.JRadioButton rdoTra;
    private javax.swing.JTable tblDoiTra;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextArea txtLiDo;
    private javax.swing.JTextField txtMaPDT;
    // End of variables declaration//GEN-END:variables
}
