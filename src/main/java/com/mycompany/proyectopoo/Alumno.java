package com.mycompany.proyectopoo;
import java.io.*;
import java.util.ArrayList;

public class Alumno extends Persona{
  
  //Instancia de Clase
  private ArrayList<Double> listaNotasAlumno;
  
 //Constructor
  
  public Alumno(ArrayList<Double> listaNotasAlumno){
    
    this.listaNotasAlumno = new ArrayList<>();
    this.listaNotasAlumno.addAll(getNotas(rutPersona));
    
  }
  
  //metodos (hacer getters y setter en el netbeans)
  
  
  
}
