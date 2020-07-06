package Interfaz;

import Clases.MySql;
import Clases.validacion;
import com.sun.media.sound.EmergencySoundbank;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import modificar.frmAgregarEmbarazo;
import modificar.frmModificar1;
import modificar.frmmesntruacion;
import modificar.frmReceta;

public class frmConsultaGeneral extends javax.swing.JInternalFrame {

    MySql sql = new MySql();
    validacion va = new validacion();
    DefaultTableModel modelo_AP, modelo_AF, modelo_EF, modelo_HT, modelo_MA, modelo_M, modelo_E, modelo_C, modelo_R, modelo_RA;
    String cuentap = "20182003050", cuentae = "20202003056";
    int idpa, iddoc, idpre;
    int idpaciente;
    int id = -1;
    String valor, valor2, valor3;
    public JSpinner JS[];
    boolean cambioAS;

    public frmConsultaGeneral() {
        initComponents();
        

    }
    public frmConsultaGeneral(String cd, String cp) {
        initComponents();
        this.txtNcuenta.setText(cp);
        cuentae=cd;
        cuentap=cp;
        iddoc = sql.valor("select buscar_iddoctores(" + cuentae + ")");
        idpaciente = sql.valor("select buscar_idpaciente(" + cuentap + ")");
        cargarModelos();
        llenarDatos();
        cambiar = true;
        cambioAS = false;

    }

    void cargarModelos() {
        modelo_AP = (DefaultTableModel) tablaAP.getModel();
        modelo_AF = (DefaultTableModel) tablaAF.getModel();
        modelo_EF = (DefaultTableModel) tablaEF.getModel();
        modelo_HT = (DefaultTableModel) tablaHT.getModel();
        modelo_MA = (DefaultTableModel) tablaMA.getModel();
        modelo_M = (DefaultTableModel) tablaM.getModel();
        modelo_E = (DefaultTableModel) tablaE.getModel();
        modelo_C = (DefaultTableModel) tablaC.getModel();
        modelo_R = (DefaultTableModel) tablaR.getModel();
        modelo_RA = (DefaultTableModel) tablaRA.getModel();
        llenarTablaAP();
        llenarTablaAF();
        llenarTablaEF();
        llenarTablaHT();
        llenarTablaMA();
        llenarTablaR();
        llenarTablaRA();
        //combos

        va.cargarCombos(cmbdia5, cmbmes5, cmbaño5);
        va.cargarEdad(cmbmenarqui);
        va.cargarEdad(cmbias);
        bloquearResu(false);
    }

    @SuppressWarnings("empty-statement")
    void llenarDatos() {
        JTextField A[] = {
            txtNcuenta, txtnombre, txtapellido, txtdireccion, txtNidentida, txtprocedencia, txtpeso, txttalla, txtimc,
            txttemperatura, txtpa, txtpulso, txtedad
        };
        sql.camposArreglo("call buscar_cs(" + idpaciente + ")", A);
        
        cargarAS();
        txtedad.setText(va.obtenerEdad(txtedad.getText()));
        int sexo = sql.valor("select sexo_idsexo from persona where nocuenta=" + cuentap);
        cmbsexo.setSelectedIndex(sexo - 1);
        
        if (sexo == 2) {
            llenarTablaE();
            llenarTablaC();
            
            llenarTablaM();
            JS = new JSpinner[]{jsg, jsp, jsa, jsc, jshv, jshm};
            obsetricos();
            bloquearBotonoesObsetricos(false);
            bloquearObse(false);
        } else {
            JPP.setEnabledAt(6,false);
        }
    }

    void obsetricos() {
        cmbmenarqui.setSelectedIndex((sql.valor("select menarquia from obsetricos where pacientes_id_pacientes=" + idpaciente)));
        JComboBox A[] = {cmbaño5, cmbmes5, cmbdia5};
        if (cmbmenarqui.getSelectedIndex() != 0) {
            sql.obtenerfecha("select fum from obsetricos where pacientes_id_pacientes=" + idpaciente, A);
            sql.obtenerJspinner("select hgo_g,hgo_p,hgo_a,hgo_c,hgo_hv,hgo_hm from obsetricos where pacientes_id_pacientes=" + idpaciente, JS);
        }
        for (JSpinner JS1 : JS) {
            JSpinn(JS1);
        }

    }

    void bloquearObse(boolean b) {
        cmbmenarqui.setEnabled(b);
        cmbaño5.setEnabled(b);
        cmbmes5.setEnabled(b);
        cmbdia5.setEnabled(b);
        for (JSpinner JS1 : JS) {
            JS1.setEnabled(b);
        }
        txtobservaciones.setEnabled(b);
    }

    void cargarAS() {
        cmbias.setSelectedIndex(sql.valor("select inicio_sexualidad from actividad_sexual where pacientes_id_pacientes=" + idpaciente));
        if (cmbias.getSelectedIndex() != 0) {
            jsnp.setValue(sql.obtenerID("select n_parejas from actividad_sexual where pacientes_id_pacientes=" + idpaciente, 1));
            JSpinn(jsnp);
            txtdes.setText(sql.obtenerValor("select descripcion_practicas_sexuales from actividad_sexual where pacientes_id_pacientes=" + idpaciente));
            if (cmbias.getSelectedIndex() != 0) {
                rbno.setEnabled(false);
                rbno.setEnabled(false);
                rbsi.setSelected(true);
                cmbias.setEnabled(false);
            }
        } else {
            comprobarRadio();
        }

    }

    void JSpinn(JSpinner a) {
        SpinnerNumberModel mo = (SpinnerNumberModel) a.getModel();
        mo.setMinimum((Comparable) a.getValue());
    }

    void llenarTablaM() {
        sql.RegistrosTabla("call menstruacion(3,0," + idpaciente + ",0,'','')", modelo_M);
    }

    void llenarTablaC() {
        sql.RegistrosTabla("call ginecologias(3,0," + idpaciente + ",'0000-00-00','')", modelo_C);
    }

    void llenarTablaE() {
        sql.RegistrosTabla("call embarazo(3,0," + idpaciente + ",'0000-00-00','')", modelo_E);
    }

    void llenarTablaR() {
        sql.RegistrosTabla("call resumenes_CG(3," + iddoc + "," + idpaciente + ",'','','')", modelo_R);
    }

    void llenarTablaRA() {
        sql.RegistrosTabla("call detalleRA(2, 'qq', " + idpaciente + ", 1, '1')", modelo_RA);
        btnNuevoRA.setEnabled(true);
        btnGUardarRA.setEnabled(false);
        btnCancelarRA.setEnabled(false);
    }

    void llenarTablaAP() {
        sql.RegistrosTabla("call verDPA(" + idpaciente + ")", modelo_AP);
    }

    void llenarTablaAF() {
        sql.RegistrosTabla("call verDAF(" + idpaciente + ")", modelo_AF);
    }

    void llenarTablaEF() {
        sql.RegistrosTabla("call verDEF(" + idpaciente + ")", modelo_EF);
    }

    void llenarTablaHT() {
        sql.RegistrosTabla("call verDHT(" + idpaciente + ")", modelo_HT);
    }

    void llenarTablaMA() {
        sql.RegistrosTablaMetodos("call clinica.detalle_MA(3, " + idpaciente + ", 1, 1)", modelo_MA);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BG1 = new javax.swing.ButtonGroup();
        BG2 = new javax.swing.ButtonGroup();
        BG3 = new javax.swing.ButtonGroup();
        BG4 = new javax.swing.ButtonGroup();
        BG5 = new javax.swing.ButtonGroup();
        BG6 = new javax.swing.ButtonGroup();
        BG7 = new javax.swing.ButtonGroup();
        BG8 = new javax.swing.ButtonGroup();
        JPP = new javax.swing.JTabbedPane();
        JpPrincipal = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNcuenta = new javax.swing.JTextField();
        txtNidentida = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtprocedencia = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cmbsexo = new javax.swing.JComboBox<>();
        txtnombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtapellido = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtedad = new javax.swing.JTextField();
        txtpeso = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txttemperatura = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtpa = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtpulso = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txttalla = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel20 = new javax.swing.JLabel();
        txtimc = new javax.swing.JTextField();
        JPAF = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaAF = new javax.swing.JTable();
        btnModificarAF = new javax.swing.JButton();
        btnNuevoAF = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        JPAP = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaAP = new javax.swing.JTable();
        btnModificarAP = new javax.swing.JButton();
        btnNuevoAP = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        JPHT = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaHT = new javax.swing.JTable();
        btnModificarHT = new javax.swing.JButton();
        btnNuevoHT = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        JPEF = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaEF = new javax.swing.JTable();
        btnModificarEF = new javax.swing.JButton();
        btnNuevoEF = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        JPActividadSexual = new javax.swing.JPanel();
        jLabel116 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        rbsi = new javax.swing.JRadioButton();
        rbno = new javax.swing.JRadioButton();
        jLabel124 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaMA = new javax.swing.JTable();
        cmbias = new javax.swing.JComboBox<>();
        jsnp = new javax.swing.JSpinner();
        btncancelarASexual = new javax.swing.JButton();
        btnguardarASexual = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtdes = new javax.swing.JTextArea();
        JPAntecedentesGinecologicos = new javax.swing.JPanel();
        jLabel121 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        jLabel131 = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        jLabel160 = new javax.swing.JLabel();
        jLabel161 = new javax.swing.JLabel();
        jLabel162 = new javax.swing.JLabel();
        jLabel163 = new javax.swing.JLabel();
        jLabel164 = new javax.swing.JLabel();
        jLabel165 = new javax.swing.JLabel();
        jLabel166 = new javax.swing.JLabel();
        jLabel167 = new javax.swing.JLabel();
        jsg = new javax.swing.JSpinner();
        jsp = new javax.swing.JSpinner();
        jsa = new javax.swing.JSpinner();
        jsc = new javax.swing.JSpinner();
        jshv = new javax.swing.JSpinner();
        jshm = new javax.swing.JSpinner();
        cmbaño5 = new javax.swing.JComboBox<>();
        cmbmes5 = new javax.swing.JComboBox<>();
        cmbdia5 = new javax.swing.JComboBox<>();
        cmbmenarqui = new javax.swing.JComboBox<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaM = new javax.swing.JTable();
        jLabel169 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tablaE = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        tablaC = new javax.swing.JTable();
        btnnuevoM = new javax.swing.JButton();
        btnnuevoC = new javax.swing.JButton();
        btnnuevoE = new javax.swing.JButton();
        btnmodificrE = new javax.swing.JButton();
        btnmodificarC = new javax.swing.JButton();
        btnmodificarm = new javax.swing.JButton();
        btnverE = new javax.swing.JButton();
        btnverC = new javax.swing.JButton();
        btnverM = new javax.swing.JButton();
        btnguardarObsetricos = new javax.swing.JButton();
        btnmodificarObsetricos = new javax.swing.JButton();
        btncalcelarObsetricos = new javax.swing.JButton();
        jScrollPane14 = new javax.swing.JScrollPane();
        txtobservaciones = new javax.swing.JTextArea();
        JPRadiobrafias = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        tablaRA = new javax.swing.JTable();
        lblimagen = new javax.swing.JLabel();
        btnNuevoRA = new javax.swing.JButton();
        btnGUardarRA = new javax.swing.JButton();
        btnCancelarRA = new javax.swing.JButton();
        JPResumen = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        txtIndica = new javax.swing.JTextArea();
        jScrollPane13 = new javax.swing.JScrollPane();
        txtImpreDiag = new javax.swing.JTextArea();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablaR = new javax.swing.JTable();
        btnguardarR = new javax.swing.JButton();
        btnnuevoR = new javax.swing.JButton();
        btncancelarR = new javax.swing.JButton();
        btnnuevoR1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        JPP.setBackground(new java.awt.Color(255, 223, 0));
        JPP.setAutoscrolls(true);

        JpPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel3.setText("Nombre:");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel5.setText("Número de Cuenta:");

        txtNcuenta.setEditable(false);
        txtNcuenta.setBackground(new java.awt.Color(255, 223, 0));
        txtNcuenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtNcuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNcuentaActionPerformed(evt);
            }
        });

        txtNidentida.setEditable(false);
        txtNidentida.setBackground(new java.awt.Color(255, 223, 0));
        txtNidentida.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtNidentida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNidentidaActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel9.setText("Procedencia:");

        txtprocedencia.setEditable(false);
        txtprocedencia.setBackground(new java.awt.Color(255, 223, 0));
        txtprocedencia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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
        cmbsexo.setEnabled(false);
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

        txtnombre.setEditable(false);
        txtnombre.setBackground(new java.awt.Color(255, 223, 0));
        txtnombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombreActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel4.setText("Apellido:");

        txtapellido.setEditable(false);
        txtapellido.setBackground(new java.awt.Color(255, 223, 0));
        txtapellido.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtapellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtapellidoActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel6.setText("Dirección:");

        txtdireccion.setEditable(false);
        txtdireccion.setBackground(new java.awt.Color(255, 223, 0));
        txtdireccion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtdireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdireccionActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel7.setText("Número de Identidad:");

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel14.setText("Edad:");

        txtedad.setEditable(false);
        txtedad.setBackground(new java.awt.Color(255, 223, 0));
        txtedad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtedadActionPerformed(evt);
            }
        });

        txtpeso.setEditable(false);
        txtpeso.setBackground(new java.awt.Color(255, 223, 0));
        txtpeso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtpeso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpesoActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel15.setText("Peso:");

        jLabel26.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel26.setText("Temperatura:");

        txttemperatura.setEditable(false);
        txttemperatura.setBackground(new java.awt.Color(255, 223, 0));
        txttemperatura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txttemperatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttemperaturaActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel27.setText("°C");

        jLabel28.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel28.setText("Presion arterial:");

        txtpa.setEditable(false);
        txtpa.setBackground(new java.awt.Color(255, 223, 0));
        txtpa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtpa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpaActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel29.setText("mmHg");

        jLabel30.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel30.setText("Pulso: ");

        txtpulso.setEditable(false);
        txtpulso.setBackground(new java.awt.Color(255, 223, 0));
        txtpulso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtpulso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpulsoActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel31.setText("mmHg");

        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel16.setText("Talla:");

        txttalla.setEditable(false);
        txttalla.setBackground(new java.awt.Color(255, 223, 0));
        txttalla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txttalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttallaActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel19.setText("HEA:");

        jTextArea1.setBackground(new java.awt.Color(255, 223, 0));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setViewportView(jTextArea1);

        jLabel20.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel20.setText("IMC:");

        txtimc.setEditable(false);
        txtimc.setBackground(new java.awt.Color(255, 223, 0));
        txtimc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtimc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtimcActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JpPrincipalLayout = new javax.swing.GroupLayout(JpPrincipal);
        JpPrincipal.setLayout(JpPrincipalLayout);
        JpPrincipalLayout.setHorizontalGroup(
            JpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpPrincipalLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNcuenta))
                    .addGroup(JpPrincipalLayout.createSequentialGroup()
                        .addGroup(JpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtapellido)
                            .addComponent(txtnombre)))
                    .addGroup(JpPrincipalLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtdireccion))
                    .addGroup(JpPrincipalLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNidentida))
                    .addGroup(JpPrincipalLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtprocedencia))
                    .addGroup(JpPrincipalLayout.createSequentialGroup()
                        .addGroup(JpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JpPrincipalLayout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtpulso, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel31))
                            .addGroup(JpPrincipalLayout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txttemperatura, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel27))
                            .addGroup(JpPrincipalLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(13, 13, 13)
                                .addComponent(txtedad, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtpeso, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JpPrincipalLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbsexo, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JpPrincipalLayout.createSequentialGroup()
                                .addGroup(JpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JpPrincipalLayout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txttalla, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(JpPrincipalLayout.createSequentialGroup()
                                        .addComponent(jLabel28)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtpa, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(JpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JpPrincipalLayout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtimc, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel29))))
                        .addGap(0, 101, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpPrincipalLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE))
                .addContainerGap())
        );
        JpPrincipalLayout.setVerticalGroup(
            JpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpPrincipalLayout.createSequentialGroup()
                .addGroup(JpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpPrincipalLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpPrincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(JpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNcuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addGroup(JpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(JpPrincipalLayout.createSequentialGroup()
                        .addGroup(JpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JpPrincipalLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(JpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtapellido, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpPrincipalLayout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jLabel4)))
                        .addGap(12, 12, 12)
                        .addGroup(JpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(12, 12, 12)
                        .addGroup(JpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNidentida, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtprocedencia, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbsexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtedad, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtpeso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(JpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JpPrincipalLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JpPrincipalLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(JpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txttalla, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpPrincipalLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtimc, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(JpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(txtpa, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29))
                        .addGap(18, 18, 18)
                        .addGroup(JpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtpulso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31)
                            .addComponent(jLabel30))
                        .addGap(18, 18, 18)
                        .addGroup(JpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttemperatura, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27)))
                    .addGroup(JpPrincipalLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        JPP.addTab("Principal", JpPrincipal);

        JPAF.setBackground(new java.awt.Color(255, 255, 255));

        tablaAF.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "ANTECEDENTES", "OBSERVACIONES"
            }
        ));
        tablaAF.setColumnSelectionAllowed(true);
        tablaAF.getTableHeader().setReorderingAllowed(false);
        tablaAF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAFMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaAF);
        tablaAF.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tablaAF.getColumnModel().getColumnCount() > 0) {
            tablaAF.getColumnModel().getColumn(0).setMinWidth(75);
            tablaAF.getColumnModel().getColumn(0).setMaxWidth(75);
            tablaAF.getColumnModel().getColumn(1).setMinWidth(300);
            tablaAF.getColumnModel().getColumn(1).setPreferredWidth(300);
            tablaAF.getColumnModel().getColumn(1).setMaxWidth(300);
        }

        btnModificarAF.setBackground(new java.awt.Color(255, 223, 0));
        btnModificarAF.setText("MODIFICAR");
        btnModificarAF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnModificarAF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarAFActionPerformed(evt);
            }
        });

        btnNuevoAF.setBackground(new java.awt.Color(255, 223, 0));
        btnNuevoAF.setText("NUEVO");
        btnNuevoAF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnNuevoAF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoAFActionPerformed(evt);
            }
        });

        jLabel1.setText("NOTA: En caso de positivo detallar parentesco en OBSERVACIONES.");

        javax.swing.GroupLayout JPAFLayout = new javax.swing.GroupLayout(JPAF);
        JPAF.setLayout(JPAFLayout);
        JPAFLayout.setHorizontalGroup(
            JPAFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPAFLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPAFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(JPAFLayout.createSequentialGroup()
                        .addGroup(JPAFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPAFLayout.createSequentialGroup()
                                .addComponent(btnNuevoAF, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModificarAF, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1))
                        .addGap(0, 533, Short.MAX_VALUE)))
                .addContainerGap())
        );
        JPAFLayout.setVerticalGroup(
            JPAFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPAFLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPAFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevoAF)
                    .addComponent(btnModificarAF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        JPP.addTab("Antecedentes Familiares", JPAF);

        JPAP.setBackground(new java.awt.Color(255, 255, 255));

        tablaAP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "ANTECEDENTES", "OBSERVACIONES"
            }
        ));
        tablaAP.getTableHeader().setReorderingAllowed(false);
        tablaAP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaAP);
        if (tablaAP.getColumnModel().getColumnCount() > 0) {
            tablaAP.getColumnModel().getColumn(0).setMinWidth(75);
            tablaAP.getColumnModel().getColumn(0).setMaxWidth(75);
            tablaAP.getColumnModel().getColumn(1).setMinWidth(300);
            tablaAP.getColumnModel().getColumn(1).setPreferredWidth(300);
            tablaAP.getColumnModel().getColumn(1).setMaxWidth(300);
        }

        btnModificarAP.setBackground(new java.awt.Color(255, 223, 0));
        btnModificarAP.setText("MODIFICAR");
        btnModificarAP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnModificarAP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarAPActionPerformed(evt);
            }
        });

        btnNuevoAP.setBackground(new java.awt.Color(255, 223, 0));
        btnNuevoAP.setText("NUEVO");
        btnNuevoAP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnNuevoAP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoAPActionPerformed(evt);
            }
        });

        jLabel2.setText("NOTA: En caso de positivo detallar inicio de la enfermedad y tratamiento en OBSERVACIONES");

        javax.swing.GroupLayout JPAPLayout = new javax.swing.GroupLayout(JPAP);
        JPAP.setLayout(JPAPLayout);
        JPAPLayout.setHorizontalGroup(
            JPAPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPAPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPAPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(JPAPLayout.createSequentialGroup()
                        .addGroup(JPAPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPAPLayout.createSequentialGroup()
                                .addComponent(btnNuevoAP, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModificarAP, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2))
                        .addGap(0, 350, Short.MAX_VALUE)))
                .addContainerGap())
        );
        JPAPLayout.setVerticalGroup(
            JPAPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPAPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPAPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevoAP)
                    .addComponent(btnModificarAP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        JPP.addTab("Antecedentes Personales", JPAP);

        JPHT.setBackground(new java.awt.Color(255, 255, 255));

        tablaHT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "HABITO TOXICOLOGICO", "OBSERVACIONES"
            }
        ));
        tablaHT.getTableHeader().setReorderingAllowed(false);
        tablaHT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaHTMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tablaHT);
        if (tablaHT.getColumnModel().getColumnCount() > 0) {
            tablaHT.getColumnModel().getColumn(0).setMinWidth(75);
            tablaHT.getColumnModel().getColumn(0).setMaxWidth(75);
            tablaHT.getColumnModel().getColumn(1).setMinWidth(300);
            tablaHT.getColumnModel().getColumn(1).setPreferredWidth(300);
            tablaHT.getColumnModel().getColumn(1).setMaxWidth(300);
        }

        btnModificarHT.setBackground(new java.awt.Color(255, 223, 0));
        btnModificarHT.setText("MODIFICAR");
        btnModificarHT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnModificarHT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarHTActionPerformed(evt);
            }
        });

        btnNuevoHT.setBackground(new java.awt.Color(255, 223, 0));
        btnNuevoHT.setText("NUEVO");
        btnNuevoHT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnNuevoHT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoHTActionPerformed(evt);
            }
        });

        jLabel11.setText("NOTA:En caso de positivo detallar el tiempo de uso, frecuencia y tipo en OBSERVACIONES.");

        javax.swing.GroupLayout JPHTLayout = new javax.swing.GroupLayout(JPHT);
        JPHT.setLayout(JPHTLayout);
        JPHTLayout.setHorizontalGroup(
            JPHTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPHTLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPHTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addGroup(JPHTLayout.createSequentialGroup()
                        .addGroup(JPHTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPHTLayout.createSequentialGroup()
                                .addComponent(btnNuevoHT, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModificarHT, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel11))
                        .addGap(0, 370, Short.MAX_VALUE)))
                .addContainerGap())
        );
        JPHTLayout.setVerticalGroup(
            JPHTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPHTLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPHTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevoHT)
                    .addComponent(btnModificarHT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        JPP.addTab("Habitos Toxicologicos", JPHT);

        JPEF.setBackground(new java.awt.Color(255, 255, 255));

        tablaEF.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "EXAMEN", "OBSERVACIONES"
            }
        ));
        tablaEF.getTableHeader().setReorderingAllowed(false);
        tablaEF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaEFMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablaEF);
        if (tablaEF.getColumnModel().getColumnCount() > 0) {
            tablaEF.getColumnModel().getColumn(0).setMinWidth(75);
            tablaEF.getColumnModel().getColumn(0).setMaxWidth(75);
            tablaEF.getColumnModel().getColumn(1).setMinWidth(300);
            tablaEF.getColumnModel().getColumn(1).setPreferredWidth(300);
            tablaEF.getColumnModel().getColumn(1).setMaxWidth(300);
        }

        btnModificarEF.setBackground(new java.awt.Color(255, 223, 0));
        btnModificarEF.setText("MODIFICAR");
        btnModificarEF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        btnModificarEF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarEFActionPerformed(evt);
            }
        });

        btnNuevoEF.setBackground(new java.awt.Color(255, 223, 0));
        btnNuevoEF.setText("NUEVO");
        btnNuevoEF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnNuevoEF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoEFActionPerformed(evt);
            }
        });

        jLabel8.setText("NOTA:: En caso de anormalidad describa en OBSERVACIONES.");

        javax.swing.GroupLayout JPEFLayout = new javax.swing.GroupLayout(JPEF);
        JPEF.setLayout(JPEFLayout);
        JPEFLayout.setHorizontalGroup(
            JPEFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPEFLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPEFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1021, Short.MAX_VALUE)
                    .addGroup(JPEFLayout.createSequentialGroup()
                        .addGroup(JPEFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPEFLayout.createSequentialGroup()
                                .addComponent(btnNuevoEF, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModificarEF, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        JPEFLayout.setVerticalGroup(
            JPEFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPEFLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPEFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevoEF)
                    .addComponent(btnModificarEF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        JPP.addTab("Examen Fisico", JPEF);

        JPActividadSexual.setBackground(new java.awt.Color(255, 255, 255));

        jLabel116.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel116.setText("2. Inicio de vida sexual");

        jLabel117.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel117.setText("3. N° de parejas sexuales");

        jLabel118.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel118.setText("4. Describir practicas sexuales");

        jLabel120.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel120.setText("de riesgo");

        jLabel122.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel122.setText("NOTA: En caso que su respuesta sea afirmativa completar información. ");

        jLabel123.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel123.setText("1. Es sexualemente activo");

        rbsi.setBackground(new java.awt.Color(255, 255, 255));
        BG1.add(rbsi);
        rbsi.setText("Si");
        rbsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbsiActionPerformed(evt);
            }
        });

        rbno.setBackground(new java.awt.Color(255, 255, 255));
        BG1.add(rbno);
        rbno.setSelected(true);
        rbno.setText("No");
        rbno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnoActionPerformed(evt);
            }
        });

        jLabel124.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel124.setText("5. Planificacion familiar actualmente");

        tablaMA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "METODO ANTICONCEPTIVO", "ACTIVO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablaMA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMAMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tablaMA);
        if (tablaMA.getColumnModel().getColumnCount() > 0) {
            tablaMA.getColumnModel().getColumn(1).setMinWidth(100);
            tablaMA.getColumnModel().getColumn(1).setMaxWidth(100);
        }

        cmbias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbiasItemStateChanged(evt);
            }
        });

        jsnp.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        jsnp.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jsnpStateChanged(evt);
            }
        });

        btncancelarASexual.setBackground(new java.awt.Color(255, 223, 0));
        btncancelarASexual.setText("CANCELAR");
        btncancelarASexual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btncancelarASexual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarASexualActionPerformed(evt);
            }
        });

        btnguardarASexual.setBackground(new java.awt.Color(255, 223, 0));
        btnguardarASexual.setText("GUARDAR");
        btnguardarASexual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnguardarASexual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarASexualActionPerformed(evt);
            }
        });

        txtdes.setColumns(20);
        txtdes.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtdes.setRows(5);
        jScrollPane11.setViewportView(txtdes);

        javax.swing.GroupLayout JPActividadSexualLayout = new javax.swing.GroupLayout(JPActividadSexual);
        JPActividadSexual.setLayout(JPActividadSexualLayout);
        JPActividadSexualLayout.setHorizontalGroup(
            JPActividadSexualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPActividadSexualLayout.createSequentialGroup()
                .addGroup(JPActividadSexualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel123)
                    .addGroup(JPActividadSexualLayout.createSequentialGroup()
                        .addComponent(rbsi)
                        .addGap(18, 18, 18)
                        .addComponent(rbno))
                    .addGroup(JPActividadSexualLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(JPActividadSexualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel122)
                            .addComponent(jLabel124)
                            .addGroup(JPActividadSexualLayout.createSequentialGroup()
                                .addGroup(JPActividadSexualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel118)
                                    .addComponent(jLabel117)
                                    .addComponent(jLabel116)
                                    .addGroup(JPActividadSexualLayout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(jLabel120)))
                                .addGap(18, 18, 18)
                                .addGroup(JPActividadSexualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jsnp, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbias, javax.swing.GroupLayout.Alignment.LEADING, 0, 378, Short.MAX_VALUE)
                                    .addComponent(jScrollPane11)))
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 319, Short.MAX_VALUE)
                .addGroup(JPActividadSexualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btncancelarASexual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnguardarASexual, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        JPActividadSexualLayout.setVerticalGroup(
            JPActividadSexualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPActividadSexualLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPActividadSexualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPActividadSexualLayout.createSequentialGroup()
                        .addComponent(jLabel123)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JPActividadSexualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbsi)
                            .addComponent(rbno))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel122))
                    .addGroup(JPActividadSexualLayout.createSequentialGroup()
                        .addComponent(btnguardarASexual)
                        .addGap(18, 18, 18)
                        .addComponent(btncancelarASexual)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPActividadSexualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel116)
                    .addComponent(cmbias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPActividadSexualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel117)
                    .addComponent(jsnp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPActividadSexualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPActividadSexualLayout.createSequentialGroup()
                        .addComponent(jLabel118)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel120))
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel124)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        JPP.addTab("Actividad Sexual y Reproductiva", JPActividadSexual);

        JPAntecedentesGinecologicos.setBackground(new java.awt.Color(255, 255, 255));

        jLabel121.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel121.setText("5. Ciclo Menstrual");

        jLabel129.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel129.setText("1. Menarquia a: ");

        jLabel130.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel130.setText("Ginecologicos: Anotar toda aclaración en Observaciones:");

        jLabel131.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel131.setText("6. Citologia");

        jLabel149.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel149.setText("2. FUM");

        jLabel160.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel160.setText("4. Observaciones:");

        jLabel161.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel161.setText("3. HGO: ");

        jLabel162.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel162.setText("G:");

        jLabel163.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel163.setText("P:");

        jLabel164.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel164.setText("A:");

        jLabel165.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel165.setText("C:");

        jLabel166.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel166.setText("HV:");

        jLabel167.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel167.setText("HM:");

        jsg.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jsp.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jsa.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jsc.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jshv.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jshm.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        cmbaño5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbaño5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbaño5ItemStateChanged(evt);
            }
        });

        cmbmes5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiempre", "Octubre", "Noviembre", "Diciembre" }));
        cmbmes5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbmes5ItemStateChanged(evt);
            }
        });

        cmbdia5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbmenarqui.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tablaM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "DURACIÓN", "PERIODICIDAD", "CARACTERISTICAS"
            }
        ));
        tablaM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tablaM);
        if (tablaM.getColumnModel().getColumnCount() > 0) {
            tablaM.getColumnModel().getColumn(0).setMinWidth(75);
            tablaM.getColumnModel().getColumn(0).setMaxWidth(75);
            tablaM.getColumnModel().getColumn(1).setMinWidth(100);
            tablaM.getColumnModel().getColumn(1).setMaxWidth(100);
        }

        jLabel169.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel169.setText("7. Embarazos");

        tablaE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "FECHA PARTO", "COMO TERMINO"
            }
        ));
        tablaE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaEMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tablaE);
        if (tablaE.getColumnModel().getColumnCount() > 0) {
            tablaE.getColumnModel().getColumn(0).setMinWidth(100);
            tablaE.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        tablaC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "FECHA", "RESULTADO"
            }
        ));
        tablaC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tablaC);
        if (tablaC.getColumnModel().getColumnCount() > 0) {
            tablaC.getColumnModel().getColumn(0).setMinWidth(100);
            tablaC.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        btnnuevoM.setBackground(new java.awt.Color(255, 223, 0));
        btnnuevoM.setText("NUEVO");
        btnnuevoM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnnuevoM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoMActionPerformed(evt);
            }
        });

        btnnuevoC.setBackground(new java.awt.Color(255, 223, 0));
        btnnuevoC.setText("NUEVO");
        btnnuevoC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnnuevoC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoCActionPerformed(evt);
            }
        });

        btnnuevoE.setBackground(new java.awt.Color(255, 223, 0));
        btnnuevoE.setText("NUEVO");
        btnnuevoE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnnuevoE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoEActionPerformed(evt);
            }
        });

        btnmodificrE.setBackground(new java.awt.Color(255, 223, 0));
        btnmodificrE.setText("MODIFICAR");
        btnmodificrE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnmodificrE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificrEActionPerformed(evt);
            }
        });

        btnmodificarC.setBackground(new java.awt.Color(255, 223, 0));
        btnmodificarC.setText("MODIFICAR");
        btnmodificarC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnmodificarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarCActionPerformed(evt);
            }
        });

        btnmodificarm.setBackground(new java.awt.Color(255, 223, 0));
        btnmodificarm.setText("MODIFICAR");
        btnmodificarm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnmodificarm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarmActionPerformed(evt);
            }
        });

        btnverE.setBackground(new java.awt.Color(255, 223, 0));
        btnverE.setText("VER");
        btnverE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnverE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnverEActionPerformed(evt);
            }
        });

        btnverC.setBackground(new java.awt.Color(255, 223, 0));
        btnverC.setText("VER");
        btnverC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnverC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnverCActionPerformed(evt);
            }
        });

        btnverM.setBackground(new java.awt.Color(255, 223, 0));
        btnverM.setText("VER");
        btnverM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnverM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnverMActionPerformed(evt);
            }
        });

        btnguardarObsetricos.setBackground(new java.awt.Color(255, 223, 0));
        btnguardarObsetricos.setText("GUARDAR");
        btnguardarObsetricos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnguardarObsetricos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarObsetricosActionPerformed(evt);
            }
        });

        btnmodificarObsetricos.setBackground(new java.awt.Color(255, 223, 0));
        btnmodificarObsetricos.setText("MODIFICAR");
        btnmodificarObsetricos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnmodificarObsetricos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarObsetricosActionPerformed(evt);
            }
        });

        btncalcelarObsetricos.setBackground(new java.awt.Color(255, 223, 0));
        btncalcelarObsetricos.setText("CANCELAR");
        btncalcelarObsetricos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btncalcelarObsetricos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncalcelarObsetricosActionPerformed(evt);
            }
        });

        txtobservaciones.setColumns(20);
        txtobservaciones.setRows(5);
        jScrollPane14.setViewportView(txtobservaciones);

        javax.swing.GroupLayout JPAntecedentesGinecologicosLayout = new javax.swing.GroupLayout(JPAntecedentesGinecologicos);
        JPAntecedentesGinecologicos.setLayout(JPAntecedentesGinecologicosLayout);
        JPAntecedentesGinecologicosLayout.setHorizontalGroup(
            JPAntecedentesGinecologicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPAntecedentesGinecologicosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPAntecedentesGinecologicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPAntecedentesGinecologicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(JPAntecedentesGinecologicosLayout.createSequentialGroup()
                            .addGroup(JPAntecedentesGinecologicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(JPAntecedentesGinecologicosLayout.createSequentialGroup()
                                    .addComponent(jLabel121)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnnuevoM)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnmodificarm)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(JPAntecedentesGinecologicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(JPAntecedentesGinecologicosLayout.createSequentialGroup()
                                    .addComponent(jLabel131)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnnuevoC)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnmodificarC)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnverC, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JPAntecedentesGinecologicosLayout.createSequentialGroup()
                            .addComponent(jLabel169)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnnuevoE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnmodificrE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnverE, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(JPAntecedentesGinecologicosLayout.createSequentialGroup()
                            .addGroup(JPAntecedentesGinecologicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JPAntecedentesGinecologicosLayout.createSequentialGroup()
                                    .addComponent(jLabel160)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(JPAntecedentesGinecologicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel130)
                                    .addGroup(JPAntecedentesGinecologicosLayout.createSequentialGroup()
                                        .addComponent(jLabel129)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbmenarqui, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(52, 52, 52)
                                        .addComponent(jLabel149)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbaño5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbmes5, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbdia5, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(JPAntecedentesGinecologicosLayout.createSequentialGroup()
                                        .addComponent(jLabel161)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel162)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jsg, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel163)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jsp, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(24, 24, 24)
                                        .addComponent(jLabel164)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jsa, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel165)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jsc, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel166)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jshv, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel167)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jshm, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(JPAntecedentesGinecologicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(JPAntecedentesGinecologicosLayout.createSequentialGroup()
                                    .addGap(62, 62, 62)
                                    .addComponent(btncalcelarObsetricos, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(JPAntecedentesGinecologicosLayout.createSequentialGroup()
                                    .addGap(50, 50, 50)
                                    .addGroup(JPAntecedentesGinecologicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnguardarObsetricos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnmodificarObsetricos, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))))))
                    .addGroup(JPAntecedentesGinecologicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnverM, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        JPAntecedentesGinecologicosLayout.setVerticalGroup(
            JPAntecedentesGinecologicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPAntecedentesGinecologicosLayout.createSequentialGroup()
                .addGroup(JPAntecedentesGinecologicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPAntecedentesGinecologicosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel130))
                    .addComponent(btnmodificarObsetricos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPAntecedentesGinecologicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbmenarqui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel129)
                    .addComponent(jLabel149)
                    .addComponent(cmbaño5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbmes5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbdia5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnguardarObsetricos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(JPAntecedentesGinecologicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel161)
                    .addComponent(jLabel162)
                    .addComponent(jsg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel163)
                    .addComponent(jsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel164)
                    .addComponent(jsa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel165)
                    .addComponent(jsc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel166)
                    .addComponent(jshv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel167)
                    .addComponent(jshm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncalcelarObsetricos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel160)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPAntecedentesGinecologicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel121)
                    .addComponent(btnnuevoM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnmodificarm, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnverM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel131)
                    .addComponent(btnnuevoC, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnmodificarC, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnverC, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPAntecedentesGinecologicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPAntecedentesGinecologicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel169)
                    .addComponent(btnnuevoE, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnmodificrE, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnverE, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        JPP.addTab("Antecedentes Ginecologicos / Obsetricos", JPAntecedentesGinecologicos);

        JPRadiobrafias.setBackground(new java.awt.Color(255, 255, 255));

        jLabel80.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N

        tablaRA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "NOMBRE"
            }
        ));
        tablaRA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaRAMouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(tablaRA);
        if (tablaRA.getColumnModel().getColumnCount() > 0) {
            tablaRA.getColumnModel().getColumn(0).setMinWidth(50);
            tablaRA.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        btnNuevoRA.setBackground(new java.awt.Color(255, 223, 0));
        btnNuevoRA.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnNuevoRA.setText("NUEVA");
        btnNuevoRA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        btnNuevoRA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoRAActionPerformed(evt);
            }
        });

        btnGUardarRA.setBackground(new java.awt.Color(255, 223, 0));
        btnGUardarRA.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnGUardarRA.setText("GUARDAR");
        btnGUardarRA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        btnGUardarRA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGUardarRAActionPerformed(evt);
            }
        });

        btnCancelarRA.setBackground(new java.awt.Color(255, 223, 0));
        btnCancelarRA.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnCancelarRA.setText("CANCELAR");
        btnCancelarRA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        btnCancelarRA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarRAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPRadiobrafiasLayout = new javax.swing.GroupLayout(JPRadiobrafias);
        JPRadiobrafias.setLayout(JPRadiobrafiasLayout);
        JPRadiobrafiasLayout.setHorizontalGroup(
            JPRadiobrafiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPRadiobrafiasLayout.createSequentialGroup()
                .addGroup(JPRadiobrafiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPRadiobrafiasLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel80)
                        .addGap(81, 81, 81))
                    .addGroup(JPRadiobrafiasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(JPRadiobrafiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPRadiobrafiasLayout.createSequentialGroup()
                                .addComponent(btnNuevoRA, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGUardarRA, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(JPRadiobrafiasLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(btnCancelarRA, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(lblimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );
        JPRadiobrafiasLayout.setVerticalGroup(
            JPRadiobrafiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPRadiobrafiasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPRadiobrafiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPRadiobrafiasLayout.createSequentialGroup()
                        .addGroup(JPRadiobrafiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNuevoRA, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGUardarRA, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarRA, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(jLabel80)
                        .addContainerGap(83, Short.MAX_VALUE))
                    .addGroup(JPRadiobrafiasLayout.createSequentialGroup()
                        .addComponent(lblimagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        JPP.addTab("Radiografias", JPRadiobrafias);

        JPResumen.setBackground(new java.awt.Color(255, 255, 255));

        jLabel79.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N

        txtIndica.setColumns(20);
        txtIndica.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtIndica.setRows(5);
        txtIndica.setBorder(null);
        jScrollPane12.setViewportView(txtIndica);

        txtImpreDiag.setColumns(20);
        txtImpreDiag.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtImpreDiag.setRows(5);
        txtImpreDiag.setBorder(null);
        jScrollPane13.setViewportView(txtImpreDiag);

        jLabel82.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel82.setText("Impresion Diagnostica");

        jLabel83.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel83.setText("Indicaciones");

        tablaR.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "FECHA", "DOCTOR", "IMPRESION DIAGNOSTICA", "INDICACIONES"
            }
        ));
        tablaR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaRMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tablaR);
        if (tablaR.getColumnModel().getColumnCount() > 0) {
            tablaR.getColumnModel().getColumn(0).setMinWidth(100);
            tablaR.getColumnModel().getColumn(0).setMaxWidth(100);
            tablaR.getColumnModel().getColumn(1).setMinWidth(200);
            tablaR.getColumnModel().getColumn(1).setMaxWidth(200);
        }

        btnguardarR.setBackground(new java.awt.Color(255, 223, 0));
        btnguardarR.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnguardarR.setText("GUARDAR");
        btnguardarR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        btnguardarR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarRActionPerformed(evt);
            }
        });

        btnnuevoR.setBackground(new java.awt.Color(255, 223, 0));
        btnnuevoR.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnnuevoR.setText("NUEVO");
        btnnuevoR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        btnnuevoR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoRActionPerformed(evt);
            }
        });

        btncancelarR.setBackground(new java.awt.Color(255, 223, 0));
        btncancelarR.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btncancelarR.setText("CANCELAR");
        btncancelarR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        btncancelarR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarRActionPerformed(evt);
            }
        });

        btnnuevoR1.setBackground(new java.awt.Color(255, 223, 0));
        btnnuevoR1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnnuevoR1.setText("RECETAR");
        btnnuevoR1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        btnnuevoR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoR1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPResumenLayout = new javax.swing.GroupLayout(JPResumen);
        JPResumen.setLayout(JPResumenLayout);
        JPResumenLayout.setHorizontalGroup(
            JPResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPResumenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPResumenLayout.createSequentialGroup()
                        .addGap(411, 411, 411)
                        .addComponent(btnnuevoR1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnnuevoR, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnguardarR, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btncancelarR, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPResumenLayout.createSequentialGroup()
                        .addGroup(JPResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane8)
                            .addGroup(JPResumenLayout.createSequentialGroup()
                                .addGroup(JPResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JPResumenLayout.createSequentialGroup()
                                        .addComponent(jLabel82)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPResumenLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(JPResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel83)
                                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(438, 438, 438)
                        .addComponent(jLabel79)
                        .addContainerGap())))
        );
        JPResumenLayout.setVerticalGroup(
            JPResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPResumenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel83)
                    .addComponent(jLabel82))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(jScrollPane12))
                .addGroup(JPResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JPResumenLayout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(jLabel79)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(JPResumenLayout.createSequentialGroup()
                        .addGap(0, 5, Short.MAX_VALUE)
                        .addGroup(JPResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnguardarR, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btncancelarR, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnnuevoR, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnnuevoR1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        JPP.addTab("Resumen", JPResumen);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JPP, javax.swing.GroupLayout.PREFERRED_SIZE, 1056, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JPP, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNcuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNcuentaActionPerformed
        txtnombre.requestFocus();
    }//GEN-LAST:event_txtNcuentaActionPerformed

    private void txtNidentidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNidentidaActionPerformed
        //  this.txtcarrera.requestFocus();
    }//GEN-LAST:event_txtNidentidaActionPerformed

    private void txtprocedenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtprocedenciaActionPerformed
        this.cmbsexo.requestFocus();
        this.cmbsexo.showPopup();
    }//GEN-LAST:event_txtprocedenciaActionPerformed

    private void cmbsexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbsexoActionPerformed

    }//GEN-LAST:event_cmbsexoActionPerformed

    private void cmbsexoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbsexoKeyPressed
        /*  if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.cmbestadocivil.requestFocus();
            this.cmbestadocivil.showPopup();
        }*/
    }//GEN-LAST:event_cmbsexoKeyPressed

    private void txtnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreActionPerformed
        this.txtapellido.requestFocus();
    }//GEN-LAST:event_txtnombreActionPerformed

    private void txtapellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtapellidoActionPerformed
        this.txtdireccion.requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txtapellidoActionPerformed

    private void txtdireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdireccionActionPerformed
        this.txtNidentida.requestFocus();
    }//GEN-LAST:event_txtdireccionActionPerformed

    private void txtedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtedadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtedadActionPerformed

    private void txtpesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpesoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpesoActionPerformed

    private void txttemperaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttemperaturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttemperaturaActionPerformed

    private void txtpaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpaActionPerformed

    private void txtpulsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpulsoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpulsoActionPerformed

    private void txttallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttallaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttallaActionPerformed

    private void btnnuevoRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoRActionPerformed
        txtImpreDiag.setEditable(true);
        txtIndica.setEditable(true);
        limpirResumenes();
        bloquearResu(true);
    }//GEN-LAST:event_btnnuevoRActionPerformed

    private void btncancelarRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarRActionPerformed
        limpirResumenes();
        bloquearResu(false);
    }//GEN-LAST:event_btncancelarRActionPerformed

    private void btnguardarRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarRActionPerformed
        String indicaciones = txtIndica.getText(), diagno = txtImpreDiag.getText();
        if (!indicaciones.isEmpty() && !diagno.isEmpty()) {
            sql.EjecutarConsultas("call resumenes_CG(1," + iddoc + "," + idpaciente + ",'" + diagno + "','"
                    + va.getfecha() + "','" + indicaciones + "')");
            llenarTablaR();
            limpirResumenes();
            bloquearResu(false);
        } else {
            JOptionPane.showMessageDialog(null, "INGRESE LA IMPRESION DIAGNOSTICA Y LAS INDICACIONES");
        }
    }//GEN-LAST:event_btnguardarRActionPerformed

    void limpirResumenes() {
        txtIndica.setText("");
        txtImpreDiag.setText("");
    }

    void bloquearResu(boolean b) {
        btnnuevoR.setEnabled(!b);
        btnguardarR.setEnabled(b);
        btncancelarR.setEnabled(b);
    }
    private void rbsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbsiActionPerformed
        comprobarRadio();
    }//GEN-LAST:event_rbsiActionPerformed

    private void btnNuevoAPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoAPActionPerformed
        if (id != -1) {
            frmModificar1 mo = new frmModificar1("ANTECEDENTES PERSONALES", "", "ANTECEDENTE PERSONAL",
                    "call insertarDAP(" + idpaciente + "," + id + ",'", modelo_AP, "call verDPA(" + idpaciente + ")");
            mo.setVisible(true);
        } else {
            error("ANTECEDENTE PERSONAL");
        }
        id = -1;
    }//GEN-LAST:event_btnNuevoAPActionPerformed

    private void btnModificarAPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarAPActionPerformed
        if (id != -1) {
            frmModificar1 mo = new frmModificar1(this.getTitle(), valor, "ANTECEDENTE PERSONAL",
                    "call updateDAP(" + idpaciente + "," + id + ",'", modelo_AP, "call verDPA(" + idpaciente + ")");
            mo.setVisible(true);
        } else {
            error("ANTECEDENTE PERSONAL");
        }
        id = -1;
    }//GEN-LAST:event_btnModificarAPActionPerformed

    private void tablaAPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAPMouseClicked
        obtenerJtable(modelo_AP, tablaAP);
        bntNM(btnNuevoAP, btnModificarAP);
    }//GEN-LAST:event_tablaAPMouseClicked

    void bntNM(JButton nuevo, JButton modificar) {
        if (valor.isEmpty()) {
            modificar.setEnabled(false);
            nuevo.setEnabled(true);
        } else {
            modificar.setEnabled(true);
            nuevo.setEnabled(false);
        }
    }
    private void tablaAFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAFMouseClicked
        obtenerJtable(modelo_AF, tablaAF);
        bntNM(btnNuevoAF, btnModificarAF);
    }//GEN-LAST:event_tablaAFMouseClicked

    private void btnModificarAFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarAFActionPerformed
        if (id != -1) {
            frmModificar1 mo = new frmModificar1(this.getTitle(), valor, "ANTECEDENTE PERSONAL",
                    "call updateDAF(" + idpaciente + "," + id + ",'", modelo_AF, "call verDAF(" + idpaciente + ")");
            mo.setVisible(true);
        } else {
            error("UN ANTECEDENTE FAMILIAR");
        }
        id = -1;
    }//GEN-LAST:event_btnModificarAFActionPerformed

    void error(String s) {
        JOptionPane.showMessageDialog(null, "NO SELECCIONO " + s, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    private void btnNuevoAFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoAFActionPerformed
        if (id != -1) {
            frmModificar1 mo = new frmModificar1("ANTECEDENTES FAMILIARES", "", "ANTECEDENTE FAMILIARES",
                    "call insertarDAF(" + idpaciente + "," + id + ",'", modelo_AF, "call verDAF(" + idpaciente + ")");
            mo.setVisible(true);
        } else {
            error("UN ANTECEDENTE FAMILIAR");
        }
    }//GEN-LAST:event_btnNuevoAFActionPerformed

    private void tablaEFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaEFMouseClicked
        obtenerJtable(modelo_EF, tablaEF);
        bntNM(btnNuevoEF, btnModificarEF);
    }//GEN-LAST:event_tablaEFMouseClicked

    private void btnModificarEFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarEFActionPerformed
        if (id != -1) {
            frmModificar1 mo = new frmModificar1("EXAMEN FISICO", valor, "EXAMEN FISICO",
                    "call updateDEF(1," + id + ",'", modelo_EF, "call verDEF(" + idpaciente + ")");
            mo.setVisible(true);
        } else {
            error("EXAMEN FISICO");
        }
        id = -1;
    }//GEN-LAST:event_btnModificarEFActionPerformed

    private void btnNuevoEFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoEFActionPerformed
        if (id != -1) {
            frmModificar1 mo = new frmModificar1("EXAMEN FISICO", "", "EXAMEN FISICO",
                    "call insertarDEF(1," + id + ",'", modelo_EF, "call verDEF(" + idpaciente + ")");
            mo.setVisible(true);
        } else {
            error("EXAMEN FISICO");
        }
        id = -1;
    }//GEN-LAST:event_btnNuevoEFActionPerformed

    private void tablaHTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaHTMouseClicked
        obtenerJtable(modelo_HT, tablaHT);
        bntNM(btnNuevoHT, btnModificarHT);
    }//GEN-LAST:event_tablaHTMouseClicked

    private void btnModificarHTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarHTActionPerformed
        if (id != -1) {
            frmModificar1 mo = new frmModificar1("HABITOS TOXICOLOGIOS", "", "HABITOS TOXICOLOGICOS",
                    "call updateDHT(" + idpaciente + "," + id + ",'", modelo_HT, "call verDHT(" + idpaciente + ")");
            mo.setVisible(true);
        } else {
            error("HABITOS TOXICOLOGICO");
        }
        id = -1;
    }//GEN-LAST:event_btnModificarHTActionPerformed

    private void btnNuevoHTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoHTActionPerformed
        if (id != -1) {
            frmModificar1 mo = new frmModificar1("HABITOS TOXICOLOGIOS", valor, "HABITOS TOXICOLOGICOS",
                    "call insertarDHT(" + idpaciente + "," + id + ",'", modelo_HT, "call verDHT(" + idpaciente + ")");
            mo.setVisible(true);
        } else {
            error("HABITOS TOXICOLOGICO");
        }
        id = -1;
    }//GEN-LAST:event_btnNuevoHTActionPerformed

    private void txtimcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtimcActionPerformed

    }//GEN-LAST:event_txtimcActionPerformed
    boolean cambiar = false;
    private void cmbaño5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbaño5ItemStateChanged
        if (cambiar) {
            va.combodia(cmbdia5, cmbmes5, cmbaño5);
        }
    }//GEN-LAST:event_cmbaño5ItemStateChanged

    private void cmbmes5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbmes5ItemStateChanged
        va.combodia(cmbdia5, cmbmes5, cmbaño5);
    }//GEN-LAST:event_cmbmes5ItemStateChanged

    private void rbnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnoActionPerformed
        comprobarRadio();
    }//GEN-LAST:event_rbnoActionPerformed

    private void btnnuevoMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoMActionPerformed
        frmmesntruacion frmm = new frmmesntruacion("call menstruacion(1,0," + idpaciente,
                "call menstruacion(3,0," + idpaciente + ",0,'','')", modelo_M);
        frmm.setVisible(true);
    }//GEN-LAST:event_btnnuevoMActionPerformed

    private void btnnuevoCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoCActionPerformed
        frmAgregarEmbarazo frmAE = new frmAgregarEmbarazo("CITOLOGIA", "RESULTADO:", "call ginecologias(1,1," + idpaciente,
                modelo_C, "call ginecologias(3,1," + idpaciente + ",'0000-00-00','')");
        frmAE.setVisible(true);
    }//GEN-LAST:event_btnnuevoCActionPerformed

    private void btnnuevoEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoEActionPerformed
        frmAgregarEmbarazo frmAE = new frmAgregarEmbarazo("EMBARAZO", "COMO TERMINO:", "call embarazo(1,1," + idpaciente,
                modelo_E, "call embarazo(3,0," + idpaciente + ",'0000-00-00','')");
        frmAE.setVisible(true);
    }//GEN-LAST:event_btnnuevoEActionPerformed

    private void tablaCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCMouseClicked
        obtenerJtable(modelo_C, tablaC);
        if (id != -1) {
            valor2 = tablaC.getValueAt((id - 1), 0).toString();
        }
    }//GEN-LAST:event_tablaCMouseClicked

    private void tablaEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaEMouseClicked
        obtenerJtable(modelo_E, tablaE);
        if (id != -1) {
            valor2 = tablaE.getValueAt((id - 1), 0).toString();
        }
    }//GEN-LAST:event_tablaEMouseClicked

    private void tablaMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMMouseClicked
        obtenerJtable(modelo_M, tablaM);
        if (id != -1) {
            valor2 = tablaM.getValueAt((id - 1), 0).toString();
            valor3 = tablaM.getValueAt((id - 1), 2).toString();
        }
    }//GEN-LAST:event_tablaMMouseClicked

    private void btnmodificrEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificrEActionPerformed
        if (id != -1) {
            int idem = sql.obtenerID("select idembarazos from embarazos where  pacientes_id_pacientes=" + idpaciente, id);
            frmAgregarEmbarazo frmAE = new frmAgregarEmbarazo("EMBARAZO", "COMO TERMINO:", "call embarazo(2," + idem + "," + idpaciente,
                    modelo_E, "call embarazo(3,0," + idpaciente + ",'0000-00-00','')");
            frmAE.cargarupdate(valor2, valor, 2);
            frmAE.setVisible(true);
        } else {
            error("UN EMBARAZO");
        }
        id = -1;
    }//GEN-LAST:event_btnmodificrEActionPerformed

    private void btnmodificarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarCActionPerformed
        if (id != -1) {
            int idci = sql.obtenerID("select idginecologias from ginecologias where  pacientes_id_pacientes=" + idpaciente, id);
            frmAgregarEmbarazo frmAE = new frmAgregarEmbarazo("CITOLOGIA", "RESULTADO:", "call ginecologias(2," + idci + "," + idpaciente,
                    modelo_C, "call ginecologias(3,1," + idpaciente + ",'0000-00-00','')");
            frmAE.cargarupdate(valor2, valor, 2);
            frmAE.setVisible(true);
        } else {
            error("UNA CITOLOGIA");
        }
        id = -1;
    }//GEN-LAST:event_btnmodificarCActionPerformed

    private void btnmodificarmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarmActionPerformed
        if (id != -1) {
            int idme = sql.obtenerID("select idmenstruacion from menstruacion where  pacientes_id_pacientes=" + idpaciente, id);
            frmmesntruacion frmm = new frmmesntruacion("call menstruacion(2," + idme + "," + idpaciente,
                    "call menstruacion(3,0," + idpaciente + ",0,'','')", modelo_M);
            frmm.cargarupdate(valor2, valor, valor3, 2);
            frmm.setVisible(true);
        } else {
            error("UNA MENSTRUACION");
        }
        id = -1;
    }//GEN-LAST:event_btnmodificarmActionPerformed

    private void btnverEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnverEActionPerformed
        if (id != -1) {
            frmAgregarEmbarazo frmAE = new frmAgregarEmbarazo("EMBARAZO", "COMO TERMINO:", "", modelo_E, "");
            int idem = sql.obtenerID("select idembarazos from embarazos where  pacientes_id_pacientes=" + idpaciente, id);
            frmAE.cargarupdate(valor2, valor, 3);
            frmAE.setVisible(true);
        } else {
            error("UN EMBARAZO");
        }
        id = -1;
    }//GEN-LAST:event_btnverEActionPerformed

    private void btnverCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnverCActionPerformed
        if (id != -1) {
            frmAgregarEmbarazo frmAE = new frmAgregarEmbarazo("CITOLOGIA", "RESULTADO", "", modelo_C, "");
            int idem = sql.obtenerID("select idginecologias from ginecologias where  pacientes_id_pacientes=" + idpaciente, id);
            frmAE.cargarupdate(valor2, valor, 3);
            frmAE.setVisible(true);
        } else {
            error("UNA CITOLOGIA");
        }
        id = -1;
    }//GEN-LAST:event_btnverCActionPerformed

    private void btnverMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnverMActionPerformed
        if (id != -1) {
            frmmesntruacion frmm = new frmmesntruacion("", "", modelo_M);
            frmm.cargarupdate(valor2, valor, valor3, 3);
            frmm.setVisible(true);
        } else {
            error("UN CICLO MESNTRUAL");
        }
        id = -1;
    }//GEN-LAST:event_btnverMActionPerformed

    private void btnguardarObsetricosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarObsetricosActionPerformed
        sql.EjecutarConsultas("call update_obsetricos(" + idpaciente + "," + cmbmenarqui.getSelectedItem()
                + "," + jsg.getValue() + "," + jsp.getValue() + "," + jsa.getValue() + "," + jsc.getValue() + ","
                + jshv.getValue() + "," + jshm.getValue() + ",'"
                + va.getfecha(cmbdia5, cmbmes5, cmbaño5) + "','" + txtobservaciones.getText() + "')");
        for (JSpinner JS1 : JS) {
            JSpinn(JS1);
        }
        bloquearBotonoesObsetricos(false);
        bloquearObse(false);
    }//GEN-LAST:event_btnguardarObsetricosActionPerformed

    private void btnmodificarObsetricosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarObsetricosActionPerformed
        bloquearObse(true);
        bloquearBotonoesObsetricos(true);
    }//GEN-LAST:event_btnmodificarObsetricosActionPerformed

    private void btncalcelarObsetricosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncalcelarObsetricosActionPerformed
        obsetricos();
        bloquearBotonoesObsetricos(false);
        bloquearObse(false);
    }//GEN-LAST:event_btncalcelarObsetricosActionPerformed

    private void btncancelarASexualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarASexualActionPerformed
        cargarAS();

    }//GEN-LAST:event_btncancelarASexualActionPerformed

    private void btnguardarASexualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarASexualActionPerformed
        int s;
        String ivs = sql.obtenerValor("select inicio_sexualidad from actividad_sexual where pacientes_id_pacientes=" + idpaciente);
        if (ivs.equals("0")) {
            s = JOptionPane.showConfirmDialog(null, "ADVERTENCIA", "¿Esta seguro de guardar? El inicio de la vida sexual no puede ser modificado", JOptionPane.YES_NO_OPTION);
        } else {
            s = 1;
        }
        if (cambioAS != false) {
            if (s == 1) {
                if (sql.EjecutarConsultas("call actividad_sexual(" + idpaciente + "," + (cmbias.getSelectedIndex() + 1) + ","
                        + jsnp.getValue() + ",'" + txtdes.getText() + "')") == 1) {
                    JOptionPane.showMessageDialog(null, "SE MODIFICARON LOS DATOS");
                    cmbias.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(null, "NO SE MODIFICARON LOS DATOS");
                }
                JSpinn(jsa);
                cambioAS = false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "NO MODIFICO NINGUN DATO", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnguardarASexualActionPerformed

    private void cmbiasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbiasItemStateChanged
        cambioAS = true;
    }//GEN-LAST:event_cmbiasItemStateChanged

    private void jsnpStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jsnpStateChanged
        cambioAS = true;              // TODO add your handling code here:
    }//GEN-LAST:event_jsnpStateChanged

    private void tablaRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaRMouseClicked
        if (modelo_R.getValueAt(tablaR.getSelectedRow(), 2) != null) {
            txtImpreDiag.setText(modelo_R.getValueAt(tablaR.getSelectedRow(), 2).toString());
            txtIndica.setText(modelo_R.getValueAt(tablaR.getSelectedRow(), 3).toString());
            txtImpreDiag.setEditable(false);
            txtIndica.setEditable(false);
        }
    }//GEN-LAST:event_tablaRMouseClicked
    public frmReceta receta = new frmReceta(this);
    private void btnnuevoR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoR1ActionPerformed
        // TODO add your handling code here:
        receta.actualizar(cuentae, cuentap);
        receta.cargarfecha();
        receta.show();
        this.btnnuevoR1.setEnabled(false);
    }//GEN-LAST:event_btnnuevoR1ActionPerformed
    public void desbloquear() {
        this.btnnuevoR1.setEnabled(true);
    }
    private void tablaMAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMAMouseClicked
        if (rbsi.isSelected() && tablaMA.getSelectedColumn() == 1) {
            id = tablaMA.getSelectedRow();
            boolean es = (boolean) modelo_MA.getValueAt(id, 1);
            if (es == false) {
                sql.EjecutarConsultas("call clinica.detalle_MA(2, " + idpaciente + ", " + (id + 1) + ", 1)");
            } else {
                sql.EjecutarConsultas("call clinica.detalle_MA(1, " + idpaciente + ", " + (id + 1) + ", 1)");
            }
        }
    }//GEN-LAST:event_tablaMAMouseClicked

    private void btnNuevoRAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoRAActionPerformed
        JFileChooser selec = new JFileChooser();
        FileNameExtensionFilter fil = new FileNameExtensionFilter(".png", "png");
        selec.addChoosableFileFilter(fil);
        selec.setFileSelectionMode(JFileChooser.FILES_ONLY);
        selec.setAcceptAllFileFilterUsed(false);
        int r = selec.showOpenDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            File ar = selec.getSelectedFile();
            ruta = ar.getPath();
            ImageIcon radio = new ImageIcon(ruta);
            ImageIcon img2 = new ImageIcon(radio.getImage().getScaledInstance(lblimagen.getWidth(), lblimagen.getHeight(), Image.SCALE_SMOOTH));
            lblimagen.setIcon(img2);
            btnGUardarRA.setEnabled(true);
            btnNuevoRA.setEnabled(false);
            btnCancelarRA.setEnabled(true);
        }

    }//GEN-LAST:event_btnNuevoRAActionPerformed
    String ruta;
    private void btnGUardarRAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGUardarRAActionPerformed
        if (!ruta.isEmpty()) {
            String con = "call clinica.detalleRA(1, 'Radiografia " + va.getfecha() + "', " + idpaciente + ", 0, ?)";
            sql.ingresoDeImagen(con, ruta);
            llenarTablaRA();
            JOptionPane.showMessageDialog(null, "Se guardo la radiografia","REALIZADO", JOptionPane.INFORMATION_MESSAGE);
            lblimagen.setIcon(null);
        } else {
            JOptionPane.showMessageDialog(null,  "No ha seleccionado una imagen con extension .png","ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnGUardarRAActionPerformed

    private void tablaRAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaRAMouseClicked
        
       if( modelo_RA.getValueAt(tablaRA.getSelectedRow(), 0) != null){
            id = Integer.valueOf(modelo_RA.getValueAt(tablaRA.getSelectedRow(), 0).toString());
            valor = modelo_RA.getValueAt(tablaRA.getSelectedRow(), 1).toString();
            lblimagen.setIcon(sql.sacarImagen("call detalleRA(3, '" + valor + "', " + idpaciente + ", " + id + ", '1')",
                    lblimagen.getWidth(), lblimagen.getHeight()));
        }
    }//GEN-LAST:event_tablaRAMouseClicked

    private void btnCancelarRAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarRAActionPerformed

        btnGUardarRA.setEnabled(false);
        btnNuevoRA.setEnabled(true);
        btnCancelarRA.setEnabled(false);
        lblimagen.setIcon(null);
        ruta = "";
    }//GEN-LAST:event_btnCancelarRAActionPerformed
    void comprobarRadio() {
        if (rbsi.isSelected()) {
            actividadSexual(true);
        } else {
            actividadSexual(false);
        }
    }

    void obtenerJtable(DefaultTableModel mo, JTable tabla) {

        if (mo.getValueAt(tabla.getSelectedRow(), 0) != null) {
            if (mo.getValueAt(tabla.getSelectedRow(), 2) != null) {
                valor = mo.getValueAt(tabla.getSelectedRow(), 1).toString();
                id = Integer.valueOf(mo.getValueAt(tabla.getSelectedRow(), 0).toString());
            } else {
                valor = "";
            }

        }
    }

    void bloquearBotonoesObsetricos(boolean a) {
        btnmodificarObsetricos.setEnabled(!a);
        btnguardarObsetricos.setEnabled(a);
        btncalcelarObsetricos.setEnabled(a);
    }

    void actividadSexual(boolean b) {
        cmbias.setEnabled(b);
        jsnp.setEnabled(b);
        txtdes.setEnabled(b);
        tablaMA.setEnabled(b);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup BG1;
    private javax.swing.ButtonGroup BG2;
    private javax.swing.ButtonGroup BG3;
    private javax.swing.ButtonGroup BG4;
    private javax.swing.ButtonGroup BG5;
    private javax.swing.ButtonGroup BG6;
    private javax.swing.ButtonGroup BG7;
    private javax.swing.ButtonGroup BG8;
    private javax.swing.JPanel JPAF;
    private javax.swing.JPanel JPAP;
    private javax.swing.JPanel JPActividadSexual;
    private javax.swing.JPanel JPAntecedentesGinecologicos;
    private javax.swing.JPanel JPEF;
    private javax.swing.JPanel JPHT;
    private javax.swing.JTabbedPane JPP;
    private javax.swing.JPanel JPRadiobrafias;
    private javax.swing.JPanel JPResumen;
    private javax.swing.JPanel JpPrincipal;
    private javax.swing.JButton btnCancelarRA;
    private javax.swing.JButton btnGUardarRA;
    private javax.swing.JButton btnModificarAF;
    private javax.swing.JButton btnModificarAP;
    private javax.swing.JButton btnModificarEF;
    private javax.swing.JButton btnModificarHT;
    private javax.swing.JButton btnNuevoAF;
    private javax.swing.JButton btnNuevoAP;
    private javax.swing.JButton btnNuevoEF;
    private javax.swing.JButton btnNuevoHT;
    private javax.swing.JButton btnNuevoRA;
    private javax.swing.JButton btncalcelarObsetricos;
    private javax.swing.JButton btncancelarASexual;
    private javax.swing.JButton btncancelarR;
    private javax.swing.JButton btnguardarASexual;
    private javax.swing.JButton btnguardarObsetricos;
    private javax.swing.JButton btnguardarR;
    private javax.swing.JButton btnmodificarC;
    private javax.swing.JButton btnmodificarObsetricos;
    private javax.swing.JButton btnmodificarm;
    private javax.swing.JButton btnmodificrE;
    private javax.swing.JButton btnnuevoC;
    private javax.swing.JButton btnnuevoE;
    private javax.swing.JButton btnnuevoM;
    private javax.swing.JButton btnnuevoR;
    private javax.swing.JButton btnnuevoR1;
    private javax.swing.JButton btnverC;
    private javax.swing.JButton btnverE;
    private javax.swing.JButton btnverM;
    private javax.swing.JComboBox<String> cmbaño5;
    private javax.swing.JComboBox<String> cmbdia5;
    private javax.swing.JComboBox<String> cmbias;
    private javax.swing.JComboBox<String> cmbmenarqui;
    private javax.swing.JComboBox<String> cmbmes5;
    private javax.swing.JComboBox<String> cmbsexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JSpinner jsa;
    private javax.swing.JSpinner jsc;
    private javax.swing.JSpinner jsg;
    private javax.swing.JSpinner jshm;
    private javax.swing.JSpinner jshv;
    private javax.swing.JSpinner jsnp;
    private javax.swing.JSpinner jsp;
    private javax.swing.JLabel lblimagen;
    private javax.swing.JRadioButton rbno;
    private javax.swing.JRadioButton rbsi;
    private javax.swing.JTable tablaAF;
    private javax.swing.JTable tablaAP;
    private javax.swing.JTable tablaC;
    private javax.swing.JTable tablaE;
    private javax.swing.JTable tablaEF;
    private javax.swing.JTable tablaHT;
    private javax.swing.JTable tablaM;
    private javax.swing.JTable tablaMA;
    private javax.swing.JTable tablaR;
    private javax.swing.JTable tablaRA;
    private javax.swing.JTextArea txtImpreDiag;
    private javax.swing.JTextArea txtIndica;
    private javax.swing.JTextField txtNcuenta;
    private javax.swing.JTextField txtNidentida;
    private javax.swing.JTextField txtapellido;
    private javax.swing.JTextArea txtdes;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtedad;
    private javax.swing.JTextField txtimc;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextArea txtobservaciones;
    private javax.swing.JTextField txtpa;
    private javax.swing.JTextField txtpeso;
    private javax.swing.JTextField txtprocedencia;
    private javax.swing.JTextField txtpulso;
    private javax.swing.JTextField txttalla;
    private javax.swing.JTextField txttemperatura;
    // End of variables declaration//GEN-END:variables
}
