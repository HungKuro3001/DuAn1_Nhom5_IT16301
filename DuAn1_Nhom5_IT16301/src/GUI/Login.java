/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.NhanVien_DAO;
import Entity.NhanVien;
import Form.TestMenu.Main;
import Utils.Auth;
import Utils.Msgbox;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Tiến Mạnh
 */
public class Login extends javax.swing.JFrame {

    private NhanVien_DAO dao = new NhanVien_DAO();
    /**
     * Creates new form Login
     */
    int a = 1, b = 1, c = 1;

    public Login() {
        initComponents();
        new Chao(this, true).setVisible(true);
        setBackground(new Color(0, 0, 0, 0));    // (1)
        jPanel1.setBackground(new Color(0, 0, 0, 0));//(2)   (1) VÀ (2) CÙNG NHAU ĐỂ tắt nền
        setLocationRelativeTo(null);
        txtUserName.setBackground(new Color(0, 0, 0, 0));
        txtPassword.setBackground(new Color(0, 0, 0, 0));
        txtPassword.setText("123");
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        txtPassword = new javax.swing.JPasswordField();
        txtUserName = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        lblDangNhap = new javax.swing.JLabel();
        chkCheck = new javax.swing.JCheckBox();
        lblDieuKhoan = new javax.swing.JLabel();
        lblminimizeSize = new javax.swing.JLabel();
        lblTB3 = new javax.swing.JLabel();
        lblExitt = new javax.swing.JLabel();
        lblTB1 = new javax.swing.JLabel();
        lblTB2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblRight = new javax.swing.JLabel();
        lblLeft = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Password:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 300, 90, 40));

        jSeparator2.setBackground(new java.awt.Color(51, 51, 51));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 280, 490, 20));

        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(153, 153, 153));
        txtPassword.setText("jPasswordField1");
        txtPassword.setBorder(null);
        txtPassword.setOpaque(false);
        txtPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPasswordMouseClicked(evt);
            }
        });
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 340, 490, 40));

        txtUserName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtUserName.setForeground(new java.awt.Color(153, 153, 153));
        txtUserName.setText("hoang02");
        txtUserName.setBorder(null);
        txtUserName.setOpaque(false);
        txtUserName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUserNameFocusGained(evt);
            }
        });
        txtUserName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtUserNameMouseClicked(evt);
            }
        });
        txtUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserNameActionPerformed(evt);
            }
        });
        jPanel1.add(txtUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 240, 490, 40));

        jSeparator3.setBackground(new java.awt.Color(51, 51, 51));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 380, 490, 10));

        lblDangNhap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDangNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/btnLoginSwitch.png"))); // NOI18N
        lblDangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDangNhapMouseClicked(evt);
            }
        });
        jPanel1.add(lblDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 580, -1, -1));

        chkCheck.setBackground(new java.awt.Color(102, 51, 255));
        chkCheck.setForeground(new java.awt.Color(51, 51, 255));
        chkCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCheckActionPerformed(evt);
            }
        });
        jPanel1.add(chkCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 520, -1, -1));

        lblDieuKhoan.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblDieuKhoan.setForeground(new java.awt.Color(255, 255, 255));
        lblDieuKhoan.setText("ShowPass");
        jPanel1.add(lblDieuKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 520, 80, -1));

        lblminimizeSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblminimizeSizeMouseClicked(evt);
            }
        });
        jPanel1.add(lblminimizeSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, -1, -1));

        lblTB3.setForeground(new java.awt.Color(255, 255, 0));
        jPanel1.add(lblTB3, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 520, -1, -1));

        lblExitt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/anhExit.png"))); // NOI18N
        lblExitt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExittMouseClicked(evt);
            }
        });
        jPanel1.add(lblExitt, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 600, -1, -1));

        lblTB1.setForeground(new java.awt.Color(255, 204, 0));
        jPanel1.add(lblTB1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 290, -1, -1));

        lblTB2.setForeground(new java.awt.Color(255, 204, 0));
        jPanel1.add(lblTB2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 390, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Username:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 190, 90, 40));

        lblRight.setForeground(new java.awt.Color(255, 255, 255));
        lblRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/rightPanel_1.png"))); // NOI18N
        jPanel1.add(lblRight, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 680, 760));

        lblLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/BaoCao.png"))); // NOI18N
        jPanel1.add(lblLeft, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 50, 620, 660));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 12, 1220, 790));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPasswordMouseClicked
        if (b == 1) {
            txtPassword.setText("");
            b = 2;
        }
        txtPassword.setForeground(Color.white);
    }//GEN-LAST:event_txtPasswordMouseClicked

    private void txtUserNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUserNameFocusGained

    }//GEN-LAST:event_txtUserNameFocusGained

    private void txtUserNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUserNameMouseClicked

        if (a == 1) {
            txtUserName.setText("");
            a = 2;
        }
        txtUserName.setForeground(Color.white);

    }//GEN-LAST:event_txtUserNameMouseClicked

    private void txtUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserNameActionPerformed

    }//GEN-LAST:event_txtUserNameActionPerformed

    private void lblDangNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDangNhapMouseClicked
        dangNhap();
    }//GEN-LAST:event_lblDangNhapMouseClicked

    private void chkCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCheckActionPerformed
        if (chkCheck.isSelected()) {
            txtPassword.setEchoChar((char) 0);
        } else {
            txtPassword.setEchoChar('*');
        }
    }//GEN-LAST:event_chkCheckActionPerformed

    private void lblminimizeSizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblminimizeSizeMouseClicked
        setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_lblminimizeSizeMouseClicked

    private void lblExittMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExittMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblExittMouseClicked

    /**
     * @param args the command line arguments
     */
    public void dangNhap() {

        String userName = txtUserName.getText();
        String passWord = txtPassword.getText();
        NhanVien nv = dao.selectById(userName);
        if (nv == null) {
            Msgbox.alert(this, "Sai tài khoản hoặc mật khẩu");
        } else if (!nv.getPassWord().equals(passWord)) {
            Msgbox.alert(this, "Sai tài khoản hoặc mật khẩu");
        } else if (nv.isTrangThai() == false) {
            Msgbox.alert(this, "Tài khoản đã bị khóa");
        } else {
            Msgbox.alert(this, "Đăng nhập thành công");
            Auth.user=nv;
           this.dispose();
            new Main().setVisible(true);
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkCheck;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblDangNhap;
    private javax.swing.JLabel lblDieuKhoan;
    private javax.swing.JLabel lblExitt;
    private javax.swing.JLabel lblLeft;
    private javax.swing.JLabel lblRight;
    private javax.swing.JLabel lblTB1;
    private javax.swing.JLabel lblTB2;
    private javax.swing.JLabel lblTB3;
    private javax.swing.JLabel lblminimizeSize;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
