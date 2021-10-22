package com.mycompany.proyectopoo;
import java.io.*;

public abstract class Persona{
  
  //Instancias de Clase
    private String rutPersona;

  //Constructor
    
    public Persona(String rutPersona){
        this.rutPersona = rutPersona;
    }
 

  //metodos, uno abstracto
   // promedio alumno se debe obtener desde alumno
  public abstract void promedioAlumno();
  
  //profesor deberia saber esto solamente
  public abstract void aprobadosCurso();
  
  public abstract void reprobadosCurso();
  
//setter y getters
  public String getRutPersona() {
        return rutPersona;
    }

    public void setRutPersona(String rutPersona) {
        this.rutPersona = rutPersona;
    }
}
