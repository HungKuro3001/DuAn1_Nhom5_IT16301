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
import Entity.SanPham;
import Utils.Auth;
import Utils.Msgbox;
import java.awt.Color;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Tiến Mạnh
 */
public class QLHD extends javax.swing.JPanel {

    /**
     * Creates new form QLHD
     */
    KhachHang_DAO khd = new KhachHang_DAO();
    HoaDon_DAO hdd = new HoaDon_DAO();
    ChiTietHD_DAO cthdd = new ChiTietHD_DAO();
    SanPham_DAO spd = new SanPham_DAO();
    List<KhachHang> listKH = new ArrayList<>();
    List<HoaDon> listHD = new ArrayList<>();
    List<SanPham> listSP = new ArrayList<>();
    List<ChiTietHD> listCTHD = new ArrayList<>();

    public QLHD() throws SQLException {
        initComponents();
        init();
        setBackground(new Color(240, 240, 240));
        String maHD = hdd.maSP_TuSinh();
        txtMaHoaDon.setText(maHD);
        txtTongTien.setText("5");
        txtGiamGia.setEnabled(false);

    }

    private void fillTextField() throws SQLException {
        txtMANhanVien.setText(Auth.user.getMaNV());
        txtTenNV.setText(Auth.user.getHoTen());
        txtThoiGian.setText("" + java.time.LocalDate.now());

    }

    private void fillCBX() {
        AutoCompleteDecorator.decorate(cbxMaKH);
        AutoCompleteDecorator.decorate(cbxMASP);
        listKH = khd.selectAll();
        listSP = spd.selectAll();
        for (KhachHang khachHang : listKH) {
            cbxMaKH.addItem(khachHang.getMaKh());
        }
        for (SanPham sanpham : listSP) {
            cbxMASP.addItem(sanpham.getMaSP());
        }
    }

    private void init() throws SQLException {
        fillTable();
        fillTextField();
        fillCBX();
        fillNameCustomer();
        fillNameProduct();
        txtKhachTra.setText("0");
        txtTongTien.setText("0");
        txtTraLaiKhach.setText("0");
    }

    private void display() throws ParseException {
        int row = tblHoaDon.getSelectedRow();
        HoaDon hd = listHD.get(row);
        txtMaHoaDon.setText(hd.getMaHD());
        cbxMaKH.setSelectedItem(hd.getMaKH());
        txtMANhanVien.setText(hd.getMaNV());
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//       Date  ngay =  dateFormat.parse(""+hd.getNgayGD());
        txtThoiGian.setText("" + hd.getNgayGD());
        cbxHTTT.setSelectedItem(hd.isHinhThucThanhToan());
        if (hd.isHinhThucThanhToan() == true) {
            cbxHTTT.setSelectedItem("Tiền mặt");
        } else {
            cbxHTTT.setSelectedItem("Thẻ tín dụng");
        }
        if (hd.isHinhthucmua() == true) {
            cbxHinhThucMua.setSelectedItem("Trực tiếp");
        } else {
            cbxHinhThucMua.setSelectedItem("Online");
        }
        txtKhachTra.setText("" + hd.getKhachTra());
        txtTongTien.setText("" + hd.getTongTien());
        cbxTrangThaiHD.setSelectedItem(hd.getTrangThaiHD());
    }

    private void fillNameCustomer() {
        for (KhachHang khachHang : listKH) {
            if (cbxMaKH.getSelectedItem().toString().equals(khachHang.getMaKh())) {
                txtTenKH.setText(khachHang.getHoTen());
            }
        }
    }

    private void fillNameProduct() {
        for (SanPham sanpham : listSP) {
            if (sanpham.getMaSP().equals(cbxMASP.getSelectedItem().toString())) {
                txtTenSP.setText(sanpham.getTenSP());
                txtTienCong.setText(sanpham.getTienCong() + "");
                txtDonGia.setText(sanpham.getGiaBanRa() + "");
            }
        }
    }

    public HoaDon getForm() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Calendar cal = Calendar.getInstance();
        java.sql.Timestamp ngayGD = new java.sql.Timestamp(cal.getTimeInMillis());
        HoaDon hd = new HoaDon();
        hd.setMaHD(txtMaHoaDon.getText());
        hd.setMaNV(txtMANhanVien.getText());
        hd.setMaKH(cbxMaKH.getSelectedItem().toString());
        hd.setNgayGD(ngayGD);
        if (cbxHTTT.getSelectedItem().equals("Tiền mặt")) {
            hd.setHinhThucThanhToan(true);
        } else {
            hd.setHinhThucThanhToan(false);
        }
        if (cbxHinhThucMua.getSelectedItem().equals("Trực tiếp")) {
            hd.setHinhthucmua(true);
        } else {
            hd.setHinhthucmua(false);
        }
        hd.setKhachTra(Double.parseDouble(txtKhachTra.getText()));
        hd.setTongTien(Double.parseDouble(txtTongTien.getText()));
        hd.setTrangThaiHD(cbxTrangThaiHD.getSelectedItem() + "");
        return hd;
    }

    public void insert() throws ParseException {
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

    private void fillTable() {
        listHD = hdd.selectAll();
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        for (HoaDon hd : listHD) {
            model.addRow(new Object[]{hd.getMaHD(), hd.getMaNV(), hd.getMaKH(),
                hd.getNgayGD(), hd.isHinhThucThanhToan() == true ? "Tiền mặt" : "Thẻ tín dụng", hd.isHinhthucmua() == true ? "Trực tiếp" : "Online", hd.getKhachTra(), hd.getTongTien(), hd.getTrangThaiHD()});
        }
    }

    private void thanhTien() {
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
            txtThanhTien.setText(thanhTien + "");
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
            } catch (Exception e) {
                Msgbox.alert(this, "Phần trăm giảm là số nguyên dương");
                txtGiamGia.setText("");
                return;
            }

            double thanhTien = TTChưaGiamGia / 100 * (100 - giamGia);
            txtThanhTien.setText(thanhTien + "");
        }

    }

    private ChiTietHD getFormCT() {
        ChiTietHD cthd = new ChiTietHD();
        cthd.setMaHD(txtMaHoaDon.getText());
        cthd.setMaSp(cbxMASP.getSelectedItem().toString());
        cthd.setTienCong(Double.parseDouble(txtTienCong.getText()));
        cthd.setDonGia(Double.parseDouble(txtDonGia.getText()));
        cthd.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        double dongia = Double.parseDouble(txtDonGia.getText());
        int sl = Integer.parseInt(txtSoLuong.getText());
        double TTChưaGiamGia = dongia * sl;
        double giamGia = Integer.parseInt(txtGiamGia.getText());
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

    private void setForm(ChiTietHD cthd) {
        cbxMASP.setSelectedItem(cthd.getMaSp());

        txtTienCong.setText(cthd.getTienCong() + "");
        txtDonGia.setText(cthd.getDonGia() + "");
        txtSoLuong.setText(cthd.getSoLuong() + "");
        txtGiamGia.setText(cthd.getGiamGia() + "");
        txtThanhTien.setText(cthd.getThanhTien() + "");

    }

    private void insertHDCT() {
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
            cthdd.insert(ctHD);
            fillTableCTHD();

        } catch (Exception e) {
            Msgbox.alert(this, "Hóa đơn chi tiết đã tồn tại");

//            e.printStackTrace();
            return;
        }
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
    private void fillTableCTHD() {
        listCTHD = cthdd.selectByMAHD(txtMaHoaDon.getText());
        DefaultTableModel model = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        model.setRowCount(0);
        for (ChiTietHD CTHD : listCTHD) {
            Object[] row = new Object[]{
                CTHD.getMaHD(), CTHD.getMaSp(), CTHD.getTienCong(), CTHD.getDonGia(),
                CTHD.getGiamGia(), CTHD.getSoLuong(), CTHD.getThanhTien()
            };
            model.addRow(row);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMANhanVien = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtThoiGian = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbxMaKH = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbxHTTT = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cbxHinhThucMua = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtKhachTra = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cbxTrangThaiHD = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbxMASP = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtTienCong = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtThanhTien = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDonChiTiet = new javax.swing.JTable();
        rdoTienMat = new javax.swing.JRadioButton();
        rdoPhanTram = new javax.swing.JRadioButton();
        txtGiamGia = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        lblBangChu = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtTraLaiKhach = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnCapNhatCTHD = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 204, 204));
        jLabel11.setText("Quản lý hóa đơn");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Thông tin chung:");

        jLabel2.setText("Mã hóa đơn");

        txtMaHoaDon.setEditable(false);

        jLabel3.setText("Mã nhân viên");

        txtMANhanVien.setEditable(false);

        jLabel4.setText("Tên nhân viên");

        txtTenNV.setEditable(false);

        jLabel5.setText("Thời gian");

        txtThoiGian.setEditable(false);

        jLabel6.setText("Mã khách hàng");

        cbxMaKH.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxMaKHItemStateChanged(evt);
            }
        });

        jLabel7.setText("Tên khách hàng");

        txtTenKH.setEditable(false);

        jLabel8.setText("HTTT");

        cbxHTTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Thẻ tín dụng" }));

        jLabel9.setText("Hình thức mua");

        cbxHinhThucMua.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Trực tiếp", "Online" }));

        jLabel10.setText("Khách trả");

        txtKhachTra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtKhachTraMouseExited(evt);
            }
        });

        jLabel12.setText("Tổng tiền");

        txtTongTien.setEditable(false);

        jLabel13.setText("Trạng thái HD");

        cbxTrangThaiHD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đã thanh toán", "Chưa thanh toán" }));

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Mã nhân viên", "Mã khách hàng", "Thời gian GD", "Hình thức thanh toán", "Hình thức mua", "Khách trả", "Tổng tiền", "Trạng thái HD"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Thông tin sản phẩm:");

        jLabel15.setText("Mã sản phẩm");

        cbxMASP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxMASPItemStateChanged(evt);
            }
        });

        jLabel16.setText("Tên sản phẩm");

        txtTenSP.setEditable(false);

        jLabel17.setText("Số lượng");

        txtSoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoLuongKeyReleased(evt);
            }
        });

        jLabel18.setText("Đơn giá");

        txtDonGia.setEditable(false);

        jLabel19.setText("Tiền công");

        txtTienCong.setEditable(false);

        jLabel20.setText("Giảm giá");

        jLabel21.setText("Thành tiền");

        txtThanhTien.setEditable(false);

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

        buttonGroup1.add(rdoTienMat);
        rdoTienMat.setText("Tiền mặt");
        rdoTienMat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoTienMatMouseClicked(evt);
            }
        });

        buttonGroup1.add(rdoPhanTram);
        rdoPhanTram.setText("Phần trăm %");
        rdoPhanTram.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoPhanTramMouseClicked(evt);
            }
        });

        txtGiamGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGiamGiaKeyReleased(evt);
            }
        });

        jLabel22.setText("Bằng chữ:");

        lblBangChu.setText("Năm trăm nghìn đồng");

        jLabel23.setText("Trả lại khách");

        txtTraLaiKhach.setEditable(false);

        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnCapNhatCTHD.setText("Cập nhật");
        btnCapNhatCTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatCTHDActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel19)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtTienCong, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel15)
                                                .addGap(18, 18, 18)
                                                .addComponent(cbxMASP, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel17)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel16)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(48, 48, 48)
                                                .addComponent(jLabel18)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel20)
                                                .addGap(13, 13, 13)
                                                .addComponent(rdoTienMat)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(rdoPhanTram)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel21)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(btnThem)
                                                        .addGap(31, 31, 31)
                                                        .addComponent(btnCapNhatCTHD)
                                                        .addGap(30, 30, 30)
                                                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                                .addGap(68, 68, 68))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(12, 12, 12)
                                                .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(56, 56, 56)
                                                .addComponent(jLabel8)
                                                .addGap(25, 25, 25)
                                                .addComponent(cbxHTTT, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel13)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(cbxTrangThaiHD, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel5)
                                                                .addGap(11, 11, 11))
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                    .addComponent(jLabel23)
                                                                    .addComponent(jLabel10))
                                                                .addGap(18, 18, 18)))
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(txtKhachTra)
                                                            .addComponent(txtThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(txtTraLaiKhach, javax.swing.GroupLayout.Alignment.TRAILING))))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(18, 18, 18)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(jLabel9)
                                                            .addComponent(jLabel12))
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addGap(21, 21, 21)
                                                                .addComponent(cbxHinhThucMua, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(41, 41, 41)
                                                        .addComponent(jButton1)
                                                        .addGap(46, 46, 46)
                                                        .addComponent(btnCapNhat))))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(50, 50, 50)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(24, 24, 24)
                                                .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(16, 16, 16)
                                                .addComponent(jLabel6)
                                                .addGap(18, 18, 18)
                                                .addComponent(cbxMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addGap(16, 16, 16)
                                                .addComponent(txtMANhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(16, 16, 16)
                                                .addComponent(jLabel7)
                                                .addGap(14, 14, 14)
                                                .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(64, 64, 64)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 857, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(660, 660, 660)
                                .addComponent(jLabel11))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(286, 286, 286)
                                .addComponent(jLabel22)
                                .addGap(18, 18, 18)
                                .addComponent(lblBangChu)
                                .addGap(67, 67, 67)
                                .addComponent(btnNew)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel11)
                .addGap(1, 1, 1)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel6)))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtMANhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(cbxHTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(cbxHinhThucMua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(lblBangChu)
                            .addComponent(txtTraLaiKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(cbxTrangThaiHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(btnCapNhat)
                            .addComponent(btnNew))))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(cbxMASP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(3, 3, 3)))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addComponent(txtTienCong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(rdoTienMat)
                            .addComponent(rdoPhanTram)
                            .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnCapNhatCTHD)
                    .addComponent(btnXoa))
                .addContainerGap(70, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbxMaKHItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxMaKHItemStateChanged
        fillNameCustomer();
    }//GEN-LAST:event_cbxMaKHItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            insert();
            fillTable();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        try {
            display();
            txtKhachTra.setEditable(false);
            fillTableCTHD();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

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
        txtTraLaiKhach.setText(tralai + "");
    }//GEN-LAST:event_txtKhachTraMouseExited

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        try {
            HoaDon hd = getForm();
            hdd.update(hd);
            fillTable();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        txtKhachTra.setEditable(true);
        txtKhachTra.setText("");
        txtThoiGian.setText("" + java.time.LocalDate.now());
        cbxHTTT.setSelectedIndex(0);
        cbxHinhThucMua.setSelectedIndex(0);
        cbxMaKH.setSelectedIndex(0);
        cbxTrangThaiHD.setSelectedIndex(0);
        try {
            String maHD = hdd.maSP_TuSinh();
            txtMaHoaDon.setText(maHD);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnNewActionPerformed

    private void cbxMASPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxMASPItemStateChanged
        fillNameProduct();
    }//GEN-LAST:event_cbxMASPItemStateChanged

    private void rdoTienMatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoTienMatMouseClicked
        if (rdoTienMat.isSelected()) {
            txtGiamGia.setEnabled(true);
            txtGiamGia.setEditable(true);
        }
    }//GEN-LAST:event_rdoTienMatMouseClicked

    private void rdoPhanTramMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoPhanTramMouseClicked
        if (rdoPhanTram.isSelected()) {
            txtGiamGia.setEnabled(true);
            txtGiamGia.setEditable(true);
        }
    }//GEN-LAST:event_rdoPhanTramMouseClicked

    private void txtGiamGiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiamGiaKeyReleased
        thanhTien();
    }//GEN-LAST:event_txtGiamGiaKeyReleased

    private void txtSoLuongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongKeyReleased
        try {
            int sl = Integer.parseInt(txtSoLuong.getText());
            if (sl <= 0) {
                Msgbox.alert(this, "Số lượng là số nguyên dương");
                txtSoLuong.setText("");
                return;
            }
        } catch (Exception e) {
            Msgbox.alert(this, "Số lượng là số nguyên dương");
            txtSoLuong.setText("");
            return;
        }
    }//GEN-LAST:event_txtSoLuongKeyReleased

    private void tblHoaDonChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChiTietMouseClicked
         int row = tblHoaDonChiTiet.getSelectedRow();
         ChiTietHD cthd= listCTHD.get(row);
         setForm(cthd);
    }//GEN-LAST:event_tblHoaDonChiTietMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try {
            HoaDon hd = hdd.selectById(txtMaHoaDon.getText());
            if (hd == null) {
                Msgbox.alert(this, "Mã hóa đơn chưa tồn tại");
                return;
            }
            insertHDCT();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatCTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatCTHDActionPerformed
        updateHDCT();
    }//GEN-LAST:event_btnCapNhatCTHDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnCapNhatCTHD;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbxHTTT;
    private javax.swing.JComboBox<String> cbxHinhThucMua;
    private javax.swing.JComboBox<String> cbxMASP;
    private javax.swing.JComboBox<String> cbxMaKH;
    private javax.swing.JComboBox<String> cbxTrangThaiHD;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBangChu;
    private javax.swing.JRadioButton rdoPhanTram;
    private javax.swing.JRadioButton rdoTienMat;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDonChiTiet;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtKhachTra;
    private javax.swing.JTextField txtMANhanVien;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtThoiGian;
    private javax.swing.JTextField txtTienCong;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txtTraLaiKhach;
    // End of variables declaration//GEN-END:variables
}
