package com.simple.app.vistas;
import com.simple.app.dao.UsuarioJpaController;
import com.simple.app.dao.exceptions.NonexistentEntityException;
import com.simple.app.modelo.Usuario;
import com.simple.app.vistas.custom.CustomCellEditor;
import com.simple.app.vistas.custom.TableActionCellRender;
import com.simple.app.vistas.interfaces.TableActionEvent;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ediso
 */
public class InterGestionarUsuario extends javax.swing.JInternalFrame {

    private int idUsuario = 0;

    public InterGestionarUsuario() {
        initComponents();
        this.setSize(new Dimension(900, 500));
        this.setTitle("Gestionar Usuarios");
        //Cargar tabla
        this.CargarTablaUsuarios();       
        this.txtId.setVisible(false);
        //insertar imagen en nuestro JLabel
        ImageIcon wallpaper = new ImageIcon("src/img/fondo3.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(900, 500, WIDTH));
        jLabel_wallpaper.setIcon(icono);
        this.repaint();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_usuarios = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_password = new javax.swing.JTextField();
        txt_apellido = new javax.swing.JTextField();
        txt_telefono = new javax.swing.JTextField();
        txt_usuario = new javax.swing.JTextField();
        jButton_actualizar = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        jLabel_wallpaper = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Administrar Usuarios");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable_usuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable_usuarios.setRowHeight(30);
        jScrollPane1.setViewportView(jTable_usuarios);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 850, 250));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 870, 270));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Nombre:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, -1));

        txt_nombre.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 170, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Password:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 90, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Apellido:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 90, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Telefono:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 90, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Usuario:");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, 90, -1));

        txt_password.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 170, -1));

        txt_apellido.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(txt_apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 170, -1));

        txt_telefono.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(txt_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 170, -1));

        txt_usuario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(txt_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, 170, -1));

        jButton_actualizar.setBackground(new java.awt.Color(51, 204, 0));
        jButton_actualizar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_actualizar.setText("Actualizar");
        jButton_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_actualizarActionPerformed(evt);
            }
        });
        jPanel3.add(jButton_actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, 90, -1));

        txtId.setEditable(false);
        txtId.setEnabled(false);
        txtId.setFocusable(false);
        jPanel3.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 170, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 870, 100));
        getContentPane().add(jLabel_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_actualizarActionPerformed
        
        UsuarioJpaController usuarioJpaController = new UsuarioJpaController();
        Usuario usuario = new Usuario();
        if(txt_nombre.getText().isEmpty() ||
           txt_apellido.getText().isEmpty() || 
           txt_usuario.getText().isEmpty()||
           txt_password.getText().isEmpty() || 
           txt_telefono.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "¡Completa todos los campos!");
            return;
        }
        
        if(txtId.getText().isBlank()){
            JOptionPane.showMessageDialog(null, "Seleccione un registro de la tabla.");
            return;
        }
        usuario.setIdUsuario(Integer.parseInt(txtId.getText()));
        usuario.setNombre(txt_nombre.getText().trim());
        usuario.setApellido(txt_apellido.getText().trim());
        usuario.setUsuario(txt_usuario.getText().trim());
        usuario.setPassword(txt_password.getText().trim());
        usuario.setTelefono(txt_telefono.getText().trim());
        usuario.setEstado(1);
        
        try {
            usuarioJpaController.edit(usuario);
            this.Limpiar();
            this.CargarTablaUsuarios();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "¡Error al Actualizar usuario!");
            Logger.getLogger(InterGestionarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            usuarioJpaController.close();
        }
    }//GEN-LAST:event_jButton_actualizarActionPerformed


    public void eliminarUsuario(int idUsuario){
     
        UsuarioJpaController usuarioJpaController = new UsuarioJpaController();
        try {
            usuarioJpaController.destroy(idUsuario);
        } catch (NonexistentEntityException ex) {
            JOptionPane.showMessageDialog(null, "¡Error al eliminar usuario!");
            Logger.getLogger(InterGestionarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            usuarioJpaController.close();
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_actualizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel_wallpaper;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable_usuarios;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txt_apellido;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_password;
    private javax.swing.JTextField txt_telefono;
    private javax.swing.JTextField txt_usuario;
    // End of variables declaration//GEN-END:variables

    /*
     * *****************************************************
     * metodo para limpiar
     * *****************************************************
     */
    private void Limpiar() {
        txt_nombre.setText("");
        txt_password.setText("");
        txt_apellido.setText("");
        txt_telefono.setText("");
        txt_usuario.setText("");
    }


    /*
     * *****************************************************
     * metodo para mostrar todos los clientes registrados
     * *****************************************************
     */
    private void CargarTablaUsuarios() {
        
        UsuarioJpaController usuarioJpaController = new UsuarioJpaController();
        List<Usuario> usuarios = usuarioJpaController.findUsuarioEntities();
        
        DefaultTableModel model = new DefaultTableModel();
        
        InterGestionarUsuario.jTable_usuarios = new JTable(model);
        InterGestionarUsuario.jScrollPane1.setViewportView(InterGestionarUsuario.jTable_usuarios);

        model.addColumn("N°");//ID
        model.addColumn("nombre");
        model.addColumn("apellido");
        model.addColumn("usuario");
        model.addColumn("password");
        model.addColumn("telefono");
        model.addColumn("estado");
        model.addColumn("Acciones");
        
        usuarios.forEach((Usuario usuario)->{
            Object[] fila = new Object[7];
            fila[0] = usuario.getIdUsuario();
            fila[1] = usuario.getNombre();
            fila[2] = usuario.getApellido();
            fila[3] = usuario.getUsuario();
            fila[4] = usuario.getPassword();
            fila[5] = usuario.getTelefono();
            fila[6] = usuario.getEstado();
            
            model.addRow(fila);
        });       
        usuarioJpaController.close();
        
        
        TableActionEvent tableActionEvent = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                System.out.println(row);
                int idUsuario = (int) jTable_usuarios.getModel().getValueAt(row, 0);
                System.out.println(idUsuario);
                EnviarDatosUsuarioSeleccionado(idUsuario);//metodo
            }

            @Override
            public void onDelete(int row) {
                System.out.println(row);
                
                String[] options = { "Eliminar", "Cancelar"};
                int response = JOptionPane.showOptionDialog(null, "¿Esta seguro que eliminar el usuario seleccionado?", null, 
                                            JOptionPane.DEFAULT_OPTION, 
                                            JOptionPane.WARNING_MESSAGE, 
                                            null, options, null);
                if(response == 0){
                    int idUsuario = (int) jTable_usuarios.getModel().getValueAt(row, 0);
                    eliminarUsuario(idUsuario);
                    CargarTablaUsuarios();
                }
                
            }

            @Override
            public void onView(int row) {
                System.out.println(row);
            }
        };
        jTable_usuarios.setRowHeight(35);
        jTable_usuarios.getColumnModel().getColumn(7).setCellRenderer(new TableActionCellRender());
        jTable_usuarios.getColumnModel().getColumn(7).setCellEditor(new CustomCellEditor(tableActionEvent));
        
        
    }


    /*
     * **************************************************
     * Metodo que envia datos seleccionados
     * **************************************************
     */
    private void EnviarDatosUsuarioSeleccionado(int idUsuario) {
        
        UsuarioJpaController usuarioJpaController = new UsuarioJpaController();
        
        Usuario usuario = usuarioJpaController.findUsuario(idUsuario);
        
        if(usuario == null)
        {
            JOptionPane.showMessageDialog(null, "Ocurrio un error inesperado.");
            return;
        }
        txtId.setText(String.valueOf(usuario.getIdUsuario()));
        txt_nombre.setText(usuario.getNombre());
        txt_apellido.setText(usuario.getApellido());
        txt_usuario.setText(usuario.getUsuario());
        txt_password.setText(usuario.getPassword());
        txt_telefono.setText(usuario.getTelefono());
        usuarioJpaController.close();
  
    }

}
