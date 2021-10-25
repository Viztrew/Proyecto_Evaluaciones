/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectopoo;


public interface EstadoMatricula {
    //
    public  String aprobado = "APROBADO";
    public String reprobado = "REPROBADO"; 
    public   String desconocido = "DESCONOCIDO";
    public abstract void reportarEstado();
    public abstract void actualizarEstado();
    
    
}
