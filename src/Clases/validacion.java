package Clases;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;

public class validacion {
Date fecha = new Date();
    Calendar cal = new GregorianCalendar();
    int year = cal.get(Calendar.YEAR);
    int mes=cal.get(Calendar.MONTH)+1;
    int dia=cal.get(Calendar.DATE);

    public String getfecha(JComboBox cmbdia, JComboBox cmbmes, JComboBox cmbaño) {
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
        return año + "-" + mes + "-" + dia;
    }

    public void cargarCombos(JComboBox cmbdia, JComboBox cmbmes, JComboBox cmbaño) {
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

    public void combodia(JComboBox cmbdia, JComboBox cmbmes, JComboBox cmbaño) {
      DefaultComboBoxModel mo = (DefaultComboBoxModel)cmbdia.getModel();
        int dia=mo.getSize();
        int mes = cmbmes.getSelectedIndex() + 1;
        int año = Integer.valueOf(cmbaño.getSelectedItem().toString());
        switch (mes) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                for (int i = dia+1; i <= 31; i++) {
                    mo.addElement(i);
                }
                break;
            case 2:
                if (año % 4 == 0) {
                    if (dia ==28) {
                        mo.addElement(29);
                    } else {
                        for (int i = dia; i >29; i--) {
                            mo.removeElementAt(29);
                        }
                    }
                } else {
                      for (int i = dia; i >28; i--) {
                             mo.removeElementAt(28);
                        }
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if (dia==31) {
                    mo.removeElementAt(30);
                } else {
                    if(dia==28){
                        mo.addElement(29);
                        mo.addElement(30);
                        break;
                    }else if(dia==29){
                        mo.addElement(30);
                    }  
                }
                break;
        }
    }

    public void cargarEdad(JComboBox cmb) {
        cmb.removeAllItems();
        for (int i = 0; i < 100; i++) {
            cmb.addItem(i);
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

    public void comprobarFormato(JToggleButton btnampm) {
        if (btnampm.isSelected()) {
            btnampm.setText("PM");
        } else {
            btnampm.setText("AM");
        }
    }
     public void obtenerfecha(String sql, JComboBox A[]) {
                String fecha[] = (sql.split("-"));
                A[0].setSelectedItem(fecha[0]);
                A[1].setSelectedIndex((Integer.valueOf(fecha[1]) - 1));
                A[2].setSelectedItem(fecha[2]);
    }
     public String getHora(){
         String a[]=cal.getTime().toString().split(" ");
         return a[3];
     }
      public String getfecha(){
         return year+":"+mes+":"+dia;
     }
      public String getfechahora(){
         return getfecha()+" "+getHora();
     }
     public String obtenerEdad(String f){
         String fecha[]=f.split("-");
         int fe[]=new int[3];
         for (int i = 0; i < 3; i++) {
             fe[i]=Integer.valueOf(fecha[i]);
         }
         int edad=year-fe[0]-1;
         System.out.println(""+mes);
         if(fe[1]==mes){
            if(dia>=fe[2]){
                 edad++; 
             }
         }else if (mes>fe[1]){
                 edad++;
             }
         return ""+edad;
     }
    
}
