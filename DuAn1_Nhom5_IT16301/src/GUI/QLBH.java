/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import DAO.ChiTietHD_DAO;
import DAO.HoaDon_DAO;
import DAO.KhachHang_DAO;
import DAO.SanPham_DAO;
import Entity.ChiTietHD;
import Entity.HoaDon;
import Entity.KhachHang;
import Entity.NhanVien;
import Entity.SanPham;
import Form.TestMenu.Main;
import Utils.Auth;
import Utils.Msgbox;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Tiến Mạnh
 */
public class QLBH extends javax.swing.JPanel {

    List<ChiTietHD> listCTHD = new ArrayList<>();
    String maSP = "";
    List<KhachHang> listKH = new ArrayList<>();
    KhachHang_DAO khd = new KhachHang_DAO();
    SanPham_DAO spd = new SanPham_DAO();
    List<NhanVien> listNV = new ArrayList<>();
    List<SanPham> listSP = new ArrayList<>();
    HoaDon_DAO hdd = new HoaDon_DAO();
    ChiTietHD_DAO cthdd = new ChiTietHD_DAO();

    public QLBH() throws SQLException {
        initComponents();
        init();
    }

    private void init() throws SQLException {
        //AutoCompleteDecorator.decorate(cbxMaKH);
        // AutoCompleteDecorator.decorate(cbxTenSP);
         setBackground(new Color(240, 240, 240));
        fillCBX();
        String maHD = hdd.maHD_TuSinh();
        txtMaHoaDon.setText(maHD);
        rdoTienMat.setSelected(true);
        txtKhachTra.setText("0");
        txtTongTien.setText("0");
        txtTraLaiKhach.setText("0");
        txtGiamGia.setText("0");
        fillProduct();

    }

    private void fillProduct() {
        for (SanPham sanpham : listSP) {
            if (sanpham.getTenSP().equals(cbxTenSP.getSelectedItem().toString())) {
                maSP = sanpham.getMaSP();
                txtTienCong.setText(sanpham.getTienCong() + "");
                if (cbxHinhThucMua.getSelectedIndex() == 1) {
                    txtDonGia.setText(new BigDecimal(sanpham.getGiaBanRa()) + "");
                } else {
                    txtDonGia.setText(new BigDecimal(sanpham.getGiaMuaVao()) + "");
                }
                txtKhoiLuong.setText(sanpham.getKhoiLuong() + "");
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbxTenSP = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtTienCong = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtThanhTien = new javax.swing.JTextField();
        rdoTienMat = new javax.swing.JRadioButton();
        rdoPhanTram = new javax.swing.JRadioButton();
        txtGiamGia = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDonChiTiet = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        txtKhachTra = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtTraLaiKhach = new javax.swing.JTextField();
        lblBangChu = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtKhoiLuong = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbxHTTT = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cbxHinhThucMua = new javax.swing.JComboBox<>();
        cbxTrangThaiHD = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbxMaKH = new javax.swing.JComboBox<>();
        btnThemKH = new javax.swing.JButton();
        btnThemHD = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnCapNhatHD = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 204, 204));
        jLabel11.setText("Quản lý mua bán hàng");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(618, 28, -1, -1));

        jLabel1.setText("Tên sản phẩm");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 96, -1, -1));

        cbxTenSP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTenSPItemStateChanged(evt);
            }
        });
        add(cbxTenSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(191, 92, 129, -1));

        jLabel17.setText("Số lượng");
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 195, -1, -1));

        txtSoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoLuongKeyReleased(evt);
            }
        });
        add(txtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(191, 195, 124, -1));

        jLabel18.setText("Đơn giá");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(374, 92, -1, -1));

        txtDonGia.setEditable(false);
        add(txtDonGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(434, 92, 124, -1));

        jLabel19.setText("Tiền công");
        add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 143, -1, -1));

        txtTienCong.setEditable(false);
        add(txtTienCong, new org.netbeans.lib.awtextra.AbsoluteConstraints(191, 143, 124, -1));

        jLabel20.setText("Giảm giá");
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 242, -1, -1));

        jLabel21.setText("Thành tiền");
        add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 314, -1, -1));

        txtThanhTien.setEditable(false);
        add(txtThanhTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(201, 314, 124, -1));

        buttonGroup1.add(rdoTienMat);
        rdoTienMat.setText("Tiền mặt");
        rdoTienMat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoTienMatMouseClicked(evt);
            }
        });
        add(rdoTienMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 242, -1, -1));

        buttonGroup1.add(rdoPhanTram);
        rdoPhanTram.setText("Phần trăm %");
        rdoPhanTram.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoPhanTramMouseClicked(evt);
            }
        });
        add(rdoPhanTram, new org.netbeans.lib.awtextra.AbsoluteConstraints(251, 242, -1, -1));

        txtGiamGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGiamGiaKeyReleased(evt);
            }
        });
        add(txtGiamGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(361, 242, 125, -1));

        tblHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Mã sản phẩm", "Tiền công", "Đơn giá", "Giảm giá", "Số lượng", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonChiTietMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHoaDonChiTiet);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 480, 848, 243));

        jLabel10.setText("Khách trả");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 150, -1, -1));

        txtKhachTra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtKhachTraMouseExited(evt);
            }
        });
        add(txtKhachTra, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 140, 124, -1));

        jLabel12.setText("Tổng tiền");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 230, -1, -1));

        txtTongTien.setEditable(false);
        add(txtTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 230, 125, -1));

        jLabel23.setText("Trả lại khách");
        add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 190, -1, -1));

        txtTraLaiKhach.setEditable(false);
        add(txtTraLaiKhach, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 190, 124, -1));

        lblBangChu.setText(" ");
        add(lblBangChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 280, 373, -1));

        jLabel22.setText("Bằng chữ:");
        add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 280, -1, -1));

        jLabel2.setText("Khối lượng");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(361, 145, -1, -1));

        txtKhoiLuong.setEditable(false);
        add(txtKhoiLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(434, 142, 124, -1));

        jLabel3.setText("Mã hóa đơn");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(613, 96, -1, -1));

        txtMaHoaDon.setEditable(false);
        add(txtMaHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(687, 93, 124, -1));

        jLabel8.setText("HTTT");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 150, -1, -1));

        cbxHTTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Thẻ tín dụng" }));
        add(cbxHTTT, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 150, 125, -1));

        jLabel9.setText("HT mua bán");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 190, -1, -1));

        cbxHinhThucMua.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mua", "Bán" }));
        cbxHinhThucMua.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxHinhThucMuaItemStateChanged(evt);
            }
        });
        add(cbxHinhThucMua, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 190, 125, -1));

        cbxTrangThaiHD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chưa thanh toán", "Đã thanh toán" }));
        add(cbxTrangThaiHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 230, 125, -1));

        jLabel13.setText("Trạng thái HD");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 230, -1, -1));

        jLabel6.setText("Mã khách hàng");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 100, -1, -1));

        add(cbxMaKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 90, 129, -1));

        btnThemKH.setText("Thêm khách hàng");
        btnThemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKHActionPerformed(evt);
            }
        });
        add(btnThemKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 90, -1, -1));

        btnThemHD.setText("Thêm hóa đơn");
        btnThemHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHDActionPerformed(evt);
            }
        });
        add(btnThemHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 140, 110, -1));

        btnThem.setText("Thêm HDCT");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 370, -1, -1));

        btnSua.setText("Sửa HDCT");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 370, -1, -1));

        btnDelete.setText("Xóa HDCT");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 370, -1, -1));

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        add(btnLamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 220, 110, -1));

        btnCapNhatHD.setText("Cập nhật hóa đơn");
        btnCapNhatHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatHDActionPerformed(evt);
            }
        });
        add(btnCapNhatHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 180, 110, -1));

        btnExport.setText("Export");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });
        add(btnExport, new org.netbeans.lib.awtextra.AbsoluteConstraints(1123, 270, 110, -1));
    }// </editor-fold>//GEN-END:initComponents
    public void fillCBX() {
        AutoCompleteDecorator.decorate(cbxTenSP);
        AutoCompleteDecorator.decorate(cbxMaKH);
        listKH = khd.selectAllKH();
        listSP = spd.selectAll();
        for (KhachHang khachHang : listKH) {
            cbxMaKH.addItem(khachHang.getMaKh());
        }
        for (SanPham sanpham : listSP) {
            cbxTenSP.addItem(sanpham.getTenSP());
        }

    }

    public String getMaKH(String maKH) {
        System.out.println("" + maKH);
        return maKH;
    }


    private void thanhTien() throws ParseException {
        if (txtGiamGia.getText().isEmpty()) {
            Msgbox.alert(this, "không để trống giảm giá");
            return;
        }
        if (rdoTienMat.isSelected()) {
            double dongia = Double.parseDouble(txtDonGia.getText());
            int sl = Integer.parseInt(txtSoLuong.getText());
            double TTChưaGiamGia = dongia * sl;
            float giamGia = 0;
            try {
                giamGia = Float.parseFloat(txtGiamGia.getText());
                if (giamGia < 0) {
                    Msgbox.alert(this, "Số tiền giảm giá >=0");
                    txtGiamGia.setText("");
                    return;
                }
            } catch (Exception e) {
                Msgbox.alert(this, "Số tiền giảm giá là số >=0");
                txtGiamGia.setText("");
                return;
            }
            if (giamGia > TTChưaGiamGia) {
                Msgbox.alert(this, "Số tiền giảm giá <= Số tiền cần thanh toán");
                txtGiamGia.setText("");
                return;
            }
            double thanhTien = TTChưaGiamGia - giamGia;
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            txtThanhTien.setText(df.format(thanhTien) + "");
        }
        if (rdoPhanTram.isSelected()) {
            double dongia = Double.parseDouble(txtDonGia.getText());
            int sl = Integer.parseInt(txtSoLuong.getText());
            double TTChưaGiamGia = dongia * sl;
            int giamGia = 0;
            try {
                giamGia = Integer.parseInt(txtGiamGia.getText());
                if (giamGia < 0) {
                    Msgbox.alert(this, "Phần trăm giảm là số nguyên dương");
                    txtGiamGia.setText("");
                    return;
                }
                if (giamGia > 100) {
                    Msgbox.alert(this, "Giảm giá không vượt 100%");
                    txtGiamGia.setText("");
                    return;
                }
            } catch (Exception e) {
                Msgbox.alert(this, "Phần trăm giảm là số nguyên dương");
                txtGiamGia.setText("");
                return;
            }

            double thanhTien = TTChưaGiamGia / 100 * (100 - giamGia);
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            txtThanhTien.setText(df.format(thanhTien) + "");
        }

    }
    private void cbxTenSPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTenSPItemStateChanged
        fillProduct();
        txtSoLuong.setText("");
        txtGiamGia.setText("0");
        txtThanhTien.setText("");
    }//GEN-LAST:event_cbxTenSPItemStateChanged

    private void txtSoLuongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongKeyReleased
        try {
            
            double dongia = Double.parseDouble(txtDonGia.getText());
            int sl = Integer.parseInt(txtSoLuong.getText());
            double TTChưaGiamGia = dongia * sl;
            double giamGia = Double.parseDouble(txtGiamGia.getText());
            double thanhTien = TTChưaGiamGia - giamGia;
            txtThanhTien.setText(new BigDecimal(thanhTien) + "");
            if (sl <= 0) {
                Msgbox.alert(this, "Số lượng là số nguyên dương");
                txtSoLuong.setText("");
                return;
            }
            for (SanPham sanpham : listSP) {
            if (sanpham.getTenSP().equals(cbxTenSP.getSelectedItem().toString())) {
                maSP = sanpham.getMaSP();
            }
            }
            int soLuongKho =spd.selectSL(maSP);
            if(soLuongKho<sl){
              Msgbox.alert(this, "Số lượng trong kho nhỏ hơn số lượng nhập");
                txtSoLuong.setText("");
                txtThanhTien.setText("0");
                return;  
            }

            if (sl <= 0) {
                Msgbox.alert(this, "Số lượng là số nguyên dương");
                txtSoLuong.setText("");
                return;
            }
            // thanhTien();
        } catch (Exception e) {
            Msgbox.alert(this, "Số lượng là số nguyên dương");
            txtSoLuong.setText("");

            return;
        }
    }//GEN-LAST:event_txtSoLuongKeyReleased

    public void insertHD() throws ParseException {
        try {
            if (txtKhachTra.getText().isEmpty()) {
                Msgbox.alert(this, "Không để trống tiền khách trả");
                return;
            }
            double khtra = Double.parseDouble(txtKhachTra.getText());
            double TT = Double.parseDouble(txtTongTien.getText());

            if (khtra < TT) {
                Msgbox.alert(this, "Khách trả phải >= Tổng tiền");
                return;
            }
        } catch (Exception e) {
            Msgbox.alert(this, "Tiền khách trả > 0");
            return;
        }
        HoaDon hd = getForm();
        hdd.insert(hd);
    }

    private HoaDon getForm() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Calendar cal = Calendar.getInstance();
        java.sql.Timestamp ngayGD = new java.sql.Timestamp(cal.getTimeInMillis());
        HoaDon hd = new HoaDon();
        hd.setMaHD(txtMaHoaDon.getText());
        hd.setMaNV(Auth.user.getMaNV());
        hd.setMaKH(cbxMaKH.getSelectedItem().toString());
        hd.setNgayGD(ngayGD);
        if (cbxHTTT.getSelectedItem().equals("Tiền mặt")) {
            hd.setHinhThucThanhToan(true);
        } else {
            hd.setHinhThucThanhToan(false);
        }
        if (cbxHinhThucMua.getSelectedItem().equals("Mua")) {
            hd.setHinhthucmua(true);
        } else {
            hd.setHinhthucmua(false);
        }
        hd.setKhachTra(Double.parseDouble(txtKhachTra.getText()));
        hd.setTongTien(Double.parseDouble(txtTongTien.getText()));
        hd.setTrangThaiHD(cbxTrangThaiHD.getSelectedItem() + "");
        return hd;
    }

    private void insertHDCT() throws SQLException {
        for (SanPham sanpham : listSP) {
            if (sanpham.getTenSP().equals(cbxTenSP.getSelectedItem().toString())) {
                maSP = sanpham.getMaSP();
            }
            }
           
        for (ChiTietHD chiTietHD : listCTHD) {
            if (chiTietHD.getMaSp().equals(maSP)) {
                Msgbox.alert(this, "Sản phẩm đã tồn tại trong hóa đơn này");
                return;
            }
        }
        try {
            int sl = Integer.parseInt(txtSoLuong.getText());
            if (sl <= 0) {
                Msgbox.alert(this, "Số lượng lớn hơn 0");
                txtSoLuong.setText("");
                return;
            }
        } catch (Exception e) {
            Msgbox.alert(this, "Số lượng là số nguyên");
            return;
        }

        if (rdoPhanTram.isSelected() == false && rdoTienMat.isSelected() == false) {
            Msgbox.alert(this, "Chọn giảm giá");
            return;
        }
        try {
            float gg = Float.parseFloat(txtGiamGia.getText());
            if (gg < 0) {
                Msgbox.alert(this, "giảm giá tối thiểu bằng 0");
                return;
            }
        } catch (Exception e) {
            Msgbox.alert(this, "giảm giá là số ");
            return;
        }

        ChiTietHD ctHD = getFormCT();
        cthdd.insert(ctHD);
        fillTableCTHD();
        Msgbox.alert(this, "Thêm thành công");
        
    }

    private void fillTableCTHD() {
        listCTHD = cthdd.selectByMAHD(txtMaHoaDon.getText());
        DefaultTableModel model = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        model.setRowCount(0);
        for (ChiTietHD CTHD : listCTHD) {
            Object[] row = new Object[]{
                CTHD.getMaHD(), CTHD.getMaSp(), CTHD.getTienCong(), new BigDecimal(CTHD.getDonGia()),
                CTHD.getGiamGia(), CTHD.getSoLuong(), new BigDecimal(CTHD.getThanhTien())
            };
            model.addRow(row);
        }
    }

    private ChiTietHD getFormCT() {
        ChiTietHD cthd = new ChiTietHD();
        cthd.setMaHD(txtMaHoaDon.getText());
        cthd.setMaSp(maSP);
        cthd.setTienCong(Double.parseDouble(txtTienCong.getText()));
        cthd.setDonGia(Double.parseDouble(txtDonGia.getText()));
        cthd.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        double dongia = Double.parseDouble(txtDonGia.getText());
        int sl = Integer.parseInt(txtSoLuong.getText());
        double TTChưaGiamGia = dongia * sl;
        double giamGia = Double.parseDouble(txtGiamGia.getText());
        if (rdoTienMat.isSelected()) {
            cthd.setGiamGia(giamGia);
            double thanhTien = TTChưaGiamGia - giamGia;
            cthd.setThanhTien(thanhTien);
        }
        if (rdoPhanTram.isSelected()) {
            double tienGiam = TTChưaGiamGia - TTChưaGiamGia / 100 * (100 - giamGia);
            cthd.setGiamGia(tienGiam);
            double thanhTien = TTChưaGiamGia / 100 * (100 - giamGia);
            cthd.setThanhTien(thanhTien);
        }
        return cthd;
    }

    private void updateHDCT() {
        try {
            int sl = Integer.parseInt(txtSoLuong.getText());
            if (sl <= 0) {
                Msgbox.alert(this, "Số lượng là số nguyên dương");
                txtSoLuong.setText("");
                return;
            }
            if (rdoPhanTram.isSelected() == false && rdoTienMat.isSelected() == false) {
                Msgbox.alert(this, "Chọn giảm giá");
                return;
            }
            if (txtGiamGia.getText().isEmpty()) {
                Msgbox.alert(this, "nhập phần trăm hoặc số tiền cần giảm");
                return;
            }
            ChiTietHD ctHD = getFormCT();
            cthdd.update(ctHD);
            fillTableCTHD();

        } catch (Exception e) {
            Msgbox.alert(this, "Hóa đơn chi tiết đã tồn tại");
            e.printStackTrace();

//            e.printStackTrace();
            return;
        }
    }

    private static String formatNumberForRead(double number) {
        NumberFormat nf = NumberFormat.getInstance();
        String temp = nf.format(number);
        String strReturn = "";
        int slen = temp.length();
        for (int i = 0; i < slen; i++) {
            if (String.valueOf(temp.charAt(i)).equals(".")) {
                break;
            } else if (Character.isDigit(temp.charAt(i))) {
                strReturn += String.valueOf(temp.charAt(i));
            }
        }
        return strReturn;

    }

    private static String numberToString(double number) {
        String sNumber = formatNumberForRead(number);
        // Tao mot bien tra ve
        String sReturn = "";
        // Tim chieu dai cua chuoi
        int iLen = sNumber.length();
        // Lat nguoc chuoi nay lai
        String sNumber1 = "";
        for (int i = iLen - 1; i >= 0; i--) {
            sNumber1 += sNumber.charAt(i);
        }
        // Tao mot vong lap de doc so
        // Tao mot bien nho vi tri cua sNumber
        int iRe = 0;
        do {
            // Tao mot bien cat tam
            String sCut = "";
            if (iLen > 3) {
                sCut = sNumber1.substring((iRe * 3), (iRe * 3) + 3);
                sReturn = Read(sCut, iRe) + sReturn;
                iRe++;
                iLen -= 3;
            } else {
                sCut = sNumber1.substring((iRe * 3), (iRe * 3) + iLen);
                sReturn = Read(sCut, iRe) + sReturn;
                break;
            }
        } while (true);
        if (sReturn.length() > 1) {
            sReturn = sReturn.substring(0, 1).toUpperCase() + sReturn.substring(1);
        }
        if (number == 0) {
            sReturn = "Không ";
        }
        sReturn = sReturn + "đồng";
        return sReturn;
    }

    // Khoi tao ham Read
    private static String Read(String sNumber, int iPo) {
        // Tao mot bien tra ve
        String sReturn = "";
        // Tao mot bien so
        String sPo[] = {"", "ngàn" + " ",
            "triệu" + " ", "tỷ" + " "};
        String sSo[] = {"không" + " ", "một" + " ",
            "hai" + " ", "ba" + " ",
            "bốn" + " ", "năm" + " ",
            "sáu" + " ", "bảy" + " ",
            "tám" + " ", "chín" + " "};
        String sDonvi[] = {"", "mươi" + " ",
            "trăm" + " "};
        // Tim chieu dai cua chuoi
        int iLen = sNumber.length();
        // Tao mot bien nho vi tri doc
        int iRe = 0;
        // Tao mot vong lap de doc so
        for (int i = 0; i < iLen; i++) {
            String sTemp = "" + sNumber.charAt(i);
            int iTemp = Integer.parseInt(sTemp);
            // Tao mot bien ket qua
            String sRead = "";
            // Kiem tra xem so nhan vao co = 0 hay ko
            if (iTemp == 0) {
                switch (iRe) {
                    case 0:
                        break;// Khong lam gi ca
                    case 1: {
                        if (Integer.parseInt("" + sNumber.charAt(0)) != 0) {
                            sRead = "lẻ" + " ";
                        }
                        break;
                    }
                    case 2: {
                        if (Integer.parseInt("" + sNumber.charAt(0)) != 0
                                && Integer.parseInt("" + sNumber.charAt(1)) != 0) {
                            sRead = "không trăm" + " ";
                        }
                        break;
                    }
                }
            } else if (iTemp == 1) {
                switch (iRe) {
                    case 1:
                        sRead = "mười" + " ";
                        break;
                    default:
                        sRead = "một" + " " + sDonvi[iRe];
                        break;
                }
            } else if (iTemp == 5) {
                switch (iRe) {
                    case 0: {
                        if (sNumber.length() <= 1) {
                            sRead = "năm" + " ";
                        } else if (Integer.parseInt("" + sNumber.charAt(1)) != 0) {
                            sRead = "lăm" + " ";
                        } else {
                            sRead = "năm" + " ";
                        }
                        break;
                    }
                    default:
                        sRead = sSo[iTemp] + sDonvi[iRe];
                }
            } else {
                sRead = sSo[iTemp] + sDonvi[iRe];
            }

            sReturn = sRead + sReturn;
            iRe++;
        }
        if (sReturn.length() > 0) {
            sReturn += sPo[iPo];
        }

        return sReturn;
    }

    private void hinhThucMuaBan() {
        if (cbxHinhThucMua.getSelectedIndex() == 0) {
            txtKhachTra.setEditable(false);
            txtKhachTra.setText("0");
        } else {
            txtKhachTra.setEditable(true);
        }
    }

    private void exportExcel() throws FileNotFoundException, IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Hóa đơn");

        XSSFRow row = null;
        Cell cell = null;
        XSSFFont font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 18); // font size
        font.setColor(IndexedColors.RED.getIndex()); // text color

        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);

        //font2
        XSSFFont font2 = sheet.getWorkbook().createFont();
        font2.setFontName("Times New Roman");

        font2.setFontHeightInPoints((short) 16); // font size
        font2.setColor(IndexedColors.RED.getIndex()); // text color

//style 2
        CellStyle cellStyle2 = sheet.getWorkbook().createCellStyle();
        cellStyle2.setFont(font2);
        //font3
        XSSFFont font3 = sheet.getWorkbook().createFont();
        font3.setFontName("Times New Roman");
        font3.setFontHeightInPoints((short) 14); // font size
        font3.setColor(IndexedColors.RED.getIndex()); // text color
//style 3
        CellStyle cellStyle3 = sheet.getWorkbook().createCellStyle();
        cellStyle3.setFont(font3);

        row = sheet.createRow(2);
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Công ty TNHH Vàng Bạc");
        cell.setCellStyle(cellStyle);
        cell = row.createCell(11, CellType.STRING);
        cell.setCellValue("Giấy đảm bảo vàng");

        cell.setCellStyle(cellStyle);

        //dòng 3
        row = sheet.createRow(3);
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Toản Huyền");
        cell.setCellStyle(cellStyle);
        cell = row.createCell(11, CellType.STRING);
        cell.setCellValue("   Uy tín quý hơn vàng");
        cell.setCellStyle(cellStyle2);
        //dòng 4
        row = sheet.createRow(4);
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Đ/c:Khu Minh Khanh -Xã Minh Khai- Huyện Tân Sơn - Tỉnh "
                + "Phú Thọ   ĐT:0978154115-0986334292");
        cell.setCellStyle(cellStyle2);
        //dòng 5
        row = sheet.createRow(5);
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("    Chuyên mua nữ trang vàng 9999-Vàng tây các loại");
        cell.setCellStyle(cellStyle);
        //dòng 6
        row = sheet.createRow(6);
        cell = row.createCell(1, CellType.STRING);
        for (KhachHang kh : listKH) {
            if (cbxMaKH.getSelectedItem().toString().equals(kh.getMaKh())) {
                cell.setCellValue("Bán cho ông (bà): " + kh.getHoTen());
            }
        }
        cell.setCellStyle(cellStyle3);
        //dòng 7
        row = sheet.createRow(7);
        cell = row.createCell(1, CellType.STRING);
        for (KhachHang kh : listKH) {
            if (cbxMaKH.getSelectedItem().toString().equals(kh.getMaKh())) {
                cell.setCellValue("Địa chỉ: " + kh.getDiaChi());
            }
        }

        cell.setCellStyle(cellStyle3);

        //dòng 8
        row = sheet.createRow(8);
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Nhân viên: " + Auth.user.getHoTen());
        cell.setCellStyle(cellStyle3);

        //dòng 9
        row = sheet.createRow(9);
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Tổng tiền: " + txtTongTien.getText() + "đ");
        cell.setCellStyle(cellStyle3);

        row = sheet.createRow(10);
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Bằng chữ : " + lblBangChu.getText());
        cell.setCellStyle(cellStyle3);

        row = sheet.createRow(12);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("STT");
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Mã hóa đơn");
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Mã sản phẩm");
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Tiền công");
        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Đơn giá");
        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("Giảm giá");
        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("Số lượng");
        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("Thành tiền");

        listCTHD = cthdd.selectByMAHD(txtMaHoaDon.getText());
        for (int i = 0; i < listCTHD.size(); i++) {
            ChiTietHD hdct = listCTHD.get(i);
            row = sheet.createRow(13 + i);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(i + 1);
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(hdct.getMaHD());
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(hdct.getMaSp());
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(hdct.getSoLuong());
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(hdct.getTienCong());
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue(hdct.getDonGia());
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue(hdct.getSoLuong());
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue(hdct.getThanhTien());

        }
        //dòng 20
        row = sheet.createRow(20);
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Lưu ý");
        cell.setCellStyle(cellStyle);

        //dòng 21
        row = sheet.createRow(21);
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Thưa quý khách chúng tôi có ưu đãi cho khách hàng mua");
        cell.setCellStyle(cellStyle3);

        //dòng 22
        row = sheet.createRow(22);
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Quý khách giữ lại phiếu đảm bảo để tiện mua bán đổi");
        cell.setCellStyle(cellStyle3);

        //dòng 23
        row = sheet.createRow(23);
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Rất hân hạnh được phục vụ quý khách");
        cell.setCellStyle(cellStyle);
        cell = row.createCell(11, CellType.STRING);
        cell.setCellValue("Tây Sơn " + java.time.LocalDate.now());
        cell.setCellStyle(cellStyle2);

        //dòng 24
        row = sheet.createRow(24);
        cell = row.createCell(11, CellType.STRING);
        cell.setCellValue("Đại diện công ty");
        cell.setCellStyle(cellStyle);

        //////////--------------------------\\\\\\\\\\
        JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jFileChooser.setDialogTitle("Chọn thư mục: ");

        int result = jFileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = new File(jFileChooser.getSelectedFile() + txtMaHoaDon.getText() + ".xlsx");

            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
        }
    }

    private void exportPdf() throws FileNotFoundException, DocumentException, IOException {
        // Tạo đối tượng tài liệu
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);

        JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jFileChooser.setDialogTitle("Chọn thư mục: ");

        int result = jFileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = new File(jFileChooser.getSelectedFile() + "" + ".pdf");

            FileOutputStream fos = new FileOutputStream(file);
            PdfWriter.getInstance(document, fos);
            document.open();

            BaseFont bf = BaseFont.createFont("Roboto-Italic.TTF",
                    BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font1 = new Font(bf, 10, Font.NORMAL);
            font1.setColor(BaseColor.RED);
            Font font11_bold = new Font(bf, 11, Font.BOLD);

            String tenKH = "";
            for (KhachHang kh : listKH) {
                if (cbxMaKH.getSelectedItem().toString().equals(kh.getMaKh())) {
                    tenKH = kh.getHoTen();
                }
            }
            String diaChi = "";
            for (KhachHang kh : listKH) {
                if (cbxMaKH.getSelectedItem().toString().equals(kh.getMaKh())) {
                    diaChi = kh.getDiaChi();
                }
            }

            document.add(new Paragraph("Công ty TNHH Vàng Bạc                              Giấy đảm bảo vàng\n"
                    + "       Toản Huyền                                               Uy tín quý hơn vàng\n"
                    + "Đ/c:Khu Minh Khanh -Xã Minh Khai- Huyện tân sơn - Tỉnh "
                + "Phú Thọ   ĐT:0978154115-0986334292\n"
                    + "        Chuyên mua nữ trang vàng 9999-Vàng tây các loại\n\n"
                    + "Bán cho ông (bà): " + tenKH + "  Địa chỉ: " + diaChi + "\n"
                    + "Tổng tiền: " + txtTongTien.getText() + "d\n"
                    + "Bằng chữ: " + lblBangChu.getText() + "\n\n", font1));
            //document.add(new Paragraph("This is fontname_Times vẫn bị thế", font1));

            // p.setAlignment(Paragraph.ALIGN_CENTER);
            PdfPTable tbl = new PdfPTable(8);
            PdfPCell cell;

            cell = new PdfPCell(new Paragraph("STT", font1));
            tbl.addCell(cell);
            cell = new PdfPCell(new Paragraph("Mã hóa đơn", font1));
            tbl.addCell(cell);
            cell = new PdfPCell(new Paragraph("Mã sản phẩm", font1));
            tbl.addCell(cell);
            cell = new PdfPCell(new Paragraph("Tiền công", font1));
            tbl.addCell(cell);
            cell = new PdfPCell(new Paragraph("Đơn giá", font1));
            tbl.addCell(cell);
            cell = new PdfPCell(new Paragraph("Giảm giá", font1));
            tbl.addCell(cell);
            cell = new PdfPCell(new Paragraph("Số lượng", font1));
            tbl.addCell(cell);
            cell = new PdfPCell(new Paragraph("Thành tiền", font1));
            tbl.addCell(cell);

            //font11_bold)); cell.setPaddingLeft(5.0f); cell.setBorder(0);
            for (int i = 0; i < tblHoaDonChiTiet.getRowCount(); i++) {
                int STT = i + 1;
                tbl.addCell("" + STT);
                String maHD = tblHoaDonChiTiet.getValueAt(i, 0).toString();
                String maSP = tblHoaDonChiTiet.getValueAt(i, 1).toString();
                String tienCong = tblHoaDonChiTiet.getValueAt(i, 2).toString();
                String donGia = tblHoaDonChiTiet.getValueAt(i, 3).toString();
                String giamGia = tblHoaDonChiTiet.getValueAt(i, 4).toString();
                String soLuong = tblHoaDonChiTiet.getValueAt(i, 5).toString();
                String thanhTien = tblHoaDonChiTiet.getValueAt(i, 6).toString();
                tbl.addCell(maHD);
                tbl.addCell(maSP);
                tbl.addCell(tienCong);
                tbl.addCell(donGia);
                tbl.addCell(giamGia);
                tbl.addCell(soLuong);
                tbl.addCell(thanhTien);
            }

            document.add(tbl);

            document.add(new Paragraph("Lưu ý:\n"
                    + "Thưa quý khách chúng tôi có ưu đãi cho khách hàng mua\n"
                    + "Quý khách giữu lại phiếu đảm bảo để tiện mua bán đổi\n"
                    + "Rất hân hạnh được phục vụ quý khách                                                                        "
                    + "Tây Sơn " + java.time.LocalDate.now() + "\n"
                    + "                                                                                                                 "
                            + "                              Đại diện công ty", font1));

            document.close();
            // workbook.write(fos);
            // fos.close();
        }
    }
    private void rdoTienMatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoTienMatMouseClicked
        if (rdoTienMat.isSelected()) {
            txtGiamGia.setEnabled(true);
            txtGiamGia.setEditable(true);
        }
    }//GEN-LAST:event_rdoTienMatMouseClicked

    private void rdoPhanTramMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoPhanTramMouseClicked

    }//GEN-LAST:event_rdoPhanTramMouseClicked

    private void txtGiamGiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiamGiaKeyReleased
        try {
            thanhTien();
        } catch (ParseException e) {
           e.printStackTrace();
        }
    }//GEN-LAST:event_txtGiamGiaKeyReleased

    private void tblHoaDonChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChiTietMouseClicked
        int row = tblHoaDonChiTiet.getSelectedRow();
        ChiTietHD cthd = listCTHD.get(row);
        for (SanPham sp : listSP) {
            if (cthd.getMaSp().equals(sp.getMaSP())) {
                cbxTenSP.setSelectedItem(sp.getTenSP());
            }
        }
        txtSoLuong.setText("" + cthd.getSoLuong());
        txtGiamGia.setText("" + cthd.getGiamGia());
        txtThanhTien.setText("" + cthd.getThanhTien());

    }//GEN-LAST:event_tblHoaDonChiTietMouseClicked

    private void txtKhachTraMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtKhachTraMouseExited
        float khachTra = 0;
        try {
            khachTra = Float.parseFloat(txtKhachTra.getText());
            if (khachTra < 0) {
                Msgbox.alert(this, "Số tiền trả cần tối thiểu bằng tổng tiền");
                return;

            }
        } catch (Exception e) {
            Msgbox.alert(this, "sai định dạng số tiền trả");
            return;
        }
        float tongtien = Float.parseFloat(txtTongTien.getText());
        float tralai = khachTra - tongtien;
        if (tralai < 0) {
            Msgbox.alert(this, "Số tiền trả cần tối thiểu bằng tổng tiền");
            return;
        }
        txtTraLaiKhach.setText(new BigDecimal(tralai) + "");
    }//GEN-LAST:event_txtKhachTraMouseExited

    private void btnThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKHActionPerformed
        this.setVisible(false);
        int DISPOSE_ON_CLOSE = Main.DISPOSE_ON_CLOSE;
        QuanLiKhachHang QLKH = new QuanLiKhachHang();
        QLKH.setVisible(true);
    }//GEN-LAST:event_btnThemKHActionPerformed

    private void btnThemHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHDActionPerformed
        try {
            insertHD();
            Msgbox.alert(this, "Thêm hóa đơn thành công");

        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnThemHDActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try {
            HoaDon hd = hdd.selectById(txtMaHoaDon.getText());
            double tongTien = 0;

            if (hd == null) {
                Msgbox.alert(this, "Mã hóa đơn chưa tồn tại");
                return;
            }
            insertHDCT();
            for (ChiTietHD cthoadon : listCTHD) {
                tongTien += cthoadon.getThanhTien();
            }

            txtTongTien.setText(new BigDecimal(tongTien) + "");
            if (cbxHinhThucMua.getSelectedIndex() == 0) {
                txtTraLaiKhach.setText(new BigDecimal(tongTien) + "");
            }
            lblBangChu.setText(numberToString(tongTien));
            HoaDon TTHD = getForm();
            hdd.updatetongTien(TTHD);
           for (SanPham sanpham : listSP) {
            if (sanpham.getTenSP().equals(cbxTenSP.getSelectedItem().toString())) {
                maSP = sanpham.getMaSP();
            }
            }
           int soluong =0 -Integer.parseInt(txtSoLuong.getText());
            spd.updateSoLuong(soluong+"", maSP);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed

        cbxHTTT.setSelectedIndex(0);
        cbxHinhThucMua.setSelectedIndex(0);
        cbxMaKH.setSelectedIndex(0);
        cbxTrangThaiHD.setSelectedIndex(0);
        txtTongTien.setText("0");
        txtKhachTra.setText("0");
        txtTraLaiKhach.setText("0");
        txtGiamGia.setText("0");
        txtSoLuong.setText("");
        txtThanhTien.setText("");
        DefaultTableModel model = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        model.setRowCount(0);
                cbxMaKH.removeAllItems();
        listKH = khd.selectAllKH();
        for (KhachHang khachHang : listKH) {
            cbxMaKH.addItem(khachHang.getMaKh());
        }

        try {
            String maHD = hdd.maHD_TuSinh();
            txtMaHoaDon.setText(maHD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        try {
            double tongTien = 0;
            updateHDCT();

            for (ChiTietHD cthoadon : listCTHD) {
                tongTien += cthoadon.getThanhTien();
            }
            txtTongTien.setText(tongTien + "");
            HoaDon TTHD = getForm();
            hdd.updatetongTien(TTHD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnCapNhatHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatHDActionPerformed
        try {
            HoaDon hd = getForm();
            hdd.update(hd);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnCapNhatHDActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (tblHoaDonChiTiet.getSelectedRow() < 0) {
            Msgbox.alert(this, "Chọn 1 dòng rồi xóa");
            return;
        }

        int result = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa?", "delete", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            ChiTietHD ctHD = getFormCT();
            double tongTien = Double.parseDouble(txtTongTien.getText()) - Double.parseDouble("" + ctHD.getThanhTien());
            cthdd.deleteHDCT(ctHD);
            txtTongTien.setText(new BigDecimal(tongTien) + "");
            lblBangChu.setText(numberToString(tongTien));
            HoaDon TTHD;
            try {
                TTHD = getForm();
                hdd.updatetongTien(TTHD);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }

            fillTableCTHD();
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void cbxHinhThucMuaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxHinhThucMuaItemStateChanged
        hinhThucMuaBan();
        fillProduct();
    }//GEN-LAST:event_cbxHinhThucMuaItemStateChanged

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed

        try {
            JFrame frame = new JFrame();
            String[] options = new String[3];
            options[0] = "Export Excel";
            options[1] = "Export PDF";
            options[2] = "Cancel";
            int result = JOptionPane.showOptionDialog(frame.getContentPane(), "Chọn kiểu bạn muốn xuất!", "Xuất file", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
            if (result == JOptionPane.YES_OPTION) {
                exportExcel();
            } else if (result == JOptionPane.NO_OPTION) {
                exportPdf();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException ex) {
            Logger.getLogger(QLHD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExportActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhatHD;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemHD;
    private javax.swing.JButton btnThemKH;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbxHTTT;
    private javax.swing.JComboBox<String> cbxHinhThucMua;
    public javax.swing.JComboBox<String> cbxMaKH;
    private javax.swing.JComboBox<String> cbxTenSP;
    private javax.swing.JComboBox<String> cbxTrangThaiHD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBangChu;
    private javax.swing.JRadioButton rdoPhanTram;
    private javax.swing.JRadioButton rdoTienMat;
    private javax.swing.JTable tblHoaDonChiTiet;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtKhachTra;
    private javax.swing.JTextField txtKhoiLuong;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTienCong;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txtTraLaiKhach;
    // End of variables declaration//GEN-END:variables
}
