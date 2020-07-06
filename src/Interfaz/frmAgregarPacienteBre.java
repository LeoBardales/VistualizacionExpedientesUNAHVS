package Interfaz;

import Clases.MySql;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Brenedin Enrique
 */
public class frmAgregarPacienteBre extends javax.swing.JInternalFrame {

    MySql sql=new MySql();
    public frmAgregarPacienteBre() {
        initComponents();
    }
    String cuenta;
    String correo;
    String nombre;
    String apellido;
    String direccion;
    String identidad;
    int carrera;
    String procedencia;
    int sexo;
    int estadoCivil;
    String fechaNacimiento;
    String telefono;
    String telefonoEm;
    String nombreEm;
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnbuscar = new javax.swing.JButton();
        btnnuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtnombre = new javax.swing.JTextField();
        txttelefonoEm = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtapellido = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNidentida = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtprocedencia = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cmbsexo = new javax.swing.JComboBox<>();
        cmbestadocivil = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtnombreEm = new javax.swing.JTextField();
        txtNcuenta = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtfeNacimiento = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtcorreo = new javax.swing.JTextField();
        cmbcarrera = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(65, 105, 225));

        btnbuscar.setBackground(new java.awt.Color(255, 223, 0));
        btnbuscar.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnbuscar.setText("BUSCAR");
        btnbuscar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });

        btnnuevo.setBackground(new java.awt.Color(255, 223, 0));
        btnnuevo.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnnuevo.setText("NUEVO");
        btnnuevo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(255, 223, 0));
        btnModificar.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btncancelar.setBackground(new java.awt.Color(255, 223, 0));
        btncancelar.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btncancelar.setText("CANCELAR");
        btncancelar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        btnguardar.setBackground(new java.awt.Color(255, 223, 0));
        btnguardar.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnguardar.setText("GUARDAR");
        btnguardar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 223, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 83, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        txtnombre.setBackground(new java.awt.Color(255, 223, 0));
        txtnombre.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtnombre.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombreActionPerformed(evt);
            }
        });

        txttelefonoEm.setBackground(new java.awt.Color(255, 223, 0));
        txttelefonoEm.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txttelefonoEm.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txttelefonoEm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttelefonoEmActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel4.setText("Apellido:");

        txtapellido.setBackground(new java.awt.Color(255, 223, 0));
        txtapellido.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtapellido.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtapellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtapellidoActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel6.setText("Dirección:");

        txtdireccion.setBackground(new java.awt.Color(255, 223, 0));
        txtdireccion.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtdireccion.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtdireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdireccionActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel7.setText("Número de Identidad:");

        txtNidentida.setBackground(new java.awt.Color(255, 223, 0));
        txtNidentida.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtNidentida.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtNidentida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNidentidaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel8.setText("Carrera:");

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel9.setText("Procedencia:");

        txtprocedencia.setBackground(new java.awt.Color(255, 223, 0));
        txtprocedencia.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtprocedencia.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtprocedencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtprocedenciaActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel10.setText("Sexo:");

        cmbsexo.setBackground(new java.awt.Color(255, 223, 0));
        cmbsexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hombre", "Mujer" }));
        cmbsexo.setToolTipText("");
        cmbsexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbsexoActionPerformed(evt);
            }
        });
        cmbsexo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbsexoKeyPressed(evt);
            }
        });

        cmbestadocivil.setBackground(new java.awt.Color(255, 223, 0));
        cmbestadocivil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Soltero", "Union Libre", "Casado", "Divorciado", "Viudo", " " }));
        cmbestadocivil.setToolTipText("");
        cmbestadocivil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbestadocivilKeyPressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel11.setText("Estado Civil:");

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel12.setText("Fecha de Nacimiento:");

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel13.setText("Telefono:");

        txttelefono.setBackground(new java.awt.Color(255, 223, 0));
        txttelefono.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txttelefono.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txttelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttelefonoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel3.setText("Nombre:");

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel14.setText("En caso de emergencia llamar a:");

        jLabel15.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel15.setText("Nombre:");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel5.setText("Número de Cuenta:");

        txtnombreEm.setBackground(new java.awt.Color(255, 223, 0));
        txtnombreEm.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtnombreEm.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtnombreEm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombreEmActionPerformed(evt);
            }
        });

        txtNcuenta.setBackground(new java.awt.Color(255, 223, 0));
        txtNcuenta.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtNcuenta.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtNcuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNcuentaActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel16.setText("Telefono:");

        txtfeNacimiento.setBackground(new java.awt.Color(255, 223, 0));
        txtfeNacimiento.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtfeNacimiento.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtfeNacimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfeNacimientoActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel17.setText("Correo institucional:");

        txtcorreo.setBackground(new java.awt.Color(255, 223, 0));
        txtcorreo.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtcorreo.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtcorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcorreoActionPerformed(evt);
            }
        });

        cmbcarrera.setBackground(new java.awt.Color(255, 223, 0));
        cmbcarrera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cmbcarrera.setToolTipText("");
        cmbcarrera.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbcarreraKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtNcuenta))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(txtcorreo)))
                        .addGap(6, 6, 6))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(txttelefono))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtapellido))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(44, 44, 44)
                                .addComponent(cmbcarrera, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNidentida))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtnombre))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(txtdireccion))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(txtprocedencia))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(cmbestadocivil, 0, 437, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(cmbsexo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(txtfeNacimiento))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel16))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(txttelefonoEm, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtnombreEm))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNcuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtapellido, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtNidentida, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtprocedencia, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cmbcarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbsexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbestadocivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfeNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnombreEm, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(txttelefonoEm, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNcuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNcuentaActionPerformed
        txtnombre.requestFocus();
    }//GEN-LAST:event_txtNcuentaActionPerformed

    private void txtnombreEmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreEmActionPerformed
        this.txttelefonoEm.requestFocus();
    }//GEN-LAST:event_txtnombreEmActionPerformed

    private void txttelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttelefonoActionPerformed
        this.txtnombreEm.requestFocus();
    }//GEN-LAST:event_txttelefonoActionPerformed

    private void cmbestadocivilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbestadocivilKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.txttelefono.requestFocus();
        }
    }//GEN-LAST:event_cmbestadocivilKeyPressed

    private void cmbsexoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbsexoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.cmbestadocivil.requestFocus();
            this.cmbestadocivil.showPopup();
        }
    }//GEN-LAST:event_cmbsexoKeyPressed

    private void cmbsexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbsexoActionPerformed

    }//GEN-LAST:event_cmbsexoActionPerformed

    private void txtprocedenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtprocedenciaActionPerformed
        this.cmbsexo.requestFocus();
        this.cmbsexo.showPopup();
    }//GEN-LAST:event_txtprocedenciaActionPerformed

    private void txtNidentidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNidentidaActionPerformed
        this.cmbcarrera.requestFocus();
    }//GEN-LAST:event_txtNidentidaActionPerformed

    private void txtdireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdireccionActionPerformed
        this.txtNidentida.requestFocus();
    }//GEN-LAST:event_txtdireccionActionPerformed

    private void txtapellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtapellidoActionPerformed
        this.txtdireccion.requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txtapellidoActionPerformed

    private void txttelefonoEmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttelefonoEmActionPerformed
        this.btnguardar.requestFocus();
    }//GEN-LAST:event_txttelefonoEmActionPerformed

    private void txtnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreActionPerformed
        this.txtapellido.requestFocus();
    }//GEN-LAST:event_txtnombreActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        cuenta = txtNcuenta.getText();
        correo = txtcorreo.getText();
        nombre = txtnombre.getText();
        apellido = txtapellido.getText();
        direccion = txtdireccion.getText();
        identidad = txtNidentida.getText();
        carrera = cmbcarrera.getSelectedIndex()+1;
        procedencia = txtprocedencia.getText();
        sexo = cmbsexo.getSelectedIndex()+1;
        estadoCivil = cmbestadocivil.getSelectedIndex()+1;
        fechaNacimiento = txtfeNacimiento.getText();
        telefono = txttelefono.getText();
        telefonoEm = txttelefonoEm.getText();
        nombreEm = txttelefonoEm.getText();
        
        sql.EjecutarConsultas("call insertar_paciente('"+cuenta+"', '"+identidad+"',"+nombre+","+apellido+","+direccion+","+correo+","+telefono+",'"+fechaNacimiento+"',"+sexo+","+estadoCivil+","+procedencia+","+nombreEm+","+telefonoEm+","+carrera+")");
        sql.EjecutarConsultas("call insertar_carrera("+carrera+")");
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        this.bloquearInmodificable(false);
        this.bloquearModificable(false);
        this.bloquearBotones(true);
        limpiar();        // TODO add your handling code here:
    }//GEN-LAST:event_btncancelarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        this.bloquearBotones(false);
        this.bloquearInmodificable(false);
        this.bloquearModificable(true);
        
    }//GEN-LAST:event_btnModificarActionPerformed

    void bloquearBotones(boolean b){
        this.btnguardar.setEnabled(!b);
        this.btncancelar.setEnabled(!b);
         this.btnbuscar.setEnabled(b);
        
        this.btnnuevo.setEnabled(b);
        this.btnModificar.setEnabled(b);
    }
    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        this.bloquearBotones(false);
        this.bloquearInmodificable(true);
        this.bloquearModificable(true);
        limpiar();      
        this.txtNcuenta.requestFocus();
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        JTextField A[]= { txtNidentida,txtnombre,txtapellido, 
        this.txtdireccion,this.txtcorreo,txttelefono,this.txtprocedencia,
        this.txtnombreEm,this.txttelefonoEm };
        JComboBox B[]={cmbcarrera,cmbsexo,cmbestadocivil};
        sql.camposArreglo("call buscar_paciente("+txtNcuenta.getText()+")", A);
        sql.comboArreglo("call buscar_paciente_combo("+txtNcuenta.getText()+")", B);
         this.bloquearInmodificable(true);
        this.bloquearModificable(true);
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        this.bloquearInmodificable(false);
        this.bloquearModificable(false);
        bloquearBotones(true);
    }//GEN-LAST:event_formInternalFrameOpened

    private void txtfeNacimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfeNacimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfeNacimientoActionPerformed

    private void txtcorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcorreoActionPerformed

    private void cmbcarreraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbcarreraKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbcarreraKeyPressed
    void bloquearInmodificable(boolean b) {
        this.txtnombre.setEnabled(b);
        this.txtapellido.setEnabled(b);
        this.cmbcarrera.setEnabled(b);
        this.txtNidentida.setEnabled(b);
        this.txtprocedencia.setEnabled(b);
        this.cmbsexo.setEnabled(b);
    }

    void bloquearModificable(boolean b) {
        this.cmbcarrera.setEnabled(b);
        this.txtdireccion.setEnabled(b);
        this.cmbestadocivil.setEnabled(b);
        this.txttelefono.setEnabled(b);
        this.txtnombreEm.setEnabled(b);
        this.txttelefonoEm.setEnabled(b);
    }

    void limpiar() {
        this.txtNcuenta.setText("");
        //this.txtcarrera.setText("");
        this.txtdireccion.setText("");
        this.cmbestadocivil.setSelectedIndex(0);
        this.txttelefono.setText("");
        this.txtnombreEm.setText("");
        this.txttelefonoEm.setText("");

        this.txtnombre.setText("");
        this.txtapellido.setText("");
        //this.txtcarrera.setText("");
        this.txtNidentida.setText("");
        this.txtprocedencia.setText("");
    }

    void guardar() {
        this.bloquearInmodificable(false);
        this.bloquearModificable(false);
        limpiar();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JComboBox<String> cmbcarrera;
    private javax.swing.JComboBox<String> cmbestadocivil;
    private javax.swing.JComboBox<String> cmbsexo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtNcuenta;
    private javax.swing.JTextField txtNidentida;
    private javax.swing.JTextField txtapellido;
    private javax.swing.JTextField txtcorreo;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtfeNacimiento;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtnombreEm;
    private javax.swing.JTextField txtprocedencia;
    private javax.swing.JTextField txttelefono;
    private javax.swing.JTextField txttelefonoEm;
    // End of variables declaration//GEN-END:variables
}
