/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.TestMenu;




import GUI.Chao;
import GUI.Login;
import java.awt.Color;
import java.awt.Panel;

import javax.swing.JOptionPane;


/**
 *
 * @author Tiến Mạnh
 */
public class Main extends javax.swing.JFrame {

   
    public Main() {
        initComponents();
        
        setBackground(new Color(0, 0, 0, 0));
        menu1.initMoving(this);
        panelTransitions1.setBackground(new Color(240,240,240));
        panelTransitions1.display(new GUI.pannelTrangChu());
        menu1.addEventMenu(new EventMenu() {
            @Override
            public void menuIndexChange(int index) {
                
                switch(index){
                    case 0:
                        panelTransitions1.display(new GUI.pannelQLNV());  
                        break;
                    case 1:  
                        panelTransitions1.display(new GUI.pannelQLDMSP());
                        break;
                    case 2:  
                          
                        break;
                    case 3:  
                        panelTransitions1.display(new GUI.QLSP());
                        break;  
                    case 4:
                       
                        break; 
                    case 5:
                  
                        break;
                    case 6:
                        panelTransitions1.display(new GUI.QuanLyChatLieu());
                        break;
                }
 
            }
        });
    }
    public void thoat(){
        this.dispose();
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new Form.TestMenu.PanelBorder();
        menu1 = new Form.TestMenu.Menu();
        panelTransitions1 = new Form.TestMenu.PanelTransitions();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelBorder1.setBackground(new java.awt.Color(242, 242, 242));
        panelBorder1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelBorder1.add(menu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 800));
        panelBorder1.add(panelTransitions1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 1420, 800));

        getContentPane().add(panelBorder1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 1780, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Form.TestMenu.Menu menu1;
    private Form.TestMenu.PanelBorder panelBorder1;
    private Form.TestMenu.PanelTransitions panelTransitions1;
    // End of variables declaration//GEN-END:variables
}
