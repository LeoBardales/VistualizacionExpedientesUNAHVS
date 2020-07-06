package Clases;

import java.sql.*;
import java.io.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class MySql {
    String BasedeDatos = "clinica";
    String username = "ADMIN";
    String password = "1881";
    String puerto = ":3306";
    public Statement s;
    public Connection conexion;
    public ResultSet rs;
    //08121997LEBM
    public void conec() {//localhost
        try {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://192.168.0.14" + puerto + "/" + BasedeDatos, username, password);
            s = conexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void RegistrosTablaMetodos(String sql, DefaultTableModel modelo) {
         try {
            modelo.setRowCount(0);
            conec();
            rs = s.executeQuery(sql);
            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getString(1),rs.getBoolean(2)});
            }
            rs.close();
            conexion.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     
    public int obtenerID(String sql,int pos){
         int id = 0,i=1;
        try {
            conec();
            rs = s.executeQuery(sql);
            for (int j = 1; j <=pos; j++) {
                rs.next();
                id=rs.getInt(1);
            }
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    public boolean versiesta(String XX) {
        boolean v = false;
        try {
            conec();
            rs = s.executeQuery(XX);
            while (rs.next()) {
                v = true;
            }
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }
    
    

    public void CamposCombo(String XX, JComboBox cmb) {
        cmb.removeAllItems();
        try {
            conec();
            rs = s.executeQuery(XX);
            while (rs.next()) {
                cmb.addItem(rs.getObject(1).toString());
            }
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String obtenerValor(String sql){
        String valor="";
         try {
            conec();
            rs = s.executeQuery(sql);
            while (rs.next()) {
                valor=rs.getString(1);
            }
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valor;
    }
    public void RegistrosTabla(String sql, DefaultTableModel modelo) {
        try {
            modelo.setRowCount(0);
            conec();
            rs = s.executeQuery(sql);
            while (rs.next()) {
                Object[] fila = new Object[modelo.getColumnCount()];
                for (int i = 0; i < modelo.getColumnCount(); i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
            rs.close();
            conexion.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void RegistrosTabla1(String sql, DefaultTableModel modelo, int t) {
       int ta=0;
        try {
            modelo.setRowCount(0);
            conec();
            rs = s.executeQuery(sql);
            while (rs.next()) {
                Object[] fila = new Object[modelo.getColumnCount()];
                for (int i = 0; i < modelo.getColumnCount(); i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
                ta++;
            }
            rs.close();
            conexion.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        t=ta;
        System.out.println(t);
    }

    public int EjecutarConsultas(String XX) {
        int res=0;
        try {
            conec();
            s.execute(XX);
            res=1;
            conexion.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
            JOptionPane.showMessageDialog(null, "Verificar la consulta \n" + XX, "ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return res;
    }

    public void camposArreglo(String sql, JTextField B[]) {
        try {
          
            conec();
            rs = s.executeQuery(sql);
              while (rs.next()) {
                for (int i = 0; i < B.length; i++) {
                    B[i].setText(rs.getObject(i + 1).toString());
 
                }
               
            }  
           
            rs.close();
            conexion.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    

 
    public void comboArreglo(String sql, JComboBox B[]) {
        try {
            conec();
            rs = s.executeQuery(sql);
            while (rs.next()) {
                for (int i = 0; i < B.length; i++) {
                    B[i].setSelectedItem(rs.getObject(i + 1));
                }
            }
            rs.close();
            conexion.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void obtenerJspinner(String sql,JSpinner A[]){
        try {
            conec();
            rs = s.executeQuery(sql);
            while (rs.next()) {
                for (int i = 0; i < A.length; i++) {
                    A[i].setValue(rs.getInt(i+1));
                }
            }
            rs.close();
            conexion.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void obtenerfecha(String sql, JComboBox A[]) {
        try {
            conec();
            rs = s.executeQuery(sql);
            while (rs.next()) {
                String fecha[] = (rs.getObject(1).toString().split("-"));
                A[0].setSelectedItem(fecha[0]);
                A[1].setSelectedIndex((Integer.valueOf(fecha[1]) - 1));
                A[2].setSelectedItem(fecha[2]);
            }
            rs.close();
            conexion.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void obtenerfechaNa(String sql, JTextField A) {
        try {
            conec();
            rs = s.executeQuery(sql);
            while (rs.next()) {
                String fecha = (rs.getString(1));
                A.setText(fecha.toString());
            }
            rs.close();
            conexion.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int CantidadRegistros(String XX) {
        int numero = 0;
        try {
            conec();
            rs = s.executeQuery(XX);
            while (rs.next()) {
                numero = rs.getInt(1);
            }
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numero;
    }

    public int valor(String XX) {
        int numero = 0;
        try {
            conec();
            s = conexion.createStatement();
            ResultSet rs = s.executeQuery(XX);
            while (rs.next()) {
                numero = rs.getInt(1);
            }
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numero;
    }

    public String[][] LlenarCampos(String XX) {
        String A[][] = new String[1][1];
        int C = 0;
        int i = 0;
        int j = 0;
        try {
            conec();
            rs = s.executeQuery(XX);
            C = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                i++;
            }
            A = new String[i][C];
            i = 0;
            rs = s.executeQuery(XX);
            while (rs.next()) {
                for (int x = 1; x <= C; x++) {
                    A[i][j] = rs.getString(x);
                    j++;
                }
                j = 0;
                i++;

            }
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return A;
    }

    public String[] ColumLlenar(String XX) {
        String A[] = new String[1];
        int i = 0;
        try {
            conec();
            rs = s.executeQuery(XX);
            A = new String[rs.getMetaData().getColumnCount()];
           while(rs.next()){
               A[i]=rs.getString(1);
               i++;
           }
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return A;
    }

    int contar = 0;
    int contar1 = 0;
    int T = 4;

    public void LlenarLista(String sqlx, JTable tablaAfiliados, DefaultTableModel afiliados) {
        JTableHeader header = tablaAfiliados.getTableHeader();
        header.setBackground(Color.yellow);

        String Noc[] = ColumLlenar(sqlx);

        System.out.println("tablaAfiliados.getColumnCount() " + tablaAfiliados.getColumnCount());

        for (int i = 0; i < tablaAfiliados.getColumnCount() - 1; i++) {
            TableColumn tcol = tablaAfiliados.getColumnModel().getColumn(i);
            tablaAfiliados.removeColumn(tcol);
        }

        for (int i = 0; i < Noc.length; i++) {
            afiliados.addColumn(Noc[i]);

        }

        for (int i = 0; i < Noc.length; i++) {
            TableColumn columnId = null;

            columnId = tablaAfiliados.getColumnModel().getColumn(i);

            columnId.setPreferredWidth(150);
        }
        tablaAfiliados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaAfiliados.setColumnSelectionAllowed(true);
        tablaAfiliados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaAfiliados.setColumnSelectionAllowed(true);

        try {
            contar = 0;
            contar1 = 0;
            conec();
            rs = s.executeQuery(sqlx);

            T = rs.getMetaData().getColumnCount();
            String a[] = new String[T];

            int cc = 0;

            for (int i = tablaAfiliados.getRowCount() - 1; i >= 0; i--) {
                afiliados.removeRow(i);
            }

            rs = s.executeQuery(sqlx);
            while (rs.next()) {

                for (int j = 1; j <= T; j++) {
                    a[contar] = rs.getString(j);
                    contar++;
                }

                afiliados.addRow(a);
                contar = 0;

                contar1++;

            }

            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String[] Llenar(String XX, String Campo) {
        String A[] = new String[1];
        int i = 0;
        try {
 conec();
             rs = s.executeQuery(XX);
            while (rs.next()) {
                i++;
            }
            A = new String[i];
            i = 0;
            rs = s.executeQuery(XX);

            while (rs.next()) {
                A[i] = rs.getString(Campo);
                i++;

            }

            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return A;
    }

    public void ingresoDeImagen(String x, String ruta) {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost" + puerto + "/" + BasedeDatos, username, password);
            PreparedStatement ps = conn.prepareStatement(x);
            conn.setAutoCommit(false);

            File file = new File(ruta);

            FileInputStream fis = new FileInputStream(file);
            ps.setBinaryStream(1, fis, (int) file.length());
            ps.executeUpdate();
            conn.commit();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ImageIcon[] ArregloImagen(String x, int NumeroCampo) {
        ImageIcon m[] = new ImageIcon[CantidadRegistros(x)];

        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost" + puerto + "/" + BasedeDatos, username, password);
            PreparedStatement ps = conn.prepareStatement(x);
            conn.setAutoCommit(false);
            ResultSet r = ps.executeQuery();
            int n = 0;
            while (r.next()) {
                byte[] i = null;
                i = r.getBytes(NumeroCampo);
                m[n] = new ImageIcon(i);
                n++;
            }
            r.close();
            conn.commit();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return m;

    }

    public ImageIcon sacarImagen(String x, int x1, int y1) {
        ImageIcon m = new ImageIcon();

        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost" + puerto + "/" + BasedeDatos, username, password);
            PreparedStatement ps = conn.prepareStatement(x);
            conn.setAutoCommit(false);
            ResultSet r = ps.executeQuery();
            while (r.next()) {
                byte[] i = null;
                i = r.getBytes(1);
                m = new ImageIcon(i);
            }
            r.close();
            conn.commit();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageIcon icon = m;
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(x1, y1, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);

        return newIcon;

    }

    public JLabel ImagenEtiqueta(String x, int x1, int y1) {
        ImageIcon m = new ImageIcon();
        JLabel yo = new JLabel();
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost" + puerto + "/" + BasedeDatos, username, password);
            PreparedStatement ps = conn.prepareStatement(x);
            conn.setAutoCommit(false);
            ResultSet r = ps.executeQuery();
            while (r.next()) {
                byte[] i = null;
                System.out.println(r.getString(1));
                i = r.getBytes("imagen");
                m = new ImageIcon(i);
            }
            r.close();
            conn.commit();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageIcon icon = m;
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(x1, y1, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);
        yo.setIcon(newIcon);
        yo.setSize(150, 150);

        return yo;

    }

    public double Total(String XX) {
        double numero = 0;
        try {

            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());

            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost" + puerto + "/" + BasedeDatos, username, password);

            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(XX);

            while (rs.next()) {
                numero = rs.getInt(1);

            }

            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numero;
    }

    public void copia(String ficheroOriginal, String ficheroCopia) {
        try {

            FileInputStream fileInput = new FileInputStream(ficheroOriginal);
            BufferedInputStream bufferedInput = new BufferedInputStream(fileInput);

            FileOutputStream fileOutput = new FileOutputStream(ficheroCopia);
            BufferedOutputStream bufferedOutput = new BufferedOutputStream(fileOutput);

            byte[] array = new byte[1000];
            int leidos = bufferedInput.read(array);
            while (leidos > 0) {
                bufferedOutput.write(array, 0, leidos);
                leidos = bufferedInput.read(array);
            }

            bufferedInput.close();
            bufferedOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    PreparedStatement prepareStatement(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

