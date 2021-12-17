/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.ChatLieu_DAO;
import DAO.DanhMuc_DAO;
import DAO.SanPham_DAO;
import DAO.loaiChatLieu_DAO;
import Entity.ChatLieu;
import Entity.DanhMuc;
import Entity.LoaiChatLieu;
import Entity.SanPham;
import Utils.Msgbox;
import java.awt.Color;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tiến Mạnh
 */
public class QLSP extends javax.swing.JPanel {

    DanhMuc_DAO dmd =new DanhMuc_DAO();
    SanPham_DAO SPD =new SanPham_DAO();
    ChatLieu_DAO CLD =new ChatLieu_DAO();
    loaiChatLieu_DAO LCLD =new loaiChatLieu_DAO();
    List<DanhMuc> listDM =new ArrayList<>();
    List<ChatLieu> listCL =new ArrayList<>();
    List<LoaiChatLieu> listLCL =new ArrayList<>();
    List<SanPham> listSP =new ArrayList<>();
    
    public QLSP() {
        initComponents();
        init();
        txtSoLuong.setText("0");
        setBackground(new Color(240, 240, 240));
    }
    private void fillCBX(){
        listDM = dmd.selectAll();
        for (DanhMuc danhMuc : listDM) { //cbx Danh mục
            cbxMaDanhMuc.addItem(danhMuc.getMaDM());
        }
        listCL = CLD.selectByTT();
        for (ChatLieu cl : listCL) { ///cbx chất liệu
            cbxChatLieu.addItem(cl.getMaCL());
        }
        fillCBXLCL();
        
    }
    private void fillCBXLCL(){
        cbxMaLoaiChatLieu.removeAllItems();
        String maCL= cbxChatLieu.getSelectedItem().toString();
        listLCL =LCLD.selectBycl(maCL);
        for (LoaiChatLieu lcl : listLCL) {
          cbxMaLoaiChatLieu.addItem(lcl.getMaLCL());
        }
    }
    private void displayTenDanhMuc(){
        for (DanhMuc danhMuc : listDM) {
            if(cbxMaDanhMuc.getSelectedItem().equals(danhMuc.getMaDM())){
                txtTenDanhMuc.setText(danhMuc.getTenDm());
            }
        }
    } 
    
    private void display() throws SQLException{
        int row =tblSanPham.getSelectedRow();
        SanPham sp =listSP.get(row);
        txtGiaBanRa.setText(""+new BigDecimal(sp.getGiaBanRa()));
        txtGiaMuaVao.setText(""+new BigDecimal(sp.getGiaMuaVao()));
        txtKhoiLuong.setText(""+sp.getKhoiLuong());
        txtMaSanPham.setText(sp.getMaSP());
        txtSoLuong.setText(sp.getSoLuong()+"");
        txtTienCong.setText(""+sp.getTienCong());
        txtaMoTa.setText(sp.getMoTa());
        txtTenSp.setText(sp.getTenSP());
        String maCL =SPD.getCL(""+sp.getMaLCL());
        setSelectedCBX(maCL, cbxChatLieu); // khi đang chất liệu khác khi click
        setSelectedCBX(sp.getMaDm(), cbxMaDanhMuc);
        setSelectedCBX(sp.getMaLCL(), cbxMaLoaiChatLieu);
        setSelectedCBX(sp.getTrangThai(), cbxTrangThai);
    }
    private void setSelectedCBX(String a,JComboBox cbx){//hiển thị cbx khi click
        for (int i = 0; i < cbx.getItemCount(); i++) {
            String b =(String) cbx.getItemAt(i);
            if(a.trim().equals(b)){
                cbx.setSelectedItem(b);
            }
        }
 
    }
    public void fillTable(){
        String trangThai =cbxTrangThai.getSelectedItem().toString();
        listSP = SPD.selectByTT(trangThai);
        DefaultTableModel model =(DefaultTableModel)tblSanPham.getModel();
        model.setRowCount(0);
        if (listSP==null) {
            return;
        }
        DecimalFormat df=new DecimalFormat("0.00");
        for (SanPham sp : listSP) {
            model.addRow(new Object[]{sp.getMaSP(),sp.getMaDm(),sp.getTenSP(),sp.getMaLCL(),sp.getKhoiLuong(),df.format(new BigDecimal(sp.getGiaMuaVao())),
                df.format(new BigDecimal(sp.getGiaBanRa())) ,sp.getTienCong(),sp.getTrangThai(),sp.getSoLuong(),sp.getMoTa()});
        }
    }
    public void insert(){
        if(txtTenSp.getText().equals("")){
           Msgbox.alert(this, "Không được để trống tên sản phẩm");
            return; 
        }
        try {
        if(txtTienCong.getText().trim().equals("")){
            Msgbox.alert(this, "Không được để trống tiền công");
            return;
            
        }
        double tienCong =Double.parseDouble(txtTienCong.getText());
        } catch (NumberFormatException  e) {
             Msgbox.alert(this, "Tiền công phải là số");
            return;
        }
                if(txtKhoiLuong.getText().equals("")){
            Msgbox.alert(this, "Không được để trống khối lượng");
            return;
        }
        try {
            double khoiLuong =Double.parseDouble(txtKhoiLuong.getText());
        } catch (NumberFormatException  e) {
            Msgbox.alert(this, "Khối lượng phải là số!");
            return;
        }
        
        
        SanPham sp =getForm();
        SPD.insert(sp);
        Msgbox.alert(this, "Thêm thành công");
        fillTable();
    }
    public void update(){
          if(txtTenSp.getText().equals("")){
           Msgbox.alert(this, "Không được để trống tên sản phẩm");
            return; 
        }
        try {
        if(txtTienCong.getText().trim().equals("")){
            Msgbox.alert(this, "Không được để trống tiền công");
            return;
            
        }
        double tienCong =Double.parseDouble(txtTienCong.getText());
        } catch (NumberFormatException  e) {
             Msgbox.alert(this, "Tiền công phải là số");
            return;
        }
                if(txtKhoiLuong.getText().equals("")){
            Msgbox.alert(this, "Không được để trống khối lượng");
            return;
        }
        try {
            double khoiLuong =Double.parseDouble(txtKhoiLuong.getText());
        } catch (NumberFormatException  e) {
            Msgbox.alert(this, "Khối lượng phải là số!");
            return;
        }
        SanPham sp =getForm();
        SPD.update(sp);
        Msgbox.alert(this, "Cập nhật thành công");
        fillTable();
    }
    
    public SanPham getForm(){
        SanPham sp =new SanPham();
        sp.setGiaBanRa(Float.parseFloat(txtGiaBanRa.getText()));
        sp.setGiaMuaVao(Float.parseFloat(txtGiaMuaVao.getText()));
        sp.setKhoiLuong(Float.parseFloat(txtKhoiLuong.getText()));
        sp.setMaDm(cbxMaDanhMuc.getSelectedItem().toString());
        sp.setMaLCL(cbxMaLoaiChatLieu.getSelectedItem().toString());
        sp.setTrangThai(cbxTrangThai.getSelectedItem().toString());
        sp.setMaSP(txtMaSanPham.getText());
        sp.setMoTa(txtaMoTa.getText());
        sp.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        sp.setTenSP(txtTenSp.getText());
        sp.setTienCong(Float.parseFloat(txtTienCong.getText()));
        return sp;
    }
    private void refreshForm(){
        txtGiaBanRa.setText("");
        txtGiaMuaVao.setText("");
        txtKhoiLuong.setText("");
        txtMaSanPham.setText(SPD.maSP_TuSinh());
        txtSoLuong.setText("0");
        txtTienCong.setText("0");
        txtaMoTa.setText("");
        txtTenSp.setText("");
        tblSanPham.clearSelection();
        cbxMaDanhMuc.setSelectedIndex(0);
        cbxMaLoaiChatLieu.setSelectedIndex(0);
        cbxTrangThai.setSelectedIndex(0);
    }
    private void CalculateMoney() throws SQLException{
        if(txtKhoiLuong.getText().equals("")){
            Msgbox.alert(this, "Không được để trống khối lượng");
            return;
        }
        try {
            double khoiLuong =Double.parseDouble(txtKhoiLuong.getText());
        } catch (NumberFormatException  e) {
            Msgbox.alert(this, "Khối lượng phải là số!");
            return;
        }
        double khoiLuong =Double.parseDouble(txtKhoiLuong.getText());
        
        
        String maLCL =cbxMaLoaiChatLieu.getSelectedItem().toString();
        double[] result =SPD.calculateMoney(khoiLuong,maLCL);
        DecimalFormat df=new DecimalFormat("0.00");
        txtGiaBanRa.setText(""+df.format(new BigDecimal(result[0])));
        txtGiaMuaVao.setText(""+df.format(new BigDecimal(result[1])));
    }
    private void search(){
        if(txtSearch.getText().trim().equals("")){
            fillTable();
        }
        else{
            String tenSP =txtSearch.getText();
        listSP = SPD.search(tenSP);
        DefaultTableModel model =(DefaultTableModel)tblSanPham.getModel();
        model.setRowCount(0);
        for (SanPham sp : listSP) {
            model.addRow(new Object[]{sp.getMaSP(),sp.getMaDm(),sp.getTenSP(),sp.getMaLCL(),sp.getKhoiLuong(),new BigDecimal(sp.getGiaMuaVao()),new BigDecimal(sp.getGiaBanRa())
                    ,sp.getTienCong(),sp.getTrangThai(),sp.getSoLuong(),sp.getMoTa()});
        }
        }
        
    }

    private void init(){
        txtMaSanPham.setText(SPD.maSP_TuSinh());
        
        fillCBX();
        fillTable();
        displayTenDanhMuc();
        txtSoLuong.setText("0");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbxMaDanhMuc = new javax.swing.JComboBox<>();
        cbxMaLoaiChatLieu = new javax.swing.JComboBox<>();
        txtMaSanPham = new javax.swing.JTextField();
        txtTenSp = new javax.swing.JTextField();
        lblDonVi = new javax.swing.JLabel();
        txtKhoiLuong = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtGiaMuaVao = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtGiaBanRa = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTienCong = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaMoTa = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        txtTenDanhMuc = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cbxTrangThai = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        cbxChatLieu = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 204, 204));
        jLabel11.setText("Quản lý sản phẩm");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, -1, -1));

        jLabel1.setText("Mã sản phẩm:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, -1, -1));

        jLabel2.setText("Tên sản phẩm:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, -1, -1));

        jLabel3.setText("Mã danh mục:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(146, 80, -1, -1));

        jLabel4.setText("Mã loại chất liệu:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 130, -1, -1));

        cbxMaDanhMuc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxMaDanhMucItemStateChanged(evt);
            }
        });
        add(cbxMaDanhMuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 76, 134, -1));

        add(cbxMaLoaiChatLieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 120, 134, -1));

        txtMaSanPham.setEditable(false);
        txtMaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSanPhamActionPerformed(evt);
            }
        });
        add(txtMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, 134, -1));

        txtTenSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSpActionPerformed(evt);
            }
        });
        add(txtTenSp, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 134, -1));

        lblDonVi.setText("Chỉ");
        add(lblDonVi, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 270, -1, -1));

        txtKhoiLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKhoiLuongKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtKhoiLuongKeyTyped(evt);
            }
        });
        add(txtKhoiLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, 134, -1));

        jLabel6.setText("Giá mua vào:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 90, -1, -1));

        txtGiaMuaVao.setEditable(false);
        add(txtGiaMuaVao, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 80, 134, -1));

        jLabel7.setText("Giá bán ra:");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 140, -1, -1));

        txtGiaBanRa.setEditable(false);
        add(txtGiaBanRa, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 130, 134, -1));

        jLabel8.setText("Tiền công:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 190, -1, -1));
        add(txtTienCong, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 180, 134, -1));

        jLabel9.setText("Trạng thái:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 280, -1, -1));

        jLabel10.setText("Số lượng:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 230, -1, -1));

        txtSoLuong.setEditable(false);
        add(txtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 230, 134, -1));

        jLabel12.setText("Mô tả:");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 180, -1, -1));

        txtaMoTa.setColumns(20);
        txtaMoTa.setRows(5);
        jScrollPane1.setViewportView(txtaMoTa);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 170, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel1.add(btnThem);

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel1.add(btnSua);

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        jPanel1.add(btnLamMoi);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(562, 276, 273, -1));

        txtTenDanhMuc.setEditable(false);
        txtTenDanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenDanhMucActionPerformed(evt);
            }
        });
        add(txtTenDanhMuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 126, 134, -1));

        jLabel13.setText("Tên danh mục:");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 129, -1, -1));

        cbxTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang bán ", "Nghỉ bán" }));
        cbxTrangThai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTrangThaiItemStateChanged(evt);
            }
        });
        add(cbxTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 280, 134, -1));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Mã danh mục", "Tên sản phẩm", "Mã loại chất liệu", "Khối lượng", "Giá mua vào", "Giá bán ra", "Tiền công", "Trạng thái", "Số lượng", "Mô tả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSanPham);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, 1004, -1));

        jLabel14.setText("Chất liệu:");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, -1, -1));

        cbxChatLieu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxChatLieuItemStateChanged(evt);
            }
        });
        add(cbxChatLieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 80, 134, -1));

        jLabel15.setText("Khối lượng:");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, -1, -1));

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });
        add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 20, 150, -1));

        jLabel5.setText("Tìm kiếm:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 20, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        try {
            display();
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
       refreshForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void txtTenDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenDanhMucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenDanhMucActionPerformed

    private void txtMaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSanPhamActionPerformed

    private void txtTenSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSpActionPerformed

    private void cbxMaDanhMucItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxMaDanhMucItemStateChanged
       displayTenDanhMuc();
    }//GEN-LAST:event_cbxMaDanhMucItemStateChanged

    private void cbxChatLieuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxChatLieuItemStateChanged
        fillCBXLCL();
    }//GEN-LAST:event_cbxChatLieuItemStateChanged

    private void txtKhoiLuongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKhoiLuongKeyReleased
        try {
            // tinh tien
            CalculateMoney();
        } catch (SQLException e) {
          e.printStackTrace();
        }
    }//GEN-LAST:event_txtKhoiLuongKeyReleased

    private void txtKhoiLuongKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKhoiLuongKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKhoiLuongKeyTyped

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
       insert();
       fillTable();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
      int choose =JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn cập nhật ?","Cập nhật",JOptionPane.YES_NO_OPTION);
      if(choose==JOptionPane.YES_OPTION){
          update();
      fillTable();
      }
        
    }//GEN-LAST:event_btnSuaActionPerformed

    private void cbxTrangThaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTrangThaiItemStateChanged
        fillTable();
    }//GEN-LAST:event_cbxTrangThaiItemStateChanged

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        search();
    }//GEN-LAST:event_txtSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JComboBox<String> cbxChatLieu;
    private javax.swing.JComboBox<String> cbxMaDanhMuc;
    private javax.swing.JComboBox<String> cbxMaLoaiChatLieu;
    private javax.swing.JComboBox<String> cbxTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDonVi;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGiaBanRa;
    private javax.swing.JTextField txtGiaMuaVao;
    private javax.swing.JTextField txtKhoiLuong;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenDanhMuc;
    private javax.swing.JTextField txtTenSp;
    private javax.swing.JTextField txtTienCong;
    private javax.swing.JTextArea txtaMoTa;
    // End of variables declaration//GEN-END:variables
}
