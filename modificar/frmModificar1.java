/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modificar;

import Clases.MySql;
import Interfaz.frmConsultaOdonto2;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frmModificar1 extends javax.swing.JFrame {

    String me, tabla;
    MySql sql = new MySql();

    public frmModificar1(String titulo, String tex, String eti, String metodo, DefaultTableModel mod, String tab) {
        initComponents();
        this.setTitle(titulo.toUpperCase());
        jLabel1.setText(eti);
        this.jTextField1.setText(tex);
        me = metodo;
        modelo = mod;
        tabla = tab;
    }

    frmConsultaOdonto2 CO;
    public int opc = 0;
    public int idAP = 0;
    public int idP = 0;
    public String AP = "";

    public frmModificar1(frmConsultaOdonto2 o, int opc, int idAP, int idP) {
        initComponents();
        CO = o;
        jLabel1.setText(o.AP);
        this.opc = opc;
        this.idAP = idAP;
        this.idP = idP;
        this.setTitle("ANTECEDENTES PERSONALES");
        this.jTextField1.setText(o.OBS);
    }
    DefaultTableModel modelo;

    boolean guardar = false;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);

        jLabel1.setText("jLabel1");

        jButton1.setText("CANCELAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("GUARDAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(31, 31, 31)
                        .addComponent(jButton1)
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (opc == 0) {
            if (!this.jTextField1.getText().isEmpty()) {
                sql.EjecutarConsultas(me + jTextField1.getText() + "')");
                sql.RegistrosTabla(tabla, modelo);
            }
        } else {
            if (opc == 1) {
                if (!this.jTextField1.getText().equals("")) {
                    sql.EjecutarConsultas("call insertarDAP(" + idP + "," + idAP + ",'" + this.jTextField1.getText() + "')");
                    JOptionPane.showMessageDialog(null, "Guardado con Exito");
                    CO.llenarAP();
                } else {
                    JOptionPane.showMessageDialog(null, "Debe colocar una Observacion");
                }
            }
            if (opc == 2) {
                if (!this.jTextField1.getText().equals("")) {
                    sql.EjecutarConsultas("call updateDAP(" + idP + "," + idAP + ",'" + this.jTextField1.getText() + "')");
                    JOptionPane.showMessageDialog(null, "Modificado con Exito");
                    CO.llenarAP();
                } else {
                    JOptionPane.showMessageDialog(null, "Debe colocar una Observacion");
                }
            }
        }
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
