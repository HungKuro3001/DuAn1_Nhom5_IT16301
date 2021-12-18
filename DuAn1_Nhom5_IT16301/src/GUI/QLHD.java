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
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
public class QLHD extends javax.swing.JPanel {

    /**
     * Creates new form QLHD
     */
    String diaChiKH = "";
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
        String maHD = hdd.maHD_TuSinh();
        txtMaHoaDon.setText(maHD);
//        txtTongTien.setText("5");
        txtGiamGia.setText("0");

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
        rdoTienMat.setSelected(true);
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
        txtThoiGian.setText("" + hd.getNgayGD());
        cbxHTTT.setSelectedItem(hd.isHinhThucThanhToan());
        if (hd.isHinhThucThanhToan() == true) {
            cbxHTTT.setSelectedItem("Tiền mặt");
        } else {
            cbxHTTT.setSelectedItem("Thẻ tín dụng");
        }
        if (hd.isHinhthucmua() == true) {
            cbxHinhThucMua.setSelectedItem("Mua");
        } else {
            cbxHinhThucMua.setSelectedItem("Bán");
        }
                       
               DecimalFormat df = new DecimalFormat("#.##");

        txtKhachTra.setText("" + df.format(hd.getKhachTra()));
        txtTongTien.setText("" + df.format(hd.getTongTien()));
        cbxTrangThaiHD.setSelectedItem(hd.getTrangThaiHD());
        String chu = numberToString(Double.parseDouble(txtTongTien.getText()));
        lblBangChu.setText(chu);

    }

    private void fillNameCustomer() {
        for (KhachHang khachHang : listKH) {
            if (cbxMaKH.getSelectedItem().toString().equals(khachHang.getMaKh())) {
                txtTenKH.setText(khachHang.getHoTen());
                diaChiKH = khachHang.getDiaChi();
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
        fillTable();
        Msgbox.alert(this, "Thêm thành công");
    }

    private void fillTable() {
        listHD = hdd.selectAll();
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        for (HoaDon hd : listHD) {
            model.addRow(new Object[]{hd.getMaHD(), hd.getMaNV(), hd.getMaKH(),
                hd.getNgayGD(), hd.isHinhThucThanhToan() == true ? "Tiền mặt"
                : "Thẻ tín dụng", hd.isHinhthucmua() == true ? "Mua" : "Bán",
                df.format(hd.getKhachTra()), df.format(hd.getTongTien()),
                hd.getTrangThaiHD()});
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

    private void setForm(ChiTietHD cthd) {
        cbxMASP.setSelectedItem(cthd.getMaSp());

        txtTienCong.setText(cthd.getTienCong() + "");
        txtDonGia.setText(cthd.getDonGia() + "");
        txtSoLuong.setText(cthd.getSoLuong() + "");
        txtGiamGia.setText(new BigDecimal(cthd.getGiamGia()) + "");

        txtThanhTien.setText(new BigDecimal(cthd.getThanhTien()) + "");

    }

    private void insertHDCT() {
        for (ChiTietHD chiTietHD : listCTHD) {
            if (chiTietHD.getMaSp().equals(cbxMASP.getSelectedItem().toString())) {
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

    private void updateHDCT() {
        int row = tblHoaDonChiTiet.getSelectedRow();
        if (row < 0) {
            Msgbox.alert(this, "chọn chi tiết hóa đơn để cập nhật");
            return;
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
        cthdd.update(ctHD);
        fillTableCTHD();
        Msgbox.alert(this, "Cập nhật thành công");
    }

    private void fillTableCTHD() {
        listCTHD = cthdd.selectByMAHD(txtMaHoaDon.getText());
        DefaultTableModel model = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        model.setRowCount(0);
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        for (ChiTietHD CTHD : listCTHD) {
            Object[] row = new Object[]{
                CTHD.getMaHD(), CTHD.getMaSp(), CTHD.getTienCong(), df.format(CTHD.getDonGia()),
                df.format(CTHD.getGiamGia()), CTHD.getSoLuong(), df.format(CTHD.getThanhTien())
            };
            model.addRow(row);
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
        cell.setCellValue("Bán cho ông (bà): " + txtTenKH.getText());
        cell.setCellStyle(cellStyle3);
        //dòng 7
        row = sheet.createRow(7);
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Địa chỉ: " + diaChiKH);
        cell.setCellStyle(cellStyle3);

        //dòng 8
        row = sheet.createRow(8);
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Nhân viên: " + txtTenNV.getText());
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
        cell.setCellValue("Thưa quý khách chúng tôi có ưu đãi cho khách hàng mua");
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
        cell.setCellValue("Tây Sơn " + txtThoiGian.getText());
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

            document.add(new Paragraph("Công ty TNHH Vàng Bạc                              "
                    + "                                       Giấy đảm bảo vàng\n"
                    + "       Toản Huyền                                               "
                    + "                                       Uy tín quý hơn vàng\n"
                    + "Đ/c:Khu Minh Khanh -Xã Minh Khai- Huyện tân sơn - Tỉnh "
                + "Phú Thọ   ĐT:0978154115-0986334292\n"
                    + "        Chuyên mua nữ trang vàng 9999-Vàng tây các loại\n\n"
                    + "Bán cho ông (bà): " + tenKH + "  Địa chỉ: " + diaChi + "\n"
                    + "Tổng tiền: " + txtTongTien.getText() + " vnđ\n"
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
                            + "                           Đại diện công ty", font1));

            document.close();
        }
    }

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
        btnThemHD = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnCapNhatCTHD = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 204, 204));
        jLabel11.setText("Quản lý hóa đơn");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Thông tin chung:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 53, -1, -1));

        jLabel2.setText("Mã hóa đơn");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        txtMaHoaDon.setEditable(false);
        add(txtMaHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 124, -1));

        jLabel3.setText("Mã nhân viên");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        txtMANhanVien.setEditable(false);
        add(txtMANhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 124, -1));

        jLabel4.setText("Tên nhân viên");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 193, -1, -1));

        txtTenNV.setEditable(false);
        add(txtTenNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 190, 124, -1));

        jLabel5.setText("Thời gian");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 228, -1, -1));

        txtThoiGian.setEditable(false);
        add(txtThoiGian, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 225, 124, -1));

        jLabel6.setText("Mã khách hàng");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, -1, -1));

        cbxMaKH.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxMaKHItemStateChanged(evt);
            }
        });
        add(cbxMaKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, 129, -1));

        jLabel7.setText("Tên khách hàng");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, -1, -1));

        txtTenKH.setEditable(false);
        add(txtTenKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 140, 125, -1));

        jLabel8.setText("HTTT");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 190, -1, -1));

        cbxHTTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Thẻ tín dụng" }));
        add(cbxHTTT, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 190, 125, -1));

        jLabel9.setText("HT mua bán");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 225, -1, -1));

        cbxHinhThucMua.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mua", "Bán" }));
        add(cbxHinhThucMua, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 225, 125, -1));

        jLabel10.setText("Khách trả");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 280, -1, -1));

        txtKhachTra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtKhachTraMouseExited(evt);
            }
        });
        add(txtKhachTra, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 277, 124, -1));

        jLabel12.setText("Tổng tiền");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(294, 280, -1, -1));

        txtTongTien.setEditable(false);
        add(txtTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(357, 277, 125, -1));

        jLabel13.setText("Trạng thái HD");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 375, -1, -1));

        cbxTrangThaiHD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chưa thanh toán", "Đã thanh toán" }));
        cbxTrangThaiHD.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTrangThaiHDItemStateChanged(evt);
            }
        });
        add(cbxTrangThaiHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, 125, -1));

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Mã nhân viên", "Mã khách hàng", "Thời gian GD", "Hình thức thanh toán", "Hình thức mua bán", "Khách trả", "Tổng tiền", "Trạng thái HD"
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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(562, 90, 848, 263));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Thông tin sản phẩm:");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, -1, -1));

        jLabel15.setText("Mã sản phẩm");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, -1, -1));

        cbxMASP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxMASPItemStateChanged(evt);
            }
        });
        add(cbxMASP, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 470, 129, -1));

        jLabel16.setText("Tên sản phẩm");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 470, -1, -1));

        txtTenSP.setEditable(false);
        add(txtTenSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 470, 124, -1));

        jLabel17.setText("Số lượng");
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 570, -1, -1));

        txtSoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoLuongKeyReleased(evt);
            }
        });
        add(txtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 570, 124, -1));

        jLabel18.setText("Đơn giá");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 520, -1, -1));

        txtDonGia.setEditable(false);
        add(txtDonGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 520, 124, -1));

        jLabel19.setText("Tiền công");
        add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 520, -1, -1));

        txtTienCong.setEditable(false);
        add(txtTienCong, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 520, 124, -1));

        jLabel20.setText("Giảm giá");
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 620, -1, -1));

        jLabel21.setText("Thành tiền");
        add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 660, -1, -1));

        txtThanhTien.setEditable(false);
        add(txtThanhTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 660, 124, -1));

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

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 470, 848, 243));

        buttonGroup1.add(rdoTienMat);
        rdoTienMat.setText("Tiền mặt");
        add(rdoTienMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 620, -1, -1));

        buttonGroup1.add(rdoPhanTram);
        rdoPhanTram.setText("Phần trăm %");
        add(rdoPhanTram, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 620, -1, -1));

        txtGiamGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGiamGiaKeyReleased(evt);
            }
        });
        add(txtGiamGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 620, 125, -1));

        jLabel22.setText("Bằng chữ:");
        add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(722, 385, -1, -1));

        lblBangChu.setText(" ");
        add(lblBangChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(789, 385, 373, -1));

        jLabel23.setText("Trả lại khách");
        add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 328, -1, -1));

        txtTraLaiKhach.setEditable(false);
        add(txtTraLaiKhach, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 325, 124, -1));

        btnThemHD.setText("Thêm");
        btnThemHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHDActionPerformed(evt);
            }
        });
        add(btnThemHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 371, -1, -1));

        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(345, 371, -1, -1));

        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        add(btnNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 371, -1, -1));

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 710, -1, -1));

        btnCapNhatCTHD.setText("Cập nhật");
        btnCapNhatCTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatCTHDActionPerformed(evt);
            }
        });
        add(btnCapNhatCTHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 710, -1, -1));

        btnExport.setText("Export");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });
        add(btnExport, new org.netbeans.lib.awtextra.AbsoluteConstraints(491, 371, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void cbxMaKHItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxMaKHItemStateChanged
        fillNameCustomer();
    }//GEN-LAST:event_cbxMaKHItemStateChanged

    private void btnThemHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHDActionPerformed
        try {
            insert();

        } catch (ParseException ex) {
            ex.printStackTrace();
        }

    }//GEN-LAST:event_btnThemHDActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        try {
            
            display();
            float khachtra = Float.parseFloat(txtKhachTra.getText());
            float tongtien = Float.parseFloat(txtTongTien.getText());
            float tralai = khachtra - tongtien;
            if (khachtra != 0) {
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                txtTraLaiKhach.setText(df.format(tralai) + "");
            }
            fillTableCTHD();
            btnThemHD.setEnabled(false);
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
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        txtTraLaiKhach.setText(df.format(tralai) + "");
    }//GEN-LAST:event_txtKhachTraMouseExited

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        try {
            HoaDon hd = getForm();
            hdd.update(hd);
            fillTable();
            Msgbox.alert(this, "Cập nhật thành công");
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
        txtTongTien.setText("0");
        txtKhachTra.setText("0");
        btnThemHD.setEnabled(true);
        try {
            String maHD = hdd.maHD_TuSinh();
            txtMaHoaDon.setText(maHD);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnNewActionPerformed

    private void cbxMASPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxMASPItemStateChanged
        fillNameProduct();
        txtSoLuong.setText("");
        txtGiamGia.setText("0");
        txtThanhTien.setText("");
    }//GEN-LAST:event_cbxMASPItemStateChanged

    private void txtGiamGiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiamGiaKeyReleased
        thanhTien();
    }//GEN-LAST:event_txtGiamGiaKeyReleased

    private void txtSoLuongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongKeyReleased
        try {
            int row = tblHoaDonChiTiet.getSelectedRow();
            if (row > 0) {
                ChiTietHD cthd = listCTHD.get(row);
                if (cthd != null) {
                    double dongia = Double.parseDouble(txtDonGia.getText());
                    int sl = Integer.parseInt(txtSoLuong.getText());
                    double TTChưaGiamGia = dongia * sl;
                    double giamGia = Double.parseDouble(txtGiamGia.getText());
                    double thanhTien = TTChưaGiamGia - giamGia;
                    txtThanhTien.setText(thanhTien + "");
                    if (sl <= 0) {
                        Msgbox.alert(this, "Số lượng là số nguyên dương");
                        txtSoLuong.setText("");
                        return;
                    }
                }
            }

            int sl = Integer.parseInt(txtSoLuong.getText());
            if (sl <= 0) {
                Msgbox.alert(this, "Số lượng là số nguyên dương");
                txtSoLuong.setText("");
                return;
            }
            thanhTien();
        } catch (Exception e) {
            Msgbox.alert(this, "Nhập đúng định dạng số");
            txtSoLuong.setText("");

            return;
        }
    }//GEN-LAST:event_txtSoLuongKeyReleased

    private void tblHoaDonChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChiTietMouseClicked
        int row = tblHoaDonChiTiet.getSelectedRow();
        ChiTietHD cthd = listCTHD.get(row);
        setForm(cthd);
    }//GEN-LAST:event_tblHoaDonChiTietMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try {
            HoaDon hd = hdd.selectById(txtMaHoaDon.getText());
            double tongTien = 0;

            if (hd == null) {
                Msgbox.alert(this, "Mã hóa đơn chưa tồn tại");
                return;
            }
            if (hd.getTrangThaiHD().equals("Đã thanh toán")) {
                Msgbox.alert(this, "Mã hóa đơn này đã hoàn tất thanh toán");
                return;
            }
            insertHDCT();
            for (ChiTietHD cthoadon : listCTHD) {
                tongTien += cthoadon.getThanhTien();
            }

            txtTongTien.setText(new BigDecimal(tongTien) + "");
            lblBangChu.setText(numberToString(tongTien));
            HoaDon TTHD = getForm();
            hdd.updatetongTien(TTHD);
            fillTable();

        } catch (Exception e) {
            e.printStackTrace();
        }
        txtGiamGia.setText("0");


    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatCTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatCTHDActionPerformed
        try {
            double tongTien = 0;
            updateHDCT();
            HoaDon hd = hdd.selectById(txtMaHoaDon.getText());
            if (hd.getTrangThaiHD().equals("Đã thanh toán")) {
                Msgbox.alert(this, "Mã hóa đơn này đã hoàn tất thanh toán");
                return;
            }
            for (ChiTietHD cthoadon : listCTHD) {
                tongTien += cthoadon.getThanhTien();
            }
            txtTongTien.setText(new BigDecimal(tongTien) + "");
            HoaDon TTHD = getForm();
            hdd.updatetongTien(TTHD);

            fillTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtGiamGia.setText("0");
    }//GEN-LAST:event_btnCapNhatCTHDActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        if (tblHoaDon.getSelectedRow() < 0) {
            Msgbox.alert(this, "Chọn 1 dòng hóa đơn rồi xuất");
            return;
        }
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

    private void cbxTrangThaiHDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTrangThaiHDItemStateChanged
        checktien();
    }//GEN-LAST:event_cbxTrangThaiHDItemStateChanged
    public void checktien() {
        if (cbxTrangThaiHD.getSelectedItem().toString().equals("Đã thanh toán")) {
            try {
                if (txtKhachTra.getText().isEmpty()) {
                    Msgbox.alert(this, "Không để trống tiền khách trả");
                    return;
                }
                double khtra = Double.parseDouble(txtKhachTra.getText());
                double TT = Double.parseDouble(txtTongTien.getText());

                if (khtra < TT) {
                    Msgbox.alert(this, "Khách trả tối thiểu bằng Tổng tiền");
                    cbxTrangThaiHD.setSelectedItem("Chưa thanh toán");
                    return;

                }

            } catch (Exception e) {
                Msgbox.alert(this, "Tiền khách trả > 0");
                return;
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnCapNhatCTHD;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemHD;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbxHTTT;
    private javax.swing.JComboBox<String> cbxHinhThucMua;
    private javax.swing.JComboBox<String> cbxMASP;
    private javax.swing.JComboBox<String> cbxMaKH;
    private javax.swing.JComboBox<String> cbxTrangThaiHD;
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
