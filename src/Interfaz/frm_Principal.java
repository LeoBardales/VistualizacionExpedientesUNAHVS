package Interfaz;

import Clases.MySql;
import Interfaz.frmAgregarPacientes2;
import Interfaz.frmConsultaGeneral;
import Interfaz.frmConsultaOdonto2;
import Interfaz.frmConsultaPsico;
import Interfaz.frmPreclinica1;
import Mantenimiento.Historial_Recetas;
import modificar.frmReceta;
import Mantenimiento.frmAntecedentes_Familiares;
import Mantenimiento.frmAntecedentes_Personales;
import Mantenimiento.frmAntecedentes_Toxicologicos;
import Mantenimiento.frmExamen_Fisico;
import Mantenimiento.frm_Carreras;
import Mantenimiento.Medicamentos;
import Mantenimiento.frmAnticonceptivos;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

public class frm_Principal extends javax.swing.JFrame {
ArrayList<JInternalFrame> ventanas = new ArrayList();
MySql sql = new MySql();
    public frm_Principal() {
        initComponents();   
        InisiarSesion();
        
        
    }

    void InisiarSesion() {
        this.MbAgregar.setEnabled(false);
        this.MbOpciones.setEnabled(false);
        this.MbConsulta.setEnabled(false);
        this.MbReceta.setEnabled(false);
        visitasMedicas.setVisible(false);
        Mantenimiento.setEnabled(false);
        frmLogin lo = new frmLogin(this,jDesktopPane1);
        jDesktopPane1.add(lo);
        lo.setLocation(550,200);
        lo.setVisible(true);   
    }
    void CerrarSesion() {
        for(int x=0; x<ventanas.size();x++){
        ventanas.get(x).setVisible(false);
        }
        if(receta!=null){receta.setVisible(false);}
        if(medicamentos.mm!=null){medicamentos.mm.setVisible(false);}
        if(medicamentos!=null){medicamentos.setVisible(false);}
        if(hr.r!=null){hr.r.setVisible(false);}
        if(hr!=null){hr.setVisible(false);}
   
        
        InisiarSesion();
    }
    public int idempleado=0;
    public int tipoempleado=0;
    public int especialidad=0;
    public String cuenta="";
    
    public void JDP(javax.swing.JInternalFrame p){
    jDesktopPane1.add(p);
    }
    
    public void validar(){
    this.MbOpciones.setEnabled(true);
    if(tipoempleado==1){
        if(especialidad==1)
        {
           this.MbConsulta.setEnabled(true);
           this.psicologo.setEnabled(false);
           this.preclinica.setEnabled(false);
           this.general.setEnabled(true);
           this.odontologia.setEnabled(false);
           this.MbReceta.setEnabled(true);
            visitasMedicas.setVisible(true);
            cola c1= new cola(cuenta,1,true,this);
            jDesktopPane1.add(c1);
            ventanas.add(c1);
            c1.setVisible(true);
            Mantenimiento.setEnabled(true);
            M7.setEnabled(false);
            M8.setEnabled(false);
        }
        if(especialidad==2)
        {
           this.MbConsulta.setEnabled(true);
           this.psicologo.setEnabled(false);
           this.preclinica.setEnabled(false);
           this.general.setEnabled(false);
           this.odontologia.setEnabled(true);
           this.MbReceta.setEnabled(true);
           cola c1= new cola(cuenta,2,true,this);
            jDesktopPane1.add(c1);
            ventanas.add(c1);
            c1.setVisible(true);
            Mantenimiento.setEnabled(true);
             M7.setEnabled(false);
             M8.setEnabled(false);
        }
        if(especialidad==3)
        {
           this.MbConsulta.setEnabled(true);
           this.psicologo.setEnabled(true);
           this.preclinica.setEnabled(false);
           this.general.setEnabled(false);
           this.odontologia.setEnabled(false);
           this.MbReceta.setEnabled(true);
           cola c1= new cola(cuenta,3,true,this);
            jDesktopPane1.add(c1);
            ventanas.add(c1);
            c1.setVisible(true);
            Mantenimiento.setEnabled(true);
            M7.setEnabled(false);
            M8.setEnabled(false);
        }
    }
    if(tipoempleado==2){
            this.MbConsulta.setEnabled(true);
           this.psicologo.setEnabled(false);
           this.preclinica.setEnabled(true);
           this.general.setEnabled(false);
           this.odontologia.setEnabled(false);
            cola c1= new cola(cuenta,4,true,this);
            jDesktopPane1.add(c1);
            ventanas.add(c1);
            c1.setVisible(true);
    }
    if(tipoempleado==3){
    this.MbAgregar.setEnabled(true);
    this.empleado.setEnabled(false);
    cita.setEnabled(true);
    }
    if(tipoempleado==4){
        Mantenimiento.setEnabled(true); 
        M7.setEnabled(true);
        M8.setEnabled(true);
    }
    if(tipoempleado==5){
        this.MbAgregar.setEnabled(true);
        this.MbOpciones.setEnabled(true);
        Mantenimiento.setEnabled(true);
        cita.setEnabled(true);
        M8.setEnabled(true);
        this.MbConsulta.setEnabled(true);
           this.psicologo.setEnabled(true);
           this.preclinica.setEnabled(true);
           this.general.setEnabled(true);
           this.odontologia.setEnabled(true);
           this.MbReceta.setEnabled(true);
            Mantenimiento.setEnabled(true);
            M7.setEnabled(true);
            M8.setEnabled(true);
    }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        MbAgregar = new javax.swing.JMenu();
        MiPaciente = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        empleado = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        cita = new javax.swing.JMenuItem();
        MbConsulta = new javax.swing.JMenu();
        preclinica = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        odontologia = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        general = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        psicologo = new javax.swing.JMenuItem();
        MbReceta = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        MbOpciones = new javax.swing.JMenu();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        Micerrar = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        MiSalir = new javax.swing.JMenuItem();
        Mantenimiento = new javax.swing.JMenu();
        M1 = new javax.swing.JMenuItem();
        M2 = new javax.swing.JMenuItem();
        M3 = new javax.swing.JMenuItem();
        M4 = new javax.swing.JMenuItem();
        M5 = new javax.swing.JMenuItem();
        M6 = new javax.swing.JMenuItem();
        M7 = new javax.swing.JMenuItem();
        M8 = new javax.swing.JMenuItem();
        visitasMedicas = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(6);
        setLocationByPlatform(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/UNAH.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                .addContainerGap())
        );

        jDesktopPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jMenuBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        MbAgregar.setText("Agregar");

        MiPaciente.setText("Paciente");
        MiPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MiPacienteActionPerformed(evt);
            }
        });
        MbAgregar.add(MiPaciente);
        MbAgregar.add(jSeparator1);

        empleado.setText("Empleado");
        empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empleadoActionPerformed(evt);
            }
        });
        MbAgregar.add(empleado);
        MbAgregar.add(jSeparator2);

        cita.setText("Citas");
        cita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                citaActionPerformed(evt);
            }
        });
        MbAgregar.add(cita);

        jMenuBar1.add(MbAgregar);

        MbConsulta.setText("Consulta");

        preclinica.setText("Preclinica");
        preclinica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preclinicaActionPerformed(evt);
            }
        });
        MbConsulta.add(preclinica);
        MbConsulta.add(jSeparator7);

        odontologia.setText("Odontologo");
        odontologia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                odontologiaActionPerformed(evt);
            }
        });
        MbConsulta.add(odontologia);
        MbConsulta.add(jSeparator5);

        general.setText("Medico General");
        general.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalActionPerformed(evt);
            }
        });
        MbConsulta.add(general);
        MbConsulta.add(jSeparator6);

        psicologo.setText("Psicologo");
        psicologo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                psicologoActionPerformed(evt);
            }
        });
        MbConsulta.add(psicologo);

        jMenuBar1.add(MbConsulta);

        MbReceta.setText("Receta");

        jMenuItem5.setText("Nueva Receta");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        MbReceta.add(jMenuItem5);

        jMenuBar1.add(MbReceta);

        MbOpciones.setText("Opciones");
        MbOpciones.add(jSeparator3);

        Micerrar.setText("Cerrar Sesion");
        Micerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MicerrarActionPerformed(evt);
            }
        });
        MbOpciones.add(Micerrar);
        MbOpciones.add(jSeparator4);

        MiSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        MiSalir.setText("Salir");
        MiSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MiSalirActionPerformed(evt);
            }
        });
        MbOpciones.add(MiSalir);

        jMenuBar1.add(MbOpciones);

        Mantenimiento.setText("Mantenimiento");

        M1.setText("Antecedentes Personales");
        M1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M1ActionPerformed(evt);
            }
        });
        Mantenimiento.add(M1);

        M2.setText("Antecedentes Familiares");
        M2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M2ActionPerformed(evt);
            }
        });
        Mantenimiento.add(M2);

        M3.setText("Hábitos Toxicológicos");
        M3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M3ActionPerformed(evt);
            }
        });
        Mantenimiento.add(M3);

        M4.setText("Examen Fisico");
        M4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M4ActionPerformed(evt);
            }
        });
        Mantenimiento.add(M4);

        M5.setText("Carreras");
        M5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M5ActionPerformed(evt);
            }
        });
        Mantenimiento.add(M5);

        M6.setText("Anticonceptivos");
        M6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M6ActionPerformed(evt);
            }
        });
        Mantenimiento.add(M6);

        M7.setText("Medicamentos");
        M7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M7ActionPerformed(evt);
            }
        });
        Mantenimiento.add(M7);

        M8.setText("Recetas");
        M8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M8ActionPerformed(evt);
            }
        });
        Mantenimiento.add(M8);

        jMenuBar1.add(Mantenimiento);

        visitasMedicas.setText("Registro Consultas");
        visitasMedicas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                visitasMedicasMouseClicked(evt);
            }
        });
        jMenuBar1.add(visitasMedicas);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        setSize(new java.awt.Dimension(895, 526));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
//        InisiarSesion();
         
    }//GEN-LAST:event_formWindowOpened

    private void MicerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MicerrarActionPerformed
        CerrarSesion();
    }//GEN-LAST:event_MicerrarActionPerformed

    private void MiPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MiPacienteActionPerformed
        frmAgregarPacientes2 paciente = new frmAgregarPacientes2();
        jDesktopPane1.add(paciente);
        ventanas.add(paciente);
        paciente.show();
    }//GEN-LAST:event_MiPacienteActionPerformed

    private void MiSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MiSalirActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_MiSalirActionPerformed

    private void empleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empleadoActionPerformed
   frmAgregarEmpleado empleado = new frmAgregarEmpleado();
        jDesktopPane1.add(empleado);
        ventanas.add(empleado);
        empleado.show();        
    }//GEN-LAST:event_empleadoActionPerformed

    private void odontologiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_odontologiaActionPerformed
        if(this.tipoempleado==5){
        frmConsultaOdonto2 odontologo = new frmConsultaOdonto2();
        jDesktopPane1.add(odontologo);
        ventanas.add(odontologo);
        odontologo.setVisible(true);
        }
        else{
        String cuentap=JOptionPane.showInputDialog("NUMERO DE CUENTA DEL PACIENTE");
        if(sql.versiesta("call buscar_paciente("+cuentap+")")){
        frmConsultaOdonto2 odontologo = new frmConsultaOdonto2(cuenta,cuentap);
        jDesktopPane1.add(odontologo);
        ventanas.add(odontologo);
        odontologo.setVisible(true); }
        else{
        JOptionPane.showMessageDialog(null, "ERROR: PACIENTE NO REGISTRADO");
        frmConsultaOdonto2 odontologo = new frmConsultaOdonto2();
        jDesktopPane1.add(odontologo);
        ventanas.add(odontologo);
        odontologo.setVisible(true);
        }
        
        }
    }//GEN-LAST:event_odontologiaActionPerformed

    private void generalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generalActionPerformed
        if(this.tipoempleado==5){
        frmConsultaGeneral general = new frmConsultaGeneral();
        jDesktopPane1.add(general);
        general.show();
        ventanas.add(general);}
        else{
        String cuentap=JOptionPane.showInputDialog("NUMERO DE CUENTA DEL PACIENTE");
        if(sql.versiesta("call buscar_paciente("+cuentap+")")){
        frmConsultaGeneral general = new frmConsultaGeneral(cuenta,cuentap);
        jDesktopPane1.add(general);
        general.show();
        ventanas.add(general);
        }
        else{
        {JOptionPane.showMessageDialog(null, "ERROR: PACIENTE NO REGISTRADO");
        frmConsultaGeneral general = new frmConsultaGeneral();
        jDesktopPane1.add(general);
        general.show();
        ventanas.add(general);
        }
        }}
// TODO add your handling code here:
    }//GEN-LAST:event_generalActionPerformed

    private void psicologoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_psicologoActionPerformed
        if(this.tipoempleado==5){
        frmConsultaPsico psicologo = new frmConsultaPsico();
        jDesktopPane1.add(psicologo);
        psicologo.setVisible(true);
        ventanas.add(psicologo);
        }else{
        frmConsultaPsico psicologo = new frmConsultaPsico(cuenta);
        jDesktopPane1.add(psicologo);
        psicologo.setVisible(true);
        ventanas.add(psicologo);
        }
    }//GEN-LAST:event_psicologoActionPerformed

    private void preclinicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preclinicaActionPerformed
        if(this.tipoempleado==5){
        frmPreclinica1 preclinica = new frmPreclinica1();
        jDesktopPane1.add(preclinica);
        ventanas.add(preclinica);
        preclinica.show();}else{
        frmPreclinica1 preclinica = new frmPreclinica1(this.cuenta);
        jDesktopPane1.add(preclinica);
        ventanas.add(preclinica);
        preclinica.show();
        
        }
    }//GEN-LAST:event_preclinicaActionPerformed

   
    frmReceta receta = new frmReceta(this);
    
    public void desbloquear(){
        this.jMenuItem5.setEnabled(true);
    }
    
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed

            String cuentap= JOptionPane.showInputDialog(null, "Numero de cuenta del Paciente");
            if(sql.versiesta("call buscar_paciente(" + cuentap + ")"))
            {
                receta.actualizar(cuenta, cuentap);
                receta.cargarfecha();
                receta.setVisible(true);
                this.jMenuItem5.setEnabled(false);
            }   
            else
            {
                JOptionPane.showMessageDialog(null, "El Paciente no existe");
            }
        
        
        
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void M1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M1ActionPerformed
        frmAntecedentes_Personales frmap = new frmAntecedentes_Personales();
        this.jDesktopPane1.add(frmap);
        ventanas.add(frmap);
        frmap.show();
    }//GEN-LAST:event_M1ActionPerformed

    private void M2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M2ActionPerformed
        frmAntecedentes_Familiares frmaf = new frmAntecedentes_Familiares();
        this.jDesktopPane1.add(frmaf);
        ventanas.add(frmaf);
        frmaf.show();        // TODO add your handling code here:
    }//GEN-LAST:event_M2ActionPerformed

    private void M3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M3ActionPerformed
        frmAntecedentes_Toxicologicos frmat = new frmAntecedentes_Toxicologicos();
        this.jDesktopPane1.add(frmat);
        ventanas.add(frmat);
        frmat.show();
    }//GEN-LAST:event_M3ActionPerformed

    private void M4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M4ActionPerformed
        frmExamen_Fisico frmef = new frmExamen_Fisico();
        this.jDesktopPane1.add(frmef);
        ventanas.add(frmef);
        frmef.show();
    }//GEN-LAST:event_M4ActionPerformed

    private void M5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M5ActionPerformed
        frm_Carreras frmca = new frm_Carreras();
        this.jDesktopPane1.add(frmca);
        ventanas.add(frmca);
        frmca.show();
    }//GEN-LAST:event_M5ActionPerformed
    Medicamentos medicamentos = new Medicamentos();
    private void M7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M7ActionPerformed
        // TODO add your handling code here:
        if(this.tipoempleado==5){
        medicamentos.validar();
        medicamentos.llenartabla();
        medicamentos.setVisible(true);
        }else{
        medicamentos.an();
        medicamentos.llenartabla();
        medicamentos.setVisible(true);}
    }//GEN-LAST:event_M7ActionPerformed

    private void visitasMedicasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_visitasMedicasMouseClicked
        frmVisitasMedicas viMe = new frmVisitasMedicas();
        this.jDesktopPane1.add(viMe);
        ventanas.add(viMe);
        viMe.setVisible(true);
    }//GEN-LAST:event_visitasMedicasMouseClicked

    private void citaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_citaActionPerformed
        // TODO add your handling code here:
        ingresocola ig= new ingresocola();
        jDesktopPane1.add(ig);
        ventanas.add(ig);
        ig.setVisible(true);
        
        
        cola c1= new cola(4,false);
        jDesktopPane1.add(c1);
        ventanas.add(c1);
        c1.setVisible(true);
        
        cola c2= new cola(3,false);
        jDesktopPane1.add(c2);
        ventanas.add(c2);
        c2.setVisible(true);
        
        
      
        
    }//GEN-LAST:event_citaActionPerformed

    private void M6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M6ActionPerformed
        // TODO add your handling code here:
        frmAnticonceptivos frmca = new frmAnticonceptivos();
        this.jDesktopPane1.add(frmca);
        ventanas.add(frmca);
        frmca.setVisible(true);
    }//GEN-LAST:event_M6ActionPerformed
    Historial_Recetas hr = new Historial_Recetas();
    private void M8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M8ActionPerformed
        hr.setVisible(true);
        
    }//GEN-LAST:event_M8ActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> {
            new frm_Principal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem M1;
    private javax.swing.JMenuItem M2;
    private javax.swing.JMenuItem M3;
    private javax.swing.JMenuItem M4;
    private javax.swing.JMenuItem M5;
    private javax.swing.JMenuItem M6;
    private javax.swing.JMenuItem M7;
    private javax.swing.JMenuItem M8;
    private javax.swing.JMenu Mantenimiento;
    private javax.swing.JMenu MbAgregar;
    private javax.swing.JMenu MbConsulta;
    private javax.swing.JMenu MbOpciones;
    private javax.swing.JMenu MbReceta;
    private javax.swing.JMenuItem MiPaciente;
    private javax.swing.JMenuItem MiSalir;
    private javax.swing.JMenuItem Micerrar;
    private javax.swing.JMenuItem cita;
    private javax.swing.JMenuItem empleado;
    private javax.swing.JMenuItem general;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JMenuItem odontologia;
    private javax.swing.JMenuItem preclinica;
    private javax.swing.JMenuItem psicologo;
    private javax.swing.JMenu visitasMedicas;
    // End of variables declaration//GEN-END:variables
}
