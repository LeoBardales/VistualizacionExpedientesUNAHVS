
package modificar;

import Clases.MySql;
import Clases.validacion;
import Interfaz.frmConsultaGeneral;
import Interfaz.frmConsultaOdonto2;
import Interfaz.frm_Principal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JTextField;
import Mantenimiento.Medicamentos;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;


/**
 *
 * @author Leonardo
 */
public class frmReceta extends javax.swing.JFrame implements Printable {
    validacion va = new validacion();
    Calendar cal = new GregorianCalendar();
     MySql sql = new MySql();
     
    /**
     * Creates new form frmReceta
     */
   
    public frmReceta() {
        initComponents();
        this.jButton1.setVisible(false);
        this.jButton2.setVisible(false);
        this.jButton3.setVisible(false);
    }
    
    int id_receta=0;
    public void cargar_receta(int id){
        receta="";
        String f_r="";
        String f="";
        id_receta=id;
        
        
        
           acumulador.removeAll(acumulador);
        
        JArea.setText("");
        try {
            sql.conec();
            sql.rs = sql.s.executeQuery("call buscar_receta1("+id+")");
            while (sql.rs.next()) {
             this.JND.setText(sql.rs.getString(1));
             this.JAD.setText(sql.rs.getString(2));
             f_r=sql.rs.getString(3);
            }
            sql.rs.close();
            
            
            sql.rs = sql.s.executeQuery("call buscar_receta2("+id+")");
            while (sql.rs.next()) {
                JNombre.setText(sql.rs.getString(1));
                JApellido.setText(sql.rs.getString(2));
                estado.setText(sql.rs.getString(3));
                f=sql.rs.getString(4);
                sql.conexion.close();
            }
            sql.rs.close();
            sql.conexion.close();
        } catch (Exception e) {
        }
        cargar_detalles(id);
       
        validacion va=new validacion();
        JEdad.setText(va.obtenerEdad(f));
        String fp[] = (f_r.split(" "));
        f_r=fp[0];
        String fp1[]=(f_r.split("-"));
        cargarfechaReceta(fp1[1]);
        int a=Integer.parseInt(fp1[0])-2000;
        this.Jano.setText(String.valueOf(a));
        Jdia.setText(fp1[2]);
        val=true;
        
        this.co2.setText(""+id);
         validarReceta();
    }
    public void cargar_detalles(int id){
    try {
        int c=0;
            sql.conec();
            sql.rs = sql.s.executeQuery("call cargar_receta("+id+")");
            while (sql.rs.next()) {
               
                agregar(sql.rs.getString(2)+" "+sql.rs.getString(3)+" "+sql.rs.getString(4));
                agregar_lista(sql.rs.getInt(1),sql.rs.getInt(2));
                c++;
            }
            sql.rs.close();
            sql.conexion.close();
        } catch (Exception e) {
        }
   
    }
    public void agregar_lista(int x, int y){
    lista l= new lista(x,y);
    acumulador.add(l);
    }
    
    public void validarReceta(){
    if(estado.getText().equals("PENDIENTE")){
    anular.setVisible(true);
    entregar.setVisible(true);
    }
    else{
    anular.setVisible(false);
    entregar.setVisible(false);
    }
    }
    boolean val=false;
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex ) throws PrinterException{
        if (pageIndex==0){
            Graphics2D g2d= (Graphics2D) graphics;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            this.printAll(graphics);
            return PAGE_EXISTS;
        }
    return NO_SUCH_PAGE;
    }
    
    int op=0;
    frmConsultaOdonto2 conO;
    frmConsultaGeneral con;
    frm_Principal p;
    
    public frmReceta(frmConsultaGeneral c) {
        initComponents();
        con=c;
        op=2;
        entregar.setVisible(false);
        anular.setVisible(false);
        estado.setVisible(false);
    }
    public frmReceta(frmConsultaOdonto2 c) {
        initComponents();
        conO=c;
        op=3;
        entregar.setVisible(false);
        anular.setVisible(false);
        estado.setVisible(false);
    }
    
    public frmReceta(frm_Principal p) {
        initComponents();
        this.p=p;
        op=1;
        entregar.setVisible(false);
        anular.setVisible(false);
        estado.setVisible(false);
    }
    
    
    public void desbloquear(){
    if(op==1){p.desbloquear();}
    if(op==2){con.desbloquear();}
    if(op==3){conO.desbloquear();}
    }
    
    public void actualizar(String ce, String cp) {
         JTextField A[]= {this.JNombre,this.JApellido};
         JTextField B[]= {this.JND,this.JAD};
         sql.camposArreglo("call buscar_Persona("+cp+")", A);
         sql.camposArreglo("call buscar_Persona("+ce+")", B);
         cuenta_e=ce;
         cuenta_p=cp;
         iddoc = sql.valor("select buscar_iddoctores(" + cuenta_e + ")");
         idpc = sql.valor("select buscar_idpaciente(" + cuenta_p + ")");
        this.jButton4.setVisible(false);
        this.co1.setVisible(false);
        this.co2.setVisible(false);
        this.co3.setVisible(false);
        cargarf_n();
    }
    
    void cargarf_n(){
        
        
        String fecha="";
    try {
            sql.conec();
            sql.rs = sql.s.executeQuery("select fecha_nacimiento from persona where nocuenta="+cuenta_p);
            while (sql.rs.next()) {
                fecha= (sql.rs.getObject(1).toString());
            }
            sql.rs.close();
            sql.conexion.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        validacion va=new validacion();
        JEdad.setText(va.obtenerEdad(fecha));
    }
    String f;
    String cuenta_e;
    String cuenta_p;
    public void cargarfecha() {
    String mes="";
    switch(cal.get(Calendar.MONTH)){
        case 0:
            mes="Enero";
            break;
        case 1:
            mes="Febrero";
            break;
        case 2:
            mes="Marzo";
            break;
        case 3:
            mes="Abril";
            break;
        case 4:
            mes="Mayo";
            break;
        case 5:
            mes="Junio";
            break;
        case 6:
            mes="Julio";
            break;
        case 7:
            mes="Agosto";
            break;
        case 8:
            mes="Septiembre";
            break;
        case 9:
            mes="Obtubre";
            break;
        case 10:
            mes="Noviembre";
            break;
        case 11:
            mes="Diciembre";
            break;
    }
    this.Jdia.setText(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
    this.Jano.setText(String.valueOf(cal.get(Calendar.YEAR)-2000));
    this.Jmes.setText(mes);
    }
    public void cargarfechaReceta(String m) {
    String mes="";
    switch(m){
        case "01":
            mes="Enero";
            break;
        case "02":
            mes="Febrero";
            break;
        case "03":
            mes="Marzo";
            break;
        case "04":
            mes="Abril";
            break;
        case "05":
            mes="Mayo";
            break;
        case "06":
            mes="Junio";
            break;
        case "07":
            mes="Julio";
            break;
        case "08":
            mes="Agosto";
            break;
        case "09":
            mes="Septiembre";
            break;
        case "10":
            mes="Obtubre";
            break;
        case "11":
            mes="Noviembre";
            break;
        case "12":
            mes="Diciembre";
            break;
    }
    this.Jmes.setText(mes);
    }
    String receta="";
    
    
    public void agregar(String x){
     receta=receta+x+"\n";
     JArea.setText(receta);
    }
    
    public ArrayList<lista> acumulador= new ArrayList();
   
    public static class  lista {
    public int id;
    public int cant_inicial;
    public int cant;
    public String nombre;
    public String indicaciones;
    public String fecha;
    
     public lista(int co, int c, int ci, String n, String i, String f ){
    id=co;
    cant=c;
    cant_inicial=ci;
    nombre=n;
    indicaciones=i;
    fecha=f;
    }
     public lista(int co, int c){
    id=co;
    cant=c;
    }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        JNombre = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        JApellido = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        co1 = new javax.swing.JLabel();
        JEdad = new javax.swing.JTextField();
        co3 = new javax.swing.JSeparator();
        Jdia = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        Jmes = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        Jano = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        JArea = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        JND = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        JAD = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        co2 = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        anular = new javax.swing.JButton();
        entregar = new javax.swing.JButton();
        estado = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 204, 255)));
        jPanel1.setLayout(null);

        jLabel3.setText("Sector El Playón, contiguo a Residencial Villas del Sol San Pedro Sula");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(170, 60, 500, 14);

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel2.setText("Universidad Nacional Autónoma de Honduras");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(160, 30, 510, 29);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/UNAH.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(540, 480, 240, 120);

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel4.setText("Nombre:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(120, 100, 80, 19);

        JNombre.setEditable(false);
        JNombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JNombre.setBorder(null);
        jPanel1.add(JNombre);
        JNombre.setBounds(190, 100, 180, 17);

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(190, 120, 190, 10);

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel5.setText("Paciente");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(20, 100, 80, 19);

        JApellido.setEditable(false);
        JApellido.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JApellido.setBorder(null);
        jPanel1.add(JApellido);
        JApellido.setBounds(460, 100, 190, 17);

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator2);
        jSeparator2.setBounds(460, 120, 190, 10);

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel6.setText("Apellido:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(390, 100, 80, 19);

        co1.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        co1.setText("Codigo:");
        jPanel1.add(co1);
        co1.setBounds(40, 490, 70, 19);

        JEdad.setEditable(false);
        JEdad.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JEdad.setBorder(null);
        jPanel1.add(JEdad);
        JEdad.setBounds(710, 100, 40, 17);

        co3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.add(co3);
        co3.setBounds(100, 510, 60, 10);

        Jdia.setEditable(false);
        Jdia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Jdia.setBorder(null);
        jPanel1.add(Jdia);
        Jdia.setBounds(140, 150, 40, 17);

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel8.setText("San Pedro Sula,");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(20, 150, 130, 19);

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator4);
        jSeparator4.setBounds(140, 170, 40, 10);

        Jmes.setEditable(false);
        Jmes.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Jmes.setBorder(null);
        jPanel1.add(Jmes);
        Jmes.setBounds(220, 150, 90, 17);

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel9.setText("de");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(190, 150, 20, 19);

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator5);
        jSeparator5.setBounds(220, 170, 90, 10);

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel10.setText("del 20");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(320, 150, 50, 19);

        Jano.setEditable(false);
        Jano.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Jano.setBorder(null);
        jPanel1.add(Jano);
        Jano.setBounds(370, 150, 40, 20);

        jSeparator6.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator6);
        jSeparator6.setBounds(370, 170, 40, 10);

        jSeparator7.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator7);
        jSeparator7.setBounds(10, 190, 750, 10);

        JArea.setEditable(false);
        JArea.setColumns(20);
        JArea.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        JArea.setRows(5);
        jScrollPane1.setViewportView(JArea);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(40, 210, 720, 270);

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel11.setText("Medico");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(10, 540, 60, 19);

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel12.setText("Nombre:");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(80, 540, 80, 19);

        JND.setEditable(false);
        JND.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JND.setBorder(null);
        jPanel1.add(JND);
        JND.setBounds(150, 540, 140, 17);

        jSeparator8.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator8);
        jSeparator8.setBounds(150, 560, 140, 10);

        JAD.setEditable(false);
        JAD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JAD.setBorder(null);
        jPanel1.add(JAD);
        JAD.setBounds(370, 540, 160, 17);

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel13.setText("Apellido:");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(300, 540, 70, 19);

        jSeparator9.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator9);
        jSeparator9.setBounds(370, 560, 160, 10);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Captura.PNG"))); // NOI18N
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel14);
        jLabel14.setBounds(750, 10, 51, 49);

        jButton1.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jButton1.setText("Agregar");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(170, 490, 100, 30);

        jButton2.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jButton2.setText("Guardar");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(40, 490, 100, 30);

        jButton3.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jButton3.setText("Cancelar");
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(300, 490, 100, 30);

        jButton4.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jButton4.setText("Imprimir");
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4);
        jButton4.setBounds(430, 490, 100, 30);

        jLabel15.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel15.setText("Edad:");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(660, 100, 50, 19);

        co2.setEditable(false);
        co2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        co2.setBorder(null);
        jPanel1.add(co2);
        co2.setBounds(100, 490, 60, 17);

        jSeparator10.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator10);
        jSeparator10.setBounds(710, 120, 40, 10);

        anular.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        anular.setText("ANULAR");
        anular.setBorder(null);
        anular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anularActionPerformed(evt);
            }
        });
        jPanel1.add(anular);
        anular.setBounds(300, 570, 100, 30);

        entregar.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        entregar.setText("ENTREGAR");
        entregar.setBorder(null);
        entregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entregarActionPerformed(evt);
            }
        });
        jPanel1.add(entregar);
        entregar.setBounds(430, 570, 100, 30);

        estado.setEditable(false);
        estado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        estado.setBorder(null);
        jPanel1.add(estado);
        estado.setBounds(150, 580, 140, 17);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 803, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jLabel14MouseClicked
    Medicamentos medicamentos = new Medicamentos(this);
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here: 
        medicamentos.llenartabla();
        medicamentos.show();
        
        
    }//GEN-LAST:event_jButton1ActionPerformed
    int iddoc=-1;
    int idpc=-1;
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         
         if(!JArea.getText().equals("")){
        validacion va=new validacion();
        f= va.getfechahora();
       sql.EjecutarConsultas("call `insertar_receta`('"+f+"', "+iddoc+","+idpc+")");
        int idreceta=sql.valor("select buscar_idreceta('"+f+"')");
       
        for(int x=0; x<acumulador.size(); x++)
        {
           
           sql.EjecutarConsultas("call insertar_detalle_receta("+idreceta+","+acumulador.get(x).id+",'"+acumulador.get(x).indicaciones+"',"+acumulador.get(x).cant+")");
           
        }
       
        JOptionPane.showMessageDialog(null,"Guardada con Exito");
        this.jButton2.setVisible(false);
        this.jButton1.setVisible(false);
        this.jButton3.setVisible(false);
        medicamentos.dispose();
        this.jButton4.setVisible(true);
        this.co1.setVisible(true);
        this.co2.setVisible(true);
        this.co3.setVisible(true);
        co2.setText(""+idreceta);
        v=1;
        if(medicamentos.nm!=null){medicamentos.nm.dispose();} 
        if(medicamentos.mm!=null){medicamentos.mm.dispose();}
        }else{
        JOptionPane.showMessageDialog(null,"Error: No Agrego nada a la receta");
        }
   
    }//GEN-LAST:event_jButton2ActionPerformed
    int v=0;
    public int contador=0;
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        for(int x=0; x<acumulador.size(); x++)
        {
        sql.EjecutarConsultas("call update_M("+acumulador.get(x).id+",'"+acumulador.get(x).nombre+"',"+acumulador.get(x).cant_inicial+",'"+acumulador.get(x).fecha+"')");
        }
        acumulador.removeAll(acumulador);
        this.dispose();
        medicamentos.dispose();
        if(medicamentos.nm!=null){medicamentos.nm.dispose();} 
        if(medicamentos.mm!=null){medicamentos.mm.dispose();}
        receta="";
        this.JArea.setText("");
        desbloquear();
        contador=0;
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintable(this);
        if(printerJob.printDialog()){
            this.jButton4.setVisible(false);
            this.jLabel14.setVisible(false);
            try {
                printerJob.print();
            } catch (Exception e) {
                System.out.println("Error"+ e);
                
            }
        }
        this.jButton4.setVisible(true);
         this.jLabel14.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        this.dispose();
        if(v==0 && val==false){
          for(int x=0; x<acumulador.size(); x++)
        {
        sql.EjecutarConsultas("call update_M("+acumulador.get(x).id+",'"+acumulador.get(x).nombre+"',"+acumulador.get(x).cant_inicial+",'"+acumulador.get(x).fecha+"')");
        acumulador.remove(x);
        }
        JOptionPane.showMessageDialog(null,"Receta Cancelada");
        this.dispose();
        medicamentos.dispose();
        if(medicamentos.nm!=null){medicamentos.nm.dispose();}  
        if(medicamentos.mm!=null){medicamentos.mm.dispose();}  
        this.JArea.setText("");
        }else{
        if(val==false){
        this.jButton4.setVisible(false);
        this.co1.setVisible(false);
        this.co2.setVisible(false);
        this.co3.setVisible(false);
        this.JArea.setText("");
        receta="";
        this.jButton2.setVisible(true);
        this.jButton1.setVisible(true);
        this.jButton3.setVisible(true);
        v=0;}
        }
        acumulador.removeAll(acumulador);
        contador=0;
        desbloquear();
        
    }//GEN-LAST:event_formWindowClosed

    private void anularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anularActionPerformed
        // TODO add your handling code here:
        sql.EjecutarConsultas("call estado(1,"+id_receta+")");
        for(int i=0;i<acumulador.size();i++){
        sql.EjecutarConsultas("call Reponer_M("+acumulador.get(i).id+","+acumulador.get(i).cant+")");
        }
        estado.setText("ANULADA");
        anular.setVisible(false);
        entregar.setVisible(false);
    }//GEN-LAST:event_anularActionPerformed

    private void entregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entregarActionPerformed
        // TODO add your handling code here:
        sql.EjecutarConsultas("call estado(2,"+id_receta+")");
        anular.setVisible(false);
        entregar.setVisible(false);
        estado.setText("ENTREGADA");
    }//GEN-LAST:event_entregarActionPerformed

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
            java.util.logging.Logger.getLogger(frmReceta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmReceta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmReceta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmReceta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmReceta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField JAD;
    private javax.swing.JTextField JApellido;
    private javax.swing.JTextArea JArea;
    private javax.swing.JTextField JEdad;
    private javax.swing.JTextField JND;
    private javax.swing.JTextField JNombre;
    private javax.swing.JTextField Jano;
    private javax.swing.JTextField Jdia;
    private javax.swing.JTextField Jmes;
    private javax.swing.JButton anular;
    private javax.swing.JLabel co1;
    private javax.swing.JTextField co2;
    private javax.swing.JSeparator co3;
    private javax.swing.JButton entregar;
    private javax.swing.JTextField estado;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    // End of variables declaration//GEN-END:variables
}
