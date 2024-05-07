/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.simple.app;

import com.simple.app.dao.UsuarioJpaController;
import com.simple.app.modelo.Usuario;
import com.simple.app.vistas.FrmLogin;

/**
 *
 * @author Lynkos
 */
public class SistemasVentasV1 {

    public static void main(String[] args) throws Exception {
        /*Usuario usuario = null;
        usuario = new Usuario();
        usuario.setIdUsuario(null);
        usuario.setNombre("jose");
        usuario.setApellido("valgs");
        usuario.setUsuario("gaga");
        usuario.setPassword("123456");
        usuario.setTelefono("12345678");
        usuario.setEstado(1);
        UsuarioJpaController usuarioJpaController = new UsuarioJpaController();
        usuarioJpaController.create(usuario);
        usuarioJpaController.close();*/
        
        
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
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmLogin().setVisible(true);
            }
        });
        
    }
}
