/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectopoo;

/**
 *
 * @author Vicente
 */
public class ControladorVentanaMenu{

    
    public ControladorVentanaMenu() {
    }
    
    
    public void boton1Presionado()
    {
        VentanaMostrar ventanaMostrar = new VentanaMostrar("Alumnos");
        ventanaMostrar.setVisible(true);
        
    }
    public void boton2Presionado()
    {
        VentanaMostrar ventanaMostrar = new VentanaMostrar("Asignaturas");
        ventanaMostrar.setVisible(true);
    }
    
}
