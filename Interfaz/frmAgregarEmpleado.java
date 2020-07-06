package Interfaz;

import Clases.MySql;
import Clases.contrasena;
import Clases.validacion;
import java.awt.event.KeyEvent;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
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
public class frmAgregarEmpleado extends javax.swing.JInternalFrame {

    Calendar cal = Calendar.getInstance();
    int year = cal.get(Calendar.YEAR);
    MySql sql = new MySql();
    validacion va=new validacion();
    
    public frmAgregarEmpleado() {
        initComponents();
        cargarCombo();
        txtContra.setBorder(BorderFactory.createCompoundBorder(txtContra.getBorder(), BorderFactory.createEmptyBorder(6, 6, 6, 6)));
       
    }
    String cuenta;
    String nombre;
    String apellido;
    String direccion;
    String identidad;
    int tipoEm;
    int sexo;
    int estadoCivil;
    String fechaNacimiento;
    String telefono;
    String hi;
    String hf;
    int especialidad;
    String correo;
    String contrasena; 
    String sal;
    
    public void cargarCombo() {
        va.cargarCombos(cmbdia, cmbmes, cmbaño);
        sql.CamposCombo("select tipo_empleado from tipo_empleado", cmbtipoEm);
        sql.CamposCombo("select estado_civil from estado_civil", cmbestadocivil);
    }
    
    public void cargarComboFecha() {
        cmbdia.removeAllItems();
        cmbaño.removeAllItems();
        cmbmes.setSelectedIndex(0);
        for (int i = year; i >= 1950; i--) {
            cmbaño.addItem(String.valueOf(i));
        }

        for (int i = 1; i < 32; i++) {
            cmbdia.addItem(String.valueOf(i));
        }
    }
    
    void combodia() {
        int mes = cmbmes.getSelectedIndex() + 1;
        int año = Integer.valueOf(cmbaño.getSelectedItem().toString());
        cmbdia.removeAllItems();
        if (mes % 2 == 0) {
            if ((mes == 2) && (año % 4 == 0)) {
                for (int i = 1; i <= 29; i++) {
                    cmbdia.addItem(String.valueOf(i));
                }
            } else if (mes == 2) {
                for (int i = 1; i < 29; i++) {
                    cmbdia.addItem(String.valueOf(i));
                }
            } else {
                for (int i = 1; i < 31; i++) {
                    cmbdia.addItem(String.valueOf(i));
                }
            }
        } else {
            for (int i = 1; i <= 31; i++) {
                cmbdia.addItem(String.valueOf(i));
            }
        }
    }

     String getfecha() {
        String dia, mes, año;
        dia = cmbdia.getSelectedItem().toString();
        mes = String.valueOf(cmbmes.getSelectedIndex() + 1);
        año = cmbaño.getSelectedItem().toString();
        if (mes.length() < 2) {
            mes = "0" + mes;
        }
        if (dia.length() < 2) {
            dia = "0" + dia;
        }
        return "'" + año + "-" + mes + "-" + dia + "'";
    }

    

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
        jLabel4 = new javax.swing.JLabel();
        txtapellido = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNidentida = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cmbsexo = new javax.swing.JComboBox<>();
        cmbestadocivil = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txthi = new javax.swing.JTextField();
        txtNcuenta = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        cmbtipoEm = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        txthf = new javax.swing.JTextField();
        cmbespecialidad = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        txtcorreo = new javax.swing.JTextField();
        cmbaño = new javax.swing.JComboBox<>();
        cmbmes = new javax.swing.JComboBox<>();
        cmbdia = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        txtContra = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
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
        jLabel8.setText("Tipo Empleado");

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

        jLabel15.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel15.setText("Hora Entrada:");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel5.setText("Número de Cuenta:");

        txthi.setBackground(new java.awt.Color(255, 223, 0));
        txthi.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txthi.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txthi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthiActionPerformed(evt);
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
        jLabel16.setText("Especialidad:");

        cmbtipoEm.setBackground(new java.awt.Color(255, 223, 0));
        cmbtipoEm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Doctor", "Enfermero", "Recepcionista", "Administrador" }));
        cmbtipoEm.setToolTipText("");
        cmbtipoEm.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbtipoEmItemStateChanged(evt);
            }
        });
        cmbtipoEm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbtipoEmActionPerformed(evt);
            }
        });
        cmbtipoEm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbtipoEmKeyPressed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel17.setText("Hora Salida:");

        txthf.setBackground(new java.awt.Color(255, 223, 0));
        txthf.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txthf.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txthf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthfActionPerformed(evt);
            }
        });

        cmbespecialidad.setBackground(new java.awt.Color(255, 223, 0));
        cmbespecialidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Odontologo", "Medico General", "Psicologo" }));
        cmbespecialidad.setToolTipText("");
        cmbespecialidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbespecialidadActionPerformed(evt);
            }
        });
        cmbespecialidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbespecialidadKeyPressed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel14.setText("Correo electronico:");

        txtcorreo.setBackground(new java.awt.Color(255, 223, 0));
        txtcorreo.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtcorreo.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtcorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcorreoActionPerformed(evt);
            }
        });

        cmbaño.setBackground(new java.awt.Color(255, 223, 0));
        cmbaño.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbaño.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbañoItemStateChanged(evt);
            }
        });

        cmbmes.setBackground(new java.awt.Color(255, 223, 0));
        cmbmes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiempre", "Octubre", "Noviembre", "Diciembre" }));
        cmbmes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbmesItemStateChanged(evt);
            }
        });

        cmbdia.setBackground(new java.awt.Color(255, 223, 0));
        cmbdia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel18.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel18.setText("CONTRASEÑA: ");

        txtContra.setBackground(new java.awt.Color(65, 105, 225));
        txtContra.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtContra.setForeground(new java.awt.Color(255, 255, 255));
        txtContra.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtContra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtapellido))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtnombre))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtNcuenta, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(txtdireccion))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNidentida))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbtipoEm, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(4, 4, 4))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcorreo))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(18, 18, 18)
                                        .addComponent(txttelefono))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(18, 18, 18)
                                        .addComponent(cmbestadocivil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(cmbsexo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                        .addComponent(txthi, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(45, 45, 45)
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                        .addComponent(txthf, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addGap(18, 18, 18)
                                        .addComponent(cmbespecialidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtContra)))
                                .addGap(4, 4, 4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbaño, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbmes, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbdia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNcuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(12, 12, 12)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(txtcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtNidentida, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cmbtipoEm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbsexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbestadocivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbaño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbmes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbdia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(txthi, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel16))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(txthf, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbespecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(txtContra, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNcuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNcuentaActionPerformed
        txtnombre.requestFocus();
    }//GEN-LAST:event_txtNcuentaActionPerformed

    private void txthiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthiActionPerformed
        //  this.txttelefonoEm.requestFocus();
    }//GEN-LAST:event_txthiActionPerformed

    private void txttelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttelefonoActionPerformed
        this.txthi.requestFocus();
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

    private void txtNidentidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNidentidaActionPerformed
        //this.txtcarrera.requestFocus();
    }//GEN-LAST:event_txtNidentidaActionPerformed

    private void txtdireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdireccionActionPerformed
        this.txtNidentida.requestFocus();
    }//GEN-LAST:event_txtdireccionActionPerformed

    private void txtapellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtapellidoActionPerformed
        this.txtdireccion.requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txtapellidoActionPerformed

    private void txtnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreActionPerformed
        this.txtapellido.requestFocus();
    }//GEN-LAST:event_txtnombreActionPerformed

    void obtenerVariables() throws NoSuchAlgorithmException {
        cuenta = txtNcuenta.getText();
        nombre = txtnombre.getText();
        apellido = txtapellido.getText();
        direccion = txtdireccion.getText();
        identidad = txtNidentida.getText();
        tipoEm = cmbtipoEm.getSelectedIndex() + 1;

        
      String  lacontrasena=txtContra.getText();
        contrasena contra = new contrasena();
        byte[] salt = contra.createSalt();
        
       contrasena= contra.generateHash(lacontrasena, "MD5", salt);
                  
      sal= contra.bytesToHex(salt);
        
        sexo = cmbsexo.getSelectedIndex() + 1;
        estadoCivil = cmbestadocivil.getSelectedIndex() + 1;
        telefono = txttelefono.getText();
        hi = txthi.getText();
        hf = txthf.getText();
        fechaNacimiento = va.getfecha(cmbdia, cmbmes, cmbaño);
        especialidad = cmbespecialidad.getSelectedIndex() + 1;
        correo = txtcorreo.getText();
       tipoEm = sql.valor("select idtipo_empleado from tipo_empleado where tipo_empleado='"+ cmbtipoEm.getSelectedItem().toString() + "'");
       
       
    }
    
    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
      try {
            obtenerVariables();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(frmAgregarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        double cuenta1= Double.parseDouble(cuenta);
        
        if (!sql.versiesta("call buscar_empleado("+cuenta1+")")) {
            
            if(tipoEm==1){
                sql.EjecutarConsultas("select insertar_doctor(" + cuenta + ", '" + identidad + "','" + nombre + "','"
                    + apellido + "','" + direccion + "','" + correo + "'," + telefono + ",'" + fechaNacimiento + "'," + sexo
                    + "," + estadoCivil + ",'"+hi+"','"+hf+"','" + contrasena + "','"+sal+"',"+tipoEm+","+especialidad+")");
            JOptionPane.showMessageDialog(null, "Registro Exitoso");
                
            }else if(tipoEm!=1){

            sql.EjecutarConsultas("select insertar_empleado(" + cuenta + ", '" + identidad + "','" + nombre + "','"
                    + apellido + "','" + direccion + "','" + correo + "'," + telefono + ",'" + fechaNacimiento + "'," + sexo
                    + "," + estadoCivil + ",'"+hi+"','"+hf+"','" + contrasena + "','"+sal+"',"+tipoEm+")");
            JOptionPane.showMessageDialog(null, "Registro Exitoso");
            }
        } else {
            JOptionPane.showMessageDialog(null, "El numero de cuenta ya esta registrado.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        limpiar();
        bloquearBotones(false);
        bloquearInmodificable(false);
        bloquearModificable(false);
    
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

    void bloquearBotones(boolean b) {
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

    boolean cambiar = false;
    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        JTextField A[] = {txtNidentida, txtnombre, txtapellido,
            this.txtdireccion, this.txtcorreo, txttelefono};
        JComboBox C[] = {cmbaño, cmbmes, cmbdia};
        sql.obtenerfecha("select fecha_nacimiento from persona where nocuenta=" + cuenta, C);
        sql.camposArreglo("call buscar_empleado(" + txtNcuenta.getText() + ")", A);
        this.bloquearInmodificable(true);
        this.bloquearModificable(true);

    }//GEN-LAST:event_btnbuscarActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        this.bloquearInmodificable(false);
        this.bloquearModificable(false);
        bloquearBotones(true);
    }//GEN-LAST:event_formInternalFrameOpened

    private void cmbtipoEmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbtipoEmActionPerformed
        if (cmbtipoEm.getSelectedIndex()==0) {
            cmbespecialidad.setEnabled(true);
        }else cmbespecialidad.setEnabled(false);       
    }//GEN-LAST:event_cmbtipoEmActionPerformed

    private void cmbtipoEmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbtipoEmKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbtipoEmKeyPressed

    private void txthfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthfActionPerformed

    private void cmbespecialidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbespecialidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbespecialidadActionPerformed

    private void cmbespecialidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbespecialidadKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbespecialidadKeyPressed

    private void txtcorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcorreoActionPerformed

    private void cmbtipoEmItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbtipoEmItemStateChanged
    
    }//GEN-LAST:event_cmbtipoEmItemStateChanged

    private void cmbañoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbañoItemStateChanged
        if(cambiar){
            combodia();
        }
    }//GEN-LAST:event_cmbañoItemStateChanged

    private void cmbmesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbmesItemStateChanged
        combodia();
    }//GEN-LAST:event_cmbmesItemStateChanged

    private void txtContraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContraActionPerformed
    void bloquearInmodificable(boolean b) {
         this.txtnombre.setEnabled(b);
        this.txtapellido.setEnabled(b);
        this.txtNidentida.setEnabled(b);   
        this.cmbsexo.setEnabled(b);
    }

    void bloquearModificable(boolean b) {
         this.txtdireccion.setEnabled(b);
        this.cmbestadocivil.setEnabled(b);
        this.txttelefono.setEnabled(b);
        this.txthi.setEnabled(b);
    }

    void limpiar() {
        this.txtNcuenta.setText("");
        this.txtdireccion.setText("");
        this.cmbestadocivil.setSelectedIndex(0);
        this.txttelefono.setText("");
        this.txthi.setText("");
        this.txthf.setText("");
        this.txtnombre.setText("");
        this.txtapellido.setText("");
        this.txtNidentida.setText("");
        this.txtContra.setText("");
        this.txtcorreo.setText("");
        this.cmbdia.setSelectedIndex(0);
        this.cmbaño.setSelectedIndex(0);
        this.cmbmes.setSelectedIndex(0);
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
    private javax.swing.JComboBox<String> cmbaño;
    private javax.swing.JComboBox<String> cmbdia;
    private javax.swing.JComboBox<String> cmbespecialidad;
    private javax.swing.JComboBox<String> cmbestadocivil;
    private javax.swing.JComboBox<String> cmbmes;
    private javax.swing.JComboBox<String> cmbsexo;
    private javax.swing.JComboBox<String> cmbtipoEm;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtContra;
    private javax.swing.JTextField txtNcuenta;
    private javax.swing.JTextField txtNidentida;
    private javax.swing.JTextField txtapellido;
    private javax.swing.JTextField txtcorreo;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txthf;
    private javax.swing.JTextField txthi;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
