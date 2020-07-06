
package Clases;

import Interfaz.cola;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


public class hilo_cola extends Thread{
    int es=0;
    DefaultTableModel modelo;
    validacion va=new validacion();
    String fecha= va.getfecha();
    cola c;
    public void cargar(int e, DefaultTableModel mo, cola co){
        es=e;
        modelo=mo;
        c=co;
    }
    int t=0;
    public void llenartabla(){
     modelo.setRowCount(0);
     sql.RegistrosTabla("call cargar_cola('"+fecha+"',"+es+")",modelo);
     t=modelo.getRowCount();
    }
    public void ordenar(){
    for(int a=1; a<modelo.getRowCount();a++){
    for(int b=0; b<modelo.getRowCount();b++){
    int temp=0;
    String cuenta="";
    String nombre="";
    String apellido="";
    String medico="";
    if(Integer.parseInt(modelo.getValueAt(b, 0).toString())>Integer.parseInt(modelo.getValueAt(a, 0).toString())){
        temp=Integer.parseInt(modelo.getValueAt(b, 0).toString());
        cuenta=modelo.getValueAt(b, 1).toString();
        nombre=modelo.getValueAt(b, 2).toString();
        apellido=modelo.getValueAt(b, 3).toString();
        medico=modelo.getValueAt(b, 4).toString();
        modelo.setValueAt(modelo.getValueAt(a, 0), b, 0);
        modelo.setValueAt(modelo.getValueAt(a, 1), b, 1);
        modelo.setValueAt(modelo.getValueAt(a, 2), b, 2);
        modelo.setValueAt(modelo.getValueAt(a, 3), b, 3);
        modelo.setValueAt(modelo.getValueAt(a, 4), b, 4);
        modelo.setValueAt(temp, a, 0);
        modelo.setValueAt(cuenta, a, 1);
        modelo.setValueAt(nombre, a, 2);
        modelo.setValueAt(apellido, a, 3);
        modelo.setValueAt(medico, a, 4);
      
    }
    }
    }
    }
    
    public MySql sql=new MySql();
    Random x= new Random();
    @Override
    public void run(){
        while(true){
            try {
                Thread.sleep(1000);
                llenartabla();
                if(t>0){
                c.bloquear(true);
                }else{c.bloquear(false);}
                
            } catch (InterruptedException ex) {
                Logger.getLogger(hilo_cola.class.getName()).log(Level.SEVERE, null, ex);
            }
            ordenar();
            
        }
        
    }
}
