/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author user
 */
public class NewClass {
    public static void main(String[] args) {
        validacion va=new validacion();
        System.out.println("edad: "+va.obtenerEdad("1997-04-16"));
        System.out.println(""+va.getfechahora());
    }
}

