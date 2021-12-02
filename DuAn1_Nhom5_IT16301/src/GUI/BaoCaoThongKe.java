/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import DAO.ChiTietHD_DAO;
import DAO.DanhMuc_DAO;
import DAO.SanPham_DAO;
import Entity.DanhMuc;
import Entity.SanPham;
import Helper.jdbcHelper;
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Tiến Mạnh
 */
public class BaoCaoThongKe extends javax.swing.JPanel {
     SanPham_DAO SPD =new SanPham_DAO();
    List<SanPham> listSP =new ArrayList<>();
    List<DanhMuc> listDM =new ArrayList<>();
    DanhMuc_DAO DDM =new DanhMuc_DAO();
    ChiTietHD_DAO CTHDD =new ChiTietHD_DAO();
    public BaoCaoThongKe() throws SQLException {
        initComponents();
        init();
        setBackground(new Color(240, 240, 240));
    }
     private void showBieuDoCot() throws SQLException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
         SimpleDateFormat sdf =new SimpleDateFormat("MM/dd/yyyy");
         String ngayBatDau=sdf.format(jDCNgayBatDau.getDate());
          String ngayKetThuc=sdf.format(jDCNgayKetThuc.getDate());
        // System.out.println(""+ngayBatDau+" ccc"+ngayKetThuc);
        listSP = SPD.top5SP(""+ngayBatDau, ""+ngayKetThuc);

        for (int i = 0; i < listSP.size(); i++) {
            SanPham sp = listSP.get(i);
            String tenSP = sp.getTenSP();
            int soLuong =sp.getSoLuong();
            dataset.setValue(soLuong, "Số sản phẩm", tenSP);
        }
        JFreeChart chart = ChartFactory.createBarChart("Biểu đồ số top 5 sản phẩm bán chạy", "Tên sản phẩm", "Số sản phẩm",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        //tạo biểu đồ 
        CategoryPlot categoryPlot = chart.getCategoryPlot();
         
        categoryPlot.setBackgroundPaint(Color.white);

        //create render object to change the moficy the line properties like color
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        Color clr3 = new Color(204, 0, 51);
        renderer.setSeriesPaint(0, clr3);

        //tạo panel để hiển thị biểu đồ
        ChartPanel barchartPanel = new ChartPanel(chart);
        panelBDCot.removeAll();
        panelBDCot.add(barchartPanel, BorderLayout.CENTER);
        panelBDCot.validate();

    }
     public void showPieChart() throws SQLException {

        //Tạo dữ liệu
        DefaultPieDataset barDataset = new DefaultPieDataset();
        listDM =DDM.selectAll();
        for (int i = 0; i < listDM.size(); i++) {
            DanhMuc danhMuc = listDM.get(i);
            String tenDM = danhMuc.getTenDm();
            String maDM = danhMuc.getMaDM();
            SimpleDateFormat sdf =new SimpleDateFormat("MM/dd/yyyy");
         String ngayBatDau=sdf.format(jDCNgayBatDau.getDate());
          String ngayKetThuc=sdf.format(jDCNgayKetThuc.getDate());
            barDataset.setValue(tenDM,CTHDD.duLieu(maDM, ngayBatDau, ngayKetThuc));
        }

        //tạo biểu đồ
        JFreeChart piechart = ChartFactory.createPieChart("Thị phần sản phẩm theo danh mục", barDataset, false, true, false);//explain

        PiePlot piePlot = (PiePlot) piechart.getPlot();

       
        piePlot.setBackgroundPaint(Color.white);

        //tạo panel để hiển thị biểu đồ
        ChartPanel barChartPanel = new ChartPanel(piechart);
        panelBarChart.removeAll();
        panelBarChart.add(barChartPanel, BorderLayout.CENTER);
        panelBarChart.validate();
    }
     private void hienThiTS() throws SQLException{
         SimpleDateFormat sdf =new SimpleDateFormat("MM/dd/yyyy");
         String ngayBatDau=sdf.format(jDCNgayBatDau.getDate());
          String ngayKetThuc=sdf.format(jDCNgayKetThuc.getDate());
         // System.out.println(""+ngayBatDau);
        double[] result= thongSo(ngayBatDau, ngayKetThuc);
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        lblDoanhTHu.setText(""+df.format(result[0]));
        lblSoHoaDon.setText(""+df.format(result[1]));
        lblGiamGia.setText(""+df.format(result[2]));

        
        lblDoanhTHuBan.setText(""+df.format(doanhThuBan(ngayBatDau, ngayKetThuc)));
       lblTienMua.setText(""+df.format(tienMua(ngayBatDau, ngayKetThuc)));
     }
     private double doanhThuBan(String ngayBatDau,String ngayKetThuc) throws SQLException{
         String query ="select SUM(TONGTIEN) from HOADON HD\n" +
"where HINHTHUCMUA =0 AND HD.NGAYGD BETWEEN '"+ngayBatDau+"' AND '"+ngayKetThuc+" 23:59:59.999'";
         ResultSet rs =jdbcHelper.query(query);
         double doanhThuBan =0;
         
        while(rs.next()){
           doanhThuBan = rs.getDouble(1);
        }
        return doanhThuBan;
     }
          private double tienMua(String ngayBatDau,String ngayKetThuc) throws SQLException{
         String query ="select SUM(TONGTIEN) from HOADON HD\n" +
"where HINHTHUCMUA =1 AND HD.NGAYGD BETWEEN '"+ngayBatDau+"' AND '"+ngayKetThuc+" 23:59:59.999'";
              System.out.println(""+query);
         ResultSet rs =jdbcHelper.query(query);
         double tienMua =0;
         
        while(rs.next()){
           tienMua = rs.getDouble(1);
        }
        return tienMua;
     }
              private double[] thongSo(String ngayBatDau,String ngayKetThuc) throws SQLException { //lấy số lượng theo danh mục
        String query ="exec SP_BCTK3 @NGAYBATDAU = '"+ngayBatDau+"',@NGAYKETTHUC ='"+ngayKetThuc+" 23:59:59.999'";
             //System.out.println(""+query);
        double doanhThu = 0 ,soHoaDon = 0, giamGia = 0;
        ResultSet rs =jdbcHelper.query(query);
        while(rs.next()){
            doanhThu =rs.getDouble(1);
            soHoaDon =rs.getDouble(2);
            giamGia =rs.getDouble(3);
        }
        
        return new double[]{doanhThu,soHoaDon,giamGia};
    }
  

    private void init() throws SQLException{

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDoanhTHu = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblMau1 = new javax.swing.JLabel();
        lblDoanhTHuBan = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblMau2 = new javax.swing.JLabel();
        lblTienMua = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblMau3 = new javax.swing.JLabel();
        lblSoHoaDon = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblMau4 = new javax.swing.JLabel();
        lblGiamGia = new javax.swing.JLabel();
        lbl = new javax.swing.JLabel();
        lblMau5 = new javax.swing.JLabel();
        panelBDCot = new javax.swing.JPanel();
        jDCNgayKetThuc = new com.toedter.calendar.JDateChooser();
        jDCNgayBatDau = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        panelBarChart = new javax.swing.JPanel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDoanhTHu.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblDoanhTHu.setForeground(new java.awt.Color(255, 255, 255));
        lblDoanhTHu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDoanhTHu.setText("0");
        add(lblDoanhTHu, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 180, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Doanh Thu ");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, -1, -1));

        lblMau1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/AnhBCTK1.png"))); // NOI18N
        lblMau1.setText("jLabel3");
        add(lblMau1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 190, 110));

        lblDoanhTHuBan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblDoanhTHuBan.setForeground(new java.awt.Color(255, 255, 255));
        lblDoanhTHuBan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDoanhTHuBan.setText("0");
        add(lblDoanhTHuBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, 180, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Doanh Thu Bán ");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, -1, -1));

        lblMau2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/AnhBCTK2.png"))); // NOI18N
        lblMau2.setText("jLabel3");
        add(lblMau2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 190, 110));

        lblTienMua.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTienMua.setForeground(new java.awt.Color(255, 255, 255));
        lblTienMua.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTienMua.setText("0");
        add(lblTienMua, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 80, 180, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tiền Mua ");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, -1, -1));

        lblMau3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/AnhBCTK3.png"))); // NOI18N
        lblMau3.setText("jLabel3");
        add(lblMau3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, 190, 110));

        lblSoHoaDon.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblSoHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        lblSoHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSoHoaDon.setText("0");
        add(lblSoHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, 180, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Số hóa đơn ");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 220, -1, -1));

        lblMau4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/AnhBCTK4.png"))); // NOI18N
        lblMau4.setText("jLabel3");
        add(lblMau4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 190, 110));

        lblGiamGia.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblGiamGia.setForeground(new java.awt.Color(255, 255, 255));
        lblGiamGia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGiamGia.setText("0");
        add(lblGiamGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 260, 180, -1));

        lbl.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        lbl.setForeground(new java.awt.Color(255, 255, 255));
        lbl.setText("Giảm giá ");
        add(lbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 220, -1, -1));

        lblMau5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/AnhBCTK5.png"))); // NOI18N
        lblMau5.setText("jLabel3");
        add(lblMau5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 200, 190, 110));

        panelBDCot.setBackground(new java.awt.Color(255, 255, 255));
        panelBDCot.setLayout(new java.awt.BorderLayout());
        add(panelBDCot, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, 560, 309));
        add(jDCNgayKetThuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 280, 150, -1));
        add(jDCNgayBatDau, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 280, 150, -1));

        jButton1.setText("Xem");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 320, 70, -1));

        panelBarChart.setBackground(new java.awt.Color(255, 255, 255));
        panelBarChart.setLayout(new java.awt.BorderLayout());
        add(panelBarChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 430, 410, 309));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         try {
             showBieuDoCot();
             showPieChart();
             hienThiTS();
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDCNgayBatDau;
    private com.toedter.calendar.JDateChooser jDCNgayKetThuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lbl;
    private javax.swing.JLabel lblDoanhTHu;
    private javax.swing.JLabel lblDoanhTHuBan;
    private javax.swing.JLabel lblGiamGia;
    private javax.swing.JLabel lblMau1;
    private javax.swing.JLabel lblMau2;
    private javax.swing.JLabel lblMau3;
    private javax.swing.JLabel lblMau4;
    private javax.swing.JLabel lblMau5;
    private javax.swing.JLabel lblSoHoaDon;
    private javax.swing.JLabel lblTienMua;
    private javax.swing.JPanel panelBDCot;
    private javax.swing.JPanel panelBarChart;
    // End of variables declaration//GEN-END:variables
}
