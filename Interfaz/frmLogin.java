package Interfaz;

import Clases.MySql;
import Clases.contrasena;
import java.awt.event.KeyEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import static oracle.jrockit.jfr.events.Bits.byteValue;

public class frmLogin extends javax.swing.JInternalFrame {
    
    frm_Principal p;
javax.swing.JDesktopPane jpd;

    public frmLogin(frm_Principal pr, javax.swing.JDesktopPane jd){ 
        initComponents();
        p = pr;
        jpd=jd;
        txtNcuenta.setBorder(BorderFactory.createCompoundBorder(txtNcuenta.getBorder(), BorderFactory.createEmptyBorder(6, 6, 6, 6)));
        txtContrasena.setBorder(BorderFactory.createCompoundBorder(txtContrasena.getBorder(), BorderFactory.createEmptyBorder(6, 6, 6, 6)));
        
    }

     MySql sql = new MySql();
    String contralocal;
    String algoritmo = "MD5";
    double cuenta;
    String contra;
    
public static String bytesToHex(byte[] in) {
    final StringBuilder builder = new StringBuilder();
    for(byte b : in) {
        builder.append(String.format("%02x", b));
    }
    return builder.toString();
}

public static byte[] hexStringToByteArray(String s) {
    int len = s.length();
    byte[] data = new byte[len / 2];
    for (int i = 0; i < len; i += 2) {
        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                             + Character.digit(s.charAt(i+1), 16));
    }
    return data;
}



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNcuenta = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtContrasena = new javax.swing.JPasswordField();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 223, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 153, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel1.setText("Numero de cuenta:");

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel2.setText("LOGIN");

        txtNcuenta.setBackground(new java.awt.Color(255, 223, 0));
        txtNcuenta.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        txtNcuenta.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtNcuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNcuentaActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel3.setText("Contraseña:");

        jButton1.setBackground(new java.awt.Color(255, 223, 0));
        jButton1.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jButton1.setText("INGRESAR");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(65, 105, 225));
        jButton2.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("CAMBIAR CONTRASEÑA");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtContrasena.setBackground(new java.awt.Color(255, 223, 0));
        txtContrasena.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtContrasena.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtContrasena.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContrasenaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator1)
                            .addComponent(txtNcuenta)
                            .addComponent(jSeparator2)
                            .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 15, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(269, 269, 269)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(252, 252, 252)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtNcuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jButton2)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
  contrasena contrasena = new contrasena();
  String ce= txtNcuenta.getText();
        try {
          cuenta= Double.parseDouble(txtNcuenta.getText());  
        } catch (Exception e) {
            cuenta=0;
        }
   
   
  contralocal= txtContrasena.getText();
  
  
  if(cuenta!=0){
  String sal = sql.obtenerValor("call enviarSalt("+cuenta+")");
        try {
           contra= contrasena.generateHash(contralocal, "MD5", contrasena.hexStringToByteArray(sal));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(frmLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
       
  int valor = Integer.parseInt(sql.obtenerValor("call verLogin("+cuenta+",'"+contra+"')"));
  if(valor == 1){
  
  p.idempleado = sql.valor("select buscar_idempleado(" + cuenta + ")");
  
      try {
           sql.conec();
            sql.rs = sql.s.executeQuery("select tipo_empleado_idtipo_empleado from empleados where idempleados="+p.idempleado+"");
            while (sql.rs.next()) {
             p.tipoempleado =sql.rs.getInt(1); 
            }
            sql.rs.close();
            
            if(p.tipoempleado==1){
            sql.rs = sql.s.executeQuery("select especialidad_idespecialidad from doctores where empleados_idempleados="+p.idempleado+"");
            while (sql.rs.next()) {
               p.especialidad =sql.rs.getInt(1); 
            }}
            sql.conexion.close();
      } catch (Exception e) {
      }
  p.cuenta=ce;
  
  p.validar();
  this.dispose();
  JOptionPane.showMessageDialog(null, "Bienvenido");
  }else {JOptionPane.showMessageDialog(null, "Contraseña o usuario incorrecto");}
  }
  else{JOptionPane.showMessageDialog(null, "Debe ingresar su Numero de cuenta");};
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        frmSolicitudContra nueva = new frmSolicitudContra(p,jpd);
        jpd.add(nueva);
        
        nueva.setVisible(true);
        
       this.hide();
         
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtContrasenaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContrasenaKeyPressed
          if (evt.getKeyCode()==KeyEvent.VK_ENTER){
        jButton1.doClick();
    }//GEN-LAST:event_txtContrasenaKeyPressed
}
    private void txtNcuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNcuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNcuentaActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtNcuenta;
    // End of variables declaration//GEN-END:variables
}
