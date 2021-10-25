package com.mycompany.proyectopoo;
import java.io.*;

public abstract class AlumnoMatricula {
  
  //Instancias de Clase
    private String rutPersona;
    private double promedioFinal;
  //Constructor
    
    public AlumnoMatricula(String rutPersona, double promedio){
        this.rutPersona = rutPersona;
        this.promedioFinal = promedio;
    }
 
    public abstract void evaluacionFinal(double nota);

//setter y getters
    public String getRutPersona() {
        return rutPersona;
    }
    
    public double getPromedioFinal()
    {
        return this.promedioFinal;
    }
    public void setPromedioFinal(double promedioFinal)
    {
        if (promedioFinal>=1.0 && promedioFinal<= 7.0)
            this.promedioFinal = promedioFinal;
    }
}
