/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import DAO.HoaDon_DAO;
import DAO.KhachHang_DAO;
import Entity.HoaDon;
import Entity.KhachHang;
import Utils.Auth;
import Utils.Msgbox;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JFileChooser;
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
public class QLHD extends javax.swing.JPanel {

    /**
     * Creates new form QLHD
     */
    KhachHang_DAO khd = new KhachHang_DAO();
    HoaDon_DAO hdd = new HoaDon_DAO();
    List<KhachHang> listKH = new ArrayList<>();
    List<HoaDon> listHD = new ArrayList<>();

    public QLHD() throws SQLException {
        initComponents();
        init();
        setBackground(new Color(240, 240, 240));
        String maHD = hdd.maSP_TuSinh();
        txtMaHoaDon.setText(maHD);

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
        for (KhachHang khachHang : listKH) {
            cbxMaKH.addItem(khachHang.getMaKh());
        }

    }

    private void init() throws SQLException {
        fillTable();
        fillTextField();
        fillCBX();
        fillNameCustomer();
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
        // 
        String bangChu = numberToString(Double.parseDouble(txtTongTien.getText()));
        lblBangChu.setText(bangChu);
    }

    private void fillNameCustomer() {
        for (KhachHang khachHang : listKH) {
            if (cbxMaKH.getSelectedItem().toString().equals(khachHang.getMaKh())) {
                txtTenKH.setText(khachHang.getHoTen());
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
        font.setBold(true);
        font2.setFontHeightInPoints((short) 16); // font size
        font2.setColor(IndexedColors.RED.getIndex()); // text color

//style 2
     CellStyle cellStyle2 = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font2);
        
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
        cell.setCellValue("Uy tín quý hơn vàng");
         cell.setCellStyle(cellStyle2);
        //
        row = sheet.createRow(4);
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Khu Minh Khanh -Xã Minh Khai- Huyện tân sơn - Tỉnh "
                + "Phú Thọ   ĐT:0978154115-0986334292");
        cell.setCellStyle(cellStyle2);
        
        
        
        //
        
             JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jFileChooser.setDialogTitle("Chọn thư mục: ");
     
          int result = jFileChooser.showSaveDialog(null);
       if(result == JFileChooser.APPROVE_OPTION){
           File file =new File(jFileChooser.getSelectedFile()+".xlsx");
           
            FileOutputStream fos =new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
       }
        
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        txtTienCong1 = new javax.swing.JTextField();
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
        jButton2 = new javax.swing.JButton();

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

        jLabel16.setText("Tên sản phẩm");

        txtTenSP.setEditable(false);

        jLabel17.setText("Số lượng");

        jLabel18.setText("Đơn giá");

        txtDonGia.setEditable(false);

        jLabel19.setText("Tiền công");

        txtTienCong.setEditable(false);

        jLabel20.setText("Giảm giá");

        jLabel21.setText("Thành tiền");

        txtTienCong1.setEditable(false);

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
        jScrollPane2.setViewportView(tblHoaDonChiTiet);

        rdoTienMat.setText("Tiền mặt");

        rdoPhanTram.setText("Phần trăm %");

        jLabel22.setText("Bằng chữ:");

        lblBangChu.setText("Năm trăm nghìn đồng");

        jLabel23.setText("Trả lại khách");

        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Export");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(660, 660, 660)
                                .addComponent(jLabel11))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel1)))
                        .addGap(0, 603, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(jLabel20)
                                        .addGap(13, 13, 13)
                                        .addComponent(rdoTienMat)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rdoPhanTram)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                                .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(68, 68, 68))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(65, 65, 65)
                                    .addComponent(jLabel21)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtTienCong1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(228, 228, 228))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(396, 396, 396)
                                    .addComponent(jButton1)
                                    .addGap(67, 67, 67)))
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
                                                        .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))))
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
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(jLabel22)
                                .addGap(18, 18, 18)
                                .addComponent(lblBangChu)
                                .addGap(228, 228, 228)
                                .addComponent(jButton2)
                                .addGap(0, 0, Short.MAX_VALUE)))))
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
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel22)
                                .addComponent(lblBangChu)
                                .addComponent(jButton2))
                            .addComponent(jButton1))))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtTienCong1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            exportExcel();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbxHTTT;
    private javax.swing.JComboBox<String> cbxHinhThucMua;
    private javax.swing.JComboBox<String> cbxMASP;
    private javax.swing.JComboBox<String> cbxMaKH;
    private javax.swing.JComboBox<String> cbxTrangThaiHD;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JTextField txtThoiGian;
    private javax.swing.JTextField txtTienCong;
    private javax.swing.JTextField txtTienCong1;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txtTraLaiKhach;
    // End of variables declaration//GEN-END:variables
}
