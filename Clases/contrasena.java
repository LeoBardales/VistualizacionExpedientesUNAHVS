/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import static Interfaz.frmLogin.bytesToHex;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *
 * @author Brenedin Enrique
 */
public class contrasena {
    public contrasena(){
      MySql sql = new MySql();
    String contra;
    String algoritmo = "MD5";
    }
    public String generateHash(String contra, String algoritmo, byte[] sal) throws NoSuchAlgorithmException{
        MessageDigest digest = MessageDigest.getInstance(algoritmo);
        digest.reset();
        digest.update(sal);
        byte[] hash = digest.digest(contra.getBytes());
       return bytesToHex(hash);
        
    }
    
     public byte[] createSalt(){
        byte[] bytes= new byte[20];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);
        return bytes;
    }
    
    public String bytesToHex(byte[] in) {
    final StringBuilder builder = new StringBuilder();
    for(byte b : in) {
        builder.append(String.format("%02x", b));
    }
    return builder.toString();  
}
    
    public byte[] hexStringToByteArray(String s) {
    int len = s.length();
    byte[] data = new byte[len / 2];
    for (int i = 0; i < len; i += 2) {
        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                             + Character.digit(s.charAt(i+1), 16));
    }
    return data;
}
    
}
