/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.PhieuNhap_DAO;
import DAO.SanPham_DAO;
import DAO.chiTietPhieuNhap_DAO;
import Entity.ChiTietPhieuNhap;
import Entity.PhieuNhap;
import Entity.SanPham;
import Utils.Msgbox;
import java.awt.Color;
import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
    private chiTietPhieuNhap_DAO ctPNDao = new chiTietPhieuNhap_DAO();
    private List<ChiTietPhieuNhap> listCTPn = new ArrayList<>();
    private SanPham_DAO spDao = new SanPham_DAO();
    private List<SanPham> listSP = new ArrayList<>();

    /**
     * Creates new form QLPhieuNhap
     */
    public QLPhieuNhap() throws SQLException {
        initComponents();
        fillComboboxSP();
        String maPn = pnDao.maPDT_TuSinh();
        txtMaPn.setText(maPn);
        txtThanhTien.setText("0");
        fillTable();
         setBackground(new Color(240, 240, 240));
    }

    public void Clear() throws SQLException {
        String maPn = pnDao.maPDT_TuSinh();
        txtMaPn.setText(maPn);

        txtThanhTien.setText("0");
      //  jDCNgayNhap.setCalendar(clndr);
        txtNoiNhap.setText("");
        txtGhiChu.setText("");
        fillTableChiTiet();
    }

    public void fillComboboxSP() {
        listSP = spDao.selectAll();
        for (SanPham sp : listSP) {
            cbxMaSp.addItem(sp.getMaSP());
        }
    }

    public PhieuNhap getForm() throws ParseException {
        PhieuNhap pn = new PhieuNhap();
        pn.setMaPN(txtMaPn.getText());
        pn.setThanhtien(Double.parseDouble(txtThanhTien.getText()));
        SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy");
        String ngayNhap = sfd.format(jDCNgayNhap.getDate());
       Date ngayNhap1 = sfd.parse(ngayNhap);
        pn.setNgayNhap(ngayNhap1);
        pn.setNoiNhap(txtNoiNhap.getText());
        pn.setGhiChu(txtGhiChu.getText());
        return pn;
    }

    public ChiTietPhieuNhap getFormCT() {
        ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap();
        ctpn.setMaPN(txtMaPn.getText());
        ctpn.setMaSp(cbxMaSp.getSelectedItem() + "");
        ctpn.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        ctpn.setDonGia(Double.parseDouble(txtDonGia.getText()));
        ctpn.setCong(Double.parseDouble(txtTienCong.getText()));
        ctpn.setThanhTien(Double.parseDouble(txtTien.getText()));
        return ctpn;
    }

    public void setForm(PhieuNhap pn) {
        txtMaPn.setText(pn.getMaPN());
        jDCNgayNhap.setDate(pn.getNgayNhap());
        txtThanhTien.setText(pn.getThanhtien() + "");
        txtNoiNhap.setText(pn.getNoiNhap());
        txtGhiChu.setText(pn.getGhiChu());
    }

    public void setFormCTPN(ChiTietPhieuNhap ctpn) {
        txtMaPn.setText(ctpn.getMaPN());
        cbxMaSp.setSelectedItem(ctpn.getMaSp());
        txtSoLuong.setText(ctpn.getSoLuong() + "");
        txtDonGia.setText(ctpn.getDonGia() + "");
        txtTienCong.setText(ctpn.getCong() + "");
        txtTien.setText(ctpn.getThanhTien() + "");
    }

    public void fillTable() {
        listPn = pnDao.selectAll();
        DefaultTableModel model = (DefaultTableModel) tblPhieuNhap.getModel();
        model.setRowCount(0);
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        for (PhieuNhap phieuNhap : listPn) {
            Object[] row = new Object[]{
                phieuNhap.getMaPN(), df.format(phieuNhap.getThanhtien()), phieuNhap.getNgayNhap(),
                phieuNhap.getNoiNhap(), phieuNhap.getGhiChu()
            };
            model.addRow(row);
        }
    }

    public void fillTableChiTiet() {
        listCTPn = ctPNDao.selectByMAPN(txtMaPn.getText());
        DefaultTableModel model = (DefaultTableModel) tblChiTietPN.getModel();
        model.setRowCount(0);
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        for (ChiTietPhieuNhap ctpn : listCTPn) {
            Object[] row = new Object[]{
                ctpn.getMaPN(), ctpn.getMaSp(), ctpn.getSoLuong(), df.format(ctpn.getDonGia()),
                df.format(ctpn.getCong()), df.format(ctpn.getThanhTien())
            };
            model.addRow(row);
        }
    }

    public void insertPN() {
        try {

//          SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy");
//        String ngayNhap = sfd.format(jDCNgayNhap.getDate());
 
         
            if (jDCNgayNhap.getDate().equals("")) {
                Msgbox.alert(this, "Không để trống ngày nhập");
                return;
            }
            if (txtNoiNhap.getText().isEmpty() || txtNoiNhap.getText().length() > 50) {
                Msgbox.alert(this, "Không để trống nơi nhập và tối đa 50 kí tự");
                return;
            }
            PhieuNhap pn = getForm();
            pnDao.insert(pn);
            Msgbox.alert(this, "Thêm thành công");
            fillTable();
//            Clear();
        } catch (Exception e) {
            e.printStackTrace();
            Msgbox.alert(this, "Ngày nhập định dạng: dd-MM-yyyy ");
            return;
        }
    }

    public void insertCTPN() throws ParseException {
//        int row = tblPhieuNhap.getSelectedRow();
//        if (row < 0) {
//            Msgbox.alert(this, "chọn phiếu nhập để thêm phiếu nhập chi tiết ");
//            return;
//        }
        PhieuNhap pn = pnDao.selectById(txtMaPn.getText());
        if (pn == null) {
            Msgbox.alert(this, "Phiếu nhập này chưa tồn tại");
            return;
        }
        for (ChiTietPhieuNhap ctpn : listCTPn) {
            if (txtMaPn.getText().equals(ctpn.getMaPN()) && cbxMaSp.getSelectedItem().toString().equals(ctpn.getMaSp())) {
                Msgbox.alert(this, "Sản phẩm này đã tồn tại trong phiếu nhập này");
                return;
            }
        }
        try {
            if (txtSoLuong.getText().isEmpty()) {
                Msgbox.alert(this, "Không để trống số lượng ");
                return;
            }
            int sl = Integer.parseInt(txtSoLuong.getText());
            if (sl <= 0) {
                Msgbox.alert(this, "Số lượng >0");
                return;
            }
        } catch (Exception e) {
            Msgbox.alert(this, "Số lượng là số nguyên dương");
            return;
        }
        try {
            if (txtDonGia.getText().isEmpty()) {
                Msgbox.alert(this, "Không để trống đơn giá ");
                return;
            }
            double donGia = Double.parseDouble(txtSoLuong.getText());
            if (donGia <= 0) {
                Msgbox.alert(this, "Đơn giá >0");
                return;
            }
        } catch (Exception e) {
            Msgbox.alert(this, "Đơn giá là số  dương");
            return;
        }
        try {
            if (txtTienCong.getText().isEmpty()) {
                Msgbox.alert(this, "Không để trống tiền công ");
                return;
            }
            double tienCong = Double.parseDouble(txtTienCong.getText());
            if (tienCong < 0) {
                Msgbox.alert(this, "Tiền công >=0");
                return;
            }
        } catch (Exception e) {
            Msgbox.alert(this, "Tiền công là số  dương");
            return;
        }

        ChiTietPhieuNhap ctpn = getFormCT();
        ctPNDao.insert(ctpn);
        Msgbox.alert(this, "Thêm thành công");
        fillTableChiTiet();
        Thanhtien();
    }

    public void tienPNCT() {
        try {
            if (txtSoLuong.getText().isEmpty()) {
                Msgbox.alert(this, "Không để trống số lượng ");
                return;
            }
            int sl = Integer.parseInt(txtSoLuong.getText());
            if (sl <= 0) {
                Msgbox.alert(this, "Số lượng >0");
                return;
            }
        } catch (Exception e) {
            Msgbox.alert(this, "Số lượng là số nguyên dương");
            return;
        }
        try {
            if (txtDonGia.getText().isEmpty()) {
                Msgbox.alert(this, "Không để trống đơn giá ");
                return;
            }
            double donGia = Double.parseDouble(txtSoLuong.getText());
            if (donGia <= 0) {
                Msgbox.alert(this, "Đơn giá >0");
                return;
            }
        } catch (Exception e) {
            Msgbox.alert(this, "Đơn giá là số  dương");
            return;
        }
        try {
            if (txtTienCong.getText().isEmpty()) {
                Msgbox.alert(this, "Không để trống tiền công ");
                return;
            }
            double tienCong = Double.parseDouble(txtTienCong.getText());
            if (tienCong < 0) {
                Msgbox.alert(this, "Tiền công >=0");
                return;
            }
        } catch (Exception e) {
            Msgbox.alert(this, "tiền công là số  dương");
            return;
        }
        int sl = Integer.parseInt(txtSoLuong.getText());
        double donGia = Double.parseDouble(txtDonGia.getText());
        double tienCong = Double.parseDouble(txtTienCong.getText());
        double thanhTien = sl * donGia + tienCong;
        txtTien.setText(new BigDecimal(thanhTien) + "");
    }

    public void updatePN() {
        try {
          
            if (jDCNgayNhap.getDate().equals("")) {
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
            Msgbox.alert(this, "Ngày nhập định dạng: dd-MM-yyyy ");
            return;
        }

    }

    public void updateCTPN() throws ParseException {
//        PhieuNhap pn = pnDao.selectById(txtMaPn.getText());
//        if (pn == null) {
//            Msgbox.alert(this, "Phiếu nhập này chưa tồn tại");
//            return;
//        }
        int row = tblChiTietPN.getSelectedRow();
        if (row < 0) {
            Msgbox.alert(this, "chọn phiếu nhập chi tiết để cập nhật");
            return;
        }
        int check = 0;
        for (ChiTietPhieuNhap ctpn : listCTPn) {
            if (cbxMaSp.getSelectedItem().toString().equals(ctpn.getMaSp())) {
                check++;
            }
        }
        if (check == 0) {
            Msgbox.alert(this, "Sản phẩm chưa tồn tại trong phiếu nhập");
            return;
        }
        try {
            if (txtSoLuong.getText().isEmpty()) {
                Msgbox.alert(this, "Không để trống số lượng ");
                return;
            }
            int sl = Integer.parseInt(txtSoLuong.getText());
            if (sl <= 0) {
                Msgbox.alert(this, "Số lượng >0");
                return;
            }
        } catch (Exception e) {
            Msgbox.alert(this, "Số lượng là số nguyên dương");
            return;
        }
        try {
            if (txtDonGia.getText().isEmpty()) {
                Msgbox.alert(this, "Không để trống đơn giá ");
                return;
            }
            double donGia = Double.parseDouble(txtSoLuong.getText());
            if (donGia <= 0) {
                Msgbox.alert(this, "Đơn giá >0");
                return;
            }
        } catch (Exception e) {
            Msgbox.alert(this, "Đơn giá là số  dương");
            return;
        }
        try {
            if (txtTienCong.getText().isEmpty()) {
                Msgbox.alert(this, "Không để trống tiền công ");
                return;
            }
            double tienCong = Double.parseDouble(txtTienCong.getText());
            if (tienCong < 0) {
                Msgbox.alert(this, "Tiền công >=0");
                return;
            }
        } catch (Exception e) {
            Msgbox.alert(this, "Tiền công là số  dương");
            return;
        }

        ChiTietPhieuNhap ctpn = getFormCT();
        ctPNDao.update(ctpn);
        Msgbox.alert(this, "Cập nhật thành công");
        fillTableChiTiet();
        Thanhtien();
    }

    public void Thanhtien() throws ParseException {
        double tong = 0.0;
        for (ChiTietPhieuNhap chiTietPhieuNhap : listCTPn) {
            tong += chiTietPhieuNhap.getThanhTien();
        }
        DecimalFormat df = new DecimalFormat("#.####");
        txtThanhTien.setText(df.format(tong) + "");
        PhieuNhap pn = getForm();
        pnDao.update(pn);
        fillTable();
    }

    public void xoaCTPN() throws ParseException {
        int row = tblChiTietPN.getSelectedRow();
        if (row < 0) {
            Msgbox.alert(this, "chọn phiếu nhập chi tiết để xóa");
            return;
        }
        ChiTietPhieuNhap ctpn = getFormCT();
        ctPNDao.deletePNCT(ctpn);
        Msgbox.alert(this, "Xóa thành công");
        fillTableChiTiet();
        Thanhtien();
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
        btnThemCTPN = new javax.swing.JButton();
        btnCapNhatCTPN = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jDCNgayNhap = new com.toedter.calendar.JDateChooser();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 204, 204));
        jLabel11.setText("Quản lý phiếu nhập");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, 197, -1));

        jLabel1.setText("Mã phiếu nhập");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, -1, -1));

        jLabel2.setText("Thành tiền");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, -1, -1));

        jLabel3.setText("Ngày nhập");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, -1, -1));

        jLabel4.setText("Nơi nhập");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, -1, -1));

        jLabel5.setText("Ghi chú");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, -1, -1));

        txtMaPn.setEditable(false);
        add(txtMaPn, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 226, -1));

        txtThanhTien.setEditable(false);
        add(txtThanhTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 226, -1));

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane1.setViewportView(txtGhiChu);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, 226, 60));

        txtNoiNhap.setColumns(20);
        txtNoiNhap.setRows(5);
        jScrollPane2.setViewportView(txtNoiNhap);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, 226, 70));

        tblPhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Phiếu nhập", "Tổng Tiền", "Ngày nhập", "Nơi nhập", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(625, 108, 692, 263));

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 400, -1, -1));

        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 400, -1, -1));

        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        add(btnNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 400, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Thông tin chi tiết");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, -1, -1));

        jLabel7.setText("Mã Sản Phẩm");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 486, -1, -1));

        jLabel8.setText("Số lượng");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 527, -1, -1));

        jLabel9.setText("Đơn giá");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 582, -1, -1));

        jLabel10.setText("Tiền công");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 632, -1, -1));

        jLabel12.setText("Thành tiền");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 681, -1, -1));

        cbxMaSp.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxMaSpItemStateChanged(evt);
            }
        });
        add(cbxMaSp, new org.netbeans.lib.awtextra.AbsoluteConstraints(301, 482, 230, -1));
        add(txtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(299, 524, 230, -1));
        add(txtDonGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(299, 579, 230, -1));

        txtTienCong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienCongKeyReleased(evt);
            }
        });
        add(txtTienCong, new org.netbeans.lib.awtextra.AbsoluteConstraints(299, 629, 230, -1));

        txtTien.setEditable(false);
        add(txtTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(299, 681, 230, -1));

        tblChiTietPN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MaPN", "Mã sản phẩm", "Số lượng", "Đơn giá", "Tiền công", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChiTietPN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietPNMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblChiTietPN);

        add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(625, 482, 692, 269));

        btnThemCTPN.setText("Thêm");
        btnThemCTPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCTPNActionPerformed(evt);
            }
        });
        add(btnThemCTPN, new org.netbeans.lib.awtextra.AbsoluteConstraints(299, 741, -1, -1));

        btnCapNhatCTPN.setText("Cập nhật");
        btnCapNhatCTPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatCTPNActionPerformed(evt);
            }
        });
        add(btnCapNhatCTPN, new org.netbeans.lib.awtextra.AbsoluteConstraints(385, 741, -1, -1));

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 741, -1, -1));
        add(jDCNgayNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 230, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        insertPN();
        fillTableChiTiet();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        updatePN();
        fillTableChiTiet();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void tblPhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuNhapMouseClicked
        int row = tblPhieuNhap.getSelectedRow();
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

    private void txtTienCongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienCongKeyReleased
        tienPNCT();
    }//GEN-LAST:event_txtTienCongKeyReleased

    private void btnThemCTPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCTPNActionPerformed

        try {
            insertCTPN();
            try {
                spDao.updateSoLuong(txtSoLuong.getText(),cbxMaSp.getSelectedItem().toString());
            } catch (SQLException e) {
              e.printStackTrace();
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnThemCTPNActionPerformed

    private void tblChiTietPNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietPNMouseClicked
        int row = tblChiTietPN.getSelectedRow();
        ChiTietPhieuNhap ctpn = listCTPn.get(row);
        setFormCTPN(ctpn);
    }//GEN-LAST:event_tblChiTietPNMouseClicked

    private void cbxMaSpItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxMaSpItemStateChanged
        txtSoLuong.setText("");
        txtDonGia.setText("");
        txtTienCong.setText("");
        txtTien.setText("");
    }//GEN-LAST:event_cbxMaSpItemStateChanged

    private void btnCapNhatCTPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatCTPNActionPerformed

        try {
            updateCTPN();
            txtSoLuong.setText("");
            txtDonGia.setText("");
            txtTienCong.setText("");
            txtTien.setText("");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnCapNhatCTPNActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        try {
            xoaCTPN();
            txtSoLuong.setText("");
            txtDonGia.setText("");
            txtTienCong.setText("");
            txtTien.setText("");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnXoaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnCapNhatCTPN;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemCTPN;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbxMaSp;
    private com.toedter.calendar.JDateChooser jDCNgayNhap;
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
    private javax.swing.JTextArea txtNoiNhap;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTien;
    private javax.swing.JTextField txtTienCong;
    // End of variables declaration//GEN-END:variables
}
