
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

            
        }
        
    }
}
