package Interfaz;

import Clases.MySql;
import Clases.validacion;
import java.util.Calendar;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Brenedin Enrique
 */
public class frmConsultaPsico extends javax.swing.JInternalFrame {

    MySql sql = new MySql();
    validacion va = new validacion();
    Calendar cal = Calendar.getInstance();
    
    String cuenta = "";
    String nombre;
    String apellido;
    String direccion;
    String identidad;
    String carrera;
    String procedencia;
    int sexo;
    int estadoCivil;
    int idpa,idpaciente,iddoc;
    int id = -1;
    String cuentae = "";
    String fechaNacimiento;
    String telefono;
    String fechaActual;
    String horaActual;
    String nombreEm;
    String telefonoEm;
    
    String motivo;
    
    String impresion_diagnostica;
    String tratamiento_psicologico;
    String fam;
    String personal;
    String histo;

    DefaultTableModel modelo_Citas;
boolean cambiar;
    
    public frmConsultaPsico(String ce ) {
        initComponents();
        cuentae=ce;
         iddoc = sql.valor("select buscar_iddoctores(" + cuentae + ")");
        
       cargarModelos();
        //cambiar = true;
        actualizar();
   
    }
    public frmConsultaPsico(String ce, String cp) {
        initComponents();
        cuentae=ce;
        cuenta=cp;
        this.txtNcuenta.setText(cp);
         iddoc = sql.valor("select buscar_iddoctores(" + cuentae + ")");
        
       cargarModelos();
        //cambiar = true;
        actualizar();
        buscar();
   
    }
    public frmConsultaPsico() {
        initComponents();
     
    }
    
    
    void cargarModelos() {
        modelo_Citas = (DefaultTableModel) tablaCA.getModel();
    }
    
    void llenarTablaAF(double Numerocuenta) {
        sql.RegistrosTabla("call verCitasPsico(" + Numerocuenta + ")", modelo_Citas);
    }
    
    void obtenerVariables() {
        cuenta = txtNcuenta.getText();
        nombre = txtnombre.getText();
        apellido = txtapellido.getText();
        direccion = txtdireccion.getText();
        identidad = txtNidentida.getText();

        procedencia = txtprocedencia.getText();
        sexo = cmbsexo.getSelectedIndex() + 1;
        estadoCivil = cmbestadocivil.getSelectedIndex() + 1;
        
        fechaNacimiento= txtfechaNacimiento.getText();
       motivo = txtMotivo.getText();
        telefono = txtfechaNacimiento.getText();
        telefonoEm = txttelefonoEm.getText();
        nombreEm = txtnombreEm.getText();
       

        
    }

    

    void actualizar(){
    validacion va=new validacion();
        
        this.txthora.setText(va.getHora());
    }
    
    void limpiar() {
        this.txtNcuenta.setText("");
        this.txtdireccion.setText("");
        this.cmbestadocivil.setSelectedIndex(0);
        this.cmbcarrera.setSelectedIndex(0);
        this.cmbsexo.setSelectedIndex(0);
        this.txttelefono.setText("");
        this.txtnombreEm.setText("");
        this.txttelefonoEm.setText("");
        this.txtnombre.setText("");
        this.txtapellido.setText("");
        this.txtNidentida.setText("");
        this.txtfechaNacimiento.setText("");
        this.txtprocedencia.setText("");
        this.txtcorreo.setText("");
        this.antecedentesPsiFam.setText("");
        this.antecedentesPsiPer.setText("");
        this.historiaPsi.setText("");
        this.impresionDiag.setText("");
        this.tratamiento.setText("");
        this.modelo_Citas.setRowCount(0);
        
        l_consultaPsico();
       
    }
    void l_consultaPsico(){
        this.txtMotivo.setText("");
        this.txthora.setText("");
     
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
    
    
    public void cargarCombo() {
        va.cargarCombos(cmbdia, cmbmes, cmbaño);
        sql.CamposCombo("select carreras from carreras", cmbcarrera);
       
        sql.CamposCombo("select estado_civil from estado_civil", cmbestadocivil);
    }
    
     void cargarsexo(double cuenta1){
        int sexo=0;
    try {
            sql.conec();
            sql.rs = sql.s.executeQuery("select sexo_idsexo from persona where nocuenta="+cuenta1);
            while (sql.rs.next()) {
                sexo= Integer.parseInt(sql.rs.getObject(1).toString());
            }
            sql.rs.close();
            sql.conexion.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if(sexo==1){cmbsexo.setSelectedIndex(0);cmbsexo.setSelectedIndex(0);}
        if(sexo==2){cmbsexo.setSelectedIndex(1);cmbsexo.setSelectedIndex(1);}
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
    public void cargarHora(JComboBox cmbhora, JComboBox cmbminuto) {
        cmbhora.removeAllItems();
        cmbminuto.removeAllItems();
        for (int i = 1; i < 13; i++) {
            cmbhora.addItem(i);
        }
        for (int i = 1; i < 60; i++) {
            cmbminuto.addItem(i);
        }
    }

    public String obtenerHora(JComboBox cmbh, JComboBox cmbm, String form) {
        String hora = "";
        if (form.equals("AM")) {
            hora = cmbh.getSelectedItem() + ":" + cmbm.getSelectedItem() + ":00";
        } else {
            hora = (cmbh.getSelectedIndex() + 13) + ":" + cmbm.getSelectedItem() + ":00";
        }
        return hora;
    }
    
    public void buscar(){
     idpaciente = sql.valor("select buscar_idpaciente(" + cuenta + ")");
     double cp= Double.parseDouble(cuenta);
        llenarTablaAF(cp);
        
        if(sql.versiesta("call buscar_paciente("+cuenta+")")){
        JTextField A[]= { txtNidentida,txtnombre,txtapellido,
        this.txtdireccion,this.txtcorreo,txttelefono,this.txtprocedencia,
        this.txtnombreEm,this.txttelefonoEm};
        JComboBox B[]={cmbcarrera,cmbsexo,cmbestadocivil};
        JTextField C=txtfechaNacimiento;
        
        String anteFam = sql.obtenerValor("select familiares from antecedentes_psicologicos \n" +
"where pacientes_id_pacientes="+idpaciente+" ORDER BY idantecedentes_psicologicos ASC LIMIT 1" );
        antecedentesPsiFam.setText(anteFam);
        
        String antePer = sql.obtenerValor("select personales from antecedentes_psicologicos \n" +
"where pacientes_id_pacientes="+idpaciente+" ORDER BY idantecedentes_psicologicos ASC LIMIT 1" );
        antecedentesPsiPer.setText(antePer);
        
        String histoCli = sql.obtenerValor("select historia from antecedentes_psicologicos \n" +
"where pacientes_id_pacientes="+idpaciente+" ORDER BY idantecedentes_psicologicos ASC LIMIT 1" );
        historiaPsi.setText(histoCli);
        
        
        
        sql.obtenerfechaNa("select fecha_nacimiento from persona where nocuenta="+cuenta, C);
        sql.camposArreglo("call buscar_paciente("+cuenta+")", A);
        
        String dia= this.cmbdia.getSelectedItem().toString();
        int mes= this.cmbmes.getSelectedIndex();
        String a= this.cmbaño.getSelectedItem().toString();
        String f=a+"-"+mes+"-"+dia;
        validacion va=new validacion();
        cargarsexo(cp);
        }
        else{
        JOptionPane.showMessageDialog(null, "Numero de cuenta no registrado");
        }
    
    
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtnombreEm = new javax.swing.JTextField();
        txtNcuenta = new javax.swing.JTextField();
        txtapellido = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNidentida = new javax.swing.JTextField();
        txthora = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtprocedencia = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cmbsexo = new javax.swing.JComboBox<>();
        cmbestadocivil = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtfechaNacimiento = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txttelefonoEm = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMotivo = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        cmbaño = new javax.swing.JComboBox<>();
        cmbmes = new javax.swing.JComboBox<>();
        cmbdia = new javax.swing.JComboBox<>();
        cmbcarrera = new javax.swing.JComboBox<>();
        btnnuevo = new javax.swing.JButton();
        btnbuscar = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        txtcorreo = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        antecedentesPsiFam = new javax.swing.JTextArea();
        jLabel21 = new javax.swing.JLabel();
        antecedentesPsiPer = new javax.swing.JTextArea();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        historiaPsi = new javax.swing.JTextArea();
        tratamiento = new javax.swing.JTextArea();
        jLabel24 = new javax.swing.JLabel();
        impresionDiag = new javax.swing.JTextArea();
        btnguardar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCA = new javax.swing.JTable();
        jLabel30 = new javax.swing.JLabel();

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

        jTabbedPane1.setBackground(new java.awt.Color(255, 223, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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

        txtapellido.setBackground(new java.awt.Color(255, 223, 0));
        txtapellido.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtapellido.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtapellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtapellidoActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel16.setText("Telefono:");

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel6.setText("Dirección:");

        jLabel17.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel17.setText("Fecha actual");

        txtdireccion.setBackground(new java.awt.Color(255, 223, 0));
        txtdireccion.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtdireccion.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtdireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdireccionActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel18.setText("Hora actual");

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

        txthora.setBackground(new java.awt.Color(255, 223, 0));
        txthora.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txthora.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txthora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthoraActionPerformed(evt);
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
        cmbsexo.setBorder(null);
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
        cmbestadocivil.setBorder(null);
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

        txtfechaNacimiento.setBackground(new java.awt.Color(255, 223, 0));
        txtfechaNacimiento.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtfechaNacimiento.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtfechaNacimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfechaNacimientoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel3.setText("Nombre:");

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel14.setText("En caso de emergencia llamar a:");

        jLabel15.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel15.setText("Nombre:");

        txtnombre.setBackground(new java.awt.Color(255, 223, 0));
        txtnombre.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtnombre.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombreActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel5.setText("Número de Cuenta:");

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

        txttelefono.setBackground(new java.awt.Color(255, 223, 0));
        txttelefono.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txttelefono.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txttelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttelefonoActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel19.setText("Motivo de la consulta");

        txtMotivo.setColumns(20);
        txtMotivo.setRows(5);
        txtMotivo.setBorder(null);
        jScrollPane1.setViewportView(txtMotivo);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logo-psicologia-png-5.png"))); // NOI18N

        cmbaño.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbaño.setBorder(null);
        cmbaño.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbañoItemStateChanged(evt);
            }
        });
        cmbaño.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbañoActionPerformed(evt);
            }
        });

        cmbmes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiempre", "Octubre", "Noviembre", "Diciembre" }));
        cmbmes.setBorder(null);
        cmbmes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbmesItemStateChanged(evt);
            }
        });

        cmbdia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbdia.setBorder(null);

        cmbcarrera.setBackground(new java.awt.Color(255, 223, 0));
        cmbcarrera.setToolTipText("");
        cmbcarrera.setBorder(null);

        btnnuevo.setBackground(new java.awt.Color(255, 223, 0));
        btnnuevo.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnnuevo.setText("LIMPIAR");
        btnnuevo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        btnbuscar.setBackground(new java.awt.Color(255, 223, 0));
        btnbuscar.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnbuscar.setText("BUSCAR");
        btnbuscar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel29.setText("Correo inst:");

        txtcorreo.setBackground(new java.awt.Color(255, 223, 0));
        txtcorreo.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtcorreo.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtcorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcorreoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel17)
                            .addGap(261, 261, 261))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel18)
                            .addGap(18, 18, 18)
                            .addComponent(txthora, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(cmbaño, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbmes, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbdia, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel14)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel16)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txttelefonoEm))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel15)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtnombreEm, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addGap(18, 18, 18)
                            .addComponent(cmbcarrera, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addGap(18, 18, 18)
                            .addComponent(cmbestadocivil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addGap(18, 18, 18)
                            .addComponent(cmbsexo, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txttelefono))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtfechaNacimiento))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel29)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtcorreo)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(18, 18, 18)
                            .addComponent(txtdireccion))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(18, 18, 18)
                            .addComponent(txtapellido))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(0, 0, 0)
                            .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(18, 18, 18)
                            .addComponent(txtNcuenta))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNidentida, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addGap(18, 18, 18)
                            .addComponent(txtprocedencia))))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(141, 141, 141)
                                .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(73, 73, 73))))
                .addGap(0, 336, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNcuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19))
                    .addComponent(jLabel5))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtapellido, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtNidentida, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtprocedencia, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(txtcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(cmbcarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbsexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbestadocivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)
                        .addGap(38, 38, 38)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnombreEm, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txttelefonoEm, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbaño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbmes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbdia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txthora, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))))
        );

        jTabbedPane1.addTab("Principal", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel20.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel20.setText("1. ANTECEDENTES PSICOLOGICOS FAMILIARES ");

        antecedentesPsiFam.setBackground(new java.awt.Color(255, 223, 0));
        antecedentesPsiFam.setColumns(20);
        antecedentesPsiFam.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        antecedentesPsiFam.setRows(5);

        jLabel21.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel21.setText("2. ANTECENTENTES PSICOLOGICOS PERSONALES:");

        antecedentesPsiPer.setBackground(new java.awt.Color(255, 223, 0));
        antecedentesPsiPer.setColumns(20);
        antecedentesPsiPer.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        antecedentesPsiPer.setRows(5);

        jLabel22.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel22.setText("3. HISTORIA PSICOLOGICA ");

        jLabel23.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel23.setText("4. IMPRESIÓN DIAGNOSTICA: ");

        historiaPsi.setBackground(new java.awt.Color(255, 223, 0));
        historiaPsi.setColumns(20);
        historiaPsi.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        historiaPsi.setRows(5);

        tratamiento.setBackground(new java.awt.Color(255, 223, 0));
        tratamiento.setColumns(20);
        tratamiento.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        tratamiento.setRows(5);

        jLabel24.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel24.setText("5. TRATAMIENTO PSICOLOGICO: ");

        impresionDiag.setBackground(new java.awt.Color(255, 223, 0));
        impresionDiag.setColumns(20);
        impresionDiag.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        impresionDiag.setRows(5);

        btnguardar.setBackground(new java.awt.Color(255, 223, 0));
        btnguardar.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnguardar.setText("GUARDAR");
        btnguardar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        btnguardar.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                btnguardarAncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(historiaPsi, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(antecedentesPsiPer, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(antecedentesPsiFam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel22)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel23))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(impresionDiag, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                                    .addComponent(tratamiento)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(0, 0, 0)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(impresionDiag, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(antecedentesPsiFam))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tratamiento, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(antecedentesPsiPer))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(historiaPsi, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Antecedentes", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tablaCA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Fecha y Hora", "Motivo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablaCA.setRowHeight(50);
        jScrollPane2.setViewportView(tablaCA);

        jLabel30.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        jLabel30.setText("Citas anteriores");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(255, 255, 255)
                        .addComponent(jLabel30)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Citas", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1043, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txttelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttelefonoActionPerformed

    private void txttelefonoEmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttelefonoEmActionPerformed
        this.btnguardar.requestFocus();
    }//GEN-LAST:event_txttelefonoEmActionPerformed

    private void txtnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreActionPerformed
        this.txtapellido.requestFocus();
    }//GEN-LAST:event_txtnombreActionPerformed

    private void txtfechaNacimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfechaNacimientoActionPerformed
        this.txtnombreEm.requestFocus();
    }//GEN-LAST:event_txtfechaNacimientoActionPerformed

    private void cmbestadocivilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbestadocivilKeyPressed

    }//GEN-LAST:event_cmbestadocivilKeyPressed

    private void cmbsexoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbsexoKeyPressed

    }//GEN-LAST:event_cmbsexoKeyPressed

    private void cmbsexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbsexoActionPerformed

    }//GEN-LAST:event_cmbsexoActionPerformed

    private void txtprocedenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtprocedenciaActionPerformed
        this.cmbsexo.requestFocus();
        this.cmbsexo.showPopup();
    }//GEN-LAST:event_txtprocedenciaActionPerformed

    private void txthoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthoraActionPerformed

    private void txtNidentidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNidentidaActionPerformed
        this.txtprocedencia.requestFocus();
    }//GEN-LAST:event_txtNidentidaActionPerformed

    private void txtdireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdireccionActionPerformed
        this.txtNidentida.requestFocus();
    }//GEN-LAST:event_txtdireccionActionPerformed

    private void txtapellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtapellidoActionPerformed
        this.txtdireccion.requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txtapellidoActionPerformed

    private void txtNcuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNcuentaActionPerformed
        txtnombre.requestFocus();
    }//GEN-LAST:event_txtNcuentaActionPerformed

    private void txtnombreEmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreEmActionPerformed
        this.txttelefonoEm.requestFocus();
    }//GEN-LAST:event_txtnombreEmActionPerformed

    private void cmbañoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbañoItemStateChanged
        if (cambiar) {
            va.combodia(cmbdia, cmbmes, cmbaño);
        }
    }//GEN-LAST:event_cmbañoItemStateChanged

    private void cmbmesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbmesItemStateChanged
        va.combodia(cmbdia, cmbmes, cmbaño);
    }//GEN-LAST:event_cmbmesItemStateChanged

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
      

        this.btnbuscar.setEnabled(true);
        //this.btncancelar.setEnabled(false);
        limpiar();
        actualizar();
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        cargarCombo();
        cambiar = true;
        this.btnguardar.setEnabled(true);
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        actualizar();

        double cuenta= Double.parseDouble(txtNcuenta.getText());
        idpaciente = sql.valor("select buscar_idpaciente(" + cuenta + ")");
        llenarTablaAF(cuenta);
        
        if(sql.versiesta("call buscar_paciente("+cuenta+")")){
        JTextField A[]= { txtNidentida,txtnombre,txtapellido,
        this.txtdireccion,this.txtcorreo,txttelefono,this.txtprocedencia,
        this.txtnombreEm,this.txttelefonoEm};
        JComboBox B[]={cmbcarrera,cmbsexo,cmbestadocivil};
        JTextField C=txtfechaNacimiento;
        
        String anteFam = sql.obtenerValor("select familiares from antecedentes_psicologicos \n" +
"where pacientes_id_pacientes="+idpaciente+" ORDER BY idantecedentes_psicologicos ASC LIMIT 1" );
        antecedentesPsiFam.setText(anteFam);
        
        String antePer = sql.obtenerValor("select personales from antecedentes_psicologicos \n" +
"where pacientes_id_pacientes="+idpaciente+" ORDER BY idantecedentes_psicologicos ASC LIMIT 1" );
        antecedentesPsiPer.setText(antePer);
        
        String histoCli = sql.obtenerValor("select historia from antecedentes_psicologicos \n" +
"where pacientes_id_pacientes="+idpaciente+" ORDER BY idantecedentes_psicologicos ASC LIMIT 1" );
        historiaPsi.setText(histoCli);
        
        
        
        sql.obtenerfechaNa("select fecha_nacimiento from persona where nocuenta="+cuenta, C);
        sql.camposArreglo("call buscar_paciente("+cuenta+")", A);
        
        String dia= this.cmbdia.getSelectedItem().toString();
        int mes= this.cmbmes.getSelectedIndex();
        String a= this.cmbaño.getSelectedItem().toString();
        String f=a+"-"+mes+"-"+dia;
        validacion va=new validacion();
        cargarsexo(cuenta);
        }
        else{
        JOptionPane.showMessageDialog(null, "Numero de cuenta no registrado");
        }
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void txtcorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcorreoActionPerformed

    private void cmbañoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbañoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbañoActionPerformed

    private void btnguardarAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_btnguardarAncestorMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnguardarAncestorMoved

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
            validacion va = new validacion();
        String f = va.getfechahora();
        motivo = "";
        double ce= 20202003056.0;
        double cuenta=0;

        try {
            motivo = txtMotivo.getText();
            impresion_diagnostica = impresionDiag.getText();
            tratamiento_psicologico= tratamiento.getText();
            fam= antecedentesPsiFam.getText();
            personal= antecedentesPsiPer.getText();
            histo= historiaPsi.getText();
            cuenta= Double.parseDouble(txtNcuenta.getText());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Dato Incorrecto o Faltan Datos");
        }
        if(motivo!=" " && impresion_diagnostica!= " " && tratamiento_psicologico!=" " && fam!=" " && personal!=" "&& histo!=" " )
        {
            sql.EjecutarConsultas("call insertar_consultaPsicoProce("+ ce + ","+cuenta+",'"+ f +"','"+ motivo +"','" + impresion_diagnostica + "','" + tratamiento_psicologico + "')");
            sql.EjecutarConsultas("call insertar_antecedentes_psico("+cuenta+",'"+fam+"','"+personal+"','"+histo+"')");
            JOptionPane.showMessageDialog(null, "Guardado con exito");
        }
    }//GEN-LAST:event_btnguardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea antecedentesPsiFam;
    private javax.swing.JTextArea antecedentesPsiPer;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JComboBox<String> cmbaño;
    private javax.swing.JComboBox<String> cmbcarrera;
    private javax.swing.JComboBox<String> cmbdia;
    private javax.swing.JComboBox<String> cmbestadocivil;
    private javax.swing.JComboBox<String> cmbmes;
    private javax.swing.JComboBox<String> cmbsexo;
    private javax.swing.JTextArea historiaPsi;
    private javax.swing.JTextArea impresionDiag;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tablaCA;
    private javax.swing.JTextArea tratamiento;
    private javax.swing.JTextArea txtMotivo;
    private javax.swing.JTextField txtNcuenta;
    private javax.swing.JTextField txtNidentida;
    private javax.swing.JTextField txtapellido;
    private javax.swing.JTextField txtcorreo;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtfechaNacimiento;
    private javax.swing.JTextField txthora;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtnombreEm;
    private javax.swing.JTextField txtprocedencia;
    private javax.swing.JTextField txttelefono;
    private javax.swing.JTextField txttelefonoEm;
    // End of variables declaration//GEN-END:variables
}

