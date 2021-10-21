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
  
  public abstract void promedioAlumno(){
  }
  
  public abstract void aprobadosCurso(){
  }
  
  public abstract void reprobadosCurso(){
  }
}
